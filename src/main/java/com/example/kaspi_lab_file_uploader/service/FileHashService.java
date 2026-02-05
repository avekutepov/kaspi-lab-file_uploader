package com.example.kaspi_lab_file_uploader.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HexFormat;

@Service
public class FileHashService {

    public String calculateHash(InputStream inputStream) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] buffer = new byte[8192];
        int read;

        while ((read = inputStream.read(buffer)) != -1){
            digest.update(buffer, 0, read);
        }

        byte[] hashBytes = digest.digest();
        return HexFormat.of().formatHex(hashBytes);
    }
}
