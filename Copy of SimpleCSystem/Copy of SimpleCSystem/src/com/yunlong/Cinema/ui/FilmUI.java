package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.yunlong.Cinema.dao.imp.FilmDAOImp;
import com.yunlong.Cinema.dao.imp.ShowPlanDAOImp;
import com.yunlong.Cinema.factory.DAOFactory;
import com.yunlong.Cinema.vo.Film;


public class FilmUI extends JPanel implements ActionListener{
	DefaultTableModel dtm=new DefaultTableModel();
	DefaultTableColumnModel dtc = new DefaultTableColumnModel();
	FilmDAOImp fdi=DAOFactory.getFilmdao();
	ShowPlanDAOImp spd=DAOFactory.getShowplandao();
	JTable jt=new JTable(dtm);
	JPanel p=new JPanel();
	JScrollPane jsp=new JScrollPane(jt);
	
	JLabel l4=new JLabel("�����Ʋ��ҵ�Ӱ��");
	JTextField t3=new JTextField(10);
	JButton b5=new JButton("ȷ��");
	JButton b6=new JButton("��Ӱ���");
	JButton b7=new JButton("�޸ĵ�Ӱ");
	JButton b8=new JButton("���ߵ�Ӱ");
	JButton b9=new JButton("���е�Ӱ");
	JButton b10=new JButton("��Ӱ����");
	JButton b11=new JButton("��Ӱ����");
	InsertFilmUI ifu;
	EditFilm ef;
	int ind=-1;
	
	public FilmUI(){
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.SOUTH);
		this.add(jsp);
		
		indate();
		
		p.add(l4);
		p.add(t3);
		p.add(b5);
		p.add(b6);
		p.add(b7);
		p.add(b8);
		p.add(b9);
		p.add(b10);
		p.add(b11);
		
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
	}
	public void indate() {
		dtm.setDataVector(fdi.list(),fdi.colname());
		tableCellCenter();
		}
	public void tableCellCenter(){
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		dtc.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jt.getColumn("��Ӱ���").setCellRenderer(dtc);
		jt.getColumn("��Ӱ����").setCellRenderer(dtc);
		jt.getColumn("����").setCellRenderer(dtc);
		jt.getColumn("����").setCellRenderer(dtc);
		jt.getColumn("ʱ��").setCellRenderer(dtc);
		jt.getColumn("����").setCellRenderer(dtc);
		jt.getColumn("��ӳʱ��").setCellRenderer(dtc);
		jt.getColumn("״̬").setCellRenderer(dtc);
		jt.getColumn("��Ӱ����").setCellRenderer(dtc);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b5){
			String fname=t3.getText().trim();
			Vector<Vector<Object>> vv=null;
			if(fname!=null&&fname.length()!=0){
				vv=fdi.filmlist(fname);
				if(vv!=null){
					dtm.setDataVector(vv, fdi.colname());
					tableCellCenter();
				}else{
					JOptionPane.showMessageDialog(this, "û����Ҫ���ҵĵ�Ӱ����");
				}
			}
		}
		if(e.getSource()==b6){
			if(ifu==null){
				ifu=new InsertFilmUI(this);
			}else{
				ifu.setVisible(true);
			}	
		}
		if(e.getSource()==b7){
			int index=jt.getSelectedRow();
			if(index!=-1){
				int id=Integer.parseInt(jt.getValueAt(index,0).toString().trim());
				Film film=fdi.list(id);
				if(ef==null||ind!=index){
					if(ef!=null){
						ef.dispose();
					}
					ind=index;
					ef=new EditFilm(this,film);
				}else{
					ef.setVisible(true);
				}
				
			}else{
				JOptionPane.showMessageDialog(this, "������Ҫ�޸ĵĵ�Ӱ");
			}
		}
		if(e.getSource()==b8){
			indate();
		}
		if(e.getSource()==b9){
			dtm.setDataVector(fdi.listAll(),fdi.colname());
			tableCellCenter();
		}
		if(e.getSource()==b10){
			int index=jt.getSelectedRow();
			if(index!=-1){
				int id=Integer.parseInt(jt.getValueAt(index,0).toString().trim());
				boolean m=spd.upSP(id);
				if(m){
					JOptionPane.showMessageDialog(this, "���߳ɹ�");
					indate();
				}else{
					JOptionPane.showMessageDialog(this, "����ʧ��");
				}
			}else{
				JOptionPane.showMessageDialog(this, "������Ҫ�޸ĵĵ�Ӱ");
			}
		}
		if(e.getSource()==b11){
			int index=jt.getSelectedRow();
			if(index!=-1){
				int id=Integer.parseInt(jt.getValueAt(index,0).toString().trim());
				boolean m=spd.downSP(id);
				if(m){
					JOptionPane.showMessageDialog(this, "���߳ɹ�");
					indate();
				}else{
					JOptionPane.showMessageDialog(this, "����ʧ��");
				}
			}else{
				JOptionPane.showMessageDialog(this, "������Ҫ�޸ĵĵ�Ӱ");
			}
		}
	}
}
