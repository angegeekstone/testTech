package com.geekstone.testtech.api.exceptions;


import com.geekstone.testtech.api.models.ApiResponse;
import com.geekstone.testtech.domain.exceptions.InvalidIdentityNumberException;
import com.geekstone.testtech.domain.exceptions.PersonCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Gestion des exceptions spécifiques (par exemple PersonNotFoundException)
    @ExceptionHandler(PersonCreationException.class)
    public ResponseEntity<ApiResponse<String>> handlePersonNotFoundException(PersonCreationException ex, WebRequest request) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Gestion des exceptions d'argument non valide (validation @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Gestion des exceptions génériques
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex, WebRequest request) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Une erreur interne s'est produite.", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidIdentityNumberException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidIdentityNumberException(InvalidIdentityNumberException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, ex.getMessage(), null));
    }
}
