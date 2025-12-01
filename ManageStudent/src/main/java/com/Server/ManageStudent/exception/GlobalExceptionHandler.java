package com.Server.ManageStudent.exception;

import com.Server.ManageStudent.common.ResponseConfig;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.NotFoundException.class)
    public ResponseEntity<ResponseConfig<Void>> handleNotFound(CustomException.NotFoundException ex) {
        return new ResponseEntity<>(ResponseConfig.failure(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomException.ConflictException.class)
    public ResponseEntity<ResponseConfig<Void>> handleConflict(CustomException.ConflictException ex) {
        return new ResponseEntity<>(ResponseConfig.failure(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseConfig<Void>> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(ResponseConfig.failure(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseConfig<Void>> handleOtherExceptions(Exception ex) {
        return new ResponseEntity<>(ResponseConfig.failure("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
