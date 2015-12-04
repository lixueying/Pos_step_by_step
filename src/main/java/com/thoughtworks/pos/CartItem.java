package com.thoughtworks.pos;

public final class CartItem {
    private final String barcode;
    private final Integer quantity;

    public CartItem(String barcode, Integer quantity) {
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
