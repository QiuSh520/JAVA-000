package io.github.kimmking.gateway.outbound.okhttp;

import io.github.kimmking.gateway.outbound.HttpOutboundHandler;
import io.github.kimmking.gateway.outbound.NamedThreadFactory;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandler implements HttpOutboundHandler {
    private OkHttpClient httpClient;
    private ExecutorService proxyService;
    private List<String> backendUrlList;
    private HttpEndpointRouter httpEndpointRouter;

    public OkhttpOutboundHandler(String backendUrl) {
        System.out.println("OkHttp is coustructed to request backend");

        httpEndpointRouter = new RandomHttpEndpointRouter();

        String[] backendUrlArr = backendUrl.split(";");
        if (backendUrl == null) {
            throw new RuntimeException("代理后端服务不存在");
        }

        backendUrlList = new ArrayList<>();
        for (int i = 0; i < backendUrlArr.length; i++) {
            backendUrlList.add(backendUrlArr[i].endsWith("/") ? backendUrlArr[i].substring(0, backendUrlArr[i].length() - 1) : backendUrlArr[i]);
        }

        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

        httpClient = new OkHttpClient.Builder().connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS).build();
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        String backendUrl = httpEndpointRouter.route(backendUrlList);
        System.out.println("选择的后端服务是：" + backendUrl);

        final String url = backendUrl + fullRequest.uri();
        proxyService.submit(() -> fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        Request.Builder builder = new Request.Builder();
        if (inbound != null) {
            HttpHeaders headers = inbound.headers();
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entries()) {
                    System.out.println("Add http header " + entry.getKey() + " with value " + entry.getValue());
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        final Request request = builder
                .url(url)
                .get()
                .build();

        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    handleResponse(inbound, ctx, response);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }
        });

    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response endpointResponse) throws Exception {
        FullHttpResponse response = null;
        try {

            byte[] body = endpointResponse.body().bytes();
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.header("Content-Length")));

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
