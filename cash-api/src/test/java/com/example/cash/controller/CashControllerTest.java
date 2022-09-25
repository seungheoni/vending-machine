package com.example.cash.controller;

import com.example.cash.dto.CashDepositPayLoad;
import com.example.cash.dto.CashDepositView;
import com.example.cash.service.CashService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CashController.class)
class CashControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CashService cashService;

    @DisplayName("CashController deposit(금액 입금) api 테스트")
    @Test
    public void cashDeposit() throws Exception {

        when(cashService.deposit())
                .thenReturn(new CashDepositView(1000L));

        mvc.perform(put("/cash/deposit")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(new CashDepositPayLoad(1000L))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(result -> {
                    byte[] content = result.getResponse().getContentAsByteArray();
                    assertDoesNotThrow(() -> {CashDepositView results = objectMapper.readValue(content, new TypeReference<>() {}); });
                });
    }

}