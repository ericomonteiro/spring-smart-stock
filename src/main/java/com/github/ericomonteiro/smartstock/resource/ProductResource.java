package com.github.ericomonteiro.smartstock.resource;

import com.github.ericomonteiro.smartstock.model.Product;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @GetMapping
    public String getAllProducts() {
        System.out.println("Entrou no método GET");
        return "Lista de produtos";
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        System.out.println(id);
        Product p = new Product(1L, "Mouse", "Mouse Gamer", 85f);
        return p;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        System.out.println(id);
        return "Excluir produto";
    }

}