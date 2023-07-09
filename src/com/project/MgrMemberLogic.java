package com.project;

import java.util.List;

import com.vo.MemberVO;

public class MgrMemberLogic {
	MgrMemberDao memDao = new MgrMemberDao();
	
	public List<MemberVO> getMemberList(){//회원보여주기 
		System.out.println("MgrBookLogic-getmemberList호출성공");
		List<MemberVO> memberList = null;
		memberList = memDao.getMemberList();
		return memberList;
	}
	/////////////////////////////////////////////////////////////////
	public List<MemberVO> getMemberSearch(MemberVO pmVO){//Search 검색기능
		System.out.println("MgrBookLogic-getMemberSearch호출성공");
		List<MemberVO> memberList = null;
		System.out.println("MemberLogic"+pmVO.getSearch());
		System.out.println("MemberLogic"+pmVO.getKeyword());
		memberList = memDao.getMemberSearch(pmVO);
		return memberList;
	}
	public int memberUpdate(String[] memberUpd) { //회원 업데이트
		System.out.println("MgrMemberLogic-memberUpdate호출성공");
		int result =0;
		result =memDao.memeberUpdate(memberUpd);
		return result;
	}
	public int memberDelete(String memid,ManagerBookApp managerBookApp) {//delete
		
		System.out.println("MgrMemberLogic - memberDelete호출 성공");
		int result =0;
		result = memDao.memberDelete(memid,managerBookApp);
		return result;
 }
	public int memberInsert(String memberIns[]) { //insert
		System.out.println("MgrMemberLogic-memberInsert호출성공");
		int result =0;
		result = memDao.memberInsert(memberIns);
		return result;
	}
}
