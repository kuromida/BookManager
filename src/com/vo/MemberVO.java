package com.vo;

public class MemberVO {
	// �˻� ���ǿ� ����� ���� ����
	// ����ڰ� JComboBox���� ������ ���ڿ��� ���� ����
	private String search = null;// �̸�|���̵�|��ȭ��ȣ
	// ����ڰ� �Է��� Ű���带 ���� ����
	private String keyword = null;// ����ڰ� �Է��� ��

	private int result = 0;
	private String command = null;
	private String memno = null;
	private String mname = null;
	private String birthday = null;
	private String tel = null;
	private String memid = null;
	private String memid2 = null;// =>������Ʈ�� �ʿ��� id
	private String mempw = null;
	private String mnager_user = null;
	private String address = null;
	private String gender = null;

	public MemberVO() {

	}

	// �Է� insert������
	public MemberVO(String memno, String mname, String birthday, String tel, String memid, String mempw, String address,
			String gender, String mnager_user) {
		this.memno = memno;
		this.mname = mname;
		this.birthday = birthday;
		this.tel = tel;
		this.memid = memid;
		this.mempw = mempw;
		this.address = address;
		this.gender = gender;
		this.mnager_user = mnager_user;

	}

	// select��
	public MemberVO(String mname, String memid, String mempw, String tel, String address, String birthday,
			String gender) {
		this.mname = mname;
		this.memid = memid;
		this.mempw = mempw;
		this.tel = tel;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;
	}

	// ������Ʈó���� �ʿ��� ������
	public MemberVO(String mname, String memid, String memid2, String mempw, String tel, String address,
			String birthday, String gender) {

		this.mname = mname;
		this.memid = memid;// ����id
		this.memid2 = memid2;// ������ ?
		this.mempw = mempw;
		this.tel = tel;
		this.address = address;
		this.birthday = birthday;
		this.gender = gender;

	}

	public String getMemno() {
		return memno;
	}

	public void setMemno(String memno) {
		this.memno = memno;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getMempw() {
		return mempw;
	}

	public void setMempw(String mempw) {
		this.mempw = mempw;
	}

	public String getMnager_user() {
		return mnager_user;
	}

	public void setMnager_user(String mnager_user) {
		this.mnager_user = mnager_user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getMemid2() {
		return memid2;
	}

	public void setMemid2(String memid2) {
		this.memid2 = memid2;
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