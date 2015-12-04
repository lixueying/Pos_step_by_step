package com.thoughtworks.pos.parser;

import com.thoughtworks.pos.DiscountPromotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class DiscountParser {
    private static final Pattern PATTERN = compile("^(\\w+):(\\d+)$");

    public Map<String, DiscountPromotion> parse(List<String> input) {
        Map<String, DiscountPromotion> discountMap = new HashMap<>();
        for (String line : input) {
            validateInput(line);
            String[] splitLine = line.split(":");
            String barcode = splitLine[0];
            int discount = Integer.parseInt(splitLine[1]);
            discountMap.put(barcode, new DiscountPromotion(discount));
        }
        return discountMap;
    }

    private void validateInput(final String line) {
        if (!PATTERN.matcher(line).matches()) {
            throw new IllegalArgumentException("invalid input format");
        }
    }
}
