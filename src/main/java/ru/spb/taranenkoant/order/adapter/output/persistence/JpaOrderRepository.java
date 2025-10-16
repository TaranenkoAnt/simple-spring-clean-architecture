package ru.spb.taranenkoant.order.adapter.output.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.spb.taranenkoant.order.adapter.output.persistence.entity.OrderEntity;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public interface JpaOrderRepository extends JpaRepository<OrderEntity, String> {
}
