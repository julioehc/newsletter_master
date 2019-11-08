package com.newsletter.service.subscriptionservice.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * Class that contains the body of a common http error
 */
@Data
public class ExceptionBody {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
}
