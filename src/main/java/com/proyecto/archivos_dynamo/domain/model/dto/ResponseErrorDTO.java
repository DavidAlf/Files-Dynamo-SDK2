package com.proyecto.archivos_dynamo.domain.model.dto;

import lombok.Getter;

@Getter
public class ResponseErrorDTO extends ResponseDTO {

    private String errorMsn;

    public ResponseErrorDTO(int statusCode, Object data, String errorMsn) {
        super(statusCode, data);
        this.errorMsn = errorMsn;
    }
}