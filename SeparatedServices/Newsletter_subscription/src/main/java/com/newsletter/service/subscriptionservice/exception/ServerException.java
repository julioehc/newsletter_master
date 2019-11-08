package com.newsletter.service.subscriptionservice.exception;

public class ServerException extends RuntimeException {

    private static final long serialVersionUID = -1036632202959378750L;

    public ServerException(String message) {
        super(message);
    }
}
