package test.codingtest.engine.promotion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import test.codingtest.engine.model.CheckoutItem;
import test.codingtest.engine.model.Item;


@SpringBootTest
public class MixedTypePromotionTests {
	
	private MixedTypePromotion mixedTypePromotion;
	
	private final Item itemC = new Item ("C", 20.00);
	private final Item itemD = new Item ("D", 15.00);
	
	@BeforeEach
	public void setup() {
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemC);
		itemList.add(itemD);
		
		mixedTypePromotion = new MixedTypePromotion(itemList, 30.00);
	}
	
	@Test
	void testapplyPromotion_CartContainsNoItem() {
		// Given
		List<CheckoutItem> cartItemDetails = new ArrayList<CheckoutItem> ();

		// When
		List<CheckoutItem> appliedPromoItems = mixedTypePromotion.applyPromotion(cartItemDetails);	
		
		// Verify
		assertEquals(Collections.EMPTY_LIST, appliedPromoItems);
	}
	
	@Test
	void testapplyPromotion_CartContainsOnlyPromotionalItems() {
		// Given
		List<CheckoutItem> cartItemDetails = new ArrayList<CheckoutItem> ();
		cartItemDetails.add(new CheckoutItem(itemC, 1));
		cartItemDetails.add(new CheckoutItem(itemD, 1));

		// When
		List<CheckoutItem> appliedPromoItems = mixedTypePromotion.applyPromotion(cartItemDetails);	
		
		// Verify
		assertEquals(30.00, appliedPromoItems.iterator().next().getPromotionalTotal());
		
	}
	

}
