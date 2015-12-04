package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;

public class DiscountPromotion {
    private final double discountRatio;

    public DiscountPromotion(int discount) {
        this.discountRatio = discount / 100d;
    }

    public double apply(CartItem cartItem, double price) {
        return cartItem.getQuantity() * price * this.discountRatio ;
    }
}
