package br.com.api.usersubscriber.application.controller;

import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidRequestBodyException.class)
    protected ResponseEntity<Object> handleConflict(InvalidRequestBodyException ex, WebRequest request) {
        String bodyOfResponse = ex.toString();
        //TODO convert HttpError to json string
        return handleExceptionInternal(ex, /*new HttpError(ex.getStatusCode(), ex.getMessage())*/bodyOfResponse,
                new HttpHeaders(), HttpStatus.resolve(ex.getStatusCode()), request);
    }
}
