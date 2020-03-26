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
import org.springframework.security.access.prepost.PreAuthorize;
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
        public ResponseEntity<?> getAllProducts()  {
        log.info("Get All Products");
        return new ResponseEntity<>( productsService.listProducts(), HttpStatus.OK);
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable("productId") String productId) throws ProductNotFoundException {
        log.info("Get Product with id: {} ", productId);
        return new ResponseEntity<>( productsService.getProduct(productId), HttpStatus.OK);
    }


    @PostMapping("/api/products")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDto productDto) throws BusinessException {
        log.info("Save product ");
        return new ResponseEntity<>( productsService.addProduct(productDto), HttpStatus.OK);
    }



    @PreAuthorize("@permissionEvaluator.isBotMaintainer(#botId)")
    @PutMapping("/api/products/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") String productId,
                                                    @Valid @RequestBody ProductDto productDto)
            throws ProductNotFoundException, BusinessException {
        log.info("Update product with id {} ", productId);
        return new ResponseEntity<>(productsService.editProduct(productId, productDto), HttpStatus.OK);
    }



    @PreAuthorize("@permissionEvaluator.isBotMaintainer(#botId)")
    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<?> deleteConversation(@PathVariable("productId") String productId)
            throws ProductNotFoundException {
        log.info("Delete product with id {} ", productId);
        productsService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
