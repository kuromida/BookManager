package com.vo;

import java.util.Date;

public class RentalMasterVO {
	// 검색 조건에 사용할 변수 선언
	// 사용자가 JComboBox에서 선택한 문자열을 담을 변수
	private String search = null;// 이름|아이디|전화번호
	// 사용자가 입력한 키워드를 담을 변수
	private String keyword = null;// 사용자가 입력한 값

	private BookVO bvo = null;
	private MemberVO mvo = null;
	private RentalDetailVO rdvo = null;
	private OverdueVO      ovo  = null;
	
	private int result = 0;
	private String command = null;
	private int rentno = 0;// 대출번호
	private String rentdate = null;// 대출일자 => Date
	private int rent_count = 0; // 총 대출권수
	private String returndate = null;// 반납예정일
	// private String memno =mvo.getMemno();// 회원번호//(fk)

//대여관리 전체 보여주기 	
//"대출번호", "회원번호" ,"회원이름","아이디","ISBN", "도서명", "대출일자",to_date(rm.rentdate)+7 "반납예정", "반납현황"

	public RentalMasterVO(int rentno, RentalDetailVO rdvo, int detailId, MemberVO mvo, String mname, String memid,
			BookVO bvo, String isbn, String bname, String rentdate, String returndate, String rental_now) {

		this.rentno = rentno;
		this.rdvo = rdvo;
		this.rdvo.setDetailid(detailId);
		this.mvo = mvo;
		this.mvo.setMname(mname);
		this.mvo.setMemid(memid);
		this.bvo = bvo;
		this.bvo.setIsbn(isbn);
		this.bvo.setBname(bname);
		this.rentdate = rentdate;
		this.returndate = returndate;
		this.rdvo.setRental_now(rental_now);
	}
	//나의도서정보
			public RentalMasterVO (BookVO bvo, MemberVO mvo, RentalDetailVO rdvo, OverdueVO ovo
								 , String memid, String mname, String isbn 
								 , String bname,String rentdate, String rental_now
								  , String overduepay) {
				this.bvo = bvo;
				this.mvo = mvo;
				this.rdvo = rdvo;
				this.ovo  = ovo;
				this.mvo.setMemid(memid);
				this.mvo.setMname(mname);
				this.bvo.setIsbn(isbn);
				this.bvo.setBname(bname);
				this.rentdate = rentdate;
				this.rdvo.setRental_now(rental_now);
				this.ovo.setOverduepay(overduepay);
			}
			

	public RentalMasterVO() {
		// TODO Auto-generated constructor stub
	}

	public int getRentno() {
		return rentno;
	}

	public void setRentno(int rentno) {
		this.rentno = rentno;
	}

	public String getRentdate() {
		return rentdate;
	}

	public void setRentdate(String rentdate) {
		this.rentdate = rentdate;
	}

	public int getRent_count() {
		return rent_count;
	}

	public void setRent_count(int rent_count) {
		this.rent_count = rent_count;
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	/*
	 * public String getMemno() { return memno; } public void setMemno(String memno)
	 * { this.memno = memno; }
	 */

	// 조인시 쓸 VO들 ===========================================================
	public BookVO getBvo() {
		return bvo;
	}

	public void setBvo(BookVO bvo) {
		this.bvo = bvo;
	}

	public MemberVO getMvo() {
		return mvo;
	}

	public void setMvo(MemberVO mvo) {
		this.mvo = mvo;
	}

	public RentalDetailVO getRdvo() {
		return rdvo;
	}

	public void setRdvo(RentalDetailVO rdvo) {
		this.rdvo = rdvo;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	public OverdueVO getOvo() {
		return ovo;
	}
	public void setOvo(OverdueVO ovo) {
		this.ovo = ovo;
	}

///////////////////////////////////////////////////////////////////////////
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
