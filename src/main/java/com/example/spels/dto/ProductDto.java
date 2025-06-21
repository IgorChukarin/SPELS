package com.example.spels.dto;

import com.example.spels.model.PageDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Integer id;
    private String imagePath;
    private String boldText;
    private String text;
    private String companyName;
    private String pageText;
    private List<String> photos = new ArrayList<>();
    private List<PageDocument> documents = new ArrayList<>();
}
