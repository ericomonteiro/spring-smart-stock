package com.github.ericomonteiro.smartstock.service;

import com.github.ericomonteiro.smartstock.model.dto.product.ProductWithHistoryDto;
import com.github.ericomonteiro.smartstock.model.mapper.ProductMapper;
import com.github.ericomonteiro.smartstock.repository.ProductRepository;
import com.github.ericomonteiro.smartstock.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductWithHistoryDto registerEntry(Long productId, Long quantity) {
        var product = productService.getAndValidProduct(productId);
        product.registerEntry(quantity);
        return ProductMapper.toProductWithHistoryDto(productRepository.save(product));
    }

    public ProductWithHistoryDto registerExit(Long productId, Long quantity) {
        var product = productService.getAndValidProduct(productId);
        product.registerExit(quantity);
        return ProductMapper.toProductWithHistoryDto(productRepository.save(product));
    }
}
