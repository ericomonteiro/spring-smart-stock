package com.github.ericomonteiro.smartstock.model.mapper;

import com.github.ericomonteiro.smartstock.model.Product;
import com.github.ericomonteiro.smartstock.model.dto.product.ProductCreateDto;
import com.github.ericomonteiro.smartstock.model.dto.product.ProductDto;

public class ProductMapper {

    public static Product of(ProductCreateDto o) {
        return new Product(0L, o.getName(), o.getDetails(), o.getPrice());
    }

    public static ProductDto toProductDto(Product p) {
        return new ProductDto(p.getId(), p.getName(), p.getDetails(), p.getPrice());
    }

}
