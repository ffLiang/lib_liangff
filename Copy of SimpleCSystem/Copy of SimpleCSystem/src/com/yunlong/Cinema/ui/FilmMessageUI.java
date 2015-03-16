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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.yunlong.Cinema.dao.FilmDAO;
import com.yunlong.Cinema.dao.PlaceDAO;
import com.yunlong.Cinema.dao.SellDAO;
import com.yunlong.Cinema.dao.ShowPlanDAO;
import com.yunlong.Cinema.dao.imp.FilmDAOImp;
import com.yunlong.Cinema.dao.imp.SellDAOImp;
import com.yunlong.Cinema.factory.DAOFactory;
import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.Place;
import com.yunlong.Cinema.vo.SellLog;
import com.yunlong.Cinema.vo.ShowPlan;


public class FilmMessageUI extends JPanel {
	static JPanel top=new JPanel();
	static JPanel Place=new JPanel();
	private static String FilmName;
	private static int SPId;
	static SellDAO logDAO=DAOFactory.getSelldao();
	static ShowPlanDAO planDAO=DAOFactory.getShowplandao();
	static FilmDAO filmDAO=DAOFactory.getFilmdao();
	
	DefaultTableModel dtm=new DefaultTableModel();
	JTable jt=new JTable(dtm);
	JScrollPane jsp=new JScrollPane(jt);
	FilmDAOImp fdi=DAOFactory.getFilmdao();
	
	
	InsertFilmUI ifu;
	EditFilm ef;
	int ind=-1;
	
	public FilmMessageUI(){
		this.setLayout(new BorderLayout());
		top.setPreferredSize(new Dimension(this.getSize().width, 300));
		this.add(top,BorderLayout.NORTH);
		top.setBorder(BorderFactory.createTitledBorder("电影信息"));
		top.setLayout(new BorderLayout());
		this.add(Place);
		Place.setBorder(BorderFactory.createTitledBorder("座位信息"));
	}
	
	
	
	public static void initTop(String filmName,int spId){
		top.removeAll();
		FilmName=filmName;
		Film fm=filmDAO.list(FilmName);
		List<Category> lt=filmDAO.catelist(fm.getFilmId());
		JPanel img=new JPanel();
		JPanel info1=new JPanel();
		JPanel info2=new JPanel();
		JPanel pri=new JPanel();
		ImageIcon ii=new ImageIcon(new ImageIcon(fm.getPicture()).getImage().getScaledInstance(200, 275,Image.SCALE_DEFAULT));
		JLabel image=new JLabel(ii);
		
		String s="";
		for (Category category : lt) {
			s=s+category.getCategoryName()+" ";
		}
		
		JLabel l1=new JLabel("电影："+fm.getFilmName());
		l1.setFont(new Font("宋体",Font.ITALIC,18));
		JLabel l2=new JLabel("导演："+fm.getDirector());
		l2.setFont(new Font("宋体",Font.ITALIC,18));
		JLabel l3=new JLabel("主演："+fm.getProtagonist());
		l3.setFont(new Font("宋体",Font.ITALIC,18));
		JLabel l4=new JLabel("地区："+fm.getRegion());
		l4.setFont(new Font("宋体",Font.ITALIC,18));
		JLabel l5=new JLabel("上映："+fm.getShowTime().substring(0,10));
		l5.setFont(new Font("宋体",Font.ITALIC,18));
		JLabel l6=new JLabel("时长："+fm.getTimeLength());
		l6.setFont(new Font("宋体",Font.ITALIC,18));
		JLabel l7=new JLabel("价格："+planDAO.spIdlist(spId).getprice());
		l7.setFont(new Font("宋体",Font.ITALIC,18));
		JLabel l8=new JLabel("类型："+s);
		l8.setFont(new Font("宋体",Font.ITALIC,18));
		
		
		top.setLayout(new GridLayout(1,4));
		img.add(image);
		top.add(img);
		
		info1.setLayout(new GridLayout(4, 1));
		info1.add(l1);
		info1.add(l2);
		info1.add(l3);
		info1.add(l4);
		top.add(info1);
		
		info2.setLayout(new GridLayout(4,1));
		info2.add(l5);
		info2.add(l6);
		info2.add(l7);
		info2.add(l8);
		top.add(info2);
		
	}
	
	
	public static void initMessage(String filmName,int spId){
		Place.removeAll();
		FilmName=filmName;
		SPId=spId;
		ShowPlan sp=DAOFactory.getShowplandao().spIdlist(SPId);
		Place rm=DAOFactory.getPlacedao().Place(sp.getPlaceId());
		Film fm=DAOFactory.getFilmdao().list(FilmName);
		List<SellLog> list=DAOFactory.getSelldao().list(SPId);
		
		Place.setBorder(BorderFactory.createTitledBorder(rm.getPlaceName()+"     "+rm.getLocation()));
		Place.setLayout(new GridLayout(rm.getRows(), rm.getColumns(),3,3));
		Click c=new Click();
		for (int i = 1; i <=rm.getRows(); i++) {
			for (int j = 1; j <=rm.getColumns(); j++) {
				JButton b=new JButton(i+"-"+j);
				for (SellLog SellLog:list) {
					if(SellLog.getRows()==i&&SellLog.getColumns()==j){
						b.setBackground(Color.RED);
					}
				}
				b.addActionListener(c);
				Place.add(b);
			}
		}
		Place.updateUI();
	}
	
	static class Click implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b=(JButton)e.getSource();
			if(b.getBackground()==Color.RED){
				JOptionPane.showMessageDialog(null, "此座位已售出");
			}else{
				String rc=b.getText();
				SellLog sl=new SellLog(0,SPId,Integer.parseInt(rc.split("-")[0]),Integer.parseInt(rc.split("-")[1]),planDAO.spIdlist(SPId).getprice(),MainUI.getAccountid());
				Boolean bl=logDAO.sell(sl);
				if(bl){
					JOptionPane.showMessageDialog(null, "购买成功");
					initMessage(FilmName, SPId);
				}else{
					JOptionPane.showMessageDialog(null, "购买失败");
				}
			}
		}
	}
}
