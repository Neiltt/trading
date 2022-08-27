package com.uuu.spring.fitness;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

// 錯誤map
public class MapValidationError {
    public static ResponseEntity<Map<String, String>> mapValidation(BindingResult bindingResult) {
        // 透過@Valid用bindingResult擷取錯誤
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                // 欄位值，model定義錯誤訊息
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
