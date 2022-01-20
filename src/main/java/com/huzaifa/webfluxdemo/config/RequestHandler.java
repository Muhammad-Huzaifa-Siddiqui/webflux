package com.huzaifa.webfluxdemo.config;

import com.huzaifa.webfluxdemo.dto.MultiplyRequestDto;
import com.huzaifa.webfluxdemo.dto.ResponseDto;
import com.huzaifa.webfluxdemo.dto.model.InputFailureValidationResponse;
import com.huzaifa.webfluxdemo.exception.InputValidationException;
import com.huzaifa.webfluxdemo.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RequestHandler {
    private final ReactiveMathService mathService;

    public RequestHandler(ReactiveMathService mathService){
        this.mathService = mathService;
    }
    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if(input<10 || input > 20){
            return Mono.error(new InputValidationException(input));
        }
        Mono<ResponseDto> responseMono = mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, ResponseDto.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<ResponseDto> responseFlux = mathService.mathTable(input);
        return ServerResponse.ok().body(responseFlux, ResponseDto.class);
    }

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<ResponseDto> responseFlux = mathService.mathTable(input);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, ResponseDto.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest){
        Mono<MultiplyRequestDto> requestDtoMono = serverRequest.bodyToMono(MultiplyRequestDto.class);
        Mono<ResponseDto> responseMono = mathService.multiply(requestDtoMono);
        return ServerResponse.ok()
                .body(responseMono, ResponseDto.class);
    }
}
