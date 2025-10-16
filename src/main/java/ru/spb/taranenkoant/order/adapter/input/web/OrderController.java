package ru.spb.taranenkoant.order.adapter.input.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.taranenkoant.order.application.dto.OrderItemData;
import ru.spb.taranenkoant.order.application.dto.CreateOrderCommand;
import ru.spb.taranenkoant.order.domain.model.*;
import ru.spb.taranenkoant.order.domain.port.input.CreateOrderUseCase;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderId> createOrder(@RequestBody CreateOrderRequest request) {
        CreateOrderCommand command = new CreateOrderCommand(
                CustomerId.of(request.getCustomerId()),
                mapToOrderItemDataList(request.getItems())
        );

        OrderId orderId = createOrderUseCase.createOrder(command);
        return ResponseEntity.ok(orderId);
    }

    private List<OrderItemData> mapToOrderItemDataList(List<OrderItemRequest> itemRequests) {
        return itemRequests.stream()
                .map(item -> {
                    OrderItemData itemData = new OrderItemData();
                    itemData.setProductId(ProductId.of(item.getProductId()));
                    itemData.setQuantity(item.getQuantity());

                    Money price = Money.of(item.getPrice(), Currency.getInstance(item.getCurrency()));
                    itemData.setPrice(price);

                    return itemData;
                })
                .collect(Collectors.toList());
    }
}
