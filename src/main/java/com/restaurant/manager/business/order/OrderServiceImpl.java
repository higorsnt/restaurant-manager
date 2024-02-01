package com.restaurant.manager.business.order;

import com.restaurant.manager.business.product.ProductService;
import com.restaurant.manager.dto.order.CreateOrderDto;
import com.restaurant.manager.dto.order.NewProductDto;
import com.restaurant.manager.dto.order.OrderDto;
import com.restaurant.manager.dto.order.OrderPageDto;
import com.restaurant.manager.entity.order.Order;
import com.restaurant.manager.entity.product.Product;
import com.restaurant.manager.entity.product_order.ProductOrder;
import com.restaurant.manager.entity.product_order.ProductOrderId;
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
        double value = 0;

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
    public OrderPageDto list(Pageable pageable) {
        return new OrderPageDto(repository.findAll(pageable));
    }

    @Override
    @Transactional
    public OrderDto addProduct(Long id, NewProductDto newProductDto) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %d not found.", id)));
        Product product = productService.findById(newProductDto.id());
        ProductOrder productOrder = new ProductOrder(new ProductOrderId(order, product), newProductDto.quantity());
        order.addProduct(productOrder);
        order.setPrice(order.getPrice() + product.getPrice() * newProductDto.quantity());
        return new OrderDto(order);
    }
}
