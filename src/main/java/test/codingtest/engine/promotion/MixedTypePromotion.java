package test.codingtest.engine.promotion;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import test.codingtest.engine.model.CheckoutItem;
import test.codingtest.engine.model.Item;

public class MixedTypePromotion implements Promotion {

	private final static Logger LOGGER = Logger.getLogger(MixedTypePromotion.class);
	
	private List<Item> 	items;
	private int			quantity;
	private Double		promotionalPrice;

	public MixedTypePromotion(List<Item> items, Double promotionalPrice) {
		super();
		this.items = items;
		this.promotionalPrice = promotionalPrice;
		quantity = 1;
	}

	public List<CheckoutItem> applyPromotion(List<CheckoutItem> itemDetails) {
		
		if (itemDetails.isEmpty() || this.items == null || this.items.isEmpty() || this.promotionalPrice == 0){
			return Collections.emptyList();
		}
		
		LOGGER.debug("Promotion items " + items.size() + " promotionalPrice " + promotionalPrice);
		List<CheckoutItem> promotionToAppliedItemDetails = new ArrayList<CheckoutItem>();
		

		
		return promotionToAppliedItemDetails;
	}
	
}
