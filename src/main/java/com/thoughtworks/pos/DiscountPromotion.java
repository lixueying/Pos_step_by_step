package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;

public class DiscountPromotion {
    private double discountRatio;

    public DiscountPromotion(){
        super();
    }

    public DiscountPromotion(int discount) {
        this.discountRatio = discount / 100d;
    }

    public double apply(CartItem cartItem, double price) {
        return cartItem.getQuantity() * price * this.discountRatio ;
    }

    public double secondHalfPrice(CartItem cartItem, double price){
        int notHalfPriceQuantity;
        int halfPriceQuantity = cartItem.getQuantity() / 2;
        if(cartItem.getQuantity() != 2 && cartItem.getQuantity() != 0){
            notHalfPriceQuantity = halfPriceQuantity + 1;
        }else if(cartItem.getQuantity() != 0){
            notHalfPriceQuantity = 1;
        }else {
            notHalfPriceQuantity = 0;
            halfPriceQuantity = 0;
        }

        double secondHalfPriceTotal =  halfPriceQuantity * price * 0.5;
        return secondHalfPriceTotal + notHalfPriceQuantity * price;
    }
}
