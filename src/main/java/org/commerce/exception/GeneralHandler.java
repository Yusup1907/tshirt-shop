package org.commerce.exception;

import org.commerce.model.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> generalException(GeneralException e) {
        GeneralResponse apiError = GeneralResponse.builder()
                .responseCode(e.getStatusCode())
                .responseMessage(e.getGeneralMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Object> productException(ProductException e) {
        GeneralResponse apiError = GeneralResponse.builder()
                .responseCode(e.getStatusCode())
                .responseMessage(e.getGeneralMessage())
                .build();
        return ResponseEntity.ok(apiError);
    }
}