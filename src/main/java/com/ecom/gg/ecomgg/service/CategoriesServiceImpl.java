package com.ecom.gg.ecomgg.service;

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
    public Categories findById(long id) {
       Optional<Categories> categoriesOptional = categoriesRepository.findById(id);
       if(categoriesOptional.isPresent()){
           return categoriesOptional.get();
       }
       //throw exception
        return null;
    }
}
