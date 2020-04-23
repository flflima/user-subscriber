package br.com.api.usersubscriber.domain.model.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class InvalidRequestBodyException extends Exception {
    private int statusCode;

    public InvalidRequestBodyException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
