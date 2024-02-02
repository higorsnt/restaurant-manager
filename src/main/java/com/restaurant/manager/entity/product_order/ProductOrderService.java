package com.restaurant.manager.entity.product_order;

import com.restaurant.manager.entity.order.Order;
import com.restaurant.manager.entity.product.Product;

public interface ProductOrderService {

    ProductOrder create(Product product, Order order, Integer quantity);

}
