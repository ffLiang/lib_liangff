package com.yunlong.Cinema.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import com.yunlong.Cinema.dao.AccountADO;
import com.yunlong.Cinema.util.ConnClose;
import com.yunlong.Cinema.vo.Account;

public class AccountADOImp implements AccountADO{
	
	@Override
	public Account enter(Account account) {
		Account ac=null;
		Connection conn=ConnClose.connection();
		String sql="select accountId,userName,password,state,roles,accountName from Account where accountName=? and password=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, account.getAccountName());
			ps.setString(2, account.getPassWord());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ac=new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getString(6));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return ac;
	}

	@Override
	public boolean insert(Account account) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="insert into Account(userName,passWord,roles,state,accountName) values(?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, account.getUserName());
			ps.setString(2, account.getPassWord());
			ps.setInt(3, account.getRoles());
			ps.setInt(4, account.getState());
			ps.setString(5, account.getAccountName());
			m=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return m>0;
	}

	@Override
	public boolean edit(Account account) {
		int m=0;
		Connection conn=ConnClose.connection();
		 String sql="update Account set userName=?,password=?, roles=?,state=?,accountName=? where AccountId=?";
		 try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,account.getUserName());
			ps.setString(2,account.getPassWord());
			ps.setInt(3, account.getRoles());
			ps.setInt(4, account.getState());
			ps.setString(5,account.getAccountName());
			ps.setInt(6, account.getAccountId());
			m=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return m>0;
	}

	@Override
	public boolean delete(int id) {
		int m=0;
		Connection conn=ConnClose.connection();
		 String sql="delete Account where AccountId=?";
		 try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			m=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return m>0;
	}
	public Vector<Vector<Object>> aselect() {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select accountId,userName,password,state,roles,accountName from Account";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(accountstate(rs.getInt(4)));
				v.add(roles(rs.getInt(5)));
				v.add(rs.getString(6));
				vv.add(v);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return vv;
	}
	public String accountstate(int state){
		String s=null;
		if(state==1){
			s="可用";
		}
		if(state==2){
			s="禁用";
		}
		return s;
	}
	public String roles(int roles){
		String s=null;
		if(roles==1){
			s="用户";
		}
		if(roles==2){
			s="管理员";
		}
		if(roles==3){
			s="超级管理员";
		}
		return s;
	}
	public Vector<String> colAccount(){
		Vector<String> v=new Vector<String>();
		v.add("用户编号");
		v.add("用户姓名");
		v.add("密码");
		v.add("状态");
		v.add("权限");
		v.add("账号");
		return v;
	}

	@Override
	public Account idSelect(int id) {
		Account a=null;
		Connection conn=ConnClose.connection();
		String sql="select accountId,userName,password,state,roles,accountName from Account where accountId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				a=new Account(id, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getString(6));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return a;
	}

	@Override
	public boolean editNP(Account account) {
		int m=0;
		Connection conn=ConnClose.connection();
		 String sql="update Account set userName=?,roles=?,state=?,accountName=? where AccountId=?";
		 try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,account.getUserName());
			//ps.setString(2,account.getPassWord());
			ps.setInt(2, account.getRoles());
			ps.setInt(3, account.getState());
			ps.setString(4,account.getAccountName());
			ps.setInt(5, account.getAccountId());
			m=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return m>0;
	}

	@Override
	public Vector<String> aName() {
		Vector<String> v=new Vector<String>();
		Connection conn=ConnClose.connection();
		String sql="select userName from Account";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				v.add(rs.getString(1));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return v;
	}

	@Override
	public boolean epassword(int id,String wp) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="update Account set password=? where AccountId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, wp);
			ps.setInt(2, id);
			m=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return m>0;
	}
}
