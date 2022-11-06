package com.example.error;

import com.example.error.exception.CashEmptyException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ErrorBody {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String code;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SimpleFieldError> fields;

    private long timestamp = Instant.now().toEpochMilli();

    /**
     * 에러 바디 객체 생성 함수 (MethodArgumentNotValidException)
     * @param exception @ModelAttribute 나 @RequestBody 유효성 검사 실패시 발생하는 예외
     */
    public static ErrorBody of(MethodArgumentNotValidException exception) {
        List<SimpleFieldError> fields = exception.getBindingResult().getFieldErrors().stream().map(SimpleFieldError::of).collect(Collectors.toList());
        ErrorBody body = new ErrorBody(ErrorMessage.INVALID_VALIDATION);
        body.fields = fields;
        return body;
    }

    /**
     * 에러 바디 객체 생성 함수 (CashEmptyException)
     */
    public static ErrorBody of(CashEmptyException cashEmptyException) {
        return new ErrorBody(ErrorMessage.CASH_EMPTY);
    }

    /**
     * 에러 바디 객체 생성 함수 (EXCEPTION)
     */
    public static ErrorBody of() {
        return new ErrorBody(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
