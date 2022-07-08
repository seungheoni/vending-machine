package com.example.display.service;

import com.example.display.dto.DisplayDrinkView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DisplayServiceImplTest {

    @Mock
    private DisplayService displayService;

    @BeforeEach
    public void setUp() {

        when(displayService.GetDrinkAllList())
                .thenReturn(List.of(
                        DisplayDrinkView.of("콜라", 1),
                        DisplayDrinkView.of("식혜", 2),
                        DisplayDrinkView.of("사이다", 3)));
    }

    @Test
    @DisplayName("DisplayService GetDrinkAllList 호출")
    public void GetDrinkAllListTest() throws JsonProcessingException {

        List<DisplayDrinkView> displayDrinkViewList = displayService.GetDrinkAllList();
        assertEquals(3,displayDrinkViewList.size());

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(displayDrinkViewList));
    }
}
