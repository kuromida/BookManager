package com.project;

import java.util.List;

import com.vo.MemberVO;
import com.vo.RentalMasterVO;

public class MgrRentController {
	public final String _SEA = "search";
	public final String _INS2 = "insert2";// detail테이블 insert를 위해서
	public final String _INS = "insert";
	public final String _UPD = "update";
	public final String _DEL = "delete";
	public final String _ALL = "select";
	// 객체주입
	MgrRentLogic rentLogic = new MgrRentLogic();

	// "대출번호", "회원번호" ,"회원이름","아이디","ISBN", "도서명"
	// , "대출일자","반납예정", "반납현황"
	/*
	 * public String[] getRentData(RentalMasterVO rmvo) {// VO에 저장된 값 가져오기
	 * 
	 * String[] rentInfo = new String[9]; int i = 0; rentInfo[i++] =
	 * Integer.toString(rmvo.getRentno()); rentInfo[i++] =
	 * Integer.toString(rmvo.getRdvo().getDetailid()); rentInfo[i++] =
	 * rmvo.getMvo().getMname(); rentInfo[i++] = rmvo.getMvo().getMemid();
	 * rentInfo[i++] = rmvo.getBvo().getIsbn(); rentInfo[i++] =
	 * rmvo.getBvo().getBname(); rentInfo[i++] = rmvo.getRentdate(); rentInfo[i++] =
	 * rmvo.getReturndate(); rentInfo[i++] = rmvo.getRdvo().getRental_now(); return
	 * rentInfo; }
	 */

	public RentalMasterVO send(RentalMasterVO prmVO, int num) {
		RentalMasterVO rrmVO = new RentalMasterVO();

		if (_DEL.equals(prmVO.getCommand())) {// 대여 삭제
			int result = 0;
			result = rentLogic.rentDelete(prmVO.getRentno(), prmVO.getRdvo().getDetailid(), num);
			System.out.println("삭제처리:" + result);
			rrmVO.setResult(result);
		} else if (_INS.equals(prmVO.getCommand())) {// 대여버튼 =>rentalMster삽입
			int result = 0;
			String memid = prmVO.getMvo().getMemid();
			int rent_count = prmVO.getRent_count();
			result = rentLogic.rentInsert(memid, rent_count);
			rrmVO.setResult(result);
		} else if (_INS2.equals(prmVO.getCommand())) {// 대여버튼 =>rentalDetail삽입
			int result = 0;
			int detailId = prmVO.getRdvo().getDetailid();
			String isbn = prmVO.getBvo().getIsbn();
			result = rentLogic.rentDetailInsert(detailId, isbn);
			rrmVO.setResult(result);
		}
		return rrmVO;

	}

////////////////////////////////////////////////////////
	public int send_returnComplete(int p_detailid) {// 반납완료
		System.out.println("RentController send_returnComplete호출 성공");
		int result = 0;
		result = rentLogic.returnComplete(p_detailid);
		return result;
	}

	/******************************************************
	 * 검색기능 처리구현
	 *******************************************************/
	public List<RentalMasterVO> sendSearch(RentalMasterVO prmVO) {
		System.out.println("RentController sendSearch 호출성공");
		List<RentalMasterVO> rentList = null;
		System.out.println("RentController" + prmVO.getSearch());
		System.out.println("RentController" + prmVO.getKeyword());
		rentList = rentLogic.getRentSearch(prmVO);
		return rentList;
	}

	/*
	 * 전체 조회 처리 구현
	 */
	public List<RentalMasterVO> send_rALL() {
		System.out.println("RentController send_rALL호출 성공");
		List<RentalMasterVO> rentList = null;
		rentList = rentLogic.getRentList();
		return rentList;
	}

	//////////////////////////////////////////////////////////////
	public List<RentalMasterVO> send_myrent(String memid) {//나의 대여 보기
		System.out.println("RentController send_myrent호출 성공");
		List<RentalMasterVO> myrentList = null;
		myrentList = rentLogic.getmyRentList(memid);
		return myrentList;
	}
	
}
