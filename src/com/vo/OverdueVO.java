package com.vo;

public class OverdueVO {
	// �˻� ���ǿ� ����� ���� ����
		// ����ڰ� JComboBox���� ������ ���ڿ��� ���� ����
		private String search = null;// �̸�|���̵�|��ȭ��ȣ
		// ����ڰ� �Է��� Ű���带 ���� ����
		private String keyword = null;// ����ڰ� �Է��� ��
	
	//��ü����
    private BookVO 		    bvo = null;
    private MemberVO	    mvo = null;
    private RentalMasterVO rmvo = null;
    private RentalDetailVO rdvo = null;
	
	//
	int	   result			=0;
	String command			=null;
	//overdue ���̺� �÷�
	//int	   detailid         = 0;fk
	String overduecount     = null;
	String overduepay       = null;
	String overpay_now      = null;
	//�󼼹�ȣ, ȸ���̸�, ���̵�, ISBN, ������, �ݳ�������, ��ü�ϼ�, ��ü��, ������Ȳ
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
	public String getSearch() {// �˻������ ���� �߰�
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
