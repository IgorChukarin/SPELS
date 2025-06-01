package com.example.spels.controller;

import com.example.spels.dto.ProductDto;
import com.example.spels.service.FileStorageService;
import com.example.spels.service.ProductCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;

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

    @PostMapping("/add")
    public String createProduct(
            @ModelAttribute ProductDto productDto,
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        String imagePath = fileStorageService.saveImage(imageFile);
        productDto.setImagePath(imagePath);
        productCrudService.create(productDto);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productCrudService.deleteById(id);
        return "redirect:/admin";
    }
}
