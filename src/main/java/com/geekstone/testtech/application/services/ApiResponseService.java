package com.geekstone.testtech.application.services;

import com.geekstone.testtech.application.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import com.geekstone.testtech.application.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApiResponseService {

    public <T> ResponseEntity<ApiResponse<T>> createResponse(String status, String message, T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>(status, message, data);
        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<ApiResponse<Void>> createErrorResponse(String message) {
        ApiResponse<Void> apiResponse = new ApiResponse<>("error", message, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
