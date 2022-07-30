package com.example.display.controller;

import com.example.display.dto.DisplayDrinkView;
import com.example.display.fixture.DisplayFixture;
import com.example.display.service.DisplayService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DisplayController.class)
public class DisplayControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DisplayService displayService;

    @Test
    public void 테스트() throws Exception {

        when(displayService.getDisplayDrinks())
                .thenReturn(List.of(DisplayFixture.of(1, "콜라"), DisplayFixture.of(2, "식혜"), DisplayFixture.of(3, "사이다")));

        mvc.perform(get("/drinks/display") //해당 url로 요청을 한다.
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // Json 타입으로 지정
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(result -> {
                    byte[] content = result.getResponse().getContentAsByteArray();
                    assertDoesNotThrow(() -> {List<DisplayDrinkView> results = objectMapper.readValue(content, new TypeReference<>() {}); });
                });
    }
}