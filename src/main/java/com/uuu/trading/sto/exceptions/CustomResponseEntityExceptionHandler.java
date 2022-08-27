package com.uuu.trading.sto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @ControllerAdvice：配置控制器異常處理
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    // 異常Handler
    @ExceptionHandler
    public final ResponseEntity<Object> handleWorkoutIdException(FangIdException exception, WebRequest request) {
        // 針對此異常，拋出想對應異常
        FangIdExceptionResponse response = new FangIdExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
