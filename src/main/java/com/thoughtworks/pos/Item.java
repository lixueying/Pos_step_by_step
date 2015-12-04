package com.thoughtworks.pos;

public final class Item {
    private final String barcode;
    private final double price;

    public Item(String barcode, double price) {
        this.barcode = barcode;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }
}
