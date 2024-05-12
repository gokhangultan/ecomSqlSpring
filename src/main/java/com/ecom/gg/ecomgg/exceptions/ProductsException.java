package com.ecom.gg.ecomgg.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProductsException extends RuntimeException{

    private HttpStatus status;

    public ProductsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
