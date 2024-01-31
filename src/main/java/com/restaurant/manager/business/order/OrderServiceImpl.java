package com.restaurant.manager.business.order;

import com.restaurant.manager.business.product.ProductService;
import com.restaurant.manager.dto.order.CreateOrderDto;
import com.restaurant.manager.dto.order.OrderDto;
import com.restaurant.manager.dto.product.ProductDto;
import com.restaurant.manager.entity.order.Order;
import com.restaurant.manager.entity.product.Product;
import com.restaurant.manager.entity.product_order.ProductOrder;
import com.restaurant.manager.entity.product_order.ProductOrderId;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ProductService productService;

    @Autowired
    OrderServiceImpl(OrderRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    @Transactional
    public OrderDto create(List<CreateOrderDto> productDtos) {
        Order order = new Order();
        List<ProductOrder> products = new ArrayList<>();
        Double value = 0d;

        for (CreateOrderDto createOrderDto : productDtos) {
            Product product = productService.findById(createOrderDto.productDto().id());

            ProductOrder productOrder = new ProductOrder();
            productOrder.setId(new ProductOrderId(order, product));
            productOrder.setQuantity(createOrderDto.quantity());
            products.add(productOrder);

            value += createOrderDto.quantity() * product.getPrice();
        }

        order = repository.save(new Order(value, products));

        return new OrderDto(order);
    }

    @Override
    public List<OrderDto> list(Pageable pageable) {
        if (pageable == null) {
            pageable = Pageable.unpaged();
        }

        return repository.findAll(pageable)
                .map(OrderDto::new)
                .stream()
                .toList();
    }
}
