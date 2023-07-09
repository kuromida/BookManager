package com.project;

import java.util.List;

import com.vo.OverdueVO;
import com.vo.RentalMasterVO;

public class MgrOverController {
	public final String _ALL = "select";
	public final String _SEA = "search";

	// 객체주입
	MgrOverdueLogic overLogic = new MgrOverdueLogic();

	public List<OverdueVO> send_ALL() {
		System.out.println("OverController send_ALL호출 성공");
		List<OverdueVO> overList = null;
		overList = overLogic.getOverList();
		return overList;
	}

	 /******************************************************
     * 검색기능 처리구현
     *******************************************************/
    public List<OverdueVO> sendSearch(OverdueVO ovVO) {
         System.out.println("OverController sendSearch 호출성공");
         List<OverdueVO> overList = null;
         System.out.println("OverController" + ovVO.getSearch());
         System.out.println("OverController" + ovVO.getKeyword());
         overList = overLogic.getOverSearch(ovVO);
         return overList;
    }

////////////////////////////////////////////////////////
	public int send_payComplete(int p_detailid) {// 납부완료
		System.out.println("MgrOverController send_payComplete호출 성공");
		int result = 0;
		result = overLogic.payComplete(p_detailid);
		return result;
	}

////////////////////////////////////////////////////////
	public void send_overdelete(int p_detailid) {// 삭제완료
		System.out.println("MgrOverController overdelete호출 성공");
		overLogic.overdelete(p_detailid);
	}
}
