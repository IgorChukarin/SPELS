package com.example.spels.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imagePath;
    private String boldText;
    private String text;
    private String companyName;

    @Column(length = 1000)
    private String pageText;


    @ElementCollection
    @CollectionTable(name = "product_photos", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "photo_url")
    private List<String> photos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "product_documents", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "document_url")
    private List<String> documents = new ArrayList<>();
}
