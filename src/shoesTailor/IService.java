package shoesTailor;

import java.util.List;

public interface IService {

	public void addLast_userList(String account, String password,
			String deliveryAddress, String cellphone, int sales,
			String purchaseGoods, boolean isAdmin, boolean isActivated);

	public void addLast_salesList(String account, String deliveryAddress,
			int price, boolean isDelivery, int orderNumber,
			int creditOfAccount, List<String> purchaseGoods);

	public void addLast_stockList(String name, int amount, int price,
			String color, int kind);

	public void addLast_cart(int stock_vo_id);

	public void addLast_message(String account, String contents);

	// 수정

	public void update_userList(String account, String password,
			String deliveryAddress, String cellphone, int sales,
			String purchaseGoods, boolean isAdmin, boolean isActivated);

	public void update_salesList(int index, String account,
			String deliveryAddress, int price, boolean isDelivery,
			int orderNumber, int creditOfAccount, List<String> purchaseGoods);

	public void update_stockList(int stock_vo_id, String name, int amount,
			int price, String color, int kind);

	public void update_cart(int index, int cart_vo_id, String name, int price,
			int orderNumber);

	public void update_message(String account, String contents);

	// 삭제

	public void delete_userList(int index);

	public void delete_salesList(int index);

	public void delete_stockList(int index);

	public void delete_cart();

	public void delete_message(int index);

	public boolean isLoginChk(String result, String result2);

	public void userActivated(String account);

	public void userUnactivated(String account);

	public void sign(String account, List<String> purchaseGoods, int creditOfAccount);

	public boolean loginType(String account);

	public void delete_Stock(int index);

	public void add_admin(String id, String pwd);

}
