package ru.spb.taranenkoant.order.domain.model;


import java.util.Objects;
import java.util.UUID;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public class ProductId {
    private final String value;

    private ProductId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("id не может быть пустым");
        }
        this.value = value;
    }

    public static ProductId of(String value) {
        return new ProductId(value);
    }

    public static ProductId generate() {
        return new ProductId("PROD-" + UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return Objects.equals(value, productId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ProductId{value='" + value + "'}";
    }
}
