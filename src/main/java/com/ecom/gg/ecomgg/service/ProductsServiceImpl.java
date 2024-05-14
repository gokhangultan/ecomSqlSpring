package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.dto.ProductsResponse;
import com.ecom.gg.ecomgg.entity.Products;
import com.ecom.gg.ecomgg.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductsResponse> findByCategoryCode(String categoryCode) {
        List<Products> productsList = productsRepository.findByCategoryCode(categoryCode);

        return productsList.stream()
                .map(this::mapToProductsResponse)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductsResponse> findAllByOrderByPriceAsc() {
        List<Products> productsList = productsRepository.findAllByOrderByPriceAsc();
        return mapToProductsResponseList(productsList);
    }

    @Override
    public List<ProductsResponse> findAllOrderByRatingDesc() {
        List<Products> products = productsRepository.findAllOrderByRatingDesc();
        return products.stream()
                .map(this::mapToProductsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsResponse> findAllOrderByRatingAsc() {
        List<Products> products = productsRepository.findAllOrderByRatingAsc();
        return products.stream()
                .map(this::mapToProductsResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsResponse> findAllByOrderByPriceDesc() {
        List<Products> productsList = productsRepository.findAllByOrderByPriceDesc();
        return mapToProductsResponseList(productsList);
    }

    private List<ProductsResponse> mapToProductsResponseList(List<Products> productsList) {
        return productsList.stream()
                .map(this::mapToProductsResponse)
                .collect(Collectors.toList());
    }


    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    private ProductsResponse mapToProductsResponse(Products products) {
        return new ProductsResponse(products.getId(), products.getPrice(), products.getDescription(), products.getImages(),
                products.getCategory(), products.getName(), products.getRating(), products.getSellCount(), products.getStock(),
                products.getStoreId());
    }

}
