package com.huzaifa.webfluxdemo.controller;

import com.huzaifa.webfluxdemo.dto.MultiplyRequestDto;
import com.huzaifa.webfluxdemo.dto.ResponseDto;
import com.huzaifa.webfluxdemo.exception.InputValidationException;
import com.huzaifa.webfluxdemo.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
    private final ReactiveMathService mathService;

    public ReactiveMathController(ReactiveMathService mathService){
        this.mathService = mathService;
    }

    @GetMapping("/square/{input}")
    public Mono<ResponseDto> findSquare(@PathVariable("input") int input){
        return Mono.just(input)
                .handle((integer, sink) -> {
                    if(integer >= 10 && integer<=20)
                        sink.next(integer);
                    else
                        sink.error(new InputValidationException(integer));
                })
                .cast(Integer.class)
                .flatMap(mathService::findSquare);
    }
    @GetMapping("/table/{input}")
    public Flux<ResponseDto> multiplicationTable(@PathVariable("input") int input){
        return mathService.mathTable(input);
    }
    @GetMapping(value = "/table/stream/{input}",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)//streams data as it is calculated
    public Flux<ResponseDto> streamMultiplicationTable(@PathVariable("input") int input){
        return mathService.mathTable(input);
    }
    @PostMapping("/multiply")
    public Mono<ResponseDto> multiply(@RequestBody Mono<MultiplyRequestDto> requestDto
//                                      @RequestHeader Map<String , String> headers
                                      ){
//        System.out.println(headers); headers work same
        return mathService.multiply(requestDto);
    }
}
