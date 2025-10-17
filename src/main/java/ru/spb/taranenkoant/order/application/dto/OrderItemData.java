package ru.spb.taranenkoant.order.application.dto;


import ru.spb.taranenkoant.order.domain.model.Money;
import ru.spb.taranenkoant.order.domain.model.ProductId;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 15.10.2025
 */
public class OrderItemData {
    private ProductId productId;
    private int quantity;
    private Money price;

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
