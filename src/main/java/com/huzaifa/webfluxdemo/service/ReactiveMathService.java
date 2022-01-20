package com.huzaifa.webfluxdemo.service;

import com.huzaifa.webfluxdemo.dto.MultiplyRequestDto;
import com.huzaifa.webfluxdemo.dto.ResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<ResponseDto> findSquare(int input){
        return Mono.fromSupplier(() -> input * input )
                .map(ResponseDto::new);
    }
    public Flux<ResponseDto> mathTable(int input){
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
//                .doOnNext(i->SleepUtil.sleepSeconds(1))
                .doOnNext(i -> System.out.println("reactive math service processing: "+i))
                .map(i -> new ResponseDto(i * input));
    }

    public Mono<ResponseDto> multiply(Mono<MultiplyRequestDto> dtoMono){
        return dtoMono
                    .map(dto -> dto.getFirstNumber() * dto.getSecondNumber())
                    .map(ResponseDto::new);
    }
}
