package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.yunlong.Cinema.dao.AccountADO;
import com.yunlong.Cinema.dao.dateDAO;
import com.yunlong.Cinema.factory.DAOFactory;
import com.yunlong.Cinema.vo.Date;

@SuppressWarnings("serial")
public class AccountCalendarUI extends JFrame implements ItemListener, MouseListener,
		MouseWheelListener, KeyListener {

	public static Color todayColor = new Color(0xff6666);
	public static Color hasNoteColor = new Color(0x66ccff);
	public static Color hoverColor = new Color(0xf0f0f0);
	public static Color todayHoverColor = new Color(0xffcccc);

	public static Font dateFont = new Font("sans-herif", Font.PLAIN, 16);
	public static Font noteFont = new Font("sans-herif", Font.PLAIN, 20);

	public static int PREFERRED_WIDTH = 560;

	public static int currDay = Calendar.getInstance().get(Calendar.DATE);
	public static int currMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
	public static int currYear = Calendar.getInstance().get(Calendar.YEAR);
	public static int checkDay = 0;
	public static int checkMonth = 0;
	public static int checkYear = 0;
	public static int checkMonthday = 0;
	
	public static dateDAO dated=DAOFactory.getdatedao();
	public static AccountADO accout=DAOFactory.getAccountdao();

	JButton prevBtn = new JButton("<");
	JButton nextBtn = new JButton(">");
	JButton todayBtn = new JButton("今天");
	JButton newBtn = new JButton("添加…");
	JComboBox year = new JComboBox();
	JLabel l1 = new JLabel("年");
	JComboBox month = new JComboBox();
	JLabel l2 = new JLabel("月");
	JPanel p = new JPanel();
	JPanel can = new JPanel();
	JPanel notePanel = new JPanel();
	JLabel noteNumberLabel = new JLabel();
	JPanel noteLabelPanel = new JPanel();
	JPanel container = new JPanel();
	String week[] = { "日", "一", "二", "三", "四", "五", "六" };
	// 日历区域是6行7列的组件 JLabel
	JLabel tm[] = new JLabel[49];// 值分配了空间 没有指定对象

	JTextField tc = new JTextField();

	public static void main(String[] args) {
		new AccountCalendarUI();
	}

	public AccountCalendarUI() {
		// 设置日历区域的布局
		can.setLayout(new GridLayout(7, 7));
		// 年份初始化
		for (int i = 1990; i < 2021; i++) {// 2012
			year.addItem(i + "");
		}
		// 月份初始化
		for (int i = 1; i < 13; i++) {
			month.addItem(i + "");
		}
		// 标签初始化
		for (int i = 0; i < 49; i++) {
			tm[i] = new JLabel();
			tm[i].setHorizontalAlignment(JLabel.CENTER);
			can.add(tm[i]);// 把42个JLabel标签添加到can对象上
		}
		// 设置第一排的值
		for (int x = 0; x < 7; x++) {
			tm[x].setText(week[x]);
			tm[x].setFont(dateFont);
		}
		for (int x = 7; x < 49; x++) {
			tm[x].addMouseListener(this);
			tm[x].setFont(dateFont);
		}
		// 需要设置为当前月份的日期

		/*
		 * for (int i = 7; i < 42; i++) { tm[i].setText(i+""); }
		 */

		init();
		//prevBtn.setPreferredSize(new Dimension(30, 20));
		prevBtn.addMouseListener(this);
		prevBtn.setFocusable(false);
		p.add(prevBtn);
		year.addItemListener(this);
		month.addItemListener(this);
		year.setFocusable(false);
		month.setFocusable(false);
		p.add(year);
		p.add(l1);
		p.add(month);
		p.add(l2);
		//nextBtn.setPreferredSize(new Dimension(30, 20));
		nextBtn.addMouseListener(this);
		nextBtn.setFocusable(false);
		p.add(nextBtn);
		//todayBtn.setPreferredSize(new Dimension(40, 20));
		todayBtn.addMouseListener(this);
		todayBtn.setFocusable(false);
		p.add(todayBtn);
		//newBtn.setPreferredSize(new Dimension(60, 20));
		newBtn.addMouseListener(this);
		newBtn.setFocusable(false);
		p.add(newBtn);
		p.setPreferredSize(new Dimension(PREFERRED_WIDTH, 30));

		this.getContentPane().add(p, BorderLayout.NORTH);
		can.setPreferredSize(new Dimension(PREFERRED_WIDTH, 280));
		can.setBackground(Color.WHITE);
		can.addMouseWheelListener(this);
		container.add(can, BorderLayout.NORTH);
		notePanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, 100));
		notePanel.setLayout(new GridLayout(2, 1));
		noteNumberLabel.setFont(noteFont);
		noteNumberLabel.setHorizontalAlignment(JLabel.CENTER);
		notePanel.add(noteNumberLabel, BorderLayout.NORTH);
		noteLabelPanel.setLayout(new GridLayout(1, 1));
		notePanel.add(noteLabelPanel);
		container.add(notePanel);
		this.getContentPane().add(container);

		p.addKeyListener(this);

		this.setTitle("日历");
		this.setSize(PREFERRED_WIDTH, 560);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		// this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

		p.requestFocus();
	}

	public void itemStateChanged(ItemEvent e) {
		setCalendar(Integer.parseInt(year.getSelectedItem().toString()),
				Integer.parseInt(month.getSelectedItem().toString()));
	}

	/**
	 * 把下拉框的值选择为当前年月
	 */
	public void init() {
		year.setSelectedIndex(currYear - 1990);
		month.setSelectedIndex(currMonth - 1);
		setCalendar(currYear, currMonth);
	}

	public void setCalendar(int yy, int mm) {
		checkDayOff();
		checkMonth = mm;
		checkYear = yy;
		if (checkMonth == currMonth && checkYear == currYear) {
			checkDay = currDay;
		} else {
			checkDay = 1;
		}
		// 清空数字标签
		for (int i = 7; i < 49; i++) {
			tm[i].setText("");
			tm[i].setOpaque(true);
			tm[i].setBackground(Color.WHITE);
			tm[i].setForeground(Color.BLACK);
		}
		int weekday = getWeekDay(yy, mm);
		checkMonthday = day(yy, mm);
		// 日 8 下标为7
		// 一 9 8
		for (int x = 6 + weekday, m = 1; m <= checkMonthday; x++, m++) {
			tm[x].setText(m + "");
			if (hasNote(m)) {
				tm[x].setForeground(hasNoteColor);
			}
		}
		checkDayOn();
	}

	public boolean checkingToday() {
		return checkDay == currDay && checkMonth == currMonth
				&& checkYear == currYear;
	}

	public void checkDayOn() {
		int ind = 5 + checkDay + getWeekDay(checkYear, checkMonth);
		tm[ind].setForeground(Color.WHITE);
		if (checkingToday()) {
			tm[ind].setBackground(todayColor);
		} else {
			tm[ind].setBackground(Color.LIGHT_GRAY);
		}
		printNote(checkDay);
	}

	public void checkDayOff() {
		int ind = 5 + checkDay + getWeekDay(checkYear, checkMonth);
		tm[ind].setBackground(Color.WHITE);
		if (checkingToday()) {
			tm[ind].setForeground(todayColor);
		} else {
			if (hasNote(checkDay)) {
				tm[ind].setForeground(hasNoteColor);
			} else {
				tm[ind].setForeground(Color.BLACK);
			}
		}
		clearNote();
	}

	public void seePrevMonth() {
		int m = checkMonth, y = checkYear;
		if (checkMonth + checkYear > 1991) {
			if (checkMonth <= 1) {
				m = 12;
				y = checkYear - 1;
			} else {
				m = checkMonth - 1;
			}
			year.setSelectedIndex(y - 1990);
			month.setSelectedIndex(m - 1);
			setCalendar(checkYear, checkMonth);
		} else {
			seeFirstDay();
		}
	}

	public void seeCurrDay() {
		init();
	}

	public void seeNextMonth() {
		int m = checkMonth, y = checkYear;
		if (checkMonth + checkYear < 2032) {
			if (checkMonth >= 12) {
				m = 1;
				y = checkYear + 1;
			} else {
				m = checkMonth + 1;
			}
			year.setSelectedIndex(y - 1990);
			month.setSelectedIndex(m - 1);
			setCalendar(checkYear, checkMonth);
		} else {
			seeLastDay();
		}
	}

	public void seeLastDay() {
		checkDayOff();
		checkDay = checkMonthday;
		checkDayOn();
	}

	public void seeFirstDay() {
		checkDayOff();
		checkDay = 1;
		checkDayOn();
	}

	public void seePrevDay() {
		if (checkDay <= 1) {
			seePrevMonth();
			seeLastDay();
		} else {
			checkDayOff();
			checkDay--;
			checkDayOn();
		}
	}

	public void seeNextDay() {
		if (checkDay >= checkMonthday) {
			seeNextMonth();
			seeFirstDay();
		} else {
			checkDayOff();
			checkDay++;
			checkDayOn();
		}
	}

	public void seePrevWeek() {
		if (checkDay - 7 < 1) {
			int ckD = checkDay;
			seePrevMonth();
			checkDayOff();
			checkDay = checkMonthday - 7 + ckD;
			checkDayOn();
		} else {
			checkDayOff();
			checkDay -= 7;
			checkDayOn();
		}
	}

	public void seeNextWeek() {
		if (checkDay + 7 > checkMonthday) {
			int ckD = checkDay + 7 - checkMonthday;
			seeNextMonth();
			checkDayOff();
			checkDay = ckD;
			checkDayOn();
		} else {
			checkDayOff();
			checkDay += 7;
			checkDayOn();
		}
	}

	/**
	 * 这个年月的1号是星期几
	 * 
	 * @param year
	 * @param month
	 */
	public int getWeekDay(int year, int month) {
		Calendar current = Calendar.getInstance();
		current.set(Calendar.YEAR, year);
		current.set(Calendar.MONTH, month - 1);
		current.set(Calendar.DAY_OF_MONTH, 1);
		return current.get(Calendar.DAY_OF_WEEK);// 日 1 一 2
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
		if (month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) {
			d = 29;
		}
		return d;
	}

	public void mouseClicked(MouseEvent e) {
		// JLabel t = (JLabel) e.getSource();
		Object o = e.getSource();
		if (o.getClass() == JLabel.class) {
			JLabel t = (JLabel) o;
			if (t.getText().trim().length() != 0) {
				int day = Integer.parseInt(t.getText());
				if (day != checkDay) {
					checkDayOff();
					checkDay = day;
					checkDayOn();
				}
			}
			if (e.getClickCount() == 2) {
				new seeNoteUI();
			}
		} else if (o.getClass() == JButton.class) {
			if (o == prevBtn) {
				seePrevMonth();
			} else if (o == nextBtn) {
				seeNextMonth();
			} else if (o == todayBtn) {
				seeCurrDay();
			} else if (o == newBtn) {
				new newNoteUI();
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		if (o.getClass() == JLabel.class) {
			JLabel t = (JLabel) o;
			if (t.getText().trim().length() != 0) {
				int day = Integer.parseInt(t.getText());
				if (day != checkDay) {
					if (day == currDay && checkMonth == currMonth
							&& checkYear == currYear) {
						tm[5
								+ day
								+ getWeekDay(Integer.parseInt(year
										.getSelectedItem().toString()),
										Integer.parseInt(month
												.getSelectedItem().toString()))]
								.setBackground(todayHoverColor);
					} else {
						tm[5
								+ day
								+ getWeekDay(Integer.parseInt(year
										.getSelectedItem().toString()),
										Integer.parseInt(month
												.getSelectedItem().toString()))]
								.setBackground(hoverColor);
					}
				}
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o.getClass() == JLabel.class) {
			JLabel t = (JLabel) o;
			if (t.getText().trim().length() != 0) {
				int day = Integer.parseInt(t.getText());
				if (day != checkDay) {
					tm[5
							+ day
							+ getWeekDay(Integer.parseInt(year
									.getSelectedItem().toString()),
									Integer.parseInt(month.getSelectedItem()
											.toString()))]
							.setBackground(Color.WHITE);
				}
			}
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() == 1)
			seePrevMonth();
		else if (e.getWheelRotation() == -1)
			seeNextMonth();
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) {
			seeNextDay();
		} else if (keyCode == KeyEvent.VK_LEFT) {
			seePrevDay();
		} else if (keyCode == KeyEvent.VK_UP) {
			seePrevWeek();
		} else if (keyCode == KeyEvent.VK_DOWN) {
			seeNextWeek();
		}
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent e) {
	}

	public boolean hasNote(int d) {
		boolean a=false;
		List<String> k=dated.list(MainUI.getAccountid());
		String[] kk = k.toArray(new String[k.size()]);
		int i=0;
		for(i=0;i<k.size();i++){
			if(d==Integer.parseInt(kk[i].split("-")[2]))
				a=true;
		}
		return a;
	}

	public int getNoteNumber(int d) {
		int i=0;
		int n=0;
		List<String> k=dated.list(MainUI.getAccountid());
		String[] kk = k.toArray(new String[k.size()]);
		for(i=0;i<k.size();i++){
			if(d==Integer.parseInt(kk[i].split("-")[2]))
				n++;
		}
		return n;
	}

	public void printNote(int d) {
		if (!hasNote(d))
			noteNumberLabel.setText("无事件");
		else {
			int noteNumber = getNoteNumber(d);
			noteNumberLabel.setText(noteNumber + "个事件");
			Box vbox = Box.createVerticalBox();
			for (int i = 0; i < noteNumber; i++) {
				JLabel noteLabel = new JLabel();
				noteLabel.setFont(noteFont);
				noteLabel.setText("   "+dated.note(MainUI.getAccountid(),
						                AccountCalendarUI.checkYear + "-" + AccountCalendarUI.checkMonth + "-"
						                + AccountCalendarUI.checkDay).get(i));
				noteLabel.setHorizontalAlignment(JLabel.CENTER);
				vbox.add(noteLabel);
				//System.out.println("note added!");
			}
			noteLabelPanel.add(vbox);
//			noteLabelPanel.setBackground(Color.WHITE);
			noteLabelPanel.revalidate();
		}
	}

	public void clearNote() {
		noteLabelPanel.removeAll();
		noteLabelPanel.repaint();
	}

}

@SuppressWarnings("serial")
class seeNoteUI extends JDialog implements ActionListener, MouseListener {

	public seeNoteUI() {
		init();
	}

	public boolean hasNote(int h) {
		return h == 20;
	}

	public void init() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(24, 1));
		for (int i = 0; i < 24; i++) {
			JLabel hour = new JLabel(i + ":00");
			hour.setFont(AccountCalendarUI.dateFont);
			hour.setOpaque(true);
			// if(hasNote(i))
			// hour.setBackground(CalendarUI.hasNoteColor);
			hour.addMouseListener(this);
			panel.add(hour);
		}
		panel.setPreferredSize(new Dimension(300, 1200));
		JScrollPane jsp = new JScrollPane(panel);
		this.getContentPane().add(jsp);
		this.setBackground(Color.WHITE);
		// this.setModal(true);
		this.setResizable(false);
		// this.setLocationRelativeTo(null);
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(320, 480));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle(AccountCalendarUI.checkYear + "-" + AccountCalendarUI.checkMonth + "-"
				+ AccountCalendarUI.checkDay);
		this.setVisible(true);
		this.requestFocus();
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		if (o.getClass().equals(JLabel.class)) {
			JLabel t = (JLabel) o;
			t.setBackground(AccountCalendarUI.hoverColor);
		}
	}

	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o.getClass().equals(JLabel.class)) {
			JLabel t = (JLabel) o;
			t.setBackground(Color.WHITE);
		}

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

}

@SuppressWarnings("serial")
class newNoteUI extends JDialog implements ActionListener {

	JLabel discribeLabel = new JLabel("描述:");
	JLabel timeLabel = new JLabel("时间:");
	JLabel hourLabel = new JLabel("时");
	JLabel minLabel = new JLabel("分");
	JTextField discribeText = new JTextField();
	JComboBox hour = new JComboBox();
	JComboBox min = new JComboBox();
	JButton submit = new JButton("完成");
	JButton cancel = new JButton("取消");
	
	AccountCalendarUI ui;
	seeNoteUI ui2;
	
	public static dateDAO dated=DAOFactory.getdatedao();

	public newNoteUI() {
		init();
	}

	public void init() {
		this.getContentPane().setLayout(new GridLayout(3, 1));

		Box topBox = Box.createHorizontalBox();
		topBox.add(discribeLabel);
		Box.createHorizontalStrut(10);
		topBox.add(discribeText);
		this.getContentPane().add(topBox);

		Box midBox = Box.createHorizontalBox();
		midBox.add(timeLabel);
		Box.createHorizontalStrut(10);
		for (int i = 0; i < 24; i++)
			hour.addItem(i + "");
		midBox.add(hour);
		Box.createHorizontalStrut(10);
		midBox.add(hourLabel);
		Box.createHorizontalStrut(10);
		for (int i = 0; i < 60; i++)
			min.addItem(i + "");
		midBox.add(min);
		Box.createHorizontalStrut(10);
		midBox.add(minLabel);
		this.getContentPane().add(midBox);

		JPanel btnPanel = new JPanel();
		btnPanel.add(submit, BorderLayout.WEST);
		btnPanel.add(cancel, BorderLayout.EAST);
		this.getContentPane().add(btnPanel);

		this.setTitle(AccountCalendarUI.checkYear + "-" + AccountCalendarUI.checkMonth + "-"
				+ AccountCalendarUI.checkDay);
		this.setSize(new Dimension(300, 150));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		submit.addActionListener(this);
		cancel.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		String h,m;
		
		if(o==submit){
			int aID=MainUI.getAccountid();
			String datek=AccountCalendarUI.checkYear+"-"+AccountCalendarUI.checkMonth+"-"
					     +AccountCalendarUI.checkDay;
			h=hour.getSelectedItem().toString();
			m=min.getSelectedItem().toString();
			if (hour.getSelectedItem().toString().length()==1){
				h="0"+h;
			}
			if (min.getSelectedItem().toString().length()==1){
				m="0"+m;
			}
			String notek=h + ":" + m + "  " + discribeText.getText();
			dated.insertNote(new Date(aID,datek,notek));
			JOptionPane.showMessageDialog(this, "添加成功");
			ui.init();
			//ui2.init();
		}else if(o==cancel){
			this.dispose();
		}
	}

}
