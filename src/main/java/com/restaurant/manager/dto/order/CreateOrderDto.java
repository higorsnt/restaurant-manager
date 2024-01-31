package com.restaurant.manager.dto.order;

import com.restaurant.manager.dto.product.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateOrderDto(
        @NotNull
        @Schema(description = "order product")
        ProductDto productDto,

        @Min(1)
        @Schema(description = "quantity of product in order", example = "2")
        Integer quantity) {
}
