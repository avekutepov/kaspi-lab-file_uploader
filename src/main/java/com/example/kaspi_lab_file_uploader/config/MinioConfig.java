package com.example.kaspi_lab_file_uploader.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class MinioConfig {

    @Value("${minio.url}")
    private String baseUrl;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient client() {
        return MinioClient.builder()
                .endpoint(baseUrl)
                .credentials(accessKey, secretKey)
                .build();
    }
}

