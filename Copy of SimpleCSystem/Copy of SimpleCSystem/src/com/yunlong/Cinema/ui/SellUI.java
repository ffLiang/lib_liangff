package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SellUI extends JPanel {
	public SellUI(){
		JSplitPane js=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new FilmTree(), new FilmMessageUI());
		js.setDividerLocation(315);
		js.setDividerSize(0);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.add(js);
	}

}
