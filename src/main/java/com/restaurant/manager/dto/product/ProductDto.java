package com.restaurant.manager.dto.product;

import com.restaurant.manager.entity.category.Category;
import com.restaurant.manager.entity.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(
        @NotNull
        @Schema(description = "Product id", example = "1")
        Long id,

        @NotBlank
        @Schema(description = "Product name", example = "Rice")
        String name,

        @NotNull
        @DecimalMin(value = "0.01", inclusive = false)
        @Schema(description = "Product price", example = "8.90")
        Double price,

        @NotNull
        @Schema(implementation = Category.class, description = "Category the product refers to")
        Category category
) {

    public ProductDto(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory()
        );
    }

}
