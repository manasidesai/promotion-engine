package test.codingtest.engine;

import java.util.ArrayList;
import java.util.List;

import test.codingtest.engine.model.Item;
import test.codingtest.engine.promotion.MixedTypePromotion;
import test.codingtest.engine.promotion.Promotion;
import test.codingtest.engine.promotion.QuantityBasedSinglePromotion;

/**
 * A configuration class to initialise list of promotions. 
 * 
 * As per scenarios provided in the coding test, 3 different promotion classes are initialised.
 * As per scenarios provided in the coding test, only 4 cart items are initialised
 *
 */
public class PromotionConfig {
	
	private final Item itemA = new Item ("A", 50.00);
	private final Item itemB = new Item ("B", 30.00);
	private final Item itemC = new Item ("C", 20.00);
	private final Item itemD = new Item ("D", 15.00);
	
	private List<Promotion> promotionList = new ArrayList<Promotion>();
	
	public PromotionConfig() {
		initialisePromotionList();
	}

	private void initialisePromotionList(){
		
		//Quantity based single promotions
		QuantityBasedSinglePromotion quantityBasedSinglePromotionA = new QuantityBasedSinglePromotion(itemA, 3, 130.00);
		QuantityBasedSinglePromotion quantityBasedSinglePromotionB = new QuantityBasedSinglePromotion(itemB, 2, 45.00);
		promotionList.add(quantityBasedSinglePromotionA);
		promotionList.add(quantityBasedSinglePromotionB);
		
		//Mixed promotions
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemC);
		itemList.add(itemD);
		MixedTypePromotion mixedTypePromotion = new MixedTypePromotion(itemList, 30.00);
		promotionList.add(mixedTypePromotion);
		
	}
	
	public List<Promotion> getPromotionList(){		
		return promotionList;
	}

}
