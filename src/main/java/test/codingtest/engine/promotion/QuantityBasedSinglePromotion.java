package test.codingtest.engine.promotion;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import test.codingtest.engine.model.CheckoutItem;
import test.codingtest.engine.model.Item;

/**
 * This is a single promotion class, implementing the Promotion interface
 * It finds matching cart item from the cart and applies the promotional price for given quantity. 
 * For remaining quantity, the non promotional price will be applied.
 * 
 *
 */
public class QuantityBasedSinglePromotion implements Promotion {

	private final static Logger LOGGER = Logger.getLogger(QuantityBasedSinglePromotion.class);
	
	private Item 	item;
	private int		quantity;
	private Double	promotionalPrice;

	public QuantityBasedSinglePromotion(Item item, int quantity, Double promotionalPrice) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.promotionalPrice = promotionalPrice;
	}
	
	/**
	 * Applies promotions on cart items which matches with the QuantityBasedSinglePromotion.item 
	 * It finds matching cart item from the cart and applies the promotional price for given quantity. 
	 * For remaining quantity, the non promotional price will be applied.
	 * Updates promotional and non promotional totals
	 * Returns List<CheckoutItem> with promotional and non promotional totals set
	 */
	@Override
	public List<CheckoutItem> applyPromotion(List<CheckoutItem> itemDetails) {
		
		if (itemDetails.isEmpty() || this.item == null || this.quantity == 0 || this.promotionalPrice == 0){
			return Collections.emptyList();
		}
		
		LOGGER.debug("Promotional item " + item.getId() + " Quantity " + quantity + " PromotionalPrice " + promotionalPrice);
		
		//check if promotional items exists in the cart		
		Optional<CheckoutItem> promotionalItemList = itemDetails.stream().filter(d -> d.getItem().getId() == item.getId() ).findFirst();
		LOGGER.debug("Promotional item found in the cart" + promotionalItemList.isPresent());
		
		if (!promotionalItemList.isPresent()){
			return Collections.emptyList();
		}
		
		CheckoutItem checkoutItem = promotionalItemList.get();
		int cartItemCount = checkoutItem.getCount();
		Double promoprice = promotionalPrice;

		if (cartItemCount >= quantity){
			if (cartItemCount == quantity){
				checkoutItem.setPromotionalTotal(promoprice);
			}
			else{
				int promoCount = cartItemCount / quantity;
				int nonPromoCount = cartItemCount % quantity;
				
				checkoutItem.setPromotionalTotal( promoCount * promoprice );
				checkoutItem.setNonpromotionalTotal( item.getPrice() * nonPromoCount ) ;
			}
		}
		else{
			//add to non promo count
			checkoutItem.setNonpromotionalTotal( item.getPrice() * cartItemCount);
		}				
	
		List<CheckoutItem> promotionAppliedItemDetails = new ArrayList<CheckoutItem>();
		promotionAppliedItemDetails.add(checkoutItem);
		
		return promotionAppliedItemDetails;
	}


}
