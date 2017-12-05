/**
 * 2017 12 02 작성자 : 조은솔
 * 
 * 2017 12 04 사이즈 게터 및 수정기능 업데이트 : 조은솔
 */
package shoesTailor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CopyOfShoesTailorDB {
	private static CopyOfShoesTailorDB Instance = new CopyOfShoesTailorDB();

	CopyOfShoesTailorDB() {
		STOCK_VO sv = new STOCK_VO();
		sv.setStock_vo_id(0);
		sv.setName("색빨강");
		sv.setPrice(100);
		sv.setKind(111);
		stockList.add(sv);

		STOCK_VO sv2 = new STOCK_VO();
		sv2.setStock_vo_id(1);
		sv2.setName("색빨강");
		sv2.setPrice(100);
		sv2.setKind(311);
		stockList.add(sv2);
		
	}

	// 리스트
	private List<USER_VO> userList = new ArrayList<USER_VO>(); // 사용자 정보
	private List<SALES_VO> salesList = new ArrayList<SALES_VO>(); // 매상
	private List<STOCK_VO> stockList = new ArrayList<STOCK_VO>(); // 재고
	private List<CART_VO> cart = new LinkedList<CART_VO>(); // 장바구니
	private List<MESSAGE_VO> message = new ArrayList<MESSAGE_VO>(); // 쪽지

	// 객체 생성
	public static CopyOfShoesTailorDB getInstance() {
		return Instance;
	}

	// 사이즈 게터
	public int size_userList() {
		return userList.size();
	}

	public int size_salesList() {
		return salesList.size();
	}

	public int size_stockList() {
		return stockList.size();
	}

	public int size_cart() {
		return cart.size();
	}

	public int size_message() {
		return message.size();
	}

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

		return userVoId + "\t" + account + "\t" + password + "\t"
				+ deliveryAddress + "\t" + cellPhone + "\t" + sales + "\t"
				+ purchseGoods + "\t" + isAdmin + "\t" + isActivated+"\n";
	}

	public String view_adminList(int index) {// 관리자 리스트 보기
		if (userList.get(index).isAdmin()) {
			int userVoId = userList.get(index).getUser_vo_id();
			String account = userList.get(index).getAccount();
			String password = userList.get(index).getPassword();
			String deliveryAddress = userList.get(index).getDeliveryAddress();
			String cellPhone = userList.get(index).getCellphone();
			int sales = userList.get(index).getSales();
			String purchseGoods = userList.get(index).getPurchaseGoods();
			boolean isAdmin = userList.get(index).isAdmin();
			boolean isActivated = userList.get(index).isActivated();

			return userVoId + "\t" + account + "\t" + password + "\t"
					+ deliveryAddress + "\t" + cellPhone + "\t" + sales + "\t"
					+ purchseGoods + "\t" + isAdmin + "\t" + isActivated+"\n";
		}
		return "관리자가 없습니다.";
	}

	public String view_activateList(int index) {// 활성화된 유저 리스트 보기
		if (userList.get(index).isActivated()) {
			int userVoId = userList.get(index).getUser_vo_id();
			String account = userList.get(index).getAccount();
			String password = userList.get(index).getPassword();
			String deliveryAddress = userList.get(index).getDeliveryAddress();
			String cellPhone = userList.get(index).getCellphone();
			int sales = userList.get(index).getSales();
			String purchseGoods = userList.get(index).getPurchaseGoods();
			boolean isAdmin = userList.get(index).isAdmin();
			boolean isActivated = userList.get(index).isActivated();

			return userVoId + "\t" + account + "\t" + password + "\t"
					+ deliveryAddress + "\t" + cellPhone + "\t" + sales + "\t"
					+ purchseGoods + "\t" + isAdmin + "\t" + isActivated+"\n";
		}
		return "활성화 된 유저가 없습니다.";
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

		return date + "\t" + account + "\t" + deliveryAddress + "\t" + price
				+ "\t" + isDelivery + "\t" + orderNumber + "\t"
				+ creditOrAccount + "\t" + purchaseGoods+"\n";

	}

	public String view_stockList(int index) {
		int stock_vo_id = stockList.get(index).getStock_vo_id();
		String name = stockList.get(index).getName();
		int amount = stockList.get(index).getAmount();
		int price = stockList.get(index).getPrice();
		String color = stockList.get(index).getColor();
		int kind = stockList.get(index).getKind();

		return stock_vo_id + "\t" + name + "\t" + amount + "\t" + price + "\t"
				+ color + "\t" + kind+"\n";
	}

	public String view_runningShoes(int index, int getKind, int getKind2,
			int getKind3) { // 해당하는 종류의 템만 받아오기
		int stock_vo_id = stockList.get(index).getStock_vo_id();
		String name = stockList.get(index).getName();
		int amount = stockList.get(index).getAmount();
		int price = stockList.get(index).getPrice();
		String color = stockList.get(index).getColor();
		int kind = stockList.get(index).getKind();
		if ((kind / 100 == getKind && (kind / 10 - getKind * 10) == getKind2 && kind % 10 == getKind3)
				|| (kind / 100 == 3 && (kind / 10 - 30
						) == getKind2 && kind % 10 == getKind3)) {
			return stock_vo_id + "\t" + name + "\t" + amount + "\t" + price
					+ "\t" + color + "\t" + kind+"\n";
		} else {
			return "";
		}
	}

	public String view_cart(int index) {
		int cart_vo_id = cart.get(index).getCart_vo_id();
		String name = cart.get(index).getName();
		int price = cart.get(index).getPrice();
		int orderNumber = cart.get(index).getOrderNumber();

		return cart_vo_id + "\t" + name + "\t" + price + "\t" + orderNumber+"\n";
	}

	public String view_message(int index) {
		String account = message.get(index).getAccount();
		String contents = message.get(index).getContents();
		String date = message.get(index).getDate();

		return account + "\t" + contents + "\t" + date+"\n";
	}

	// 가장 마지막에 추가
	public void addLast_userList(int user_vo_id, String account,
			String password, String deliveryAddress, String cellphone,
			int sales, String purchaseGoods, boolean isAdmin,
			boolean isActivated) {
		if (idChk(account)) {
			USER_VO uv = new USER_VO();
			uv.setUser_vo_id(userList.size());
			uv.setAccount(account);
			uv.setPassword(password);
			uv.setDeliveryAddress(deliveryAddress);
			uv.setCellphone(cellphone);
			uv.setSales(sales);
			uv.setPurchaseGoods(purchaseGoods);
			uv.setAdmin(isAdmin);
			uv.setActivated(isActivated);
			userList.add(uv);
		} else {
			return;
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
			int price, String color, int kind) {
		STOCK_VO sv = new STOCK_VO();
		sv.setStock_vo_id(stockList.size());
		sv.setName(name);
		sv.setAmount(amount);
		sv.setPrice(price);
		sv.setColor(color);
		sv.setKind(kind);
		stockList.add(sv);
	}

	public void addLast_cart(String name, int price, int orderNumber) {
		CART_VO cv = new CART_VO();
		cv.setCart_vo_id(cart.size());
		cv.setName(name);
		cv.setPrice(price);
		cv.setOrderNumber(orderNumber);
		cart.add(cv);
	}

	public void addLast_message(String account, String contents) {
		MESSAGE_VO mv = new MESSAGE_VO();
		mv.setAccount(account);
		mv.setContents(contents);
		mv.setDate();
		message.add(mv);
	}

	// //수정
	// 일단 인덱스넘버를 받아서 수정하는 것으로 해 두었습니다.
	// 그외의 경우에는 필요한 경우 말 해 주세요.
	// "" 혹은 0을 입력하면 셋팅되지 않습니다.
	public void update_userList(int index, int user_vo_id, String account,
			String password, String deliveryAddress, String cellphone,
			int sales, String purchaseGoods, boolean isAdmin,
			boolean isActivated) {
		if (index >= userList.size()) {
			return;
		} else {
			if (user_vo_id != 0) {
				userList.get(index).setUser_vo_id(user_vo_id);
			}
			if (account != "") {
				userList.get(index).setAccount(account);
			}
			if (password != "") {
				userList.get(index).setPassword(password);
			}
			if (deliveryAddress != "") {
				userList.get(index).setDeliveryAddress(deliveryAddress);
			}
			if (cellphone != "") {
				userList.get(index).setCellphone(cellphone);
			}
			if (sales != 0) {
				userList.get(index).setSales(sales);
			}
			if (purchaseGoods != "") {
				userList.get(index).setPurchaseGoods(purchaseGoods);
			}
			userList.get(index).setAdmin(isAdmin);
			userList.get(index).setActivated(isActivated);
		}
	}

	public void update_salesList(int index, String account,
			String deliveryAddress, int price, boolean isDelivery,
			int orderNumber, int creditOfAccount, String purchaseGoods) {
		if (index >= salesList.size()) {
			return;
		} else {
			if (account != "") {
				salesList.get(index).setAccount(account);
			}
			if (deliveryAddress != "") {
				salesList.get(index).setDeliveryAddress(deliveryAddress);
			}
			if (price != 0) {
				salesList.get(index).setPrice(price);
			}
			salesList.get(index).setDelivery(isDelivery);
			if (orderNumber != 0) {
				salesList.get(index).setOrderNumber(orderNumber);
			}
			if (creditOfAccount != 0) {
				salesList.get(index).setCreditOfAccount(creditOfAccount);
			}
			if (purchaseGoods != "") {
				salesList.get(index).setPurchaseGoods(purchaseGoods);
			}
		}
	}

	public void update_stockList(int index, int stock_vo_id, String name,
			int amount, int price, String color, int kind) {
		if (index >= stockList.size()) {
			return;
		} else {
			if (stock_vo_id != 0) {
				stockList.get(index).setStock_vo_id(stock_vo_id);
			}
			if (name != "") {
				stockList.get(index).setName(name);
			}
			if (amount != 0) {
				stockList.get(index).setAmount(amount);
			}
			if (price != 0) {
				stockList.get(index).setPrice(price);
			}
			if (color != "") {
				stockList.get(index).setColor(color);
			}
			if (kind != 0) {
				stockList.get(index).setKind(kind);
			}
		}
	}

	public void update_cart(int index, int cart_vo_id, String name, int price,
			int orderNumber) {
		if (index >= cart.size()) {
			return;
		} else {
			if (cart_vo_id != 0) {
				cart.get(index).setCart_vo_id(cart_vo_id);
			}
			if (name != "") {
				cart.get(index).setName(name);
			}
			if (price != 0) {
				cart.get(index).setPrice(price);
			}
			if (orderNumber != 0) {
				cart.get(index).setOrderNumber(orderNumber);
			}
		}
	}

	public void update_message(int index, String account, String contents) {
		if (index >= message.size()) {
			return;
		} else {
			if (account != "") {
				message.get(index).setAccount(account);
			}
			if (contents != "") {
				message.get(index).setContents(contents);
			}
		}
	}

	// 삭제
	public void delete_userList(int index) {
		if (userList.isEmpty()) {
			return;
		}
		userList.remove(index);
	}

	public void delete_salesList(int index) {
		if (salesList.isEmpty()) {
			return;
		}
		salesList.remove(index);
	}

	public void delete_stockList(int index) {
		if (stockList.isEmpty()) {
			return;
		}
		stockList.remove(index);
	}

	public void delete_cart(int index) {
		if (cart.isEmpty()) {
			return;
		}
		cart.remove(index);
	}

	public void delete_message(int index) {
		if (message.isEmpty()) {
			return;
		}
		message.remove(index);
	}

	// 기타기능들
	// 아이디 중복체크
	private boolean idChk(String account) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).equals(account)) {
				return false;
			}
		}
		return true;
	}
}
