package com.example.spels.service;

import com.example.spels.dto.ProductDto;
import com.example.spels.model.Product;
import com.example.spels.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductCrudService implements CrudService<ProductDto> {

    private final ProductRepository repository;
    private final FileStorageService fileStorageService;

    public ProductCrudService(ProductRepository productRepository, FileStorageService fileStorageService) {
        this.repository = productRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ProductDto getById(Integer id) {
        return null;
    }

    @Override
    public Collection<ProductDto> getAll() {
        return repository.findAll().stream().map(ProductCrudService::mapToDto).toList();
    }

    @Override
    public void create(ProductDto productDto) {
        Product product = mapToEntity(productDto);
//        Integer id = repository.findAll().size() + 1;
//        product.setId(id);
        repository.save(product);
    }

    @Override
    public void update(ProductDto item) {

    }

    @Override
    public void deleteById(Integer id) {
        Optional<Product> productOpt = repository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            String imagePath = product.getImagePath();
            fileStorageService.deleteImage(imagePath);
        }
        repository.deleteById(id);
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
