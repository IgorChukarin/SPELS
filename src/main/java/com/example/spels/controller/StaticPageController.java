package com.example.spels.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StaticPageController {

    @GetMapping
    public String getMainPage() {
        return "index";
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
