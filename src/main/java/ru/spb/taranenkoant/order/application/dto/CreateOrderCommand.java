package ru.spb.taranenkoant.order.application.dto;


import ru.spb.taranenkoant.order.domain.model.CustomerId;
import java.util.List;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public class CreateOrderCommand {
    private final CustomerId customerId;
    private final List<OrderItemData> items;

    public CreateOrderCommand(CustomerId customerId, List<OrderItemData> items) {
        this.customerId = customerId;
        this.items = items;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }
    public List<OrderItemData> getItems() {
        return items;
    }
}
