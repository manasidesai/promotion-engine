package test.codingtest.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import test.codingtest.engine.model.CheckoutItem;
import test.codingtest.engine.model.Item;
import test.codingtest.engine.promotion.Promotion;

/**
 * PromotionEngine class, calculates the bill total.
 * This class receives list of promotions from the configuration class promotionConfig 
 * For each promotion type it calls the applyPromotion() method and receives a list of checkout items with promotion/non promotional price applied 
 * It calculates total of all promotional and non promotional prices and returns.
 *
 */
public class PromotionEngine {
	
	private final static Logger LOGGER = Logger.getLogger(PromotionEngine.class);
	
	private PromotionConfig promotionConfig;

	@Autowired
	PromotionEngine(PromotionConfig promotionConfig) {
		this.promotionConfig = promotionConfig;
	}
	
	/**
	 * This method receives list of cart items.
	 * It calculates count of the each cart item and saves it in the map.
	 * Gets a list of promotions from the configuration class promotionConfig 
	 * For each promotion type it calls the applyPromotion() method and receives a list of checkout items with promotion/non promotional prices applied 
	 * It calculates total of all promotional and non promotional prices and returns the bill total.
	 * @param cartItems
	 * @return
	 */
	public Double getCartTotal(List<Item> cartItems){
		double promoTotalPrice = 0.00;
		double nonpromoTotalPrice = 0.00;
		double billTotal = 0.00;

		Map<Item, CheckoutItem> cartItemsMap = new HashMap<Item, CheckoutItem>();
		if (cartItems == null || cartItems.isEmpty()){
			return billTotal;
		}
		
		//Calculates count of the each cart item and save it in the map.
		for (Item item : cartItems) {
			if (cartItemsMap.containsKey(item)){
				CheckoutItem itemDetails = cartItemsMap.get(item);
				Integer count = itemDetails.getCount();
				count++;
				itemDetails.setCount(count);
			}
			else{
				cartItemsMap.put(item, new CheckoutItem(item, 1));
			}
		}
		
		List<Promotion> newPromotionList = promotionConfig.getPromotionList();
		
		//For each promotion type call the applyPromotion() method and receives a list of checkout items with promotion/non promotional prices applied  
		List<CheckoutItem> checkoutItemsAfterPromotionApplied = new ArrayList<CheckoutItem>();
		for (Promotion promotion : newPromotionList) {
			List<CheckoutItem> checkoutItemsAfterPromo = promotion.applyPromotion(new ArrayList<CheckoutItem>(cartItemsMap.values()));
			if (!checkoutItemsAfterPromo.isEmpty()){
				checkoutItemsAfterPromotionApplied.addAll(checkoutItemsAfterPromo);
			}
		}
		
		LOGGER.debug("Found cart promotions " + checkoutItemsAfterPromotionApplied.size());
		//From checkoutItemsAfterPromotionApplied, get promotionalTotal and nonpromo total
		for (Entry<Item, CheckoutItem> entry : cartItemsMap.entrySet()) {
			
			CheckoutItem itemDetails = entry.getValue();
			Optional<CheckoutItem> promoItem = checkoutItemsAfterPromotionApplied.stream()
							.filter(c -> c.getItem().getId() == itemDetails.getItem().getId())
							.findFirst();

			if (promoItem.isPresent()){
				CheckoutItem checkoutItem = promoItem.get();
				promoTotalPrice += checkoutItem.getPromotionalTotal();
				nonpromoTotalPrice += checkoutItem.getNonpromotionalTotal();
			}
			else{
				nonpromoTotalPrice += itemDetails.getItem().getPrice() * itemDetails.getCount();
			}
		}
		
		billTotal = promoTotalPrice + nonpromoTotalPrice;
		return billTotal;
	}

}
