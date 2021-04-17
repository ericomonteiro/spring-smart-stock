package com.github.ericomonteiro.smartstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition="TEXT", nullable = false)
    private String details;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Long stock;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<StockHistory> history;

    public Product(Long id, String name, String details, Float price) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.price = price;
        this.stock = 0L;
    }

}
