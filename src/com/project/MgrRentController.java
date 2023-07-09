package com.project;

import java.util.List;

import com.vo.MemberVO;
import com.vo.RentalMasterVO;

public class MgrRentController {
	public final String _SEA = "search";
	public final String _INS2 = "insert2";// detail���̺� insert�� ���ؼ�
	public final String _INS = "insert";
	public final String _UPD = "update";
	public final String _DEL = "delete";
	public final String _ALL = "select";
	// ��ü����
	MgrRentLogic rentLogic = new MgrRentLogic();

	// "�����ȣ", "ȸ����ȣ" ,"ȸ���̸�","���̵�","ISBN", "������"
	// , "��������","�ݳ�����", "�ݳ���Ȳ"
	/*
	 * public String[] getRentData(RentalMasterVO rmvo) {// VO�� ����� �� ��������
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

		if (_DEL.equals(prmVO.getCommand())) {// �뿩 ����
			int result = 0;
			result = rentLogic.rentDelete(prmVO.getRentno(), prmVO.getRdvo().getDetailid(), num);
			System.out.println("����ó��:" + result);
			rrmVO.setResult(result);
		} else if (_INS.equals(prmVO.getCommand())) {// �뿩��ư =>rentalMster����
			int result = 0;
			String memid = prmVO.getMvo().getMemid();
			int rent_count = prmVO.getRent_count();
			result = rentLogic.rentInsert(memid, rent_count);
			rrmVO.setResult(result);
		} else if (_INS2.equals(prmVO.getCommand())) {// �뿩��ư =>rentalDetail����
			int result = 0;
			int detailId = prmVO.getRdvo().getDetailid();
			String isbn = prmVO.getBvo().getIsbn();
			result = rentLogic.rentDetailInsert(detailId, isbn);
			rrmVO.setResult(result);
		}
		return rrmVO;

	}

////////////////////////////////////////////////////////
	public int send_returnComplete(int p_detailid) {// �ݳ��Ϸ�
		System.out.println("RentController send_returnCompleteȣ�� ����");
		int result = 0;
		result = rentLogic.returnComplete(p_detailid);
		return result;
	}

	/******************************************************
	 * �˻���� ó������
	 *******************************************************/
	public List<RentalMasterVO> sendSearch(RentalMasterVO prmVO) {
		System.out.println("RentController sendSearch ȣ�⼺��");
		List<RentalMasterVO> rentList = null;
		System.out.println("RentController" + prmVO.getSearch());
		System.out.println("RentController" + prmVO.getKeyword());
		rentList = rentLogic.getRentSearch(prmVO);
		return rentList;
	}

	/*
	 * ��ü ��ȸ ó�� ����
	 */
	public List<RentalMasterVO> send_rALL() {
		System.out.println("RentController send_rALLȣ�� ����");
		List<RentalMasterVO> rentList = null;
		rentList = rentLogic.getRentList();
		return rentList;
	}

	//////////////////////////////////////////////////////////////
	public List<RentalMasterVO> send_myrent(String memid) {//���� �뿩 ����
		System.out.println("RentController send_myrentȣ�� ����");
		List<RentalMasterVO> myrentList = null;
		myrentList = rentLogic.getmyRentList(memid);
		return myrentList;
	}
	
}
