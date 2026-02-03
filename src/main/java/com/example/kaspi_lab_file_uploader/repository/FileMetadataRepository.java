package com.example.kaspi_lab_file_uploader.repository;

import com.example.kaspi_lab_file_uploader.model.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long>{}