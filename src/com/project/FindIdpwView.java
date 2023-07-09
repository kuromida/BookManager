package com.project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.project.EasyDBTest;


import oracle.jdbc.OracleCallableStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class FindIdpwView {
	//�̹��� ��� 
	//String path = "C:\\dev_kosmo20181101\\dev_project\\src\\com\\project\\image\\";
	String path = "C:\\image\\";
	String mid = null;
	String pw1 = null;
	String pw2 = null;
	
	Connection con = null;
	CallableStatement cstmt = null;
	OracleCallableStatement ocstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	EasyDBTest dbTest = new EasyDBTest();

	

	Font f_findid = new Font("����ü",Font.PLAIN,13);
	JDialog jdl_findid = new JDialog();
	JPanel jp_button = new JPanel();
	JPanel jp_findid = new JPanel();
	JButton jbtn_findid = new JButton();
	JButton jbtn_findpw = new JButton();
	JLabel jlb_findid_name = new JLabel("�̸�");
	JTextField jtf_findid_name = new JTextField();
	JLabel jlb_findid_hp = new JLabel("��ȭ��ȣ");
	JTextField jtf_findid_hp = new JTextField();
	JButton jbtn_findid_findid = new JButton();
	
	JButton jbtn_findid_gologin = new JButton("New button");
	
	JLabel jlb_findpw_id = new JLabel("���̵�");
	JTextField jtf_findpw_id = new JTextField();
	JLabel jlb_findpw_name = new JLabel("�̸�");
	JTextField jtf_findpw_name = new JTextField();
	JButton jbtn_findpw_findpw = new JButton();	
	JPanel jp_findpw = new JPanel();
	JLabel jlb_findpw_hp = new JLabel("��ȭ��ȣ");
	JTextField jtf_findpw_hp = new JTextField();
	JButton jbtn_findpw_gologin = new JButton("New button");
	
	//////////////////////////
	JDialog jdl_pwchange = new JDialog();
	JPanel jp_pwchange = new JPanel();
	JLabel jlb_pwchange = new JLabel("���ο� ��й�ȣ�Է�");
	JPasswordField jpf_pwchange = new JPasswordField();
	JPasswordField jpf_pwchangeok = new JPasswordField();
	JButton jbtn_pwchange = new JButton();
	JLabel jlb_ment1 = new JLabel("�� ��й�ȣ�� 4~20�ڷ� �Է����ּ���");
	JLabel jlb_ment2 = new JLabel("�� ������ ���� ���� ����߰ų� ����");
	JLabel jlb_ment3 = new JLabel("   ��й�ȣ�� �ƴ� �� ��й�ȣ��");
	JLabel jlb_ment4 = new JLabel("   ����ϰ� �ֱ������� �������ּ���");
	JButton jbtn_pwchange_clear = new JButton();
	JButton jbtn_pwchange_clear2 = new JButton();
	
	public void initDisplay(){
		jp_button.setBackground(Color.WHITE);
		jdl_findid.getContentPane().add(jp_button);
		jp_findid.setBackground(Color.WHITE);
		jdl_findid.getContentPane().add(jp_findid);
		jdl_findid.getContentPane().setBackground(Color.WHITE);
		jdl_findid.setSize(400, 550);	
		jdl_findid.setVisible(true);
		jdl_findid.getContentPane().setLayout(null);
		jdl_findid.setTitle("ID/PWã��");
		
		jtf_findid_hp.setBounds(33, 112, 158, 38);
		jtf_findid_hp.setColumns(10);
		
		jtf_findid_name.setBounds(33, 37, 158, 38);
		jtf_findid_name.setColumns(10);
						
		jp_button.setBounds(12, 10, 360, 85);
		
		jp_button.setLayout(null);
		jbtn_findpw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jp_findid.setVisible(false);
			
				jp_findpw.setVisible(true);	
				jtf_findpw_id.setText("");
				jtf_findpw_name.setText("");
				jtf_findpw_hp.setText("");
			}
		});
		jp_button.add(jbtn_findpw);		
		jbtn_findid.setBounds(30, 10, 130, 40);
		
		jdl_findid.setResizable(false);
		
		jp_button.add(jbtn_findid);
		jbtn_findpw.setBounds(201, 10, 130, 40);
		jbtn_findid.setIcon(new ImageIcon(path+"idã��.png"));				
		jbtn_findpw.setIcon(new ImageIcon(path+"pwã��.png"));	
		
		jdl_findid.getContentPane().add(jp_findid);
		jp_findid.setBounds(12, 105, 360, 370);
		jp_findid.setLayout(null);
		jlb_findid_name.setBounds(33, 0, 81, 38);
		jlb_findid_name.setFont(f_findid);
		
		jp_findid.add(jlb_findid_name);
		
		jp_findid.add(jtf_findid_name);
		jlb_findid_hp.setBounds(33, 75, 81, 38);
		jlb_findid_hp.setFont(f_findid);
		
		jp_findid.add(jlb_findid_hp);
		
		jp_findid.add(jtf_findid_hp);
		jbtn_findid_findid.setBounds(33, 300, 130, 40);
		
		
		jp_findid.add(jbtn_findid_findid);
		jbtn_findid_findid.setIcon(new ImageIcon(path+"idã��final.png"));
		jbtn_findid_gologin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdl_findid.dispose();
				
			}
		});
		jbtn_findid_gologin.setBounds(190, 300, 130, 40);
		
		jp_findid.add(jbtn_findid_gologin);
		jbtn_findid_gologin.setIcon(new ImageIcon(path+"�α���ȭ���ư.png"));
												
	
	
	jtf_findpw_hp.setBounds(33, 185, 158, 38);
	jtf_findpw_hp.setColumns(10);
	jp_button.setBackground(Color.WHITE);
	jdl_findid.getContentPane().add(jp_button);
	jp_findpw.setBackground(Color.WHITE);
	jdl_findid.getContentPane().add(jp_findpw);
	jdl_findid.getContentPane().setBackground(Color.WHITE);
	jdl_findid.setSize(400, 550);	
	jdl_findid.setVisible(true);
	jdl_findid.getContentPane().setLayout(null);
	jdl_findid.setResizable(false);
	jdl_findid.setTitle("ID/PWã��");
	
	jtf_findpw_name.setBounds(33, 112, 158, 38);
	jtf_findpw_name.setColumns(10);
	
	jtf_findpw_id.setBounds(33, 37, 158, 38);
	jtf_findpw_id.setColumns(10);
					
	jp_button.setBounds(12, 10, 360, 85);
	
	jp_button.setLayout(null);
	jp_button.add(jbtn_findpw);
	jbtn_findid.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			jtf_findid_name.setText("");
			jtf_findid_hp.setText("");
			jp_findpw.setVisible(false);
			JDialog jdl_findid = new JDialog();
			jp_findid.setVisible(true);
			
		}
	});
	
	jbtn_findid.setBounds(30, 10, 130, 40);
	
	jp_button.add(jbtn_findid);
	jbtn_findpw.setBounds(201, 10, 130, 40);
					
	jdl_findid.getContentPane().add(jp_findpw);
	jp_findpw.setBounds(12, 105, 360, 370);
	jp_findpw.setLayout(null);
	jlb_findpw_id.setBounds(33, 0, 81, 38);
	jlb_findpw_id.setFont(f_findid);
	
	jbtn_findid.setIcon(new ImageIcon(path+"idã��.png"));				
	jbtn_findpw.setIcon(new ImageIcon(path+"pwã��.png"));	
	
	jp_findpw.add(jlb_findpw_id);
	
	jp_findpw.add(jtf_findpw_id);
	jlb_findpw_name.setBounds(33, 76, 81, 38);
	jlb_findpw_name.setFont(f_findid);
	jp_findpw.add(jlb_findpw_name);
	
	jp_findpw.add(jtf_findpw_name);
	jbtn_findpw_findpw.setBounds(33, 300, 130, 40);
	jbtn_findpw_findpw.setIcon(new ImageIcon(path+"pwã��final.png"));
	
	jp_findpw.add(jbtn_findpw_findpw);
	jlb_findpw_hp.setBounds(33, 148, 81, 38);
	jlb_findpw_hp.setFont(f_findid);
	jp_findpw.add(jlb_findpw_hp);
	
	jp_findpw.add(jtf_findpw_hp);
	jbtn_findpw_gologin.setBounds(190, 300, 130, 40);
	
	jp_findpw.add(jbtn_findpw_gologin);
	jbtn_findpw_gologin.setIcon(new ImageIcon(path+"�α���ȭ���ư.png"));
	jbtn_findpw_gologin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			jdl_findid.dispose();
			
		}
	});
	jbtn_findid_findid.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			idfind(jtf_findid_name.getText(), jtf_findid_hp.getText());
			
		}
	});
	jbtn_findpw_findpw.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pwchange(jtf_findpw_id.getText(),
						     jtf_findpw_name.getText(),
							 jtf_findpw_hp.getText());
			
		}
	});
	
	
											
}
	public String idfind(String mname, String tel) {
		StringBuilder sql = new StringBuilder();
		String rid = null;
		sql.append("select memid from member");
		sql.append(" WHERE mname = ? ");
		sql.append(" AND TEL = ? " );
		try {
			con = dbTest.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mname);
			pstmt.setString(2, tel);
			//select�� ��� ����Ŭ ������ ��û�� �� executeQuery()���
			//insert|update|delete�� ��� ����Ŭ ������ ��û�� �� executeUpdate()���
			//result 1:��������, 0:���� ����
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
	    		//insert here - ��������
	    		//��ȸ �Ǽ��� n���̹Ƿ� 1�Ǿ� BookVO��  ��������� �������
	    		rid = rs.getString("memid");
			}
			if(rid != null) {
			System.out.println(rid);
			System.out.println(mname+","+tel);
			System.out.println("[[query]]"+sql.toString());
			JOptionPane.showMessageDialog(null, "ȸ������ ���̵�� "+rid+" �Դϴ�", "���̵�", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(rid == null) {
				JOptionPane.showMessageDialog(null, "���̵� �������� �ʽ��ϴ�", "���̵�", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {//try..catch���̿� ����� �ڵ尡 ���ܹ߻����ɼ��� �ִ� �ڵ�
			System.out.println(e.toString());//���ܰ� �߻����� �� ��Ʈ�� ��� ����
		} 
		return rid;
	}
	public String pwchange(String memid, String mname, String tel) {
		StringBuilder sql = new StringBuilder();
		String rpw = null;
		sql.append("select mempw from member");
		sql.append(" WHERE memid = ? ");
		sql.append(" AND mname = ? " );
		sql.append(" AND tel = ? " );
		try {
			con = dbTest.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, memid);
			pstmt.setString(2, mname);
			pstmt.setString(3, tel);
			//select�� ��� ����Ŭ ������ ��û�� �� executeQuery()���
			//insert|update|delete�� ��� ����Ŭ ������ ��û�� �� executeUpdate()���
			//result 1:��������, 0:���� ����
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
	    		//insert here - ��������
	    		//��ȸ �Ǽ��� n���̹Ƿ� 1�Ǿ� BookVO��  ��������� �������
	    		rpw = rs.getString("mempw");
			}
			if(rpw != null) {
				mid = memid; 
			System.out.println(rpw);
			System.out.println(memid+","+mname+","+tel);
			System.out.println("[[query]]"+sql.toString());
			pwchange_initdisplay();
			
			
			}
			else if(rpw == null) {
				
				JOptionPane.showMessageDialog(null, "������ �ٽ� �Է����ּ���", "��й�ȣ", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {//try..catch���̿� ����� �ڵ尡 ���ܹ߻����ɼ��� �ִ� �ڵ�
			System.out.println(e.toString());//���ܰ� �߻����� �� ��Ʈ�� ��� ����
		} 
		return rpw;
	}
	public void pwchange_initdisplay() {
		jdl_pwchange.setTitle("��й�ȣ����");
		jdl_pwchange.setVisible(true);
		jdl_pwchange.setSize(300,450);
		jp_pwchange.setBackground(Color.WHITE);
		
		
		jdl_pwchange.getContentPane().add(jp_pwchange);
		jp_pwchange.setLayout(null);
		jbtn_pwchange_clear2.setBounds(234, 111, 35, 34);
		jbtn_pwchange_clear2.setBackground(Color.white);
		jbtn_pwchange_clear2.setIcon(new ImageIcon(path+"��й�ȣŬ����1.png"));
		jbtn_pwchange_clear2.setBorderPainted(false);
		jbtn_pwchange_clear2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jpf_pwchangeok.setText("");
				
			}
		});
		
		jp_pwchange.add(jbtn_pwchange_clear2);
		jbtn_pwchange_clear.setBounds(234, 63, 35, 34);
		jbtn_pwchange_clear.setBackground(Color.white);
		jbtn_pwchange_clear.setIcon(new ImageIcon(path+"��й�ȣŬ����1.png"));
		jbtn_pwchange_clear.setBorderPainted(false);
	jbtn_pwchange_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jpf_pwchange.setText("");
				
			}
		});
		
		
		jp_pwchange.add(jbtn_pwchange_clear);
		jlb_pwchange.setFont(new Font("����ü", Font.BOLD, 15));
		
		
		jlb_pwchange.setBounds(12, 10, 149, 40);
		jp_pwchange.add(jlb_pwchange);
		
		
		jbtn_pwchange.setBounds(12, 361, 259, 40);
		jp_pwchange.add(jbtn_pwchange);
		jbtn_pwchange.setIcon(new ImageIcon(path+"��й�ȣ����Ȯ��.png"));
		jlb_ment1.setBounds(12, 161, 259, 40);
		
		jp_pwchange.add(jlb_ment1);
		jlb_ment2.setBounds(12, 211, 259, 40);
		
		jp_pwchange.add(jlb_ment2);
		jlb_ment3.setBounds(12, 261, 259, 40);
		
		jp_pwchange.add(jlb_ment3);
		jlb_ment4.setBounds(12, 311, 259, 40);
		
		jp_pwchange.add(jlb_ment4);
		
		
		jpf_pwchange.setBounds(12, 60, 217, 40);
		jp_pwchange.add(jpf_pwchange);
		jpf_pwchange.setColumns(10);
		
		
		jpf_pwchangeok.setBounds(12, 110, 217, 40);
		jp_pwchange.add(jpf_pwchangeok);
		jpf_pwchangeok.setColumns(10);
		
	

		
		
		jbtn_pwchange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String jpf1 = Arrays.toString(jpf_pwchange.getPassword());
				String jpf2 = Arrays.toString(jpf_pwchangeok.getPassword());
				
				System.out.println(jpf_pwchange.getPassword());
				System.out.println(jpf_pwchangeok.getPassword());
				
				if(jpf1.equals(jpf2)) {
					String vpw = new String(jpf_pwchange.getText());
					pwfinal(vpw,mid);
				}
				else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٽ� Ȯ���ϼ���", "��й�ȣ����", JOptionPane.WARNING_MESSAGE);
				}
								
				
				
			}
		});
		
	
	}
	public int pwfinal(String mempw, String memid) {
		StringBuilder sql = new StringBuilder();
		int result = 0;
		sql.append("update member ");
		sql.append(" set mempw = ? ");
		sql.append(" where memid = ? " );
		
	    
			
			if(jpf_pwchange.getPassword().length < 4) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� 4�ڸ��̻����� �Է����ּ���.", "��й�ȣ", JOptionPane.ERROR_MESSAGE);
			}
			
			
			else  {
				
				try {
					con = dbTest.getConnection();
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, mempw);
					pstmt.setString(2, memid);
					System.out.println("[Query]:"+sql.toString());
					System.out.println(mempw+","+memid);
					
					
					
					//select�� ��� ����Ŭ ������ ��û�� �� executeQuery()���
					//insert|update|delete�� ��� ����Ŭ ������ ��û�� �� executeUpdate()���
					//result 1:��������, 0:���� ����
					//010-2000-1234
					result = pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ����Ǿ����ϴ�.",
											"��й�ȣ����", JOptionPane.INFORMATION_MESSAGE);
				jdl_pwchange.dispose();
				} catch (Exception e) {//try..catch���̿� ����� �ڵ尡 ���ܹ߻����ɼ��� �ִ� �ڵ�
					System.out.println(e.toString());//���ܰ� �߻����� �� ��Ʈ�� ��� ����
				} 
				return result ;
				
			}
			
			
			return result ;
	
	}
	
	/*public static void main(String[] args) {
	pwchangetest1 fi = new pwchangetest1();
	fi.initdisplay();
	
	
	}*/
}
			
			
	
