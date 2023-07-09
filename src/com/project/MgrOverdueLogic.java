package com.project;

import java.util.List;

import com.vo.OverdueVO;
import com.vo.RentalMasterVO;

public class MgrOverdueLogic {
	MgrOverdueDao mOverDao = new MgrOverdueDao();

	public List<OverdueVO> getOverList() {// ��ü�����ֱ�
		System.out.println("MgrOVERLogic-getrentListȣ�⼺��");
		List<OverdueVO> overList = null;
		overList = mOverDao.getOverList();
		return overList;
	}

/////////////////////////////////////////////////////////////////
	public List<OverdueVO> getOverSearch(OverdueVO ovVO) {// Search �˻����
		System.out.println("MgrOverdueLogic-getOverSearchȣ�⼺��");
		List<OverdueVO> overList = null;
		System.out.println("MgrOverdueLogic" + ovVO.getSearch());
		System.out.println("MgrOverdueLogic" + ovVO.getKeyword());
		overList = mOverDao.getoverSearch(ovVO);
		return overList;
	}

///////////////////////////////////////////////////////////
	public int payComplete(int p_detailid) { // paycomplete���οϷ�
		System.out.println("MgrOverdueLogic-payCompleteȣ�⼺��");
		int result = 0;
		result = mOverDao.paycomplete(p_detailid);
		return result;
	}

///////////////////////////////////////////////////////////
	public void overdelete(int p_detailid) { // delete �����Ϸ�
		System.out.println("MgrOverdueLogic-overdeleteȣ�⼺��");
		mOverDao.overdelete(p_detailid);
	}


}
