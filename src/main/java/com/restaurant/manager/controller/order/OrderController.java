package com.restaurant.manager.controller.order;

import com.restaurant.manager.business.order.OrderService;
import com.restaurant.manager.dto.order.CreateOrderDto;
import com.restaurant.manager.dto.order.OrderDto;
import com.restaurant.manager.dto.product.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Save a new order")
    @ApiResponse(
            responseCode = "201",
            description = "Saved order",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderDto.class))}
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(List<CreateOrderDto> orderDtos) {
        return orderService.create(orderDtos);
    }

    @Operation(summary = "List orders")
    @ApiResponse(
            responseCode = "200",
            description = "List with all filtered orders"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> list() {
        return orderService.list(null);
    }
}
