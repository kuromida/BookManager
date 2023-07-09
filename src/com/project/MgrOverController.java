package com.project;

import java.util.List;

import com.vo.OverdueVO;
import com.vo.RentalMasterVO;

public class MgrOverController {
	public final String _ALL = "select";
	public final String _SEA = "search";

	// ��ü����
	MgrOverdueLogic overLogic = new MgrOverdueLogic();

	public List<OverdueVO> send_ALL() {
		System.out.println("OverController send_ALLȣ�� ����");
		List<OverdueVO> overList = null;
		overList = overLogic.getOverList();
		return overList;
	}

	 /******************************************************
     * �˻���� ó������
     *******************************************************/
    public List<OverdueVO> sendSearch(OverdueVO ovVO) {
         System.out.println("OverController sendSearch ȣ�⼺��");
         List<OverdueVO> overList = null;
         System.out.println("OverController" + ovVO.getSearch());
         System.out.println("OverController" + ovVO.getKeyword());
         overList = overLogic.getOverSearch(ovVO);
         return overList;
    }

////////////////////////////////////////////////////////
	public int send_payComplete(int p_detailid) {// ���οϷ�
		System.out.println("MgrOverController send_payCompleteȣ�� ����");
		int result = 0;
		result = overLogic.payComplete(p_detailid);
		return result;
	}

////////////////////////////////////////////////////////
	public void send_overdelete(int p_detailid) {// �����Ϸ�
		System.out.println("MgrOverController overdeleteȣ�� ����");
		overLogic.overdelete(p_detailid);
	}
}
