package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;
import com.thoughtworks.pos.domain.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PosMachineTest {
    private PosMachine posMachine;
    private PromotionManager promotionManager;

    @Before
    public void setUp() {
        List<Item> allItems = Arrays.asList(new Item("ITEM000001", 40),
                new Item("ITEM000003", 50), new Item("ITEM000005", 60));
        promotionManager = mock(PromotionManager.class);
        posMachine = new PosMachine(allItems, promotionManager);

    }

    @Test
    public void should_return_0_when_shopping_cart_empty() {
        List<CartItem> emptyCart = Arrays.asList();
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(0d));
    }

    @Test
    public void should_calculate_total_when_given_1_item() {
        List<CartItem> itemCart = Arrays.asList(new CartItem("ITEM000003", 1));
        double total = posMachine.calculate(itemCart);
        when(promotionManager.getAvailablePromotion("ITEM000003")).thenReturn(null);

        assertThat(total, is(50d));
    }

    @Test
    public void should_calculate_total_when_given_multiple_items() {
        List<CartItem> itemCart = Arrays.asList(new CartItem("ITEM000001", 3));
        double total = posMachine.calculate(itemCart);
        when(promotionManager.getAvailablePromotion("ITEM000003")).thenReturn(null);

        assertThat(total, is(120d));
    }

    @Test
    public void should_calculate_total_when_given_multiple_types_item() {
        List<CartItem> itemCart = Arrays.asList(new CartItem("ITEM000001", 3), new CartItem("ITEM000003", 2));
        double total = posMachine.calculate(itemCart);
        when(promotionManager.getAvailablePromotion("ITEM000003")).thenReturn(null);

        assertThat(total, is(220d));
    }

    @Test
    public void should_calculate_total_will_apply_discount() {
        List<CartItem> itemCart = Arrays.asList(new CartItem("ITEM000001", 3), new CartItem("ITEM000003", 2));
        when(promotionManager.getAvailablePromotion("ITEM000003")).thenReturn(new DiscountPromotion(75));
        when(promotionManager.getAvailablePromotion("ITEM000001")).thenReturn(new DiscountPromotion(75));

        double total = posMachine.calculate(itemCart);

        assertThat(total, is(165d));
    }


}