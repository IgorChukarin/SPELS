package com.example.spels.service;

import com.example.spels.dto.ProductDto;
import com.example.spels.model.PageDocument;
import com.example.spels.model.Product;
import com.example.spels.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Service
public class ProductCrudService {

    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;

    public ProductCrudService(ProductRepository productRepository, FileStorageService fileStorageService) {
        this.productRepository = productRepository;
        this.fileStorageService = fileStorageService;
    }


    @Transactional
    public void createProduct(ProductDto productDto, MultipartFile imageFile, List<MultipartFile> pagePhotos, List<MultipartFile> pageDocuments) {
        String imagePath = fileStorageService.saveCardPhoto(imageFile);
        List<String> photoPaths = fileStorageService.savePagePhotos(pagePhotos);
        List<PageDocument> documents = fileStorageService.savePageDocuments(pageDocuments);

        productDto.setImagePath(imagePath);
        productDto.setPhotos(photoPaths);
        productDto.getDocuments().addAll(documents);

        Product product = mapToEntity(productDto);

        product.getDocuments().forEach(doc -> doc.setProduct(product));

        productRepository.save(product);
    }


    @Transactional
    public void updateProduct(ProductDto productDto, MultipartFile imageFile, List<MultipartFile> pagePhotos, List<MultipartFile> pageDocuments) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Не удалось обновить продукт, продукт не найден"));

        updateBasicFields(product, productDto);

        if (imageFile != null && !imageFile.isEmpty()) {
            fileStorageService.deleteCardPhoto(product.getImagePath());
            String imagePath = fileStorageService.saveCardPhoto(imageFile);
            productDto.setImagePath(imagePath);
            updateImage(product, productDto);
        }

        if (
                (pagePhotos != null && !pagePhotos.isEmpty()) &&
                !(pagePhotos.size() == 1 && pagePhotos.get(0).isEmpty())
        ) {
            fileStorageService.deletePagePhotos(product.getPhotos());
            List<String> photoPaths = fileStorageService.savePagePhotos(pagePhotos);
            productDto.setPhotos(photoPaths);
            updatePhotos(product, productDto);
        }

        if (
                (pageDocuments != null && !pageDocuments.isEmpty()) &&
                !(pageDocuments.size() == 1 && pageDocuments.get(0).isEmpty())
        ) {
            fileStorageService.deletePageDocuments(product.getDocuments());
            List<PageDocument> documents = fileStorageService.savePageDocuments(pageDocuments);
            productDto.setDocuments(documents);
            updateDocuments(product, productDto);
        }

        productRepository.save(product);
    }


    private void updateBasicFields(Product product, ProductDto dto) {
        product.setBoldText(dto.getBoldText());
        product.setText(dto.getText());
        product.setCompanyName(dto.getCompanyName());
        product.setPageText(dto.getPageText());
    }


    private void updateImage(Product product, ProductDto dto) {
        if (dto.getImagePath() != null) {
            product.setImagePath(dto.getImagePath());
        }
    }


    private void updatePhotos(Product product, ProductDto dto) {
        if (dto.getPhotos() != null && !dto.getPhotos().isEmpty()) {
            product.setPhotos(dto.getPhotos());
        }
    }


    private void updateDocuments(Product product, ProductDto dto) {
        if (dto.getDocuments() != null && !dto.getDocuments().isEmpty()) {
            product.getDocuments().clear();
            for (PageDocument doc : dto.getDocuments()) {
                doc.setProduct(product);
            }
            product.getDocuments().addAll(dto.getDocuments());
        }
    }


    @Transactional
    public void deleteById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));

        fileStorageService.deleteCardPhoto(product.getImagePath());
        fileStorageService.deletePagePhotos(product.getPhotos());
        fileStorageService.deletePageDocuments(product.getDocuments());
        productRepository.delete(product);
    }


    public ProductDto getById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт с id " + id + " не найден"));
        return mapToDto(product);
    }


    public Collection<ProductDto> getAll() {
        return productRepository.findAll().stream().map(ProductCrudService::mapToDto).toList();
    }


    public static ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setImagePath(product.getImagePath());
        productDto.setBoldText(product.getBoldText());
        productDto.setText(product.getText());

        productDto.setCompanyName(product.getCompanyName());
        productDto.setPageText(product.getPageText());
        productDto.setPhotos(product.getPhotos());
        productDto.setDocuments(product.getDocuments());
        return productDto;
    }


    public static Product mapToEntity(ProductDto productDto) {
        Product product = new Product();

        product.setImagePath(productDto.getImagePath());
        product.setBoldText(productDto.getBoldText());
        product.setText(productDto.getText());

        product.setCompanyName(productDto.getCompanyName());
        product.setPageText(productDto.getPageText());
        product.setPhotos(productDto.getPhotos());
        product.setDocuments(productDto.getDocuments());
        return product;
    }
}