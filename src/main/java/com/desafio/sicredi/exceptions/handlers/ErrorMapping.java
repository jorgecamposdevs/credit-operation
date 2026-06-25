package com.desafio.sicredi.exceptions.handlers;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ErrorMapping {

    public static final Map<HttpStatus, String> STATUS_SPECIFIC_MESSAGES = Map.of(
            HttpStatus.UNAUTHORIZED, "error.unauthorized",
            HttpStatus.NOT_FOUND, "error.not-found",
            HttpStatus.BAD_REQUEST, "error.bad-request",
            HttpStatus.GATEWAY_TIMEOUT, "error.gateway-timeout",
            HttpStatus.SERVICE_UNAVAILABLE, "error.service-unavailable"
    );

    public static final Map<HttpStatus.Series, String> SERIES_MESSAGES = Map.of(
            HttpStatus.Series.CLIENT_ERROR, "error.bad-request",
            HttpStatus.Series.SERVER_ERROR, "error.internal"
    );

    public static String resolveMessageKey(HttpStatus status) {
        if (STATUS_SPECIFIC_MESSAGES.containsKey(status)) {
            return STATUS_SPECIFIC_MESSAGES.get(status);
        }

        return SERIES_MESSAGES.getOrDefault(status.series(), "error.unknown");
    }
}