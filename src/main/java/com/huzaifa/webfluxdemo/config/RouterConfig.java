package com.huzaifa.webfluxdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    private final RequestHandler requestHandler;
    public RouterConfig(RequestHandler requestHandler){
        this.requestHandler = requestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("router/square/{input}",requestHandler::squareHandler)
                .GET("router/table/{input}",requestHandler::tableHandler)
                .GET("router/table/stream/{input}",requestHandler::tableStreamHandler)
                .POST("router/multiply",requestHandler::multiplyHandler)
                .build();
    }
}
