package com.restaurant.manager.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record NewProductDto(
        @NotNull
        @Schema(description = "Product id", example = "1")
        Long id,

        @Schema(description = "Product quantity", example = "3", defaultValue = "1")
        Integer quantity
) {
}
