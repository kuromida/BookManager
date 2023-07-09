package com.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.vo.MemberVO;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class MgrMemberDao {
	Connection con = null;// 물리적으로 떨어져 있는 오라클 서버에 연결
	CallableStatement cstmt = null;// 프로시저 호출을 위해 필요
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	EasyDBTest dbTest = new EasyDBTest();

	// 관리자 - 회원 select문 (회원정보 보여주기)
	public List<MemberVO> getMemberList() {
		List<MemberVO> memberList = new ArrayList<MemberVO>();

		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_member(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// 실행
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(1);
			MemberVO mvo = null;
			while (cursor.next()) {
				mvo = new MemberVO(cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
						cursor.getString(6), cursor.getString(7), cursor.getString(8));
				memberList.add(mvo);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return memberList;

	}

	// 관리자 - 회원 Update 수정
	public int memeberUpdate(String[] memberUpd) {
		int result = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_memberUpd(?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < memberUpd.length; i++) {
				if (memberUpd[i].equals(" ")) {
					return result;
				} else {
					cstmt.setString(i + 1, memberUpd[i]);
				}
			}
			result = cstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;

	}

	// 관리자 -회원 삭제 delete
	public int memberDelete(String memid, ManagerBookApp managerBookApp) {
		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_member_del(?)}");
			cstmt.setString(1, memid);
			res = cstmt.executeUpdate();// delete
		} catch (Exception e) {
			JOptionPane.showMessageDialog(managerBookApp.jf_mngapp, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.toString());
		}
		return res;

	}

	// 관리자 -회원 입력 insert
	public int memberInsert(String[] memberIns) {
		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_member_ins(?,?,?,?,?,?,?,?)}");
			// mname,birthday,tel,memid,mempw,address,gender,mnager_user
			for (int i = 0; i < memberIns.length; i++) {
				cstmt.setString(i + 1, memberIns[i]);
			}

			res = cstmt.executeUpdate();// Insert
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return res;

	}

////////////////////////////////////////////////////////////////////////////
//관리자 -회원 화면 검색 Search
	public List<MemberVO> getMemberSearch(MemberVO pmVO) {
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		System.out.println("MgrMemberDao getbookSearch 호출");
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_member_cat(?,?,?)}");

			String Search = pmVO.getSearch();
			String Keyword = pmVO.getKeyword();
			System.out.println("Search : " + Search + " Keyword : " + Keyword);

			cstmt.setString(1, Search);
			cstmt.setString(2, Keyword);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
			// 실행
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(3);
			MemberVO mvo = null;
			while (cursor.next()) {
				mvo = new MemberVO(cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
						cursor.getString(6), cursor.getString(7), cursor.getString(8));
				memberList.add(mvo);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return memberList;
	}
}
