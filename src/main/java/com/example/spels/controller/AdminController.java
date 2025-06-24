package com.example.spels.controller;

import com.example.spels.dto.ProductDto;
import com.example.spels.service.FileStorageService;
import com.example.spels.service.ProductCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductCrudService productCrudService;
    private final FileStorageService fileStorageService;

    public AdminController(ProductCrudService productCrudService, FileStorageService fileStorageService) {
        this.productCrudService = productCrudService;
        this.fileStorageService = fileStorageService;
    }


    @GetMapping
    public String adminHome(Model model) {
        Collection<ProductDto> products = productCrudService.getAll();
        model.addAttribute("products", products);
        return "admin";
    }

    @GetMapping("/products/{id}")
    public String getAdminProductPage(@PathVariable Integer id, Model model) {
        ProductDto product = productCrudService.getById(id);
        model.addAttribute("product", product);
        return "adminProductPage";
    }


    @PostMapping("/add")
    public String createProduct(
            @ModelAttribute ProductDto productDto,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "pagePhotos", required = false) List<MultipartFile> pagePhotos,
            @RequestParam(value = "pageDocuments", required = false) List<MultipartFile> pageDocuments
    ) {
        productCrudService.createProduct(productDto, imageFile, pagePhotos, pageDocuments);
        return "redirect:/admin";
    }


    @PostMapping("/edit")
    public String editProduct(
            @ModelAttribute ProductDto productDto,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "pagePhotos", required = false) List<MultipartFile> pagePhotos,
            @RequestParam(value = "pageDocuments", required = false) List<MultipartFile> pageDocuments
            ) {
        productCrudService.updateProduct(productDto, imageFile, pagePhotos, pageDocuments);
        return "redirect:/admin";
    }


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productCrudService.deleteById(id);
        return "redirect:/admin";
    }
}
