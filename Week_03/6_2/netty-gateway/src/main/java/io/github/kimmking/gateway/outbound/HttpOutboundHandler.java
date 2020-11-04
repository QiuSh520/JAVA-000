package io.github.kimmking.gateway.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author qiushuang
 * @version V1.0
 * @date 2020/11/3 18:06
 */
public interface HttpOutboundHandler {
    void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx);
}
