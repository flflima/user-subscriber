package br.com.api.usersubscriber.application.error.handler;

import br.com.api.usersubscriber.domain.model.HttpError;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = InvalidRequestBodyException.class)
  protected ResponseEntity<Object> handleInvalidRequestBodyException(
      InvalidRequestBodyException ex, WebRequest request) {
    return handleExceptionInternal(
        ex,
        new HttpError(ex.getMessage()),
        new HttpHeaders(),
        HttpStatus.resolve(ex.getStatusCode()),
        request);
  }

  @ExceptionHandler(value = HttpClientErrorException.class)
  protected ResponseEntity<Object> handleGenericException(HttpClientErrorException ex, WebRequest request) {
    return handleExceptionInternal(
            ex,
            new HttpError(ex.getMessage()),
            new HttpHeaders(),
            HttpStatus.resolve(ex.getRawStatusCode()),
            request);
  }

  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
    return handleExceptionInternal(
            ex,
            new HttpError(ex.getMessage()),
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request);
  }
}
