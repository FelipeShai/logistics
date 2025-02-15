package br.com.fiap.postech.logistics.application.exception;

public interface ApiErrorResponse {
    String getType();
    String getTitle();
    int getStatus();
}
