package com.casamento.wedding_api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Map<String, Object>>
    handleRegraNegocioException(RegraNegocioException ex) {


        Map<String, Object> body = new HashMap<>();


        body.put("status", 409);
        body.put("mensagem", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(body);
    }
}
