package ru.spb.taranenkoant.order.domain.model;


import java.util.Objects;
import java.util.UUID;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public class CustomerId {
    private final String value;

    private CustomerId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Id заказчика не может быть пустым");
        }
        this.value = value;
    }

    public static CustomerId of(String value) {
        return new CustomerId(value);
    }

    public static CustomerId generate() {
        return new CustomerId("CUST-" + UUID.randomUUID());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerId that = (CustomerId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CustomerId{value='" + value + "'}";
    }
}
