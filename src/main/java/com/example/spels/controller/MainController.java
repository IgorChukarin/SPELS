package com.example.spels.controller;

import com.example.spels.dto.ProductDto;
import com.example.spels.service.ProductCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/")
public class MainController {

    private final ProductCrudService productCrudService;

    public MainController(ProductCrudService productCRUDService) {
        this.productCrudService = productCRUDService;
    }

    @GetMapping
    public String getMainPage() {
        return "index";
    }

    @GetMapping("products")
    public String getProductsPage(Model model) {
        Collection<ProductDto> products = productCrudService.getAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("services")
    public String getServicesPage() {
        return "services";
    }

    @GetMapping("contacts")
    public String getContactsPage() {
        return "contacts";
    }
}
