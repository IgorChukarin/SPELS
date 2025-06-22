package com.example.spels.service;

import com.example.spels.dto.ProductDto;
import com.example.spels.model.PageDocument;
import com.example.spels.model.Product;
import com.example.spels.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCrudService {

    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;

    public ProductCrudService(ProductRepository productRepository, FileStorageService fileStorageService) {
        this.productRepository = productRepository;
        this.fileStorageService = fileStorageService;
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


    public void createProduct(ProductDto productDto, MultipartFile imageFile, List<MultipartFile> PagePhotos, List<MultipartFile> PageDocuments) {
        String imagePath = fileStorageService.saveCardPhoto(imageFile);
        productDto.setImagePath(imagePath);

        List<String> photoPaths = fileStorageService.savePagePhoto(PagePhotos);
        productDto.setPhotos(photoPaths);

        Product product = mapToEntity(productDto);
        productRepository.save(product);

        List<PageDocument> pageDocuments = fileStorageService.saveDocument(PageDocuments, product);
        product.getDocuments().clear();
        product.getDocuments().addAll(pageDocuments);

        productRepository.save(product);
    }

    public void update(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));

        existingProduct.setBoldText(productDto.getBoldText());
        existingProduct.setText(productDto.getText());
        existingProduct.setCompanyName(productDto.getCompanyName());
        existingProduct.setPageText(productDto.getPageText());

        if (productDto.getImagePath() != null) {
            existingProduct.setImagePath(productDto.getImagePath());
        }

        if (!productDto.getPhotos().isEmpty()) {
            existingProduct.setPhotos(productDto.getPhotos());
        }

        if (!productDto.getDocuments().isEmpty()) {
            existingProduct.getDocuments().clear();
            for (PageDocument doc : productDto.getDocuments()) {
                doc.setProduct(existingProduct);
            }
            existingProduct.getDocuments().addAll(productDto.getDocuments());
        }
        productRepository.save(existingProduct);
    }


    public void deleteById(Integer id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            String imagePath = product.getImagePath();
            fileStorageService.deleteImage(imagePath);
        }
        productRepository.deleteById(id);
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
