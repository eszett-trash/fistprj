package shoesTailor;

import java.util.Calendar;

public class ServiceImpl implements IService {
	ShoesTailorDB sdb = new ShoesTailorDB();

	// 가장 마지막에 추가

	public void addLast_userList(String account, String password,
			String deliveryAddress, String cellphone, int sales,
			String purchaseGoods, boolean isAdmin, boolean isActivated) {
		if (sdb.idChk(account)) {
			USER_VO uv = new USER_VO();
			uv.setUser_vo_id(sdb.userList().size());
			uv.setAccount(account);
			uv.setPassword(password);
			uv.setDeliveryAddress(deliveryAddress);
			uv.setCellphone(cellphone);
			uv.setSales(sales);
			uv.setPurchaseGoods(purchaseGoods);
			uv.setAdmin(isAdmin);
			uv.setActivated(isActivated);
			sdb.userList().add(uv);
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
		sdb.salesList().add(sv);
	}

	public void addLast_stockList(String name, int amount, int price,
			String color, int kind) {
		STOCK_VO sv = new STOCK_VO();
		sv.setStock_vo_id(sdb.stockList().size());
		sv.setName(name);
		sv.setAmount(amount);
		sv.setPrice(price);
		sv.setColor(color);
		sv.setKind(kind);
		sdb.stockList().add(sv);
	}

	public void addLast_cart(int stock_vo_id) {
		CART_VO cv = new CART_VO();
		cv.setCart_vo_id(sdb.cart().size());
		cv.setName(getStockName(stock_vo_id));
		cv.setPrice(getStockPrice(stock_vo_id));
		cv.setOrderNumber(setOrderNum());
		cv.setStock_vo_id(stock_vo_id);
		sdb.cart().add(cv);
	}

	private int getStockPrice(int stock_vo_id) {
		for (int index = 0; index < sdb.stockList().size(); index++) {
			if (sdb.stockList().get(index).getStock_vo_id() == stock_vo_id) {
				return sdb.stockList().get(index).getPrice();
			}
		}
		return -1;
	}

	private String getStockName(int stock_vo_id) {
		for (int index = 0; index < sdb.stockList().size(); index++) {
			if (sdb.stockList().get(index).getStock_vo_id() == stock_vo_id) {
				return sdb.stockList().get(index).getName();
			}
		}
		return "입력에러";
	}

	public void addLast_message(String account, String contents) {
		MESSAGE_VO mv = new MESSAGE_VO();
		mv.setAccount(account);
		mv.setContents(contents);
		mv.setDate();
		sdb.message().add(mv);
	}

	// //수정
	// 일단 인덱스넘버를 받아서 수정하는 것으로 해 두었습니다.
	// 그외의 경우에는 필요한 경우 말 해 주세요.
	// "" 혹은 0을 입력하면 셋팅되지 않습니다.

	public void update_userList(String account, String password,
			String deliveryAddress, String cellphone, int sales,
			String purchaseGoods, boolean isAdmin, boolean isActivated) {
		for (int index = 0; index < sdb.userList().size(); index++) {
			if (sdb.userList().get(index).getAccount().equals(account)) {
				if (password != "null") {
					sdb.userList().get(index).setPassword(password);
				}
				if (deliveryAddress != "null") {
					sdb.userList().get(index)
							.setDeliveryAddress(deliveryAddress);
				}
				if (cellphone != "null") {
					sdb.userList().get(index).setCellphone(cellphone);
				}
				if (sales != 0) {
					sdb.userList().get(index).setSales(sales);
				}
				if (purchaseGoods != "null") {
					sdb.userList().get(index).setPurchaseGoods(purchaseGoods);
				}
				sdb.userList().get(index).setAdmin(isAdmin);
				sdb.userList().get(index).setActivated(isActivated);
				return;
			}
		}

	}

	public void update_salesList(int index, String account,
			String deliveryAddress, int price, boolean isDelivery,
			int orderNumber, int creditOfAccount, String purchaseGoods) {
		if (index >= sdb.salesList().size()) {
			return;
		} else {
			if (account != "null") {
				sdb.salesList().get(index).setAccount(account);
			}
			if (deliveryAddress != "null") {
				sdb.salesList().get(index).setDeliveryAddress(deliveryAddress);
			}
			if (price != 0) {
				sdb.salesList().get(index).setPrice(price);
			}
			sdb.salesList().get(index).setDelivery(isDelivery);
			if (orderNumber != 0) {
				sdb.salesList().get(index).setOrderNumber(orderNumber);
			}
			if (creditOfAccount != 0) {
				sdb.salesList().get(index).setCreditOfAccount(creditOfAccount);
			}
			if (purchaseGoods != "null") {
				sdb.salesList().get(index).setPurchaseGoods(purchaseGoods);
			}
		}
	}

	public void update_stockList(int stock_vo_id, String name, int amount,
			int price, String color, int kind) {
		for (int index = 0; index < sdb.stockList().size(); index++) {
			if (sdb.stockList().get(index).getStock_vo_id() == stock_vo_id) {
				if (name != "null") {
					sdb.stockList().get(index).setName(name);
				}
				if (amount != 0) {
					sdb.stockList().get(index).setAmount(amount);
				}
				if (price != 0) {
					sdb.stockList().get(index).setPrice(price);
				}
				if (color != "null") {
					sdb.stockList().get(index).setColor(color);
				}
				if (kind != 0) {
					sdb.stockList().get(index).setKind(kind);
				}
			}
		}
	}

	public void update_cart(int index, int cart_vo_id, String name, int price,
			int orderNumber) {
		if (index >= sdb.cart().size()) {
			return;
		} else {
			if (cart_vo_id != 0) {
				sdb.cart().get(index).setCart_vo_id(cart_vo_id);
			}
			if (name != "null") {
				sdb.cart().get(index).setName(name);
			}
			if (price != 0) {
				sdb.cart().get(index).setPrice(price);
			}
			if (orderNumber != 0) {
				sdb.cart().get(index).setOrderNumber(orderNumber);
			}
		}
	}

	public void update_message(int index, String account, String contents) {
		if (index >= sdb.message().size()) {
			return;
		} else {
			if (account != "null") {
				sdb.message().get(index).setAccount(account);
			}
			if (contents != "null") {
				sdb.message().get(index).setContents(contents);
			}
		}
	}

	// 삭제

	public void delete_userList(int index) {
		if (sdb.userList().isEmpty()) {
			return;
		}
		sdb.userList().remove(index);
	}

	public void delete_salesList(int index) {
		if (sdb.salesList().isEmpty()) {
			return;
		}
		sdb.salesList().remove(index);
	}

	public void delete_stockList(int index) {
		if (sdb.stockList().isEmpty()) {
			return;
		}
		sdb.stockList().remove(index);
	}

	public void delete_cart(int index) {
		if (sdb.cart().isEmpty()) {
			return;
		}
		sdb.cart().remove(index);
	}

	public void delete_message(int index) {
		if (sdb.message().isEmpty()) {
			return;
		}
		sdb.message().remove(index);
	}

	public boolean isLoginChk(String result, String result2) {
		for (int i = 0; i < sdb.userList().size(); i++) {
			if (result.equals(sdb.userList().get(i).getAccount())) { // 스트링은참조형
				if (result2.equals(sdb.userList().get(i).getPassword())) {
					return true;
				}// pwd chk
			}// account chk
		}
		return false;
	}

	public void userActivated(String account) {
		for (int j = 0; j < sdb.userList().size(); j++) {
			if (sdb.userList().get(j).getAccount().equals(account)) {
				if (!sdb.userList().get(j).isAdmin()) {
					update_userList(account, "", "", "", 0, "", false, true);
				}
			}
		}
	}

	public void sign(String account, String purchaseGoods, int creditOfAccount) {
		//세일즈 리스트에서 추가하기
		for (int index = 0; index < sdb.userList().size(); index++) {
			addLast_salesList(account,sdb.userList().get(index)
					.getDeliveryAddress(),cart_price_add(), false,
					sdb.cart().get(0).getOrderNumber(), creditOfAccount, purchaseGoods);
		}
		//재고에서 빼기
		for (int index = 0; index < sdb.stockList().size(); index++) {
			if (sdb.cart().get(index).getStock_vo_id()==sdb.stockList().get(index).getStock_vo_id()) {
				sdb.stockList().get(index).setAmount(sdb.stockList().get(index).getAmount()-1);
			}
		}
		//카트 비우기
		for (int index = 0; index < sdb.cart().size(); index++) {
			sdb.cart().remove(index);
		}
	}

	private int cart_price_add() {
		int add = 0;
		for (int i = 0; i < sdb.cart().size(); i++) {
			add+=sdb.cart().get(i).getPrice();
		}
		return add;
	}


	public boolean loginType(String account) {
		for (int i = 0; i < sdb.userList().size(); i++) {
			if (account.equals(sdb.userList().get(i).getAccount())) {
				if (sdb.userList().get(i).isAdmin()) {
					return true;
				}
			}
		}
		return false;
	}

	public void delete_Stock(int index) {
		int amount = sdb.stockList().get(index).getAmount();
		if (amount == 0) {
			delete_stockList(index);
		}
	}

	private int setOrderNum() {
		Calendar c = Calendar.getInstance();
		return Integer.parseInt(c.get(Calendar.YEAR) + c.get(Calendar.MONTH)
				+ c.get(Calendar.DATE) + "" + (int) (Math.random() * 100));
	}

	public void add_admin(String id, String pwd) {
		for (int i = 0; i < sdb.userList().size(); i++) {
			if (sdb.userList().get(i).getAccount().equals(id)) {
				return;
			}
		}
		addLast_userList(id, pwd, null, null, 0, null, true, true);
	}

}
