package ru.spb.taranenkoant.order.domain.model;


import java.util.Objects;
import java.util.UUID;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public class OrderId {

    private final String value;

    public OrderId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("id заказа не может быть пустым");
        }
        this.value = value;
    }

    public static OrderId of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("id заказа не может быть пустым");
        }
        return new OrderId(value);
    }

    public static OrderId generate() {
        return new OrderId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return Objects.equals(value, orderId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
