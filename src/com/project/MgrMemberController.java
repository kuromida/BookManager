package com.project;

import java.util.List;

import com.vo.MemberVO;

public class MgrMemberController {
	public final String _REG = "register";
	public final String _INS = "insert";
	public final String _UPD = "update";
	public final String _DEL = "delete";
	public final String _ALL = "select";
	// ��ü ������ ���� �ν��Ͻ�ȭ
	MgrMemberLogic memberLogic = new MgrMemberLogic();

	public String[] getMemberData(MemberVO pmVO) {// MemberVO�� ����� �� ��������

		String[] memberInfo = new String[8];
		int i = 0;
		memberInfo[i++] = pmVO.getMname();
		memberInfo[i++] = pmVO.getMemid();
		memberInfo[i++] = pmVO.getMemid2();
		memberInfo[i++] = pmVO.getMempw();
		memberInfo[i++] = pmVO.getTel();
		memberInfo[i++] = pmVO.getAddress();
		memberInfo[i++] = pmVO.getBirthday();
		memberInfo[i++] = pmVO.getGender();
		return memberInfo;
	}
	public String[] getMembersData(MemberVO pmvo) {//MemberVO�� ����� �� �������� 
		
		String[] memberInfo =new String[8];
		int i=0;
		memberInfo[i++]= pmvo.getMname();
		memberInfo[i++]= pmvo.getBirthday();
		memberInfo[i++]= pmvo.getTel();
		memberInfo[i++]= pmvo.getMemid();
		memberInfo[i++]= pmvo.getMempw();
		memberInfo[i++]= pmvo.getAddress();
		memberInfo[i++]= pmvo.getGender();
		memberInfo[i++]= pmvo.getMnager_user();
		
		return memberInfo;
	}
	
	public MemberVO send(MemberVO pmvo,ManagerBookApp mgrBookApp) {
		MemberVO rmVO = new MemberVO();
		
			if(_DEL.equals(pmvo.getCommand())) {//�����Ҷ�
				System.out.println("MgerMemberController-send - deleteȣ��");
			int result =0;
			result = memberLogic.memberDelete(pmvo.getMemid(),mgrBookApp);
			rmVO.setResult(result);
			
			
		}
			else if(_INS.equals(pmvo.getCommand())) {//�Է��Ҷ�
				
				System.out.println("MgerMemberController-send - insertȣ��");
				int result =0;
				String memberInfo [] = getMembersData(pmvo);
				result = memberLogic.memberInsert(memberInfo);
				rmVO.setResult(result);
			}
				else	if (_UPD.equals(pmvo.getCommand())) {//������Ʈ �Ҷ� 
			int result = 0;
			String memberInfo[] = getMemberData(pmvo);

			result = memberLogic.memberUpdate(memberInfo);
			rmVO.setResult(result);

		}
		return rmVO;

	}
	/*
	 * �˻���� ó������
	 */
	public List<MemberVO> sendSearch(MemberVO pmVO) {
		System.out.println("MgrMemberController sendSearch ȣ�⼺��");
		List<MemberVO> memberList = null;
		System.out.println("MgrMemberController"+pmVO.getSearch());
		System.out.println("MgrMemberController"+pmVO.getKeyword());
		 memberList= memberLogic.getMemberSearch(pmVO);
		return memberList;
	}

	/*
	 * ��ü ��ȸ ó�� ����
	 */
	public List<MemberVO> send_mALL() {
		System.out.println("BookController send_mALLȣ�� ����");
		List<MemberVO> memberList = null;
		memberList = memberLogic.getMemberList();
		return memberList;
	}
}
