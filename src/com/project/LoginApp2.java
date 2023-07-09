package com.project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.vo.MemberVO;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginApp extends JFrame implements ActionListener {
	// 媛�泥� 二쇱��

	ManagerBookApp mgrBookApp = null;// 愿�由ъ��
	UserBookApp 	userBookApp = null;
	LoginController loginCtrl = new LoginController();
	
	JPanel contentPane;

	// ����蹂�����
	JButton jbtn_con = new JButton("오라클연결테스트");
	JButton jbtn_driver = new JButton("드라이버테스트 |");//
	JLabel loginMsg = new JLabel();
	//JRadioButton radiob1 = new JRadioButton("사용자용 로그인");
	//JRadioButton radiob2 = new JRadioButton("관리자용 로그인 ");
	JButton loginb = new JButton("로그인");
	JTextField textField1 = new JTextField();//아이디
	JPasswordField textField2 = new JPasswordField();//패스워드
	//JTextField textField2 = new JTextField();
	// ����媛���李�
	Login_RegisterView rsView = null;
	FindIdpwView find = new FindIdpwView();

	JButton btn_register = new JButton();
	JButton btn_id = new JButton();
	JButton btn_pw = new JButton();

	Font f2 = new Font("돋움체", Font.PLAIN, 12);
	JLabel line = new JLabel();
	//String path = "C:\\dev_kosmo20181101\\Kenari\\src\\com\\project\\image\\";
	String path = "C:\\image\\";
	LoginApp() {
		initDisplay();
	}

	public void initDisplay() {
		// �대깽�� 泥�由� 異�媛�
		jbtn_con.addActionListener(this);
		jbtn_driver.addActionListener(this);

		loginb.addActionListener(this);
		btn_register.addActionListener(this);
		btn_id.addActionListener(this);
		btn_pw.addActionListener(this);

		// frame ��蹂� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(900, 400, 550, 360);
		// frame�� �ｌ�� �щ��ㅼ�� �댁�� �⑷린 ����
		setTitle("로그인");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// �ㅻ�쇳�댁�곌껐���ㅽ��
		jbtn_con.setBounds(370, 20, 150, 50);
		jbtn_con.setForeground(Color.DARK_GRAY);
		jbtn_con.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		jbtn_con.setBorderPainted(false);
		jbtn_con.setContentAreaFilled(false);
		jbtn_con.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				jbtn_con.setForeground(new Color(164, 164, 164));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				jbtn_con.setForeground(Color.DARK_GRAY);
			}
		});
		contentPane.add(jbtn_con);

		// ���쇱�대����ㅽ��
		jbtn_driver.setBounds(260, 20, 140, 50);
		jbtn_driver.setForeground(Color.DARK_GRAY);
		jbtn_driver.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		jbtn_driver.setBorderPainted(false);
		jbtn_driver.setContentAreaFilled(false);
		jbtn_driver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				jbtn_driver.setForeground(new Color(164, 164, 164));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				jbtn_driver.setForeground(Color.DARK_GRAY);
			}
		});
		contentPane.add(jbtn_driver);

		// 濡�洹몄�� ��蹂� ����
		loginMsg.setText("");
		loginMsg.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		loginMsg.setForeground(Color.RED);
		loginMsg.setBounds(140, 189, 200, 20);
		loginMsg.setVisible(false);
		contentPane.add(loginMsg);

		// �ъ�⑹���� 愿�由ъ�� ����

		//radiob1.setBackground(new Color(255, 255, 255));
		//radiob1.setFont(new Font("돋움체",Font.PLAIN,11));
		//radiob1.setBounds(140, 235, 120, 20);
		//radiob1.setSelected(true);// ���� ����

		//radiob2.setBackground(new Color(255, 255, 255));
		//radiob2.setFont(new Font("돋움체",Font.PLAIN,11));
		//radiob2.setBounds(320, 235, 120, 20);

		// 濡�洹몄�� 踰���

		loginb.setFont(new Font("돋움체", Font.BOLD, 15));
		loginb.setBackground(new Color(251, 190, 19));
		loginb.setBounds(140, 212, 280, 40);
		contentPane.add("Center", loginb);
		//
		line.setText("");
		line.setIcon(new ImageIcon(path + "yellowLine.png"));
		line.setBounds(10, 60, 520, 5);
		contentPane.add(line);
	
		ButtonGroup bg = new ButtonGroup();


		// ���대�� 移�
		textField1.setText("이메일 또는 아이디");
		textField1.setFont(f2);
		textField1.setForeground(new Color(21, 21, 21, 95));
		textField1.setToolTipText("");
		textField1.setBounds(140, 100, 280, 40);
		contentPane.add(textField1);
		textField1.setColumns(10);

		textField1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField1.setText("");
				if (textField2.getText().equals(""))
					textField2.setText("비밀번호");
				textField1.setForeground(new Color(21, 21, 21, 95));
				textField1.setBorder(new LineBorder(new Color(0, 128, 255), 2));// 텍스트 필드 색상
				textField2.setBorder(new LineBorder(new Color(88, 88, 88, 95), 1));// 텍스트 필드 색상 
			}

		});

		// �ㅼ�대깽�멸� 諛�����硫� ���ㅽ�� ����諛�袁멸린
		textField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyTyped(e);
				textField1.setForeground(Color.BLACK);
			}

		});

		// 鍮�踰� 移�
		textField2.setText("비밀번호");
		textField2.setFont(f2);
		textField2.setForeground(new Color(21, 21, 21, 95));
		textField2.setToolTipText("");
		textField2.setBounds(140, 150, 280, 40);
		contentPane.add(textField2);
		textField2.setColumns(10);



		textField2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField2.setText("");
				if (textField1.getText().equals(""))
					textField1.setText("이메일 또는 아이디");
				textField2.setForeground(new Color(21, 21, 21, 95));
				textField2.setBorder(new LineBorder(new Color(0, 128, 255), 2));
				textField1.setBorder(new LineBorder(new Color(88, 88, 88, 95), 1));
			}

		});
		
		textField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyTyped(e);
				textField2.setForeground(Color.BLACK);
			}

		});
		textField1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			  textField1.setText("");
			  textField1.setForeground(new Color(21, 21, 21, 95));
				textField1.setBorder(new LineBorder(new Color(0, 128, 255), 2));// 텍스트 필드 색상
				textField2.setBorder(new LineBorder(new Color(88, 88, 88, 95), 1));// 텍스트 필드 색상 
			  
			}
		});
textField2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			  textField2.setText("");
			  textField2.setForeground(new Color(21, 21, 21, 95));
				textField2.setBorder(new LineBorder(new Color(0, 128, 255), 2));
				textField1.setBorder(new LineBorder(new Color(88, 88, 88, 95), 1));
			}
		});
		

		// ����媛���, ���대�� 鍮�踰� 李얘린
		btn_register.setText("회원가입");
		btn_register.setBorderPainted(false);
		btn_register.setContentAreaFilled(false);
		btn_register.setFont(f2);
		btn_register.setForeground(Color.BLACK);
		btn_register.setBounds(123, 270, 100, 30);

		btn_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btn_register.setForeground(new Color(164, 164, 164));

			}
		});
		
		btn_register.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				btn_register.setForeground(Color.BLACK);
			}
		});
		contentPane.add(btn_register);

		btn_id.setText("아이디 |");
		btn_id.setBorderPainted(false);
		btn_id.setContentAreaFilled(false);
		btn_id.setFont(f2);
		btn_id.setForeground(Color.BLACK);
		btn_id.setBounds(260, 270, 100, 30);
		btn_id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_id.setForeground(new Color(164, 164, 164));

			}
		});

		btn_id.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				btn_id.setForeground(Color.BLACK);
			}
		});
		

		contentPane.add(btn_id);

		btn_pw.setText("비밀번호 찾기");
		btn_pw.setBorderPainted(false);
		btn_pw.setContentAreaFilled(false);
		btn_pw.setFont(f2);
		btn_pw.setBounds(322, 270, 120, 30);
		btn_pw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_pw.setForeground(new Color(164, 164, 164));
			}
		});
		btn_pw.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				btn_pw.setForeground(Color.BLACK);
			}
		});
		contentPane.add(btn_pw);

		// KENARI 濡�洹몄��
		JLabel tit = new JLabel("KENARI 로그인");
		tit.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		tit.setBounds(30, 20, 200, 40);
		contentPane.add(tit);

		// jframe

		setResizable(false);
		setVisible(true);

	}

	public MemberVO loginFunc(String memid, String mempw) {
		MemberVO pmvo = new MemberVO();

		pmvo.setCommand("login");
		pmvo.setMemid(memid);
		pmvo.setMempw(mempw);
		
		MemberVO rmvo = loginCtrl.sendLogin(pmvo);

		//rmvo.getMnager_user();
		//rmvo.getMname();
		//rmvo.getResult();
		rmvo.getMemid();
		rmvo.setMemid(pmvo.getMemid());
		System.out.println(rmvo.getMnager_user()+rmvo.getMname()+rmvo.getResult());  
		return rmvo;

	}

	public static void main(String[] args) {
		new LoginApp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String label = e.getActionCommand();
		if ("회원가입".equals(label)) {
			System.out.println("회원가입호출");
			rsView = new Login_RegisterView(true,"회원가입");

		} else if ("아이디 |".equals(label) || "비밀번호 찾기".equals(label)) {
			System.out.println("아이디비번찾기 호출");
			find.initDisplay();

		} else if ("로그인".equals(label)) {
			System.out.println("로그인 호출");
			char[] passWord = textField2.getPassword();//패스워드 char로 바꾸기 
			//String pw="";
		
		
			String id = textField1.getText();
			String pw ="";
			
			for(int i=0;i<passWord.length;i++) {
				
				System.out.println(passWord[i]);
				
				
				pw+= Character.toString(passWord[i]);// 패스워드 문자열로 바꿔서 합쳐서 저장 
			}
			
		
			MemberVO mvo = loginFunc(id, pw);

		
			if (mvo.getResult() == 1) {// 濡?洹몄?? ?깃났
				JOptionPane.showMessageDialog(this, mvo.getMname() + "님환영합니다.", "INFO",
						JOptionPane.INFORMATION_MESSAGE);
				if (mvo.getMnager_user().equals("관리자")) {
					mgrBookApp = new ManagerBookApp(mvo.getMname());
					System.out.println("리프레쉬 호출 완료");
                    loginCtrl.refresh();
                    this.dispose();
				}else if(mvo.getMnager_user().equals("사용자")) {
					System.out.println(mvo.getMemid());
					userBookApp = new UserBookApp(mvo.getMname(),mvo.getMemid());
					this.dispose();
					
					
				}

			} else  {// 아이디 또는 패스워드 불일치 res0,2
				if (id.equals("아이디 또는 이메일") || pw.equals("비밀번호")) {
					loginMsg.setText("아이디 또는 패스워드를 입력하세요.");
					loginMsg.setVisible(true);
				} else {
					loginMsg.setText("아이디 또는 패스워드가 불일치 합니다.");
					loginMsg.setVisible(true);
				}

			} //////////end of login
		} else if ("오라클연결테스트".equals(label)) {

			EasyDBTest dbTest = new EasyDBTest();
			boolean isOk = dbTest.connectTest();
			if (isOk) {
				JOptionPane.showMessageDialog(this, "오라클 연결 성공", "INFO", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "오라클 연결 실패", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if ("드라이버테스트 |".equals(label)) {

			EasyDBTest dbTest = new EasyDBTest();
			boolean isOk = dbTest.driverTest();
			if (isOk) {
				JOptionPane.showMessageDialog(this, "드라이버 연결 성공", "INFO", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "드라이버 연결 실패 ", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

	}
}
