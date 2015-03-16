package com.yunlong.Cinema.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.BreakIterator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;
import javax.xml.crypto.Data;

import com.yunlong.Cinema.dao.imp.CategoryImp;
import com.yunlong.Cinema.dao.imp.FilmDAOImp;
import com.yunlong.Cinema.factory.DAOFactory;
import com.yunlong.Cinema.util.CalendarUI;
import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.Film;

public class InsertFilmUI extends JFrame implements ActionListener{
	JLabel l=new JLabel("电影入库");
	
	JLabel l1=new JLabel("电影名称：");
	JTextField t1=new JTextField(8);
	
	JLabel l2=new JLabel("电影导演：");
	JTextField t2=new JTextField(8);
	
	JLabel l3=new JLabel("电影主演：");
	JTextField t3=new JTextField(8);
	
	JLabel l4=new JLabel("电影时长：");
	JTextField t4=new JTextField(8);
	
	JLabel l5=new JLabel("所属地区：");
	JTextField t5=new JTextField(8);
	
	JLabel l7=new JLabel("上映时间：");
	JTextField t7=new JTextField(8);
	
	JButton b3=new JButton("添加海报");
	String pic=null;
	
	JLabel l0=new JLabel("请添加海报");
	JFileChooser fc=new JFileChooser();
	
	JLabel l9=new JLabel("状态：");
	String s[]={"上线","下线"};
	JComboBox c1=new JComboBox(s);
	
	JButton b1=new JButton("确定");
	JButton b2=new JButton("取消");
	
	JPanel pnorth=new JPanel();
	JPanel pcenter=new JPanel();
	JPanel psouth=new JPanel();
	
	JPanel pleft=new JPanel();
	JPanel pright=new JPanel();
	
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel p5=new JPanel();
	JPanel p6=new JPanel();
	JPanel p7=new JPanel();
	JPanel p8=new JPanel();
	JPanel p9=new JPanel();
	JPanel p10=new JPanel();
	JPanel p11=new JPanel();
	JPanel p12=new JPanel();
	Date date=new Date();
	JCheckBox check[];
	
	FilmDAOImp fdi=DAOFactory.getFilmdao();
	CategoryImp ci=DAOFactory.getCategorydao();
	FilmUI ui;
	public InsertFilmUI(FilmUI ui){
		this.ui=ui;
		this.setResizable(false);
		this.setTitle("电影入库");
		this.setSize(600, 650);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.add(pnorth,BorderLayout.NORTH);
		this.add(psouth,BorderLayout.SOUTH);
		
		l.setFont(new Font("楷书", Font.BOLD, 20));
		
		l0.setFont(new Font("",Font.BOLD,40));
		psouth.setLayout(new GridLayout(3, 1));
		this.add(pcenter);
		pcenter.setLayout(new GridLayout(1, 2));
		
		pcenter.add(pleft);
		pcenter.add(pright);
		//pright.setLayout();
		pright.add(l0,BorderLayout.NORTH);
		
		pleft.setLayout(new GridLayout(8, 1));
		
		pnorth.add(l);
		p2.add(l1);
		p2.add(t1);
		pleft.add(p2);
		p3.add(l2);
		p3.add(t2);
		pleft.add(p3);
		p4.add(l3);
		p4.add(t3);
		pleft.add(p4);
		p5.add(l4);
		p5.add(t4);
		pleft.add(p5);
		p6.add(l5);
		p6.add(t5);
		pleft.add(p6);
		p8.add(l7);
		p8.add(t7);
		pleft.add(p8);
		p9.add(l9);
		p9.add(c1);
		pleft.add(p9);
		
		p12.setLayout(new GridLayout(2,10));
		List<Category> list=DAOFactory.getCategorydao().list();
		check=new JCheckBox[list.size()];
		for (int i = 0; i < check.length; i++) {
			check[i]=new JCheckBox(list.get(i).getCategoryName());
			p12.add(check[i]);
		}
		psouth.add(p12);
		p10.add(b3);
		psouth.add(p10);
		p11.add(b1);
		p11.add(b2);
		psouth.add(p11);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		t7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new CalendarUI(t7);
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			String fname=t1.getText().trim();
			String fdir=t2.getText().trim();
			String fpro=t3.getText().trim();
			String ftl=t4.getText().trim();
			String freg=t5.getText().trim();
			
			String fst=t7.getText().trim();
			int st=c1.getSelectedIndex()+1;
			String picname="picture\\"+System.currentTimeMillis()+date.getMonth()+date.getDate()+pic.substring(pic.lastIndexOf("."));
			
			boolean m=(fname.length()!=0&&fdir.length()!=0&&fpro.length()!=0&&ftl.length()!=0&&freg.length()!=0&&fst.length()!=0&&pic.length()!=0);
			if(m){
				Film film=new Film(0, fname, fdir, fpro, ftl, freg, fst, picname, st);
				boolean n=fdi.insert(film);
				if(n){
					int x=0;
					for (int i = 0; i < check.length; i++) {
						if(check[i].isSelected()){
							x++;
						}
					}
					if(x!=0){
						boolean bo=true;
						for (int i = 0; i < check.length; i++) {
							if(check[i].isSelected()){
								bo=bo&&fdi.insertcate(fdi.list(fname).getFilmId(),ci.cate(check[i].getText()) );	
							}
						}
						try {
							FileInputStream is=new FileInputStream(pic);
							FileOutputStream fos=new FileOutputStream(picname);
							byte bs[]=new byte[1024*1024*5];
							while(true){
								int len=is.read(bs);
								if(len==-1){
									break;
								}
								fos.write(bs, 0, len);
							}
							fos.flush();
							is.close();
							fos.close();
							this.dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if(bo){
							JOptionPane.showMessageDialog(this, "添加成功");
									ui.indate();
						}else{
							JOptionPane.showMessageDialog(this, "电影类型添加失败");
						}
					}
					
				}else{
					JOptionPane.showMessageDialog(this, "添加失败");
				}
			}else{
				JOptionPane.showMessageDialog(this, "请将信息添加完整！");
			}
			
		}
		if(e.getSource()==b2){
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t7.setText("");
			//t8.setText("");
		}
		if(e.getSource()==b3){
			JFileChooser jf=new JFileChooser("D:\\");
			FileNameExtensionFilter fnef=new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
			jf.setFileFilter(fnef);
			int t=jf.showOpenDialog(this);
			if(t==JFileChooser.OPEN_DIALOG){
				String pt=jf.getSelectedFile().getPath();
				ImageIcon ii=new ImageIcon(new ImageIcon(pt).getImage().getScaledInstance(275, 400,Image.SCALE_DEFAULT));
				l0.setText("");
				l0.setIcon(ii);
				pic=pt;
			}
		}
		
	}
	
	
}
