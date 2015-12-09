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

    public boolean isSecondHalfPrice(String barxode) {
        for(int i = 0; i < ShopData.SECOND_HALF_PRICE_PROMOTION.size(); i++){
            if(barxode.equals(ShopData.SECOND_HALF_PRICE_PROMOTION.get(i))){
                return true;
            }
        }
        return false;
    }
}
