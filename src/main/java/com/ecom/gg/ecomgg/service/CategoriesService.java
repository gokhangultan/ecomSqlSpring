package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.dto.CategoriesResponse;
import com.ecom.gg.ecomgg.entity.Categories;

public interface CategoriesService {

    CategoriesResponse findById(long id);
}
