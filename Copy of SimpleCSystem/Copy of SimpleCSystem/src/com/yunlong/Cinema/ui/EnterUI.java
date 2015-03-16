package com.yunlong.Cinema.ui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.yunlong.Cinema.dao.imp.AccountADOImp;
import com.yunlong.Cinema.util.MD5;
import com.yunlong.Cinema.vo.Account;

public class EnterUI extends JFrame implements ActionListener,KeyListener{
	JPanel p = new JPanel();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p0 = new JPanel();
	JPanel p4 = new JPanel();
	
	JButton b = new JButton("登陆");
	JButton bb = new JButton("修改密码");
	JButton b1 = new JButton("清空");
	JTextField t = new JTextField(12);
	JPasswordField t1 = new JPasswordField(12);
	JLabel l = new JLabel("账号：");
	JLabel l1 = new JLabel("密码：");
	JLabel li = new JLabel("票票亮亮");
	
	public EnterUI() {
		this.setTitle("登陆");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(300, 250);
		this.setLayout(new GridLayout(5, 1));
		li.setFont(new Font("华文行楷", Font.BOLD, 35));
		li.setForeground(Color.blue);
		this.add(p0);
		p0.add(li);
		
		this.add(p4);
		
		this.add(p);
		p.add(l);
		p.add(t);

		this.add(p1);
		p1.add(l1);
		p1.add(t1);

		this.add(p2);
		p2.add(b);
		p2.add(bb);
		p2.add(b1);

		b.addActionListener(this);
		b1.addActionListener(this);
		bb.addActionListener(this);
		this.addKeyListener(this);
		t.addKeyListener(this);
		t1.addKeyListener(this);
	}

	public static void main(String[] args)
			throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new AcrylLookAndFeel());
		new EnterUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b) {
			ent();
		}
		if (e.getSource() == b1) {
			t.setText("");
			t1.setText("");
		}
		if(e.getSource() == bb){
			MD5 md5 = new MD5();
			String ac = t.getText().trim();
			String pw = new String(t1.getPassword());
			if (ac.trim().length() != 0 && pw.trim().length() != 0) {
				AccountADOImp adi = new AccountADOImp();
				Account at = new Account(0, "", md5.getMD5ofStr(pw), 0, 0, ac);

				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//EXIT_ON_CLOSE（在 JFrame 中定义）：使用 System exit 方法退出应用程序。仅在应用程序中使用。 
				Account acc = adi.enter(at);
				if (acc == null) {
					JOptionPane.showMessageDialog(this, "帐户或者密码有误");
				} else if (acc.getState() == 2) {
					JOptionPane.showMessageDialog(this, "账号已被禁用,请联系管理员");
				} else {
					String aname = acc.getUserName();
					int accountid = acc.getAccountId();
					new EditpasswordUI(accountid, aname);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar()=='\n'){
			ent();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void ent(){
		MD5 md5 = new MD5();
		String ac = t.getText().trim();
		String pw = new String(t1.getPassword());
		if (ac.trim().length() != 0 && pw.trim().length() != 0) {
			AccountADOImp adi = new AccountADOImp();
			Account at = new Account(0, "", md5.getMD5ofStr(pw), 0, 0, ac);

			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Account acc = adi.enter(at);
			if (acc == null) {
				JOptionPane.showMessageDialog(this, "用户名或者密码有误");
			} else if (acc.getState() == 2) {
				JOptionPane.showMessageDialog(this, "账号已被禁用,请联系管理员");
			} else {
				int rl = acc.getRoles();
				String aname = acc.getUserName();
				int accountid = acc.getAccountId();
				new MainUI(aname, rl, accountid);
				this.dispose();
			}
		}
	}
}
