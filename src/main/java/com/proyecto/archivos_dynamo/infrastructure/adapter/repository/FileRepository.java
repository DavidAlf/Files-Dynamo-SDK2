package com.proyecto.archivos_dynamo.infrastructure.adapter.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.proyecto.archivos_dynamo.infrastructure.adapter.entity.FileEntity;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

@Slf4j
@Repository
public class FileRepository {

    private static final String TABLE_NAME = "REGISTROS_S3_JDAO";
    private final DynamoDbTable<FileEntity> fileTable;

    public FileRepository(DynamoDbEnhancedClient enhancedClient) {

        this.fileTable = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(FileEntity.class));
    }

    public List<FileEntity> getList() {
        log.info("[FileRepository(getList)] -> Listar");

        PageIterable<FileEntity> result = fileTable.scan();

        return result.items().stream().collect(Collectors.toList());
    }

    public Optional<FileEntity> getByIdRegistros(String idRegistro) {
        log.info("[FileRepository(getByIdRegistros)] -> Buscar por id");

        return Optional.ofNullable(fileTable.getItem(Key.builder().partitionValue(idRegistro).build()));
    }

}
