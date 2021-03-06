package shoesTailor;

import java.util.Calendar;
import java.util.List;

public class ServiceImpl implements IService {
	ShoesTailorDB sdb = new ShoesTailorDB();

	// 리스트 가장 마지막에 추가
	@Override
	public void addLast_userList(String account, String password,
			String deliveryAddress, String cellphone, int sales,
			String purchaseGoods, boolean isAdmin, boolean isActivated) {
		if (idChk(account)) {
			USER_VO uv = new USER_VO();
			uv.setUser_vo_id(sdb.userList().size());
			uv.setAccount(account);
			uv.setPassword(password);
			uv.setDeliveryAddress(deliveryAddress);
			uv.setCellphone(cellphone);
			uv.setSales(sales);
			uv.setAdmin(isAdmin);
			uv.setActivated(isActivated);
			sdb.userList().add(uv);
		} else {
			return;
		}
	}
//	아이디 중복 체크
	private boolean idChk(String account) {
		for (int i = 0; i < sdb.userList().size(); i++) {
			if (sdb.userList().get(i).equals(account)) {
				return false;
			}
		}
		return true;
	}

	//관리자 체크
	public boolean loginType(String account) {
		for (int i = 0; i < sdb.userList().size(); i++) {
			if (account.equals(sdb.userList().get(i).getAccount())) {
				if (sdb.userList().get(i).isAdmin()) {
					return true; //관리자
				}
			}
		}
		return false; //유저
	}
	@Override
	public void addLast_salesList(String account, String deliveryAddress,
			int price, boolean isDelivery, int orderNumber,
			int creditOfAccount, List<String> purchaseGoods) {
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

	@Override
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

	@Override
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

	@Override
	public void addLast_message(String account, String contents) {
		MESSAGE_VO mv = new MESSAGE_VO();
		mv.setAccount(account);
		mv.setContents(contents);
		mv.setDate();
		sdb.message().add(mv);
	}

	// //수정
	// "" 혹은 0을 입력하면 셋팅되지 않습니다.
	@Override
	public void update_userList(String account, String password,
			String deliveryAddress, String cellphone, int sales,
			String purchaseGoods, boolean isAdmin, boolean isActivated) {
		for (int index = 0; index < sdb.userList().size(); index++) {
			if (sdb.userList().get(index).getAccount().equals(account)) {
				if (password.isEmpty()) {
					sdb.userList().get(index).setPassword(sdb.userList().get(index).getPassword());
				} else {
					sdb.userList().get(index).setPassword(password);
				}
				if (deliveryAddress.isEmpty()) {
					sdb.userList().get(index).setDeliveryAddress(sdb.userList().get(index).getDeliveryAddress());
				} else {
					sdb.userList().get(index).setDeliveryAddress(deliveryAddress);
				}
				if (cellphone.isEmpty()) {
					sdb.userList().get(index).setCellphone(sdb.userList().get(index).getCellphone());
				} else {
					sdb.userList().get(index).setCellphone(cellphone);
				}
				if (sales == 0) {
					sdb.userList().get(index).setSales(sdb.userList().get(index).getSales());
				} else {
					sdb.userList().get(index).setSales(sales);
				}
				sdb.userList().get(index).setAdmin(isAdmin);
				sdb.userList().get(index).setActivated(isActivated);
				return;
			}
		}

	}

	@Override
	public void update_salesList(int index, String account,
			String deliveryAddress, int price, boolean isDelivery,
			int orderNumber, int creditOfAccount, List<String> purchaseGoods) {
		if (index >= sdb.salesList().size()) {
			return;
		} else {
			if (account.isEmpty()) {
				sdb.salesList().get(index).setAccount(account);
			}
			if (deliveryAddress.isEmpty()) {
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
			if (purchaseGoods.isEmpty()) {
				sdb.salesList().get(index).setPurchaseGoods(purchaseGoods);
			}
		}
	}

	@Override
	public void update_stockList(int stock_vo_id, String name, int amount,
			int price, String color, int kind) {
		for (int index = 0; index < sdb.stockList().size(); index++) {
			if (sdb.stockList().get(index).getStock_vo_id() == stock_vo_id) {
				if (name != "null") {
					sdb.stockList().get(index).setName(name);
				}
					sdb.stockList().get(index).setAmount(amount);
					
					sdb.stockList().get(index).setPrice(price);
				if (color != "null") {
					sdb.stockList().get(index).setColor(color);
				}
				if (kind != 0) {
					sdb.stockList().get(index).setKind(kind);
				}
			}
		}
	}

	@Override
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

	@Override
	public void update_message(String account, String contents) {
		for (int index = 0; index < sdb.message().size(); index++) {
			if (account != "null") {
				sdb.message().get(index).setAccount(account);
			}
			if (contents != "null") {
				sdb.message().get(index).setContents(contents);
			}
		}
	}

	// 삭제
	@Override
	public void delete_userList(int index) {
		if (sdb.userList().isEmpty()) {
			return;
		}
		sdb.userList().remove(index);
	}
	@Override
	public void delete_salesList(int index) {
		if (sdb.salesList().isEmpty()) {
			return;
		}
		sdb.salesList().remove(index);
	}
	@Override
	public void delete_stockList(int index) {
		if (sdb.stockList().isEmpty()) {
			return;
		}
		sdb.stockList().remove(index);
	}
	@Override
	//재고에서 남은 수량이 0일 경우에만 삭제가 가능하게 만드는 메서드
	public void delete_Stock(int index) {
		int amount = sdb.stockList().get(index).getAmount();
		if (amount == 0) {
			delete_stockList(index);
		}
	}
	@Override
	public void delete_cart() {
		for (int index = sdb.cart().size()-1; index >= 0; index--) {
			sdb.cart().remove(index);
		}
	}
	@Override
	public void delete_message(int index) {
		if (sdb.message().isEmpty()) {
			return;
		}
		sdb.message().remove(index);
	}
	@Override
	public boolean isLoginChk(String result, String result2) {
		for (int i = 0; i < sdb.userList().size(); i++) {
			if (result.equals(sdb.userList().get(i).getAccount())) { // 스트링은참조형
				if (result2.equals(sdb.userList().get(i).getPassword())) {
					if (sdb.userList().get(i).isActivated()) {
						return true;
					}
				}// pwd chk
			}// account chk
		}
		return false;
	}
	//유저 비활성화 -> 활성화
	@Override
	public void userActivated(String account) {
		for (int j = 0; j < sdb.userList().size(); j++) {
			if (sdb.userList().get(j).getAccount().equals(account)) {
				if (!sdb.userList().get(j).isAdmin()) {
					update_userList(account, "null", "null", "null", 0, "null",
							false, true);
				}
			}
		}
	}
	//유저 활성화 -> 비활성화
	@Override
	public void userUnactivated(String account) {
		for (int j = 0; j < sdb.userList().size(); j++) {
			if (sdb.userList().get(j).getAccount().equals(account)) {
				update_userList(account, "null", "null", "null", 0, "null",
						false, false);
			}
		}
	}

	@Override
	public void sign(String account, List<String> purchaseGoods, int creditOfAccount) {
		// 세일즈 리스트에서 추가하기
		for (int index = 0; index < sdb.userList().size(); index++) {
			if (sdb.userList().get(index).getAccount().equals(account)) {
				addLast_salesList(account, sdb.userList().get(index)
						.getDeliveryAddress(), cart_price_add(), false, sdb.cart()
						.get(0).getOrderNumber(), creditOfAccount, purchaseGoods);
				
			}
		}
			
		// 재고에서 빼기
		for (int index = 0; index < sdb.cart().size(); index++) {
			for (int i = 0; i < sdb.stockList().size(); i++) {
				if (sdb.cart().get(index).getStock_vo_id() == sdb.stockList().get(i).getStock_vo_id()) {
					 sdb.stockList().get(i).setAmount(sdb.stockList().get(i).getAmount() - 1);
				}

			}
		}
		// 카트 비우기
		delete_cart();
	}
	public int cart_price_add() {
		int add = 0;
		for (int i = 0; i < sdb.cart().size(); i++) {
			add += sdb.cart().get(i).getPrice();
		}
		return add;
	}
	
	//주문번호 자동 생성기
	private int setOrderNum() {
		Calendar c = Calendar.getInstance();
		return Integer.parseInt(c.get(Calendar.YEAR) + c.get(Calendar.MONTH)
				+ c.get(Calendar.DATE) + "" + (int) (Math.random() * 10000));
	}
	//관리자 추가
	@Override
	public void add_admin(String id, String pwd) {
		for (int i = 0; i < sdb.userList().size(); i++) {
			if (sdb.userList().get(i).getAccount().equals(id)) {
				return;
			}
		}
		addLast_userList(id, pwd, null, null, 0, null, true, true);
	}

	public void deliveryCheck(int num) {
		for (int i = 0; i < sdb.salesList().size(); i++) {	
			if(sdb.salesList().get(i).getOrderNumber()==num){
				sdb.salesList().get(i).setDelivery(true);
			}
		}
	}
	
	public void shoesStyleDuplChk(int id) {
	//카트에 stock_vo_id가 0~5사이에 있는 값인 아이템이 이미 잇는지 확인 후
		// 있으면 변경 없으면 추가
		if (!sdb.cart().isEmpty()) {
			if (sdb.cart().get(0).getStock_vo_id() >= 0
					|| sdb.cart().get(0).getStock_vo_id() <= 5) {
				update_cart(0, sdb.stockList().get(id).getStock_vo_id(),sdb.stockList().get(id).getName(), sdb.stockList().get(id).getPrice(), 0);
				System.out.println("변경되었습니다.");
			}
		} else {
			addLast_cart(id);
		}
	}

}
