package com.example.kaspi_lab_file_uploader.service;

import com.example.kaspi_lab_file_uploader.model.FileUploadDTO;
import com.example.kaspi_lab_file_uploader.repository.FileMetadataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileProcessingService {
    private final FileMetadataRepository fileRepository;

    public void uploadFile(FileUploadDTO dto){
    }
}
