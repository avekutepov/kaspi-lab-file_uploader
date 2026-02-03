package com.example.kaspi_lab_file_uploader.model;

import com.example.kaspi_lab_file_uploader.enums.UploadStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Table(
        name = "file_metadata",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"client_id", "file_hash"}
        )
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String fileHash;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false, unique = true)
    private String storageUrl;

    @Column(nullable = false)
    private UploadStatus uploadStatus;
}
