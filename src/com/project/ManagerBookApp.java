package com.project;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.vo.BookVO;
import com.vo.MemberVO;
import com.vo.OverdueVO;
import com.vo.RentalDetailVO;
import com.vo.RentalMasterVO;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Vector;
import java.awt.Font;

public class ManagerBookApp implements ActionListener {

	// 객체주입을 위한 인스턴스화
	MgrOverController mOverCtrl = new MgrOverController();
	MgrRentView mRentView = new MgrRentView();
	MgrBookController mBookCtrl = new MgrBookController();
	MgrMemberController memCtrl = new MgrMemberController();
	MgrRentController mRentCtrl = new MgrRentController();
	Member_Ins_View member_ins_view = null;
	BookVO pbVO = new BookVO();
	MemberVO pmVO = new MemberVO();
	RentalMasterVO prmVO = new RentalMasterVO();
	OverdueVO ovVO = new OverdueVO();

	// 전역변수 선언
	int page = 1;// 페이지 구분
	String memid;// 업데이트시 저장할 변수(회원 memid)
	String isbn;// 업데이트시 저장할 변수(도서 isbn)

	
	//String path = "C:\\dev_kosmo20181101\\Kenari\\src\\com\\project\\image\\";
	String path = "C:\\image\\";
	
	Font f_title = new Font("맑은 고딕", Font.BOLD, 24);
	// Font f_title = new Font("상상토끼 꽃길", Font.BOLD, 24);
	Font f = new Font("맑은 고딕", Font.BOLD, 20);
	JFrame jf_mngapp = new JFrame();
	JPanel jp_mngtop = new JPanel();
	JMenuBar jmb_mng = new JMenuBar();

	JMenu jm_mng_file = new JMenu("File");
	JMenuItem jmi_mng_file_exit = new JMenuItem("종료");
	JMenu jm_mng_edit = new JMenu("Edit");
	JPanel jp_mng_lrent = new JPanel();
	JPanel jp_mng_rrent = new JPanel();
	String mng_rent_rcols[] = { "대출번호", "상세번호", "이름", "아이디", "ISBN", "도서명", "대여일", "반납예정", "반납현황" };
	String mng_rent_rcols_data[][] = new String[0][9];
	DefaultTableModel dtm_mng_rent = new DefaultTableModel(mng_rent_rcols_data, mng_rent_rcols);
	JTable jt_mng_rent = new JTable(dtm_mng_rent);
	JScrollPane jsp_mng_rent = new JScrollPane(jt_mng_rent, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String[] rcate = { "", "이름", "도서명", "반납현황" };
	JComboBox jcb_mng_rcate = new JComboBox(rcate);

	JMenuItem jmi_mng_edit_ins = new JMenuItem("입력");
	JMenuItem jmi_mng_edit_upd = new JMenuItem("수정");
	JMenuItem jmi_mng_edit_del = new JMenuItem("삭제");
	JPanel jp_mng_second = new JPanel();
	JButton jbtn_mng_book = new JButton("도서");
	JButton jbtn_mng_mem = new JButton("회원");
	JButton jbtn_mng_rent = new JButton("대여");
	JButton jbtn_mng_delay = new JButton("연체");
	JButton jbtn_mng_ins = new JButton("");//입력

	JTextField jtf_mng_search = new JTextField();
	JButton jbtn_mng_upd = new JButton("");//수정
	JButton jbtn_mng_del = new JButton("");//삭제
	JButton jbtn_mng_search = new JButton("");//검색

	String[] mcate = { "", "이름", "아이디", "전화번호" };
	JComboBox jcb_mng_mcate = new JComboBox(mcate);
	JPanel jp_mng_mleft = new JPanel();
	JPanel jp_mng_mright = new JPanel();

	JLabel jlb_mng_mname = new JLabel("이름");
	JTextField jtf_mng_mname = new JTextField();
	JLabel jlb_mng_mid = new JLabel("아이디");
	JTextField jtf_mng_mid = new JTextField();
	JLabel jlb_mng_mpw = new JLabel("비밀번호");
	JTextField jtf_mng_mpw = new JTextField();
	JLabel jlb_mng_mhp = new JLabel("전화번호");
	JTextField jtf_mng_mhp = new JTextField();
	JLabel jlb_mng_maddr = new JLabel("주소");
	JTextField jtf_mng_maddr = new JTextField();
	JLabel jlb_mng_mbirth = new JLabel("생일");
	JTextField jtf_mng_mbirth = new JTextField();
	JLabel jlb_mng_mgender = new JLabel("성별");
	JTextField jtf_mng_mgender = new JTextField();
	String mng_book_mcols[] = { "이름", "아이디", "비밀번호", "전화번호", "주소", "생일", "성별" };
	String mng_book_mcols_data[][] = new String[0][7];
	DefaultTableModel dtm_mng_mem = new DefaultTableModel(mng_book_mcols_data, mng_book_mcols);
	JTable jt_mng_mem = new JTable(dtm_mng_mem);
	JScrollPane jsp_mng_mem = new JScrollPane(jt_mng_mem, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String[] bcate = { "", "도서명", "저자명", "출판년도" };
	JComboBox jcb_mng_bcate = new JComboBox(bcate);
	JPanel jp_mng_bleft = new JPanel();
	JPanel jp_mng_bright = new JPanel();
	JLabel jlb_mng_isbn = new JLabel("ISBN");
	JTextField jtf_mng_isbn = new JTextField();
	JLabel jlb_mng_name = new JLabel("도서명");
	JTextField jtf_mng_name = new JTextField();
	JLabel jlb_mng_writer = new JLabel("저자명");
	JTextField jtf_mng_writer = new JTextField();
	JLabel jlb_mng_company = new JLabel("출판사명");
	JTextField jtf_mng_company = new JTextField();
	JLabel jlb_mng_year = new JLabel("출판년도");
	JTextField jtf_mng_year = new JTextField();
	JLabel jlb_mng_price = new JLabel("도서가격");
	JTextField jtf_mng_price = new JTextField();
	JLabel jlb_mng_quanty = new JLabel("재고");
	JTextField jtf_mng_quanty = new JTextField();
	String mng_book_cols[] = { "ISBN", "도서명", "저자명", "출판사", "출판년도", "정가", "재고수량" };
	String mng_book_cols_data[][] = new String[0][7];
	DefaultTableModel dtm_mng_book = new DefaultTableModel(mng_book_cols_data, mng_book_cols);
	JTable jt_mng_book = new JTable(dtm_mng_book);
	JScrollPane jsp_mng_book = new JScrollPane(jt_mng_book, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JMenu jm_mng_help = new JMenu("Help");
	JMenuItem jmi_mng_drivertest = new JMenuItem("드라이버테스트");
	JMenuItem jmi_mng_dbtest = new JMenuItem("데이터베이스 테스트");
	JButton jbtn_mng_dorent = new JButton("대여");
	JButton jbtn_mng_return = new JButton("반납");
	///////// 납부완료 버튼
	JButton jbtn_mng_dopay = new JButton("납부완료");

	JButton jbtn_mng_clear = new JButton();
	JButton jbtn_mng_clear2 = new JButton();
	JButton jbtn_mng_clear3 = new JButton();

	JLabel jlb_mng_nameview = new JLabel();
	JLabel jlb_mng_welcomement = new JLabel("님 환영합니다 ͡~ ͜ʖ ͡° ");
	JButton jbtn_mng_logout = new JButton(" ");//로그아웃 
	JButton jbtn_mng_homepagegogo = new JButton();

	// JButton jbtn_mng_rentorder = new JButton("대여건수순");
	// 연체테이블추가
	JPanel jp_mng_ldelay = new JPanel();
	JPanel jp_mng_rdelay = new JPanel();
	String mng_delay_cols[] = { "상세번호", "이름", "아이디", "ISBN", "도서명", "반납예정일", "연체일수", "연체료", "납부현황" };
	// 대출일자, ISBN, 연체현황 //상세번호, 회원이름, 아이디, ISBN, 도서명, 반납예정일, 연체일수, 연체료, 납부현황
	String mng_delay_cols_data[][] = new String[0][9];
	DefaultTableModel dtm_mng_delay = new DefaultTableModel(mng_delay_cols_data, mng_delay_cols);
	JTable jt_mng_delay = new JTable(dtm_mng_delay);
	JScrollPane jsp_mng_delay = new JScrollPane(jt_mng_delay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String[] dcate = { "", "이름", "아이디","납부현황" };
	JComboBox jcb_mng_dcate = new JComboBox(dcate);

	Font f_mng_order = new Font("맑은 고딕", Font.PLAIN, 9);

	Font f_mng_header = new Font("맑은 고딕", Font.BOLD, 16);

	JTableHeader jth_mng_book = jt_mng_book.getTableHeader();
	JTableHeader jth_mng_mem = jt_mng_mem.getTableHeader();
	JTableHeader jth_mng_rent = jt_mng_rent.getTableHeader();
	JTableHeader jth_mng_delay = jt_mng_delay.getTableHeader();

	public void initDisplay() {
		//jcb_mng_rcate,jcb_mng_mcate,cb_mng_bcate,jcb_mng_dcate
		jcb_mng_dcate.setVisible(false);
		jcb_mng_rcate.setBorder(new LineBorder(new Color(247, 183, 3), 3));
		jcb_mng_mcate.setBorder(new LineBorder(new Color(247, 183, 3), 3));
		jcb_mng_bcate.setBorder(new LineBorder(new Color(247, 183, 3), 3));
		jcb_mng_dcate.setBorder(new LineBorder(new Color(247, 183, 3), 3));
		// 로그아웃 이벤트 처리
		jbtn_mng_logout.addActionListener(this);
		// 종료 이벤트 처리
		jmi_mng_file_exit.addActionListener(this);
		// 지우기 버튼 이벤트 처리
		jbtn_mng_clear2.addActionListener(this);
		jbtn_mng_clear.addActionListener(this);

		// 검색 Search 이벤트 처리
		jcb_mng_bcate.addActionListener(this);// 도서Search 이벤트
		jcb_mng_mcate.addActionListener(this);// 멤버Search 이벤트
		jcb_mng_rcate.addActionListener(this);// 대출Search 이벤트
		jcb_mng_dcate.addActionListener(this);// 연체Search 이벤트

		// 컴포넌트 이벤트 처리
		jtf_mng_search.addActionListener(this);// 검색창
		jbtn_mng_search.addActionListener(this);// 검색
		jbtn_mng_del.addActionListener(this);// 삭제
		jbtn_mng_ins.addActionListener(this);// 입력
		jmi_mng_edit_ins.addActionListener(this);
		jbtn_mng_upd.addActionListener(this);// 수정
		jmi_mng_edit_upd.addActionListener(this);
		jbtn_mng_return.addActionListener(this);// 반납완료
		jbtn_mng_dopay.addActionListener(this);// 납부완료

		// =================================================>>>>jtable에 마우스 클릭시 이벤트

		jt_mng_book.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				bookUpdateText();

			}
		});
		jt_mng_mem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				memberUpdateText();
				// ============>대여버튼 텍스트 뜨게 함
				String memberInfo[] = new String[2];
				memberInfo[0] = jtf_mng_mname.getText();
				memberInfo[1] = jtf_mng_mid.getText();
				mRentView.setTextMember(memberInfo);
			}
		});

		jf_mngapp.setTitle("개나리도서관-관리자용");
		// jth_mng_book.setBackground(Color.white);
		// jth_mng_book.setForeground(Color.YELLOW);
		// jth_mng_mem.setBackground(Color.white);
		// jth_mng_mem.setForeground(Color.YELLOW);
		// jth_mng_rent.setBackground(Color.white);
		// jth_mng_rent.setForeground(Color.YELLOW);

		/////////////
		////////////// 회원화면 라벨+텍스트필드 /////////////////
		// 이름-mname 아이디-mid 비밀번호-mpw 전화번호-mhp 주소-maddr 생일-mbrith 성별-mgender

		jlb_mng_mname.setBounds(12, 107, 189, 40);
		jlb_mng_mid.setBounds(12, 196, 189, 40);
		jlb_mng_mpw.setBounds(12, 286, 189, 40);
		jlb_mng_mhp.setBounds(12, 375, 189, 40);
		jlb_mng_maddr.setBounds(12, 465, 189, 40);
		jlb_mng_mbirth.setBounds(12, 555, 189, 40);
		jlb_mng_mgender.setBounds(12, 645, 189, 40);

		jtf_mng_mgender.setText("");
		jtf_mng_mgender.setBounds(12, 685, 201, 40);
		jtf_mng_mgender.setColumns(10);
		jtf_mng_mbirth.setText("");
		jtf_mng_mbirth.setBounds(12, 595, 201, 40);
		jtf_mng_mbirth.setColumns(10);
		jtf_mng_maddr.setText("");
		jtf_mng_maddr.setBounds(12, 505, 201, 40);
		jtf_mng_maddr.setColumns(10);
		jtf_mng_mhp.setText("");
		jtf_mng_mhp.setBounds(12, 415, 201, 40);
		jtf_mng_mhp.setColumns(10);
		jtf_mng_mpw.setText("");
		jtf_mng_mpw.setBounds(12, 325, 201, 40);
		jtf_mng_mpw.setColumns(10);
		jtf_mng_mid.setText("");
		jtf_mng_mid.setBounds(12, 236, 201, 40);
		jtf_mng_mid.setColumns(10);
		jtf_mng_mname.setText("");
		jtf_mng_mname.setBounds(12, 146, 201, 40);
		jtf_mng_mname.setColumns(10);

		jp_mng_mleft.add(jlb_mng_mname);
		jp_mng_mleft.add(jlb_mng_mid);
		jp_mng_mleft.add(jlb_mng_mpw);
		jp_mng_mleft.add(jlb_mng_mhp);
		jp_mng_mleft.add(jlb_mng_maddr);
		jp_mng_mleft.add(jlb_mng_mbirth);
		jp_mng_mleft.add(jlb_mng_mgender);

		jp_mng_mleft.add(jtf_mng_mname);
		jp_mng_mleft.add(jtf_mng_mid);
		jp_mng_mleft.add(jtf_mng_mpw);
		jp_mng_mleft.add(jtf_mng_mhp);
		jp_mng_mleft.add(jtf_mng_maddr);
		jp_mng_mleft.add(jtf_mng_mbirth);
		jp_mng_mleft.add(jtf_mng_mgender);

		///// 도서화면 라벨+텍스트 필드 ////////////////////////

		jlb_mng_isbn.setBounds(12, 107, 189, 40);
		jp_mng_bleft.add(jlb_mng_isbn);
		jp_mng_bleft.add(jtf_mng_isbn);
		jlb_mng_name.setBounds(12, 196, 189, 40);
		jp_mng_bleft.add(jlb_mng_name);
		jp_mng_bleft.add(jtf_mng_name);
		jlb_mng_writer.setBounds(12, 286, 189, 40);
		jp_mng_bleft.add(jlb_mng_writer);
		jp_mng_bleft.add(jtf_mng_writer);
		jlb_mng_company.setBounds(12, 375, 189, 40);
		jp_mng_bleft.add(jlb_mng_company);
		jp_mng_bleft.add(jtf_mng_company);
		jlb_mng_year.setBounds(12, 465, 189, 40);
		jp_mng_bleft.add(jlb_mng_year);
		jp_mng_bleft.add(jtf_mng_year);
		jlb_mng_price.setBounds(12, 555, 189, 40);
		jp_mng_bleft.add(jlb_mng_price);
		jp_mng_bleft.add(jtf_mng_price);
		jlb_mng_quanty.setBounds(12, 645, 189, 40);
		jp_mng_bleft.add(jlb_mng_quanty);
		jp_mng_bleft.add(jtf_mng_quanty);

		jtf_mng_quanty.setText("");
		jtf_mng_quanty.setBounds(12, 685, 201, 40);
		jtf_mng_quanty.setColumns(10);
		jtf_mng_price.setText("");
		jtf_mng_price.setBounds(12, 595, 201, 40);
		jtf_mng_price.setColumns(10);
		jtf_mng_year.setText("");
		jtf_mng_year.setBounds(12, 505, 201, 40);
		jtf_mng_year.setColumns(10);
		jtf_mng_company.setText("");
		jtf_mng_company.setBounds(12, 415, 201, 40);
		jtf_mng_company.setColumns(10);
		jtf_mng_writer.setText("");
		jtf_mng_writer.setBounds(12, 325, 201, 40);
		jtf_mng_writer.setColumns(10);
		jtf_mng_name.setText("");
		jtf_mng_name.setBounds(12, 236, 201, 40);
		jtf_mng_name.setColumns(10);
		jtf_mng_isbn.setText("");
		jtf_mng_isbn.setBounds(12, 146, 201, 40);
		jtf_mng_isbn.setColumns(10);

		/////////////////////////////// 패널///////////
		jp_mng_bleft.setBounds(0, 116, 230, 735);
		jf_mngapp.getContentPane().add(jp_mng_bleft);
		jp_mng_bleft.setLayout(null);

		jp_mng_mleft.setBounds(0, 116, 213, 735);
		jf_mngapp.getContentPane().add(jp_mng_mleft);
		jp_mng_mleft.setLayout(null);

		jp_mng_lrent.setBounds(0, 116, 213, 735);
		jf_mngapp.getContentPane().add(jp_mng_lrent);
		jp_mng_lrent.setBackground(new Color(251, 190, 19));
		jp_mng_lrent.setVisible(true);

		////////// 대여화면 라벨+텍스트필드 /////////////////////
		// jtf_mng_rpay.setText("");
		// jtf_mng_rpay.setBounds(12, 685, 201, 40);
		// jtf_mng_rpay.setColumns(10);
		// jtf_mng_rstat.setText("");
		// jtf_mng_rstat.setBounds(12, 595, 201, 40);
		// jtf_mng_rstat.setColumns(10);
		// jtf_mng_rdate.setText("");
		// jtf_mng_rdate.setBounds(12, 505, 201, 40);
		// jtf_mng_rdate.setColumns(10);
		// jtf_mng_rbname.setText("");
		// jtf_mng_rbname.setBounds(12, 415, 201, 40);
		// jtf_mng_rbname.setColumns(10);
		jtf_mng_mpw.setText("");
		jtf_mng_mpw.setBounds(12, 325, 201, 40);
		jtf_mng_mpw.setColumns(10);
		jtf_mng_mid.setText("");
		jtf_mng_mid.setBounds(12, 236, 201, 40);
		jtf_mng_mid.setColumns(10);
		jtf_mng_mname.setText("");
		jtf_mng_mname.setBounds(12, 146, 201, 40);
		jtf_mng_mname.setColumns(10);
		jf_mngapp.getContentPane().setBackground(Color.WHITE);

		jf_mngapp.setVisible(true);
		jf_mngapp.setSize(1200, 900);
		jf_mngapp.getContentPane().setLayout(null);

		jp_mngtop.setBounds(0, 0, 1184, 30);
		jf_mngapp.getContentPane().add(jp_mngtop);
		jp_mngtop.setLayout(null);
		jp_mngtop.add(jmb_mng);

		jmb_mng.setBounds(0, 0, 1200, 30);

		jmb_mng.add(jm_mng_file);
		jm_mng_file.add(jmi_mng_file_exit);
		jmb_mng.add(jm_mng_edit);
		jm_mng_edit.add(jmi_mng_edit_ins);
		jm_mng_edit.add(jmi_mng_edit_upd);
		jm_mng_edit.add(jmi_mng_edit_del);

		jmb_mng.add(jm_mng_help);

		jm_mng_help.add(jmi_mng_drivertest);
		jm_mng_help.add(jmi_mng_dbtest);

		jp_mng_second.setBounds(0, 27, 1200, 91);
		jf_mngapp.getContentPane().add(jp_mng_second);
		jp_mng_second.setLayout(null);

		jbtn_mng_book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page = 1;
				jp_mng_mleft.setVisible(false);
				jp_mng_mright.setVisible(false);
				jp_mng_lrent.setVisible(false);
				jp_mng_rrent.setVisible(false);
				jp_mng_ldelay.setVisible(false);
				jp_mng_rdelay.setVisible(false);
				jp_mng_bleft.setVisible(true);
				jp_mng_bright.setVisible(true);
				jcb_mng_rcate.setVisible(false);
				jcb_mng_mcate.setVisible(false);
				jcb_mng_bcate.setVisible(true);
				jcb_mng_dcate.setVisible(false);

				dtm_mng_book.setRowCount(0);
				bookRefreshData(pbVO);

			}
		});
		jbtn_mng_book.setBounds(0, 0, 110, 38);
		jp_mng_second.add(jbtn_mng_book);
		jbtn_mng_mem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page = 2;
				jp_mng_bleft.setVisible(false);
				jp_mng_bright.setVisible(false);
				jp_mng_lrent.setVisible(false);
				jp_mng_rrent.setVisible(false);
				jp_mng_ldelay.setVisible(false);
				jp_mng_rdelay.setVisible(false);
				jp_mng_mleft.setVisible(true);
				jp_mng_mright.setVisible(true);
				jcb_mng_rcate.setVisible(false);
				jcb_mng_bcate.setVisible(false);
				jcb_mng_dcate.setVisible(false);
				jcb_mng_mcate.setVisible(true);

				dtm_mng_mem.setRowCount(0);
				memberRefreshData();

			}
		});

		jbtn_mng_dorent.addActionListener(new ActionListener() { // ===========>회원에서 대여버튼 눌렀을때
			public void actionPerformed(ActionEvent a) {
				// 대여창에 이름과 아이디 뜨게 하기
				String memberInfo[] = new String[2];
				memberInfo[0] = jtf_mng_mname.getText();
				memberInfo[1] = jtf_mng_mid.getText();
				mRentView.setTextMember(memberInfo);
				mRentView.initDisplay();
			}
		});

		jbtn_mng_mem.setBounds(110, 10, 110, 38);
		jp_mng_second.add(jbtn_mng_mem);

		//jbtn_mng_ins.setBounds(330, 0, 141, 80);
		//jp_mng_second.add(jbtn_mng_ins);

		jtf_mng_search.setBounds(801, 20, 267, 42);
		jtf_mng_search.setBorder(new LineBorder(new Color(247, 183, 3), 3));
		jp_mng_second.add(jtf_mng_search);
		jtf_mng_search.setColumns(10);

		//jbtn_mng_upd.setBounds(444, 10, 110, 38);
		//jp_mng_second.add(jbtn_mng_upd);

		//jbtn_mng_del.setBounds(550, 10, 110, 38);
		//jp_mng_second.add(jbtn_mng_del);

		//jbtn_mng_search.setBounds(1040, 20,100, 50);
		//jp_mng_second.add(jbtn_mng_search);

		jcb_mng_mcate.setBounds(694, 20, 95, 42);
		jp_mng_second.add(jcb_mng_mcate);
		jcb_mng_mcate.setVisible(false);

		jcb_mng_rcate.setBounds(694, 20, 95, 42);
		jp_mng_second.add(jcb_mng_rcate);
		jcb_mng_rcate.setVisible(false);

		jp_mng_rrent.add(jsp_mng_rent);

	//=======================================================>

		jp_mng_mleft.add(jbtn_mng_dorent);
		jbtn_mng_clear.setBounds(157, 90, 56, 50);
		jp_mng_mleft.add(jbtn_mng_clear);
		jbtn_mng_clear2.setBounds(157, 90, 56, 50);
		jp_mng_bleft.add(jbtn_mng_clear2);
		jbtn_mng_dorent.setBounds(10, 10, 203, 79);
	
		//jp_mng_lrent.add(jbtn_mng_clear3);
		//jbtn_mng_clear3.setBounds(157, 90, 56, 50);

		jp_mng_bleft.add(jbtn_mng_homepagegogo);
		jbtn_mng_homepagegogo.setBounds(10, 10, 203, 79);
		jbtn_mng_homepagegogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gohomepage();
				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}
		});

		jp_mng_lrent.add(jbtn_mng_return);
		jbtn_mng_return.setBounds(10, 10, 203, 79);

		jp_mng_ldelay.add(jbtn_mng_dopay);///// 납부완료 버튼
		jbtn_mng_dopay.setBounds(10, 10, 203, 79);
		


		jp_mng_rrent.setLayout(null);
		jp_mng_lrent.setLayout(null);

		jp_mng_mright.setBounds(225, 116, 959, 735);
		jf_mngapp.getContentPane().add(jp_mng_mright);
		jp_mng_mright.setLayout(null);

		jp_mng_mright.add(jsp_mng_mem);
		jsp_mng_mem.setBounds(12, 10, 935, 715);

		///////// 대여화면 라벨+텍스트필드 //////////////

		//////////////////////////////////////////////////

		jf_mngapp.setVisible(true);
		jf_mngapp.setSize(1200, 900);
		jf_mngapp.getContentPane().setLayout(null);

		jp_mngtop.setBounds(0, 0, 1184, 30);
		jf_mngapp.getContentPane().add(jp_mngtop);
		jp_mngtop.setLayout(null);
		jp_mngtop.add(jmb_mng);

		jmb_mng.setBounds(0, 0, 1200, 30);

		jmb_mng.add(jm_mng_file);
		jm_mng_file.add(jmi_mng_file_exit);
		jmb_mng.add(jm_mng_edit);
		jm_mng_edit.add(jmi_mng_edit_ins);
		jm_mng_edit.add(jmi_mng_edit_upd);
		jm_mng_edit.add(jmi_mng_edit_del);

		jmb_mng.add(jm_mng_help);

		jm_mng_help.add(jmi_mng_drivertest);
		jm_mng_help.add(jmi_mng_dbtest);

		jp_mng_second.setBounds(0, 27, 1200, 91);
		jf_mngapp.getContentPane().add(jp_mng_second);
		jp_mng_second.setLayout(null);

		jbtn_mng_rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				page = 3;
				jp_mng_bleft.setVisible(false);
				jp_mng_bright.setVisible(false);
				jp_mng_mleft.setVisible(false);
				jp_mng_mright.setVisible(false);
				jp_mng_ldelay.setVisible(false);
				jp_mng_rdelay.setVisible(false);
				jp_mng_lrent.setVisible(true);//
				
				jp_mng_rrent.setVisible(true);
				jcb_mng_bcate.setVisible(false);
				jcb_mng_mcate.setVisible(false);
				jcb_mng_dcate.setVisible(false);
				jcb_mng_rcate.setVisible(true);

				System.out.println("rentRefreshData 호출 ");
				dtm_mng_rent.setRowCount(0);
				rentRefreshData();
			}
		});
		jbtn_mng_delay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page = 4;
				jp_mng_bleft.setVisible(false);
				jp_mng_bright.setVisible(false);
				jp_mng_mleft.setVisible(false);
				jp_mng_mright.setVisible(false);
				jp_mng_ldelay.setVisible(true);
				jp_mng_rdelay.setVisible(true);
				jp_mng_lrent.setVisible(false);
				jp_mng_rrent.setVisible(false);
				jcb_mng_bcate.setVisible(false);
				jcb_mng_mcate.setVisible(false);
				jcb_mng_rcate.setVisible(false);
				jcb_mng_dcate.setVisible(true);

				dtm_mng_delay.setRowCount(0);
				overRefreshData();
			}
		});

		///////// 버튼
		jbtn_mng_delay.setBounds(330, 2, 110, 38);
		jp_mng_second.add(jbtn_mng_delay);

		jbtn_mng_book.setBounds(0, 2, 110, 38);
		jp_mng_second.add(jbtn_mng_book);

		jbtn_mng_mem.setBounds(110, 2, 110, 38);
		jp_mng_second.add(jbtn_mng_mem);

		jbtn_mng_rent.setBounds(220, 2, 110, 38);
		jp_mng_second.add(jbtn_mng_rent);

		jbtn_mng_ins.setBounds(25, 50,50,35);
		jp_mng_second.add(jbtn_mng_ins);

		jbtn_mng_upd.setBounds(100, 50, 50, 35);
		jp_mng_second.add(jbtn_mng_upd);

		jbtn_mng_del.setBounds(180, 50, 50, 35);
		jp_mng_second.add(jbtn_mng_del);

		jbtn_mng_search.setBounds(1060,20, 80,42);
		jp_mng_second.add(jbtn_mng_search);

		//////////////////////////////////

		jtf_mng_search.setBounds(801, 20, 267, 42);
		jp_mng_second.add(jtf_mng_search);
		jtf_mng_search.setColumns(10);
		jcb_mng_bcate.setBounds(694, 20, 95, 42);
		jp_mng_second.add(jcb_mng_bcate);

		jf_mngapp.getContentPane().add(jp_mng_rrent);
		jp_mng_rrent.setBounds(225, 116, 959, 735);
		jp_mng_rrent.setVisible(false);
		jp_mng_rrent.add(jsp_mng_rent);
		jsp_mng_rent.setBounds(12, 10, 935, 715);

		jp_mng_bright.setBounds(225, 116, 959, 735);
		jf_mngapp.getContentPane().add(jp_mng_bright);
		jp_mng_bright.setLayout(null);

		jp_mng_bright.add(jsp_mng_book);
		jsp_mng_book.setBounds(12, 10, 935, 715);

		/*
		 * jf_mngapp.getContentPane().add(jp_mng_bleft);
		 * jtf_mng_quanty.add(jp_mng_bleft);
		 */

		jcb_mng_dcate.setBounds(694, 20, 95, 42);
		jp_mng_second.add(jcb_mng_dcate);

		jcb_mng_bcate.setBounds(694, 20, 95, 42);
		jp_mng_second.add(jcb_mng_bcate);

		jlb_mng_nameview.setBounds(360,50, 60, 30);
		jlb_mng_nameview.setFont(new Font("맑은 고딕",Font.BOLD,15));
		jp_mng_second.add(jlb_mng_nameview);

		jlb_mng_welcomement.setBounds(410, 50, 150, 30);
		jlb_mng_welcomement.setFont(new Font("",Font.BOLD,15));
		jp_mng_second.add(jlb_mng_welcomement);

		jbtn_mng_logout.setBounds(595,20,95,40);
		jp_mng_second.add(jbtn_mng_logout);

		jlb_mng_isbn.setBounds(12, 107, 189, 40);
		jp_mng_bleft.add(jlb_mng_isbn);
		jp_mng_bleft.add(jtf_mng_isbn);
		jlb_mng_name.setBounds(12, 196, 189, 40);
		jp_mng_bleft.add(jlb_mng_name);
		jp_mng_bleft.add(jtf_mng_name);
		jlb_mng_writer.setBounds(12, 286, 189, 40);
		jp_mng_bleft.add(jlb_mng_writer);
		jp_mng_bleft.add(jtf_mng_writer);
		jlb_mng_company.setBounds(12, 375, 189, 40);
		jp_mng_bleft.add(jlb_mng_company);
		jp_mng_bleft.add(jtf_mng_company);
		jlb_mng_year.setBounds(12, 465, 189, 40);
		jp_mng_bleft.add(jlb_mng_year);
		jp_mng_bleft.add(jtf_mng_year);
		jlb_mng_price.setBounds(12, 555, 189, 40);
		jp_mng_bleft.add(jlb_mng_price);
		jp_mng_bleft.add(jtf_mng_price);
		jlb_mng_quanty.setBounds(12, 645, 189, 40);
		jp_mng_bleft.add(jlb_mng_quanty);
		jp_mng_bleft.add(jtf_mng_quanty);

		jp_mng_bright.setBounds(225, 116, 959, 735);
		jf_mngapp.getContentPane().add(jp_mng_bright);
		jp_mng_bright.setLayout(null);

		jp_mng_bright.add(jsp_mng_book);
		jsp_mng_book.setBounds(12, 10, 935, 715);

		jp_mng_mleft.setVisible(false);
		jp_mng_lrent.setVisible(false);
		jp_mng_rrent.setVisible(false);
		jp_mng_mright.setVisible(false);
		jp_mng_bleft.setVisible(true);
		jp_mng_bright.setVisible(true);
		jp_mng_ldelay.setVisible(false);
		jp_mng_rdelay.setVisible(false);

		jf_mngapp.setResizable(false);
		Dimension frameSize = jf_mngapp.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jf_mngapp.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		////////////////////////////////////////////////////////
		/////// 연체화면 추가

		jp_mng_rdelay.setLayout(null);
		jp_mng_ldelay.setLayout(null);
		jp_mng_ldelay.setBounds(0, 116, 213, 735);
		jf_mngapp.getContentPane().add(jp_mng_ldelay);
		jp_mng_ldelay.setLayout(null);

		jp_mng_rdelay.setBounds(225, 116, 959, 735);
		jf_mngapp.getContentPane().add(jp_mng_rdelay);
		jp_mng_rdelay.setLayout(null);

		jp_mng_rdelay.add(jsp_mng_delay);
		jsp_mng_delay.setBounds(12, 10, 935, 715);

		////////////////////////////////////////////////////
		// 정렬추가

		//////////////////////////////////////////////////////////////////////////////////////
		// 꾸며보자~ㅎㅎ

		jmb_mng.setBackground(Color.WHITE);
		jf_mngapp.setBackground(Color.WHITE);
		jp_mng_bleft.setBackground(Color.WHITE);
		jp_mng_bright.setBackground(Color.WHITE);
		jp_mng_rrent.setBackground(Color.WHITE);
		jp_mng_lrent.setBackground(Color.WHITE);
		jp_mng_mleft.setBackground(Color.WHITE);
		jp_mng_mright.setBackground(Color.WHITE);
		jp_mng_ldelay.setBackground(Color.WHITE);
		jp_mng_rdelay.setBackground(Color.WHITE);
		jp_mng_second.setBackground(Color.WHITE);
		jp_mngtop.setBackground(Color.WHITE);
		jf_mngapp.getContentPane().setBackground(Color.WHITE);

		jcb_mng_bcate.setBackground(Color.WHITE);
		jcb_mng_mcate.setBackground(Color.WHITE);
		jcb_mng_rcate.setBackground(Color.WHITE);
		jcb_mng_dcate.setBackground(Color.white);

		jbtn_mng_book.setBorderPainted(false);
		jbtn_mng_clear.setBorderPainted(false);
		jbtn_mng_clear2.setBorderPainted(false);
		jbtn_mng_clear3.setBorderPainted(false);
		jbtn_mng_del.setBorderPainted(false);

		jbtn_mng_dorent.setBorderPainted(false);
		jbtn_mng_return.setBorderPainted(false);// 반납완료
		jbtn_mng_dopay.setBorderPainted(false);// 납부완료
		jbtn_mng_ins.setBorderPainted(false);
		jbtn_mng_logout.setBorderPainted(false);
		jbtn_mng_mem.setBorderPainted(false);
		jbtn_mng_rent.setBorderPainted(false);
		jbtn_mng_delay.setBorderPainted(false);

		jbtn_mng_search.setBorderPainted(false);
		jbtn_mng_upd.setBorderPainted(false);

		jbtn_mng_del.setBackground(Color.WHITE);
		jbtn_mng_logout.setBackground(Color.WHITE);
		jbtn_mng_upd.setBackground(Color.white);
		jbtn_mng_ins.setBackground(Color.white);
		jbtn_mng_mem.setBackground(Color.white);
		jbtn_mng_book.setBackground(Color.WHITE);
		jbtn_mng_rent.setBackground(Color.WHITE);
		jbtn_mng_delay.setBackground(Color.WHITE);
		jbtn_mng_clear.setBackground(Color.white);
		jbtn_mng_clear2.setBackground(Color.white);
		jbtn_mng_clear3.setBackground(Color.white);

		jbtn_mng_book.setFont(f_title);
		jbtn_mng_mem.setFont(f_title);
		jbtn_mng_rent.setFont(f_title);
		jbtn_mng_delay.setFont(f_title);

		jbtn_mng_book.setForeground(Color.DARK_GRAY);
		jbtn_mng_mem.setForeground(Color.DARK_GRAY);
		jbtn_mng_rent.setForeground(Color.DARK_GRAY);
		jbtn_mng_delay.setForeground(Color.DARK_GRAY);

		 jbtn_mng_logout.setIcon(new ImageIcon(path + "로그아웃2.png"));
		jbtn_mng_upd.setIcon(new ImageIcon(path + "수정1.png"));
		jbtn_mng_del.setIcon(new ImageIcon(path + "삭제1.png"));
		jbtn_mng_ins.setIcon(new ImageIcon(path + "입력1.png"));

		jbtn_mng_clear.setIcon(new ImageIcon(path + "클리어버튼.png"));
		jbtn_mng_clear2.setIcon(new ImageIcon(path + "클리어버튼.png"));
		// jbtn_mng_clear3.setIcon(new ImageIcon(path + "클리어버튼.png"));

		jbtn_mng_search.setIcon(new ImageIcon(path + "검색1.jpg"));
		jbtn_mng_homepagegogo.setIcon(new ImageIcon(path + "도서관홈피아이콘.png"));

		jbtn_mng_dorent.setBackground(new Color(251, 190, 19));
		jbtn_mng_return.setBackground(new Color(251, 190, 19));// 반납완료
		jbtn_mng_dopay.setBackground(new Color(251, 190, 19));// 납부완료
		jbtn_mng_dorent.setFont(f);
		jbtn_mng_return.setFont(f);
		jbtn_mng_dopay.setFont(f);

		/// jth_mng_book.setFont();
		jth_mng_book.setBackground(Color.orange);
		jth_mng_book.setReorderingAllowed(false);

		jth_mng_mem.setBackground(Color.orange);
		jth_mng_mem.setReorderingAllowed(false);

		jth_mng_rent.setBackground(Color.orange);
		jth_mng_rent.setReorderingAllowed(false);

		jth_mng_delay.setBackground(Color.orange);
		jth_mng_delay.setReorderingAllowed(false);

		jth_mng_book.setFont(f_mng_header);
		jth_mng_mem.setFont(f_mng_header);
		jth_mng_rent.setFont(f_mng_header);
		jth_mng_delay.setFont(f_mng_header);

		jf_mngapp.add(jp_mng_lrent);
		jf_mngapp.add(jp_mng_rrent);
		jp_mng_lrent.setBounds(0, 116, 213, 735);

	}

	public void gohomepage() throws Exception {
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("https://cdn.discordapp.com/attachments/373498871726342146/526653562093305867/unknown.png"));
	}

	public ManagerBookApp() {
	}

	// 생성자
	public ManagerBookApp(String mname) {
		jlb_mng_nameview.setText(mname);
		initDisplay();
		bookRefreshData(pbVO);
		memberRefreshData();
		rentRefreshData();
		overRefreshData();

	}

	public static void main(String[] arg) {
		new ManagerBookApp("이지윤");

	}

	// 로우선택시 텍스트 옆창에 뜨게하기
	public void bookUpdateText() {

		int index[] = jt_mng_book.getSelectedRows();
		String[] bookInfo = new String[7];
		if (index.length == 0) {
			JOptionPane.showMessageDialog(jf_mngapp, "수정할 데이터를 선택하세요", "Error", JOptionPane.ERROR_MESSAGE);
			return;

		} else {

			for (int i = 0; i < dtm_mng_book.getRowCount(); i++) {
				for (int j = 0; j < bookInfo.length; j++) {
					if (jt_mng_book.isRowSelected(i)) {
						bookInfo[j] = (String) dtm_mng_book.getValueAt(i, j);
						if (j == 0) {
							isbn = bookInfo[j]; // 원래 isbn 저장
						}
					}

				}
				// 로우선택시 텍스트 뜨게하기
				setBookText(bookInfo);
			}
		}
	}

	// 회원에서 로우선택시 텍스트 옆창에 뜨게하기
	public void memberUpdateText() {

		int index[] = jt_mng_mem.getSelectedRows();
		String[] memInfo = new String[7];
		if (index.length == 0) {// 수정할 데이터를 선택하세요
			JOptionPane.showMessageDialog(jt_mng_mem, "수정할 데이터를 선택하세요", "Error", JOptionPane.ERROR_MESSAGE);
			return;

		} else {

			for (int i = 0; i < dtm_mng_mem.getRowCount(); i++) {
				for (int j = 0; j < memInfo.length; j++) {
					if (jt_mng_mem.isRowSelected(i)) {
						memInfo[j] = (String) dtm_mng_mem.getValueAt(i, j);
						if (j == 1) {
							memid = memInfo[j]; // 원래 아이디 저장
						}

					}
				}

			}
			// 로우선택시 텍스트 뜨게하기
			setMemberText(memInfo);

		}
	}

	// 연체에서 로우선택시 텍스트 옆창에 뜨게하기

	public void setBookText(String[] bookInfo) {// 텍스트 필드에 값을 넣어주기 (쓰기)
		int k = 0;
		jtf_mng_isbn.setText(bookInfo[k++]);
		jtf_mng_name.setText(bookInfo[k++]);
		jtf_mng_writer.setText(bookInfo[k++]);
		jtf_mng_company.setText(bookInfo[k++]);
		jtf_mng_year.setText(bookInfo[k++]);
		jtf_mng_price.setText(bookInfo[k++]);
		jtf_mng_quanty.setText(bookInfo[k++]);
	}

	public String[] getBookText() {// 텍스트필트장에 있는 데이터 읽어오기
		int k = 0;// 초기화

		String bookText[] = new String[7];
		bookText[k++] = jtf_mng_isbn.getText();
		bookText[k++] = jtf_mng_name.getText();
		bookText[k++] = jtf_mng_writer.getText();
		bookText[k++] = jtf_mng_company.getText();
		bookText[k++] = jtf_mng_year.getText();
		bookText[k++] = jtf_mng_price.getText();
		bookText[k++] = jtf_mng_quanty.getText();
		return bookText;
	}

	public void setMemberText(String[] memInfo) {// 텍스트 필드에 값을 넣어주기 (쓰기)
		int k = 0;
		jtf_mng_mname.setText(memInfo[k++]);
		jtf_mng_mid.setText(memInfo[k++]);
		jtf_mng_mpw.setText(memInfo[k++]);
		jtf_mng_mhp.setText(memInfo[k++]);
		jtf_mng_maddr.setText(memInfo[k++]);
		jtf_mng_mbirth.setText(memInfo[k++]);
		jtf_mng_mgender.setText(memInfo[k++]);
	}

	public String[] getMemberText() {// 텍스트필트장에 있는 데이터 읽어오기
		int k = 0;// 초기화

		String memText[] = new String[7];
		memText[k++] = jtf_mng_mname.getText();
		memText[k++] = jtf_mng_mid.getText();
		memText[k++] = jtf_mng_mpw.getText();
		memText[k++] = jtf_mng_mhp.getText();
		memText[k++] = jtf_mng_maddr.getText();
		memText[k++] = jtf_mng_mbirth.getText();
		memText[k++] = jtf_mng_mgender.getText();
		return memText;
	}

	// 삽입- insert
	public void bookInsert() {
		String bookIns[] = getBookText();
		BookVO pbvo = new BookVO(bookIns[0], bookIns[1], bookIns[2], bookIns[3], bookIns[4], bookIns[5], bookIns[6]);
		pbvo.setCommand("insert");
		BookVO rbvo = mBookCtrl.send(pbvo);
		if (rbvo.getResult() == 1) {// 삽입성공
			JOptionPane.showMessageDialog(jf_mngapp, "추가 완료 ", "INFO", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(jf_mngapp, "추가 실패-데이터가 올바르지 않습니다. ", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	// isbn bname author publisher pdate price stock

	// 업데이트-update
	public void bookUpdate() {
		// 텍스프필드창에서 수정한데이터 가져오기 - 업데이트 적용

		String bookUpd[] = getBookText();
		BookVO pbvo = new BookVO(this.isbn, bookUpd[0], bookUpd[1], bookUpd[2], bookUpd[3], bookUpd[4], bookUpd[5],
				bookUpd[6]);
		pbvo.setCommand("update");
		BookVO rbvo = mBookCtrl.send(pbvo);
		if (rbvo.getResult() == 1) {// 수정성공

			JOptionPane.showMessageDialog(jf_mngapp, "업데이트 완료", "INFO", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(jf_mngapp, "업데이트 실패 -데이터가 올바르지 않습니다.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		// bookRefreshData();
	}

	public void bookDelete() {
		int index[] = jt_mng_book.getSelectedRows();
		if (index.length == 0) {
			JOptionPane.showMessageDialog(jf_mngapp, "삭제할 데이터를 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			for (int i = 0; i < dtm_mng_book.getRowCount(); i++) {
				if (jt_mng_book.isRowSelected(i)) {
					String p_isbn = (String) dtm_mng_book.getValueAt(i, 0);
					BookVO pbvo = new BookVO();
					pbvo.setCommand("delete");
					pbvo.setIsbn(p_isbn);
					BookVO rbvo = mBookCtrl.send(pbvo);
					if (rbvo.getResult() == 1) {// 삭제성공
						JOptionPane.showMessageDialog(jt_mng_book, "삭제 완료", "INFO", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}

		}
	}

	public void bookRefreshData(BookVO pbVO) {

		if (pbVO != null) {
			System.out.println("pbVO에 search가 있을 때 : " + pbVO.getSearch());

		}
		if (pbVO == null) {// 삭제 처리 후 새로고침 처리시에는 null이 넘어온다.
			// 만일 이 부분이 실행이 되면 제목, 저자 , 내용 이부분이 null이 되는 것이고
			// 이유는 null일 경우 새로 인스턴스화를 하기 때문에.....
			System.out.println("pbVO에 search를 선택 안했을때 :" + pbVO.getSearch());
			pbVO = new BookVO();
		}
		pbVO.setCommand("select");
		List<BookVO> bookList = mBookCtrl.sendALL();
		BookVO[] bvos = null;
		bvos = new BookVO[bookList.size()];
		// 배열에서 꺼낸 정보를 DefaultTableModel에 넣어준다.- 그래야 화면에 출력됨.
		for (int i = 0; i < bookList.size(); i++) {
			Vector oneRow = new Vector();
			BookVO bvo1 = new BookVO(bookList.get(i).getIsbn(), bookList.get(i).getBname(), bookList.get(i).getAuthor(),
					bookList.get(i).getPublisher(), bookList.get(i).getPdate(), bookList.get(i).getPrice(),
					bookList.get(i).getStock());

			bvos[i] = bvo1;
			oneRow.add(bvos[i].getIsbn());
			oneRow.add(bvos[i].getBname());
			oneRow.add(bvos[i].getAuthor());
			oneRow.add(bvos[i].getPublisher());
			oneRow.add(bvos[i].getPdate());
			oneRow.add(bvos[i].getPrice());
			oneRow.add(bvos[i].getStock());

			System.out.println();

			// dtm_mng_book.addRow(bvos);
			dtm_mng_book.addRow(oneRow);
		}
	}

	public void booksearch(BookVO pbVO) {
		// 기존 테이블의 DefalultTableModel에 담겨 있는 데이터로우를 삭제하기
		while (dtm_mng_book.getRowCount() > 0) {
			// removeRow파라미터에 상수로 0을 준 이유는 삭제되고 나면 로우가 하나씩 당겨지므로
			// 반복해서 0번째 로우를 삭제하기 위해...
			dtm_mng_book.removeRow(0);
		}
		System.out.println("booksearch 호출");
		if (pbVO != null) {
			System.out.println("pbVO에 search가 있을 때 : " + pbVO.getSearch());

		}
		if (pbVO == null) {// 삭제 처리 후 새로고침 처리시에는 null이 넘어온다.
			// 만일 이 부분이 실행이 되면 제목, 저자 , 내용 이부분이 null이 되는 것이고
			// 이유는 null일 경우 새로 인스턴스화를 하기 때문에.....
			System.out.println("pbVO에 search를 선택 안했을때 :" + pbVO.getSearch());
			pbVO = new BookVO();
			System.out.println("이건실행되면 안되");
		}
		pbVO.setCommand("search");
		List<BookVO> bookList = mBookCtrl.sendSearch(pbVO);
		BookVO[] bvos = null;
		bvos = new BookVO[bookList.size()];
		// 배열에서 꺼낸 정보를 DefaultTableModel에 넣어준다.- 그래야 화면에 출력됨.
		for (int i = 0; i < bookList.size(); i++) {
			Vector oneRow = new Vector();
			BookVO bvo1 = new BookVO(bookList.get(i).getIsbn(), bookList.get(i).getBname(), bookList.get(i).getAuthor(),
					bookList.get(i).getPublisher(), bookList.get(i).getPdate(), bookList.get(i).getPrice(),
					bookList.get(i).getStock());

			bvos[i] = bvo1;
			oneRow.add(bvos[i].getIsbn());
			oneRow.add(bvos[i].getBname());
			oneRow.add(bvos[i].getAuthor());
			oneRow.add(bvos[i].getPublisher());
			oneRow.add(bvos[i].getPdate());
			oneRow.add(bvos[i].getPrice());
			oneRow.add(bvos[i].getStock());

			// dtm_mng_book.addRow(bvos);
			dtm_mng_book.addRow(oneRow);
		}
		System.out.println("테스트값 : " + bvos[0].getAuthor());
	}

	///////////////////////////////////////////////////////////////////////////// 도서

	// 회원 수정
	public void memberUpdate() {
		// 텍스프필드창에서 수정한데이터 가져오기 - 업데이트 적용

		String memberUpd[] = this.getMemberText();
		MemberVO pmVO = new MemberVO(memberUpd[0], this.memid, memberUpd[1], memberUpd[2], memberUpd[3], memberUpd[4],
				memberUpd[5], memberUpd[6]);
		pmVO.setCommand("update");

		MemberVO rmVO = memCtrl.send(pmVO, this);
		for (int i = 0; i < memberUpd.length; i++) {
			if (memberUpd[i].equals("")) {
				rmVO.setResult(0);
			}
		}
		if (rmVO.getResult() == 1) {// 수정성공

			JOptionPane.showMessageDialog(jf_mngapp, "업데이트 완료", "INFO", JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(jf_mngapp, "업데이트 실패 -데이터가 올바르지 않습니다.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		// bookRefreshData();
	}

	// 회원삭제
	public void memberDelete() {
		int index[] = jt_mng_mem.getSelectedRows();
		if (index.length == 0) {
			JOptionPane.showMessageDialog(jt_mng_mem, "삭제할 데이터를 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			for (int i = 0; i < dtm_mng_mem.getRowCount(); i++) {
				if (jt_mng_mem.isRowSelected(i)) {
					String p_memid = (String) dtm_mng_mem.getValueAt(i, 1);
					System.out.println("삭제할 데이터 : " + p_memid);
					MemberVO pmvo = new MemberVO();
					pmvo.setCommand("delete");
					pmvo.setMemid(p_memid);
					MemberVO rmvo = memCtrl.send(pmvo, this);
					if (rmvo.getResult() == 1) {// 삭제성공
						JOptionPane.showMessageDialog(jt_mng_mem, "삭제 완료", "INFO", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}

		}
	}

	public void memberRefreshData() {

		MemberVO mvo = new MemberVO();
		mvo.setCommand("select");
		List<MemberVO> memberList = memCtrl.send_mALL();
		MemberVO[] bvos = null;
		bvos = new MemberVO[memberList.size()];
		// 배열에서 꺼낸 정보를 DefaultTableModel에 넣어준다.- 그래야 화면에 출력됨.
		for (int i = 0; i < memberList.size(); i++) {
			Vector oneRow = new Vector();
			MemberVO bvo1 = new MemberVO(memberList.get(i).getMname(), memberList.get(i).getMemid(),
					memberList.get(i).getMempw(), memberList.get(i).getTel(), memberList.get(i).getAddress(),
					memberList.get(i).getBirthday(), memberList.get(i).getGender());

			bvos[i] = bvo1;
			oneRow.add(bvos[i].getMname());
			oneRow.add(bvos[i].getMemid());
			oneRow.add(bvos[i].getMempw());
			oneRow.add(bvos[i].getTel());
			oneRow.add(bvos[i].getAddress());
			oneRow.add(bvos[i].getBirthday());
			oneRow.add(bvos[i].getGender());

			System.out.println();

			// dtm_mng_book.addRow(bvos);
			dtm_mng_mem.addRow(oneRow);
		}
	}

	////////////////////////////////////////////////// 회원검색
	public void membersearch(MemberVO pmVO) {
		// 기존 테이블의 DefalultTableModel에 담겨 있는 데이터로우를 삭제하기
		while (dtm_mng_mem.getRowCount() > 0) {
			// removeRow파라미터에 상수로 0을 준 이유는 삭제되고 나면 로우가 하나씩 당겨지므로
			// 반복해서 0번째 로우를 삭제하기 위해...
			dtm_mng_mem.removeRow(0);
		}
		System.out.println("membersearch 호출");
		if (pmVO != null) {
			System.out.println("pmVO에 search가 있을 때 : " + pmVO.getSearch());

		}
		if (pmVO == null) {// 삭제 처리 후 새로고침 처리시에는 null이 넘어온다.
			// 만일 이 부분이 실행이 되면 제목, 저자 , 내용 이부분이 null이 되는 것이고
			// 이유는 null일 경우 새로 인스턴스화를 하기 때문에.....
			System.out.println("pmVO에 search를 선택 안했을때 :" + pmVO.getSearch());
			pmVO = new MemberVO();
			System.out.println("이건실행되면 안되");
		}
		pmVO.setCommand("search");
		List<MemberVO> memberList = memCtrl.sendSearch(pmVO);
		MemberVO[] mvos = null;
		mvos = new MemberVO[memberList.size()];
		// 배열에서 꺼낸 정보를 DefaultTableModel에 넣어준다.- 그래야 화면에 출력됨.
		for (int i = 0; i < memberList.size(); i++) {
			Vector oneRow = new Vector();
			MemberVO bvo1 = new MemberVO(memberList.get(i).getMname(), memberList.get(i).getMemid(),
					memberList.get(i).getMempw(), memberList.get(i).getTel(), memberList.get(i).getAddress(),
					memberList.get(i).getBirthday(), memberList.get(i).getGender());

			mvos[i] = bvo1;
			oneRow.add(mvos[i].getMname());
			oneRow.add(mvos[i].getMemid());
			oneRow.add(mvos[i].getMempw());
			oneRow.add(mvos[i].getTel());
			oneRow.add(mvos[i].getAddress());
			oneRow.add(mvos[i].getBirthday());
			oneRow.add(mvos[i].getGender());

			// dtm_mng_mem.addRow(mvos);
			dtm_mng_mem.addRow(oneRow);
		}
		System.out.println("테스트값 : " + mvos[0].getMname());
	}
	/////////////////////////////////////////////////////////////////////////////////// end
	/////////////////////////////////////////////////////////////////////////////////// of
	/////////////////////////////////////////////////////////////////////////////////// 회원
	// 대출 관리 -전체조회

	public void rentRefreshData() {
		// "대출번호", "상세번호" ,"회원이름","아이디","ISBN", "도서명", "대출일자","반납예정", "반납현황"
		RentalMasterVO rmvo = new RentalMasterVO();
		BookVO bvo = null;
		MemberVO mvo = null;
		RentalDetailVO rdvo = null;
		rmvo.setCommand("select");
		List<RentalMasterVO> rentList = mRentCtrl.send_rALL();
		RentalMasterVO[] rmvos = null;
		rmvos = new RentalMasterVO[rentList.size()];

		for (int i = 0; i < rentList.size(); i++) {
			Vector oneRow = new Vector();
			RentalMasterVO rmvo1 = new RentalMasterVO(rentList.get(i).getRentno(), rentList.get(i).getRdvo(),
					rentList.get(i).getRdvo().getDetailid(), rentList.get(i).getMvo(),
					rentList.get(i).getMvo().getMname(), rentList.get(i).getMvo().getMemid(), rentList.get(i).getBvo(),
					rentList.get(i).getBvo().getIsbn(), rentList.get(i).getBvo().getBname(),
					rentList.get(i).getRentdate(), rentList.get(i).getReturndate(),
					rentList.get(i).getRdvo().getRental_now());

			rmvos[i] = rmvo1;
			oneRow.add(rmvos[i].getRentno());
			oneRow.add(rmvos[i].getRdvo().getDetailid());
			oneRow.add(rmvos[i].getMvo().getMname());
			oneRow.add(rmvos[i].getMvo().getMemid());
			oneRow.add(rmvos[i].getBvo().getIsbn());
			oneRow.add(rmvos[i].getBvo().getBname());
			oneRow.add(rmvos[i].getRentdate());
			oneRow.add(rmvos[i].getReturndate());
			oneRow.add(rmvos[i].getRdvo().getRental_now());

			System.out.println();

			// dtm_mng_book.addRow(bvos);
			dtm_mng_rent.addRow(oneRow);
		}
	}

	///////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// 대출검색
	public void rentsearch(RentalMasterVO prmVO) {
		// 기존 테이블의 DefalultTableModel에 담겨 있는 데이터로우를 삭제하기
		while (dtm_mng_rent.getRowCount() > 0) {
			// removeRow파라미터에 상수로 0을 준 이유는 삭제되고 나면 로우가 하나씩 당겨지므로
			// 반복해서 0번째 로우를 삭제하기 위해...
			dtm_mng_rent.removeRow(0);
		}
		System.out.println("rentsearch 호출");
		if (prmVO != null) {
			System.out.println("prmVO에 search가 있을 때 : " + prmVO.getSearch());

		}
		if (prmVO == null) {// 삭제 처리 후 새로고침 처리시에는 null이 넘어온다.
			// 만일 이 부분이 실행이 되면 제목, 저자 , 내용 이부분이 null이 되는 것이고
			// 이유는 null일 경우 새로 인스턴스화를 하기 때문에.....
			System.out.println("prmVO에 search를 선택 안했을때 :" + prmVO.getSearch());
			prmVO = new RentalMasterVO();
			System.out.println("이건실행되면 안되");
		}
		prmVO.setCommand("search");
		List<RentalMasterVO> rentList = mRentCtrl.sendSearch(prmVO);
		RentalMasterVO[] rmvos = null;
		rmvos = new RentalMasterVO[rentList.size()];
		// 배열에서 꺼낸 정보를 DefaultTableModel에 넣어준다.- 그래야 화면에 출력됨.
		for (int i = 0; i < rentList.size(); i++) {
			Vector oneRow = new Vector();
			RentalMasterVO rmvo1 = new RentalMasterVO(rentList.get(i).getRentno(), rentList.get(i).getRdvo(),
					rentList.get(i).getRdvo().getDetailid(), rentList.get(i).getMvo(),
					rentList.get(i).getMvo().getMname(), rentList.get(i).getMvo().getMemid(), rentList.get(i).getBvo(),
					rentList.get(i).getBvo().getIsbn(), rentList.get(i).getBvo().getBname(),
					rentList.get(i).getRentdate(), rentList.get(i).getReturndate(),
					rentList.get(i).getRdvo().getRental_now());

			rmvos[i] = rmvo1;
			oneRow.add(rmvos[i].getRentno());
			oneRow.add(rmvos[i].getRdvo().getDetailid());
			oneRow.add(rmvos[i].getMvo().getMname());
			oneRow.add(rmvos[i].getMvo().getMemid());
			oneRow.add(rmvos[i].getBvo().getIsbn());
			oneRow.add(rmvos[i].getBvo().getBname());
			oneRow.add(rmvos[i].getRentdate());
			oneRow.add(rmvos[i].getReturndate());
			oneRow.add(rmvos[i].getRdvo().getRental_now());

			// dtm_mng_mem.addRow(mvos);
			dtm_mng_rent.addRow(oneRow);
		}
		System.out.println("테스트값 : " + rmvos[0].getRentno());
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	// 관리자 - 대출 삭제
	public int rentDelete(int num) {
		int result = 0;
		int index[] = jt_mng_rent.getSelectedRows();
		if (index.length == 0) {
			JOptionPane.showMessageDialog(jf_mngapp, "삭제할 데이터를 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			return result;
		} else {
			for (int i = 0; i < dtm_mng_rent.getRowCount(); i++) {
				if (jt_mng_rent.isRowSelected(i)) {
					int p_rentno = (int) dtm_mng_rent.getValueAt(i, 0);
					int p_detailId = (int) dtm_mng_rent.getValueAt(i, 1);
					RentalMasterVO prmvo = new RentalMasterVO();
					RentalDetailVO prdvo = new RentalDetailVO();
					prmvo.setCommand("delete");
					prmvo.setRentno(p_rentno);

					prdvo.setDetailid(p_detailId);
					prmvo.setRdvo(prdvo);

					RentalMasterVO rrmvo = this.mRentCtrl.send(prmvo, num);
					result = rrmvo.getResult();

				}
			}

		}
		return result;
	}

	// 대출 반납 완료
	public void returnComplete() {
		System.out.println("returnComplete호출완료");
		int index[] = jt_mng_rent.getSelectedRows();
		if (index.length == 0) {
			JOptionPane.showMessageDialog(jf_mngapp, "반납할 데이터를 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			System.out.println("여긴 나와?");
			System.out.println(dtm_mng_rent.getRowCount());
			System.out.println("dfdf" + jt_mng_rent.isRowSelected(1));
			for (int i = 0; i < dtm_mng_rent.getRowCount(); i++) {
				if (jt_mng_rent.isRowSelected(i)) {
					int detailid = (int) dtm_mng_rent.getValueAt(i, 1);
					System.out.println(detailid);
					mRentCtrl.send_returnComplete(detailid);
				}

			}
		}
	}

	// 연체 화면 ===> select문 전체조회
	public void overRefreshData() {
		// 상세번호, 회원이름, 아이디, ISBN, 도서명, 반납예정일, 연체일수, 연체료, 납부현황

		OverdueVO odVO = new OverdueVO();
		odVO.setCommand("select");
		List<OverdueVO> overList = mOverCtrl.send_ALL();
		OverdueVO[] odvos = null;
		odvos = new OverdueVO[overList.size()];
		// 배열에서 꺼낸 정보를 DefaultTableModel에 넣어준다.- 그래야 화면에 출력됨.
		for (int i = 0; i < overList.size(); i++) {
			Vector oneRow = new Vector();
			OverdueVO odvo = new OverdueVO(overList.get(i).getMvo(), overList.get(i).getBvo(),
					overList.get(i).getRmvo(), overList.get(i).getRdvo(), overList.get(i).getRdvo().getDetailid(),
					overList.get(i).getMvo().getMname(), overList.get(i).getMvo().getMemid(),
					overList.get(i).getBvo().getIsbn(), overList.get(i).getBvo().getBname(),
					overList.get(i).getRmvo().getReturndate(), overList.get(i).getOverduecount(),
					overList.get(i).getOverduepay(), overList.get(i).getOverpay_now());

			odvos[i] = odvo;
			oneRow.add(odvos[i].getRdvo().getDetailid());
			oneRow.add(odvos[i].getMvo().getMname());
			oneRow.add(odvos[i].getMvo().getMemid());
			oneRow.add(odvos[i].getBvo().getIsbn());
			oneRow.add(odvos[i].getBvo().getBname());
			oneRow.add(odvos[i].getRmvo().getReturndate());
			oneRow.add(odvos[i].getOverduecount());
			oneRow.add(odvos[i].getOverduepay());
			oneRow.add(odvos[i].getOverpay_now());

			System.out.println();

			// dtm_mng_book.addRow(bvos);
			dtm_mng_delay.addRow(oneRow);
		}
	}
///////////////////////////////////////////////////////////////
//////////////////////////////////////////////////연체검색
	public void oversearch(OverdueVO ovVO) {
		//기존 테이블의 DefalultTableModel에 담겨 있는 데이터로우를 삭제하기
		while (dtm_mng_delay.getRowCount() > 0) {
			//removeRow파라미터에 상수로 0을 준 이유는 삭제되고 나면 로우가 하나씩 당겨지므로
			//반복해서 0번째 로우를 삭제하기 위해...
			dtm_mng_delay.removeRow(0);
		}
		System.out.println("oversearch 호출");
		if (ovVO != null) {
			System.out.println("ovVO에 search가 있을 때 : " + ovVO.getSearch());
		}
		if (ovVO == null) {// 삭제 처리 후 새로고침 처리시에는 null이 넘어온다.
			//만일 이 부분이 실행이 되면 제목, 저자 , 내용 이부분이 null이 되는 것이고
			//이유는 null일 경우 새로 인스턴스화를 하기 때문에.....
			System.out.println("ovVO에 search를 선택 안했을때 :" + ovVO.getSearch());
			ovVO = new OverdueVO();
			System.out.println("이건실행되면 안되");
		}
		ovVO.setCommand("search");
		List<OverdueVO> overList = mOverCtrl.sendSearch(ovVO);
		OverdueVO[] odvos = null;
		odvos = new OverdueVO[overList.size()];
		//배열에서 꺼낸 정보를 DefaultTableModel에 넣어준다.- 그래야 화면에 출력됨.
		for (int i = 0; i < overList.size(); i++) {
			Vector oneRow = new Vector();
			OverdueVO odvo = new OverdueVO(overList.get(i).getMvo(), overList.get(i).getBvo(),
					overList.get(i).getRmvo(), overList.get(i).getRdvo(), overList.get(i).getRdvo().getDetailid(),
					overList.get(i).getMvo().getMname(), overList.get(i).getMvo().getMemid(),
					overList.get(i).getBvo().getIsbn(), overList.get(i).getBvo().getBname(),
					overList.get(i).getRmvo().getReturndate(), overList.get(i).getOverduecount(),
					overList.get(i).getOverduepay(), overList.get(i).getOverpay_now());
			odvos[i] = odvo;
			oneRow.add(odvos[i].getRdvo().getDetailid());
			oneRow.add(odvos[i].getMvo().getMname());
			oneRow.add(odvos[i].getMvo().getMemid());
			oneRow.add(odvos[i].getBvo().getIsbn());
			oneRow.add(odvos[i].getBvo().getBname());
			oneRow.add(odvos[i].getRmvo().getReturndate());
			oneRow.add(odvos[i].getOverduecount());
			oneRow.add(odvos[i].getOverduepay());
			oneRow.add(odvos[i].getOverpay_now());
			System.out.println();
// dtm_mng_book.addRow(bvos);
			dtm_mng_delay.addRow(oneRow);
		}

	}

	// 연체 납부 완료
	public void payComplete() {
		System.out.println("payComplete호출완료");
		int index[] = jt_mng_delay.getSelectedRows();
		if (index.length == 0) {
			JOptionPane.showMessageDialog(jf_mngapp, "납부완료할 데이터를 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			System.out.println("여긴 나와?");
			System.out.println(dtm_mng_delay.getRowCount());
			System.out.println("값" + jt_mng_delay.isRowSelected(1));
			for (int i = 0; i < dtm_mng_delay.getRowCount(); i++) {
				if (jt_mng_delay.isRowSelected(i)) {
					int detailid = (int) dtm_mng_delay.getValueAt(i, 0);
					System.out.println(detailid);
					mOverCtrl.send_payComplete(detailid);
				}

			}
		}
	}

	// 연체 삭제 완료
	public void overDelete() {
		System.out.println("overDelete호출완료");
		int index[] = jt_mng_delay.getSelectedRows();
		if (index.length == 0) {
			JOptionPane.showMessageDialog(jf_mngapp, "삭제할 데이터를 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			System.out.println("여긴 나와?");
			System.out.println(dtm_mng_delay.getRowCount());
			System.out.println("값" + jt_mng_delay.isRowSelected(1));
			for (int i = 0; i < dtm_mng_delay.getRowCount(); i++) {
				if (jt_mng_delay.isRowSelected(i)) {
					if ("완납".equals((String) dtm_mng_delay.getValueAt(i, 8))) {
						int detailid = (int) dtm_mng_delay.getValueAt(i, 0);
						System.out.println(detailid);
						mOverCtrl.send_overdelete(detailid);
					} else if ("미납".equals((String) dtm_mng_delay.getValueAt(i, 8))) {
						JOptionPane.showMessageDialog(jf_mngapp, "미납인 데이터는 삭제가 불가합니다.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == jbtn_mng_upd || obj == jmi_mng_edit_upd) {// 업데이트 처리 수정
			if (page == 1) {

				bookUpdate();
				dtm_mng_book.setRowCount(0);// 데이터지우고 다시뿌리기위해
				this.bookRefreshData(pbVO);

			} else if (page == 2) {

				this.memberUpdate();
				this.dtm_mng_mem.setRowCount(0);
				this.memberRefreshData();
			}

		} else if (obj == jbtn_mng_clear2) { // 클리어 지우기1
			String bookInfo[] = getBookText();
			for (int i = 0; i < bookInfo.length; i++) {
				bookInfo[i] = " ";
			}
			setBookText(bookInfo);
		} else if (obj == jbtn_mng_clear) { // 클리어 지우기2
			String memInfo[] = getMemberText();
			for (int i = 0; i < memInfo.length; i++) {
				memInfo[i] = " ";
			}
			setMemberText(memInfo);
		} else if (obj == jbtn_mng_ins || obj == jmi_mng_edit_ins) {// 입력일때
			if (page == 1) {
				bookInsert();
				dtm_mng_book.setRowCount(0);// 데이터지우고 다시뿌리기위해
				bookRefreshData(pbVO);
			} else if (page == 2) {
				new Member_Ins_View(true, "회원입력", this);

			}
		} else if (obj == jbtn_mng_del) {// 삭제할때
			if (page == 1) {
				bookDelete();
				dtm_mng_book.setRowCount(0);
				bookRefreshData(pbVO);
				String bookInfo[] = getBookText();
				for (int i = 0; i < bookInfo.length; i++) {
					bookInfo[i] = " ";
				}
				setBookText(bookInfo);
			} else if (page == 2) {
				memberDelete();
				dtm_mng_mem.setRowCount(0);
				memberRefreshData();
				String memInfo[] = getMemberText();
				for (int i = 0; i < memInfo.length; i++) {
					memInfo[i] = " ";
				}
				setMemberText(memInfo);
			} else if (page == 3) {

				this.rentDelete(1);
				this.rentDelete(2);
				this.rentDelete(3);
				this.rentDelete(4);
				if (this.rentDelete(1) == 1 && this.rentDelete(2) == 1 && this.rentDelete(3) == 1
						&& this.rentDelete(4) == 1) {// 삭제성공
					JOptionPane.showMessageDialog(jf_mngapp, "삭제 완료", "INFO", JOptionPane.INFORMATION_MESSAGE);
				}
				dtm_mng_rent.setRowCount(0);
				rentRefreshData();

			} else if (page == 4) {
				this.overDelete();
				dtm_mng_delay.setRowCount(0);
				this.overRefreshData();

			}
			//////////////////////////////////////////////////////////////////////
		} else if (obj == jcb_mng_bcate) {// 도서 검색기능 컬럼 선택
			String jcb_label = (String) jcb_mng_bcate.getSelectedItem();
			pbVO.setSearch(jcb_label);
			System.out.println("사용자가 선택한 컬럼정보 : " + jcb_label);

		} else if (obj == jcb_mng_mcate) {// 멤버 검색기능 컬럼선택
			String jcb_label = (String) jcb_mng_mcate.getSelectedItem();
			pmVO.setSearch(jcb_label);
			System.out.println("사용자가 선택한 컬럼정보 : " + jcb_label);

		} else if (obj == jcb_mng_rcate) {// 대출 검색기능 컬럼선택
			String jcb_label = (String) jcb_mng_rcate.getSelectedItem();
			prmVO.setSearch(jcb_label);
			System.out.println("사용자가 선택한 컬럼정보 : " + jcb_label);
			
		} else if (obj == jcb_mng_dcate) {// 대출 검색기능 컬럼선택
			String jcb_label = (String) jcb_mng_dcate.getSelectedItem();
			ovVO.setSearch(jcb_label);
			System.out.println("사용자가 선택한 컬럼정보 : " + jcb_label);
			/////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////// 검색
		} else if (obj == jbtn_mng_search || obj == jtf_mng_search) {
			if (page == 1) {
				System.out.println("도서검색호출 ");
				if (pbVO.getSearch() == null) {
					pbVO.setSearch("도서명");
				}
				if (jtf_mng_search.getText() == null || jtf_mng_search.getText().length() == 0) {
					JOptionPane.showMessageDialog(jf_mngapp, "키워드를 입력하세요.", "INFO", JOptionPane.INFORMATION_MESSAGE);
					jtf_mng_search.requestFocus();
					return;
				}
				pbVO.setKeyword(jtf_mng_search.getText());
				System.out.println("사용자가 입력한값 : " + jtf_mng_search.getText());
				// 조건절에만 변화가 있을 뿐 전체 조회의 프로세스와 동일하다.
				booksearch(pbVO);

			} else if (page == 2) {
				System.out.println("멤버검색호출 ");
				if (pmVO.getSearch() == null) {
					pmVO.setSearch("이름");
				}
				if (jtf_mng_search.getText() == null || jtf_mng_search.getText().length() == 0) {
					JOptionPane.showMessageDialog(jf_mngapp, "키워드를 입력하세요.", "INFO", JOptionPane.INFORMATION_MESSAGE);
					jtf_mng_search.requestFocus();
					return;
				}
				pmVO.setKeyword(jtf_mng_search.getText());
				System.out.println("사용자가 입력한값 : " + jtf_mng_search.getText());
				// 조건절에만 변화가 있을 뿐 전체 조회의 프로세스와 동일하다.
				membersearch(pmVO);

			} else if (page == 3) {
				System.out.println("대출검색호출 ");
				if (prmVO.getSearch() == null) {
					prmVO.setSearch("이름");
				}
				if (jtf_mng_search.getText() == null || jtf_mng_search.getText().length() == 0) {
					JOptionPane.showMessageDialog(jf_mngapp, "키워드를 입력하세요.", "INFO", JOptionPane.INFORMATION_MESSAGE);
					jtf_mng_search.requestFocus();
					return;
				}
				prmVO.setKeyword(jtf_mng_search.getText());
				System.out.println("사용자가 입력한값 : " + jtf_mng_search.getText());
				// 조건절에만 변화가 있을 뿐 전체 조회의 프로세스와 동일하다.
				rentsearch(prmVO);
			
			} else if (page == 4) {
                System.out.println("연체검색호출 ");
                if (ovVO.getSearch() == null) {
                     ovVO.setSearch("이름");
                }
                if (jtf_mng_search.getText() == null || jtf_mng_search.getText().length() == 0) {
                     JOptionPane.showMessageDialog(jf_mngapp, "키워드를 입력하세요.", "INFO", JOptionPane.INFORMATION_MESSAGE);
                     jtf_mng_search.requestFocus();
                     return;
                }
                ovVO.setKeyword(jtf_mng_search.getText());
                System.out.println("사용자가 입력한값 : " + jtf_mng_search.getText());
                // 조건절에만 변화가 있을 뿐 전체 조회의 프로세스와 동일하다.
                oversearch(ovVO);
           }
			/////////////////////////////////////////////////////////////////////////////////////////
		} else if (obj == jmi_mng_file_exit) {
			System.exit(0); // 종료
		} else if (obj == jbtn_mng_return) {/// 반납완료
			this.returnComplete();
			this.dtm_mng_rent.setRowCount(0);
			this.rentRefreshData();
		} else if (obj == jbtn_mng_dopay) {/// 납부완료
			this.payComplete();
			this.dtm_mng_delay.setRowCount(0);
			this.overRefreshData();
		} else if (obj == jbtn_mng_logout) {
			jf_mngapp.dispose();
			new LoginApp();
		}

	}
}