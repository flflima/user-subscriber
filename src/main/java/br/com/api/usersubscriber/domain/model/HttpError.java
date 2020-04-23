package br.com.api.usersubscriber.domain.model;

public class HttpError {
    private int statusCode;
    private String message;

    public HttpError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
