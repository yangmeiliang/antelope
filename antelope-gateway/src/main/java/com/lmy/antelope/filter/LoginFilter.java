package com.lmy.antelope.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yangmeiliang
 */
@Component
public class LoginFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//        System.out.println(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress() );
//        ServerHttpRequest request = exchange.getRequest();
//        URI uri = request.getURI();
//        System.out.println(uri.toString());
//        System.out.println(request.getPath().toString());
//
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(HttpStatus.SEE_OTHER);
//        response.getHeaders().set("Location", "http://www.baidu.com");
//        return response.setComplete();

        System.out.println("login############");

//        Mono<ServerResponse> redirect = WebUtil.redirect("http://class.dxy.cn");
        return chain.filter(exchange);

    }
}
