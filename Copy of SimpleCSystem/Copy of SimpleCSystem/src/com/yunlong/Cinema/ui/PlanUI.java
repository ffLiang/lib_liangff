package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import com.yunlong.Cinema.dao.imp.ShowPlanDAOImp;
import com.yunlong.Cinema.factory.DAOFactory;

public class PlanUI extends JPanel implements ActionListener{
	DefaultTableModel dtm=new DefaultTableModel();
	DefaultTableColumnModel dtc = new DefaultTableColumnModel();
	ShowPlanDAOImp spd=DAOFactory.getShowplandao();
	JTable jt=new JTable(dtm);
	JPanel p=new JPanel();
	JScrollPane jsp=new JScrollPane(jt);
	
	JLabel l1=new JLabel("查");
	JTextField t1=new JTextField(10);
	JLabel l2=new JLabel("时间到");
	JTextField t2=new JTextField(10);
	JLabel l3=new JLabel("的电影计划");
	JButton b1=new JButton("确定");
	JButton b2=new JButton("添加计划");
	JButton b3=new JButton("修改计划");
	JButton b4=new JButton("有效计划");
	JButton b5=new JButton("所有计划");
	
	InsertSPUI ser;
	EditSPUI es;
	int ind=-1;
	public PlanUI(){
	
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.SOUTH);
		this.add(jsp);
		indate();
		
		p.add(l1);
		p.add(t1);
		p.add(l2);
		p.add(t2);
		p.add(l3);
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		p.add(b5);
			
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		t1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			JTextField j=(JTextField)e.getSource();
			new CalendarUII(j);
			}
		});
		t2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			JTextField j=(JTextField)e.getSource();
			new CalendarUII(j);
			}
		});
	}
	public void indate() {
		dtm.setDataVector(spd.selectSPAll(),spd.colname());
		tableCellCenter1();
		}
	public void tableCellCenter1(){
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		dtc.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jt.getColumn("计划编号").setCellRenderer(dtc);
		jt.getColumn("电影名称").setCellRenderer(dtc);
		jt.getColumn("电影院").setCellRenderer(dtc);
		jt.getColumn("影厅").setCellRenderer(dtc);
		jt.getColumn("放映时间").setCellRenderer(dtc);
		jt.getColumn("结束时间").setCellRenderer(dtc);
		jt.getColumn("价格").setCellRenderer(dtc);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			String st=t1.getText().trim();
			String et=t2.getText().trim();
			if(st.length()!=0&&et.length()!=0){
				Vector<Vector<Object>> vv=null;
				vv=spd.selectSP(st,et);
				if(vv!=null){
					dtm.setDataVector(vv, spd.colname());
					tableCellCenter1();
				}else{
					JOptionPane.showMessageDialog(this, "没有你要查的信息");
				}
			}else{
				JOptionPane.showMessageDialog(this, "请输入时间信息");
			}
		}
		if(e.getSource()==b2){
			if(ser==null){
				ser=new InsertSPUI(this);
			}else{
				ser.setVisible(true);
			}
			
		}
		if(e.getSource()==b3){
			int index=jt.getSelectedRow();
			if(index!=-1){
				int spid=Integer.parseInt(jt.getValueAt(index, 0).toString());
				String filmname=jt.getValueAt(index, 1).toString().trim();
				if(es==null||ind!=index){
					ind=index;
					if(es!=null){
						es.dispose();
					}
					es=new EditSPUI(this,spid,filmname);
				}else{
					es.setVisible(true);
				}
			}else{
				JOptionPane.showMessageDialog(this,"请点击您要修改的项");
			}
		}
		if(e.getSource()==b4){
			indate();
		}
		if(e.getSource()==b5){
			dtm.setDataVector(spd.allshowplan(),spd.colname());
			tableCellCenter1();
		}
	}
}
