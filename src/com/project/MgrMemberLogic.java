package com.project;

import java.util.List;

import com.vo.MemberVO;

public class MgrMemberLogic {
	MgrMemberDao memDao = new MgrMemberDao();
	
	public List<MemberVO> getMemberList(){//ȸ�������ֱ� 
		System.out.println("MgrBookLogic-getmemberListȣ�⼺��");
		List<MemberVO> memberList = null;
		memberList = memDao.getMemberList();
		return memberList;
	}
	/////////////////////////////////////////////////////////////////
	public List<MemberVO> getMemberSearch(MemberVO pmVO){//Search �˻����
		System.out.println("MgrBookLogic-getMemberSearchȣ�⼺��");
		List<MemberVO> memberList = null;
		System.out.println("MemberLogic"+pmVO.getSearch());
		System.out.println("MemberLogic"+pmVO.getKeyword());
		memberList = memDao.getMemberSearch(pmVO);
		return memberList;
	}
	public int memberUpdate(String[] memberUpd) { //ȸ�� ������Ʈ
		System.out.println("MgrMemberLogic-memberUpdateȣ�⼺��");
		int result =0;
		result =memDao.memeberUpdate(memberUpd);
		return result;
	}
	public int memberDelete(String memid,ManagerBookApp managerBookApp) {//delete
		
		System.out.println("MgrMemberLogic - memberDeleteȣ�� ����");
		int result =0;
		result = memDao.memberDelete(memid,managerBookApp);
		return result;
 }
	public int memberInsert(String memberIns[]) { //insert
		System.out.println("MgrMemberLogic-memberInsertȣ�⼺��");
		int result =0;
		result = memDao.memberInsert(memberIns);
		return result;
	}
}
