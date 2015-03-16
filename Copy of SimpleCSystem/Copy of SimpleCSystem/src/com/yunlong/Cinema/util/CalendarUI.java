package com.yunlong.Cinema.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalendarUI extends JDialog implements ItemListener,MouseListener{
	JLabel title = new JLabel("请选择:");
	JComboBox year = new JComboBox();
	JLabel l1 = new JLabel("年");
	JComboBox month = new JComboBox();
	JLabel l2 = new JLabel("月");
	JPanel p = new JPanel();
	JPanel can = new JPanel();
	String week[] = { "日", "一", "二", "三", "四", "五", "六" };
	//日历区域是6行7列的组件 JLabel
	JLabel tm[]=new JLabel[49];//值分配了空间  没有指定对象
	JTextField tc;
	public CalendarUI(JTextField t) {
		this.tc=t;
		//设置日历区域的布局
		can.setLayout(new GridLayout(7,7));
		//年份初始化
		for (int i = 1990; i < 2021; i++) {//2012
			year.addItem(i + "");
		}
		//月份初始化
		for (int i = 1; i < 13; i++) {
			month.addItem(i + "");
		}
		//标签初始化
		for (int i = 0; i < 49; i++) {
			tm[i]=new JLabel();
			tm[i].setHorizontalAlignment(JLabel.CENTER);
			can.add(tm[i]);//把42个JLabel标签添加到can对象上
		}
		//设置第一排的值
		for(int x=0;x<7;x++){
			tm[x].setText(week[x]);
		}
		for(int x=7;x<49;x++){
			tm[x].addMouseListener(this);
		}
		//需要设置为当前月份的日期
		
		/*
		for (int i = 7; i < 42; i++) {
			tm[i].setText(i+"");
		}*/
		
		init();
		p.add(title);
		year.addItemListener(this);
		month.addItemListener(this);
		p.add(year);
		p.add(l1);
		p.add(month);
		p.add(l2);
		
		this.getContentPane().add(p,BorderLayout.NORTH);
		can.setBackground(Color.ORANGE);
		this.getContentPane().add(can);
		this.setSize(240, 240);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		setCalender(Integer.parseInt(year.getSelectedItem().toString()), Integer.parseInt(month.getSelectedItem().toString()));
	}
	/**
	 * 把下拉框的值选择为当前年月
	 */
	public void init(){
		Calendar current=Calendar.getInstance();
		int y=current.get(Calendar.YEAR);
		int m=current.get(Calendar.MONTH);
		year.setSelectedIndex(y-1990);
		month.setSelectedIndex(m);
		setCalender(y, m+1);
	}
	
	
	public void setCalender(int yy,int mm){
		//清空数字标签
		for (int i = 7; i < 49; i++) {
			tm[i].setText("");
		}
		int weekday=getWeekDay(yy, mm);
		//日    8  下标为7
		//一   9      8
		for(int x=6+weekday,m=1;m<=day(yy,mm);x++,m++){
			tm[x].setText(m+"");
		}
	}
	
	/**
	 * 这个年月的1号是星期几
	 * @param year
	 * @param month
	 */
	public int getWeekDay(int year,int month){
		Calendar current=Calendar.getInstance();
		current.set(Calendar.YEAR, year);
		current.set(Calendar.MONTH,month-1);
		current.set(Calendar.DAY_OF_MONTH, 1);
		return current.get(Calendar.DAY_OF_WEEK);//日  1   一 2
	}
	
	
	
	public static void main(String[] args) {
		//new CalendarUI();
	}
	
	
	
	/**
	 * 判断选择的月份有多少天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public int day(int year, int month) {
		int d = 31;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			d = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			d = 30;
			break;
		case 2:
			d = 28;
			break;
		}
		if(month==2&&(year%4==0&&year%100!=0  ||year%400==0)){
			d=29;
		}
		return d;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel t=(JLabel)e.getSource();
		if(t.getText().trim().length()!=0){
			tc.setText(year.getSelectedItem()+"-"+month.getSelectedItem()+"-"+t.getText());
			this.dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}

