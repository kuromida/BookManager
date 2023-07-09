package com.project;

import java.util.List;

import com.vo.MemberVO;
import com.vo.RentalMasterVO;

public class MgrRentLogic {
	MgrRentDao mRentDao = new MgrRentDao();

	public List<RentalMasterVO> getRentList() {// 회원보여주기
		System.out.println("MgrRentLogic-getrentList호출성공");
		List<RentalMasterVO> rentList = null;
		rentList = mRentDao.getRentList();
		return rentList;
	}

/////////////////////////////////////////////////////////////////
	public List<RentalMasterVO> getRentSearch(RentalMasterVO prmVO) {// Search 검색기능
		System.out.println("MgrBookLogic-getRentSearch호출성공");
		List<RentalMasterVO> rentList = null;
		System.out.println("MgrRentLogic" + prmVO.getSearch());
		System.out.println("MgrRentLogic" + prmVO.getKeyword());
		rentList = mRentDao.getrentSearch(prmVO);
		return rentList;
	}

	public int rentDelete(int rentno, int detailId, int num) {
		System.out.println("MgrRentLogic - rentDelete호출 성공");
		int result = 0;
		result = mRentDao.rentDelete(rentno, detailId, num);
		return result;
	}

	// String memid, int rent_count
	public int rentInsert(String memid, int rent_count) {
		System.out.println("MgrRentLogic - rentInsert호출 성공");
		int result = 0;
		result = mRentDao.rentInsert(memid, rent_count);
		return result;
	}

	// int detailId, String isbn
	public int rentDetailInsert(int detailId, String isbn) {
		System.out.println("MgrRentLogic - rentDetailInsert호출 성공");
		int result = 0;
		result = mRentDao.rentDetailInsert(detailId, isbn);
		return result;
	}

	//////////////////////////////////////////////////////////////
	public List<RentalMasterVO> getmyRentList(String memid) {// 나의 연체보기
		System.out.println("MgrRentLogic-getmyrentList호출성공");
		List<RentalMasterVO> myrentlist = null;
		myrentlist = mRentDao.getmyRentList(memid);
		return myrentlist;
	}

	///////////////////////////////////////////////////////////
	public int returnComplete(int p_detailid) { // returncomplete반납완료
		System.out.println("MgrRentLogic-returnComplete호출성공");
		int result = 0;
		result = mRentDao.returncomplete(p_detailid);
		return result;
	}

}
