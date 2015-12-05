package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;
import com.thoughtworks.pos.domain.Item;

import java.util.List;

public final class PosMachine {
    private final List<Item> allItems;
    private final PromotionManager promotionManager;

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
        String barcode = cartItem.getBarcode();
        double originPrice = queryItemPrice(barcode);
        DiscountPromotion availablePromotion = promotionManager.getAvailablePromotion(barcode);
        double subtotal = availablePromotion != null ?
                availablePromotion.apply(cartItem, originPrice) :
                cartItem.getQuantity() * originPrice;
        return subtotal;
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
