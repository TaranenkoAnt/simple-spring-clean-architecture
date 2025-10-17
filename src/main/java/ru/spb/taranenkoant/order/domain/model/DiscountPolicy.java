package ru.spb.taranenkoant.order.domain.model;


/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 16.10.2025
 */
public interface DiscountPolicy {
    Money calculateDiscount(Order order);
    boolean isApplicable(Order order);
}
