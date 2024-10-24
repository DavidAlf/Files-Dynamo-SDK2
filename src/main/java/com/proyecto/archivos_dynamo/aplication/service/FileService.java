package com.proyecto.archivos_dynamo.aplication.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proyecto.archivos_dynamo.aplication.use_case.FileUseCase;
import com.proyecto.archivos_dynamo.domain.model.dto.FileDTO;
import com.proyecto.archivos_dynamo.domain.model.dto.ResponseDTO;
import com.proyecto.archivos_dynamo.domain.model.dto.ResponseErrorDTO;
import com.proyecto.archivos_dynamo.domain.port.FilePort;
import com.proyecto.archivos_dynamo.infrastructure.config.util.mapper.ModelMapperUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService implements FileUseCase {

    private final FilePort filePort;

    private final ModelMapperUtil modelMapperUtil;

    public FileService(FilePort filePort, ModelMapperUtil modelMapperUtil) {
        this.filePort = filePort;
        this.modelMapperUtil = modelMapperUtil;
    }

    @Override
    public ResponseEntity<ResponseDTO> getList() {
        log.info("[FileService(getList)] -> Lista");

        try {
            var filesList = filePort.getList();

            var filesListDto = modelMapperUtil.mapAll(filesList, FileDTO.class);

            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), filesListDto);

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            log.error("[ERROR] getList {}", e.getMessage());
            ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(HttpStatus.NOT_FOUND.value(),
                    null, "Error listando los archivos");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErrorDTO);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> getByIdRegistros(String idRegistros) {
        log.info("[FileService(getByName)] -> Buscar por nombre");

        try {
            var filesRecord = filePort.getByIdRegistros(idRegistros);

            var filesRecordDto = modelMapperUtil.map(filesRecord.get(), FileDTO.class);

            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), filesRecordDto);

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            log.error("[ERROR] getByName {}", e.getMessage());
            ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(HttpStatus.NOT_FOUND.value(),
                    null, "Error buscando el id {" + idRegistros + "} en los archivos.");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErrorDTO);
        }
    }

}
