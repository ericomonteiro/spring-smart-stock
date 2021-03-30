package com.github.ericomonteiro.smartstock.model.dto.product;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String details;
    private Float price;
}
