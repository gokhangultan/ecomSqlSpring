package com.ecom.gg.ecomgg.controller;

import com.ecom.gg.ecomgg.dto.CategoriesResponse;
import com.ecom.gg.ecomgg.dto.ProductsResponse;
import com.ecom.gg.ecomgg.exceptions.ProductsException;
import com.ecom.gg.ecomgg.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private CategoriesService categoriesService;


    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriesResponse> findById(@PathVariable long id) {
        CategoriesResponse categoriesResponse = categoriesService.findById(id);
        if (categoriesResponse != null) {
            return new ResponseEntity<>(categoriesResponse, HttpStatus.OK);
        } else {
            throw new ProductsException("Category bulunamadı: " + id, HttpStatus.NOT_FOUND);
        }
    }

}
