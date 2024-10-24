package com.proyecto.archivos_dynamo.aplication.use_case;

import org.springframework.http.ResponseEntity;

import com.proyecto.archivos_dynamo.domain.model.dto.ResponseDTO;

public interface FileUseCase {

    public ResponseEntity<ResponseDTO> getList();

    public ResponseEntity<ResponseDTO> getByIdRegistros(String idRegistros);
}
