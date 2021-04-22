package com.github.ericomonteiro.smartstock.service.stock

import com.github.ericomonteiro.smartstock.model.Product
import com.github.ericomonteiro.smartstock.model.StockHistory
import com.github.ericomonteiro.smartstock.repository.ProductRepository
import com.github.ericomonteiro.smartstock.service.product.ProductService
import spock.lang.Specification

class StockServiceSpec extends Specification {

    var productService = Mock(ProductService)
    var productRepository = Mock(ProductRepository)

    var stockService = new StockService(productService, productRepository)
    var stockHistory = new ArrayList<StockHistory>()
    var product = new Product(PRODUCT_ID, "NAME", "DETAILS", 10.0, 15L, stockHistory)

    def PRODUCT_ID = 1L

    def "shall entry stock"() {
        when: "get the product"
        1 * productService.getAndValidProduct(PRODUCT_ID) >> product

        and: "save the new stock"
        1 * productRepository.save(_ as Product) >> {Product p -> p}

        then: "the entry must be registered"
        var newProduct = stockService.registerEntry(PRODUCT_ID, 10)
        newProduct.getStock() == 25L
        newProduct.getHistory().size() == 1
    }


}
