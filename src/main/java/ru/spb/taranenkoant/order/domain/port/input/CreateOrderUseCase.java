package ru.spb.taranenkoant.order.domain.port.input;


import ru.spb.taranenkoant.order.application.dto.CreateOrderCommand;
import ru.spb.taranenkoant.order.domain.model.OrderId;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public interface CreateOrderUseCase {
    OrderId createOrder(CreateOrderCommand command);
}
