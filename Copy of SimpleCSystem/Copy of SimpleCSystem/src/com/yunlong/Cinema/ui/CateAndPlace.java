package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit.CutAction;

import com.yunlong.Cinema.dao.imp.CategoryImp;
import com.yunlong.Cinema.dao.imp.FilmDAOImp;
import com.yunlong.Cinema.dao.imp.PlaceDAOImp;
import com.yunlong.Cinema.factory.DAOFactory;
import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.Place;

public class CateAndPlace extends JPanel implements ActionListener,
		MouseListener {
	JPanel lp = new JPanel();
	JPanel rp = new JPanel();

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	static JPanel p6 = new JPanel();

	JLabel l1 = new JLabel("��ӰԺ���ƣ�");
	JLabel l2 = new JLabel("����λ��");
	JLabel l3 = new JLabel("������");
	JLabel l4 = new JLabel("������");
	JLabel l5 = new JLabel("Ӱ����");

	JTextField f1 = new JTextField(15);
	JTextField f2 = new JTextField(3);
	JTextField f3 = new JTextField(2);
	JTextField f4 = new JTextField(2);
	JTextField f5 = new JTextField(5);

	DefaultTableModel dtm1 = new DefaultTableModel();
	static DefaultTableModel dtm2 = new DefaultTableModel();
	DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
	JTable jt1 = new JTable(dtm1);
	static JTable jt2 = new JTable(dtm2);

	JScrollPane js1 = new JScrollPane(jt1);
	JScrollPane js2 = new JScrollPane(jt2);
	JSplitPane js = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p6, p5);

	JButton b1 = new JButton("��ӰԺ���");
	JButton b2 = new JButton("��ӰԺ�޸�");
	JButton b3 = new JButton("��ӰԺɾ��");
	JLabel inser = new JLabel("������");
	JTextField jtf = new JTextField(4);
	JButton b4 = new JButton("ȷ��");
	JLabel edit = new JLabel("�޸����");
	static JTextField xg = new JTextField(4);
	JButton b5 = new JButton("�޸�");

	static JButton button[];

	int index = -1;
	static List<Category> list = DAOFactory.getCategorydao().list();
	static String ButC = list.get(0).getCategoryName();
	static int cateId;
	PlaceDAOImp rdi = DAOFactory.getPlacedao();
	static FilmDAOImp fdi = DAOFactory.getFilmdao();
	static CategoryImp ci = DAOFactory.getCategorydao();

	public CateAndPlace() {
		js.setDividerLocation(50);
		js.setDividerSize(0);

		this.setLayout(new GridLayout(1, 2));
		this.add(lp);
		lp.setLayout(new BorderLayout());
		lp.add(js1, BorderLayout.CENTER);
		lp.setBorder(BorderFactory.createTitledBorder("��ӰԺ����"));
		lp.add(p3, BorderLayout.SOUTH);
		p3.setLayout(new GridLayout(2, 1));
		p3.add(p4);
		p4.add(l1);
		p4.add(f1);
		p4.add(l5);
		p4.add(f5);
		p4.add(l2);
		p4.add(f2);
		p4.add(l3);
		p4.add(f3);
		p4.add(l4);
		p4.add(f4);
		p3.add(p1);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		
		this.add(rp);
		rp.setLayout(new BorderLayout());
		rp.add(js);
		p6.setLayout(new FlowLayout());
		initCate();
		p5.setLayout(new GridLayout(1, 1));
		p5.add(js2);
		rp.setBorder(BorderFactory.createTitledBorder("������"));
		rp.add(p2, BorderLayout.SOUTH);
		p2.add(inser);
		p2.add(jtf);
		p2.add(b4);
		p2.add(edit);
		p2.add(xg);
		p2.add(b5);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);

		jt1.addMouseListener(this);

		initRTable();
		initCTable();

	}

	public void initRTable() {
		dtm1.setDataVector(rdi.Placevector(), rdi.colRName());
		tablecellcenter1();
	}

	public void initCTable() {
		dtm2.setDataVector(fdi.FClist(ButC), fdi.colCategory());
		tablecellcenter2();
	}

	public void tablecellcenter1() {
		DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
		dtc.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		dtc.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jt1.getColumn("��ӰԺ���").setCellRenderer(dtc);
		jt1.getColumn("��ӰԺ����").setCellRenderer(dtc);
		jt1.getColumn("Ӱ��").setCellRenderer(dtc);
		jt1.getColumn("����λ��").setCellRenderer(dtc);
		jt1.getColumn("��λ����").setCellRenderer(dtc);
		jt1.getColumn("��λ����").setCellRenderer(dtc);
		
	}

	public static void tablecellcenter2() {
		DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
		dtc.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		dtc.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jt2.getColumn("��Ӱ����").setCellRenderer(dtc);
		jt2.getColumn("����").setCellRenderer(dtc);
		jt2.getColumn("����").setCellRenderer(dtc);
		jt2.getColumn("ʱ��").setCellRenderer(dtc);
		jt2.getColumn("����").setCellRenderer(dtc);
		jt2.getColumn("״̬").setCellRenderer(dtc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			String rn = f1.getText().trim();
			int sn = 0;
			int rs = 0;
			int cn = 0;
			try {
				sn = Integer.parseInt(f2.getText().trim());
				rs = Integer.parseInt(f3.getText().trim());
				cn = Integer.parseInt(f4.getText().trim());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "�뽫��λ����������������д��ȷ");
				e1.printStackTrace();
				return;
			}
			String lc = f5.getText().trim();
			boolean n = rn.length() != 0 && sn != 0 && rs != 0 && cn != 0
					&& lc.length() != 0;
			boolean a = rn != null && f2.getText().trim() != null
					&& f3.getText().trim() != null
					&& f4.getText().trim() != null && lc != null;
			if (n && a) {
				Place Place = new Place(0, rn, sn, rs, cn, lc);
				boolean m = rdi.insertPlace(Place);
				if (m) {
					JOptionPane.showMessageDialog(this, "��ӳɹ�");
					initRTable();
				} else {
					JOptionPane.showMessageDialog(this, "���ʧ��");
				}
			} else {
				JOptionPane.showMessageDialog(this, "�뽫��Ϣ��д����");
			}
		}
		if (e.getSource() == b2) {
			if (index != -1) {
				int id = Integer.parseInt(jt1.getValueAt(index, 0).toString());
				String rn = f1.getText().trim();
				int sn = 0;
				int rs = 0;
				int cn = 0;
				try {
					sn = Integer.parseInt(f2.getText().trim());
					rs = Integer.parseInt(f3.getText().trim());
					cn = Integer.parseInt(f4.getText().trim());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "�뽫��λ����������������д��ȷ");
					e1.printStackTrace();
					return;
				}
				String lc = f5.getText().trim();
				boolean n = rn.length() != 0 && sn != 0 && rs != 0 && cn != 0
						&& lc.length() != 0;
				boolean a = rn != null && f2.getText().trim() != null
						&& f3.getText().trim() != null
						&& f4.getText().trim() != null && lc != null;
				if (n && a) {
					Place Place = new Place(id, rn, sn, rs, cn, lc);
					boolean m = rdi.editPlace(Place);
					if (m) {
						JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
						initRTable();
						index = -1;
					} else {
						JOptionPane.showMessageDialog(this, "�޸�ʧ��");
						index = -1;
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫ�޸ĵ���");
			}
		}
		if (e.getSource() == b3) {
			if (index != -1) {
				int id = Integer.parseInt(jt1.getValueAt(index, 0).toString());
				boolean m = rdi.deletePlace(id);
				if (m) {
					JOptionPane.showMessageDialog(this, "ɾ���ɹ�");
					initRTable();
					index = -1;
				} else {
					JOptionPane.showMessageDialog(this, "ɾ��ʧ��");
					index = -1;
				}
			} else {
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫɾ������");
			}
		}
		if (e.getSource() == b4) {
			String in = jtf.getText().trim().toString();
			boolean n1 = in.length() != 0 && jtf.getText() != null;
			if (n1) {
				Category cat = new Category(0, in);
				boolean m1 = ci.insertCate(cat);
				if (m1) {
					JOptionPane.showMessageDialog(this, "��ӳɹ�");
					initCate();
					p6.updateUI();
				} else {
					JOptionPane.showMessageDialog(this, "���ʧ��");
				}
			} else {
				JOptionPane.showMessageDialog(this, "����д��Ҫ��ӵ������");
			}
		}
		if (e.getSource() == b5) {
			String in = xg.getText().trim().toString();
			boolean n2 = in.length() != 0 && xg.getText() != null;
			if (n2) {
				Category cate = new Category(cateId, in);
				boolean m2 = ci.editCate(cate);
				if (m2) {
					JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
					ButC = in;
					initCate();
					p6.updateUI();
				} else {
					JOptionPane.showMessageDialog(this, "�޸�ʧ��");
				}
			} else {
				JOptionPane.showMessageDialog(this, "����д���޸ĵ������");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		index = jt1.getSelectedRow();
		if (index != -1) {
			int id = Integer.parseInt(jt1.getValueAt(index, 0).toString());
			Place rm = rdi.Place(id);
			f1.setText(rm.getPlaceName());
			f2.setText(rm.getSeatNumber() + "");
			f3.setText(rm.getRows() + "");
			f4.setText(rm.getColumns() + "");
			f5.setText(rm.getLocation());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	static class Click implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			ButC = b.getText().trim().toString();
			for (int i = 0; i < button.length; i++) {
				button[i].setBackground(null);
			}
			cateId = ci.cate(ButC);
			dtm2.setDataVector(fdi.FClist(ButC), fdi.colCategory());
			tablecellcenter2();
			b.setBackground(Color.RED);
			xg.setText(ButC);
		}
	}

	public void initCate() {
		p6.removeAll();
		List<Category> list1 = DAOFactory.getCategorydao().list();
		Click c = new Click();
		button = new JButton[list1.size()];
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton(list1.get(i).getCategoryName());
			p6.add(button[i]);
			button[i].addActionListener(c);
			if (list1.get(i).getCategoryName().equals(ButC)) {
				button[i].setBackground(Color.RED);
			}
		}
	}
}