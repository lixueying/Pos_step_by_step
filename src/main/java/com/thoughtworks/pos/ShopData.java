package com.thoughtworks.pos;

import com.thoughtworks.pos.strategy.*;

import java.util.*;

public final class ShopData {

    public static final List<String> ITEMS_DATA =
            Arrays.asList("ITEM000001:40", "ITEM000003:50", "ITEM000005:60");

    public static final List<String> SHOPPING_CART_DATA =
            Arrays.asList("ITEM000001-2", "ITEM000003-5", "ITEM000005-3");

    public static final Map<String, PromotionStrategy> ALL_PROMOTIONS = new HashMap<>();

    static {
        PromotionStrategy item1Promotion =
                new CompositeStrategy(Arrays.asList(new DiscountStrategy(75),new SecondHalfStrategy()));
        ALL_PROMOTIONS.put("ITEM000001",item1Promotion);

        PromotionStrategy item3Promotion =
                new CompositeStrategy(Arrays.asList(new SecondHalfStrategy(),new ReduceXUponYStrategy(100, 10)));
        ALL_PROMOTIONS.put("ITEM000003", item3Promotion);

        PromotionStrategy item5Promotion =
                new CompositeStrategy(Arrays.asList(new DiscountStrategy(90), new ReduceXUponYStrategy(300, 50)));
        ALL_PROMOTIONS.put("ITEM000005", item5Promotion);
    }
}
