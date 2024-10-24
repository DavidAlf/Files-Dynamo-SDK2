package com.proyecto.archivos_dynamo.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.archivos_dynamo.aplication.use_case.FileUseCase;
import com.proyecto.archivos_dynamo.domain.model.dto.ResponseDTO;

@RestController
@RequestMapping("/dynamo-files")
public class FileController {

    private final FileUseCase fileUseCase;

    public FileController(FileUseCase fileUseCase) {
        this.fileUseCase = fileUseCase;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getList() {
        return fileUseCase.getList();
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseDTO> getByIdRegistros(
            @RequestParam(name = "idRegistros", required = true) String idRegistros) {
        return fileUseCase.getByIdRegistros(idRegistros);
    }

}
