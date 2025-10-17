package ru.spb.taranenkoant.order.application.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.taranenkoant.order.application.dto.CreateOrderCommand;
import ru.spb.taranenkoant.order.application.exception.ValidationException;
import ru.spb.taranenkoant.order.domain.model.*;
import ru.spb.taranenkoant.order.domain.port.input.CreateOrderUseCase;
import ru.spb.taranenkoant.order.domain.port.output.SaveOrderPort;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
@Service
@Transactional
public class CreateOrderService implements CreateOrderUseCase {

    private final SaveOrderPort saveOrderPort;
    private final DiscountPolicy discountPolicy;

    public CreateOrderService(SaveOrderPort saveOrderPort, DiscountPolicy discountPolicy) {
        this.saveOrderPort = saveOrderPort;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public OrderId createOrder(CreateOrderCommand command) {
        validateCommand(command);

        List<OrderItem> orderItems = command.getItems().stream()
                .map(item -> new OrderItem(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());

        Order order = Order.create(command.getCustomerId(), orderItems);
        if (discountPolicy.isApplicable(order)) {
            Money discount = discountPolicy.calculateDiscount(order);
            order.applyDiscount(discount);
        }
        return saveOrderPort.save(order);
    }

    private void validateCommand(CreateOrderCommand command) {
        if (command.getCustomerId() == null) {
            throw new ValidationException("id заказчика не может быть null");
        }
        if (command.getItems() == null || command.getItems().isEmpty()) {
            throw new ValidationException("В заказе должен быть хотя бы один товар");
        }
    }
}
