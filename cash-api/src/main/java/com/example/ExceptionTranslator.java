package com.example;

import com.example.error.ErrorBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionTranslator {

    /**
     * MethodArgumentNotValidException 예외 발생시 ResponseEntity 생성 로직
     * @param exception @ModelAttribute 나 @RequestBody 유효성 검사 실패시 발생하는 예외
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBody> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(ErrorBody.of(exception));
    }
}
