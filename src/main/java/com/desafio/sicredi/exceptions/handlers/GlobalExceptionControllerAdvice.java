package com.desafio.sicredi.exceptions.handlers;

import com.desafio.sicredi.exceptions.models.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Integer.MIN_VALUE)
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    private final ErrorPropertiesResolver errorPropertiesResolver;

    @ExceptionHandler(CreditOperationException.class)
    public ResponseEntity<ErrorResponse> handlerCustomException(CreditOperationException e) {
        log.error("Tratando exceção: {}", e.getMessage());
        ErrorResponse errorResponse = errorPropertiesResolver.resolve(e.getMessage());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
}