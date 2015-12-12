package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;

/**
 * Created by lixueying on 15/12/12.
 */
public interface Discount {
    double apply(CartItem cartItem, double price);
    double secondHalfPrice(CartItem cartItem, double price);
}
