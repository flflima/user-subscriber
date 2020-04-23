package br.com.api.usersubscriber.domain.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
public class HttpError {
    private String message;

    public HttpError(String message) {
        this.message = message;
    }
}
