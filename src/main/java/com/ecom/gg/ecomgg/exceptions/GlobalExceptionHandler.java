package com.ecom.gg.ecomgg.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//Intercapter araya giriyor problem anında
@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleException(ProductsException productsException){

        ProductsErrorResponse errorResponse = new ProductsErrorResponse(productsException.getStatus().value(), productsException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, productsException.getStatus());
    }


    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleException(ProductsLogException productsLogException) {
        ProductsErrorResponse errorResponse = new ProductsErrorResponse(
                productsLogException.getStatus().value(),
                productsLogException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, productsLogException.getStatus());
    }


    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleException(Exception exception){

        ProductsErrorResponse errorResponse = new ProductsErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
