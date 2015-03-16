package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.yunlong.Cinema.dao.FavorDirectorDAO;
import com.yunlong.Cinema.dao.FavorProtagonistDAO;
import com.yunlong.Cinema.dao.FilmDAO;
import com.yunlong.Cinema.dao.PlaceDAO;
import com.yunlong.Cinema.dao.SellDAO;
import com.yunlong.Cinema.dao.favorCategoryDAO;
import com.yunlong.Cinema.dao.imp.CategoryImp;
import com.yunlong.Cinema.dao.imp.FilmDAOImp;
import com.yunlong.Cinema.dao.imp.SellDAOImp;
import com.yunlong.Cinema.factory.DAOFactory;
import com.yunlong.Cinema.ui.CateAndPlace.Click;
import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.FavorCategory;
import com.yunlong.Cinema.vo.FavorDirector;
import com.yunlong.Cinema.vo.FavorProtagonist;
import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.Place;
import com.yunlong.Cinema.vo.SellLog;
import com.yunlong.Cinema.vo.ShowPlan;


public class AccountMessageUI extends JPanel implements ActionListener, MouseListener{
	static JPanel top=new JPanel();
	static JPanel Place=new JPanel();
	private static String FilmName;
	private static int SPId;
	static SellDAO logDAO=DAOFactory.getSelldao();
	static FilmDAO filmDAO=DAOFactory.getFilmdao();
	
	static DefaultTableModel dtm=new DefaultTableModel();
	static JTable jt=new JTable(dtm);
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel p5 = new JPanel();
	JPanel p11 = new JPanel();
	JPanel p22 = new JPanel();
	JPanel p31 = new JPanel();
	JPanel p32 = new JPanel();
	JPanel p33 = new JPanel();
	JPanel p333 = new JPanel();
	static JPanel p6 = new JPanel();
	JScrollPane jsp=new JScrollPane(jt);
	FilmDAOImp fdi=DAOFactory.getFilmdao();
	
	JLabel l1=new JLabel("喜欢的电影类型：");
	JLabel l2=new JLabel("喜欢的导演：");
	JLabel l3=new JLabel("喜欢的演员：");
	JLabel l11=new JLabel("电影类型：");
	JTextField t11=new JTextField(10);
	JLabel l22=new JLabel("导演：");
	JTextField t22=new JTextField(10);
	JLabel l33=new JLabel("演员：");
	JTextField t33=new JTextField(10);
	JButton b11=new JButton("添加");
	JButton b22=new JButton("删除");
	JButton b33=new JButton("清空");
	JButton b2=new JButton("查看观影日程");
	
	JLabel l4=new JLabel("按");
	String s[]={"电影名称","导演","演员"};
	JComboBox jc=new JComboBox(s);
	JLabel l5=new JLabel("查找电影：");
	JTextField t5=new JTextField(10);
	JButton b5=new JButton("确定");
	JButton b9=new JButton("所有电影");
	JButton b6=new JButton("清空");
	static JButton button[];
	static List<Category> list = DAOFactory.getCategorydao().list();
	static String ButC = list.get(0).getCategoryName();
	static FilmDAOImp fdi2 = DAOFactory.getFilmdao();
	static CategoryImp ci = DAOFactory.getCategorydao();
	static int cateId;
	static DefaultTableModel dtm2 = new DefaultTableModel();
	static JTextField xg = new JTextField(4);
	
	InsertFilmUI ifu;
	EditFilm ef;
	int ind=-1;
	
	
	int i = 0;
	
	public AccountMessageUI(){
		this.setLayout(new BorderLayout());
		top.setPreferredSize(new Dimension(this.getSize().width, 300));
		this.add(top,BorderLayout.NORTH);
		top.setBorder(BorderFactory.createTitledBorder("个人信息"));
		top.setLayout(new BorderLayout());
		// TODO
		p1.setLayout(new BorderLayout());
		p4.add(b2);
		p4.add(l11);
		p4.add(t11);
		p4.add(l22);
		p4.add(t22);
		p4.add(l33);
		p4.add(t33);
		p4.add(b11);
		p4.add(b22);
		p4.add(b33);
		p1.add(p4,BorderLayout.WEST);
		p2.setLayout(new GridLayout(3,1));
		p2.add(l1);
		p2.add(l2);
		p2.add(l3);
		p3.setLayout(new GridLayout(3,1));
		
		p3.add(p11);
		p3.add(p22);
		p3.add(p33);
		p333.setLayout(new BorderLayout());
		p333.add(p3,BorderLayout.WEST);
		
		top.add(p1,BorderLayout.SOUTH);
		top.add(p2,BorderLayout.WEST);
		top.add(p333,BorderLayout.CENTER);
		
		
		this.add(Place);
		Place.setBorder(BorderFactory.createTitledBorder("电影查询"));
		Place.setLayout(new BorderLayout());
		p6.setLayout(new FlowLayout());
		initCate();
		p6.setPreferredSize(new Dimension(50, 600));
		Place.add(p6,BorderLayout.WEST);
		Place.add(p,BorderLayout.SOUTH);
		Place.add(jsp);
		
		
		indate();
		
		p.add(l4);
		p.add(jc);
		p.add(l5);
		p.add(t5);
		p.add(b5);
		p.add(b9);
		p.add(b6);
		
		b11.addActionListener(this);
		b22.addActionListener(this);
		b33.addActionListener(this);
		b2.addActionListener(this);
		b5.addActionListener(this);
		b9.addActionListener(this);
		b6.addActionListener(this);
		jc.addActionListener(this);
	}
	
	public void indate() {
		
		List<String> fcate=new favorCategoryDAO().list(MainUI.getAccountid());
		String[] fc=fcate.toArray(new String[fcate.size()]);
		List<String> fdire=new FavorDirectorDAO().list(MainUI.getAccountid());
		String[] fd=fdire.toArray(new String[fdire.size()]);
		List<String> fprot=new FavorProtagonistDAO().list(MainUI.getAccountid());
		String[] fp=fprot.toArray(new String[fprot.size()]);
		p11.removeAll();
		p22.removeAll();
		p33.removeAll();
		p11.setLayout(new GridLayout(1,fc.length));
		for (i=0;i<fc.length;i++){
			JLabel jl=new JLabel(fc[i]);
			jl.addMouseListener(this);
			p11.add(jl);
		}
		p22.setLayout(new GridLayout(1,fd.length));
		for (i=0;i<fd.length;i++){
			JLabel jl=new JLabel(fd[i]);
			jl.addMouseListener(this);
			p22.add(jl);
		}
		p33.setLayout(new GridLayout(1,fp.length));
		for (i=0;i<fp.length;i++){
			JLabel jl=new JLabel(fp[i]);
			jl.addMouseListener(this);
			p33.add(jl);
		}
		
		dtm.setDataVector(fdi.list1(fc[0],fd[0],fp[0]),fdi.colCategory1());
		tableCellCenter();
	}
	
	public static void tableCellCenter(){
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		dtc.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jt.getColumn("电影名称").setCellRenderer(dtc);
		jt.getColumn("导演").setCellRenderer(dtc);
		jt.getColumn("主演").setCellRenderer(dtc);
		jt.getColumn("时长").setCellRenderer(dtc);
		jt.getColumn("地区").setCellRenderer(dtc);
		jt.getColumn("上映时间").setCellRenderer(dtc);
		jt.getColumn("电影类型").setCellRenderer(dtc);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b11){
			String s;
			if((s=t11.getText())!=null){
				FavorCategory fc=new FavorCategory(MainUI.getAccountid(),s);
				new favorCategoryDAO().insertFCate(fc);
			}
			if((s=t22.getText())!=null){
				FavorDirector fd=new FavorDirector(MainUI.getAccountid(),s);
				new FavorDirectorDAO().insertFDire(fd);
			}
			if((s=t33.getText())!=null){
				FavorProtagonist fp=new FavorProtagonist(MainUI.getAccountid(),s);
				new FavorProtagonistDAO().insertFProt(fp);
			}
			indate();
			if(((t11.getText())!=null)&&((t22.getText())!=null)&&((t33.getText())!=null)){
				JOptionPane.showMessageDialog(this, "添加成功");
			}
		}
        if(e.getSource()==b22){
        	String s;
			favorCategoryDAO fcd = null;
			FavorDirectorDAO fdd = null;
			FavorProtagonistDAO fpd = null;
			if((s=t11.getText())!=null){
				FavorCategory fc=new FavorCategory(MainUI.getAccountid(),s);
				new favorCategoryDAO().deleteFCate(fc);
			}
			if((s=t22.getText())!=null){
				FavorDirector fd=new FavorDirector(MainUI.getAccountid(),s);
				new FavorDirectorDAO().deleteFDire(fd);
			}
			if((s=t33.getText())!=null){
				FavorProtagonist fp=new FavorProtagonist(MainUI.getAccountid(),s);
				new FavorProtagonistDAO().deleteFProt(fp);
			}
			indate();
			if(((t11.getText())!=null)&&((t22.getText())!=null)&&((t33.getText())!=null)){
				JOptionPane.showMessageDialog(this, "删除成功");
			}
		}
        if(e.getSource()==b33){
			t11.setText("");
			t22.setText("");
			t33.setText("");
		}
        
		if(e.getSource()==b2){
			new AccountCalendarUI();
		}
		if(e.getSource()==b5){
			String name=t5.getText().trim();
			Vector<Vector<Object>> vv=null;
			if(name!=null && name.length()!=0){
				if (jc.getSelectedIndex()==0){
					vv=fdi.filmlist1(name);
				}
				else if (jc.getSelectedIndex()==1){
					vv=fdi.filmlist2(name);
				}
				else if (jc.getSelectedIndex()==2){
					vv=fdi.filmlist3(name);
				}
				
				if(vv!=null){
					dtm.setDataVector(vv, fdi.colCategory1());
					tableCellCenter();
				}
				else{
					JOptionPane.showMessageDialog(this, "没有您要查找的电影资料");
				}
			}
		}
		
		if(e.getSource()==b6){
			t5.setText("");
		}
		
		if(e.getSource()==b9){
			dtm.setDataVector(fdi.list2(),fdi.colCategory1());
			tableCellCenter();
		}
	}
	
	
	public void initCate() {
		p6.removeAll();
		List<Category> list1 = DAOFactory.getCategorydao().list();
		Click2 c = new Click2();
		button = new JButton[list1.size()];
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton(list1.get(i).getCategoryName());
			p6.add(button[i]);
			button[i].addActionListener(c);
		}
	}
	
	static class Click2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			ButC = b.getText().trim().toString();
			for (int i = 0; i < button.length; i++) {
				button[i].setBackground(null);
			}
			dtm.setDataVector(fdi2.FClist1(ButC), fdi2.colCategory1());
			tableCellCenter();
			b.setBackground(Color.RED);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
