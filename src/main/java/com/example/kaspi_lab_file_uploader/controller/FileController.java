package com.example.kaspi_lab_file_uploader.controller;

import com.example.kaspi_lab_file_uploader.model.FileUploadDTO;
import com.example.kaspi_lab_file_uploader.service.FileProcessingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {
    private final FileProcessingService service;

    @PostMapping("/upload")
    public void uploadFile(
            @RequestParam("file")MultipartFile file
            ) throws IOException {
        FileUploadDTO dto = new FileUploadDTO(
                file.getName(),
                file.getSize(),
                file.getContentType(),
                file.getInputStream());


    }
}
