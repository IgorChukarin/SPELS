package com.example.spels.service;

import com.example.spels.model.PageDocument;
import com.example.spels.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageService {
    private static final String CARD_PHOTO_UPLOAD_DIR = "uploads/products";
    private static final String PAGE_PHOTO_UPLOAD_DIR = "uploads/photos";
    private static final String PAGE_DOCUMENT_UPLOAD_DIR = "uploads/documents";


    public String saveCardPhoto(MultipartFile file) {
        return saveFile(file, CARD_PHOTO_UPLOAD_DIR);
    }


    public void deleteCardPhoto(String cardPhotoPath) {
        if (cardPhotoPath != null) {
            deleteFile(cardPhotoPath);
        }
    }


    public List<String> savePagePhotos(List<MultipartFile> PagePhotos) {
        List<String> photoPaths = new ArrayList<>();
        if (PagePhotos != null && !PagePhotos.isEmpty()) {
            for (MultipartFile photo : PagePhotos) {
                if (!photo.isEmpty()) {
                    String path = saveFile(photo, PAGE_PHOTO_UPLOAD_DIR);
                    photoPaths.add(path);
                }
            }
        }
        return photoPaths;
    }


    public void deletePagePhotos(List<String> pagePhotoPaths) {
        if (pagePhotoPaths != null && !pagePhotoPaths.isEmpty()) {
            for (String photoPath : pagePhotoPaths) {
                deleteFile(photoPath);
            }
        }
    }


    public List<PageDocument> savePageDocuments(List<MultipartFile> PageDocuments) {
        List<PageDocument> pageDocuments = new ArrayList<>();
        if (PageDocuments != null && !PageDocuments.isEmpty()) {
            for (MultipartFile document : PageDocuments) {
                if (!document.isEmpty()) {
                    String name = document.getOriginalFilename();
                    String path = saveFile(document, PAGE_DOCUMENT_UPLOAD_DIR);
                    PageDocument pageDocument = new PageDocument();
                    pageDocument.setName(name);
                    pageDocument.setPath(path);
                    pageDocuments.add(pageDocument);
                }
            }
        }
        return pageDocuments;
    }


    public void deletePageDocuments(List<PageDocument> pageDocuments) {
        if (pageDocuments != null && !pageDocuments.isEmpty()) {
            for (PageDocument pageDocument: pageDocuments) {
                deleteFile(pageDocument.getPath());
            }
        }
    }


    private String saveFile(MultipartFile file, String uploadDirectory) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Файл Пуст " + file.getName());
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

//            return "/" + uploadDirectory + "/" + fileName;
            return uploadDirectory + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении изображения", e);
        }
    }


    private String getFileExtension(String filename) {
        if (filename == null) return "";
        int dotIndex = filename.lastIndexOf(".");
        return (dotIndex >= 0) ? filename.substring(dotIndex) : "";
    }


    private void deleteFile(String filePath) {
        if (filePath != null && !filePath.isBlank()) {
            String relativePath = filePath.substring(0); // here
            Path path = Paths.get(relativePath);
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
