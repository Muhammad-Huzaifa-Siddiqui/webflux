package com.huzaifa.webfluxdemo.config;

import com.huzaifa.webfluxdemo.dto.model.InputFailureValidationResponse;
import com.huzaifa.webfluxdemo.exception.InputValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class RouterConfig {
    private final RequestHandler requestHandler;
    public RouterConfig(RequestHandler requestHandler){
        this.requestHandler = requestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerBoss(){
        return RouterFunctions.route()
                .path("router",this::serverResponseRouterFunction)
                .build();
    }
    private RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("square/{input}",requestHandler::squareHandler)
                .GET("table/{input}",requestHandler::tableHandler)
                .GET("table/stream/{input}",requestHandler::tableStreamHandler)
                .POST("multiply",requestHandler::multiplyHandler)
                .onError(InputValidationException.class, exceptionHandler())
                .build();
    }
    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
        return (err, req) -> {
            InputValidationException ex = (InputValidationException) err;
            InputFailureValidationResponse response = new InputFailureValidationResponse();
            response.setInput(ex.getInput());
            response.setMessage(ex.getMessage());
            response.setErrorCode(ex.getErrorCode());
            return ServerResponse.badRequest().bodyValue(response);
        };
    }



}
