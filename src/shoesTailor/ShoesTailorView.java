package shoesTailor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ShoesTailorView {
	private int getKind = 0;//대분류 (실루엣)
	private int getKind2 = 0;//중분류
	private int getKind3 = 0;//소분류
	private int getKind4 = 0;//상세분류

	private String account;//현재 로그인한 사람 체크
	private String purchaseGoods; // TODO 필터와는 별개로 계속 구매 아이템을 넣어주어야 합니다.
	private ServiceImpl sv = new ServiceImpl();//ServiceImpl 객체 생성

	private int intErrchk() { //입력단 에러체크 메서드 (view 클래스 전체에서 사용)
		Scanner ss = new Scanner(System.in);
		int temp;
		try {
			temp = ss.nextInt();
		} catch (Exception e) {
			System.out.println("조건에 맞게 입력하세요");
			temp = -1; //입력 오류시 -1을 리턴
		}
		return temp;
	}
	
	private String strErrchk(){
		Scanner sc = new Scanner(System.in);
		String temp;
		try {
			temp=sc.nextLine();
			temp = temp.trim();
		} catch (Exception e) {
			temp = "";
		}
		return temp;
	}
	private String strErrChk_next(){
		Scanner sc = new Scanner(System.in);
		String temp;
		try {
			temp=sc.next();
		} catch (Exception e) {
			temp = "";
		}
		return temp;
	}


	public void start() { // 시작 메인화면
		sv.addLast_userList("1", "1", "어쩌구 주소", "010-8888-5784", 0, "", false, true);
		while (true) { // 시작 모드 선택
			System.out.println();
			System.out.println("\t          ★☆★☆    Welcome to    ★☆★☆");
			System.out.println("\t▶▷ The Kindest OlineShop In The World ◁◀");
			System.out.println();
			System.out.println("\t 1. 로그인");
			System.out.println("\t 2. 회원가입");
			System.out.print("\t 3. 종료\n▶");
			switch (strErrchk()) {
			case "1": // 로그인
				login();
			case "2": // 회원가입
				join();
			case "3": // 종료
				System.out.println("시스템을 종료합니다.");
				System.exit(0);//종료
			default:
				System.out.println("입력이 잘못 되었습니다.");
				System.out.println();
				start();
			}
			break;
		}
	}

	public void login() { // 로그인
		while (true) {
			
			System.out.println(" ■■■■■■■■■■■■■■■■■ 로그인 ■■■■■■■■■■■■■■■■■ ");
			System.out.print("ID를 입력하세요 :\n▶");
			String result = strErrChk_next();
			System.out.print("PW를 입력하세요 :\n▶");
			String result2 = strErrChk_next();
			if (sv.isLoginChk(result, result2)) { //로그인이 되었는지 체크
				account = result; // 성공했을 경우 account에 값 저장
				System.out.println("로그인 성공!");
				if (sv.loginType(result)) {//관리자인지 체크
					admin();
				} else {
					user();
				}
			} else {
				System.out.println("다시 입력 해 주세요.");
				System.out.println();
			}
		}// close while loop
	}// close method

	private void admin() { // 관리자 로그인 화면
		System.out.println(" ■■■■■■■■■■■■■■■■■ 관리자 화면 ■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 상품관리");
			System.out.println("\t 2. 매출관리");
			System.out.println("\t 3. 회원관리");
			System.out.println("\t 4. 배송관리");
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrChk_next()) {
			case "1": // 상품관리
				stockManagement();
			case "2": // 매출관리
				salesManagement();
			case "3": // 회원관리
				memberManagement();
			case "4": // 배송관리
				deliveryManagement();
			case "#": // 뒤로가기
				start();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				admin();
			}
		}
	}

	// ------------------------------- 상품관리
	private void stockManagement() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 재고관리 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			view_stockList();
			System.out.println("\t 1. 추가");
			System.out.println("\t 2. 삭제");
			System.out.println("\t 3. 수정");
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrchk()) {
			case "1":
				String name;
				while(true){
					System.out.print("이름: 필수입력 사항입니다\n▶");
					name = strErrchk();
					if(!(name.isEmpty())){
						System.out.println();
						break;
					}else{
						System.out.println("이름: 필수입력 사항입니다!");
					}
				}
				
				int amount;
				while(true){
					System.out.print("수량: 999 이하로 입력하세요\n▶"); // 0가능
					amount = intErrchk();
					if(!(amount<0||amount>999)){
						System.out.println();
						break;
					}
				}
				
				int price;
				while(true){
					System.out.print("가격: 0 이상, 9999999 이하로 입력하세요\n▶"); // 0가능
					price = intErrchk();
					if(!(price<0||price>9999999)){
						System.out.println();
						break;
					}
				}
				
								
				System.out.print("색상을 입력 해 주세요\n▶");
				String color = strErrchk();
				
			
				int kind;
				while(true){
					System.out.print("종류를 입력 해 주세요(1000이상, 4000 미만로 입력하세요)\n▶");
					kind = intErrchk();
					if(!(kind<1000||kind>=4000)){
						System.out.println();
						break;
					}
				
				}sv.addLast_stockList(name, amount, price, color, kind);
					break;
				
				
			case "2":
				System.out.print("삭제 할 물품의 아이디를 입력 해 주세요.(수량이 0이면 삭제 불가능)\n▶");
				int index = intErrchk();
				if (index>sv.sdb.stockList().size()||index<0) {
					System.out.println("아이디가 없습니다.");
				}
				break;
			
			case "3":
				int stock_vo_id;
				while(true){
					System.out.print("수정 할 물품의 아이디를 입력 해 주세요\n▶");
					stock_vo_id = intErrchk();
					if (!((stock_vo_id < 0) || (stock_vo_id > sv.sdb.stockList().size()-1))) {
						System.out.println();
						break;
					}else{
						System.out.println("존재하지 않는 아이디입니다.");
						System.out.println();
					}
				}
				
				String name_s;
				while(true){
					System.out.print("이름을 수정 하실경우에는 이름을 입력해 주세요\n"
							+ "이름은 반드시 입력해야 합니다.\n▶");
					name_s = strErrchk();
					if(!(name_s.isEmpty())){
						System.out.println();
						break;
					}
				}

				int amount_s;
				while(true){
					System.out.print("수량을 수정 하실경우에는 수량을 입력해 주세요.\n"
							+ "\n최댓값은 999입니다.▶");
					amount_s = intErrchk();

					if(!(amount_s<0||amount_s>999)){
						System.out.println();
						break;
					}
				}


				int price_s;
				while(true){
					System.out.print("가격을 수정 하실경우에는 가격을 입력해 주세요.\n"
							+ "최댓값은 9999999입니다▶");
					price_s = intErrchk();

					if(!(price_s<0||price_s>9999999)){
						System.out.println();
						break;
					}
				}


				System.out.print("색상을 수정 하실경우에는 색상을 입력해 주세요\n"
						+ "입력하지 않으려면 엔터를 눌러주세요.\n▶");
				String color_s = strErrchk();


				int kind_s;		
				while(true){
					System.out.print("종류를 수정 하실 경우에는 종류를 입력해 주세요\n"
							+ "최댓값은 3999입니다▶");
					kind_s = intErrchk();
					if(!(kind_s<=999||kind_s>=4000)){
						System.out.println();
						break;
					}
				}


				System.out.println("입력 하시겠습니까? Y:입력 그 외의값 : 입력취소");
				String InputTemp = strErrchk();
				if (InputTemp.equals("Y")||InputTemp.equals("y")) {
					sv.update_stockList(stock_vo_id, name_s, amount_s, price_s,
							color_s, kind_s);
				}

			case "#":
				admin();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}


	// ------------------------------- 매출관리
	private void salesManagement() {
		System.out.println(" ■■■■■■■■■■■■■■■■■ 매출관리 ■■■■■■■■■■■■■■■■■ ");
		while (true) {
			view_salesList();
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrChk_next()) {
			case "#": // 뒤로가기
				admin();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				salesManagement();
			}
		}
	}

	// ------------------------------- 회원관리
	private void memberManagement() {
		System.out.println(" ■■■■■■■■■■■■■■■■■ 회원관리 ■■■■■■■■■■■■■■■■■ ");
		while (true) {
			view_userList();
			System.out.println("\t 1. 회원 활성화");
			System.out.println("\t 2. 회원 메세지");
			System.out.println("\t 3. 관리자 추가");
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrChk_next()) {
			case "1":
				System.out.print("비활성화 된 유저의 어카운트를 입력시 활성화 됩니다."
						+ "\n관리자는 비활성화 할 수 없습니다.\n▶");
				sv.userActivated(strErrChk_next());
				break;
			case "2":
				view_message();
				break;
			case "3":
				view_adminList();
				System.out.print("추가 할 관리자 아이디를 입력 해 주세요 (스페이스 구분)\n▶");

				String tempAccount = strErrChk_next();
				System.out.print("추가 할 관리자 비밀번호를 입력 해 주세요 (스페이스 구분)\n▶");
				String tempPwd = strErrChk_next();

				if (tempAccount.isEmpty() || tempPwd.isEmpty()) {
					System.out.println("공백은 입력되지 않습니다.");
				} else {
					sv.add_admin(strErrChk_next(), strErrChk_next());
				}
				break;
			case "#":
				admin();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				memberManagement();
			}
		}
	}

	// ------------------------------- 배송관리
	private void deliveryManagement() {
		System.out.println(" ■■■■■■■■■■■■■■■■■ 배송관리 ■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 미배송리스트");
			System.out.println("\t 2. 배송 유/무 체크");
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrchk()) {
			case "1":
				deliveryList();
			case "2":
				view_salesList();
				System.out.print("배송번호를 입력하세요(※배송번호를 입력하면 배송유무의 값이 확인으로 바뀜)\n▶");
				int tempOrderNumber = intErrchk();
				sv.deliveryCheck(tempOrderNumber);
			case "#": // 뒤로가기
				admin();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				deliveryManagement();
			}
		}
	}


	private void deliveryList() {
		System.out.println(" ■■■■■■■■■■■■■■■■■ 배송관리 리스트 ■■■■■■■■■■■■■■■■■ ");
		while (true) {
			for (int index = 0; index < sv.sdb.salesList().size(); index++) {
				if (!sv.sdb.salesList().get(index).isDelivery()) {
					view_salesList();
					break;
				}
				System.out.println("배송리스트에 목록이 없습니다.");
			}
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrchk()) {
			case "1":
				deliveryList();
			case "#": // 뒤로가기
				admin();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				deliveryManagement();
			}
		}		
	}
	
	
	

	// ------------------------------- 유저 로그인
	private void user() { // 유저 로그인 화면
		System.out.println(" ■■■■■■■■■■■■■■■■■ 환영합니다!! ■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 상품 주문");
			System.out.println("\t 2. 장바구니");
			System.out.println("\t 3. 마이 페이지");
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrchk()) {
			case "1":
				silhouette();
			case "2":
				cart();
			case "3":
				myPage();
			case "#": // 뒤로가기
				start();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				user();
			}
		}
	}

	// ------------------------------- 상품주문
	private void silhouette() {
		System.out.println(" ■■■■■■■■■■■■■■■■■ 실루엣 선택 ■■■■■■■■■■■■■■■■■ ");
		System.out.println(" ========== 나만의 신발 만들기 가장 첫 단계 ========== ");
		while (true) {
			System.out.println("\t 1. 런닝화");
			System.out.println("\t 2. 오리지널");
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrchk()) {
			case "1":
				getKind = 1;
				shoes_Style();
			case "2":
				getKind = 2;
				shoes_Style();
			case "#": // 뒤로가기
				user();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				silhouette();
			}
		}
	}

	private void shoes_Style() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 스타일 선택 ■■■■■■■■■■■■■■■■■■ ");
		System.out.println(" ========== 나만의 신발 만들기 두 번째 단계 ========== ");
		while (true) {
			if (getKind == 1) {
				System.out.println("\t 1. 슈퍼노바");
				System.out.println("\t 2. 아디오스 3");
				System.out.println("\t 3. 퓨어부스트");
				System.out.print("\t #. 뒤로가기\n▶");
				switch (strErrchk()) {
				case "1":
					getKind2 = 1;
					System.out.println("슈퍼 노바를 선택하셨습니다.");
					sv.shoesStyleDuplChk(0);
					shoes_parts();
				case "2":
					getKind2 = 2;
					System.out.println("아디오스 3를 선택하셨습니다.");
					sv.shoesStyleDuplChk(1);
					shoes_parts();
				case "3":
					getKind2 = 3;
					System.out.println("퓨어부스트를 선택하셨습니다.");
					sv.shoesStyleDuplChk(2);
					shoes_parts();				
				case "#": // 뒤로가기
					silhouette();
				default:
					System.out.println("입력이 잘못 되었습니다.");
					shoes_Style();
				}
			}
			if(getKind ==2)
			{
				System.out.println("\t 1. 스위프트런");
				System.out.println("\t 2. 가젤");
				System.out.println("\t 3. 슈퍼스타");
				System.out.print("\t #. 뒤로가기\n▶");
				switch (strErrchk()) {
				case "1":
					getKind2 = 1;
					System.out.println("스위프트런을 선택하셨습니다.");
					//sv.addLast_cart(3);
					sv.shoesStyleDuplChk(3);
					shoes_parts();
				case "2":
					getKind2 = 2;
					System.out.println("가젤을 선택하셨습니다.");
					sv.shoesStyleDuplChk(4);
					shoes_parts();
				case "3":
					getKind2 = 3;
					System.out.println("슈퍼스타를 선택하셨습니다.");
					sv.shoesStyleDuplChk(5);
					shoes_parts();				
				case "#": // 뒤로가기
					silhouette();
				default:
					System.out.println("입력이 잘못 되었습니다.");
					shoes_Style();
				}
			}
		}
	}
	
	private void shoes_parts() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 세부 부품 선택 ■■■■■■■■■■■■■■■■■■ ");
		System.out.println(" ========== 나만의 신발 만들기 세 번째 단계 ========== ");
		while (true) {
			System.out.println("\t 1. 메인");
			System.out.println("\t 2. TOP");
			System.out.println("\t 3. Sole");
			System.out.println("\t 4. Size");
			System.out.print("\t #. 뒤로가기\n▶");
			int temp;
			String re_List;
			re_List = strErrChk_next();
			switch (re_List) {
			case "1":
				getKind3 = 1;
				shoes_main();
				break;
			case "2":
				getKind3 = 2;
				shoes_top();
				break;
			case "3":
				getKind3 = 3;
				shoes_sole();
				break;
			case "4":
				getKind3 = 4;
				getKind4 = 0;
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				break;
			case "#": // 뒤로가기
				shoes_Style();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				shoes_parts();
			}
		}
	}
	
	private void shoes_main() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 메인 옵션 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 바디 선택");
			System.out.println("\t 2. 안감 선택");
			System.out.println("\t 3. 끈 선택");
			System.out.println("\t 4. 쿼터패널 선택");
			System.out.println("\t 5. 힐 카운터 선택");
			System.out.print("\t #. 뒤로가기\n▶");
			int temp;
			String re_List;
			re_List = strErrChk_next();
			switch (re_List) {
			case "1":
				getKind4 = 1;
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				shoes_main();
				break;
			case "2":
				getKind4 = 2;
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				shoes_main();
				break;
			case "3":
				getKind4 = 3;
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				shoes_main();
				break;
			case "4":
				getKind4 = 4;
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				shoes_main();
				break;
			case "5":
				getKind4 = 5;
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				shoes_main();
				break;
			case "#": // 뒤로가기
				shoes_parts();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				shoes_main();
			}
		}
	}

	private void shoes_top() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ Top 옵션 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 로고");
			System.out.println("\t 2. 토우캡");
			System.out.print("\t #. 뒤로가기\n▶");
			int temp;
			String re_List;
			re_List = strErrChk_next();
			switch (re_List) {
			case "1":
				getKind4 = 1;
				System.out
						.println(" ■■■■■■■■■■■■■■■■■■ 로고 ■■■■■■■■■■■■■■■■■■ ");
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				shoes_main();
				break;
			case "2":
				getKind4 = 2;
				System.out
						.println(" ■■■■■■■■■■■■■■■■■■ 토우캡 ■■■■■■■■■■■■■■■■■■ ");
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				shoes_main();
				break;
			case "#": // 뒤로가기
				shoes_parts();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				shoes_top();
			}
		}
	}
	
	
	private void shoes_sole() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ sole 옵션 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 미드솔");
			System.out.println("\t 2. 아웃솔");
			System.out.print("\t #. 뒤로가기\n▶");
			int temp;
			String re_List;
			re_List = strErrChk_next();
			switch (re_List) {
			case "1":
				getKind4 = 1;
				System.out
						.println(" ■■■■■■■■■■■■■■■■■■ 미드솔 ■■■■■■■■■■■■■■■■■■ ");
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				shoes_main();
				break;
			case "2":
				getKind4 = 2;
				System.out
						.println(" ■■■■■■■■■■■■■■■■■■ 아웃솔 ■■■■■■■■■■■■■■■■■■ ");
				view_Shoes();
				temp = intErrchk();
				re_List = outOfBound(temp);
				shoes_main();
				break;
			case "#": // 뒤로가기
				shoes_parts();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				shoes_sole();
			}
		}
	}
	// ------------------------------- 장바구니
	private void cart() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 장바구니 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			view_cart();
			System.out.println("\t 1. 다시 만들기");
			System.out.println("\t 2. 결제하기");
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrchk()) {
			case "1":
				sv.delete_cart();
				silhouette();
			case "2":
				payment();
			case "#": // 뒤로가기
				user();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				cart();
			}
		}
	}

	// ------------------------------- 결제
	private void payment() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 결제 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 카드로 결제하기");
			System.out.println("\t 2. 계좌이체로 결제하기");
			System.out.print("\t #. 뒤로가기\n▶");
			switch (strErrchk()) {
			case "1":
				view_recipt();
				sv.sign(account, orderList(), 1);
				System.out.println("결제가 완료되었습니다. 또 이용해주세요");
				user();
				break;
			case "2":
				view_recipt();
				sv.sign(account, orderList(), 2);
				System.out.println("결제가 완료되었습니다. 또 이용해주세요");
				
				user();
				break;
			case "#": // 뒤로가기
				user();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				payment();
			}
		}
	}

	// ------------------------------- 마이 페이지
	private void myPage() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 마이 페이지 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {

			System.out.println("\t 1. 개인정보 수정");
			System.out.println("\t 2. 문의 사항");
			System.out.println("\t 3. 주문 내역");
			System.out.println("\t 4. 탈퇴");
			System.out.print("\t #. 뒤로가기\n▶");
			String result = strErrchk();

			switch (result) {
			case "1":
				call_myPage(account);
				changeInfomation();
				myPage();
			case "2":
				message();

			case "3":
				user_orderList();

			case "4":
				sv.userUnactivated(account);
				System.out.println("지금까지 이용해주셔서 감사합니다");
				System.out.println();
				start();
				break;
			case "#": // 뒤로가기
				user();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				myPage();
			}
		}
	}

	// ------------------------------- 주문 내역
	private void user_orderList() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 주문 내역 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 주문 리스트");
			System.out.print("\t #. 뒤로가기\n▶");
			String result = strErrchk();

			switch (result) {
			case "1":
				view_UserOrder();
			case "#": // 뒤로가기
				myPage();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				user_orderList();
			}
		}
	}


	// ------------------------------- 문의 사항
	private void message() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 문의 사항 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 문의 사항 목록");
			System.out.println("\t 2. 문의 사항 작성");
			System.out.print("\t #. 뒤로가기\n▶");
			String result = strErrchk();

			switch (result) {
			case "1":
				messageList();
				break;
			case "2":
				System.out.print("문의 사항을 입력하세요\n▶");
				String write_message = strErrchk();
				sv.addLast_message(account, write_message);
				message();
				System.out.println();
				break;
			case "#": // 뒤로가기
				myPage();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				message();
			}
		}
	}

	// ------------------------------- 문의 사항 리스트
	private void messageList() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 문의 사항 리스트 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			if(sv.sdb.message().isEmpty()){
				System.out.println("현재까지 작성된 문의사항이 없습니다.");
				System.out.print("\t #. 뒤로가기\n▶");
			} else {
				view_user_message();
				System.out.print("\t #. 뒤로가기\n▶");
			}
			String result = strErrchk();				
			switch (result) {
			case "#": // 뒤로가기
				message();
			default:
				System.out.println("입력이 잘못 되었습니다.");
				messageList();
			}
		}
	}
	
	
	
	// ---------------------------개인정보 수정
	private void changeInfomation() {
		//비밀번호 수정
		String pwd;
		System.out.print("비밀번호를 수정 하실 경우 바꿀 비밀번호를 입력해 주세요.(변경하지 않을 경우 기존 비밀번호를 적어주세요.)\n▶");
		while (true) {
			pwd = strErrchk();
			String regEx01 = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{6,16}$";
			if (Pattern.matches(regEx01, pwd)) {
				System.out.println("사용할 수 있는 패스워드 입니다.");
				break;
			} else {
				System.out.println("패스워드: 특수문자[!@#$%^*+=-] 또는 숫자를 포함하여 8~16자리로 다시 입력하세요. ");
			}
		}
		//주소 수정
		System.out.print("주소를 수정 하실 경우 바꿀 주소를 입력해 주세요.(변경하지 않을 경우 기존 주소를 적어주세요.)\n▶");
		String address = strErrchk();
		//핸드폰번호 수정
		System.out.print("핸드폰번호를 수정 하실 경우 바꿀 핸드폰번호를 입력해 주세요.(변경하지 않을 경우 기존 핸드폰번호를 적어주세요.)\n▶");
		String cellPhone;
		while (true) {
			cellPhone = strErrchk();
			String regEx03 = "^01[01876]-[1-9][0-9]{2,3}-[0-9][0-9][0-9][0-9]$";
			if (Pattern.matches(regEx03, cellPhone)) {
				System.out.println("사용할 수 있는 전화번호 입니다.");
				break;
			} else {
				System.out.println("전화번호: 000-0000-0000 형식으로 다시 입력하세요. ");
			}
		}
		sv.update_userList(account, pwd, address, cellPhone, 0, "", false,true);
		System.out.println("개인정보 수정이 완료되었습니다.");
		System.out.println();
	}

	// ------------------------------- 유저 회원가입
	private void join() { // 회원가입
		while (true) {
			String account;
			System.out.println(" ■■■■■■■■■■■■■■■■■ 회원가입 ■■■■■■■■■■■■■■■■■ ");
			while (true) {
				System.out
						.print(" ================ 아이디(이메일)를 입력하세요.[영문자로 시작, 영(대,소문자) 또는 특수문자(_\\.-) 또는 숫자 조합] ================  \n▶");
				account = strErrchk();
				String regEx01 = "^[a-zA-Z]([-_\\\\.]*\\w)*@\\w{1,7}\\.[a-zA-Z]{2,3}(\\.[a-zA-Z]{2,3})?";
				if (Pattern.matches(regEx01, account)) {
					System.out.println("사용할 수 있는 아이디 입니다.");
					break;
				} else {
					System.out.println("아이디 : 영문자로 시작, 영(대,소문자) 또는 특수문자(_\\.-) 또는 숫자의 조합으로 다시 입력하세요.");
				}
			}

			String password;
			while (true) {
				System.out
						.print(" ================ 패스워드를 입력하세요.[특수문자[!@#$%^*+=-] 또는 숫자를 조합하여 8~16자리 ] ================  \n▶");
				password = strErrchk();
				String regEx02 = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{6,16}$";
				if (Pattern.matches(regEx02, password)) {
					System.out.println("사용할 수 있는 패스워드 입니다.");
					break;
				} else {
					System.out
							.println("패스워드: 특수문자[!@#$%^*+=-] 또는 숫자를 포함하여 8~16자리로 다시 입력하세요. ");
				}
			}
			System.out
					.print(" ================ 배송지를 입력하세요. ================  \n▶");
			String deliveryAddress = strErrchk();
			String cellphone;
			while (true) {
				System.out
						.print(" ================ 전화번호를 입력하세요. ================  \n▶");
				cellphone = strErrchk();
				String regEx03 = "^01[01876]-[1-9][0-9]{2,3}-[0-9][0-9][0-9][0-9]$";

				if (Pattern.matches(regEx03, cellphone)) {
					System.out.println("사용할 수 있는 전화번호 입니다.");
					break;
				} else {
					System.out.println("전화번호: 000-0000-0000 형식으로 다시 입력하세요. ");
				}
			}

			System.out.println("");
			System.out.println("============== 입력하신 내용이 맞습니까? ==============");
			System.out.println("아이디 	: " + account);
			System.out.println("비밀번호 	: " + password);
			System.out.println("배송지 	: " + deliveryAddress);
			System.out.println("전화번호	: " + cellphone);
			System.out.println("");
			System.out.println("위 내용이 옳다면...  ▶ 1. 가입하기, 다시 입력하기는 ▶ 2를 누르세요");
			System.out.println("\t 1. 가입하기");
			System.out.println("\t 2. 다시 입력하기");
			System.out.print("\t #. 뒤로가기\n▶");

			switch (strErrchk()) {
			case "1":
				sv.addLast_userList(account, password, deliveryAddress,
						cellphone, 0, purchaseGoods, false, true);
				login();
			case "2":
				break;
			case "#":
				start();
			default:
				System.out.println("입력이 잘못되었습니다.");
				break;
			}
		}
	}

	private void view_adminList() {
		for (int i = 0; i < sv.sdb.userList().size(); i++) {
			if (sv.sdb.userList().get(i).isAdmin()) {
				System.out.printf("|1.ID%11s|2.Account%9s|3.Password%8s|4.DeliveryAddress%9s|5.Cellphone%8s"
						+ "|6.Sales%9s|7.PurchaseGoods%9s|8.isAdmin%9s|9.isActivated%9s",
						(sv.sdb.userList().get(i).getUser_vo_id()),
						(sv.sdb.userList().get(i).getAccount()),
						(sv.sdb.userList().get(i).getPassword()),
						(sv.sdb.userList().get(i).getDeliveryAddress()),
						(sv.sdb.userList().get(i).getCellphone()),
						(sv.sdb.userList().get(i).getSales()),
						(sv.sdb.userList().get(i).isAdmin()),
						(sv.sdb.userList().get(i).isActivated())
						);
				System.out.println();
			}
		}
	}

	private void view_userList() {
		for (int i = 0; i < sv.sdb.userList().size(); i++) {
			System.out.println(" ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ "+sv.sdb.userList().get(i).getUser_vo_id() + " ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.printf("|1.ID%42s|2.Account%37s|3.Password%36s|4.DeliveryAddress%29s|5.Cellphone%35s"
					+ "|6.Sales%39s|7.isAdmin%37s|8.isActivated%32s",
					(sv.sdb.userList().get(i).getUser_vo_id())+"\n",
					(sv.sdb.userList().get(i).getAccount())+"\n",
					(sv.sdb.userList().get(i).getPassword())+"\n",
					(sv.sdb.userList().get(i).getDeliveryAddress())+"\n",
					(sv.sdb.userList().get(i).getCellphone())+"\n",
					(sv.sdb.userList().get(i).getSales())+"\n",
					(sv.sdb.userList().get(i).isAdmin())+"\n",
					(sv.sdb.userList().get(i).isActivated())
					);
			System.out.println();
		}
	}

	 private void view_salesList() {
	      for (int i = 0; i < sv.sdb.salesList().size(); i++) {
	         System.out.println("|1.OrderNumber     : "+sv.sdb.salesList().get(i).getOrderNumber());
	         System.out.println("|2.Account         : "+sv.sdb.salesList().get(i).getAccount());
	         System.out.println("|3.DeliveryAddress : "+sv.sdb.salesList().get(i).getDeliveryAddress());
	         System.out.println("|4.Price           : "+sv.sdb.stockList().get(i).getPrice());
	         System.out.println("|5.isDelivery      : "+sv.sdb.salesList().get(i).isDelivery());
	         System.out.println("|6.Date            : "+sv.sdb.salesList().get(i).getDate());
	         System.out.println("|7.CreditOfAccount : "+sv.sdb.salesList().get(i).getCreditOfAccount());
	         System.out.println("|8.PurchaseGoods   : "+sv.sdb.salesList().get(i).getPurchaseGoods()); 
	         System.out.println();
	      }
	   }

	private void view_stockList() {
		for (int i = 0; i < sv.sdb.stockList().size(); i++) {
			System.out.printf("|1.ID%11s|2.Name%9s|3.Amount%8s|4.Price%9s|5.Color%8s|4.Kind%9s",
					(sv.sdb.stockList().get(i).getStock_vo_id()),
					(sv.sdb.stockList().get(i).getName()),
					(sv.sdb.stockList().get(i).getAmount()),
					(sv.sdb.stockList().get(i).getPrice()),
					(sv.sdb.stockList().get(i).getColor()),
					(sv.sdb.stockList().get(i).getKind())
					);
			System.out.println();
		}
	}
	
	
/**
 * 선택한 분류의 아이템과 같은 분류의 아이템이 DB에 있는지 체크하고, 존재한다면 [같은 분류]의 아이템리스트 전부를 출력
 */
	private void view_Shoes() {// 필터링 기능
		for (int i = 0; i < sv.sdb.stockList().size(); i++) {
			int kind = sv.sdb.stockList().get(i).getKind();
			if (((kind / 1000 == this.getKind)
					&& (kind / 100 - this.getKind * 10 == this.getKind2)
					&& (kind / 10 - this.getKind * 100 - this.getKind2 * 10 == this.getKind3) && (kind % 10 == this.getKind4))
					|| ((kind / 1000 == 3)
							&& (kind / 100 - 30 == this.getKind2)
							&& (kind / 10 - 300 - this.getKind2 * 10 == this.getKind3) && (kind % 10 == this.getKind4))) {
				System.out.printf("|1.ID%11s|2.Name%9s|3.Amount%8s|4.Price%9s|5.Color%8s|6.Kind%9s",
						(sv.sdb.stockList().get(i).getStock_vo_id()),
						(sv.sdb.stockList().get(i).getName()),
						(sv.sdb.stockList().get(i).getAmount()),
						(sv.sdb.stockList().get(i).getPrice()),
						(sv.sdb.stockList().get(i).getColor()),
						(kind)
						);
				System.out.println();
			} 			
		}
		System.out.println("ID에 있는 번호를 입력해주세요!!!");
		System.out.print("\n▶");
	}

	private void view_cart() { // 카트리스트 전부 출력
		for (int i = 0; i < sv.sdb.cart().size(); i++) {
			System.out.printf("|1.ID%11s|2.Name%9s|3.Price%9s",
					(sv.sdb.cart().get(i).getCart_vo_id()),
					(sv.sdb.cart().get(i).getName()),
					(sv.sdb.cart().get(i).getPrice())
					);
			System.out.println();
		}
		System.out.println("총 금액은 : " + sv.cart_price_add());
	}
	
//	영수증
	private void view_recipt()
	{
		System.out.println("========================================");
		System.out.println("                 영수증                                ");
		System.out.println("========================================");
		view_cart();
		System.out.println("========================================");
	}
	
   private void view_message() { //메시지 전부출력(관리자)
      for (int i = 0; i < sv.sdb.message().size(); i++) {
         System.out.println("|1.Account  : "+sv.sdb.message().get(i).getAccount());
         System.out.println("|2.Contents : \n"+"☞"+sv.sdb.message().get(i).getContents());
         System.out.println("|3.Date     : "+sv.sdb.message().get(i).getDate());
         System.out.println();
      }
   }

   private void view_user_message() {//자신이 보낸 메시지 출력
      for (int index = 0; index < sv.sdb.userList().size(); index++) {
         for (int i = 0; i < sv.sdb.message().size(); i++) {
            if (sv.sdb.userList().get(index).getAccount().equals(account)) {
               System.out.println("|1.Account  : "+sv.sdb.message().get(i).getAccount());
               System.out.println("|2.Contents : \n"+"☞"+sv.sdb.message().get(i).getContents());
               System.out.println("|3.Date     : "+sv.sdb.message().get(i).getDate());
               System.out.println();
            }
         }
      }
   }

	public void call_myPage(String account) {//자신의 정보 출력
		for (int i = 0; i < sv.sdb.userList().size(); i++) {
			if (sv.sdb.userList().get(i).getAccount().equals(account)) {
				System.out.print("Account : "+sv.sdb.userList().get(i).getAccount()+"||");
				System.out.print("Password : "+sv.sdb.userList().get(i).getPassword()+"||");
				System.out.print("DeliveryAddress : "+sv.sdb.userList().get(i).getDeliveryAddress()+"||");
				System.out.print("Cellphone : "+sv.sdb.userList().get(i).getCellphone()+"||");
				System.out.print("Sales : "+sv.sdb.userList().get(i).getSales()+"||");
				System.out.println();
			}
		}
	}
	//선택한 상품의 id범위 밖의 물건을 고를 경우 및 기존에 선택한 아이템과 같은 분류의 아이템을 고른경우를 체크합니다.
	private String outOfBound(int temp) {
		int tt = else_check(temp);
			if ( tt == 0) {
				sv.addLast_cart(temp);
				return null;
			}
			else if(tt == -1)
			{
				return null;
			}
			
			view_Shoes();
			return outOfBound(intErrchk());
	}
	//선택한 아이템의 아이디를 받아서 
	private int else_check(int kind){
		for (int i = 0; i < sv.sdb.stockList().size(); i++) {
			if (sv.sdb.stockList().get(i).getStock_vo_id()==kind) {
				if(sv.sdb.stockList().get(i).getKind()==(getKind*1000+getKind2*100+getKind3*10+getKind4)|sv.sdb.stockList().get(i).getKind()==(3000+getKind2*100+getKind3*10+getKind4)){
					for (int index = 0; index < sv.sdb.cart().size(); index++) {
						//kind값이 같은 것이 있는지 체크 후 변경
						if (sv.sdb.cart().get(index).getName().equals(sv.sdb.stockList().get(i).getName())) {
							sv.update_cart(index, sv.sdb.stockList().get(i).getStock_vo_id(), sv.sdb.stockList().get(i).getName(), sv.sdb.stockList().get(i).getPrice(), 0);
							System.out.println("기존 선택한 아이템에서 변경 되었습니다.");
							return -1;
						}
					}
					//kind값이 다르고 id범위 안일경우 카트에 새로운 아이템 추가 
					return 0;
				}
			}
		}
		//id범위를 벗어난 경우
		System.out.println("목록에 있는 상품을 골라주세요.");
		
		return -2;
	}
	
	public List<String> orderList() {
		List<String> ol = new ArrayList<String>();
		for (int i = 0; i < sv.sdb.cart().size(); i++) {
			ol.add(sv.sdb.cart().get(i).getName());
		}
		return ol;
	}
	
	public void view_UserOrder(){
		for (int index = 0; index < sv.sdb.salesList().size(); index++) {
			for (int i = 0; i < sv.sdb.userList().size(); i++) {
				if(account.equals(sv.sdb.userList().get(i).getAccount())){
			         System.out.println("|1.OrderNumber     : "+sv.sdb.salesList().get(index).getOrderNumber());
			         System.out.println("|2.Account         : "+sv.sdb.salesList().get(index).getAccount());
			         System.out.println("|3.DeliveryAddress : "+sv.sdb.salesList().get(index).getDeliveryAddress());
			         System.out.println("|4.Price           : "+sv.sdb.stockList().get(index).getPrice());
			         System.out.println("|5.isDelivery      : "+sv.sdb.salesList().get(index).isDelivery());
			         System.out.println("|6.Date            : "+sv.sdb.salesList().get(index).getDate());
			         System.out.println("|7.CreditOfAccount : "+sv.sdb.salesList().get(index).getCreditOfAccount());
			         System.out.println("|8.PurchaseGoods   : "+sv.sdb.salesList().get(index).getPurchaseGoods()); 
			         System.out.println();
				}
			}
		}
		
	}
	
	
}

