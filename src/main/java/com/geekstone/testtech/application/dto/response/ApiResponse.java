package com.geekstone.testtech.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String status; // 'success' ou 'error'
    private String message; // Message de réponse
    private T data; // Données de réponse
}
