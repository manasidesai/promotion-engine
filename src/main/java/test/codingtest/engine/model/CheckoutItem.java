package test.codingtest.engine.model;

/**
 * CheckoutItem is used to save promotional and non promotional prices of a item along with it's order quantity 
 */
public class CheckoutItem {
	
	private Item item;
	private Integer count;
	private Double promotionalTotal;
	private Double nonpromotionalTotal;
	
	public CheckoutItem(Item item, Integer count) {
		super();
		this.item = item;
		this.count = count;
		promotionalTotal = 0.00;
		nonpromotionalTotal = 0.00;
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getPromotionalTotal() {
		return promotionalTotal;
	}

	public void setPromotionalTotal(Double promotionalTotal) {
		this.promotionalTotal = promotionalTotal;
	}

	public Double getNonpromotionalTotal() {
		return nonpromotionalTotal;
	}

	public void setNonpromotionalTotal(Double nonpromotionalTotal) {
		this.nonpromotionalTotal = nonpromotionalTotal;
	}
}
