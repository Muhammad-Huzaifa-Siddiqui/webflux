package com.huzaifa.webfluxdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@NoArgsConstructor
@ToString
public class ResponseDto {
    private Date date = new Date();
    private int output;

    public ResponseDto(int output) {
        this.output = output;
    }
}
