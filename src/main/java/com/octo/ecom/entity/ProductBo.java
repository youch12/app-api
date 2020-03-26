package com.octo.ecom.entity;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = {"ref"})})
public class ProductBo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank(message = "Ref must not be empty or null")
    private String ref;

    @NotBlank(message = "Title must not be empty or null")
    private String title;

    @NumberFormat
    @PositiveOrZero
    @NotNull(message = "Quantity must not be null")
    @Min(0)
    private Integer quantity;

    @NumberFormat
    @PositiveOrZero
    @NotNull(message = "Price must not be null")
    @Min(0)
    private BigDecimal price;

    private String description;

    private String imageUrl;

    private String category;

    private Boolean isActive = false;

}
