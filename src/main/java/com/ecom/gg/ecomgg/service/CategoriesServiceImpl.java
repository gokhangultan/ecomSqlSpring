package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.dto.CategoriesResponse;
import com.ecom.gg.ecomgg.entity.Categories;
import com.ecom.gg.ecomgg.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
           return new CategoriesResponse(categoriesOptional.get().getId(),categoriesOptional.get().getTitle(), categoriesOptional.get().getCode(),categoriesOptional.get().getRating(),categoriesOptional.get().getImage());
       }
       //throw exception
        return null;
    }

    @Override
    public List<CategoriesResponse> findAll() {
        return categoriesRepository.findAll().stream()
                .map(category -> new CategoriesResponse(category.getId(), category.getTitle(), category.getCode(), category.getRating(),category.getImage()))
                .collect(Collectors.toList());
    }
}
