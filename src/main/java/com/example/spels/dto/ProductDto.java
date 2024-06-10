package com.example.spels.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Integer id;
    private String boldText;
    private String text;
    private String imagePath;
}
