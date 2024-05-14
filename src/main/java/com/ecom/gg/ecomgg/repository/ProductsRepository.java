package com.ecom.gg.ecomgg.repository;

import com.ecom.gg.ecomgg.dto.ProductsResponse;
import com.ecom.gg.ecomgg.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    @Query("SELECT p FROM Products p INNER JOIN p.categoriesList c WHERE c.code = :categoryCode")
    List<Products> findByCategoryCode(@Param("categoryCode") String categoryCode);

    List<Products> findAllByOrderByPriceAsc(); // Ascending order

    List<Products> findAllByOrderByPriceDesc(); // Descending order

}
