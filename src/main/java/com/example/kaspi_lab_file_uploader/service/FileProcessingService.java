package com.example.kaspi_lab_file_uploader.service;

import com.example.kaspi_lab_file_uploader.enums.UploadStatus;
import com.example.kaspi_lab_file_uploader.model.FileMetadata;
import com.example.kaspi_lab_file_uploader.repository.FileMetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileProcessingService {
    private final FileMetadataRepository repository;
    private final FileStorageService storageService;
    private final FileHashService hashService;

    @Async("fileUploadExecutor")
    public void processAsync(Long fileId, MultipartFile multipartFile) {
        FileMetadata metadata = repository.findById(fileId).orElseThrow();

        try (InputStream is = multipartFile.getInputStream()){

            String hash = hashService.calculateHash(is);

            String objectKey = fileId + "_" + multipartFile.getOriginalFilename();

            storageService.uploadFile(
                    objectKey,
                    multipartFile.getInputStream(),
                    multipartFile.getSize(),
                    multipartFile.getContentType()
            );

            metadata.setFileHash(hash);
            metadata.setStorageUrl(objectKey);
            metadata.setUploadStatus(UploadStatus.SUCCESS);
            metadata.setUploadedAt(LocalDateTime.now());

        }catch (Exception e) {
            log.error("Upload failed, fileId={}", fileId, e);
            metadata.setUploadStatus(UploadStatus.FAILED);
        } finally {
            repository.save(metadata);
        }
    }
}
