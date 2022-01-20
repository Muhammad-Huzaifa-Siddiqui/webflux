package com.huzaifa.webfluxdemo.controller;

import com.huzaifa.webfluxdemo.dto.ResponseDto;
import com.huzaifa.webfluxdemo.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
    private final ReactiveMathService mathService;

    public ReactiveMathController(ReactiveMathService mathService){
        this.mathService = mathService;
    }

    @GetMapping("/square/{input}")
    public Mono<ResponseDto> findSquare(@PathVariable("input") int input){
        return mathService.findSquare(input);
    }
    @GetMapping("/table/{input}")
    public Flux<ResponseDto> multiplicationTable(@PathVariable("input") int input){
        return mathService.mathTable(input);
    }
    @GetMapping(value = "/table/stream/{input}",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ResponseDto> streamMultiplicationTable(@PathVariable("input") int input){
        return mathService.mathTable(input);
    }
}
