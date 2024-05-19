package com.ecom.gg.ecomgg.repository;

import com.ecom.gg.ecomgg.entity.Categories;
import com.ecom.gg.ecomgg.entity.Products;
import io.swagger.v3.oas.annotations.media.DependentSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//JUNIT 5  --> Jupiter API  (5.gezegen)

@SpringBootTest
class ProductsRepositoryTest {

    private ProductsRepository productsRepository;

    @Autowired
    public ProductsRepositoryTest(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @DisplayName("Can Find Products By Given CategoryCode")
    @Test
    void findByCategoryCode() {
        Products products = new Products();
        products.setName("testProduct");
        products.setImages("test image url");
        products.setCategory("test category");
        products.setPrice(100);
        products.setDescription("test description");
        products.setRating(3.5);
        products.setSellCount(5);
        products.setStoreId(2);
        products.setSellCount(125);
        products.setStock(200);

        Categories category1 = new Categories();
        category1.setCode("new:season");
        category1.setImage("newseason.png");
        category1.setRating(4.0);
        category1.setTitle("Category 1");

        Categories category2 = new Categories();
        category2.setCode("old:season");
        category2.setImage("oldseason.png");
        category2.setRating(4.5);
        category2.setTitle("Category 2");

        List<Categories> categoriesList = new ArrayList<>();
        categoriesList.add(category1);
        categoriesList.add(category2);
        products.setCategoriesList(categoriesList);


        productsRepository.save(products);

        List<Products> foundProducts = productsRepository.findByCategoryCode("new:season");
        assertNotNull(foundProducts);
        assertFalse(foundProducts.isEmpty());
    }

    @DisplayName("Can Order Products By Price Ascent")
    @Test
    void findAllByOrderByPriceAsc() {
    }

    @DisplayName("Can Order Products By Price Descent")
    @Test
    void findAllByOrderByPriceDesc() {
    }

    @DisplayName("Can Order Products By Rating Descent")
    @Test
    void findAllOrderByRatingDesc() {
    }

    @DisplayName("Can Order Products By Rating Ascent")
    @Test
    void findAllOrderByRatingAsc() {
    }

    @DisplayName("Can Order Products By Ascent Price With Descent Rating")
    @Test
    void findAllOrderByPriceAscAndRatingDesc() {
    }
}