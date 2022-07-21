package com.example.display.controller;


import com.example.display.fixture.DisplayDrinkFixture;
import com.example.display.service.DisplayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DisplayController.class)
public class DisplayControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DisplayService displayService;


    @Test
    public void drinksService() throws Exception {

        when(displayService.GetDrinkAllList())
                .thenReturn(List.of(DisplayDrinkFixture.of(1, "콜라"),DisplayDrinkFixture.of(2, "식혜"),DisplayDrinkFixture.of(3, "사이다")));

        mvc.perform(get("/drinks/display") //해당 url로 요청을 한다.
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED) // Json 타입으로 지정
                ).andExpect(status().isOk()).andDo(print());
    }
}