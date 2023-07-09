package com.project;

import java.util.List;

import com.vo.OverdueVO;
import com.vo.RentalMasterVO;

public class MgrOverdueLogic {
	MgrOverdueDao mOverDao = new MgrOverdueDao();

	public List<OverdueVO> getOverList() {// 연체보여주기
		System.out.println("MgrOVERLogic-getrentList호출성공");
		List<OverdueVO> overList = null;
		overList = mOverDao.getOverList();
		return overList;
	}

/////////////////////////////////////////////////////////////////
	public List<OverdueVO> getOverSearch(OverdueVO ovVO) {// Search 검색기능
		System.out.println("MgrOverdueLogic-getOverSearch호출성공");
		List<OverdueVO> overList = null;
		System.out.println("MgrOverdueLogic" + ovVO.getSearch());
		System.out.println("MgrOverdueLogic" + ovVO.getKeyword());
		overList = mOverDao.getoverSearch(ovVO);
		return overList;
	}

///////////////////////////////////////////////////////////
	public int payComplete(int p_detailid) { // paycomplete납부완료
		System.out.println("MgrOverdueLogic-payComplete호출성공");
		int result = 0;
		result = mOverDao.paycomplete(p_detailid);
		return result;
	}

///////////////////////////////////////////////////////////
	public void overdelete(int p_detailid) { // delete 삭제완료
		System.out.println("MgrOverdueLogic-overdelete호출성공");
		mOverDao.overdelete(p_detailid);
	}


}
