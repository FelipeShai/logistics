package br.com.fiap.postech.logistics.application.exception.custom;

import br.com.fiap.postech.logistics.application.exception.ApiErrorResponse;
import br.com.fiap.postech.logistics.application.exception.ErrorType;

    public class DuplicateOrderIdException extends RuntimeException implements ApiErrorResponse {

    public DuplicateOrderIdException(String orderId) {
        super("Order ID already exists: " + orderId);
    }

    @Override
    public String getType() {
        return ErrorType.DUPLICATE_ORDER.name();
    }

    @Override
    public String getTitle() {
        return ErrorType.DUPLICATE_ORDER.getTitle();
    }

    @Override
    public int getStatus() {
        return 409;
    }
}
