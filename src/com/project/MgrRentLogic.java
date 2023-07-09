package com.project;

import java.util.List;

import com.vo.MemberVO;
import com.vo.RentalMasterVO;

public class MgrRentLogic {
	MgrRentDao mRentDao = new MgrRentDao();

	public List<RentalMasterVO> getRentList() {// ȸ�������ֱ�
		System.out.println("MgrRentLogic-getrentListȣ�⼺��");
		List<RentalMasterVO> rentList = null;
		rentList = mRentDao.getRentList();
		return rentList;
	}

/////////////////////////////////////////////////////////////////
	public List<RentalMasterVO> getRentSearch(RentalMasterVO prmVO) {// Search �˻����
		System.out.println("MgrBookLogic-getRentSearchȣ�⼺��");
		List<RentalMasterVO> rentList = null;
		System.out.println("MgrRentLogic" + prmVO.getSearch());
		System.out.println("MgrRentLogic" + prmVO.getKeyword());
		rentList = mRentDao.getrentSearch(prmVO);
		return rentList;
	}

	public int rentDelete(int rentno, int detailId, int num) {
		System.out.println("MgrRentLogic - rentDeleteȣ�� ����");
		int result = 0;
		result = mRentDao.rentDelete(rentno, detailId, num);
		return result;
	}

	// String memid, int rent_count
	public int rentInsert(String memid, int rent_count) {
		System.out.println("MgrRentLogic - rentInsertȣ�� ����");
		int result = 0;
		result = mRentDao.rentInsert(memid, rent_count);
		return result;
	}

	// int detailId, String isbn
	public int rentDetailInsert(int detailId, String isbn) {
		System.out.println("MgrRentLogic - rentDetailInsertȣ�� ����");
		int result = 0;
		result = mRentDao.rentDetailInsert(detailId, isbn);
		return result;
	}

	//////////////////////////////////////////////////////////////
	public List<RentalMasterVO> getmyRentList(String memid) {// ���� ��ü����
		System.out.println("MgrRentLogic-getmyrentListȣ�⼺��");
		List<RentalMasterVO> myrentlist = null;
		myrentlist = mRentDao.getmyRentList(memid);
		return myrentlist;
	}

	///////////////////////////////////////////////////////////
	public int returnComplete(int p_detailid) { // returncomplete�ݳ��Ϸ�
		System.out.println("MgrRentLogic-returnCompleteȣ�⼺��");
		int result = 0;
		result = mRentDao.returncomplete(p_detailid);
		return result;
	}

}
