package com.restaurant.manager.dto.order;

import com.restaurant.manager.entity.order.Order;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

public record OrderPageDto(

        @ArraySchema(schema = @Schema(description = "Elements returned by the search", implementation = OrderDto.class))
        List<OrderDto> products,

        @Schema(description = "Current page with returned values", example = "3")
        Integer currentPage,

        @Schema(description = "Total number of elements returned by the search", example = "100")
        Long totalElements,

        @Schema(description = "Total number of pages containing the elements returned by the search", example = "5")
        Integer totalPages
) {
    public OrderPageDto(Page<Order> page) {
        this(
                page.stream().map(OrderDto::new).toList(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
