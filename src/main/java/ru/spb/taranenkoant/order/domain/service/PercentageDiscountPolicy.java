package ru.spb.taranenkoant.order.domain.service;


import ru.spb.taranenkoant.order.domain.model.DiscountPolicy;
import ru.spb.taranenkoant.order.domain.model.Money;
import ru.spb.taranenkoant.order.domain.model.Order;

import java.math.BigDecimal;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 16.10.2025
 */
public class PercentageDiscountPolicy implements DiscountPolicy {

    private final double percentage;
    private final Money minimumOrderAmount;

    public PercentageDiscountPolicy(double percentage, Money minimumOrderAmount) {
        this.percentage = percentage;
        this.minimumOrderAmount = minimumOrderAmount;
    }

    @Override
    public Money calculateDiscount(Order order) {
        if (!isApplicable(order)) {
            return Money.ZERO;
        }

        Money total = order.getTotalPrice();
        BigDecimal discountAmount = total.getAmount()
                .multiply(BigDecimal.valueOf(percentage / 100));

        return Money.of(discountAmount, total.getCurrency());
    }

    @Override
    public boolean isApplicable(Order order) {
        return order.getTotalPrice().getAmount()
                .compareTo(minimumOrderAmount.getAmount()) >= 0;
    }
}
