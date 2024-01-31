package com.restaurant.manager.business.order;

import com.restaurant.manager.dto.order.CreateOrderDto;
import com.restaurant.manager.dto.order.OrderDto;
import com.restaurant.manager.dto.product.ProductDto;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderDto create(List<CreateOrderDto> productDtos);

    List<OrderDto> list(Pageable pageable);

}
