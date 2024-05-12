package com.ecom.gg.ecomgg.dto;

public record ProductsResponse(long id, double price, String description, String images, String category, String name, double rating, long sellCount, long stock, long storeId) {
}
