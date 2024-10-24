package com.proyecto.archivos_dynamo.domain.port;

import java.util.List;
import java.util.Optional;

import com.proyecto.archivos_dynamo.domain.model.FileModel;

public interface FilePort {

    List<FileModel> getList();

    Optional<FileModel> getByIdRegistros(String idRegistro);

}
