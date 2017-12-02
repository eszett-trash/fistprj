package fistprj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShoesTailorDB {
	private static ShoesTailorDB Instance = new ShoesTailorDB();
	
	public static ShoesTailorDB getInstance(){
		return Instance;
	}
	
	ShoesTailorDB() {
		STOCK_VO sv = new STOCK_VO();
		sv.setStock_vo_id(0);
		sv.setName("색상 : 빨강");
		sv.setPrice(100);
		sv.setKind(4);
		stockList.add(sv);
	}

		List<USER_VO> userList = new ArrayList<USER_VO>();		//사용자 정보
		List<SALES_VO> salesList = new ArrayList<SALES_VO>(); 	//매상
		List<STOCK_VO> stockList = new ArrayList<STOCK_VO>(); 	//재고
		List<CART_VO> cart = new LinkedList<CART_VO>(); 		//장바구니
		List<MESSAGE_VO> message = new ArrayList<MESSAGE_VO>(); //쪽지
		
		
	
		public void view_stock()
		{
			for (int i = 0; i < stockList.size(); i++) {
				System.out.println(stockList.get(i).getStock_vo_id()); //고유아이디 (입력순서)
				System.out.println(stockList.get(i).getName()); //이름
				System.out.println(stockList.get(i).getPrice()); //가격
				System.out.println(stockList.get(i).getAmount()); // 수량
				System.out.println(stockList.get(i).getKind()); //종류
			}
		}
}
