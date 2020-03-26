package com.octo.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String productId;
    @NotBlank(message = "Ref must not be null or empty")
    private String productRef;
    @NumberFormat
    @PositiveOrZero
    @NotNull(message = "Quantity must not be null")
    @Min(0)
    private Integer productQuantity;
    @NumberFormat
    @PositiveOrZero
    @NotNull(message = "Price must not be null")
    @Min(0)
    private BigDecimal productPrice;
    private String productDescription;
    @NotBlank(message = "Ref must not be null or empty")
    private String productTitle;
    private String productImageUrl;
    private String productCategory;
    private Boolean productIsActive = false;

}