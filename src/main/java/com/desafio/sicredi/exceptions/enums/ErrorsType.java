package com.desafio.sicredi.exceptions.enums;

import lombok.Getter;

@Getter public enum ErrorsType {

    BAD_REQUEST("error.bad-request.message",
            "error.bad-request.code",
            "error.bad-request.status"),

    NOT_FOUND("error.not-found.message",
            "error.not-found.code",
            "error.not-found.status"),

    UNAUTHORIZED("error.unauthorized.message",
            "error.unauthorized.code",
            "error.unauthorized.status"),

    INTERNAL("error.internal.message",
            "error.internal.code",
            "error.internal.status"),

    SERVICE_UNAVAILABLE("error.service-unavailable.message",
            "error.service-unavailable.code",
            "error.service-unavailable.status"),

    GATEWAY_TIMEOUT("error.gateway-time-out.message",
            "error.gateway-time-out.code",
            "error.gateway-time-out.status");

    private final String messageKey;
    private final String codeKey;
    private final String statusKey;

    ErrorsType(String messageKey, String codeKey, String statusKey) {
        this.messageKey = messageKey;
        this.codeKey = codeKey;
        this.statusKey = statusKey;
    }
}
