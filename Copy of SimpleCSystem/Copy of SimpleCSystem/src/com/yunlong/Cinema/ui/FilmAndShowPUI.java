package com.yunlong.Cinema.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FilmAndShowPUI extends JPanel {
	JTabbedPane jt=new JTabbedPane(JTabbedPane.TOP);
	public FilmAndShowPUI(){
		this.setLayout(new GridLayout(1,1));
		jt.add("计划管理",new PlanUI());
		jt.add("电影管理",new FilmUI());
		jt.add("电影院和电影类别管理",new CateAndPlace());
		this.add(jt);
		
	}
}
