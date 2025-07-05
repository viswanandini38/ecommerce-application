package com.example.ecommerce_application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class ImageUploadController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @PostMapping("/multiple")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body("No files uploaded.");
        }

        List<String> uploadedFiles = new ArrayList<>();

        File uploadFolder = new File(UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            boolean created = uploadFolder.mkdirs();
            if (!created) {
                return new ResponseEntity<>("Failed to create upload directory", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    File dest = new File(uploadFolder, file.getOriginalFilename());
                    file.transferTo(dest);
                    uploadedFiles.add(file.getOriginalFilename());
                }
            }

            return ResponseEntity.ok("Successfully uploaded: " + uploadedFiles);
        } catch (IOException e) {
            return new ResponseEntity<>("Error during upload: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
