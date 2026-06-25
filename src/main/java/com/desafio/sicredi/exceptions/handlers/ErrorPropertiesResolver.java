package com.desafio.sicredi.exceptions.handlers;

import com.desafio.sicredi.exceptions.models.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Locale;

@Component
@RequiredArgsConstructor
@Slf4j
public class ErrorPropertiesResolver {

    private final MessageSource messageSource;

    public ErrorResponse resolve(String key) {

        String message = messageSource.getMessage(key + ".message", null, Locale.getDefault());
        String code = messageSource.getMessage(key + ".code", null, Locale.getDefault());
        String status = messageSource.getMessage(key + ".status", null, Locale.getDefault());

        Integer statusCode = Integer.valueOf(status);
        String traceId = MDC.get("traceId");

        return new
                ErrorResponse(
                Instant.now().toEpochMilli(),
                code,
                message,
                statusCode,
                traceId
        );
    }
}