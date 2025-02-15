package br.com.fiap.postech.logistics.application.exception.handlers;

import br.com.fiap.postech.logistics.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.logistics.application.exception.custom.DuplicateOrderIdException;
import br.com.fiap.postech.logistics.application.exception.ErrorType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DatabaseExceptionHandler {

    public ApiErrorResponseImpl handleDuplicateOrderIdException(DuplicateOrderIdException e) {
        return new ApiErrorResponseImpl(
                ErrorType.DUPLICATE_ORDER.name(),
                ErrorType.DUPLICATE_ORDER.getTitle(),
                HttpStatus.CONFLICT.value(),
                e.getMessage()
        );
    }

    public ApiErrorResponseImpl handleDatabaseIntegrityViolation(DataIntegrityViolationException e) {
        return new ApiErrorResponseImpl(
                ErrorType.INTERNAL_SERVER_ERROR.name(),
                ErrorType.INTERNAL_SERVER_ERROR.getTitle(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
    }
}
