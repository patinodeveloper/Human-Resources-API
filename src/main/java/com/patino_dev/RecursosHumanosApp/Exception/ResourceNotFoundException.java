package com.patino_dev.RecursosHumanosApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // Responde con 404 Not Found
public class ResourceNotFoundException extends RuntimeException {
    // Constructor para pasar un mensaje
    public ResourceNotFoundException(String message) {
        super(message); // Pasa el mensaje a RuntimeException
    }
}
