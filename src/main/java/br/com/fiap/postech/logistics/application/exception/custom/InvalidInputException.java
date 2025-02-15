package br.com.fiap.postech.logistics.application.exception.custom;

import br.com.fiap.postech.logistics.application.exception.ApiErrorResponse;

public class InvalidInputException  extends RuntimeException implements ApiErrorResponse {

    public InvalidInputException(String message) {
        super(message);
    }

    @Override
    public String getType() {
        return "invalid_input";
    }

    @Override
    public String getTitle() {
        return "Invalid input";
    }

    @Override
    public int getStatus() {
        return 400;
    }
}
