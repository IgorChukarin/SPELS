package com.example.spels.controller;

import com.example.spels.dto.ProductDto;
import com.example.spels.service.ProductCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductCrudService productCrudService;

    public ProductController(ProductCrudService productCRUDService) {
        this.productCrudService = productCRUDService;
    }

    @GetMapping()
    public String getProductsPage(Model model) {
        Collection<ProductDto> products = productCrudService.getAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/{id}")
    public String getProductPage(@PathVariable Integer id, Model model) {
        ProductDto product = productCrudService.getById(id);
        model.addAttribute("product", product);
        return "productPage";
    }
}
