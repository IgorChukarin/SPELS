package com.example.spels.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {
    private static final String CARD_PHOTO_UPLOAD_DIR = "uploads/products";
    private static final String PAGE_PHOTO_UPLOAD_DIR = "uploads/photos";
    private static final String DOCUMENT_UPLOAD_DIR = "uploads/documents";


    public String saveCardPhoto(MultipartFile file) {
        return saveFile(file, CARD_PHOTO_UPLOAD_DIR);
    }

    public String savePagePhoto(MultipartFile file) {
        return saveFile(file, PAGE_PHOTO_UPLOAD_DIR);
    }

    public String saveDocument(MultipartFile file) {
        return saveFile(file, DOCUMENT_UPLOAD_DIR);
    }

    private String saveFile(MultipartFile file, String uploadDirectory) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Файл пуст");
        }

        try {
            Path uploadPath = Paths.get(uploadDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Уникальное имя файла (например, по времени)
            String originalFilename = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFilename);
            String fileName = System.currentTimeMillis() + fileExtension;

            // Путь к файлу
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/" + uploadDirectory + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении изображения", e);
        }
    }


    private String getFileExtension(String filename) {
        if (filename == null) return "";
        int dotIndex = filename.lastIndexOf(".");
        return (dotIndex >= 0) ? filename.substring(dotIndex) : "";
    }


    public void deleteImage(String imagePath) {
        if (imagePath != null && !imagePath.isBlank()) {
            Path filePath = Paths.get(imagePath);
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
