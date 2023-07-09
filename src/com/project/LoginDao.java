package com.project;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.vo.MemberVO;

import oracle.jdbc.OracleCallableStatement;

public class LoginDao {
	Connection con = null;// ���������� ������ �ִ� ����Ŭ ������ ����
	CallableStatement cstmt = null;// ���ν��� ȣ���� ���� �ʿ�
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	EasyDBTest dbTest = new EasyDBTest();
	
	//�α��� ���ν��� 
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
			 results[0]=cstmt.getString(3);//����� ������
			 results[1]=cstmt.getString(4);//�̸�
			 results[2]= Integer.toString(res);//�����
			 
			 if(res==1) {
				 System.out.println( results[1]+"�� ȯ���մϴ�.");
				
			
				 
			 }else  { //res = 0 ,2
				 System.out.println("���̵� �Ǵ� �н����� ����ġ");
				
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
	mv.setMnager_user("�����");
	LoginDao ld = new LoginDao();
	String result [] = ld.loginfunc(mv);
	System.out.println(result [0]);
	System.out.println(result [1]);
	
}*/
	
	//ȸ������ insert
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
	
	//���̵� üũ ���ν���
	public int reg_idcheck(String memid){
		int result = 0;
		try {
			con = dbTest.getConnection();
			cstmt = con.prepareCall("{call m_regi_idcheck(?,?)}");
			//�Է¹��� id ��, ������ ���̵� ī��Ʈ�Ѱ�
			cstmt.setString(1, memid);
		    cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
		    cstmt.executeUpdate();//�����Ű��.
		    result = cstmt.getInt(2);//ī��Ʈ�� ��
			 
		    
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//�������� ���ν���
	public void refresh(){
		System.out.println("LoginDao - REFRESH ����");	
		try {
				con = dbTest.getConnection();
				cstmt = con.prepareCall("{call reflesh(?)}");
				cstmt.setString(1, "������������");
			    cstmt.executeUpdate();//�����Ű��.
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			}
	public static void main(String[] args) {
		LoginDao ld = new LoginDao();
		ld.refresh();
	}
}
