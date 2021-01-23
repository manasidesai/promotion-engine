package test.codingtest.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import test.codingtest.engine.model.Item;
import test.codingtest.engine.promotion.MixedTypePromotion;
import test.codingtest.engine.promotion.Promotion;
import test.codingtest.engine.promotion.QuantityBasedSinglePromotion;

@SpringBootTest
public class PromotionEngineTests {

	private final static Logger LOGGER = Logger.getLogger(PromotionEngineTests.class);
	
	@InjectMocks
	PromotionEngine promotionEngine;
	
	@MockBean
	PromotionConfig promotionConfig;
	
	private final Item itemA = new Item ("A", 50.00);
	private final Item itemB = new Item ("B", 30.00);
	private final Item itemC = new Item ("C", 20.00);
	private final Item itemD = new Item ("D", 15.00);
	
	
	@BeforeEach
	public void setup(){
		promotionEngine = new PromotionEngine(promotionConfig);
	}
	
	@Test
	void test_getCartTotal_withoutdata() {
		
		assertEquals(0.00, promotionEngine.getCartTotal(Collections.EMPTY_LIST));	
		
	}

	@Test
	void test_getCartTotal_withdata_nopromo_scenario_A() {
		
		// Given
		List<Item> cartItems = new ArrayList<Item>();
		cartItems.add(itemA);
		cartItems.add(itemB);
		cartItems.add(itemC);
		Mockito.when(promotionConfig.getPromotionList()).thenReturn(getPromotionList());

		// Then
		assertEquals(100.00, promotionEngine.getCartTotal(cartItems));		

		//verify
		Mockito.verify(promotionConfig, Mockito.times(1)).getPromotionList();
		
	}
	
	private List<Promotion> getPromotionList(){
		
		List<Promotion> promotionList = new ArrayList<Promotion>();
		
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
		
		return promotionList;
		
	}
}
