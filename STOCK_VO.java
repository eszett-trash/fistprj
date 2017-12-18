package shoesTailor;

public class STOCK_VO {
	private int stock_vo_id;
	private String name;
	private int amount;
	private int price;
	private String color;
	private int kind;
	
	public int getStock_vo_id() {
		return stock_vo_id;
	}
	public void setStock_vo_id(int stock_vo_id) {
		this.stock_vo_id = stock_vo_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
}
