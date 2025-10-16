package ru.spb.taranenkoant.order.adapter.output.persistence.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Column(name = "product_id")
    private String productId;

    private int quantity;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
