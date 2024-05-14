package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.dto.ProductsResponse;
import com.ecom.gg.ecomgg.entity.Products;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsService {

    ProductsResponse save(Products products);
    ProductsResponse findById(long id);
    ProductsResponse delete(long id);
    List<ProductsResponse> findByCategoryCode(@Param("categoryCode") String categoryCode);
    List<ProductsResponse> findAllByOrderByPriceAsc(); // Ascending order

    List<ProductsResponse> findAllOrderByRatingDesc();
    List<ProductsResponse> findAllOrderByRatingAsc();
    List<ProductsResponse> findAllByOrderByPriceDesc(); // Descending order
    List<Products> getAllProducts();}
