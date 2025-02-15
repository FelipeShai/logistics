package br.com.fiap.postech.logistics.application.exception;

public enum ErrorType {
    DUPLICATE_ORDER("Duplicate Order"),
    DATABASE_INTEGRITY("Database Integrity Error"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    INVALID_JSON("Invalid Request Body"),
    INVALID_INPUT("Invalid Input");

    private final String title;

    ErrorType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
