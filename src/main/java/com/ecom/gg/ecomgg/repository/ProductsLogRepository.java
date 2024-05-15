package com.ecom.gg.ecomgg.repository;

import com.ecom.gg.ecomgg.document.ProductsLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsLogRepository extends MongoRepository<ProductsLog, String> {


}
