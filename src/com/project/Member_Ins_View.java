package com.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.vo.MemberVO;

public class Member_Ins_View implements ActionListener {
	// boolean isView = false;
	// String title = " ";

	LoginController loginCtrl = new LoginController();
	MgrMemberController memberCtrl = new MgrMemberController();
	ManagerBookApp mgrBookApp= null;
	
	String dayss = " ";
	String monthss = " ";
	int pwcount = 0;
	int idcount = 0;

	String month[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	// 콤보박스 생성
	JComboBox<String> months = new JComboBox<String>(month);

	// 콤보박스 항목 최대 4
	JFrame jf_new = new JFrame();

	JPanel jp_new = new JPanel();

	Font title1 = new Font("맑은 고딕", Font.BOLD, 20);
	Font normal = new Font("맑은 고딕", Font.CENTER_BASELINE, 15);

	JLabel jlb_text = new JLabel("회원입력");

	private String path="C:\\javaprogramming\\source\\dev_java\\src\\com\\project\\image\\"; 

	//private String path = "C:\\dev_kosmo20181101\\dev_project\\src\\com\\project\\image\\";
	// yellowLine이미지
	JLabel yellowLine = new JLabel((Icon) new ImageIcon(path + "yellowLine.png"));
	JLabel jlb_id = new JLabel("아이디");
	JLabel jlb_pw = new JLabel("비밀번호(4~20자리)");
	JLabel jlb_pwok = new JLabel("비밀번호 재입력");
	JLabel jlb_name = new JLabel("이름");
	JLabel jlb_hp = new JLabel("핸드폰번호");
	JLabel jlb_birth = new JLabel("생년월일");
	JLabel jlb_birth_year = new JLabel("년");
	JLabel jlb_birth_month = new JLabel("월");
	JLabel jlb_birth_day = new JLabel("일");
	JLabel jlb_jender = new JLabel("성별");
	JLabel jl_line = new JLabel();
	JTextField jtf_id = new JTextField();
	TextField tf_pw = new TextField();
	TextField tf_pwok = new TextField();
	JTextField jtf_hp = new JTextField();
	JTextField jtf_name = new JTextField();
	JTextField jtf_birth = new JTextField();
	JTextField jtf_jender = new JTextField();
	JRadioButton jrb_man = new JRadioButton("남자");
	JRadioButton jrb_women = new JRadioButton("여자");
	JRadioButton jrb_secret = new JRadioButton("비공개");
	ButtonGroup bg = new ButtonGroup();
	JButton jbtn_ok = new JButton("계정생성");
	JPasswordField jpf_pw = new JPasswordField(20);
	JPasswordField jpf_pwok = new JPasswordField(20);

	JTextField jtf_juso = new JTextField();
	JLabel jlb_juso = new JLabel("주소");
	JRadioButton jrb_manager = new JRadioButton("관리자");
	JRadioButton jrb_member = new JRadioButton("사용자");
	JLabel jlb_grant = new JLabel("권한");
	ButtonGroup bg_grant = new ButtonGroup();

	JLabel jlb_idcheck = new JLabel();
	JLabel jlb_pwcheck = new JLabel();
	Color color1 = new Color(0, 128, 255);// 텍스트 필드 색상
	Color color2 = new Color(88, 88, 88, 95);// 원래 텍스트 색상 ?

	String day = null;

	public Member_Ins_View() {
	}

	public Member_Ins_View(boolean isView, String title,ManagerBookApp managerBookApp) {
		this.mgrBookApp=managerBookApp;
		initDisplay(isView, title);

	}

	public void initDisplay(boolean isView, String title) {
		// jl_line.setIcon(new ImageIcon("C:\\line.png"));
		// jl_line.setBounds(10, 580, 300, 30);
		// jl_line.setLocation(20, 90);

		jp_new.setBackground(Color.WHITE);

		jf_new.setVisible(isView);
		jf_new.setTitle(title);
		jf_new.setSize(450, 650);
		jf_new.add(jp_new);

		jp_new.setLayout(null);
		jp_new.add(jlb_id);
		jp_new.add(jlb_pw);
		jp_new.add(jlb_pwok);
		jp_new.add(jlb_name);
		jp_new.add(jlb_hp);
		jp_new.add(jlb_birth);
		jp_new.add(jlb_jender);
		jp_new.add(jtf_id);
		// jp_new.add(tf_pw);
		jp_new.add(jtf_name);
		// jp_new.add(tf_pwok);
		jp_new.add(jtf_hp);
		jp_new.add(jtf_birth);
		jp_new.add(jlb_birth_year);
		jp_new.add(jlb_birth_month);
		jp_new.add(jlb_birth_day);
		jp_new.add(jtf_jender);
		jp_new.add(jbtn_ok);
		jp_new.add(jpf_pw);
		jp_new.add(jpf_pwok);
		jlb_idcheck.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		jlb_idcheck.setBounds(200, 149, 200, 30);
		jp_new.add(jlb_idcheck);
		jtf_id.addFocusListener(new FocusAdapter() {// jtf_id 아이디 입력창을 빠져나오는 순간 이벤트 발생
			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println("아이디중복검사");
				MemberVO pmvo = new MemberVO();
				int count = 0;
				pmvo.setCommand("idcheck");

				MemberVO rmvo = loginCtrl.sendidcheck(pmvo, jtf_id.getText());

				count = rmvo.getResult();
				System.out.println(rmvo.getResult());
				if (count == 0) {
					idcount = 0;
					jlb_idcheck.setText("사용하실수있는 아이디입니다.");
					jlb_idcheck.setForeground(new Color(35, 174, 2));
				} else {
					idcount = 1;
					jlb_idcheck.setText("이미 사용되고있는 아이디입니다.");
					jlb_idcheck.setForeground(Color.RED);

				}

			}// idcount가 0이여야 회원 가입이 가능하다.

		});
		jlb_pwcheck.setBounds(203, 248, 197, 30);
		jlb_pwcheck.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		jp_new.add(jlb_pwcheck);
		jpf_pwok.addFocusListener(new FocusAdapter() {// jpf_pwok 즉 두번째 비밀번호를 빠져나오는 순간 이벤트 발생
			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println("비밀번호 일치 테스트");
				if (jpf_pw.getText().length() < 4) {
					jlb_pwcheck.setText("비밀번호는 4자리 이상 입력하세요.");
					jlb_pwcheck.setForeground(Color.RED);
					pwcount = 1;
				} else if (jpf_pw.getText().equals(jpf_pwok.getText())) {
					jlb_pwcheck.setText("비밀번호가 일치합니다.");
					jlb_pwcheck.setForeground(new Color(35, 174, 2));
					pwcount = 2;
				} else {
					jlb_pwcheck.setText("비밀번호가 일치하지 않습니다.");
					jlb_pwcheck.setForeground(Color.RED);
					pwcount = 3;

				} /////// pwcount 가 2일때만 회원입력이 가능하다.
			}
		});

		jf_new.setResizable(false);

		Dimension frameSize = jf_new.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jf_new.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		jp_new.add(jlb_text);
		yellowLine.setBounds(10, 60, 430, 5);
		jp_new.add(yellowLine);
		jlb_text.setBounds(10, 20, 300, 50);
		jlb_id.setBounds(30, 120, 170, 30);
		jtf_id.setBounds(200, 120, 200, 30);
		jlb_pw.setBounds(30, 180, 170, 30);
		jpf_pw.setBounds(200, 180, 200, 30);
		jlb_pwok.setBounds(30, 220, 170, 30);
		jpf_pwok.setBounds(200, 220, 200, 30);
		jlb_name.setBounds(30, 280, 170, 30);
		jtf_name.setBounds(200, 280, 200, 30);
		jlb_hp.setBounds(30, 320, 170, 30);
		jtf_hp.setBounds(200, 320, 200, 30);
		jlb_birth.setBounds(30, 360, 170, 30);
		jtf_birth.setBounds(200, 360, 60, 30);
		jlb_birth_year.setBounds(265, 360, 40, 30);
		jlb_birth_month.setBounds(330, 360, 40, 30);
		jlb_birth_day.setBounds(385, 360, 40, 30);
		jlb_jender.setBounds(30, 400, 170, 30);
		jbtn_ok.setBounds(155, 548, 150, 50);

		jlb_id.setFont(normal);
		jlb_pw.setFont(normal);
		jlb_pwok.setFont(normal);
		jlb_name.setFont(normal);
		jlb_hp.setFont(normal);
		jlb_birth.setFont(normal);
		jlb_jender.setFont(normal);
		jlb_birth_year.setFont(normal);
		jlb_birth_month.setFont(normal);
		jlb_birth_day.setFont(normal);
		jrb_man.setFont(normal);
		jrb_women.setFont(normal);
		jrb_secret.setFont(normal);
		jbtn_ok.setFont(normal);

		jp_new.add(months);
		months.setBounds(283, 360, 40, 30);
		months.setBackground(Color.WHITE);

		/*
		 * jlb_id.setForeground(Color.GRAY); jlb_pw.setForeground(Color.GRAY);
		 * jlb_pwok.setForeground(Color.GRAY); jlb_name.setForeground(Color.GRAY);
		 * jlb_hp.setForeground(Color.GRAY); jlb_birth.setForeground(Color.GRAY);
		 * jlb_jender.setForeground(Color.GRAY);
		 */

		jlb_text.setFont(title1);
		months.addActionListener(this);
		jbtn_ok.addActionListener(this);
		/*
		 * String days31[] = new String[31]; for(int i=0;i<days31.length;i++) { int j =
		 * i+1; String k = Integer.toString(j); days31[i] = k; } JComboBox<String> day =
		 * new JComboBox<String>(days31); jp_new.add(day); day.setBounds(440, 360, 60,
		 * 30);
		 */
		// tf_pw.setEchoChar('*');
		// tf_pwok.setEchoChar('*');

		jp_new.add(jrb_man);
		jp_new.add(jrb_women);
		jp_new.add(jrb_secret);
		jrb_man.setBounds(200, 400, 70, 50);
		jrb_women.setBounds(270, 400, 70, 50);
		jrb_secret.setBounds(340, 400, 70, 50);

		jrb_man.setBackground(Color.WHITE);
		jrb_women.setBackground(Color.WHITE);
		jrb_secret.setBackground(Color.WHITE);
		jbtn_ok.setBackground(new Color(251, 190, 19));

		bg.add(jrb_man);
		bg.add(jrb_women);
		bg.add(jrb_secret);

		jlb_juso.setBounds(30, 450, 170, 30);
		jp_new.add(jlb_juso);

		jtf_juso.setBounds(200, 456, 200, 30);
		jp_new.add(jtf_juso);
		jtf_juso.setColumns(10);

		jrb_member.setBounds(200, 504, 79, 30);
		jp_new.add(jrb_member);
		jrb_manager.setBounds(330, 504, 79, 30);

		jp_new.add(jrb_manager);
		jlb_grant.setBounds(30, 504, 117, 30);

		jp_new.add(jlb_grant);
		bg_grant.add(jrb_manager);
		bg_grant.add(jrb_member);

		jlb_juso.setFont(normal);
		jrb_manager.setFont(normal);
		jrb_member.setFont(normal);
		jlb_grant.setFont(normal);

		jrb_member.setBackground(Color.WHITE);
		jrb_manager.setBackground(Color.WHITE);
		/////////////////////////////////////////////////////////
		// 여기주석밑에막아논거는 관리자잇는버전

		jlb_grant.setVisible(true);
		jrb_member.setVisible(true);
		jrb_manager.setVisible(true);

	}

	public static void main(String[] args) {
		Member_Ins_View  miv =	new Member_Ins_View();
		miv.initDisplay(true, "회원가입");
		
	}

	public void day31() {
		String days31[] = new String[31];
		for (int i = 0; i < days31.length; i++) {
			int j = i + 1;
			if (j < 10) {
				String k = "0" + Integer.toString(j);
				days31[i] = k;
			} else {
				String k = Integer.toString(j);
				days31[i] = k;
			}
		}
		JComboBox days = new JComboBox<String>(days31);
		jp_new.add(days);
		days.setBackground(Color.white);
		days.setBounds(350, 360, 35, 30);
		days.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dayss = days.getSelectedItem().toString();
				System.out.println(dayss + " 일 선택");
			}
		});
	}

	public void day30() {
		String days30[] = new String[30];
		for (int i = 0; i < days30.length; i++) {
			int j = i + 1;
			if (j < 10) {
				String k = "0" + Integer.toString(j);
				days30[i] = k;
			} else {
				String k = Integer.toString(j);
				days30[i] = k;
			}
		}
		JComboBox days = new JComboBox<String>(days30);
		jp_new.add(days);
		days.setBackground(Color.white);
		days.setBounds(350, 360, 35, 30);

		days.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dayss = days.getSelectedItem().toString();
				System.out.println(dayss + " 일 선택");
			}
		});
	}

	public void day29() {
		String days29[] = new String[29];
		for (int i = 0; i < days29.length; i++) {
			int j = i + 1;
			if (j < 10) {
				String k = "0" + Integer.toString(j);
				days29[i] = k;
			} else {
				String k = Integer.toString(j);
				days29[i] = k;
			}

		}
		JComboBox days = new JComboBox<String>(days29);
		jp_new.add(days);
		days.setBackground(Color.white);
		days.setBounds(350, 360, 35, 30);
		days.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dayss = days.getSelectedItem().toString();
				System.out.println(dayss + " 일 선택");
			}
		});
	}
	/////////////////////////////////////////////////////////////
	/// 회원입력

	public String[] getMemberText() {// 텍스트필트장에 있는 데이터 읽어오기
		int k = 0;// 초기화

		String memText[] = new String[8];
		memText[k++] = jtf_name.getText();
		memText[k++] = jtf_birth.getText() + '/' + monthss + '/' + dayss;
		memText[k++] = jtf_hp.getText();
		memText[k++] = jtf_id.getText();
		memText[k++] = jpf_pw.getText();
		memText[k++] = jtf_juso.getText();

		if (jrb_man.isSelected()) {
			memText[k++] = "남";
		} else if (jrb_women.isSelected()) {
			memText[k++] = "여";
		} else {
			memText[k++] = "비공개";
		}
		if (jrb_manager.isSelected()) {
			memText[k++] = "관리자";

		} else {
			memText[k++] = "사용자";

		}
		return memText;

	}

	// 회원입력 - insert()
	public int memberInsert(ManagerBookApp mgrBookApp) {
		int result=0;
		System.out.println("insert메소드 호출 완료");
		String memIns[] = getMemberText();

		MemberVO pmvo = new MemberVO(null,memIns[0], memIns[1], memIns[2], memIns[3], memIns[4], memIns[5], memIns[6],
				memIns[7]);
		pmvo.setCommand("insert");
		MemberVO rmvo = memberCtrl.send(pmvo,mgrBookApp);
		result = rmvo.getResult();
		if (result == 1) {// 회원입력성공
			JOptionPane.showMessageDialog(jf_new, memIns[0] + "님이 입력되었습니다.", "INFO", JOptionPane.INFORMATION_MESSAGE);
			
		}else {//실패 =>0
			JOptionPane.showMessageDialog(jf_new, "입력실패", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return result;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String label = e.getActionCommand();

		if (obj == months && months.getSelectedItem().equals("1")
				|| obj == months && months.getSelectedItem().equals("3")
				|| obj == months && months.getSelectedItem().equals("5")
				|| obj == months && months.getSelectedItem().equals("7")
				|| obj == months && months.getSelectedItem().equals("8")
				|| obj == months && months.getSelectedItem().equals("10")
				|| obj == months && months.getSelectedItem().equals("12")) {
			System.out.println(months.getSelectedItem().toString() + " 월달 선택");
			monthss = months.getSelectedItem().toString();

			day31();

		} else if (obj == months && months.getSelectedItem().equals("4")
				|| obj == months && months.getSelectedItem().equals("6")
				|| obj == months && months.getSelectedItem().equals("9")
				|| obj == months && months.getSelectedItem().equals("11")) {
			System.out.println(months.getSelectedItem().toString() + " 월달 선택");
			monthss = months.getSelectedItem().toString();
			day30();
		}

		else {
			System.out.println(months.getSelectedItem().toString() + " 월달 선택");
			monthss = months.getSelectedItem().toString();
			day29();

		}

		// 회원입력 - insert
		if (obj == jbtn_ok) {
			System.out.println("계정생성 이벤트 요청");
			System.out.println(bg.getSelection().toString() + "선택");
			if (pwcount == 2 && idcount == 0) {
			
				memberInsert(this.mgrBookApp);
				this.mgrBookApp.dtm_mng_mem.setRowCount(0);
				this.mgrBookApp.memberRefreshData();
				jf_new.dispose();
			} else if (idcount != 0) {
				JOptionPane.showMessageDialog(jf_new, "존재하는아이디입니다.", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else if (idcount == 0 && pwcount != 2) {
				JOptionPane.showMessageDialog(jf_new, "비밀번호는 4자리이상 입력하세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(jf_new, "가입하지 마세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		}

	}
}