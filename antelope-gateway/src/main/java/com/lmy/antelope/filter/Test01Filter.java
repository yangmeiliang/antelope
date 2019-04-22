package com.lmy.antelope.filter;

import com.lmy.antelope.util.WebUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.net.URI;

/**
 * @author yangmeiliang
 */
@Component
public class Test01Filter implements GlobalFilter {

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

        System.out.println("01############");

//        Mono<ServerResponse> redirect = WebUtil.redirect("http://class.dxy.cn");
        return chain.filter(exchange);

    }
}
