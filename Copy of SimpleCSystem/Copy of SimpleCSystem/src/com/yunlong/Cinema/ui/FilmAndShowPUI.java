package com.yunlong.Cinema.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FilmAndShowPUI extends JPanel {
	JTabbedPane jt=new JTabbedPane(JTabbedPane.TOP);
	public FilmAndShowPUI(){
		this.setLayout(new GridLayout(1,1));
		jt.add("�ƻ�����",new PlanUI());
		jt.add("��Ӱ����",new FilmUI());
		jt.add("��ӰԺ�͵�Ӱ������",new CateAndPlace());
		this.add(jt);
		
	}
}
