package com.proyecto.archivos_dynamo.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDTO {
    private int statusCode;
    private Object data;
}