package com.restaurant.manager.dto.order;

import com.restaurant.manager.dto.product.ProductDto;
import com.restaurant.manager.entity.order.Order;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record OrderDto(
        @Schema(description = "Product id", example = "1")
        Long id,

        @ArraySchema(schema = @Schema(implementation = OrderProductDto.class))
        List<OrderProductDto> products,

        @Schema(description = "Total order value", example = "8.90")
        Double price
) {
    public OrderDto(Order order) {
        this(
                order.getId(),
                order.getProducts().stream().map(OrderProductDto::new).toList(),
                order.getPrice()
        );
    }
}
