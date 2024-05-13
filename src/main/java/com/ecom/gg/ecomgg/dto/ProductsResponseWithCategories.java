package com.ecom.gg.ecomgg.dto;

import java.util.List;


public record ProductsResponseWithCategories(
        long id,
        double price,
        String description,
        String images,
        String category,
        String name,
        double rating,
        long sellCount,
        long stock,
        long storeId,
        List<CategoriesResponse> categories
) {

}
