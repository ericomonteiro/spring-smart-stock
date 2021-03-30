package com.github.ericomonteiro.smartstock.model.dto.product;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@ToString
public class ProductCreateDto {
    @NotBlank
    private String name;

    @NotBlank
    private String details;

    @Positive
    private Float price;
}
