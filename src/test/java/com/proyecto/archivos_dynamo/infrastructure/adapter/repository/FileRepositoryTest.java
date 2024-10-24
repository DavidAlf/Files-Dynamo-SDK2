package com.proyecto.archivos_dynamo.infrastructure.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyecto.archivos_dynamo.infrastructure.adapter.entity.FileEntity;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

@ExtendWith(MockitoExtension.class)
class FileRepositoryTest {

    @Mock
    private DynamoDbEnhancedClient enhancedClient;

    @Mock
    private DynamoDbTable<FileEntity> fileTable;

    @Mock
    private PageIterable<FileEntity> pageIterable;

    private FileRepository fileRepository;

    @BeforeEach
    void setUp() {
        when(enhancedClient.table(eq("REGISTROS_S3_JDAO"), any(TableSchema.class))).thenReturn(fileTable);
        fileRepository = new FileRepository(enhancedClient);
    }

    // @Test
    // void getList_shouldReturnListOfFileEntities() {
    //     // Arrange
    //     FileEntity entity1 = new FileEntity();
    //     FileEntity entity2 = new FileEntity();
    //     List<FileEntity> expectedList = Arrays.asList(entity1, entity2);

    //     when(fileTable.scan()).thenReturn(pageIterable);
    //     when(pageIterable.items()).thenReturn(expectedList.stream().co);

    //     // Act
    //     List<FileEntity> result = fileRepository.getList();

    //     // Assert
    //     assertEquals(expectedList, result);
    //     verify(fileTable).scan();
    // }

    @Test
    void getByIdRegistros_shouldReturnFileEntityWhenFound() {
        String idRegistro = "testId";
        FileEntity expectedEntity = new FileEntity();
        expectedEntity.setIdRegistros(idRegistro);

        when(fileTable.getItem(any(Key.class))).thenReturn(expectedEntity);

        Optional<FileEntity> result = fileRepository.getByIdRegistros(idRegistro);

        assertTrue(result.isPresent());
        assertEquals(expectedEntity, result.get());
        verify(fileTable).getItem(Key.builder().partitionValue(idRegistro).build());
    }

    @Test
    void getByIdRegistros_shouldReturnEmptyOptionalWhenNotFound() {
        // Arrange
        String idRegistro = "nonExistentId";

        when(fileTable.getItem(any(Key.class))).thenReturn(null);

        // Act
        Optional<FileEntity> result = fileRepository.getByIdRegistros(idRegistro);

        // Assert
        assertFalse(result.isPresent());
        verify(fileTable).getItem(Key.builder().partitionValue(idRegistro).build());
    }
}
