package com.newsletter.service.subscriptionservice.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -7495328477069393464L;

    public BadRequestException(String message) {
        super(message);
    }
}
