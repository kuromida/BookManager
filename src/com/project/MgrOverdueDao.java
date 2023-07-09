package com.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.vo.BookVO;
import com.vo.MemberVO;
import com.vo.OverdueVO;
import com.vo.RentalDetailVO;
import com.vo.RentalMasterVO;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class MgrOverdueDao {
	Connection con = null;// ���������� ������ �ִ� ����Ŭ ������ ����
	PreparedStatement pstmt = null;
	CallableStatement cstmt = null;// ���ν��� ȣ���� ���� �ʿ�
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	EasyDBTest dbTest = new EasyDBTest();
	// �������� �����

	// ��ü ����Ʈ �̱�
	// �󼼹�ȣ, ȸ���̸�, ���̵�, ISBN, ������, �ݳ�������, ��ü�ϼ�, ��ü��, ������Ȳ
	public List<OverdueVO> getOverList() {
		List<OverdueVO> overList = new ArrayList<OverdueVO>();

		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_overdue(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// ����
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(1);
			OverdueVO odvo = null;
			RentalMasterVO rmvo = null;
			BookVO bvo = null;
			MemberVO mvo = null;
			RentalDetailVO rdvo = null;

			int detailId = 0;
			String mname = null;
			String memid = null;
			String isbn = null;
			String bname = null;
			String returndate = null;
			while (cursor.next()) {

				mvo = new MemberVO();// ȸ��
				bvo = new BookVO();// ����
				rmvo = new RentalMasterVO();// �뿩������
				rdvo = new RentalDetailVO();// �뿩 ������

				rdvo.setDetailid(cursor.getInt(1));
				mvo.setMname(cursor.getString(2));
				mvo.setMemid(cursor.getString(3));
				bvo.setIsbn(cursor.getString(4));
				bvo.setBname(cursor.getString(5));
				rmvo.setReturndate(cursor.getString(6));

				detailId = rdvo.getDetailid();
				mname = mvo.getMname();
				memid = mvo.getMemid();
				isbn = bvo.getIsbn();
				bname = bvo.getBname();
				returndate = rmvo.getReturndate();

				odvo = new OverdueVO(mvo, bvo, rmvo, rdvo, detailId, mname, memid, isbn, bname, returndate,
						cursor.getString(7), cursor.getString(8), cursor.getString(9));

				overList.add(odvo);
				System.out.println(odvo.getRdvo().getDetailid());
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
		}
		return overList;
	}

////////////////////////////////////////////////////////////////////////////
//������ -��ü ȭ�� �˻� Search
	public List<OverdueVO> getoverSearch(OverdueVO ovVO) {
		List<OverdueVO> overList = new ArrayList<OverdueVO>();
		System.out.println("MgrOverdueDao getoverSearch ȣ��");
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_overdue_CAT(?,?,?)}");

			String Search = ovVO.getSearch();
			String Keyword = ovVO.getKeyword();
			System.out.println("Search : " + Search + " Keyword : " + Keyword);

			cstmt.setString(1, Search);
			cstmt.setString(2, Keyword);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
//����
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(3);
			OverdueVO odvo = null;
			RentalMasterVO rmvo = null;
			BookVO bvo = null;
			MemberVO mvo = null;
			RentalDetailVO rdvo = null;

			int detailId = 0;
			String mname = null;
			String memid = null;
			String isbn = null;
			String bname = null;
			String returndate = null;
			while (cursor.next()) {

				mvo = new MemberVO();// ȸ��
				bvo = new BookVO();// ����
				rmvo = new RentalMasterVO();// �뿩������
				rdvo = new RentalDetailVO();// �뿩 ������

				rdvo.setDetailid(cursor.getInt(1));
				mvo.setMname(cursor.getString(2));
				mvo.setMemid(cursor.getString(3));
				bvo.setIsbn(cursor.getString(4));
				bvo.setBname(cursor.getString(5));
				rmvo.setReturndate(cursor.getString(6));

				detailId = rdvo.getDetailid();
				mname = mvo.getMname();
				memid = mvo.getMemid();
				isbn = bvo.getIsbn();
				bname = bvo.getBname();
				returndate = rmvo.getReturndate();

				odvo = new OverdueVO(mvo, bvo, rmvo, rdvo, detailId, mname, memid, isbn, bname, returndate,
						cursor.getString(7), cursor.getString(8), cursor.getString(9));

				overList.add(odvo);
				System.out.println(odvo.getRdvo().getDetailid());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return overList;
	}
	
/////////////////////////////////////////////////////////////////////////
//���οϷ��ư �޼ҵ�
	public int paycomplete(int p_detailid) {
		System.out.println("MgrOverdueDao - paycomplete ȣ��");
		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call paycomplete(?)}");
			cstmt.setInt(1, p_detailid);
			res = cstmt.executeUpdate();// ���� �� �Է�

		} catch (Exception e) {
//JOptionPane.showMessageDialog(jf_mngapp, e.toString()
//,"Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.toString());
		}
		return res;
	}

/////////////////////////////////////////////////////////////////////////
//��ü ���� �޼ҵ�
	public void overdelete(int p_detailid) {
		System.out.println("MgrOverdueDao - overdelete ȣ��");

		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_over_del(?)}");
			cstmt.setInt(1, p_detailid);
			cstmt.executeUpdate();

		} catch (Exception e) {

			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {

		MgrOverdueDao mgrDao = new MgrOverdueDao();
		mgrDao.getOverList();
	}

}
