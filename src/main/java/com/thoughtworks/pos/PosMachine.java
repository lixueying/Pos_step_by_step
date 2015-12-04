package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;
import com.thoughtworks.pos.domain.Item;

import java.util.List;

public final class PosMachine {
    private final List<Item> allItems;

    public PosMachine(final List<Item> allItems) {
        this.allItems = allItems;
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
        return cartItem.getQuantity() * originPrice;
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
