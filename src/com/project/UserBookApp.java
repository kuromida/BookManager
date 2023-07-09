package com.project;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

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

import com.project.LoginApp;
import com.project.MgrBookController;
import com.vo.BookVO;

public class UserBookApp implements ActionListener {
	// "C:\\dev_kosmo20181101\\dev_project\\src\\com\\project\\image\\";
//	String path = "C:\\dev_kosmo20181101\\Kenari\\src\\com\\project\\image\\";
	String path = "C:\\image\\";

	MgrBookController mBookCtrl = new MgrBookController();
	BookVO pbVO = new BookVO();
	String isbn;
	String memid;

	JFrame jf_memapp = new JFrame();
	JPanel jp_memtop = new JPanel();
	JMenuBar jmb_mem = new JMenuBar();

	JMenu jm_mem_file = new JMenu("File");
	JMenuItem jmi_mem_file_exit = new JMenuItem("����");
	JMenu jm_mem_edt = new JMenu("Edit");
	JMenuItem jmi_mem_edit_upd = new JMenuItem("ȸ����������");
	JMenuItem jmi_mem_edit_memout = new JMenuItem("ȸ��Ż��");

	JPanel jp_mem_second = new JPanel();
	JButton jbtn_mem_booklist = new JButton("��������");
	JButton jbtn_mem_myrentinfo = new JButton("��������");
	JButton jbtn_mem_myinfo = new JButton("��������");

	JTextField textField = new JTextField("");// =>�˻� 

	JButton jbtn_mem_search = new JButton("");//�˻�
	String[] msearchcate = { "������", "���ڸ�", "���ǳ⵵" };
	JComboBox jcb_mem_cate = new JComboBox(msearchcate);
	JPanel jp_mem_left = new JPanel();
	JPanel jp_mem_bright = new JPanel();
	JLabel jlb_mem_isbn = new JLabel("ISBN");
	JTextField jtf_mem_isbn = new JTextField();
	JLabel jlb_mem_name = new JLabel("������");
	JTextField jtf_mem_name = new JTextField();
	JLabel jlb_mem_writer = new JLabel("���ڸ�");
	JTextField jtf_mem_writer = new JTextField();
	JLabel jlb_mem_company = new JLabel("���ǻ��");
	JTextField jtf_mem_company = new JTextField();
	JLabel jlb_mem_year = new JLabel("���ǳ⵵");
	JTextField jtf_mem_year = new JTextField();
	JLabel jlb_mem_price = new JLabel("��������");
	JTextField jtf_mem_price = new JTextField();
	JLabel jlb_mem_quanty = new JLabel("���");
	JTextField jtf_mem_quanty = new JTextField();
	String mem_book_cols[] = { "ISBN", "������", "���ڸ�", "���ǻ�", "���ǳ⵵", "����", "������" };
	String mem_book_cols_data[][] = new String[0][7];
	DefaultTableModel dtm_mem_book = new DefaultTableModel(mem_book_cols_data, mem_book_cols);
	JTable jt_mem_book = new JTable(dtm_mem_book);
	JScrollPane jsp_mem_book = new JScrollPane(jt_mem_book, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JMenu jm_mem_help = new JMenu("Help");
	//////////////////////// ����̹��׽�Ʈ ���ְ� �޽��������� �����ϸ� ����
	JMenuItem jmi_mem_infomation = new JMenuItem("����");
	JMenuItem jmi_mem_call = new JMenuItem("�޼���������");

	JLabel jlb_mem_nameview = new JLabel();
	JLabel jlb_mem_welcomement = new JLabel("�� ȯ���մϴ�.");
	JButton jbtn_mem_logout = new JButton("");

	JTableHeader jth_mem_book = jt_mem_book.getTableHeader();
	Font f_mem_header = new Font("���� ���", Font.BOLD, 16);

	Font f_title = new Font("����䳢 �ɱ�", Font.BOLD, 24);

	public JLabel jlb_book_img= new JLabel();

	public void initDisplay() {
		jbtn_mem_booklist.addActionListener(this);// �������� ��ư
		jbtn_mem_myrentinfo.addActionListener(this);// �������� ��ư

		jcb_mem_cate.addActionListener(this);
		textField.addActionListener(this);
		jbtn_mem_search.addActionListener(this);

		jbtn_mem_logout.addActionListener(this);// �α׾ƿ�

		jtf_mem_quanty.setText("");
		jtf_mem_quanty.setBounds(12, 685, 201, 40);
		jtf_mem_quanty.setColumns(10);
		jtf_mem_price.setText("");
		jtf_mem_price.setBounds(12, 595, 201, 40);
		jtf_mem_price.setColumns(10);
		jtf_mem_year.setText("");
		jtf_mem_year.setBounds(12, 505, 201, 40);
		jtf_mem_year.setColumns(10);
		jtf_mem_company.setText("");
		jtf_mem_company.setBounds(12, 415, 201, 40);
		jtf_mem_company.setColumns(10);
		jtf_mem_writer.setText("");
		jtf_mem_writer.setBounds(12, 325, 201, 40);
		jtf_mem_writer.setColumns(10);
		jtf_mem_name.setText("");
		jtf_mem_name.setBounds(12, 236, 201, 40);
		jtf_mem_name.setColumns(10);
		jtf_mem_isbn.setText("");
		jtf_mem_isbn.setBounds(12, 146, 201, 40);
		jtf_mem_isbn.setColumns(10);

		jf_memapp.setTitle("������������_�����Ver");
		jf_memapp.setVisible(true);
		jf_memapp.setSize(1200, 900);
		jf_memapp.getContentPane().setLayout(null);

		jp_memtop.setBounds(0, 0, 1184, 30);
		jf_memapp.getContentPane().add(jp_memtop);
		jp_memtop.setLayout(null);
		jp_memtop.add(jmb_mem);

		jmb_mem.setBounds(0, 0, 1200, 30);

		jmb_mem.add(jm_mem_file);
		jm_mem_file.add(jmi_mem_file_exit);
		jmb_mem.add(jm_mem_edt);
		jm_mem_edt.add(jmi_mem_edit_upd);
		jm_mem_edt.add(jmi_mem_edit_memout);

		jmb_mem.add(jm_mem_help);

		jm_mem_help.add(jmi_mem_infomation);
		jm_mem_help.add(jmi_mem_call);

		jp_mem_second.setBounds(0, 27, 1200, 79);
		jf_memapp.getContentPane().add(jp_mem_second);
		jp_mem_second.setLayout(null);
		jbtn_mem_myrentinfo.setBounds(140, 3, 141, 80);
		jp_mem_second.add(jbtn_mem_myrentinfo);
		jbtn_mem_myinfo.setBounds(280, 3, 141, 80);
		// jp_mem_second.add(jbtn_mem_myinfo);
		jbtn_mem_booklist.setBounds(0, 3, 141, 80);
		jp_mem_second.add(jbtn_mem_booklist);
		textField.setBounds(801, 20, 267, 42);
		textField.setBorder(new LineBorder(new Color(247, 183, 3), 3));
		jp_mem_second.add(textField);
		textField.setColumns(10);

		jbtn_mem_search.setBounds(1065, 20, 70, 42);
		jbtn_mem_search.setBackground(Color.WHITE);
		jp_mem_second.add(jbtn_mem_search);

		jcb_mem_cate.setBounds(694, 20, 95, 42);
		jp_mem_second.add(jcb_mem_cate);

		jlb_mem_nameview.setBounds(427, 38, 60, 40);
		jp_mem_second.add(jlb_mem_nameview);

		jlb_mem_welcomement.setBounds(486, 38, 153, 40);
		jp_mem_second.add(jlb_mem_welcomement);
		//jbtn_mem_logout.setBounds(595, 10, 95, 40);
		//jp_mem_second.add(jbtn_mem_logout);

		jp_mem_left.setBounds(0, 116, 213, 735);
		jf_memapp.getContentPane().add(jp_mem_left);
		jp_mem_left.setLayout(null);
		jlb_mem_isbn.setBounds(12, 107, 189, 40);
		jp_mem_left.add(jlb_mem_isbn);
		jp_mem_left.add(jtf_mem_isbn);
		jlb_mem_name.setBounds(12, 196, 189, 40);
		jp_mem_left.add(jlb_mem_name);
		jp_mem_left.add(jtf_mem_name);
		jlb_mem_writer.setBounds(12, 286, 189, 40);
		jp_mem_left.add(jlb_mem_writer);
		jp_mem_left.add(jtf_mem_writer);
		jlb_mem_company.setBounds(12, 375, 189, 40);
		jp_mem_left.add(jlb_mem_company);
		jp_mem_left.add(jtf_mem_company);
		jlb_mem_year.setBounds(12, 465, 189, 40);
		jp_mem_left.add(jlb_mem_year);
		jp_mem_left.add(jtf_mem_year);
		jlb_mem_price.setBounds(12, 555, 189, 40);
		jp_mem_left.add(jlb_mem_price);
		jp_mem_left.add(jtf_mem_price);
		jlb_mem_quanty.setBounds(12, 645, 189, 40);
		jp_mem_left.add(jlb_mem_quanty);
		jp_mem_left.add(jtf_mem_quanty);
		

		jp_mem_bright.setBounds(225, 116, 959, 735);
		jf_memapp.getContentPane().add(jp_mem_bright);
		jp_mem_bright.setLayout(null);
	
		jp_mem_bright.add(jsp_mem_book);
		jsp_mem_book.setBounds(12, 10, 935, 715);
		//////////////////////////////////////////////////////////

		jmb_mem.setBackground(Color.WHITE);
		jf_memapp.setBackground(Color.WHITE);
		jp_mem_left.setBackground(Color.WHITE);
		jp_mem_bright.setBackground(Color.WHITE);
		jp_mem_second.setBackground(Color.WHITE);
		jp_memtop.setBackground(Color.WHITE);


		jf_memapp.getContentPane().setBackground(Color.WHITE);

		jcb_mem_cate.setBackground(Color.WHITE);

		jbtn_mem_myinfo.setBackground(Color.WHITE);
		jbtn_mem_myrentinfo.setBackground(Color.WHITE);
		jbtn_mem_booklist.setBackground(Color.WHITE);
		jbtn_mem_myinfo.setBorderPainted(false);
		jbtn_mem_myrentinfo.setBorderPainted(false);
		jbtn_mem_booklist.setBorderPainted(false);

		jbtn_mem_logout.setBorderPainted(false);
		jbtn_mem_search.setBorderPainted(false);
		jbtn_mem_search.setIcon(new ImageIcon(path + "�˻�1.jpg"));

		jth_mem_book.setBackground(Color.orange);
		jth_mem_book.setReorderingAllowed(false);
		jth_mem_book.setFont(f_mem_header);

		jbtn_mem_logout.setBounds(630, 25, 50, 40);
		jbtn_mem_logout.setBackground(Color.WHITE);
		jp_mem_second.add(jbtn_mem_logout);
		jbtn_mem_logout.setIcon(new ImageIcon(path+"�α׾ƿ�2.png"));

		jbtn_mem_myinfo.setFont(f_title);
		jbtn_mem_myrentinfo.setFont(f_title);
		jbtn_mem_booklist.setFont(f_title);
		jbtn_mem_myinfo.setForeground(Color.GRAY);
		jbtn_mem_myrentinfo.setForeground(Color.gray);
		jbtn_mem_booklist.setForeground(Color.gray);

		jt_mem_book.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				bookUpdateText();
			}
		});

	}

	public UserBookApp() {
	}

	////////////////////////////////////////////////////////////////////////////
	/////// �α��ν� �����ڷ� ȣ��ȴ�.
	public UserBookApp(String mname, String memid) {
		// �Ķ���ͷ� �α����� ����� �̸��� ���̵� �޴´�.
		jlb_mem_nameview.setText(mname);
		this.memid = memid;
		initDisplay();
		bookRefreshData(pbVO);
	}
	
	public void bookRefreshData(BookVO pbVO) {
		while (dtm_mem_book.getRowCount() > 0) {
			// removeRow�Ķ���Ϳ� ����� 0�� �� ������ �����ǰ� ���� �ο찡 �ϳ��� ������Ƿ�
			// �ݺ��ؼ� 0��° �ο츦 �����ϱ� ����...
			dtm_mem_book.removeRow(0);
		}
		if (pbVO != null) {
			System.out.println("pbVO�� search�� ���� �� : " + pbVO.getSearch());

		}
		if (pbVO == null) {// ���� ó�� �� ���ΰ�ħ ó���ÿ��� null�� �Ѿ�´�.
			// ���� �� �κ��� ������ �Ǹ� ����, ���� , ���� �̺κ��� null�� �Ǵ� ���̰�
			// ������ null�� ��� ���� �ν��Ͻ�ȭ�� �ϱ� ������.....
			System.out.println("pbVO�� search�� ���� �������� :" + pbVO.getSearch());
			pbVO = new BookVO();
		}
		pbVO.setCommand("select");
		List<BookVO> bookList = mBookCtrl.sendALL();
		BookVO[] bvos = null;
		bvos = new BookVO[bookList.size()];
		// �迭���� ���� ������ DefaultTableModel�� �־��ش�.- �׷��� ȭ�鿡 ��µ�.
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
			/*
			 * BookVO bvo1 =new BookVO(); bvos[i] = bvo1;
			 * bvos[i].setIsbn(bookList.get(i).getIsbn());
			 * bvos[i].setBname(bookList.get(i).getBname());
			 * bvos[i].setAuthor(bookList.get(i).getAuthor());
			 * bvos[i].setPublisher(bookList.get(i).getPublisher());
			 * bvos[i].setPdate(bookList.get(i).getPdate());
			 * bvos[i].setPrice(bookList.get(i).getPrice());
			 * bvos[i].setStock(bookList.get(i).getStock());
			 */
			System.out.println();

			// dtm_mng_book.addRow(bvos);
			dtm_mem_book.addRow(oneRow);

		}
	}

	//////////////////////////////////////////////////////////////////
	// �ο켱�ý� �ؽ�Ʈ ��â�� �߰��ϱ�
	public void bookUpdateText() {
		int index[] = jt_mem_book.getSelectedRows();
		String[] bookInfo = new String[7];

		for (int i = 0; i < dtm_mem_book.getRowCount(); i++) {
			for (int j = 0; j < bookInfo.length; j++) {
				if (jt_mem_book.isRowSelected(i)) {
					bookInfo[j] = (String) dtm_mem_book.getValueAt(i, j);
					if (j == 0) {
						isbn = bookInfo[j]; // ���� ���̵� ����
					}
				}

			}
			// �ο켱�ý� �ؽ�Ʈ �߰��ϱ�
			setBookText(bookInfo);
		}
	}

	public void setBookText(String[] bookInfo) {// �ؽ�Ʈ �ʵ忡 ���� �־��ֱ� (����)

		int k = 0;
		jtf_mem_isbn.setText(bookInfo[k++]);
		jtf_mem_name.setText(bookInfo[k++]);
		jtf_mem_writer.setText(bookInfo[k++]);
		jtf_mem_company.setText(bookInfo[k++]);
		jtf_mem_year.setText(bookInfo[k++]);
		jtf_mem_price.setText(bookInfo[k++]);
		jtf_mem_quanty.setText(bookInfo[k++]);
	}

	public static void main(String[] arg) {
		UserBookApp mat = new UserBookApp("���ϴ�", "apple");
		System.out.println();
	}

	///////////////////////////////////////////////////////////////// �˻����
	public void bookdetail(BookVO pbVO) {
		// ���� ���̺��� DefalultTableModel�� ��� �ִ� �����ͷο츦 �����ϱ�
		while (dtm_mem_book.getRowCount() > 0) {
			// removeRow�Ķ���Ϳ� ����� 0�� �� ������ �����ǰ� ���� �ο찡 �ϳ��� ������Ƿ�
			// �ݺ��ؼ� 0��° �ο츦 �����ϱ� ����...
			dtm_mem_book.removeRow(0);
		}
		System.out.println("booksearch ȣ��");
		if (pbVO != null) {
			System.out.println("pbVO�� search�� ���� �� : " + pbVO.getSearch());

		}
		if (pbVO == null) {// ���� ó�� �� ���ΰ�ħ ó���ÿ��� null�� �Ѿ�´�.
			// ���� �� �κ��� ������ �Ǹ� ����, ���� , ���� �̺κ��� null�� �Ǵ� ���̰�
			// ������ null�� ��� ���� �ν��Ͻ�ȭ�� �ϱ� ������.....
			System.out.println("pbVO�� search�� ���� �������� :" + pbVO.getSearch());
			pbVO = new BookVO();
			System.out.println("�̰ǽ���Ǹ� �ȵ�");
		}
		pbVO.setCommand("detail");
		List<BookVO> bookList = mBookCtrl.sendSearch(pbVO);
		BookVO[] bvos = null;
		bvos = new BookVO[bookList.size()];
		// �迭���� ���� ������ DefaultTableModel�� �־��ش�.- �׷��� ȭ�鿡 ��µ�.
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
			dtm_mem_book.addRow(oneRow);
		}
		System.out.println("�׽�Ʈ�� : " + bvos[0].getAuthor());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == jcb_mem_cate) {
			String jcb_label = (String) jcb_mem_cate.getSelectedItem();
			pbVO.setSearch(jcb_label);
			System.out.println("����ڰ� ������ �÷����� : " + jcb_label);
		} else if (obj == jbtn_mem_search || obj == textField) {

			if (pbVO.getSearch() == null) {
				pbVO.setSearch("������");
			}
			if (textField.getText() == null || textField.getText().length() == 0) {
				JOptionPane.showMessageDialog(jf_memapp, "Ű���带 �Է��ϼ���.", "INFO", JOptionPane.INFORMATION_MESSAGE);
				textField.requestFocus();
				return;
			}
			pbVO.setKeyword(textField.getText());
			System.out.println("����ڰ� �Է��Ѱ� : " + textField.getText());
			// ���������� ��ȭ�� ���� �� ��ü ��ȸ�� ���μ����� �����ϴ�.
			bookdetail(pbVO);
		} else if (obj == jbtn_mem_logout) {
			jf_memapp.dispose();
			new LoginApp();
		} else if (obj == jbtn_mem_booklist) {
			bookRefreshData(pbVO);
		} else if (obj == jbtn_mem_myrentinfo) {// ���Ǵ��� ȭ�� ȣ��
			// ���⿡ �α��� ������� ���̵�� �̸��� �����Ѵ�
			new MyrentInfo(this.memid, jlb_mem_nameview.getText());
		}
	}
}