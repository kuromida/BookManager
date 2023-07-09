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
	Connection con = null;// 물리적으로 떨어져 있는 오라클 서버에 연결
	PreparedStatement pstmt = null;
	CallableStatement cstmt = null;// 프로시저 호출을 위해 필요
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	EasyDBTest dbTest = new EasyDBTest();
	// 전역변수 선언란
	int rentno;

	// 도서 대여 관리 전체 리스트 뽑아오기 (RentalMaster와 Rentald)
	public List<RentalMasterVO> getRentList() {
		List<RentalMasterVO> rentList = new ArrayList<RentalMasterVO>();

		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rent(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// 실행
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

				mvo = new MemberVO();// 회원
				bvo = new BookVO();// 도서
				rdvo = new RentalDetailVO();// 대여 디테일

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

				//// "대출번호", "회원번호" ,"회원이름","아이디","ISBN", "도서명", "대출일자",to_date(rm.rentdate)+7
				//// "반납예정", "반납현황"

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

	// 관리자 -rent 대여 삭제 delete 으어 ㅜㅠㅠㅠㅠㅠ
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

	// 관리자 -대여버튼 대출 삽입// rentalMaster 에 삽입
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
			this.rentno = cstmt.getInt(3);// 대출번호
			System.out.println("대출번호:" + rentno);

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return res;

	}

	// 관리자 rentalDetail 삽입하기
	public int rentDetailInsert(int detailId, String isbn) {

		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rentde_ins(?,?,?)}");
			System.out.println("rentno: " + rentno);
			cstmt.setInt(1, rentno);// 전역변수 사용
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
//나의 도서 대출 목록 리스트 뽑아오기 (RentalMaster와 Rentald) // 시용자용임 
	public List<RentalMasterVO> getmyRentList(String pmemid) {
		List<RentalMasterVO> myrentList = new ArrayList<RentalMasterVO>();
		System.out.println(" 실행 중 ");
		try {
			con = dbTest.getConnection();

			cstmt = con.prepareCall("{call user_my_rent(?,?)}");
			cstmt.setString(1, pmemid);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
// 실행
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

				mvo = new MemberVO();// 회원
				bvo = new BookVO();// 도서
				rdvo = new RentalDetailVO();// 대여 디테일
				ovo = new OverdueVO();

				System.out.println(cursor.getString(5));
				mvo.setMemid(cursor.getString(1));
				mvo.setMname(cursor.getString(2));
				bvo.setIsbn(cursor.getString(3));
				bvo.setBname(cursor.getString(4));
				System.out.println("돌아가냐?");
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
////아이디/이름/isbn/도서명/대여일/반납현황/연체료

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
//반납완료버튼 메소드
	public int returncomplete(int p_detailid) {
		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call returncomplete(?)}");
			cstmt.setInt(1, p_detailid);
			res = cstmt.executeUpdate();// 수정 및 입력

		} catch (Exception e) {
//JOptionPane.showMessageDialog(jf_mngapp, e.toString()
//,"Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.toString());
		}
		return res;
	}

////////////////////////////////////////////////////////////////////////////
//관리자 -대출 화면 검색 Search
	public List<RentalMasterVO> getrentSearch(RentalMasterVO prmVO) {
		List<RentalMasterVO> rentList = new ArrayList<RentalMasterVO>();
		System.out.println("MgrRentDao getrentSearch 호출");
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rent_cat(?,?,?)}");

			String Search = prmVO.getSearch();
			String Keyword = prmVO.getKeyword();
			System.out.println("Search : " + Search + " Keyword : " + Keyword);

			cstmt.setString(1, Search);
			cstmt.setString(2, Keyword);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
// 실행
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

				mvo = new MemberVO();// 회원
				bvo = new BookVO();// 도서
				rdvo = new RentalDetailVO();// 대여 디테일

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

				//// "대출번호", "회원번호" ,"회원이름","아이디","ISBN", "도서명", "대출일자",to_date(rm.rentdate)+7
				//// "반납예정", "반납현황"

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
	// 단위테스트를 위한메인메소드

	public static void main(String[] args) {
		// MgrRentDao mrDao = new MgrRentDao();

		// mrDao.rentDelete(5444,54441,1); mrDao.rentDelete(5444,54441,2);
		// mrDao.rentDelete(5444,54441,3); mrDao.rentDelete(5444,54441,4);

		// mrDao.rentInsert("EURE", 1);
		// mrDao.rentDetailInsert(1, "1820");
	}

}
