package com.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.vo.MemberVO;

import oracle.jdbc.OracleCallableStatement;

public class LoginDao {
	Connection con = null;// 물리적으로 떨어져 있는 오라클 서버에 연결
	CallableStatement cstmt = null;// 프로시저 호출을 위해 필요
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	EasyDBTest dbTest = new EasyDBTest();
	
	//로그인 프로시저 
	public String[] loginfunc(MemberVO pmvo) {
		 
		int res =0;
	//	String mname = "";
		String[] results =new String[3]; 
		try {
			con = dbTest.getConnection();
			//p_id in varchar2, p_pw in varchar2, u_mnager_user out varchar2, u_mname out varchar2,res out number
			cstmt = con.prepareCall("{call proc_login02(?,?,?,?,?)}");
			cstmt.setString(1, pmvo.getMemid());
			cstmt.setString(2,pmvo.getMempw());
		
		
		    cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(5, java.sql.Types.NUMERIC);
			 cstmt.executeUpdate();
			 res = cstmt.getInt(5);
			 results[0]=cstmt.getString(3);//사용자 관리자
			 results[1]=cstmt.getString(4);//이름
			 results[2]= Integer.toString(res);//결과값
			 
			 if(res==1) {
				 System.out.println( results[1]+"님 환영합니다.");
				
			
				 
			 }else  { //res = 0 ,2
				 System.out.println("아이디 또는 패스워드 불일치");
				
			 }
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return results;
		
	}
/*public static void main(String[] args) {
	MemberVO mv = new MemberVO();
	mv.setMemid("das");
	mv.setMempw("dfD");
	mv.setMnager_user("사용자");
	LoginDao ld = new LoginDao();
	String result [] = ld.loginfunc(mv);
	System.out.println(result [0]);
	System.out.println(result [1]);
	
}*/
	
	//회원가입 insert
	public int memberRegister(String[] memberIns) {
		int res =0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call m_register(?,?,?,?,?,?,?)}");
			//mname,birthday,tel,memid,mempw,address,gender
			for (int i = 0; i < memberIns.length; i++) {
				cstmt.setString(i + 1, memberIns[i]);
			}

			res = cstmt.executeUpdate();// Insert 
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return res;
		
	}
	
	//아이디 체크 프로시저
	public int reg_idcheck(String memid){
		int result = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call m_regi_idcheck(?,?)}");
			//입력받은 id 값, 동일한 아이디 카운트한값
			cstmt.setString(1, memid);
		    cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
		    cstmt.executeUpdate();//실행시키기.
		    result = cstmt.getInt(2);//카운트한 값
			 
		    
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//리프레쉬 프로시저
	public void refresh(){
		System.out.println("LoginDao - REFRESH 실행");	
		try {
				con = dbTest.getConnection();
				cstmt = con.prepareCall("{call reflesh(?)}");
				cstmt.setString(1, "리프레쉬실행");
			    cstmt.executeUpdate();//실행시키기.
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			}
	public static void main(String[] args) {
		LoginDao ld = new LoginDao();
		ld.refresh();
	}
}
