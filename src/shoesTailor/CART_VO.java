package shoesTailor;

public class CART_VO {
	private int cart_vo_id;
	private String name;
	private int price;
	private int orderNumber;
	
	public int getCart_vo_id() {
		return cart_vo_id;
	}
	public void setCart_vo_id(int cart_vo_id) {
		this.cart_vo_id = cart_vo_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
}
