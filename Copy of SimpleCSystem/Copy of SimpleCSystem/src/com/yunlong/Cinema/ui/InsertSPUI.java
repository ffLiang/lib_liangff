package com.yunlong.Cinema.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.yunlong.Cinema.dao.imp.PlaceDAOImp;
import com.yunlong.Cinema.factory.DAOFactory;
import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.ShowPlan;

public class InsertSPUI extends JFrame implements ActionListener, ItemListener {
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JPanel p7 = new JPanel();
	JPanel p8 = new JPanel();

	JLabel l = new JLabel("��Ӽƻ�");
	JLabel l1 = new JLabel("��Ӱ���ƣ�");
	JLabel l2 = new JLabel("��ӰԺ��");
	JLabel l3 = new JLabel("��ӳʱ�䣺");
	JLabel l4 = new JLabel("����ʱ�䣺");
	JLabel l5 = new JLabel("Ӱ����");
	JLabel l6 = new JLabel("�۸�");
	
	JComboBox c1 = new JComboBox();
	JComboBox c = new JComboBox();
	JComboBox c2 = new JComboBox();
	
	JTextField t2 = new JTextField(8);
	JTextField t3 = new JTextField(8);
	JTextField t4 = new JTextField(8);
	
	JButton b1 = new JButton("ȷ��");
	JButton b2 = new JButton("����");
	PlaceDAOImp rdi = DAOFactory.getPlacedao();
	PlanUI PI;

	public InsertSPUI(PlanUI pi) {
		int i=0;
		this.PI = pi;
		this.setTitle("��Ӽƻ�");
		this.setSize(300, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(new GridLayout(8, 1));
		
		Vector<String> v = DAOFactory.getFilmdao().namelist();
		for (i=0; i<v.size(); i++){
			c1.addItem(v.get(i));
		}
		Vector<String> rn = DAOFactory.getPlacedao().PlaceName();
		for (i=0; i<rn.size(); i++){
			c.addItem(rn.get(i));
		}
		String r = c.getSelectedItem().toString();
		Vector<String> rn2 = DAOFactory.getPlacedao().location(r);
		for (i=0; i<rn2.size(); i++){
			c2.addItem(rn2.get(i));
		}
		
		
		p1.add(l);
		this.add(p1);

		p2.add(l1);
		p2.add(c1);
		this.add(p2);

		p3.add(l2);
		p3.add(c);
		this.add(p3);
		
		p7.add(l5);
		p7.add(c2);
		this.add(p7);

		p4.add(l3);
		p4.add(t2);
		this.add(p4);

		p5.add(l4);
		p5.add(t3);
		this.add(p5);
		
		p8.add(l6);
		p8.add(t4);
		this.add(p8);

		p6.add(b1);
		p6.add(b2);
		this.add(p6);

		b1.addActionListener(this);
		b2.addActionListener(this);
		t2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField j = (JTextField) e.getSource();
				new CalendarUII(j);
			}
		});
		t3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField j = (JTextField) e.getSource();
				new CalendarUII(j);
			}
		});
		c.addItemListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			String name = c1.getSelectedItem().toString().trim();
			// int rid=c.getSelectedIndex()+1;
			String rn = c.getSelectedItem().toString();
			String rn1 = c2.getSelectedItem().toString();
			int rid = rdi.Placen(rn,rn1);
			String st = t2.getText().trim();
			String et = t3.getText().trim();
			int price = Integer.parseInt(t4.getText().trim());
			Film fm = DAOFactory.getFilmdao().list(name);
			List<String> ft = DAOFactory.getShowplandao().selecshowpw(st, et, rid);
			ShowPlan sp = new ShowPlan(0, fm.getFilmId(), rid, st, et, price);
			boolean m = false;
			if (ft.size()==0) {
				m = DAOFactory.getShowplandao().adshowplan(sp);
				if (m) {
					JOptionPane.showMessageDialog(this, "��ӳɹ�");
					PI.indate();
				} else {
					JOptionPane.showMessageDialog(this, "���ʧ��");
				}
			} else {
				JOptionPane.showMessageDialog(this, "��ʱ��θõ�ӰԺ���з�ӳ�ƻ�");
			}

		}
		if (e.getSource() == b2) {
			c1.setSelectedIndex(0);
			c.setSelectedIndex(0);
			t2.setText("");
			t3.setText("");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int i=0;
		c2.removeAllItems();
		String rn = c.getSelectedItem().toString();
		Vector<String> rn2 = DAOFactory.getPlacedao().location(rn);
		for (i=0; i<rn2.size(); i++){
			c2.addItem(rn2.get(i));
		}
		
	}
}
