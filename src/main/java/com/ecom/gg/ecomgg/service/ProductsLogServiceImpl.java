package com.ecom.gg.ecomgg.service;

import com.ecom.gg.ecomgg.document.ProductsLog;
import com.ecom.gg.ecomgg.exceptions.ProductsLogException;
import com.ecom.gg.ecomgg.repository.ProductsLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductsLogServiceImpl implements  ProductsLogService{

    private ProductsLogRepository productsLogRepository;

    @Autowired
    public ProductsLogServiceImpl(ProductsLogRepository productsLogRepository) {
        this.productsLogRepository = productsLogRepository;
    }

    @Override
    public ProductsLog save(ProductsLog productsLog) {
        try {
            return productsLogRepository.save(productsLog);
        } catch (Exception e) {
            throw new ProductsLogException("Unable to save product log", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
