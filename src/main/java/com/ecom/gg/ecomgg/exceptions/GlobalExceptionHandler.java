package com.ecom.gg.ecomgg.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice

public class GlobalExceptionHandler {

    public ResponseEntity<ProductsErrorResponse> handleException(ProductsException productsException){

        ProductsErrorResponse errorResponse = new ProductsErrorResponse(productsException.getStatus().value(), productsException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, productsException.getStatus());
    }


}
