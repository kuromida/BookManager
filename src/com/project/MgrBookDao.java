package com.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.vo.BookVO;
import com.vo.MemberVO;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class MgrBookDao {
	Connection con = null;// 물리적으로 떨어져 있는 오라클 서버에 연결
	CallableStatement cstmt = null;// 프로시저 호출을 위해 필요
	PreparedStatement pstmt = null;
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	EasyDBTest dbTest = new EasyDBTest();

	// 관리자 -도서 전체리스트
	public List<BookVO> getBookList() {
		List<BookVO> bookList = new ArrayList<BookVO>();

		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_mbook2(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// 실행
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(1);
			BookVO bvo = null;
			while (cursor.next()) {
				bvo = new BookVO(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
						cursor.getString(5), cursor.getString(6), cursor.getString(7));
				bookList.add(bvo);
				// System.out.println(bvo.getAuthor());
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
		}
		return bookList;
	}

	// 관리자 -도서수정
	public int bookUpdate(String[] bookUpd) {
		int result = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_bookUpd(?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < bookUpd.length; i++) {
				if (i == 6 || i == 7) {

					cstmt.setInt(i + 1, Integer.parseInt(bookUpd[i]));
				} else {
					cstmt.setString(i + 1, bookUpd[i]);
				}
			}

			result = cstmt.executeUpdate();// update 임

		} catch (Exception e) {
			System.out.println(e.toString());
			// JOptionPane.showMessageDialog(mbookApp.jf_mngapp,e.toString(),"Error",JOptionPane.ERROR_MESSAGE);
		}

		return result;

	}

	// 관리자 -도서 삽입
	public int bookInsert(String[] bookIns) {
		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_bookIns(?,?,?,?,?,?,?)}");
			for (int i = 0; i < bookIns.length; i++) {
				if (i == 5 || i == 6) {

					cstmt.setInt(i + 1, Integer.parseInt(bookIns[i]));
				} else {
					cstmt.setString(i + 1, bookIns[i]);
				}
			}

			res = cstmt.executeUpdate();// Insert
		} catch (Exception e) {
			System.out.println(e.toString());
			// JOptionPane.showMessageDialog(mbookApp.jf_mngapp,e.toString(),"Error",JOptionPane.ERROR_MESSAGE);

		}
		return res;

	}

	// 관리자 -도서 삭제 delete
	public int bookDelete(String isbn) {

		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_book_del(?)}");
			cstmt.setString(1, isbn);
			res = cstmt.executeUpdate();// delete
		} catch (Exception e) {
			System.out.println(e.toString());
			// mbookApp.jf_mngapp
			// JOptionPane.showMessageDialog(managerBookApp.jf_mngapp, e.toString(),
			// "Error", JOptionPane.ERROR_MESSAGE);
		}
		return res;

	}

	// 관리자 - 회원- 대여버튼 - 추가 ->ISBN가 일치하는것 추가
	// isbn,bname,publisher,stock
	public List<BookVO> getBookList02(String isbn) {
		List<BookVO> bookList = new ArrayList<BookVO>();

		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_book_plus(?,?)}");
			cstmt.setString(1, isbn);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			// 실행
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(2);
			BookVO bvo = null;
			while (cursor.next()) {
				bvo = new BookVO(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
				System.out.println(
						bvo.getIsbn() + " " + bvo.getBname() + " " + bvo.getPublisher() + " " + bvo.getStock());
				bookList.add(bvo);
			}

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return bookList;
	}

	// 관리자 도서 검색 Search
	public List<BookVO> getbookSearch(BookVO pbVO) {
		List<BookVO> bookList = new ArrayList<BookVO>();
		System.out.println("MgrBookDao getbookSearch 호출");
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_book_cat(?,?,?)}");

			String Search = pbVO.getSearch();
			String Keyword = pbVO.getKeyword();
			System.out.println("Search : " + Search + " Keyword : " + Keyword);

			cstmt.setString(1, Search);
			cstmt.setString(2, Keyword);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
			// 실행
			cstmt.execute();
			ocstmt = (OracleCallableStatement) cstmt;
			ResultSet cursor = ocstmt.getCursor(3);
			BookVO bvo = null;
			while (cursor.next()) {
				bvo = new BookVO(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
						cursor.getString(5), cursor.getString(6), cursor.getString(7));
				bookList.add(bvo);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return bookList;
	}

	// 단위테스트를 위한메인메소드
	public static void main(String[] args) {
		// 이상없음 => 재고수량 약간 문제있음
		MgrBookDao mbookDao = new MgrBookDao();
		BookVO pbVO = new BookVO();

		mbookDao.getBookList02("1810");

	}

}
