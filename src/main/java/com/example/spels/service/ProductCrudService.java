package com.example.spels.service;

import com.example.spels.dto.ProductDto;
import com.example.spels.model.Product;
import com.example.spels.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductCrudService implements CrudService<ProductDto> {

    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;

    public ProductCrudService(ProductRepository productRepository, FileStorageService fileStorageService) {
        this.productRepository = productRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ProductDto getById(Integer id) {
        return null;
    }

    @Override
    public Collection<ProductDto> getAll() {
        return productRepository.findAll().stream().map(ProductCrudService::mapToDto).toList();
    }

    @Override
    public void create(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        productRepository.save(product);
    }

    @Override
    public void update(ProductDto productDto) {
        Product existingProduct = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
        existingProduct.setBoldText(productDto.getBoldText());
        existingProduct.setText(productDto.getText());
        if (productDto.getImagePath() != null) {
            existingProduct.setImagePath(productDto.getImagePath());
        }
        productRepository.save(existingProduct);
    }

    @Override
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
        productDto.setBoldText(product.getBoldText());
        productDto.setText(product.getText());
        productDto.setImagePath(product.getImagePath());
        return productDto;
    }

    public static Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setBoldText(productDto.getBoldText());
        product.setText(productDto.getText());
        product.setImagePath(productDto.getImagePath());
        return product;
    }
}
