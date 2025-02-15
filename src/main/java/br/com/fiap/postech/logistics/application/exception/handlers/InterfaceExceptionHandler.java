package br.com.fiap.postech.logistics.application.exception.handlers;

import br.com.fiap.postech.logistics.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.logistics.application.exception.ErrorType;
import br.com.fiap.postech.logistics.application.exception.custom.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class InterfaceExceptionHandler {

    public ApiErrorResponseImpl handleInvalidInputException(InvalidInputException e) {
        return new ApiErrorResponseImpl(
                ErrorType.INVALID_INPUT.name(),
                ErrorType.INVALID_INPUT.getTitle(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
    }
}
