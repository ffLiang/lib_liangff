package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

//import org.junit.internal.matchers.SubstringMatcher;
import org.hamcrest.core.SubstringMatcher;

import com.yunlong.Cinema.dao.imp.FilmDAOImp;
import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.ShowPlan;

public class FilmTree extends JPanel implements ActionListener{
	JPanel p=new JPanel();
	static JPanel p1=new JPanel();
	JLabel l=new JLabel("预售：");
	JButton b=new JButton("确定");
	String s[]={"今天","明天","后天"};
	JComboBox jc=new JComboBox(s);
	
	static DefaultMutableTreeNode dmt=new DefaultMutableTreeNode("请选择电影");
	static JTree tree=new JTree(dmt);
	
	static FilmDAOImp fdi=new FilmDAOImp();
	static int time=1;
	
	public FilmTree(){
		
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.NORTH);
		p1.setBackground(Color.YELLOW);
		
		p.add(l);
		p.add(jc);
		p.add(b);
		
		p1.setLayout(new BorderLayout());
		p1.add(tree);
		this.add(p1);
		
		b.addActionListener(this);
		
		initRoot();
		
	}
	public static void initRoot(){
		dmt.removeAllChildren();
		List<Film> list=null;
		switch(time){
			case 1: list=fdi.listCurrentDay();
			break;
			case 2: list=fdi.tomCurrentDay();
			break;
			case 3: list=fdi.atomCurrentDay();
			break;
		}
		for (Film film : list) {
			DefaultMutableTreeNode f=new DefaultMutableTreeNode(film.getFilmName());
			for (ShowPlan sp : film.getShowplan()) {
				String st=sp.getShowTime();
				String s=st.substring(st.length()-10,st.length()-5);
				String et=sp.getEndTime();
				String e=et.substring(et.length()-10,et.length()-5);
				f.add(new DefaultMutableTreeNode(sp.getPlace().getPlaceName()+"--"+s+"开始"+"--"+e+"结束"+"--"+sp.getprice()+"元"+"--"+sp.getSpId()));
			}
			dmt.add(f);
			tree.updateUI();
		}
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TreePath tp=tree.getSelectionPath();
				if(tp!=null){
					DefaultMutableTreeNode select=(DefaultMutableTreeNode)tp.getLastPathComponent();
					if(select.getLevel()==2){
						String s=select.toString();
						int id=Integer.parseInt(s.split("--")[4]);
						FilmMessageUI.initMessage(select.getParent().toString(),id);
						FilmMessageUI.initTop(select.getParent().toString(),id);
					}
				}
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b){
			int index=jc.getSelectedIndex()+1;
			time=index;
			initRoot();
		}
		
	}
}
