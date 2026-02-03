package com.example.kaspi_lab_file_uploader.model;

import java.io.InputStream;

public record FileUploadDTO(String fileName, Long fileSize, String contentType, InputStream inputStream){}
