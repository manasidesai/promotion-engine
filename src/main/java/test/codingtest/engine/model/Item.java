package test.codingtest.engine.model;

public class Item {

	private String id;
	private Double price;
	
	public Item(String id, Double price) {
		super();
		this.id = id;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
