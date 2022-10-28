package com.example.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ErrorBody {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String code;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SimpleFieldError> fields;

    private long timestamp;

    /**
     * 에러 바디 객체 생성 함수
     * @param exception @ModelAttribute 나 @RequestBody 유효성 검사 실패시 발생하는 예외
     */
    public static ErrorBody of(MethodArgumentNotValidException exception) {
        List<SimpleFieldError> fields = exception.getBindingResult().getFieldErrors().stream().map(SimpleFieldError::of).collect(Collectors.toList());
        long timeStamp = Instant.now().toEpochMilli();
        return new ErrorBody(ErrorMessage.INVALID_VALIDATION, null, fields, timeStamp);
    }
}
