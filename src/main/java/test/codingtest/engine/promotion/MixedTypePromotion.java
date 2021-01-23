package test.codingtest.engine.promotion;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import test.codingtest.engine.model.CheckoutItem;
import test.codingtest.engine.model.Item;

/**
 * This is a mixed promotion class, implementing the Promotion interface
 * It finds matching cart item from the cart and applies the promotional price for given quantity. 
 * For remaining quantity, the non promotional price will be applied.
 *
 */
public class MixedTypePromotion implements Promotion {

	private final static Logger LOGGER = Logger.getLogger(MixedTypePromotion.class);
	
	private List<Item> 	items;
	private int			quantity;
	private Double		promotionalPrice;

	public MixedTypePromotion(List<Item> items, Double promotionalPrice) {
		super();
		this.items = items;
		this.promotionalPrice = promotionalPrice;
		quantity = 1;	//As per assumption quantity is always set to 1
	}

	
	/**
	 * It finds matching cart item from the cart and applies the promotional price for given quantity. 
	 * For remaining quantity, the non promotional price will be applied.
	 * Updates promotional and non promotional totals
	 * Returns List<CheckoutItem> with promotional and non promotional totals set
	 * 
	 * Assumption - Item Quantity is always set to 1
	 */
	public List<CheckoutItem> applyPromotion(List<CheckoutItem> itemDetails) {
		
		if (itemDetails.isEmpty() || this.items == null || this.items.isEmpty() || this.promotionalPrice == 0){
			return Collections.emptyList();
		}
		
		LOGGER.debug("Promotion items " + items.size() + " promotionalPrice " + promotionalPrice);
		List<CheckoutItem> promotionToApplyItemDetails = new ArrayList<CheckoutItem>();
		List<CheckoutItem> promotionToAppliedItemDetails = new ArrayList<CheckoutItem>();
		
		boolean allItemsExists = true;
		
		//Find if all promotional items exists in the cart
		for (Item promoItem : items) {
			
			Optional<CheckoutItem> promotionalItemList = itemDetails.stream()
																	.filter(d -> d.getItem().getId() == promoItem.getId() )
																	.findFirst();
			if (!promotionalItemList.isPresent()){
				allItemsExists = false;
				break;
			}
			else{
				promotionToApplyItemDetails.add(promotionalItemList.get());
			}
		}
		
		//We will apply promotional price only when all items exits in the cart
		if (allItemsExists){
			
			boolean promotionalPriceApplied = false;
			
			for (CheckoutItem checkoutItem : promotionToApplyItemDetails) {
				if (!promotionalPriceApplied){
					checkoutItem.setPromotionalTotal(promotionalPrice);
					promotionalPriceApplied = true;
				}
				//Remaining items will be charged at non promotional rate
				if (checkoutItem.getCount() > 1){
					checkoutItem.setNonpromotionalTotal((checkoutItem.getCount() - 1) * checkoutItem.getItem().getPrice());
				}
				promotionToAppliedItemDetails.add(checkoutItem);
			}
			
		}
		
		return promotionToAppliedItemDetails;
	}
	
}
