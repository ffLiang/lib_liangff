package com.yunlong.Cinema.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

public  class MainUI extends JFrame implements ActionListener,MouseMotionListener,MouseListener {
	JTabbedPane jt=new JTabbedPane(JTabbedPane.TOP);
	private String aname;
	private int roles=0;
	private static int accountid;
	
	
	SystemTray st=null;
	Toolkit t=Toolkit.getDefaultToolkit();
	Image img=t.getImage("ioc/okmm.png").getScaledInstance(18, 15, Image.SCALE_DEFAULT);
	//ImageIcon i1=new ImageIcon(new ImageIcon("logo-icon.PNG").getImage().getScaledInstance(10, 20,Image.SCALE_DEFAULT));
	TrayIcon ti=null;
	JPopupMenu pm=new JPopupMenu("����");
	JMenuItem m1=new JMenuItem("��");
	JMenuItem m2=new JMenuItem("�˳�");
	JMenuItem m3=new JMenuItem("����");
	public MainUI(String aname,int roles,int accountid){
		this.aname=aname;
		this.roles=roles;
		this.accountid=accountid;
		if(roles==1){
			jt.add("������Ϣ",new AccountMessageUI());
			jt.add("��Ʊ��Ϣ",new SellUI());
		}
		if(roles==2){
			jt.add("������Ϣ",new AccountMessageUI());
			jt.add("��Ʊ��Ϣ",new SellUI());
			jt.add("��ӳ����",new FilmAndShowPUI());
		}
		if(roles==3){
			jt.add("������Ϣ",new AccountMessageUI());
			jt.add("��Ʊ��Ϣ",new SellUI());
			jt.add("��ӳ����",new FilmAndShowPUI());
			jt.add("ϵͳ����",new AccountUI());
		}
		
		pm.setPopupSize(60,90);
		pm.add(m1);
		pm.add(m2);
		pm.add(m3);
		
		m1.addActionListener(this);
		m2.addActionListener(this);
		m3.addActionListener(this);
		
		if(SystemTray.isSupported()){
		st=SystemTray.getSystemTray();
		ti=new TrayIcon(img);
		try {
			st.add(ti);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ti.setToolTip("ƱƱ����");
		ti.addMouseListener(this);
		ti.addMouseMotionListener(this);
		pm.addMouseMotionListener(this);
		}
		
		this.add(jt);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setTitle("��ǰ�û���"+aname);
	}
	public static int getAccountid() {
		return accountid;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==1&&e.getClickCount()==2){
			this.setVisible(true);
		}else{
			if(e.getButton()==3){
				pm.show(null, e.getX(), e.getY()-85);
			}else{
				pm.setVisible(false);
			}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==m1){
			this.setVisible(true);
			pm.setVisible(false);
		}
		if(e.getSource()==m2){
			System.exit(0);
		}
		if(e.getSource()==m3){
			this.dispose();
			pm.setVisible(false);
		}
		
	}
	
}
