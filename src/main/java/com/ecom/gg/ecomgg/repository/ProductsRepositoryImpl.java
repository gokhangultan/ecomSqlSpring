package com.ecom.gg.ecomgg.repository;

import com.ecom.gg.ecomgg.entity.Products;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductsRepositoryImpl implements ProductsRepository {

    private EntityManager entityManager;

    @Autowired
    public ProductsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Products> get() {
        //SQL --> JPQL
        // SELECT p.id FROM Products p
        // SELECT p.name FROM Products p
       TypedQuery<Products> query = entityManager.createQuery("SELECT p FROM Products p", Products.class);
        return query.getResultList();
    }
    @Transactional
    @Override
    public Products save(Products products) {
        entityManager.persist(products);
        return products;
    }
}
