package com.ecom.gg.ecomgg.document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collation = "products_log")
public class ProductsLog {

    //Mongo id leri String tutar
    @Id
    private String id;
    private Long productId;
    private String message;
    private Instant creationTime;
}
