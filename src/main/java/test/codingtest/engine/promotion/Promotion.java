package test.codingtest.engine.promotion;

import java.util.List;

import test.codingtest.engine.model.CheckoutItem;

public interface Promotion {
	
	/**
	 * Main interface for Promotion
	 * All promotion classes should implement this interface 
	 * It applies promotions on matching cart items from the itemDetails
	 * All promotion classes will apply promotional prices as per their behaviour

	 * In future additional promotional classes can be added extending base Promotion interface 
	 * @param itemDetails
	 * @return
	 */
	public List<CheckoutItem> applyPromotion(List<CheckoutItem> itemDetails);
	
}
