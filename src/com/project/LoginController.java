package com.project;

import com.vo.MemberVO;

public class LoginController {
	public final String _LOGIN ="login";
	public final String _REG = "register";
	public final String _IDCHECK = "idcheck";
	//��ü����
	LoginLogic loginLogic = new LoginLogic();
	
	public String[] getMemberData(MemberVO pmvo) {//MemberVO�� ����� �� �������� 
		 
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
		if(_LOGIN.equals(pmvo.getCommand())) {//�α���
		
			System.out.println("LoginController-sendLoginȣ��");
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
		if(_REG.equals(pmvo.getCommand())) {//ȸ������
			
			System.out.println("LoginController-sendȣ��");
			int result =0;
			String memberInfo [] = getMemberData(pmvo);
			result = loginLogic.memberRegister(memberInfo);
			rmVO.setResult(result);
			
		}
		return rmVO;
	}
		
		public MemberVO sendidcheck(MemberVO pmvo, String memid) {
			MemberVO rmVO = new MemberVO();
		if(_IDCHECK.equals(pmvo.getCommand())) {//ȸ������ ���̵�üũ
			System.out.println("LoginController-sendidcheckȣ��");
			int result =0;
			result = loginLogic.reg_idcheck(memid);
			rmVO.setResult(result);
		}
		return rmVO;
	
			
		}
		//////////////////////////////�������� �޼ҵ�
		public void refresh() {
			System.out.println("LoginController-refreshȣ�⼺��");
			loginLogic.refresh();
		}
}
