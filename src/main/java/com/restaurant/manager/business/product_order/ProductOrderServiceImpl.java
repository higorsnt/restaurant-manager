package com.restaurant.manager.business.product_order;

import com.restaurant.manager.entity.order.Order;
import com.restaurant.manager.entity.product.Product;
import com.restaurant.manager.entity.product_order.ProductOrder;
import com.restaurant.manager.entity.product_order.ProductOrderService;
import org.springframework.stereotype.Service;

@Service
class ProductOrderServiceImpl implements ProductOrderService {

    private final ProductOrderRepository repository;

    public ProductOrderServiceImpl(ProductOrderRepository repository) {
        this.repository = repository;
    }


    public ProductOrder create(Product product, Order order, Integer quantity) {
        ProductOrder productOrder = new ProductOrder(order, product, quantity);
        return repository.save(productOrder);
    }
}
