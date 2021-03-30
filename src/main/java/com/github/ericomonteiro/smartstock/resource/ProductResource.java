package com.github.ericomonteiro.smartstock.resource;

import lombok.AllArgsConstructor;
import com.github.ericomonteiro.smartstock.model.Product;
import com.github.ericomonteiro.smartstock.model.dto.product.ProductCreateDto;
import com.github.ericomonteiro.smartstock.model.dto.product.ProductDto;
import com.github.ericomonteiro.smartstock.repository.ProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductResource {
    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        System.out.println("Entrou no método GET");
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productRepository.findById(id).get();
    }

    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductCreateDto productCreate) {
        System.out.println(productCreate);
        return new ProductDto();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
    }

}