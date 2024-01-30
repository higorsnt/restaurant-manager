package com.restaurant.manager.entity.product_order;

import com.restaurant.manager.entity.order.Order;
import com.restaurant.manager.entity.product.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductOrderId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrderId that = (ProductOrderId) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }

    @Override
    public String toString() {
        return "ProductOrderId{" +
                "order=" + order +
                ", product=" + product +
                '}';
    }
}
