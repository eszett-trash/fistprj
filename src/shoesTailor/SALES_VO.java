package shoesTailor;

import java.util.Calendar;

public class SALES_VO {
	private String date;			 // 타임스탬프
	private String account;			 // ID
	private String deliveryAddress;	 // 주소
	private int price;				 // 가격
	private boolean isDelivery; 	 // 배송유무
	private int orderNumber; 		 // 구매고유번호(USER_VO의 purchase Goods와 연동)	 
	private int creditOrAccount;	 // 현금이냐 카드냐 (카드면 무슨 카드냐까지 해야하지 않나?)
	private String purchaseGoods; 	 // 구매물품
	
	public String getDate() {
		return date;
	}

	public void setDate() {
		Calendar c = Calendar.getInstance();
		this.date = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-"
				+ c.get(Calendar.DATE) + " " + c.get(Calendar.HOUR_OF_DAY)
				+ ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isDelivery() {
		return isDelivery;
	}

	public void setDelivery(boolean isDelivery) {
		this.isDelivery = isDelivery;
	}

	public int getCreditOfAccount() {
		return creditOrAccount;
	}

	public void setCreditOfAccount(int creditOfAccount) {
		this.creditOrAccount = creditOfAccount;
	}

	public void setPurchaseGoods(String purchaseGoods) {
		this.purchaseGoods = purchaseGoods;
	}
	public String getPurchaseGoods() {
		return purchaseGoods;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

}
