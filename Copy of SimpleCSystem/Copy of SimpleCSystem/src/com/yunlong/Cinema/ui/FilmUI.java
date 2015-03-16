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
	
	JLabel l4=new JLabel("按名称查找电影：");
	JTextField t3=new JTextField(10);
	JButton b5=new JButton("确定");
	JButton b6=new JButton("电影入库");
	JButton b7=new JButton("修改电影");
	JButton b8=new JButton("上线电影");
	JButton b9=new JButton("所有电影");
	JButton b10=new JButton("电影上线");
	JButton b11=new JButton("电影下线");
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
		jt.getColumn("电影编号").setCellRenderer(dtc);
		jt.getColumn("电影名称").setCellRenderer(dtc);
		jt.getColumn("导演").setCellRenderer(dtc);
		jt.getColumn("主演").setCellRenderer(dtc);
		jt.getColumn("时长").setCellRenderer(dtc);
		jt.getColumn("地区").setCellRenderer(dtc);
		jt.getColumn("上映时间").setCellRenderer(dtc);
		jt.getColumn("状态").setCellRenderer(dtc);
		jt.getColumn("电影类型").setCellRenderer(dtc);
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
					JOptionPane.showMessageDialog(this, "没有您要查找的电影资料");
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
				JOptionPane.showMessageDialog(this, "请点击您要修改的电影");
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
					JOptionPane.showMessageDialog(this, "上线成功");
					indate();
				}else{
					JOptionPane.showMessageDialog(this, "上线失败");
				}
			}else{
				JOptionPane.showMessageDialog(this, "请点击您要修改的电影");
			}
		}
		if(e.getSource()==b11){
			int index=jt.getSelectedRow();
			if(index!=-1){
				int id=Integer.parseInt(jt.getValueAt(index,0).toString().trim());
				boolean m=spd.downSP(id);
				if(m){
					JOptionPane.showMessageDialog(this, "下线成功");
					indate();
				}else{
					JOptionPane.showMessageDialog(this, "下线失败");
				}
			}else{
				JOptionPane.showMessageDialog(this, "请点击您要修改的电影");
			}
		}
	}
}
