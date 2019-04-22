package com.lmy.antelope.config;

import com.lmy.antelope.handler.TimeHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author yangmeiliang
 */
@Configuration
@AllArgsConstructor
public class RouterConfig {

    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return route(GET("/time"), serverRequest -> timeHandler.getTime(serverRequest))
                .andRoute(GET("/date"), serverRequest1 -> timeHandler.getDate(serverRequest1))
                .andRoute(GET("/pre-second"), timeHandler::sendTimePerSec)
                ;
    }
}
