package com.ecom.gg.ecomgg.repository;

import com.ecom.gg.ecomgg.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {


}
