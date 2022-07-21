package com.example.display.service;

import com.example.display.dto.DisplayDrinkView;
import com.example.display.fixture.DisplayFixture;
import com.example.display.repo.DisplayRepository;
import com.example.mongo.config.MongoConfigurer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DisplayServiceImplTest {

    @Mock
    private DisplayRepository displayRepository;
    @InjectMocks
    private DisplayServiceImpl displayServiceImpl;

    @BeforeEach
    void setUp() {

            when(displayRepository.findWithDrink())
                    .thenReturn(List.of(
                            DisplayFixture.of(1,"콜라"),
                            DisplayFixture.of(2,"식혜"),
                            DisplayFixture.of(3,"사이다")));
    }

    @Test
    @DisplayName("DisplayService GetDrinkAllList 호출")
    public void GetDrinkGetDrinkAllListTestAllListTest() throws JsonProcessingException {

        List<DisplayDrinkView> displayDrinkViewList = displayServiceImpl.GetDrinkAllList();
        assertEquals(3,displayDrinkViewList.size());

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(displayDrinkViewList));
    }
}
