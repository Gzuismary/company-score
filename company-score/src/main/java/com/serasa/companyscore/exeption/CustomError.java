package com.serasa.companyscore.exeption;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Getter
@Setter
public class CustomError<T> {
    private HttpStatus status;
    private String message;
    private Timestamp timestamp;
}
