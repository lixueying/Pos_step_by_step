package com.thoughtworks.pos;

import java.util.Map;

public class PromotionManager {
    private final Map<String, DiscountPromotion> promotionMap;

    public PromotionManager(Map<String, DiscountPromotion> promotionMap) {
        this.promotionMap = promotionMap;
    }

    public DiscountPromotion getAvailablePromotion(String barcode) {
        return this.promotionMap.get(barcode);
    }
}
