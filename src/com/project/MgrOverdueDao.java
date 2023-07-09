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
	Connection con = null;// 물리적으로 떨어져 있는 오라클 서버에 연결
	PreparedStatement pstmt = null;
	CallableStatement cstmt = null;// 프로시저 호출을 위해 필요
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	EasyDBTest dbTest = new EasyDBTest();
	// 전역변수 선언란

	// 연체 리스트 뽑기
	// 상세번호, 회원이름, 아이디, ISBN, 도서명, 반납예정일, 연체일수, 연체료, 납부현황
	public List<OverdueVO> getOverList() {
		List<OverdueVO> overList = new ArrayList<OverdueVO>();

		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_overdue(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// 실행
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

				mvo = new MemberVO();// 회원
				bvo = new BookVO();// 도서
				rmvo = new RentalMasterVO();// 대여마스터
				rdvo = new RentalDetailVO();// 대여 디테일

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
//관리자 -연체 화면 검색 Search
	public List<OverdueVO> getoverSearch(OverdueVO ovVO) {
		List<OverdueVO> overList = new ArrayList<OverdueVO>();
		System.out.println("MgrOverdueDao getoverSearch 호출");
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_overdue_CAT(?,?,?)}");

			String Search = ovVO.getSearch();
			String Keyword = ovVO.getKeyword();
			System.out.println("Search : " + Search + " Keyword : " + Keyword);

			cstmt.setString(1, Search);
			cstmt.setString(2, Keyword);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
//실행
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

				mvo = new MemberVO();// 회원
				bvo = new BookVO();// 도서
				rmvo = new RentalMasterVO();// 대여마스터
				rdvo = new RentalDetailVO();// 대여 디테일

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
//납부완료버튼 메소드
	public int paycomplete(int p_detailid) {
		System.out.println("MgrOverdueDao - paycomplete 호출");
		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call paycomplete(?)}");
			cstmt.setInt(1, p_detailid);
			res = cstmt.executeUpdate();// 수정 및 입력

		} catch (Exception e) {
//JOptionPane.showMessageDialog(jf_mngapp, e.toString()
//,"Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.toString());
		}
		return res;
	}

/////////////////////////////////////////////////////////////////////////
//연체 삭제 메소드
	public void overdelete(int p_detailid) {
		System.out.println("MgrOverdueDao - overdelete 호출");

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
