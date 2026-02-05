package com.example.kaspi_lab_file_uploader.service;

import com.example.kaspi_lab_file_uploader.enums.UploadStatus;
import com.example.kaspi_lab_file_uploader.model.FileMetadata;
import com.example.kaspi_lab_file_uploader.repository.FileMetadataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class FileUploadService {

    private final FileMetadataRepository repository;
    private final FileProcessingService service;
    private final FileHashService hashService;

    public Long startUpload(MultipartFile file) {

        String hash = hashService.calculateHash(file.getInputStream());

        FileMetadata metadata = new FileMetadata();
        metadata.setFileName(file.getOriginalFilename());
        metadata.setSize(file.getSize());
        metadata.setFileType(file.getContentType());
        metadata.setUploadStatus(UploadStatus.IN_PROCESS);

        metadata = repository.save(metadata);

        service.processAsync(metadata.getId(), file);

        return metadata.getId();
    }
}
