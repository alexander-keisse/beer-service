package org.rastalion.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rastalion.beerservice.web.model.BeerDto;
import org.rastalion.beerservice.web.model.BeerStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String url;
    BeerDto validBeerDto;

    @BeforeEach
    void setUp() {
        url = "/api/v1/beer/";
        validBeerDto = BeerDto.builder()
                .beerName("Duvel")
                .beerStyle(BeerStyle.ALE)
                .upc(123456789012L)
                .price(new BigDecimal("2.95"))
                .build();
    }

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(url + UUID.randomUUID())
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        String beerDtoJson = objectMapper.writeValueAsString(validBeerDto);

        mockMvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        String beerDtoJson = objectMapper.writeValueAsString(validBeerDto);

        mockMvc.perform(put(url + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @AfterEach
    void tearDown() {
        url = null;
        validBeerDto = null;
    }
}