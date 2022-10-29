package com.example.error;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class SimpleFieldError {
    private String name;
    private String message;

    public static SimpleFieldError of(FieldError error) {
        String objectName = error.getField();
        String message = error.getDefaultMessage();
        return new SimpleFieldError(objectName, message);
    }
}
