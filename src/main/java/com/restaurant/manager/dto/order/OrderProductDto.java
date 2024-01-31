package com.restaurant.manager.dto.order;

import com.restaurant.manager.dto.product.ProductDto;
import com.restaurant.manager.entity.product_order.ProductOrder;
import io.swagger.v3.oas.annotations.media.Schema;

public record OrderProductDto(
        @Schema(description = "Order product")
        ProductDto productDto,

        @Schema(description = "Quantity of product in order", example = "2")
        Integer quantity
) {
    public OrderProductDto(ProductOrder productOrder) {
        this (
                new ProductDto(productOrder.getId().getProduct()),
                productOrder.getQuantity()
        );
    }
}
