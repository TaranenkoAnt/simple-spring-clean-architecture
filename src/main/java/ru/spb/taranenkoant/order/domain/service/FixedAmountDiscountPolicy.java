package ru.spb.taranenkoant.order.domain.service;


import ru.spb.taranenkoant.order.domain.model.DiscountPolicy;
import ru.spb.taranenkoant.order.domain.model.Money;
import ru.spb.taranenkoant.order.domain.model.Order;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 16.10.2025
 */
public class FixedAmountDiscountPolicy implements DiscountPolicy {
    private final Money discountAmount;
    private final Money minimumOrderAmount;

    public FixedAmountDiscountPolicy(Money discountAmount, Money minimumOrderAmount) {
        this.discountAmount = discountAmount;
        this.minimumOrderAmount = minimumOrderAmount;
    }

    @Override
    public Money calculateDiscount(Order order) {
        return isApplicable(order) ? discountAmount : Money.ZERO;
    }

    @Override
    public boolean isApplicable(Order order) {
        return order.getTotalPrice().getAmount()
                .compareTo(minimumOrderAmount.getAmount()) >= 0;
    }
}
