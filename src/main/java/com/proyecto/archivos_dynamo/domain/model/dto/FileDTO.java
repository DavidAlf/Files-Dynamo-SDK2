package com.proyecto.archivos_dynamo.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

    String idRegistros;

    String bucket;

    String document;

    String insertedAt;

    String lastModified;

    String lastName;

    String name;

    String size;

    String status;

}
