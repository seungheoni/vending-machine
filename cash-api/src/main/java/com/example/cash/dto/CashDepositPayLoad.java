package com.example.cash.dto;

import com.example.error.ErrorMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CashDepositPayLoad {

    /**
     * 투입 금액 양
     */
    @Min(value = 0, message = ErrorMessage.INVALID_VALIDATION)
    private long amount;
}
