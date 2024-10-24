package com.proyecto.archivos_dynamo.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamoDbBean
public class FileEntity {

    String idRegistros;

    String bucket;

    String document;

    String insertedAt;

    String lastModified;

    String lastName;

    String name;

    String size;

    String status;

    @DynamoDbPartitionKey
    public String getIdRegistros() {
        return idRegistros;
    }

    public void setIdRegistros(String idRegistros) {
        this.idRegistros = idRegistros;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(String insertedAt) {
        this.insertedAt = insertedAt;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
