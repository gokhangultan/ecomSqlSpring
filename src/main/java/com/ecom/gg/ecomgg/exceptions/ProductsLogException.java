package com.ecom.gg.ecomgg.exceptions;

import org.springframework.http.HttpStatus;

public class ProductsLogException extends RuntimeException {
    private HttpStatus status;

    public ProductsLogException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
