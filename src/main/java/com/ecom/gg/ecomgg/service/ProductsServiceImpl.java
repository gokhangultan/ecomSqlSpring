package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.dto.ProductsResponse;
import com.ecom.gg.ecomgg.entity.Products;
import com.ecom.gg.ecomgg.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService{


    //dao
    private ProductsRepository productsRepository;
    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public ProductsResponse save(Products products) {
        Products savedProducts = productsRepository.save(products);
        return new ProductsResponse(savedProducts.getId(),savedProducts.getPrice(),savedProducts.getDescription(),
                savedProducts.getImages(),savedProducts.getCategory(),savedProducts.getName(),
                savedProducts.getRating(),savedProducts.getSellCount(),savedProducts.getStock(),
                savedProducts.getStoreId());
    }

    @Override
    public ProductsResponse findById(long id) {
        Optional<Products> productsOptional = productsRepository.findById(id);
        if (productsOptional.isPresent()) {
            Products foundProducts = productsOptional.get();
            return new ProductsResponse(foundProducts.getId(),foundProducts.getPrice(),foundProducts.getDescription(),foundProducts.getImages(),
                    foundProducts.getCategory(),foundProducts.getName(),foundProducts.getRating(),foundProducts.getSellCount(),foundProducts.getStock(),
                    foundProducts.getStoreId());
        }
        //throw exception
        return null;
    }

    @Override
    public ProductsResponse delete(long id) {
        ProductsResponse willRemoveProductsResponse = findById(id);
        productsRepository.deleteById(willRemoveProductsResponse.id());
        return null;
    }
}
