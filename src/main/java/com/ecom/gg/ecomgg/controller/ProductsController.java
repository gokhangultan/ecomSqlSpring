package com.ecom.gg.ecomgg.controller;

import com.ecom.gg.ecomgg.document.ProductsLog;
import com.ecom.gg.ecomgg.dto.CategoriesResponse;
import com.ecom.gg.ecomgg.dto.ProductsCategoriesRequest;
import com.ecom.gg.ecomgg.dto.ProductsResponse;
import com.ecom.gg.ecomgg.dto.ProductsResponseWithCategories;
import com.ecom.gg.ecomgg.entity.Categories;
import com.ecom.gg.ecomgg.entity.Products;
import com.ecom.gg.ecomgg.exceptions.ProductsException;
import com.ecom.gg.ecomgg.repository.ProductsRepository;
import com.ecom.gg.ecomgg.service.ProductsLogService;
import com.ecom.gg.ecomgg.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name="Rest API for Products..", description = "CRUD Operations for Products.")
@Validated
@RestController
@RequestMapping("/products")
public class ProductsController {

   private ProductsService productsService;
   private ProductsLogService productsLogService;

   @Autowired
    public ProductsController(ProductsService productsService, ProductsLogService productsLogService) {
        this.productsService = productsService;
        this.productsLogService = productsLogService;
    }

    @Operation(summary = "Save Product with Category List", description = "User need to post Product with details also need categoriesList in array, List format")
    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "JSON Must Be Same Format With ProductsCategpriesRequest DTO")
    public ProductsResponse save(@RequestBody ProductsCategoriesRequest productsCategoriesRequest){
        Products products = productsCategoriesRequest.getProducts();
        List<Categories> categoriesList = productsCategoriesRequest.getCategoriesList();

        for(Categories categories: categoriesList){
            products.addCategories(categories);

        }
        ProductsResponse savedProductsResponse = productsService.save(products);
        return savedProductsResponse;
    }

    @Operation(summary = "Response All Products With mapped Categories")
    @GetMapping("/")
    @ApiResponse(responseCode = "200")
    public List<ProductsResponseWithCategories> getAllProductsWithCategories() {
        List<Products> productsList = productsService.getAllProducts();
        List<ProductsResponseWithCategories> responseList = new ArrayList<>();

        for (Products product : productsList) {
            List<CategoriesResponse> categoryDetailsList = new ArrayList<>();
            for (Categories category : product.getCategoriesList()) {
                CategoriesResponse categoryDetails = new CategoriesResponse(category.getId(),category.getTitle(), category.getCode(), category.getRating(),category.getImage());
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


    @Operation(summary = "Response Product With Given Product {id}.", description = "Some of the datas are hidden for performance and security reasons.")
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Product id Must Be Valid")
    public ResponseEntity<ProductsResponse> findById(@PathVariable long id) {
        ProductsResponse productsResponse = productsService.findById(id);


        //encapsulation nice example
        ProductsLog productsLog = new ProductsLog();
        productsLog.setProductId(productsResponse.id());
        productsLog.setMessage("Product found successfully with given Id:" + productsLog.getProductId());
        productsLog.setCreationTime(Instant.now());
        productsLogService.save(productsLog);

        if (productsResponse != null) {
            return new ResponseEntity<>(productsResponse, HttpStatus.OK);
        } else {
            throw new ProductsException("Ürün bulunamadı: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Response Products With filtered by {categoryCode}.", description = "Response Products details with associated {categoryCode}.")
    @GetMapping("/category/{categoryCode}")
    @ApiResponse(responseCode = "200", description = "Category Code Must Be Valid")
    public ResponseEntity<List<ProductsResponse>> findByCategoryCode(@PathVariable String categoryCode) {
        List<ProductsResponse> products = productsService.findByCategoryCode(categoryCode);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @Operation(summary = "Sort Request for Products.", description = "Response All Products within sorted by RequestParams (asc,desc,ratingasc,ratingdesc)")
    @GetMapping("/sort")
    @ApiResponse(responseCode = "200", description = "There is only 1 order type accepted")
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
