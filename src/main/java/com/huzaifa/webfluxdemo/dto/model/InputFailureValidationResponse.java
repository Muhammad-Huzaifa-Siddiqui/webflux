package com.huzaifa.webfluxdemo.dto.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
public class InputFailureValidationResponse {
    private int errorCode;
    private int input;
    private String message;
}
