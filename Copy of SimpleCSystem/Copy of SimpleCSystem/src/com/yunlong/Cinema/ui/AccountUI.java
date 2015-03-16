package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.yunlong.Cinema.dao.imp.AccountADOImp;
import com.yunlong.Cinema.dao.imp.SellDAOImp;
import com.yunlong.Cinema.factory.DAOFactory;
import com.yunlong.Cinema.util.MD5;
import com.yunlong.Cinema.vo.Account;

public class AccountUI extends JPanel implements ActionListener{
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	
	JPanel p6=new JPanel();
	
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel p5=new JPanel();
	
	JPanel p7=new JPanel();//
	JPanel p8=new JPanel();//
	
	DefaultTableModel dtm1=new DefaultTableModel();
	DefaultTableModel dtm2=new DefaultTableModel();
	
	
	//JTable t1=new JTable(dtm1);
	JPanel pa=new JPanel();
	JPanel pa1=new JPanel();
	JPanel pa2=new JPanel();
	JPanel p21=new JPanel();
	JPanel p22=new JPanel();
	JTable t2=new JTable(dtm2);
	
	JLabel e1=new JLabel("从");
	JLabel e2=new JLabel("    ");
	JLabel e3=new JLabel("到");
	JLabel e4=new JLabel("    ");
	JLabel e5=new JLabel("统计方式");
	JLabel e6=new JLabel("售出");
	JLabel e7=new JLabel("总额");
	
	//JScrollPane js1=new JScrollPane(t1);
	JScrollPane Js2=new JScrollPane(t2);

	JButton b1=new JButton("统计");
	JButton b2=new JButton("统计");
	JButton b3=new JButton("统计");
	
	JLabel l0=new JLabel("从");
	JLabel l1=new JLabel("～");
	JLabel l2=new JLabel("时间段");
	JLabel l3=new JLabel("按电影院：");
	JLabel l4=new JLabel("按电影名：");
	JLabel l5=new JLabel("按用户：");
	
	JLabel l6=new JLabel("用户姓名：");
	JLabel l7=new JLabel("密码：");
	JLabel l8=new JLabel("状态：");
	JLabel l9=new JLabel("权限：");
	JLabel l10=new JLabel("账号：");
	
	JTextField jt1=new JTextField(8);
	JTextField jt2=new JTextField(8);
	JTextField jt3=new JTextField(8);
	
	JTextField jt4=new JTextField(8);
	JPasswordField jt5=new JPasswordField(8);
	String s[]={"可用","禁用"};
	JComboBox jc3=new JComboBox(s);
	String r[]={"用户","管理员","超级管理员"};
	JComboBox jc4=new JComboBox(r);
	JTextField jt6=new JTextField(8);
	
	Vector<String> un=DAOFactory.getAccountdao().aName();
	JComboBox jc2=new JComboBox(un);
	
	JButton b4=new JButton("添加用户");
	JButton b5=new JButton("修改资料");
	
	int index=-1;//初始化点击table的行
	
	MD5 md5=new MD5();
	int accid;
	
	AccountADOImp aai=DAOFactory.getAccountdao();
	SellDAOImp sdi=DAOFactory.getSelldao();
	
	JComboBox jc1;
	public AccountUI(){
		Vector<String> rn=DAOFactory.getPlacedao().PlaceName();
		Vector<String> RN=new Vector<String>();
		RN.add("所有");
		for (int i = 0; i <rn.size(); i++) {
			RN.add(rn.get(i));
		}
		jc1=new JComboBox(RN);
		this.setLayout(new GridLayout(1, 2));
		
		this.add(p1);
		this.add(p2);
		
		e1.setFont(new Font("宋体", Font.BOLD, 20));
		e2.setFont(new Font("宋体", Font.BOLD, 20));
		e3.setFont(new Font("宋体", Font.BOLD, 20));
		e4.setFont(new Font("宋体", Font.BOLD, 20));
		e5.setFont(new Font("宋体", Font.BOLD, 20));
		e6.setFont(new Font("宋体", Font.BOLD, 20));
		e7.setFont(new Font("宋体", Font.BOLD, 20));
		p1.setLayout(new BorderLayout());
		p1.setBorder(BorderFactory.createTitledBorder("收入统计"));
		p1.add(pa);
		pa.setLayout(new BorderLayout());
		pa.add(pa1,BorderLayout.NORTH);
		pa1.add(e1);
		pa1.add(e2);
		pa1.add(e3);
		pa1.add(e4);
		pa.add(pa2);
		pa2.setLayout(new GridLayout(1,2));
		pa2.add(p21);
		p21.setLayout(new GridLayout(3,1));
		JPanel jp1=new JPanel();
		jp1.add(e5);
		JPanel jp2=new JPanel();
		jp2.add(e6);
		JPanel jp3=new JPanel();
		jp3.add(e7);
		p21.add(jp1);
		p21.add(jp2);
		p21.add(jp3);
		pa2.add(p22);
		//ImageIcon ii=new ImageIcon(new ImageIcon("ioc/kzw2.png").getImage().getScaledInstance(350, 600,Image.SCALE_DEFAULT));
		//JLabel jm=new JLabel(ii);
		//p22.add(jm);
		
		p1.add(p3,BorderLayout.SOUTH);
		p3.setLayout(new GridLayout(2,1));
		p3.add(p4);
		p3.add(p5);
		p4.add(l0);
		p4.add(jt1);
		p4.add(l1);
		p4.add(jt2);
		p4.add(l2);
		p5.add(l3);
		p5.add(jc1);
		p5.add(b1);
		p5.add(l4);
		p5.add(jt3);
		p5.add(b2);
		p5.add(l5);
		p5.add(jc2);
		p5.add(b3);
		
		p2.setLayout(new BorderLayout());
		p2.setBorder(BorderFactory.createTitledBorder("账号管理"));
		p2.add(Js2);
		p2.add(p6,BorderLayout.SOUTH);
		p6.setLayout(new GridLayout(2,1));
		p6.add(p7);
		p7.add(l6);
		p7.add(jt4);
		p7.add(l7);
		p7.add(jt5);
		p7.add(l8);
		p7.add(jc3);
		p7.add(l9);
		p7.add(jc4);
		p7.add(l10);
		p7.add(jt6);
		
		p6.add(p8);
		p8.add(b4);
		p8.add(b5);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		t2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index=t2.getSelectedRow();
				if(index!=-1){
					accid=Integer.parseInt(t2.getValueAt(index, 0).toString().trim());
					Account a=aai.idSelect(accid);
					jt4.setText(a.getUserName());
					jt5.setText("");
					jc3.setSelectedIndex(a.getState()-1);
					jc4.setSelectedIndex(a.getRoles()-1);
					jt6.setText(a.getAccountName());
				}
				super.mouseClicked(e);
			}
		});
		jt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			JTextField j=(JTextField)e.getSource();
			new CalendarUII(j);
			}
		});
		jt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			JTextField j=(JTextField)e.getSource();
			new CalendarUII(j);
			}
		});
		initAccount();
		
	}
	
	public void initAccount(){
		dtm2.setDataVector(aai.aselect(), aai.colAccount());
		initCell();
	}
	public void initCell(){
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		dtc.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		t2.getColumn("用户编号").setCellRenderer(dtc);
		t2.getColumn("用户姓名").setCellRenderer(dtc);
		t2.getColumn("密码").setCellRenderer(dtc);
		t2.getColumn("状态").setCellRenderer(dtc);
		t2.getColumn("权限").setCellRenderer(dtc);
		t2.getColumn("账号").setCellRenderer(dtc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b4){
			String user=jt4.getText().trim().toString();
			String pw=new String(jt5.getPassword());
			int st=jc3.getSelectedIndex()+1;
			int ro=jc4.getSelectedIndex()+1;
			String acc=jt6.getText().trim().toString();
			boolean n=user.length()!=0&&user!=null&&pw.length()!=0&&pw!=null&&acc.length()!=0&&acc!=null;
			if(n){
				Account at=new Account(0, user, md5.getMD5ofStr(pw), st, ro, acc);
				boolean m=aai.insert(at);
				if(m){
					JOptionPane.showMessageDialog(this,"添加成功");
					dtm2.setDataVector(aai.aselect(), aai.colAccount());
					initCell();
					
				}else{
					JOptionPane.showMessageDialog(this,"添加失败");
				}
			}else{
				JOptionPane.showMessageDialog(this,"请将信息添加完整");
			}
		}
		
		if(e.getSource()==b5){
			if(index!=-1){
				//if(){}
			String user=jt4.getText().trim().toString();
			String pw=new String(jt5.getPassword());
			int st=jc3.getSelectedIndex()+1;
			int ro=jc4.getSelectedIndex()+1;
			String acc=jt6.getText().trim().toString();
			boolean n=user.length()!=0&&user!=null&&acc.length()!=0&&acc!=null;
			if(n){
				boolean bl=pw.length()==0||pw==null;
				Account at=new Account(accid, user, md5.getMD5ofStr(pw), st, ro, acc);
				boolean m=false;
				if(bl){
					m=aai.editNP(at);
					}else{
					m=aai.edit(at);	
					}
				if(m){
					JOptionPane.showMessageDialog(this,"修改成功");
					dtm2.setDataVector(aai.aselect(), aai.colAccount());
					initCell();
					
				}else{
					JOptionPane.showMessageDialog(this,"修改失败");
				}
				
			}else{
				JOptionPane.showMessageDialog(this,"请将信息添加完整");
			}
			}else{
				JOptionPane.showMessageDialog(this,"请点击您要修改的用户");
			}
		}
		if(e.getSource()==b1){
			int r=jc1.getSelectedIndex();
			String rna=jc1.getSelectedItem().toString().trim();
			String star=jt1.getText().trim();
			String end=jt2.getText().trim();
			boolean n=star.length()!=0&&jt1.getText()!=null&&end.length()!=0&&jt2.getText()!=null;
			if(n){
				if(r==0){
					Vector<Object> v=sdi.State(star, end);
					e2.setText(star);
					e4.setText(end);
					e5.setText(rna+"电影院");
					e6.setText("售出："+v.get(0)+"张票");
					e7.setText("总额："+v.get(1));
				}else{
					Vector<Object> v=sdi.placeStat(rna, star, end);
					e2.setText(star);
					e4.setText(end);
					e5.setText(rna);
					e6.setText("售出："+v.get(0)+"张票");
					e7.setText("总额："+v.get(1));
				}
			}else{
				JOptionPane.showMessageDialog(this, "请填写时间段");
			}
			
		}
		if(e.getSource()==b2){
				String star=jt1.getText().trim();
				String end=jt2.getText().trim();
				String fn=jt3.getText().trim();
				boolean n=star.length()!=0&&jt1.getText()!=null&&end.length()!=0&&jt2.getText()!=null;
				boolean m=star.length()==0&&end.length()==0;
				if(n||m){
					if(n){
					Vector<Object> v=sdi.filmStat(fn, star, end);
					e2.setText(star);
					e4.setText(end);
					e5.setText(fn);
					e6.setText("售出："+v.get(0)+"张票");
					e7.setText("总额："+v.get(1));
					}else{
						Vector<Object> v=sdi.filmStat(fn, "1900-1-1", "2100-1-1");
						e2.setText(star);
						e4.setText(end);
						e5.setText(fn);
						e6.setText("售出："+v.get(0)+"张票");
						e7.setText("总额："+v.get(1));
					}					
				}else{
					JOptionPane.showMessageDialog(this, "请将时间段填写完整或不填");
				}
			}
		if(e.getSource()==b3){
			String star=jt1.getText().trim();
			String end=jt2.getText().trim();
			String sname=jc2.getSelectedItem().toString().trim();
			boolean n=star.length()!=0&&jt1.getText()!=null&&end.length()!=0&&jt2.getText()!=null;
			if(n){
				Vector<Object> v=sdi.accountStat(sname, star, end);
				e2.setText(star);
				e4.setText(end);
				e5.setText(sname);
				e6.setText("购买："+v.get(0)+"张票");
				e7.setText("总额："+v.get(1));
			}else{
				JOptionPane.showMessageDialog(this, "请填写时间段");
			}
		}
	}
}
