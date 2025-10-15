package ru.spb.taranenkoant.order.domain.model;


import ru.spb.taranenkoant.order.domain.exception.IllegalOrderOperationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public class Order {
    private OrderId id;
    private CustomerId customerId;
    private List<OrderItem> items;
    private OrderStatus status;
    private Money totalPrice;

    // Статический фабричный метод
    public static Order create(CustomerId customerId, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        Order order = new Order();
        order.id = OrderId.generate();
        order.customerId = customerId;
        order.items = new ArrayList<>(items);
        order.status = OrderStatus.PENDING;
        order.totalPrice = calculateTotalPrice(items);

        return order;
    }

    // Метод для восстановления из базы данных (если нужен)
    public static Order restore(OrderId id, CustomerId customerId, List<OrderItem> items,
                                OrderStatus status, Money totalPrice) {
        Order order = new Order();
        order.id = id;
        order.customerId = customerId;
        order.items = new ArrayList<>(items);
        order.status = status;
        order.totalPrice = totalPrice;
        return order;
    }

    public void cancel() {
        if (status != OrderStatus.PENDING) {
            throw new IllegalOrderOperationException(
                    "Нельзя отменить заказ со статусом: %s. ",
                    status
            );
        }
        this.status = OrderStatus.CANCELLED;
    }

    public void confirm() {
        if (status != OrderStatus.PENDING) {
            throw new IllegalOrderOperationException(
                    "Не возможно подтвердить заказ со статусом: %s", status
            );
        }
        this.status = OrderStatus.CONFIRMED;
    }

    public void markAsPaid() {
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalOrderOperationException(
                    "Нельзя отметить оплаченным заказ со статусом: %s.",
                    status
            );
        }
        this.status = OrderStatus.PAID;
    }

    private Order() {
    }

    private static Money calculateTotalPrice(List<OrderItem> items) {
        return items.stream()
                .map(OrderItem::getSubTotal)
                .reduce(Money.ZERO, Money::add);
    }

    public OrderId getId() {
        return id;
    }
    public CustomerId getCustomerId() {
        return customerId;
    }
    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }
    public OrderStatus getStatus() {
        return status;
    }
    public Money getTotalPrice() {
        return totalPrice;
    }
}
