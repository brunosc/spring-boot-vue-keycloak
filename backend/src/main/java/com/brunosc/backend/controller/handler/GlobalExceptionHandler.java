package com.brunosc.backend.controller.handler;

import com.brunosc.backend.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException e, HttpServletRequest request) {
        Map<String, String> error = Map.of("error", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
