package com.proyecto.archivos_dynamo.infrastructure.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.proyecto.archivos_dynamo.domain.model.dto.ResponseDTO;

@EnableWebMvc
@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public ResponseEntity<ResponseDTO> ping() {

        Map<String, String> pong = new HashMap<>();
        pong.put("pong", "Hello, World!");

        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), pong);

        return ResponseEntity.ok(responseDTO);
    }
}