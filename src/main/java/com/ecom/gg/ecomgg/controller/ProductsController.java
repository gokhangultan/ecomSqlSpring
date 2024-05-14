package com.ecom.gg.ecomgg.controller;

import com.ecom.gg.ecomgg.dto.CategoriesResponse;
import com.ecom.gg.ecomgg.dto.ProductsCategoriesRequest;
import com.ecom.gg.ecomgg.dto.ProductsResponse;
import com.ecom.gg.ecomgg.dto.ProductsResponseWithCategories;
import com.ecom.gg.ecomgg.entity.Categories;
import com.ecom.gg.ecomgg.entity.Products;
import com.ecom.gg.ecomgg.exceptions.ProductsException;
import com.ecom.gg.ecomgg.repository.ProductsRepository;
import com.ecom.gg.ecomgg.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductsController {

   private ProductsService productsService;

   @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;

    }
    @PostMapping("/")
    public ProductsResponse save(@RequestBody ProductsCategoriesRequest productsCategoriesRequest){
        Products products = productsCategoriesRequest.getProducts();
        List<Categories> categoriesList = productsCategoriesRequest.getCategoriesList();

        for(Categories categories: categoriesList){
            products.addCategories(categories);

        }
        ProductsResponse savedProductsResponse = productsService.save(products);
        return savedProductsResponse;
    }


    @GetMapping("/")
    public List<ProductsResponseWithCategories> getAllProductsWithCategories() {
        List<Products> productsList = productsService.getAllProducts();
        List<ProductsResponseWithCategories> responseList = new ArrayList<>();

        for (Products product : productsList) {
            List<CategoriesResponse> categoryDetailsList = new ArrayList<>();
            for (Categories category : product.getCategoriesList()) {
                CategoriesResponse categoryDetails = new CategoriesResponse(category.getId(),category.getTitle(), category.getCode(), category.getRating());
                categoryDetailsList.add(categoryDetails);
            }
            ProductsResponseWithCategories response = new ProductsResponseWithCategories(
                    product.getId(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getImages(),
                    product.getCategory(),
                    product.getName(),
                    product.getRating(),
                    product.getSellCount(),
                    product.getStock(),
                    product.getStoreId(),
                    categoryDetailsList
            );
            responseList.add(response);
        }

        return responseList;
    }



    @GetMapping("/{id}")
    public ResponseEntity<ProductsResponse> findById(@PathVariable long id) {
        ProductsResponse productsResponse = productsService.findById(id);
        if (productsResponse != null) {
            return new ResponseEntity<>(productsResponse, HttpStatus.OK);
        } else {
            throw new ProductsException("Ürün bulunamadı: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{categoryCode}")
    public ResponseEntity<List<ProductsResponse>> findByCategoryCode(@PathVariable String categoryCode) {
        List<ProductsResponse> products = productsService.findByCategoryCode(categoryCode);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/sort")
    public ResponseEntity<List<ProductsResponse>> sortProducts(@RequestParam("order") String order) {
        List<ProductsResponse> sortedProducts;
        if ("asc".equalsIgnoreCase(order)) {
            sortedProducts = productsService.findAllByOrderByPriceAsc();
        } else if ("desc".equalsIgnoreCase(order)) {
            sortedProducts = productsService.findAllByOrderByPriceDesc();
        } else if ("ratingasc".equalsIgnoreCase(order)) {
            sortedProducts = productsService.findAllOrderByRatingAsc();
        } else if ("ratingdesc".equalsIgnoreCase(order)) {
            sortedProducts = productsService.findAllOrderByRatingDesc();
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sortedProducts, HttpStatus.OK);
    }

}
