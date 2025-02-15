package br.com.fiap.postech.logistics.application.exception.handlers;

import br.com.fiap.postech.logistics.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.logistics.application.exception.custom.DuplicateOrderIdException;
import br.com.fiap.postech.logistics.application.exception.custom.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final DatabaseExceptionHandler databaseExceptionHandler;
    private final InterfaceExceptionHandler interfaceExceptionHandler;

    public GlobalExceptionHandler(DatabaseExceptionHandler databaseExceptionHandler
            ,
                                  InterfaceExceptionHandler interfaceExceptionHandler
                                  ) {
        this.databaseExceptionHandler = databaseExceptionHandler;
        this.interfaceExceptionHandler = interfaceExceptionHandler;
    }
    @ExceptionHandler(DuplicateOrderIdException.class)
    public ApiErrorResponseImpl handleDatabaseException(DuplicateOrderIdException e) {
        return databaseExceptionHandler.handleDuplicateOrderIdException(e);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiErrorResponseImpl handleDatabaseException(DataIntegrityViolationException e) {
        return databaseExceptionHandler.handleDatabaseIntegrityViolation(e);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ApiErrorResponseImpl handleInterfaceException(InvalidInputException e) {
        return interfaceExceptionHandler.handleInvalidInputException(e);
    }
}
