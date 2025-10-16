package ru.spb.taranenkoant.order.domain.port.output;


import ru.spb.taranenkoant.order.domain.model.Order;
import ru.spb.taranenkoant.order.domain.model.OrderId;

import java.util.Optional;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public interface SaveOrderPort {

    OrderId save(Order order);
    Optional<Order> findById(OrderId orderId);
}
