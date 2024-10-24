package com.proyecto.archivos_dynamo.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proyecto.archivos_dynamo.infrastructure.adapter.FileAdapter;
import com.proyecto.archivos_dynamo.infrastructure.config.util.mapper.ModelMapperUtil;

import lombok.Generated;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Generated
@Configuration
public class DynamoDBConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(ddb)
                .build();
    }

    @Bean
    FileAdapter fileAdapter(ModelMapperUtil modelMapperUtil,
            DynamoDbEnhancedClient enhancedClient) {
        return new FileAdapter(modelMapperUtil, enhancedClient);
    }
}