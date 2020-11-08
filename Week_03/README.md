学习笔记

### 1、整合你上次作业的 httpclient/okhttp（5_1目录）

其中netty-server为后端服务，netty-gateway为网关

netty-gateway中，相对基础代码，新增了io.github.kimmking.gateway.outbound.okhttp.OkhttpOutboundHandler，以OKhttp作为HTTP客户端访问backend。抽取了一个接口io.github.kimmking.gateway.outbound.HttpOutboundHandler，作为httpclient和OKhttp两种实现的基础。

### 2、使用 netty 实现后端 http 访问（未做）

### 3、实现过滤器（6_1目录）

其中netty-server为后端服务，netty-gateway为网关

netty-gateway中，相对于5_1目录代码，新增io.github.kimmking.gateway.filter.HttpRequestFilter的实现io.github.kimmking.gateway.filter.CustomHttpRequestFilter，并在io.github.kimmking.gateway.inbound.HttpInboundHandler的channelRead()方法中，调用CustomHttpRequestFilter.filter()方法，实现过滤器功能；在io.github.kimmking.gateway.outbound.okhttp.OkhttpOutboundHandler#OkhttpOutboundHandler中的fetchGet()方法中，对FullHttpRequest中的所有请求头全部复制到调用后端服务的请求中。

### 4、实现路由（6_2目录）

其中netty-server为后端服务，netty-gateway为网关

netty-gateway中，相对于6_1目录代码，新增io.github.kimmking.gateway.router.HttpEndpointRouter的实现

随机路由io.github.kimmking.gateway.router.RandomHttpEndpointRouter，并在io.github.kimmking.gateway.outbound.okhttp.OkhttpOutboundHandler#OkhttpOutboundHandler进行真正访问后端服务前选择一个后端服务的地址；修改启动类io.github.kimmking.gateway.NettyServerApplication的proxyServer为代理多个后端服务。