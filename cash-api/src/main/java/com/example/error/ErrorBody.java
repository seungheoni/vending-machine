package com.example.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ErrorBody {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int code;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SimpleFieldError> fields;

    private long timestamp;

    /**
     * 에러 바디 객체 생성 함수 (MethodArgumentNotValidException)
     * @param exception @ModelAttribute 나 @RequestBody 유효성 검사 실패시 발생하는 예외
     */
    public static ErrorBody of(MethodArgumentNotValidException exception) {
        List<SimpleFieldError> fields = exception.getBindingResult().getFieldErrors().stream().map(SimpleFieldError::of).collect(Collectors.toList());
        long timeStamp = Instant.now().toEpochMilli();
        return new ErrorBody(ErrorMessage.INVALID_VALIDATION, HttpStatus.BAD_REQUEST.value(), fields, timeStamp);
    }


    /**
     * 에러 바디 객체 생성 함수 (EXCEPTION)
     * @param exception @ModelAttribute 나 @RequestBody 유효성 검사 실패시 발생하는 예외
     */
    public static ErrorBody of(Exception exception) {
        long timeStamp = Instant.now().toEpochMilli();
        return new ErrorBody(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),HttpStatus.INTERNAL_SERVER_ERROR.value(),null,timeStamp);
    }
}
