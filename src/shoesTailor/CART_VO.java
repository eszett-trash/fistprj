package shoesTailor;

public class CART_VO {
	private int cart_vo_id; //카트 구별 id
	private String name; // 상품명
	private int price; // 상품 가격
	private int orderNumber; // 주문번호
	private int stock_vo_id; //재고 구별 id
	
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
	public int getStock_vo_id() {
		return stock_vo_id;
	}
	public void setStock_vo_id(int stock_vo_id) {
		this.stock_vo_id = stock_vo_id;
	}
}
