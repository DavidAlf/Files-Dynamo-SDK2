package com.proyecto.archivos_dynamo.infrastructure.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.archivos_dynamo.aplication.use_case.FileUseCase;
import com.proyecto.archivos_dynamo.domain.model.dto.FileDTO;
import com.proyecto.archivos_dynamo.domain.model.dto.ResponseDTO;

@WebMvcTest(FileController.class)
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileUseCase fileUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private FileDTO fileDTO;

    private ResponseDTO responseDTO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        fileDTO = new FileDTO(
                "1",
                "my-bucket",
                "document.txt",
                "2024-10-21T12:00:00Z",
                "2024-10-21T12:00:00Z",
                "Doe",
                "John",
                "1024",
                "active");

    }

    @Test
    void testGetList() throws Exception {
        // > Given
        List<FileDTO> list = new ArrayList<FileDTO>();
        list.add(fileDTO);

        responseDTO = new ResponseDTO(HttpStatus.OK.value(), list);

        given(fileUseCase.getList()).willReturn(ResponseEntity.ok(responseDTO));

        // > When
        ResultActions response = mockMvc.perform(get("/dynamo-files"));

        // > Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.size()", is(list.size())));
    }

    @Test
    void testGetByIdRegistros() throws Exception {
        // >Given
        String idRegistros = fileDTO.getIdRegistros();

        responseDTO = new ResponseDTO(HttpStatus.OK.value(), fileDTO);

        given(fileUseCase.getByIdRegistros(idRegistros)).willReturn(ResponseEntity.ok(responseDTO));

        // >When
        ResultActions response = mockMvc.perform(get("/dynamo-files/find")
                .param("idRegistros", idRegistros)
                .contentType(MediaType.APPLICATION_JSON));

        // >Then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.name", is(fileDTO.getName())))
                .andExpect(jsonPath("$.data.lastName", is(fileDTO.getLastName())));
    }
}
