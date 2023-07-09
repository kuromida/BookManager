package com.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RegisterView implements ActionListener {
	boolean isView = false;
	String title = " ";

	JDialog jd_new = new JDialog();

	JPanel jp_new = new JPanel();
	String month[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
// 콤보박스 생성
	JComboBox<String> months = new JComboBox<String>(month);

// 콤보박스 항목 최대 4   
	Font title1 = new Font("돋움체", Font.BOLD, 20);
	Font normal = new Font("돋움체", Font.CENTER_BASELINE, 15);

	JLabel jlb_text = new JLabel("계정 정보입력");
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

	public void rg_initDisplay() {

		// jl_line.setIcon(new ImageIcon("C:\\line.png"));
		// jl_line.setBounds(10, 580, 300, 30);
		// jl_line.setLocation(20, 90);
		jp_new.setBackground(Color.WHITE);

		jd_new.setVisible(isView);
		jd_new.setTitle(title);
		jd_new.setSize(450, 550);
		jd_new.add(jp_new);

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

		jd_new.setResizable(false);

		Dimension frameSize = jd_new.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jd_new.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		jp_new.add(jlb_text);
		jlb_text.setBounds(10, 20, 300, 60);
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
		jbtn_ok.setBounds(150, 450, 150, 50);

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

		/*
		 * jlb_id.setForeground(Color.GRAY); jlb_pw.setForeground(Color.GRAY);
		 * jlb_pwok.setForeground(Color.GRAY); jlb_name.setForeground(Color.GRAY);
		 * jlb_hp.setForeground(Color.GRAY); jlb_birth.setForeground(Color.GRAY);
		 * jlb_jender.setForeground(Color.GRAY);
		 */

		jlb_text.setFont(title1);
		months.addActionListener(this);
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
		jbtn_ok.setBackground(new Color(252, 215, 5));

		bg.add(jrb_man);
		bg.add(jrb_women);
		bg.add(jrb_secret);

	}

	public static void main(String[] args) {
		new RegisterView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String days31[] = new String[31];
		for (int i = 0; i < days31.length; i++) {
			int j = i + 1;
			String k = Integer.toString(j);
			days31[i] = k;
		}
		String days30[] = new String[30];
		for (int i = 0; i < days30.length; i++) {
			int j = i + 1;
			String k = Integer.toString(j);
			days30[i] = k;
		}
		String days29[] = new String[29];
		for (int i = 0; i < days29.length; i++) {
			int j = i + 1;
			String k = Integer.toString(j);
			days29[i] = k;
		}
		if (e.getSource() == months && months.getSelectedItem().equals("1")
				|| e.getSource() == months && months.getSelectedItem().equals("3")
				|| e.getSource() == months && months.getSelectedItem().equals("5")
				|| e.getSource() == months && months.getSelectedItem().equals("7")
				|| e.getSource() == months && months.getSelectedItem().equals("8")
				|| e.getSource() == months && months.getSelectedItem().equals("10")
				|| e.getSource() == months && months.getSelectedItem().equals("12")) {

			JComboBox<String> days = new JComboBox<String>(days31);
			jp_new.add(days);
			days.setBounds(350, 360, 35, 30);
		} else if (e.getSource() == months && months.getSelectedItem().equals("4")
				|| e.getSource() == months && months.getSelectedItem().equals("6")
				|| e.getSource() == months && months.getSelectedItem().equals("9")
				|| e.getSource() == months && months.getSelectedItem().equals("11")) {
			JComboBox<String> days = new JComboBox<String>(days30);
			jp_new.add(days);
			days.setBounds(350, 360, 35, 30);
		} else {
			JComboBox<String> days = new JComboBox<String>(days29);
			jp_new.add(days);
			days.setBounds(350, 360, 35, 30);
		}

	}
}