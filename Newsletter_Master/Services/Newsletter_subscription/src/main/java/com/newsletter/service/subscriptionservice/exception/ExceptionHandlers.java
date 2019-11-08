package com.newsletter.service.subscriptionservice.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    /**
     * Method to catch 409 conflict errors.
     */
    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestConflictException(RequestException e) {
        HttpStatus error = HttpStatus.CONFLICT;
        ExceptionBody commonException =
                new ExceptionBody(e.getMessage(), error, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(commonException, error);
    }

    /**
     * Method to catch 500 Interal Server Errors.
     */
    @ExceptionHandler(value = {ServerException.class})
    public ResponseEntity<Object> handleRequestErrorException (RequestException e) {
        HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionBody commonException =
                new ExceptionBody(e.getMessage(), error, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(commonException, error);
    }

    /**
     * Method to catch 400 bad request errors.
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException (RequestException e) {
        HttpStatus error = HttpStatus.BAD_REQUEST;
        ExceptionBody commonException =
                new ExceptionBody(e.getMessage(), error, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(commonException, error);
    }
}

