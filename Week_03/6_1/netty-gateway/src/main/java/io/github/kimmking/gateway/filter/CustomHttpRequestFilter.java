package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

import java.util.Map;

public class CustomHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders headers = fullRequest.headers();
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entries()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println("HTTP header 【" + key + "】: 【" + value+"】");
            }

            headers.add("nio","qiushuang");
        }
    }
}
