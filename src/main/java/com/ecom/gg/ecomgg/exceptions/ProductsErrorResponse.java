package com.ecom.gg.ecomgg.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsErrorResponse {
    private int status;
    private String message;
    private long timestamp;

}
