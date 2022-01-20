package com.huzaifa.webfluxdemo.exception;

import com.huzaifa.webfluxdemo.dto.model.InputFailureValidationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalCustomExceptionController {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailureValidationResponse> handleInputValidationException(InputValidationException ex){
          InputFailureValidationResponse response = new InputFailureValidationResponse();
          response.setErrorCode(ex.getErrorCode());
          response.setInput(ex.getInput());
          response.setMessage(ex.getMessage());
          return ResponseEntity.badRequest().body(response);
    }
}