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
	Connection con = null;// 弘軒旋生稽 恭嬢閃 赤澗 神虞適 辞獄拭 尻衣
	PreparedStatement pstmt = null;
	CallableStatement cstmt = null;// 覗稽獣煽 硲窒聖 是背 琶推
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	EasyDBTest dbTest = new EasyDBTest();
	// 穿蝕痕呪 識情空
	int rentno;

	// 亀辞 企食 淫軒 穿端 軒什闘 嗣焼神奄 (RentalMaster人 Rentald)
	public List<RentalMasterVO> getRentList() {
		List<RentalMasterVO> rentList = new ArrayList<RentalMasterVO>();

		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rent(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// 叔楳
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

				mvo = new MemberVO();// 噺据
				bvo = new BookVO();// 亀辞
				rdvo = new RentalDetailVO();// 企食 巨砺析

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

				//// "企窒腰硲", "噺据腰硲" ,"噺据戚硯","焼戚巨","ISBN", "亀辞誤", "企窒析切",to_date(rm.rentdate)+7
				//// "鋼崖森舛", "鋼崖薄伐"

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

	// 淫軒切 -rent 企食 肢薦 delete 生嬢 ぬばばばばば
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

	// 淫軒切 -企食獄動 企窒 諮脊// rentalMaster 拭 諮脊
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
			this.rentno = cstmt.getInt(3);// 企窒腰硲
			System.out.println("企窒腰硲:" + rentno);

		} catch (Exception e) {
			System.out.println(e.toString());

		}
		return res;

	}

	// 淫軒切 rentalDetail 諮脊馬奄
	public int rentDetailInsert(int detailId, String isbn) {

		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rentde_ins(?,?,?)}");
			System.out.println("rentno: " + rentno);
			cstmt.setInt(1, rentno);// 穿蝕痕呪 紫遂
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
//蟹税 亀辞 企窒 鯉系 軒什闘 嗣焼神奄 (RentalMaster人 Rentald) // 獣遂切遂績 
	public List<RentalMasterVO> getmyRentList(String pmemid) {
		List<RentalMasterVO> myrentList = new ArrayList<RentalMasterVO>();
		System.out.println(" 叔楳 掻 ");
		try {
			con = dbTest.getConnection();

			cstmt = con.prepareCall("{call user_my_rent(?,?)}");
			cstmt.setString(1, pmemid);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
// 叔楳
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

				mvo = new MemberVO();// 噺据
				bvo = new BookVO();// 亀辞
				rdvo = new RentalDetailVO();// 企食 巨砺析
				ovo = new OverdueVO();

				System.out.println(cursor.getString(5));
				mvo.setMemid(cursor.getString(1));
				mvo.setMname(cursor.getString(2));
				bvo.setIsbn(cursor.getString(3));
				bvo.setBname(cursor.getString(4));
				System.out.println("宜焼亜劃?");
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
////焼戚巨/戚硯/isbn/亀辞誤/企食析/鋼崖薄伐/尻端戟

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
//鋼崖刃戟獄動 五社球
	public int returncomplete(int p_detailid) {
		int res = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call returncomplete(?)}");
			cstmt.setInt(1, p_detailid);
			res = cstmt.executeUpdate();// 呪舛 貢 脊径

		} catch (Exception e) {
//JOptionPane.showMessageDialog(jf_mngapp, e.toString()
//,"Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.toString());
		}
		return res;
	}

////////////////////////////////////////////////////////////////////////////
//淫軒切 -企窒 鉢檎 伊事 Search
	public List<RentalMasterVO> getrentSearch(RentalMasterVO prmVO) {
		List<RentalMasterVO> rentList = new ArrayList<RentalMasterVO>();
		System.out.println("MgrRentDao getrentSearch 硲窒");
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call proc_rent_cat(?,?,?)}");

			String Search = prmVO.getSearch();
			String Keyword = prmVO.getKeyword();
			System.out.println("Search : " + Search + " Keyword : " + Keyword);

			cstmt.setString(1, Search);
			cstmt.setString(2, Keyword);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
// 叔楳
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

				mvo = new MemberVO();// 噺据
				bvo = new BookVO();// 亀辞
				rdvo = new RentalDetailVO();// 企食 巨砺析

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

				//// "企窒腰硲", "噺据腰硲" ,"噺据戚硯","焼戚巨","ISBN", "亀辞誤", "企窒析切",to_date(rm.rentdate)+7
				//// "鋼崖森舛", "鋼崖薄伐"

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
	// 舘是砺什闘研 是廃五昔五社球

	public static void main(String[] args) {
		// MgrRentDao mrDao = new MgrRentDao();

		// mrDao.rentDelete(5444,54441,1); mrDao.rentDelete(5444,54441,2);
		// mrDao.rentDelete(5444,54441,3); mrDao.rentDelete(5444,54441,4);

		// mrDao.rentInsert("EURE", 1);
		// mrDao.rentDetailInsert(1, "1820");
	}

}
