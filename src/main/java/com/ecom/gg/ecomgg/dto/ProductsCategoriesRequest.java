package com.ecom.gg.ecomgg.dto;

import com.ecom.gg.ecomgg.entity.Categories;
import com.ecom.gg.ecomgg.entity.Products;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProductsCategoriesRequest {

    private Products products;
    private List<Categories> categoriesList;
}
