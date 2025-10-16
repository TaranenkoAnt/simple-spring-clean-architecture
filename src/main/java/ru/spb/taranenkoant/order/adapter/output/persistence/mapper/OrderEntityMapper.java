package ru.spb.taranenkoant.order.adapter.output.persistence.mapper;


import org.springframework.stereotype.Component;
import ru.spb.taranenkoant.order.adapter.output.persistence.entity.OrderEntity;
import ru.spb.taranenkoant.order.adapter.output.persistence.entity.OrderItemEntity;
import ru.spb.taranenkoant.order.domain.model.*;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
@Component
public class OrderEntityMapper {

    public OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId().getValue());
        entity.setCustomerId(order.getCustomerId().getValue());
        entity.setStatus(order.getStatus());
        entity.setTotalAmount(order.getTotalPrice().getAmount());
        entity.setCurrency(order.getTotalPrice().getCurrency().getCurrencyCode());

        // Маппинг items
        List<OrderItemEntity> itemEntities = order.getItems().stream()
                .map(this::toItemEntity)
                .collect(Collectors.toList());
        entity.setItems(itemEntities);

        itemEntities.forEach(item -> item.setOrder(entity));

        return entity;
    }

    public Order toDomain(OrderEntity entity) {
        List<OrderItem> items = entity.getItems().stream()
                .map(this::toOrderItem)
                .collect(Collectors.toList());

        return Order.restore(
                OrderId.of(entity.getId()),
                CustomerId.of(entity.getCustomerId()),
                items,
                entity.getStatus(),
                Money.of(entity.getTotalAmount(), Currency.getInstance(entity.getCurrency()))
        );
    }

    private OrderItemEntity toItemEntity(OrderItem item) {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setProductId(item.getProductId().getValue());
        entity.setQuantity(item.getQuantity());
        entity.setPrice(item.getPrice().getAmount());
        return entity;
    }

    private OrderItem toOrderItem(OrderItemEntity entity) {
        return new OrderItem(
                ProductId.of(entity.getProductId()),
                entity.getQuantity(),
                Money.of(entity.getPrice(), Currency.getInstance(entity.getOrder().getCurrency()))
        );
    }
}
