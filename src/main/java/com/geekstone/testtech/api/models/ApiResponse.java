package com.geekstone.testtech.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;   // Code HTTP (ex: 200, 400, 404)
    private String message; // Message de réponse (ex: "Succès", "Erreur")
    private T data;        // Données de la réponse (peut être n'importe quel type)
}