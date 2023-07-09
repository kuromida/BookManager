package com.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.vo.BookVO;
import com.vo.MemberVO;
import com.vo.RentalDetailVO;
import com.vo.RentalMasterVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
/*
 * isbn 도서명  출판사
 */
public class MgrRentView implements ActionListener {
	// 객체주입을 위한것 
	MgrBookController mBookCtrl = new MgrBookController();
	MgrRentController mRentCtrl = new MgrRentController();
	//전역변수란
	JDialog jdl_mng_rent_rent = new JDialog();
	JPanel jp_mng_rent_rent = new JPanel();
	JLabel jlb_mng_rent_name = new JLabel("이름");
	JLabel jlb_mng_rent_id = new JLabel("아이디");

	JTextField jtf_id = new JTextField(); // 아이디 나오는창
	JTextField jtf_name = new JTextField(); // 이름 나오는 창

	JLabel jlb_mng_rent_isbn = new JLabel("ISBN");
	JTextField jtf_mng_rent_bname = new JTextField("");
	JButton jbtn_mng_rent_gorent = new JButton("확인");

	JLabel jlb_mng_rent_picture = new JLabel("picture");
	JLabel jbl_mng_rent_isbnview = new JLabel("");

	JLabel jbl_rent_idview = new JLabel();
	String mng_rent_bdcols[] = { "ISBN", "도서명", "출판사","재고" };
	String mng_rent_bdcols_data[][] = new String[0][4];

	DefaultTableModel dtm_rent_isbnsearch = new DefaultTableModel(mng_rent_bdcols_data, mng_rent_bdcols);
	JTable jt_rent_isbnsearch = new JTable(dtm_rent_isbnsearch);
	JScrollPane jsp_rent_isbnsearch = new JScrollPane(jt_rent_isbnsearch, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String mname = null;
	String mid = null;
	String[] bisbn = null;
	JButton jbtn_mng_rent_minus = new JButton();
	JLabel jlb_mng_rent_nameview = new JLabel();
	JButton jbtn_mng_rent_plus = new JButton();

	JTableHeader jth_rent_isbnsearch = jt_rent_isbnsearch.getTableHeader();
	Font f_rent_header = new Font("맑은 고딕", Font.BOLD, 16);
	Font f_rent_normal = new Font("맑은 고딕", Font.BOLD, 15);
	//String path ="C:\\dev_kosmo20181101\\kenari_book\\src\\com\\project\\image\\";
	String path = "C:\\image\\";
	public MgrRentView() {

	}

	public void initDisplay() {

		// 이벤트 처리
		jbtn_mng_rent_gorent.addActionListener(this);
		jbtn_mng_rent_plus.addActionListener(this);
		 jbtn_mng_rent_minus.addActionListener(this);
		 
		jtf_id.setBounds(120, 270, 240, 30);
		jtf_name.setBounds(120, 220, 240, 30);

		jdl_mng_rent_rent.setTitle("대여하기");
		jtf_mng_rent_bname.setText("");
		jtf_mng_rent_bname.setBounds(120, 315, 240, 30);
		jtf_mng_rent_bname.setColumns(10);
		jdl_mng_rent_rent.getContentPane().add("Center", jp_mng_rent_rent);
		jp_mng_rent_rent.setLayout(null);
		jdl_mng_rent_rent.setVisible(true);
		jdl_mng_rent_rent.setSize(500, 700);
		jlb_mng_rent_id.setBounds(47, 270, 80, 37);

		jdl_mng_rent_rent.setBackground(Color.white);
		jp_mng_rent_rent.setBackground(Color.WHITE);

		jp_mng_rent_rent.add(jtf_id);
		jp_mng_rent_rent.add(jtf_name);
		jp_mng_rent_rent.add(jlb_mng_rent_id);
		jlb_mng_rent_isbn.setBounds(47, 314, 80, 37);
		jp_mng_rent_rent.add(jlb_mng_rent_isbn);
		jp_mng_rent_rent.add(jtf_mng_rent_bname);
		jbtn_mng_rent_gorent.setBounds(194, 601, 100, 37);

		jp_mng_rent_rent.add(jbtn_mng_rent_gorent);

		jlb_mng_rent_picture.setBounds(47, 10, 398, 224);
		jp_mng_rent_rent.add(jlb_mng_rent_picture);

		jsp_rent_isbnsearch.setBounds(70, 376, 375, 215);
		jp_mng_rent_rent.add(jsp_rent_isbnsearch);
		jbl_rent_idview.setBounds(139, 220, 217, 37);

		jp_mng_rent_rent.add(jbl_rent_idview);

		jbtn_mng_rent_minus.setBounds(5, 450, 40, 40);
		jp_mng_rent_rent.add(jbtn_mng_rent_minus);

		jlb_mng_rent_name.setBounds(47, 220, 80, 37);
		jp_mng_rent_rent.add(jlb_mng_rent_name);

		jlb_mng_rent_nameview.setBounds(139, 269, 233, 35);
		jp_mng_rent_rent.add(jlb_mng_rent_nameview);

		jbtn_mng_rent_plus.setBounds(384, 314, 40, 40);
		jp_mng_rent_rent.add(jbtn_mng_rent_plus);

		jlb_mng_rent_picture.setIcon(new ImageIcon(path+"책대여배너2.png"));

		jth_rent_isbnsearch.setBackground(Color.orange);
		jth_rent_isbnsearch.setReorderingAllowed(false);

		jth_rent_isbnsearch.setFont(f_rent_header);

		jbtn_mng_rent_minus.setBorderPainted(false);
		jbtn_mng_rent_plus.setBorderPainted(false);
		jbtn_mng_rent_gorent.setBorderPainted(false);

		 jbtn_mng_rent_minus.setIcon(new ImageIcon(path+"-아이콘.png"));
		 jbtn_mng_rent_plus.setIcon(new ImageIcon(path+"+아이콘.png"));
		 jbtn_mng_rent_gorent.setIcon(new ImageIcon(path+"대여아이콘1.png"));

		jlb_mng_rent_id.setFont(f_rent_normal);
		jlb_mng_rent_name.setFont(f_rent_normal);
		jlb_mng_rent_isbn.setFont(f_rent_normal);

	}

	// 아이디와 이름 텍스트 설정
	public void setTextMember(String[] memberInfo) {
		int k = 0;
		this.jtf_name.setText(memberInfo[k++]);
		this.jtf_id.setText(memberInfo[k++]);
	}

	// isbn 도서명  출판사 재고수  테이블에 뜨게하기 
	public void bookRefreshData(String isbn) {

		BookVO bVO = new BookVO();
		bVO.setCommand("select");
		List<BookVO> bookList = mBookCtrl.sendALL02(isbn);
		BookVO[] bvos = null;
		bvos = new BookVO[bookList.size()];
		// 배열에서 꺼낸 정보를 DefaultTableModel에 넣어준다.- 그래야 화면에 출력됨.
		for (int i = 0; i < bookList.size(); i++) {
			Vector oneRow = new Vector();
			BookVO bvo1 = new BookVO(bookList.get(i).getIsbn(), bookList.get(i).getBname()
									,bookList.get(i).getPublisher(),bookList.get(i).getStock());

			bvos[i] = bvo1;
			oneRow.add(bvos[i].getIsbn());
			oneRow.add(bvos[i].getBname());
			oneRow.add(bvos[i].getPublisher());
			oneRow.add(bvos[i].getStock());

			System.out.println(bvos[i].getIsbn());
	

			// dtm_mng_book.addRow(bvos);
			dtm_rent_isbnsearch.addRow(oneRow);
		}
	}
	

	public void bookDelete() { //삭제 
		int row[] = jt_rent_isbnsearch.getSelectedRows();
		if (row.length == 0) {
			JOptionPane.showMessageDialog(jp_mng_rent_rent, "삭제할 데이터를 선택하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			
		} else {
			for (int i = 0; i < dtm_rent_isbnsearch.getRowCount(); i++) {
			
					dtm_rent_isbnsearch.removeRow(row[i]);
				
			}

		}
	}
	//RentalMaster 삽입 String memid ,int rent_count 
	public int rentInsert() {
		int result =0;
		RentalMasterVO prmVO = new RentalMasterVO();
		MemberVO mvo = new MemberVO();
		String memid = jtf_id.getText();
		int rent_count = jt_rent_isbnsearch.getRowCount();
		
		System.out.println("아이디"+memid);
		System.out.println("총 대여권수"+rent_count);
		
		mvo.setMemid(memid);
		prmVO.setMvo(mvo);
		prmVO.setRent_count(rent_count);
		prmVO.setCommand("insert");
		RentalMasterVO rrmVO = mRentCtrl.send(prmVO, 0);
		result =rrmVO.getResult();
	
		return result;
	}

	//RentalDtail 삽입 int detailId ,String isbn 
	public int rentDetailInsert() {
		int result=0;
		RentalMasterVO prmVO = new RentalMasterVO();
		RentalDetailVO rdVO= new RentalDetailVO();
		BookVO bVO = new BookVO();
		int rent_count = jt_rent_isbnsearch.getRowCount();
		int detailId = 0;
		String isbn = jtf_mng_rent_bname.getText();
		for(int i=0;i<rent_count;i++) {
			detailId =i+1;	
			rdVO.setDetailid(detailId);
			bVO.setIsbn(isbn);
			prmVO.setRdvo(rdVO);
			prmVO.setBvo(bVO);
			prmVO.setCommand("insert2");
			RentalMasterVO rrmVO = mRentCtrl.send(prmVO, 0);
			result =rrmVO.getResult();
			
		}
		System.out.println("result: "+result);
		return result;
		
	}
	
	public static void main(String[] args) {
		//MgrRentView mr = new MgrRentView();
		//mr.initDisplay();

	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String isbn =jtf_mng_rent_bname.getText();
		if(obj ==jbtn_mng_rent_plus ) {// 추가 눌렀을떄 
			
			bookRefreshData(isbn);
		}else if(obj ==  jbtn_mng_rent_minus) {//삭제 눌럿을때 
			bookDelete();
		
		}else if(obj ==jbtn_mng_rent_gorent) {//확인 눌렀을때 
			
			int res1 =rentInsert();
			int res2 =rentDetailInsert();
			if(res1 ==1&&res2==1) {
				JOptionPane.showMessageDialog(jdl_mng_rent_rent, "추가 완료","INFO",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(jdl_mng_rent_rent, "추가 실패","Error",JOptionPane.ERROR_MESSAGE);
			}
			this.jdl_mng_rent_rent.dispose();
		}
		
	}
}