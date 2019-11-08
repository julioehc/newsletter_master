package com.newsletter.service.subscriptionservice.exception;

public class RequestException extends RuntimeException {

    private static final long serialVersionUID = 7985473966147752202L;

    public RequestException(String message) {
        super(message);
    }
}
