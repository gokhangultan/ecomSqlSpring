package com.ecom.gg.ecomgg.repository;

import com.ecom.gg.ecomgg.entity.Products;

import java.util.List;

public interface ProductsRepository {

    List<Products> get();
    Products save(Products products);
}
