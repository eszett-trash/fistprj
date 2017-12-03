package shoesTailor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShoesTailorDB {
	private static ShoesTailorDB Instance = new ShoesTailorDB();

	ShoesTailorDB() {
		STOCK_VO sv = new STOCK_VO();
		sv.setStock_vo_id(0);
		sv.setName("색상:빨강");
		sv.setPrice(100);
		sv.setKind(4);
		stockList.add(sv);
		
		STOCK_VO sv2 = new STOCK_VO();
		sv2.setStock_vo_id(0);
		sv2.setName("색상:빨강");
		sv2.setPrice(100);
		sv2.setKind(4);
		stockList.add(sv2);
	}

	//리스트
	private List<USER_VO> userList = new ArrayList<USER_VO>(); // 사용자 정보
	private List<SALES_VO> salesList = new ArrayList<SALES_VO>(); // 매상
	private List<STOCK_VO> stockList = new ArrayList<STOCK_VO>(); // 재고
	private List<CART_VO> cart = new LinkedList<CART_VO>(); // 장바구니
	private List<MESSAGE_VO> message = new ArrayList<MESSAGE_VO>(); // 쪽지
	private List<COLOR_VO> colorList = new ArrayList<COLOR_VO>(); // 색상

	// 객체 생성
	public static ShoesTailorDB getInstance() {
		return Instance;
	}
	
	//사이즈 게터
	public int size_userList()		{ return userList.size();}
	public int size_salesList()		{ return salesList.size();}
	public int size_stockList()		{ return stockList.size();}
	public int size_cart()			{ return cart.size();}
	public int size_message()		{ return message.size();}
	public int size_colorList()		{ return colorList.size();}
	

	// 단순 조회
	public String view_userList(int index) {
		int userVoId = userList.get(index).getUser_vo_id();
		String account = userList.get(index).getAccount();
		String password = userList.get(index).getPassword();
		String deliveryAddress = userList.get(index).getDeliveryAddress();
		String cellPhone = userList.get(index).getCellphone();
		int sales = userList.get(index).getSales();
		String purchseGoods = userList.get(index).getPurchaseGoods();
		boolean isAdmin = userList.get(index).isAdmin();
		boolean isActivated = userList.get(index).isActivated();

		return userVoId + " " + account + " " + password + " "
				+ deliveryAddress + " " + cellPhone + " " + sales + " "
				+ purchseGoods + " " + isAdmin + " " + isActivated;
	}

	public String view_salesList(int index) {
		String date = salesList.get(index).getDate();
		String account = salesList.get(index).getAccount();
		String deliveryAddress = salesList.get(index).getDeliveryAddress();
		int price = salesList.get(index).getPrice();
		boolean isDelivery = salesList.get(index).isDelivery();
		int orderNumber = salesList.get(index).getOrderNumber();
		int creditOrAccount = salesList.get(index).getCreditOfAccount();
		String purchaseGoods = salesList.get(index).getPurchaseGoods();

		return date + " " + account + " " + deliveryAddress + " " + price + " "
				+ isDelivery + " " + orderNumber + " " + creditOrAccount + " "
				+ purchaseGoods;

	}

	public String view_stockList(int index) {
		int stock_vo_id = stockList.get(index).getStock_vo_id();
		String name = stockList.get(index).getName();
		int amount = stockList.get(index).getAmount();
		int price = stockList.get(index).getPrice();
		int kind = stockList.get(index).getKind();

		return stock_vo_id + " " + name + " " + amount + " " + price + " "
				+ kind;
	}

	public String view_cart(int index) {
		int cart_vo_id = cart.get(index).getCart_vo_id();
		String name = cart.get(index).getName();
		int price = cart.get(index).getPrice();
		int orderNumber = cart.get(index).getOrderNumber();

		return cart_vo_id + " " + name + " " + price + " " + orderNumber;
	}

	public String view_message(int index) {
		String account = message.get(index).getAccount();
		String contents = message.get(index).getContents();
		String date = message.get(index).getDate();

		return account + " " + contents + " " + date;
	}

	public String view_colorList(int index) {
		String color = colorList.get(index).getColor();
		int kind = colorList.get(index).getKind();
		return color + " " + kind;
	}

	// 가장 마지막에 추가
	public void addLast_userList(int user_vo_id, String account,
			String password, String deliveryAddress, String cellphone,
			int sales, String purchaseGoods, boolean isAdmin,
			boolean isActivated) {

		if (user_vo_id == 0) {
			return;
		} else {
			USER_VO uv = new USER_VO();
			uv.setUser_vo_id(user_vo_id);
			uv.setAccount(account);
			uv.setPassword(password);
			uv.setDeliveryAddress(deliveryAddress);
			uv.setCellphone(cellphone);
			uv.setSales(sales);
			uv.setPurchaseGoods(purchaseGoods);
			uv.setAdmin(isAdmin);
			uv.setActivated(isActivated);
			userList.add(uv);
		}
	}

	public void addLast_salesList(String account, String deliveryAddress,
			int price, boolean isDelivery, int orderNumber,
			int creditOfAccount, String purchaseGoods) {
		SALES_VO sv = new SALES_VO();
		sv.setAccount(account);
		sv.setDeliveryAddress(deliveryAddress);
		sv.setPrice(price);
		sv.setDelivery(isDelivery);
		sv.setOrderNumber(orderNumber);
		sv.setCreditOfAccount(creditOfAccount);
		sv.setPurchaseGoods(purchaseGoods);
		sv.setDate();
		salesList.add(sv);
	}

	public void addLast_stockList(int stock_vo_id, String name, int amount,
			int price, int kind) {

		if (stock_vo_id == 0) {
			return;
		} else {
			STOCK_VO sv = new STOCK_VO();
			sv.setStock_vo_id(stock_vo_id);
			sv.setName(name);
			sv.setAmount(amount);
			sv.setPrice(price);
			sv.setKind(kind);
			stockList.add(sv);
		}
	}

	public void addLast_cart(int cart_vo_id, String name, int price,
			int orderNumber) {
		if (cart_vo_id == 0) {
			return;
		} else {
			CART_VO cv = new CART_VO();
			cv.setCart_vo_id(cart_vo_id);
			cv.setName(name);
			cv.setPrice(price);
			cv.setOrderNumber(orderNumber);
			cart.add(cv);
		}
	}

	public void addLast_message(String account, String contents) {
		MESSAGE_VO mv = new MESSAGE_VO();
		mv.setAccount(account);
		mv.setContents(contents);
		mv.setDate();
		message.add(mv);
	}

	public void addLast_colorList(String color, int kind) {
		COLOR_VO cv = new COLOR_VO();
		cv.setColor(color);
		cv.setKind(kind);
		colorList.add(cv);
	}

	// 삭제
	public void delete_userList(int index) {
		if (userList.isEmpty()) {return;}
		userList.remove(index);
	}

	public void delete_salesList(int index) {
		if (salesList.isEmpty()) {return;}
		salesList.remove(index);
	}

	public void delete_stockList(int index) {
		if (stockList.isEmpty()) {return;}
		stockList.remove(index);
	}

	public void delete_cart(int index) {
		if (cart.isEmpty()) {return;}
		cart.remove(index);
	}

	public void delete_message(int index) {
		if (message.isEmpty()) {return;}
		message.remove(index);
	}

	public void delete_colorList(int index) {
		if (colorList.isEmpty()) {return;}
		colorList.remove(index);
	}

}
