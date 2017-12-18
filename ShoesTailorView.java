package shoesTailor;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ShoesTailorView {
	private Scanner sc = new Scanner(System.in);
	private Scanner s = new Scanner(System.in);

	private int getKind = 0;
	private int getKind2 = 0;
	private int getKind3 = 0;
	private int getKind4 = 0;

	private String account;
	private String purchaseGoods; // TODO 필터와는 별개로 계속 구매 아이템을 넣어주어야 합니다.
	private ServiceImpl sv = new ServiceImpl();

	private int intErrchk() {
		int temp;
		try {
			temp = sc.nextInt();
		} catch (Exception e) {
			temp = 0;
		}
		return temp;
	}

	public void start() { // 시작 메인화면
		while (true) { // 시작 모드 선택
			System.out.println(" ■■■■■■■■■■■■■■■■■■■ 시작 ■■■■■■■■■■■■■■■■■■■ ");
			System.out.println("\t 1. 로그인");
			System.out.println("\t 2. 회원가입");
			System.out.println("\t 3. 종료");

			String result = sc.next(); // 공백안됨->공백제거, stackoverflow

			switch (result) {
			case "1": // 로그인
				login();
				break;

			case "2": // 회원가입
				join();
				break;

			case "3": // 종료
				System.out.println("시스템을 종료합니다.");
				System.exit(0);

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
			System.out.println("ID를 입력하세요 :");
			String result = sc.next();
			sc.nextLine();
			System.out.println("PW를 입력하세요 :");
			String result2 = sc.next();
			sc.nextLine();
			result = result.trim();
			result2 = result2.trim();

			if (sv.isLoginChk(result, result2)) {
				account = result;
				System.out.println("로그인 성공!");

				if (sv.loginType(result)) {
					admin();
				} else {
					user();
				}
			} else {
				System.out.println("다시 입력 해 주세요.");
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
			System.out.println("\t 5. 유저화면으로 이동");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1": // 상품관리
				stockManagement();
				break;

			case "2": // 매출관리
				salesManagement();
				break;

			case "3": // 회원관리
				memberManagement();
				break;

			case "4": // 배송관리
				deliveryManagement();
				break;

			case "5": // 유저관리
				user();
				break;

			case "#": // 뒤로가기
				login();

			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	// ------------------------------- 상품관리

	private void stockManagement() {
		System.out
				.println(" ■■■■■■■■■■■■■■■■■ stockManagement ■■■■■■■■■■■■■■■■■ ");
		while (true) {

			view_stockList();

			System.out.println("\t 1. 추가");
			System.out.println("\t 2. 삭제");
			System.out.println("\t 3. 수정");
			System.out.println("\t #. 뒤로가기");
			
			switch (sc.next()) {
			case "1":
				System.out.println("이름을 입력 해 주세요");
				String name = sc.next();
				System.out.println("수량을 입력 해 주세요"); // 0가능
				int amount = intErrchk();
				System.out.println("가격을 입력 해 주세요"); // 0가능
				int price = intErrchk();
				System.out.println("색상을 입력 해 주세요");
				String color = sc.next();
				System.out.println("종류를 입력 해 주세요");
				int kind = intErrchk();

				if (name == null || color == null || kind == 0) {
					stockManagement();
				} else {
					sv.addLast_stockList(name, amount, price, color, kind);
				}
				break;
			case "2":
				System.out.println("삭제 할 물품의 아이디를 입력 해 주세요.");
				int index = intErrchk();

				sv.delete_Stock(index);
				break;
			case "3":
				System.out.println("수정 할 물품의 아이디를 입력 해 주세요");
				int stock_vo_id = intErrchk();
				System.out
						.println("이름을 수정 하실경우에는 이름을 입력 해 주세요\n입력하지 않으려면 null을 입력 해 주세요.");
				String name_s = sc.next();
				System.out
						.println("수량을 수정 하실경우에는 수량을 입력 해 주세요.\n입력하지 않으려면 0을 입력 해 주세요.");
				int amount_s = intErrchk();
				System.out
						.println("가격을 수정 하실경우에는 가격을 입력 해 주세요.\n입력하지 않으려면 0을 입력 해 주세요.");
				int price_s = intErrchk();
				System.out
						.println("색상을 수정 하실경우에는 색상을 입력 해 주세요\n입력하지 않으려면 null을 입력 해 주세요.");
				String color_s = sc.next();
				System.out
						.println("종류를 수정 하실 경우에는 종류를 입력 해 주세요\n입력하지 않으려면 null을 입력 해 주세요.");
				int kind_s = intErrchk();
				sv.update_stockList(stock_vo_id, name_s, amount_s, price_s,
						color_s, kind_s);
				break;
			case "#":
				admin();

			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	// ------------------------------- 매출관리
	private void salesManagement() {
		System.out
				.println(" ■■■■■■■■■■■■■■■■■ salesManagement ■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();
			view_salesList();
			switch (result) {
			case "#": // 뒤로가기
				admin();
				break;
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	// ------------------------------- 회원관리
	private void memberManagement() {
		System.out
				.println(" ■■■■■■■■■■■■■■■■■ memberManagement ■■■■■■■■■■■■■■■■■ ");
		while (true) {
			view_userList();
			System.out.println("\t 1. 회원 활성화");
			System.out.println("\t 2. 관리자 추가");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1":
				System.out.println("비활성화 된 유저의 어카운트를 입력시 활성화 됩니다."
						+ "\n관리자는 비활성화 할 수 없습니다.");
				sv.userActivated(sc.next());
				break;
			case "2":
				view_adminList();
				System.out.println("관리자 아이디와 비밀번호를 입력 해 주세요 (스페이스 구분)");
				sv.add_admin(sc.next(), sc.next());
				break;
			case "#":
				admin();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	private void deliveryManagement() {
		System.out
				.println(" ■■■■■■■■■■■■■■■■■ deliveryManagement ■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 배송리스트");
			System.out.println("\t 2. 쪽지 확인");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1":
				view_salesList();
				break;
			case "2":
				view_message();
				break;
			case "#": // 뒤로가기
				admin();
			default:
				System.out.println("입력이 잘못 되었습니다.");
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
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1":
				silhouette();
				break;
			case "2":
				cart();
				break;
			case "3":
				myPage();
				break;
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
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1":
				getKind = 1;
				shoes_Style();
				break;
			case "2":
				getKind = 2;
				shoes_Style();
				break;
			case "#": // 뒤로가기
				user();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	private void shoes_main() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ Main 옵션 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 바디 선택");
			System.out.println("\t 2. 안감 선택");
			System.out.println("\t 3. 끈 선택");
			System.out.println("\t 4. 쿼터패널 선택");
			System.out.println("\t 5. 힐 카운터 선택");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();
			int temp;
			switch (result) {
			case "1":
				getKind4 = 1;
				view_Shoes(getKind, getKind2, getKind3, getKind4);
				temp = intErrchk();
				sv.addLast_cart(temp);
				break;
			case "2":
				getKind4 = 2;
				view_Shoes(getKind, getKind2, getKind3, getKind4);
				temp = intErrchk();
				sv.addLast_cart(temp);
				break;
			case "3":
				getKind4 = 3;
				view_Shoes(getKind, getKind2, getKind3, getKind4);
				temp = intErrchk();
				sv.addLast_cart(temp);
				break;
			case "4":
				getKind4 = 4;
				view_Shoes(getKind, getKind2, getKind3, getKind4);
				temp = intErrchk();
				sv.addLast_cart(temp);
				break;
			case "5":
				getKind4 = 5;
				view_Shoes(getKind, getKind2, getKind3, getKind4);
				temp = intErrchk();
				sv.addLast_cart(temp);
				break;
			case "#": // 뒤로가기
				silhouette();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	private void shoes_Style() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 스타일 선택 ■■■■■■■■■■■■■■■■■■ ");
		System.out.println(" ========== 나만의 신발 만들기 두 번째 단계 ========== ");
		while (true) {
			System.out.println("\t 1. 슈퍼노바");
			System.out.println("\t 2. 아디오스 3");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1":
				getKind2 = 1;
				System.out.println("슈퍼 노바를 선택하셨습니다.");
				shoes_parts();
				break;
			case "2":
				getKind3 = 2;
				System.out.println("아디오스 3를 선택하셨습니다.");
				shoes_parts();
				break;
			case "#": // 뒤로가기
				silhouette();
			default:
				System.out.println("입력이 잘못 되었습니다.");
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
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();
			int temp;
			switch (result) {
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
				getKind4 = 0;
				view_Shoes(getKind, getKind2, getKind3, getKind4);
				temp = intErrchk();
				sv.addLast_cart(temp);
				break;
			case "4":
				getKind3 = 4;
				view_Shoes(getKind, getKind2, getKind3, getKind4);
				temp = intErrchk();
				sv.addLast_cart(temp);
				break;
			case "#": // 뒤로가기
				silhouette();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	private void shoes_top() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ Top 옵션 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 로고");
			System.out.println("\t 2. 토우캡");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();
			int temp;
			switch (result) {
			case "1":
				getKind4 = 1;
				System.out
						.println(" ■■■■■■■■■■■■■■■■■■ 로고 ■■■■■■■■■■■■■■■■■■ ");
				view_Shoes(getKind, getKind2, getKind3, getKind4);
				temp = intErrchk();
				sv.addLast_cart(temp);
				break;
			case "2":
				getKind4 = 2;
				System.out
						.println(" ■■■■■■■■■■■■■■■■■■ 토우캡 ■■■■■■■■■■■■■■■■■■ ");
				view_Shoes(getKind, getKind2, getKind3, getKind4);
				temp = intErrchk();
				sv.addLast_cart(temp);
				break;
			case "#": // 뒤로가기
				silhouette();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	// ------------------------------- 장바구니
	private void cart() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 장바구니 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			view_cart();
			System.out.println("\t 1.  다시 만들기");
			System.out.println("\t 2. 결제하기");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1":
				reCustomize();
				break;
			case "2":
				payment();
				break;
			case "#": // 뒤로가기
				user();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	private void reCustomize() {

	}

	// ------------------------------- 결제
	private void payment() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 결제 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 카드로 결제하기");
			System.out.println("\t 2. 계좌이체로 결제하기");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1":
				// TODO 카드인경우 1
				sv.sign(account, purchaseGoods, 1);
				break;
			case "2":
				// 현금일경우 2
				sv.sign(account, purchaseGoods, 2);
				break;
			case "#": // 뒤로가기
				user();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}

	}

	// ------------------------------- 마이 페이지
	private void myPage() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 마이 페이지 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			call_myPage(account);
			System.out.println("\t 1. 개인정보 수정");
			System.out.println("\t 2. 문의 사항");
			System.out.println("\t 3. 탈퇴");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1":
				changeInfomation();
				break;
			case "2":
				message();
				break;
			case "3":
				sv.userUnactivated(account);
				break;
			case "#": // 뒤로가기
				user();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}

	}
	
	// ------------------------------- 문의 사항
	private void message() {
		System.out.println(" ■■■■■■■■■■■■■■■■■■ 문의 사항 ■■■■■■■■■■■■■■■■■■ ");
		while (true) {
			System.out.println("\t 1. 문의 사항 목록");
			System.out.println("\t 2. 문의 사항 작성");
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			switch (result) {
			case "1":
				view_user_message();
				break;
			case "2":
				System.out.println("문의 사항을 입력하세요");
				String write_message = sc.nextLine();
				write_message=write_message.trim();
				sv.update_message(account, write_message);
				break;
			case "#": // 뒤로가기
				myPage();
			default:
				System.out.println("입력이 잘못 되었습니다.");
			}
		}

	}

	
	private void changeInfomation() {

		System.out.println("비밀번호를 수정 하실 경우 바꿀 비밀번호를 입력 해 주세요.");
		String pwd = sc.next();
		System.out.println("주소를 수정 하실 경우 바꿀 주소를 입력 해 주세요.");
		String address = s.nextLine();
		System.out.println("핸드폰번호를 수정 하실 경우 바꿀 핸드폰번호를 입력 해 주세요.");
		String cellPhone = sc.next();

		sv.update_userList(account, pwd, address, cellPhone, 0, "", false, false);
	}

	// ------------------------------- 유저 회원가입
	private void join() { // 회원가입
		while (true) {
			String account;

			System.out.println(" ■■■■■■■■■■■■■■■■■ 회원가입 ■■■■■■■■■■■■■■■■■ ");
			while (true) {
				System.out
						.println(" ================ 아이디를 입력하세요. ================  ");
				account = sc.next();
				sc.nextLine();
				account = account.trim();
				String regEx01 = "^[a-zA-Z]{1}[a-zA-Z0-9_]{6,16}$"; // 영문자로 시작,
																	// 6~16자 사이
				if (Pattern.matches(regEx01, account)) {
					System.out.println("사용할 수 있는 아이디 입니다.");
					break;
				} else {
					System.out
							.println("아이디 : 영문자로 시작, 영문자 또는 숫자의 조합으로 (8~16자) 다시 입력하세요. ");
				}
			}

			String password;
			while (true) {
				System.out
						.println(" ================ 패스워드를 입력하세요. ================  ");
				password = sc.next();
				sc.nextLine();
				password = password.trim();
				String regEx02 = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{6,16}$"; // 영문자로
																						// 시작,
																						// 6~16자

				if (Pattern.matches(regEx02, password)) {
					System.out.println("사용할 수 있는 패스워드 입니다.");
					break;
				} else {
					System.out
							.println("패스워드: 특수문자[!@#$%^*+=-] 또는 숫자를 포함하여 8~16자리로 다시 입력하세요. ");
				}
			}
			System.out
					.println(" ================ 배송지를 입력하세요. ================  ");
			String deliveryAddress = sc.nextLine();
			deliveryAddress = deliveryAddress.trim();

			String cellphone;
			while (true) {
				System.out
						.println(" ================ 전화번호를 입력하세요. ================  ");
				cellphone = sc.next();
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
			System.out.println("\t #. 뒤로가기");
			String result = sc.next();

			if (result == "1") {
				sv.addLast_userList(account, password, deliveryAddress,
						cellphone, 0, result, false, true);
				login();
			} else if (result == "2") {
			} else if (result == "#") {
				start();
			} else {
				System.out.println("입력이 잘못 되었습니다.");
			}
		}
	}

	private void view_adminList() {
		for (int i = 0; i < sv.sdb.userList().size(); i++) {
			if (sv.sdb.userList().get(i).isAdmin()) {
				System.out.print(sv.sdb.userList().get(i).getUser_vo_id());
				System.out.print(sv.sdb.userList().get(i).getAccount());
				System.out.print(sv.sdb.userList().get(i).getPassword());
				System.out.print(sv.sdb.userList().get(i).getDeliveryAddress());
				System.out.print(sv.sdb.userList().get(i).getCellphone());
				System.out.print(sv.sdb.userList().get(i).getSales());
				System.out.print(sv.sdb.userList().get(i).getPurchaseGoods());
				System.out.print(sv.sdb.userList().get(i).isAdmin());
				System.out.print(sv.sdb.userList().get(i).isActivated());
			}
		}
	}

	private void view_userList() {
		for (int i = 0; i < sv.sdb.userList().size(); i++) {
			System.out.print(sv.sdb.userList().get(i).getUser_vo_id());
			System.out.print(sv.sdb.userList().get(i).getAccount());
			System.out.print(sv.sdb.userList().get(i).getPassword());
			System.out.print(sv.sdb.userList().get(i).getDeliveryAddress());
			System.out.print(sv.sdb.userList().get(i).getCellphone());
			System.out.print(sv.sdb.userList().get(i).getSales());
			System.out.print(sv.sdb.userList().get(i).getPurchaseGoods());
			System.out.print(sv.sdb.userList().get(i).isAdmin());
			System.out.print(sv.sdb.userList().get(i).isActivated());
			System.out.println();
		}
	}

	private void view_activateList() {// 활성화된 유저 리스트 보기
		for (int i = 0; i < sv.sdb.userList().size(); i++) {
			if (sv.sdb.userList().get(i).isActivated()) {
				System.out.print(sv.sdb.userList().get(i).getUser_vo_id());
				System.out.print(sv.sdb.userList().get(i).getAccount());
				System.out.print(sv.sdb.userList().get(i).getPassword());
				System.out.print(sv.sdb.userList().get(i).getDeliveryAddress());
				System.out.print(sv.sdb.userList().get(i).getCellphone());
				System.out.print(sv.sdb.userList().get(i).getSales());
				System.out.print(sv.sdb.userList().get(i).getPurchaseGoods());
				System.out.print(sv.sdb.userList().get(i).isAdmin());
				System.out.print(sv.sdb.userList().get(i).isActivated());
			}
		}
	}

	private void view_salesList() {
		for (int i = 0; i < sv.sdb.salesList().size(); i++) {
			System.out.print(sv.sdb.salesList().get(i).getDate());
			System.out.print(sv.sdb.salesList().get(i).getAccount());
			System.out.print(sv.sdb.salesList().get(i).getDeliveryAddress());
			System.out.print(sv.sdb.salesList().get(i).getPrice());
			System.out.print(sv.sdb.salesList().get(i).isDelivery());
			System.out.print(sv.sdb.salesList().get(i).getOrderNumber());
			System.out.print(sv.sdb.salesList().get(i).getCreditOfAccount());
			System.out.print(sv.sdb.salesList().get(i).getPurchaseGoods());
		}
	}

	private void view_stockList() {
		for (int i = 0; i < sv.sdb.stockList().size(); i++) {
			System.out.print(sv.sdb.stockList().get(i).getStock_vo_id());
			System.out.print(sv.sdb.stockList().get(i).getName());
			System.out.print(sv.sdb.stockList().get(i).getAmount());
			System.out.print(sv.sdb.stockList().get(i).getPrice());
			System.out.print(sv.sdb.stockList().get(i).getColor());
			System.out.print(sv.sdb.stockList().get(i).getKind());
			System.out.println();
		}
	}

	private void view_Shoes(int getKind, int getKind2, int getKind3,
			int getKind4) {

		for (int i = 0; i < sv.sdb.stockList().size(); i++) {
			int kind = sv.sdb.stockList().get(i).getKind();
			if ((kind / 1000 == getKind)
					&& (kind / 100 - getKind * 10 == getKind2)
					&& (kind / 10 - getKind * 100 - getKind2 * 10 == getKind3)
					&& (kind % 10 == getKind4)
					|| ((kind / 1000 == 3)
							&& (kind / 100 - getKind * 10 == getKind2)
							&& (kind / 10 - getKind * 100 - getKind2 * 10 == getKind3) && (kind % 10 == getKind4))) {
				System.out.print(sv.sdb.stockList().get(i).getStock_vo_id());
				System.out.print(sv.sdb.stockList().get(i).getName());
				System.out.print(sv.sdb.stockList().get(i).getAmount());
				System.out.print(sv.sdb.stockList().get(i).getPrice());
				System.out.print(sv.sdb.stockList().get(i).getColor());
				System.out.print(kind);
			}
		}
	}

	private void view_cart() {
		for (int i = 0; i < sv.sdb.cart().size(); i++) {
			System.out.print(sv.sdb.cart().get(i).getCart_vo_id());
			System.out.print(sv.sdb.cart().get(i).getName());
			System.out.print(sv.sdb.cart().get(i).getPrice());
			System.out.print(sv.sdb.cart().get(i).getOrderNumber());
			System.out.println();
		}

	}

	private void view_message() {
		for (int i = 0; i < sv.sdb.message().size(); i++) {
			System.out.print(sv.sdb.message().get(i).getAccount());
			System.out.print(sv.sdb.message().get(i).getContents());
			System.out.print(sv.sdb.message().get(i).getDate());
		}
	}

	private void view_user_message() {
		for (int i = 0; i < sv.sdb.message().size(); i++) {
			if (sv.sdb.userList().get(i).getAccount().equals(account)) {
				System.out.print(sv.sdb.message().get(i).getAccount());
				System.out.print(sv.sdb.message().get(i).getContents());
				System.out.print(sv.sdb.message().get(i).getDate());
			}
		}
	}

	public void call_myPage(String account) {
		for (int i = 0; i < sv.sdb.userList().size(); i++) {
			if (sv.sdb.userList().get(i).getAccount().equals(account)) {
				System.out.print(sv.sdb.userList().get(i).getAccount());
				System.out.print(sv.sdb.userList().get(i).getPassword());
				System.out.print(sv.sdb.userList().get(i).getDeliveryAddress());
				System.out.print(sv.sdb.userList().get(i).getCellphone());
				System.out.print(sv.sdb.userList().get(i).getSales());
				System.out.println();
			}
		}
	}

}
