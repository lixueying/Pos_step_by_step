package com.thoughtworks.pos;


import com.thoughtworks.pos.domain.CartItem;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiscountPromotionTest {
    private DiscountPromotion discountPromotion;

    @Test
    public void should_return_0_when_quantity_0() {
        discountPromotion = new DiscountPromotion(75);
        double subtotal = discountPromotion.apply(new CartItem("ITEM000001", 0), 40);

        assertThat(subtotal, is(0d));
    }

    @Test
    public void should_return_0_when_price_0() {
        discountPromotion = new DiscountPromotion(75);
        double subtotal = discountPromotion.apply(new CartItem("ITEM000001", 5), 0);

        assertThat(subtotal, is(0d));
    }

    @Test
    public void should_return_subtotal_after_discount() {
        discountPromotion = new DiscountPromotion(75);
        double subtotal = discountPromotion.apply(new CartItem("ITEM000001", 1), 40);

        assertThat(subtotal, is(30d));
    }
}