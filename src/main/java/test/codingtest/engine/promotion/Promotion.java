package test.codingtest.engine.promotion;

import java.util.List;

import test.codingtest.engine.model.CheckoutItem;

public interface Promotion {
	
	public List<CheckoutItem> applyPromotion(List<CheckoutItem> itemDetails);
	
}
