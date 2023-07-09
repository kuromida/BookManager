package com.project;

import com.vo.MemberVO;

public class LoginController {
	public final String _LOGIN ="login";
	public final String _REG = "register";
	public final String _IDCHECK = "idcheck";
	//객체주입
	LoginLogic loginLogic = new LoginLogic();
	
	public String[] getMemberData(MemberVO pmvo) {//MemberVO에 저장된 값 가져오기 
		 
		String[] memberInfo =new String[7];
		int i=0;
		memberInfo[i++]= pmvo.getMname();
		memberInfo[i++]= pmvo.getBirthday();
		memberInfo[i++]= pmvo.getTel();
		memberInfo[i++]= pmvo.getMemid();
		memberInfo[i++]= pmvo.getMempw();
		memberInfo[i++]= pmvo.getAddress();
		memberInfo[i++]= pmvo.getGender();
		for(int m=0; m<memberInfo.length;m++) {
			
			System.out.println(memberInfo[m]);
		}
		return memberInfo;
	}
	
	public MemberVO sendLogin(MemberVO pmvo) {
		MemberVO mvo= new MemberVO();
		if(_LOGIN.equals(pmvo.getCommand())) {//로그인
		
			System.out.println("LoginController-sendLogin호출");
			String[] results =null;
			results = loginLogic.loginfunc(pmvo);

			mvo.setMnager_user(results[0]);
			mvo.setMname(results[1]);
			mvo.setResult(Integer.parseInt(results[2]));
		
		}
		return mvo;
	}
		public MemberVO send(MemberVO pmvo) {
			MemberVO rmVO = new MemberVO();
		if(_REG.equals(pmvo.getCommand())) {//회원가입
			
			System.out.println("LoginController-send호출");
			int result =0;
			String memberInfo [] = getMemberData(pmvo);
			result = loginLogic.memberRegister(memberInfo);
			rmVO.setResult(result);
			
		}
		return rmVO;
	}
		
		public MemberVO sendidcheck(MemberVO pmvo, String memid) {
			MemberVO rmVO = new MemberVO();
		if(_IDCHECK.equals(pmvo.getCommand())) {//회원가입 아이디체크
			System.out.println("LoginController-sendidcheck호출");
			int result =0;
			result = loginLogic.reg_idcheck(memid);
			rmVO.setResult(result);
		}
		return rmVO;
	
			
		}
		//////////////////////////////리프레쉬 메소드
		public void refresh() {
			System.out.println("LoginController-refresh호출성공");
			loginLogic.refresh();
		}
}
