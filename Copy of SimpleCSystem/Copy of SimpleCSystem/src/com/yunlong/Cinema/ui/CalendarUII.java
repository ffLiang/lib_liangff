package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalendarUII extends JDialog implements ActionListener,MouseListener,ItemListener{
	JLabel title = new JLabel("��ѡ��:");
	JComboBox year = new JComboBox();
	JLabel l1 = new JLabel("��");
	JComboBox month = new JComboBox();
	JLabel l2 = new JLabel("��");
	JComboBox hour=new JComboBox();
	JLabel l3=new JLabel("ʱ");
	JComboBox min=new JComboBox();
	JLabel l4=new JLabel("��");
	JPanel p = new JPanel();
	JPanel can = new JPanel();
	String week[] = { "��", "һ", "��", "��", "��", "��", "��" };
	//����������6��7�е���� JLabel
	JLabel tm[]=new JLabel[49];//ֵ�����˿ռ�  û��ָ������
	
	String dd=null;
	JTextField tc;
	public CalendarUII(JTextField t) {
		this.tc=t;
		//������������Ĳ���
		can.setLayout(new GridLayout(7,7));
		//��ݳ�ʼ��
		for (int i = 1990; i < 2021; i++) {//2012
			year.addItem(i + "");
		}
		//�·ݳ�ʼ��
		for (int i = 1; i < 13; i++) {
			month.addItem(i + "");
		}
		for (int i = 0; i <24; i++) {
			hour.addItem(i+"");
		}
		for (int i = 0; i <60; i++) {
			min.addItem(i+"");
		}
		//��ǩ��ʼ��
		for (int i = 0; i < 49; i++) {
			tm[i]=new JLabel();
			tm[i].setHorizontalAlignment(JLabel.CENTER);
			can.add(tm[i]);//��42��JLabel��ǩ��ӵ�can������
		}
		//���õ�һ�ŵ�ֵ
		for(int x=0;x<7;x++){
			tm[x].setText(week[x]);
		}
		for(int x=7;x<49;x++){
			tm[x].addMouseListener(this);
		}
		//��Ҫ����Ϊ��ǰ�·ݵ�����
		
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
		p.add(hour);
		p.add(l3);
		p.add(min);
		p.add(l4);
		
		this.getContentPane().add(p,BorderLayout.NORTH);
		can.setBackground(Color.ORANGE);
		this.getContentPane().add(can);
		this.setSize(320, 240);
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
	 * ���������ֵѡ��Ϊ��ǰ����
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
		//������ֱ�ǩ
		for (int i = 7; i < 49; i++) {
			tm[i].setText("");
		}
		int weekday=getWeekDay(yy, mm);
		//��    8  �±�Ϊ7
		//һ   9      8
		for(int x=6+weekday,m=1;m<=day(yy,mm);x++,m++){
			tm[x].setText(m+"");
		}
	}
	
	/**
	 * ������µ�1�������ڼ�
	 * @param year
	 * @param month
	 */
	public int getWeekDay(int year,int month){
		Calendar current=Calendar.getInstance();
		current.set(Calendar.YEAR, year);
		current.set(Calendar.MONTH,month-1);
		current.set(Calendar.DAY_OF_MONTH, 1);
		return current.get(Calendar.DAY_OF_WEEK);//��  1   һ 2
	}
	
	
	
	public static void main(String[] args) {
		//new CalendarUI();
	}
	
	
	
	/**
	 * �ж�ѡ����·��ж�����
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
			tc.setText(year.getSelectedItem()+"-"+month.getSelectedItem()+"-"+t.getText()+" "+hour.getSelectedItem()+":"+min.getSelectedItem()+":00");
			this.dispose();
			//dd=t.getText();
			
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
