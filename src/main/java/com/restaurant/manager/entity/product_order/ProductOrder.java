package com.restaurant.manager.entity.product_order;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "PRODUCT_ORDER")
public class ProductOrder {

    @EmbeddedId
    private ProductOrderId id;

    @Column(name = "quantity")
    private Integer quantity;

    public ProductOrder() {
    }

    public ProductOrder(ProductOrderId id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public ProductOrderId getId() {
        return id;
    }

    public void setId(ProductOrderId id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
