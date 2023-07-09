package com.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sound.midi.Synthesizer;
import javax.swing.JOptionPane;

import com.vo.BookVO;
import com.vo.MemberVO;
import com.vo.OverdueVO;
import com.vo.RentalDetailVO;
import com.vo.RentalMasterVO;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class MgrRentDao {
	Connection con = null;// ���������� ������ �ִ� ����Ŭ ������ ����
	PreparedStatement pstmt = null;
	CallableStatement cstmt = null;// ���ν��� ȣ���� ���� �ʿ�
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	EasyDBTest dbTest = new EasyDBTest();
	// �������� �����
	int rentno;

	// ���� �뿩 ���� ��ü ����Ʈ �̾ƿ��� (RentalMaster�� Rentald)
	public List<RentalMasterVO> getRentList() {
		List<RentalMasterVO> rentList = new ArrayList<RentalMasterVO>();

		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rent(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// ����
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(1);
			RentalMasterVO rmvo = null;
			BookVO bvo = null;
			MemberVO mvo = null;
			RentalDetailVO rdvo = null;
			int detailId = 0;
			String mname = null;
			String memid = null;
			String isbn = null;
			String bname = null;
			String rental_now = null;
			while (cursor.next()) {

				mvo = new MemberVO();// ȸ��
				bvo = new BookVO();// ����
				rdvo = new RentalDetailVO();// �뿩 ������

				rdvo.setDetailid(cursor.getInt(2));
				mvo.setMname(cursor.getString(3));
				mvo.setMemid(cursor.getString(4));

				bvo.setIsbn(cursor.getString(5));
				bvo.setBname(cursor.getString(6));

				rdvo.setRental_now(cursor.getString(9));

				detailId = rdvo.getDetailid();
				mname = mvo.getMname();
				memid = mvo.getMemid();
				isbn = bvo.getIsbn();
				bname = bvo.getBname();
				rental_now = rdvo.getRental_now();

				rmvo = new RentalMasterVO(cursor.getInt(1), rdvo, detailId, mvo, mname, memid, bvo, isbn, bname,
						cursor.getDate(7).toString(), cursor.getDate(8).toString(), rental_now);

				//// "�����ȣ", "ȸ����ȣ" ,"ȸ���̸�","���̵�","ISBN", "������", "��������",to_date(rm.rentdate)+7
				//// "�ݳ�����", "�ݳ���Ȳ"

				rentList.add(rmvo);
				/*
				 * System.out.println(rmvo.getRentno()+" "+rdvo.getDetailid()+" "+rmvo.getMvo().
				 * getMname()
				 * +" "+rmvo.getMvo().getMemid()+" "+rmvo.getBvo().getIsbn()+" "+rmvo.getBvo().
				 * getBname()
				 * +" "+rmvo.getRentdate()+" "+rmvo.getReturndate()+" "+rmvo.getRdvo().
				 * getRental_now());
				 */
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
		}
		return rentList;
	}

	// ������ -rent �뿩 ���� delete ���� �̤ФФФФ�
	public int rentDelete(int rentno, int detailId, int num) {
		int result = 0;

		StringBuilder sql = new StringBuilder();
		if (num == 4) {
			sql.append("DELETE FROM rentalmarster");
			sql.append(" WHERE rentno = ?");
			// sql.append("COMMIT;");
		} else if (num == 3) {
			sql.append("DELETE FROM rentaldetail");
			sql.append(" WHERE detailid = ?");
			// sql.append("COMMIT;");
		} else if (num == 2) {
			sql.append("DELETE FROM overdue");
			sql.append(" WHERE detailid = ?");
			// sql.append("COMMIT;");
		} else if (num == 1) {
			sql.append("DELETE FROM return");
			sql.append(" WHERE detailid = ?");
			// sql.append("COMMIT;");
		}
		try {
			con = dbTest.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			if (num == 4) {
				pstmt.setInt(1, rentno);
			} else {
				pstmt.setInt(1, detailId);
			}

			result = pstmt.executeUpdate();// delete

		} catch (Exception e) {
			System.out.println(e.toString());
			// mbookApp.jf_mngapp
			// JOptionPane.showMessageDialog(managerBookApp.jf_mngapp, e.toString(),
			// "Error", JOptionPane.ERROR_MESSAGE);
		}
		return result;

	}

	// ������ -�뿩��ư ���� ����// rentalMaster �� ����
	public int rentInsert(String p_memid, int p_rent_count) {
		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rentma_ins(?,?,?)}");

			System.out.println("memid " + p_memid);
			System.out.println("rent_count" + p_rent_count);
			cstmt.setInt(1, p_rent_count);
			cstmt.setString(2, p_memid);
			cstmt.registerOutParameter(3, java.sql.Types.NUMERIC);

			res = cstmt.executeUpdate();// Insert
			System.out.println("res: " + res);
			this.rentno = cstmt.getInt(3);// �����ȣ
			System.out.println("�����ȣ:" + rentno);

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return res;

	}

	// ������ rentalDetail �����ϱ�
	public int rentDetailInsert(int detailId, String isbn) {

		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rentde_ins(?,?,?)}");
			System.out.println("rentno: " + rentno);
			cstmt.setInt(1, rentno);// �������� ���
			cstmt.setInt(2, detailId);
			cstmt.setString(3, isbn);

			res = cstmt.executeUpdate();// Insert
			System.out.println("res: " + res);
		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return res;

	}

/////////////////////////////////////////////////////////////////////	
//���� ���� ���� ��� ����Ʈ �̾ƿ��� (RentalMaster�� Rentald) // �ÿ��ڿ��� 
	public List<RentalMasterVO> getmyRentList(String pmemid) {
		List<RentalMasterVO> myrentList = new ArrayList<RentalMasterVO>();
		System.out.println(" ���� �� ");
		try {
			con = dbTest.getConnection();

			cstmt = con.prepareCall("{call user_my_rent(?,?)}");
			cstmt.setString(1, pmemid);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
// ����
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(2);

			RentalMasterVO rmvo = null;
			BookVO bvo = null;
			MemberVO mvo = null;
			RentalDetailVO rdvo = null;
			OverdueVO ovo = null;

			String memid = null;
			String mname = null;
			String isbn = null;
			String bname = null;
			String rental_now = null;
			String overduepay =null;

			while (cursor.next()) {

				mvo = new MemberVO();// ȸ��
				bvo = new BookVO();// ����
				rdvo = new RentalDetailVO();// �뿩 ������
				ovo = new OverdueVO();

				System.out.println(cursor.getString(5));
				mvo.setMemid(cursor.getString(1));
				mvo.setMname(cursor.getString(2));
				bvo.setIsbn(cursor.getString(3));
				bvo.setBname(cursor.getString(4));
				System.out.println("���ư���?");
				rdvo.setRental_now(cursor.getString(6));
				ovo.setOverduepay(cursor.getString(7));

				memid = mvo.getMemid();
				mname = mvo.getMname();
				isbn = bvo.getIsbn();
				bname = bvo.getBname();
				rental_now = rdvo.getRental_now();
				overduepay = ovo.getOverduepay();

				rmvo = new RentalMasterVO(bvo, mvo, rdvo, ovo, memid, mname, isbn, bname, cursor.getString(5),
						rental_now, overduepay);
////���̵�/�̸�/isbn/������/�뿩��/�ݳ���Ȳ/��ü��

				myrentList.add(rmvo);
				System.out.println(memid + "" + mname + "" + isbn + "" + bname + "" + cursor.getString(5) + ""
						+ rental_now + "" + overduepay);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return myrentList;
	}

	
/////////////////////////////////////////////////////////////////////////
//�ݳ��Ϸ��ư �޼ҵ�
	public int returncomplete(int p_detailid) {
		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call returncomplete(?)}");
			cstmt.setInt(1, p_detailid);
			res = cstmt.executeUpdate();// ���� �� �Է�

		} catch (Exception e) {
//JOptionPane.showMessageDialog(jf_mngapp, e.toString()
//,"Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.toString());
		}
		return res;
	}

////////////////////////////////////////////////////////////////////////////
//������ -���� ȭ�� �˻� Search
	public List<RentalMasterVO> getrentSearch(RentalMasterVO prmVO) {
		List<RentalMasterVO> rentList = new ArrayList<RentalMasterVO>();
		System.out.println("MgrRentDao getrentSearch ȣ��");
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rent_cat(?,?,?)}");

			String Search = prmVO.getSearch();
			String Keyword = prmVO.getKeyword();
			System.out.println("Search : " + Search + " Keyword : " + Keyword);

			cstmt.setString(1, Search);
			cstmt.setString(2, Keyword);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
// ����
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(3);
			RentalMasterVO rmvo = null;
			BookVO bvo = null;
			MemberVO mvo = null;
			RentalDetailVO rdvo = null;

			int detailId = 0;
			String mname = null;
			String memid = null;
			String isbn = null;
			String bname = null;
			String rental_now = null;
			while (cursor.next()) {

				mvo = new MemberVO();// ȸ��
				bvo = new BookVO();// ����
				rdvo = new RentalDetailVO();// �뿩 ������

				rdvo.setDetailid(cursor.getInt(2));
				mvo.setMname(cursor.getString(3));
				mvo.setMemid(cursor.getString(4));

				bvo.setIsbn(cursor.getString(5));
				bvo.setBname(cursor.getString(6));

				rdvo.setRental_now(cursor.getString(9));

				detailId = rdvo.getDetailid();
				mname = mvo.getMname();
				memid = mvo.getMemid();
				isbn = bvo.getIsbn();
				bname = bvo.getBname();
				rental_now = rdvo.getRental_now();

				rmvo = new RentalMasterVO(cursor.getInt(1), rdvo, detailId, mvo, mname, memid, bvo, isbn, bname,
						cursor.getDate(7).toString(), cursor.getDate(8).toString(), rental_now);

				//// "�����ȣ", "ȸ����ȣ" ,"ȸ���̸�","���̵�","ISBN", "������", "��������",to_date(rm.rentdate)+7
				//// "�ݳ�����", "�ݳ���Ȳ"

				rentList.add(rmvo);
				/*
				 * System.out.println(rmvo.getRentno()+" "+rdvo.getDetailid()+" "+rmvo.getMvo().
				 * getMname()
				 * +" "+rmvo.getMvo().getMemid()+" "+rmvo.getBvo().getIsbn()+" "+rmvo.getBvo().
				 * getBname()
				 * +" "+rmvo.getRentdate()+" "+rmvo.getReturndate()+" "+rmvo.getRdvo().
				 * getRental_now());
				 */
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return rentList;
	}
	// �����׽�Ʈ�� ���Ѹ��θ޼ҵ�

	public static void main(String[] args) {
		// MgrRentDao mrDao = new MgrRentDao();

		// mrDao.rentDelete(5444,54441,1); mrDao.rentDelete(5444,54441,2);
		// mrDao.rentDelete(5444,54441,3); mrDao.rentDelete(5444,54441,4);

		// mrDao.rentInsert("EURE", 1);
		// mrDao.rentDetailInsert(1, "1820");
	}

}
