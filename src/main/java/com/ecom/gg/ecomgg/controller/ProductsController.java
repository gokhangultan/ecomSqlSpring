package com.ecom.gg.ecomgg.controller;

import com.ecom.gg.ecomgg.entity.Products;
import com.ecom.gg.ecomgg.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductsRepository productsRepository;

    @Autowired
    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products save(@RequestBody Products products){
        Products savedProducts = productsRepository.save(products);
        return savedProducts;
    }
}
