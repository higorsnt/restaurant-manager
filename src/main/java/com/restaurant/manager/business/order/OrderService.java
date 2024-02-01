package com.restaurant.manager.business.order;

import com.restaurant.manager.dto.order.CreateOrderDto;
import com.restaurant.manager.dto.order.NewProductDto;
import com.restaurant.manager.dto.order.OrderDto;
import com.restaurant.manager.dto.order.OrderPageDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderDto create(List<CreateOrderDto> productDtos);

    OrderPageDto list(Pageable pageable);

    OrderDto addProduct(Long id, NewProductDto productId);

}
