package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;
import com.thoughtworks.pos.domain.Item;

import java.util.List;

public final class PosMachine {
    private final List<Item> allItems;
    private final PromotionManager promotionManager;
    private DiscountPromotion discountPromotion;

    public PosMachine(final List<Item> allItems, PromotionManager promotionManager) {
        this.allItems = allItems;
        this.promotionManager = promotionManager;
    }

    public double calculate(final List<CartItem> cartItems) {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += calculateSubtotal(cartItem);
        }
        return total;
    }

    private double calculateSubtotal(final CartItem cartItem) {
        double subtotalAfterSecondHalfPrice;
        String barcode = cartItem.getBarcode();
        double originPrice = queryItemPrice(barcode);
        DiscountPromotion availablePromotion = promotionManager.getAvailablePromotion(barcode);
        double subtotalAfterDiscount = availablePromotion != null ?
                availablePromotion.apply(cartItem, originPrice) :
                cartItem.getQuantity() * originPrice;
        int a = cartItem.getQuantity();
        double originPriceAfterDiscount = subtotalAfterDiscount/cartItem.getQuantity();

        discountPromotion = new DiscountPromotion();

        boolean isSecondHalf = promotionManager.isSecondHalfPrice(barcode);
        if(subtotalAfterDiscount == cartItem.getQuantity() * originPrice){
            subtotalAfterSecondHalfPrice = isSecondHalf == true ?
                    discountPromotion.secondHalfPrice(cartItem, originPrice) : cartItem.getQuantity() * originPrice;
        }else {
            subtotalAfterSecondHalfPrice = isSecondHalf == true ?
                    availablePromotion.secondHalfPrice(cartItem, originPriceAfterDiscount) : subtotalAfterDiscount;
        }
        return subtotalAfterSecondHalfPrice;
    }

    private double queryItemPrice(final String barcode) {
        for (Item item : allItems) {
            if (item.getBarcode().equals(barcode)) {
                return item.getPrice();
            }
        }

        throw new IllegalArgumentException("unknown item");
    }
}
