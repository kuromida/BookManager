package com.vo;

public class OverdueVO {
	// 검색 조건에 사용할 변수 선언
		// 사용자가 JComboBox에서 선택한 문자열을 담을 변수
		private String search = null;// 이름|아이디|전화번호
		// 사용자가 입력한 키워드를 담을 변수
		private String keyword = null;// 사용자가 입력한 값
	
	//객체주입
    private BookVO 		    bvo = null;
    private MemberVO	    mvo = null;
    private RentalMasterVO rmvo = null;
    private RentalDetailVO rdvo = null;
	
	//
	int	   result			=0;
	String command			=null;
	//overdue 테이블 컬럼
	//int	   detailid         = 0;fk
	String overduecount     = null;
	String overduepay       = null;
	String overpay_now      = null;
	//상세번호, 회원이름, 아이디, ISBN, 도서명, 반납예정일, 연체일수, 연체료, 납부현황
	public OverdueVO (){
		
	}
	public OverdueVO(MemberVO mvo, BookVO bvo, RentalMasterVO rmvo, RentalDetailVO rdvo
			, int detailId,String mname, String memid, String isbn, String bname, String returndate
			, String overduecount , String overduepay,String overpay_now ) 
	{
		this.mvo=mvo;
		this.bvo=bvo;
		this.rmvo =rmvo;
		this.rdvo =rdvo;
		this.rdvo.setDetailid(detailId);
		this.mvo.setMname(mname);
		this.mvo.setMemid(memid);
		this.bvo.setIsbn(isbn);
		this.bvo.setBname(bname);
		this.rmvo.setReturndate(returndate);
		this.overduecount =overduecount;
		this.overduepay =overduepay;
		this.overpay_now=overpay_now;
	}
	/*public int getDetailid() {
		return detailid;
	}
	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}*/
	public String getOverduecount() {
		return overduecount;
	}
	public void setOverduecount(String overduecount) {
		this.overduecount = overduecount;
	}
	public String getOverduepay() {
		return overduepay;
	}
	public void setOverduepay(String overduepay) {
		this.overduepay = overduepay;
	}
	public String getOverpay_now() {
		return overpay_now;
	}
	public void setOverpay_now(String overpay_now) {
		this.overpay_now = overpay_now;
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
	public RentalMasterVO getRmvo() {
		return rmvo;
	}
	public void setRmvo(RentalMasterVO rmvo) {
		this.rmvo = rmvo;
	}
	public RentalDetailVO getRdvo() {
		return rdvo;
	}
	public void setRdvo(RentalDetailVO rdvo) {
		this.rdvo = rdvo;
	}
	
///////////////////////////////////////////////////
	public String getSearch() {// 검색기능을 위해 추가
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
