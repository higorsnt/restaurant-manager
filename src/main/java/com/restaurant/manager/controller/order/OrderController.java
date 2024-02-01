package com.restaurant.manager.controller.order;

import com.restaurant.manager.business.order.OrderService;
import com.restaurant.manager.dto.exception.ExceptionMessage;
import com.restaurant.manager.dto.order.CreateOrderDto;
import com.restaurant.manager.dto.order.NewProductDto;
import com.restaurant.manager.dto.order.OrderDto;
import com.restaurant.manager.dto.order.OrderPageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Saved order",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product specified in the order was not found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))}
                    ),
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(List<CreateOrderDto> orderDtos) {
        return orderService.create(orderDtos);
    }

    @Operation(summary = "List orders")
    @ApiResponse(
            responseCode = "200",
            description = "List with all filtered orders",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = OrderPageDto.class))}
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderPageDto list(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return orderService.list(pageable);
    }

    @Operation(summary = "Add a product to the order")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order updated with new product"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested order or product was not found.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionMessage.class))}
                    ),
            }
    )
    @PatchMapping("/{orderId}/add")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto addProduct(@PathVariable Long orderId, @Valid @RequestBody NewProductDto newProductDto) {
        return orderService.addProduct(orderId, newProductDto);
    }
}
