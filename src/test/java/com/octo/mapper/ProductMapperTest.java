package com.octo.mapper;

import com.octo.ecom.dto.ProductDto;
import com.octo.ecom.entity.ProductBo;
import com.octo.ecom.mapper.ProductMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void Should_Map_Dto_To_Bo() {

       ProductMapper.mapDtoToBo(new ProductBo(), new ProductDto());
    }

    @Test
    void Should_Map_Bo_To_Dto() {
        ProductBo productBo = new ProductBo();
        productBo.setId("87d687c95");
        productBo.setRef("Product Ref 2");
        productBo.setTitle("Product Title 2");
        productBo.setDescription("Product Description 2");
        productBo.setQuantity(101);
        productBo.setPrice(BigDecimal.valueOf(56));
        productBo.setImageUrl("ImageUrl 2");
        productBo.setCategory("Category2");
        productBo.setIsActive(false);

        ProductDto productDto = ProductMapper.mapBoToDto(productBo);

        assertEquals("Product Ref 2", productDto.getProductRef());
        assertEquals("Product Title 2", productDto.getProductTitle() );
        assertEquals("Product Description 2", productDto.getProductDescription());
        assertEquals(101, productDto.getProductQuantity());
        assertEquals(BigDecimal.valueOf(56), productDto.getProductPrice());
        assertEquals("ImageUrl 2", productDto.getProductImageUrl());
        assertEquals("Category2", productDto.getProductCategory());
        assertFalse(productDto.getProductIsActive());
    }
}