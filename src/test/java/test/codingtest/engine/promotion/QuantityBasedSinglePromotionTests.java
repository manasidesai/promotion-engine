package test.codingtest.engine.promotion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import test.codingtest.engine.model.CheckoutItem;
import test.codingtest.engine.model.Item;


@SpringBootTest
public class QuantityBasedSinglePromotionTests {

	private final Item itemA = new Item ("A", 50.00);
	private final Item itemB = new Item ("B", 30.00);
	
	@Test
	void testapplyPromotion_CartContainsNoItem() {
		
		// Given
		QuantityBasedSinglePromotion quantityBasedSinglePromotionA = new QuantityBasedSinglePromotion(itemA, 3, 130.00);
		List<CheckoutItem> itemDetails = new ArrayList<CheckoutItem> ();
		
		// When
		List<CheckoutItem> applyPromotion = quantityBasedSinglePromotionA.applyPromotion(itemDetails);		

		// Then
		assertEquals(Collections.EMPTY_LIST, applyPromotion);		

	}
	
	@Test
	void testapplyPromotion_CartContainsOnlyPromotionalItems() {
		
		// Given
		QuantityBasedSinglePromotion quantityBasedSinglePromotionA = new QuantityBasedSinglePromotion(itemA, 3, 130.00);
		List<CheckoutItem> itemDetailsA = new ArrayList<CheckoutItem> ();
		itemDetailsA.add(new CheckoutItem(itemA, 3));
		
		// When
		List<CheckoutItem> applyPromotion = quantityBasedSinglePromotionA.applyPromotion(itemDetailsA);		
		
		// Then
		assertEquals(130.00, applyPromotion.iterator().next().getPromotionalTotal());		
	}
	
	@Test
	void testapplyPromotion_CartContainsMixOfPromotionalAnNonPromotionalItems() {
		
		// Given
		QuantityBasedSinglePromotion quantityBasedSinglePromotionB = new QuantityBasedSinglePromotion(itemB, 2, 45.00);
		List<CheckoutItem> itemDetailsB = new ArrayList<CheckoutItem> ();
		itemDetailsB.add(new CheckoutItem(itemB, 5));
		
		// When
		List<CheckoutItem> applyPromotionB = quantityBasedSinglePromotionB.applyPromotion(itemDetailsB);	
		
		// Then
		assertEquals(90.00, applyPromotionB.iterator().next().getPromotionalTotal());
		assertEquals(30.00, applyPromotionB.iterator().next().getNonpromotionalTotal());
	}
}
