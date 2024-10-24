package com.proyecto.archivos_dynamo.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

import com.proyecto.archivos_dynamo.domain.model.FileModel;
import com.proyecto.archivos_dynamo.domain.port.FilePort;
import com.proyecto.archivos_dynamo.infrastructure.adapter.entity.FileEntity;
import com.proyecto.archivos_dynamo.infrastructure.adapter.repository.FileRepository;
import com.proyecto.archivos_dynamo.infrastructure.config.util.mapper.ModelMapperUtil;
import com.proyecto.archivos_dynamo.infrastructure.config.util.tags.Adapter;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Slf4j
@Adapter
public class FileAdapter implements FilePort {

    private final FileRepository fileRepository;

    private final ModelMapperUtil modelMapperUtil;

    @Value("${aws.region}")
    private String awsRegion;

    public FileAdapter(ModelMapperUtil modelMapperUtil,
            DynamoDbEnhancedClient enhancedClient) {
        this.fileRepository = new FileRepository(enhancedClient);
        this.modelMapperUtil = modelMapperUtil;
    }

    @Override
    public List<FileModel> getList() {
        log.info("[FileAdapter(getList)] -> Listar");

        List<FileEntity> records = fileRepository.getList();
        return modelMapperUtil.mapAll(records, FileModel.class);
    }

    @Override
    public Optional<FileModel> getByIdRegistros(String idRegistro) {
        log.info("[FileAdapter(getByIdRegistros)] -> Buscar por id");

        Optional<FileEntity> recordEntity = fileRepository.getByIdRegistros(idRegistro);

        return Optional.ofNullable(modelMapperUtil.map(recordEntity.get(), FileModel.class));
    }
}
