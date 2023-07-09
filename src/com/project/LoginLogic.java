package com.project;

import com.vo.MemberVO;

public class LoginLogic {
	//객체주입
	LoginDao loginDao = new LoginDao();
	
	//MemberVO mvo,LoginApp loginApp
	
	public String[] loginfunc(MemberVO pmvo) {
		System.out.println("LoginLoginc-loginfunc호출");
		String[] results =null;
		results = loginDao.loginfunc(pmvo);
		return results;
		
	}
	
	public int memberRegister(String memberIns[]) { //register
		System.out.println("LoginLogic-memberregister호출성공");
		int result =0;
		result = loginDao.memberRegister(memberIns);
		return result;
	}
	
	public int reg_idcheck(String memid) { //register - id체크
		System.out.println("LoginLogic-memberregister호출성공");
		int result = 0;
		result = loginDao.reg_idcheck(memid);
		return result;
	}
///////////////////////////////////////////////////////////
	public void refresh() { // 리프레쉬
		System.out.println("LoginLogic-refresh호출성공");
		loginDao.refresh();
}}
