package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.dto.CategoriesResponse;
import com.ecom.gg.ecomgg.entity.Categories;
import com.ecom.gg.ecomgg.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService{

    private CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public CategoriesResponse findById(long id) {
       Optional<Categories> categoriesOptional = categoriesRepository.findById(id);
       if(categoriesOptional.isPresent()){
           return new CategoriesResponse(categoriesOptional.get().getTitle(), categoriesOptional.get().getCode());
       }
       //throw exception
        return null;
    }
}
