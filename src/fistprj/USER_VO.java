package fistprj;

public class USER_VO {
private int user_vo_id; 		//유저 고유번호 (아이디 외의 식별번호)
private String account; 		//ID
private String password;		//비밀번호
private String deliveryAddress; //배송주소
private String cellphone;		//핸드폰번호
private int sales;				//매상
private String purchaseGoods; 		// 구매 부속
private boolean isAdmin; 		//관리자 유무
private boolean isActivated; 		//활성화 유무

public int getUser_vo_id() {
	return user_vo_id;
}
public void setUser_vo_id(int user_vo_id) {
	this.user_vo_id = user_vo_id;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getDeliveryAddress() {
	return deliveryAddress;
}
public void setDeliveryAddress(String deliveryAddress) {
	this.deliveryAddress = deliveryAddress;
}
public String getCellphone() {
	return cellphone;
}
public void setCellphone(String cellphone) {
	this.cellphone = cellphone;
}
public int getSales() {
	return sales;
}
public void setSales(int sales) {
	this.sales = sales;
}
public String getPurchaseGoods() {
	return purchaseGoods;
}
public void setPurchaseGoods(String purchaseGoods) {
	this.purchaseGoods = purchaseGoods;
}
public boolean isAdmin() {
	return isAdmin;
}
public void setAdmin(boolean isAdmin) {
	this.isAdmin = isAdmin;
}
public boolean isActivated() {
	return isActivated;
}
public void setActivated(boolean isActivated) {
	this.isActivated = isActivated;
}

}
