package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.yunlong.Cinema.dao.imp.AccountADOImp;
import com.yunlong.Cinema.factory.DAOFactory;
import com.yunlong.Cinema.util.MD5;

public class EditpasswordUI extends JDialog implements ActionListener{
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel P=new JPanel();
	
	JLabel l1=new JLabel("�������룺");
	JLabel l2=new JLabel("ȷ�����룺");
	JLabel l3=new JLabel("�޸�����");
	
	JPasswordField jp1=new JPasswordField(8);
	JPasswordField jp2=new JPasswordField(8);
	
	JButton b1=new JButton("ȷ��");
	JButton b2=new JButton("ȡ��");
	MD5 md5=new MD5();
	AccountADOImp aai=DAOFactory.getAccountdao();
	int ID;
	String UName;
	public EditpasswordUI(int id,String username){
		this.ID=id;
		this.UName=username;
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(P,BorderLayout.CENTER);
		P.setLayout(new GridLayout(4, 1));
		P.add(p1);
		p1.add(l3);
		P.add(p2);
		p2.add(l1);
		p2.add(jp1);
		P.add(p3);
		p3.add(l2);
		p3.add(jp2);
		P.add(p4);
		p4.add(b1);
		p4.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		l3.setFont(new Font("����", Font.BOLD, 18));
		
		this.setTitle(UName);
		this.setSize(250, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			String wp1=new String(jp1.getPassword());
			String wp2=new String(jp2.getPassword());
			if(wp1.equals(wp2)){
				boolean m=aai.epassword(ID,md5.getMD5ofStr(wp2));
				if(m){
					JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(this, "�޸�ʧ��");
				}
			}else{
				JOptionPane.showMessageDialog(this, "�������벻һ��");
			}
		}
		if(e.getSource()==b2){
			jp2.setText("");
			jp1.setText("");
		}
		
	}

}
