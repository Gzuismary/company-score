package com.serasa.companyscore.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ConstraintViolationExceptionHandler {

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<CustomError> handleConstraintViolationException(javax.validation.ConstraintViolationException exception,
                                                                          ServletWebRequest webRequest) throws IOException {
        CustomError customError = new CustomError();
        customError.setStatus(HttpStatus.BAD_REQUEST);
        customError.setMessage(exception.getMessage());
        customError.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.badRequest().body(customError);    }
}