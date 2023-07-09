package com.vo;

import java.util.Date;

public class RentalMasterVO {
	// �˻� ���ǿ� ����� ���� ����
	// ����ڰ� JComboBox���� ������ ���ڿ��� ���� ����
	private String search = null;// �̸�|���̵�|��ȭ��ȣ
	// ����ڰ� �Է��� Ű���带 ���� ����
	private String keyword = null;// ����ڰ� �Է��� ��

	private BookVO bvo = null;
	private MemberVO mvo = null;
	private RentalDetailVO rdvo = null;
	private OverdueVO      ovo  = null;
	
	private int result = 0;
	private String command = null;
	private int rentno = 0;// �����ȣ
	private String rentdate = null;// �������� => Date
	private int rent_count = 0; // �� ����Ǽ�
	private String returndate = null;// �ݳ�������
	// private String memno =mvo.getMemno();// ȸ����ȣ//(fk)

//�뿩���� ��ü �����ֱ� 	
//"�����ȣ", "ȸ����ȣ" ,"ȸ���̸�","���̵�","ISBN", "������", "��������",to_date(rm.rentdate)+7 "�ݳ�����", "�ݳ���Ȳ"

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
	//���ǵ�������
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

	// ���ν� �� VO�� ===========================================================
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
