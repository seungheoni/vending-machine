package com.example.error;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor
public class SimpleFieldError {
    private String name;
    private String message;

    public static SimpleFieldError of(FieldError error) {
        String objectName = error.getField();
        String message = error.getDefaultMessage();
        return new SimpleFieldError(objectName, message);
    }
}
