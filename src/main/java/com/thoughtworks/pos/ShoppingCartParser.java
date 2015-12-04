package com.thoughtworks.pos;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class ShoppingCartParser {
    private static final Pattern PATTERN = compile("^(\\w+)-(\\d+)$");

    public List<CartItem> parse(List<String> input) {
        List<CartItem> list = new ArrayList<>();
        for (String line : input) {
            if (!PATTERN.matcher(line).matches()) {
                throw new IllegalArgumentException("invalid input format");
            }
            String[] splitLine = line.split("-");
            String barcode = splitLine[0];
            int quantity = Integer.parseInt(splitLine[1]);
            list.add(new CartItem(barcode, quantity));
        }
        return list;
    }

}
