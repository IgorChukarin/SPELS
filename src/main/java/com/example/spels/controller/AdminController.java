package com.example.spels.controller;

import com.example.spels.dto.ProductDto;
import com.example.spels.service.FileStorageService;
import com.example.spels.service.ProductCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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


    @PostMapping("/add")
    public String createProduct(
            @ModelAttribute ProductDto productDto,
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        String imagePath = fileStorageService.saveCardPhoto(imageFile);
        productDto.setImagePath(imagePath);
        productCrudService.create(productDto);
        return "redirect:/admin";
    }


    @PostMapping("/edit")
    public String editProduct(
            @ModelAttribute ProductDto productDto,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "pagePhotos", required = false) List<MultipartFile> PagePhotos
            ) {

        if (!imageFile.isEmpty()) {
            String imagePath = fileStorageService.saveCardPhoto(imageFile);
            productDto.setImagePath(imagePath);
        }

        System.out.println("РАЗМЕР:" + PagePhotos.size());
        if (PagePhotos != null && !PagePhotos.isEmpty()) {
            List<String> photoPaths = new ArrayList<>();
            for (MultipartFile photo : PagePhotos) {
                if (!photo.isEmpty()) {
                    String path = fileStorageService.savePagePhoto(photo); // или savePhoto
                    photoPaths.add(path);
                }
            }
            productDto.setPhotos(photoPaths);
        }


        productCrudService.update(productDto);
        return "redirect:/admin";
    }


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productCrudService.deleteById(id);
        return "redirect:/admin";
    }
}
