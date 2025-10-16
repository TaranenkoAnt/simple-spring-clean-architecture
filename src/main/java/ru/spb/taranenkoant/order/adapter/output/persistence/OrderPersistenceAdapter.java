package ru.spb.taranenkoant.order.adapter.output.persistence;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.taranenkoant.order.adapter.output.persistence.entity.OrderEntity;
import ru.spb.taranenkoant.order.adapter.output.persistence.mapper.OrderEntityMapper;
import ru.spb.taranenkoant.order.domain.model.Order;
import ru.spb.taranenkoant.order.domain.model.OrderId;
import ru.spb.taranenkoant.order.domain.port.output.SaveOrderPort;

import java.util.Optional;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
@Repository
@Transactional
public class OrderPersistenceAdapter implements SaveOrderPort {

    private final JpaOrderRepository orderRepository;
    private final OrderEntityMapper mapper;

    public OrderPersistenceAdapter(JpaOrderRepository orderRepository, OrderEntityMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderId save(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        OrderEntity savedEntity = orderRepository.save(entity);
        Order savedOrder = mapper.toDomain(savedEntity);
        return savedOrder.getId();
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderRepository.findById(orderId.getValue())
                .map(mapper::toDomain);
    }
}
