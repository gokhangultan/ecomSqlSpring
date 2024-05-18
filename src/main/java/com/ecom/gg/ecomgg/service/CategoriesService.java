package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.dto.CategoriesResponse;
import com.ecom.gg.ecomgg.entity.Categories;

import java.util.List;

public interface CategoriesService {

    CategoriesResponse findById(long id);
    List<CategoriesResponse> findAll();


}
