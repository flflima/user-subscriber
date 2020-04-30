package br.com.api.usersubscriber.application.error.handler;

import br.com.api.usersubscriber.domain.model.HttpError;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
    logger.error("Error: {0}", ex);

    if (ex instanceof HttpClientErrorException) {
      return handleExceptionInternal(
          ex,
          new HttpError(ex.getMessage()),
          new HttpHeaders(),
          resolveHttpStatusCode(((HttpClientErrorException) ex).getRawStatusCode()),
          request);
    } else if (ex instanceof InvalidRequestBodyException) {
      return handleExceptionInternal(
          ex,
          new HttpError(ex.getMessage()),
          new HttpHeaders(),
          resolveHttpStatusCode(((InvalidRequestBodyException) ex).getStatusCode()),
          request);
    } else {
      return handleExceptionInternal(
          ex,
          new HttpError(ex.getMessage() == null ? "An internal server error occurred" : ex.getMessage()),
          new HttpHeaders(),
          HttpStatus.INTERNAL_SERVER_ERROR,
          request);
    }
  }

  private HttpStatus resolveHttpStatusCode(int statusCode) {
    final HttpStatus httpStatus = HttpStatus.resolve(statusCode);
    if (httpStatus == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    return httpStatus;
  }
}
