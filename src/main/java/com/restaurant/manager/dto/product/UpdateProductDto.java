package com.restaurant.manager.dto.product;

import com.restaurant.manager.entity.category.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;

public record UpdateProductDto(
        @Schema(description = "Product name", example = "Rice")
        String name,

        @DecimalMin(value = "0.01", inclusive = false)
        @Schema(description = "Product price", example = "8.90")
        Double price,

        @Schema(implementation = Category.class, description = "Category the product refers to")
        Category category
) {
}
