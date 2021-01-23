package test.codingtest.engine;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import test.codingtest.engine.model.Item;

/**
 * PromotionEngine class, calculates the bill total.
 * This class receives list of promotions from the configuration class promotionConfig 
 * For each promotion type it calls the applyPromotion() method and receives list of promotion price applied checkout Items
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
	 * This method receives list of promotions from the configuration class promotionConfig 
	 * For each promotion type it calls the applyPromotion() method and receives list of promotion price applied checkout Items
	 * It calculates total of all promotional and non promotional prices and returns the bill total.
	 * @param cartItems
	 * @return
	 */
	public Double getCartTotal(List<Item> cartItems){
		return 0.00;
	}

}
