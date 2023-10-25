package com.preran.BlogPost2.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<CustomResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        CustomResponse response = new CustomResponse(message, false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceFoundException.class)
    private ResponseEntity<CustomResponse> resourceFoundExceptionHandler(ResourceFoundException ex) {
        String message = ex.getMessage();
        CustomResponse response = new CustomResponse(message, false);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String,String>> handleInvalidArgs (MethodArgumentNotValidException ex){
        Map<String,String> resp = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e-> resp.put(e.getField(),e.getDefaultMessage()));
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }


}
