package com.octo.ecom.service;

import com.octo.ecom.dto.ProductDto;
import com.octo.ecom.exception.BusinessException;
import com.octo.ecom.exception.ProductNotFoundException;

import java.util.List;

public interface ProductsService {

    ProductDto addProduct(ProductDto productDto) throws  BusinessException;
    ProductDto editProduct(String productId, ProductDto productDto) throws ProductNotFoundException, BusinessException;
    void deleteProduct(String productId) throws ProductNotFoundException;
    ProductDto getProduct(String productId) throws  ProductNotFoundException;
    List<ProductDto> listProducts();

}