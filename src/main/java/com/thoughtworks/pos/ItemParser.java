package com.thoughtworks.pos;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class ItemParser {
    private static final Pattern PATTERN = compile("^(\\w+):(\\d+)$");

    public List<Item> parse(List<String> input) {
        List<Item> list = new ArrayList<>();
        for (String line : input) {
            if (!PATTERN.matcher(line).matches()) {
                throw new IllegalArgumentException("invalid input format");
            }
            String barcode = line.split(":")[0];
            double price = Double.parseDouble(line.split(":")[1]);
            list.add(new Item(barcode, price));
        }
        return list;
    }

}
