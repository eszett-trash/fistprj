package shoesTailor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShoesTailorDB {

	ShoesTailorDB() {
		STOCK_VO sv = new STOCK_VO();
		sv.setStock_vo_id(0);
		sv.setName("가나다");
		sv.setKind(1111);
		sv.setColor("빨강");
		stockList.add(sv);

		STOCK_VO sv2 = new STOCK_VO();
		sv2.setStock_vo_id(1);
		sv2.setName("가나다");
		sv2.setKind(3111);
		sv2.setColor("까망");
		stockList.add(sv2);
		
		USER_VO uv = new USER_VO();
		uv.setAccount("admin");
		uv.setPassword("admin");
		uv.setAdmin(true);
		uv.setActivated(true);
		userList.add(uv);
	}

	// 리스트
	private List<USER_VO> userList = new ArrayList<USER_VO>(); // 사용자 정보
	private List<SALES_VO> salesList = new ArrayList<SALES_VO>(); // 매상
	private List<STOCK_VO> stockList = new ArrayList<STOCK_VO>(); // 재고
	private List<CART_VO> cart = new LinkedList<CART_VO>(); // 장바구니
	private List<MESSAGE_VO> message = new ArrayList<MESSAGE_VO>(); // 쪽지

	
	// 단순 조회
	public List<USER_VO> userList() {
		return userList;
	}

	public List<SALES_VO> salesList() {
		return salesList;
	}

	public List<STOCK_VO> stockList() {
		return stockList;
	}

	public List<CART_VO> cart() {
		return cart;
	}

	public List<MESSAGE_VO> message() {
		return message;
	}
	
	//아이디 중복체크
	public boolean idChk(String account) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).equals(account)) {
				return false;
			}
		}
		return true;
	}
}
