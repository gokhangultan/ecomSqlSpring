package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.dto.ProductsResponse;
import com.ecom.gg.ecomgg.entity.Products;

import java.util.List;

public interface ProductsService {

    ProductsResponse save(Products products);
    ProductsResponse findById(long id);
    ProductsResponse delete(long id);

    List<ProductsResponse> getAllProducts();
}
