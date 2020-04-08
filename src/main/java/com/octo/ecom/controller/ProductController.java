package com.octo.ecom.controller;

import com.octo.ecom.dto.ProductDto;
import com.octo.ecom.exception.BusinessException;
import com.octo.ecom.exception.ProductNotFoundException;
import com.octo.ecom.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/api/products")
        public ResponseEntity<?> getAllProducts() throws InterruptedException {
        log.info("Get All Products");
        Thread.sleep(2000);
        return new ResponseEntity<>( productsService.listProducts(), HttpStatus.OK);
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable("productId") String productId) throws ProductNotFoundException {
        log.info("Get Product with id: {} ", productId);
        return new ResponseEntity<>( productsService.getProduct(productId), HttpStatus.OK);
    }


    @PostMapping("/api/products")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDto productDto) throws BusinessException, InterruptedException {
        log.info("Save product ");
        Thread.sleep(2000);
        return new ResponseEntity<>( productsService.addProduct(productDto), HttpStatus.OK);
    }



    @PutMapping("/api/products/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") String productId,
                                                    @Valid @RequestBody ProductDto productDto)
            throws ProductNotFoundException, BusinessException {
        log.info("Update product with id {} ", productId);
        return new ResponseEntity<>(productsService.editProduct(productId, productDto), HttpStatus.OK);
    }



    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") String productId)
            throws ProductNotFoundException {
        log.info("Delete product with id {} ", productId);
        productsService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
