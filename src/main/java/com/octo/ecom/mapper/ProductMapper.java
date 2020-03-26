package com.octo.ecom.mapper;

import com.octo.ecom.dto.ProductDto;
import com.octo.ecom.entity.ProductBo;

public interface ProductMapper {

    static ProductBo mapDtoToBo(ProductBo productBo, ProductDto productDto) {
        productBo.setRef(productDto.getProductRef());
        productBo.setTitle(productDto.getProductTitle());
        productBo.setDescription(productDto.getProductDescription());
        productBo.setQuantity(productDto.getProductQuantity());
        productBo.setPrice(productDto.getProductPrice());
        productBo.setImageUrl(productDto.getProductImageUrl());
        productBo.setCategory(productDto.getProductCategory());
        productBo.setIsActive(productDto.getProductIsActive());
        return productBo;
    }

    static ProductDto mapBoToDto(ProductBo productBo) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productBo.getId());
        productDto.setProductRef(productBo.getRef());
        productDto.setProductTitle(productBo.getTitle());
        productDto.setProductDescription(productBo.getDescription());
        productDto.setProductQuantity(productBo.getQuantity());
        productDto.setProductPrice(productBo.getPrice());
        productDto.setProductImageUrl(productBo.getImageUrl());
        productDto.setProductCategory(productBo.getCategory());
        productDto.setProductIsActive(productBo.getIsActive());
        return productDto;
    }
}
