package com.proyecto.archivos_dynamo.infrastructure.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.proyecto.archivos_dynamo.domain.model.dto.ResponseDTO;

@WebMvcTest(PingController.class)
class PingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Map<String, String> expectedResponse;

    ResponseDTO responseDTO;

    @BeforeEach
    void setup() {
        expectedResponse = new HashMap<>();
        expectedResponse.put("pong", "Hello, World!");
        responseDTO = new ResponseDTO(HttpStatus.OK.value(), expectedResponse);        
    }

    @Test
    void pingTest() throws Exception {
        // >When
        mockMvc.perform(get("/ping")
                .contentType(MediaType.APPLICATION_JSON))
                // >Then                
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", is(responseDTO.getData())));                
    }
}
