package com.restaurant.manager.business.order;

import com.restaurant.manager.business.product.ProductService;
import com.restaurant.manager.dto.order.CreateOrderDto;
import com.restaurant.manager.dto.order.NewProductDto;
import com.restaurant.manager.dto.order.OrderDto;
import com.restaurant.manager.dto.order.OrderPageDto;
import com.restaurant.manager.entity.order.Order;
import com.restaurant.manager.entity.product.Product;
import com.restaurant.manager.entity.product_order.ProductOrder;
import com.restaurant.manager.entity.product_order.ProductOrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ProductOrderService productOrderService;
    private final ProductService productService;

    @Autowired
    OrderServiceImpl(OrderRepository repository, ProductOrderService productOrderService, ProductService productService) {
        this.repository = repository;
        this.productOrderService = productOrderService;
        this.productService = productService;
    }

    @Override
    public OrderDto create(List<CreateOrderDto> productDtos) {
        Order order = new Order();
        List<ProductOrder> products = new ArrayList<>();
        double value = 0;

        order = repository.save(new Order());

        for (CreateOrderDto createOrderDto : productDtos) {
            Product product = productService.findById(createOrderDto.productId());
            ProductOrder productOrder = productOrderService.create(product, order, createOrderDto.quantity());
            value += createOrderDto.quantity() * product.getPrice();
            order.addProduct(productOrder);
        }

        order.setPrice(value);
        repository.save(order);

        return new OrderDto(order);
    }

    @Override
    public OrderPageDto list(Pageable pageable) {
        return new OrderPageDto(repository.findAll(pageable));
    }

    @Override
    @Transactional
    public OrderDto addProduct(Long id, NewProductDto newProductDto) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %d not found.", id)));
        Product product = productService.findById(newProductDto.id());
        ProductOrder productOrder = new ProductOrder(order, product, newProductDto.quantity());
        order.addProduct(productOrder);
        order.setPrice(order.getPrice() + product.getPrice() * newProductDto.quantity());
        return new OrderDto(order);
    }
}
