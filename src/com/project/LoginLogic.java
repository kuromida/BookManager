package com.project;

import com.vo.MemberVO;

public class LoginLogic {
	//��ü����
	LoginDao loginDao = new LoginDao();
	
	//MemberVO mvo,LoginApp loginApp
	
	public String[] loginfunc(MemberVO pmvo) {
		System.out.println("LoginLoginc-loginfuncȣ��");
		String[] results =null;
		results = loginDao.loginfunc(pmvo);
		return results;
		
	}
	
	public int memberRegister(String memberIns[]) { //register
		System.out.println("LoginLogic-memberregisterȣ�⼺��");
		int result =0;
		result = loginDao.memberRegister(memberIns);
		return result;
	}
	
	public int reg_idcheck(String memid) { //register - idüũ
		System.out.println("LoginLogic-memberregisterȣ�⼺��");
		int result = 0;
		result = loginDao.reg_idcheck(memid);
		return result;
	}
///////////////////////////////////////////////////////////
	public void refresh() { // ��������
		System.out.println("LoginLogic-refreshȣ�⼺��");
		loginDao.refresh();
}}
