package com.example.kaspi_lab_file_uploader.service;

import com.example.kaspi_lab_file_uploader.repository.FileMetadataRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    public String uploadFile(
            String objectKey,
            InputStream inputStream,
            long size,
            String contentType
    ) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(objectKey)
                        .stream(inputStream, size, -1)
                        .contentType(contentType)
                        .build()
        );

        return objectKey;
    }
}
