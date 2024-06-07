package com.example.spels.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getMainPage() {
        return "index";
    }

    @GetMapping("products")
    public String getProductsPage() {
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
