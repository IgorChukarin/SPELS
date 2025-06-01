package com.example.spels.controller;

import com.example.spels.dto.ProductDto;
import com.example.spels.service.ProductCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductCrudService productCrudService;

    public AdminController(ProductCrudService productCrudService) {
        this.productCrudService = productCrudService;
    }

    @GetMapping
    public String adminHome(Model model) {
        Collection<ProductDto> products = productCrudService.getAll();
        model.addAttribute("products", products);
        return "admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productCrudService.deleteById(id);
        return "redirect:/admin";
    }
}
