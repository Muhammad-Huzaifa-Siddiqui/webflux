package com.huzaifa.webfluxdemo.service;

import com.huzaifa.webfluxdemo.dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService {
    public ResponseDto calculateSquare(int input){
        return new ResponseDto(input * input);
    }

    public List<ResponseDto> multiplicationTable(int input){
        return IntStream.rangeClosed(1,10)
                .peek(i->SleepUtil.sleepSeconds(1)) // simulating a time consuming process
                .peek(i-> System.out.println("Math-service-processing element: "+i))
                .mapToObj(i->new ResponseDto(i * input))
                .collect(Collectors.toList());
    }
}
