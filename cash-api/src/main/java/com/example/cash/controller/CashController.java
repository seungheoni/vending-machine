package com.example.cash.controller;

import com.example.cash.dto.CashDepositPayLoad;
import com.example.cash.dto.CashDepositView;
import com.example.cash.service.CashService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash")
@RequiredArgsConstructor
public class CashController {

    private final CashService cashService;

    /**
     * 금액 입금
     * @param cashDepositPayLoad
     * @return
     */
    @Operation(summary = "금액 입금")
    @PutMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public CashDepositView cashDeposit(@RequestBody CashDepositPayLoad cashDepositPayLoad) {
        return cashService.deposit();
    }
}
