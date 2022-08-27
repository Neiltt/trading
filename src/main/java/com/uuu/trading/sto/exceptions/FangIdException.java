package com.uuu.trading.sto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FangIdException extends RuntimeException {
    public FangIdException(String message) {
        super(message);
    }
}
