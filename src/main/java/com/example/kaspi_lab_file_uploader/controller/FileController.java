package com.example.kaspi_lab_file_uploader.controller;

import com.example.kaspi_lab_file_uploader.service.FileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {
    private final FileUploadService service;

    @PostMapping("/upload")
    public ResponseEntity<Long> uploadFile(@RequestParam MultipartFile file){
        return ResponseEntity.accepted().body(service.startUpload(file));
    }
}
