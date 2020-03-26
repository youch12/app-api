package com.octo.ecom.exception;


public class ProductNotFoundException  extends Exception {

    public ProductNotFoundException(String id) {
        super("No Product Found with the Id "+ id);
    }
}
