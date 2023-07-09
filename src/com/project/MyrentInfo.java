package com.project;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.project.MgrRentController;
import com.vo.RentalMasterVO;

public class MyrentInfo {

	MgrRentController mRentCtrl = new MgrRentController();

	JDialog jdl_myrentinfo = new JDialog();
	JPanel jp_myrentinfo_top = new JPanel();
	JPanel jp_myrentinfo_center = new JPanel();

	JLabel jlb_myrentinfo_nameview = new JLabel();
	JLabel jlb_myrentinfo_ment = new JLabel("���� �뿩����");

	Font f_title = new Font("����䳢 �ɱ�", Font.BOLD, 24);

	String myrent_cols[] = { "���̵�", "�̸�", "ISBN", "������", "�뿩��", "�ݳ���Ȳ", "��ü��" };
	String myrent_cols_data[][] = new String[0][7];
	DefaultTableModel dtm_myrentinfo = new DefaultTableModel(myrent_cols_data, myrent_cols);
	JTable jt_myrentinfo = new JTable(dtm_myrentinfo);
	JScrollPane jsp_myrentinfo = new JScrollPane(jt_myrentinfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public MyrentInfo() {
	}

	/////////// ��ư Ŭ���� �����ڷ� ȣ�� ���ϰ� �Ķ���ͷ� ���̵�� �̸��� �޴´�.
	public MyrentInfo(String memid, String mname) {

		jlb_myrentinfo_nameview.setText(mname);
		jdl_myrentinfo.setSize(500, 500);
		jdl_myrentinfo.setVisible(true);

		jdl_myrentinfo.add("North", jp_myrentinfo_top);
		jdl_myrentinfo.add("Center", jp_myrentinfo_center);

		jp_myrentinfo_top.add(jlb_myrentinfo_nameview);
		jp_myrentinfo_top.add(jlb_myrentinfo_ment);
		jp_myrentinfo_center.add(jsp_myrentinfo);

		jp_myrentinfo_top.setBackground(Color.WHITE);
		jp_myrentinfo_center.setBackground(Color.WHITE);

		jlb_myrentinfo_nameview.setFont(f_title);
		jlb_myrentinfo_ment.setFont(f_title);
		//System.out.println(memid);
		rentRefreshData(memid);
	}
	// ���� ������� ȭ��

	public String rentRefreshData(String memid) {
		// ���̵� �̸� isbn ������ �뿩�� �ݳ���Ȳ ��ü��
		String umname = null;
		RentalMasterVO pmvo = new RentalMasterVO();

		List<RentalMasterVO> myrentList = mRentCtrl.send_myrent(memid);
		RentalMasterVO[] rmvos = null;
		rmvos = new RentalMasterVO[myrentList.size()];
		// �迭���� ���� ������ DefaultTableModel�� �־��ش�.- �׷��� ȭ�鿡 ��µ�.
		for (int i = 0; i < myrentList.size(); i++) {
			Vector oneRow = new Vector();
			RentalMasterVO rmvo1 = new RentalMasterVO(myrentList.get(i).getBvo(), myrentList.get(i).getMvo(),
					myrentList.get(i).getRdvo(), myrentList.get(i).getOvo(), myrentList.get(i).getMvo().getMemid(),
					myrentList.get(i).getMvo().getMname(), myrentList.get(i).getBvo().getIsbn(),
					myrentList.get(i).getBvo().getBname(), myrentList.get(i).getRentdate(),
					myrentList.get(i).getRdvo().getRental_now(), myrentList.get(i).getOvo().getOverduepay());

			rmvos[i] = rmvo1;
			oneRow.add(rmvos[i].getMvo().getMemid());
			oneRow.add(rmvos[i].getMvo().getMname());
			oneRow.add(rmvos[i].getBvo().getIsbn());
			oneRow.add(rmvos[i].getBvo().getBname());
			oneRow.add(rmvos[i].getRentdate());
			oneRow.add(rmvos[i].getRdvo().getRental_now());
			oneRow.add(rmvos[i].getOvo().getOverduepay());

			// System.out.println();

			// dtm_mng_book.addRow(bvos);
			dtm_myrentinfo.addRow(oneRow);
			umname = rmvos[i].getMvo().getMname();
		}
		return umname;
	}
	public static void main(String[] args) {
		new MyrentInfo("EPOV","�Ʊ��ľ�");
	}

	
}
