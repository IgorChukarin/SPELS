package com.example.spels.service;

import com.example.spels.dto.ProductDto;
import com.example.spels.model.PageDocument;
import com.example.spels.model.Product;
import com.example.spels.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
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


    public void createProduct(ProductDto productDto, MultipartFile imageFile, List<MultipartFile> PagePhotos, List<MultipartFile> PageDocuments) {
        String imagePath = fileStorageService.saveCardPhoto(imageFile);
        productDto.setImagePath(imagePath);

        List<String> photoPaths = fileStorageService.savePagePhotos(PagePhotos);
        productDto.setPhotos(photoPaths);

        Product product = mapToEntity(productDto);
        productRepository.save(product);

        List<PageDocument> pageDocuments = fileStorageService.savePageDocuments(PageDocuments, product);
        product.getDocuments().addAll(pageDocuments);

        productRepository.save(product);
    }

    public void updateProduct(ProductDto productDto, MultipartFile imageFile, List<MultipartFile> pagePhotos, List<MultipartFile> pageDocuments) {
        Product existingProduct = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));

        updateBasicFields(existingProduct, productDto);

        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = fileStorageService.saveCardPhoto(imageFile);
            productDto.setImagePath(imagePath);
            updateImage(existingProduct, productDto);
        }

        if (pagePhotos != null && !imageFile.isEmpty()) {
            List<String> photoPaths = fileStorageService.savePagePhotos(pagePhotos);
            productDto.setPhotos(photoPaths);
            updatePhotos(existingProduct, productDto);
        }

        productRepository.save(existingProduct);

        if (pageDocuments != null && !pageDocuments.isEmpty()) {
            List<PageDocument> documents = fileStorageService.savePageDocuments(pageDocuments, existingProduct);
            productDto.setDocuments(documents);
            updateDocuments(existingProduct, productDto);
        }

        productRepository.save(existingProduct);
    }


    private void updateBasicFields(Product product, ProductDto dto) {
        product.setBoldText(dto.getBoldText());
        product.setText(dto.getText());
        product.setCompanyName(dto.getCompanyName());
        product.setPageText(dto.getPageText());
    }


    private void updateImage(Product product, ProductDto dto) {
        if (dto.getImagePath() != null) {
            product.setImagePath(product.getImagePath());
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
        ProductDto productDto = mapToDto(product);
        return productDto;
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
        product.setId(productDto.getId());
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