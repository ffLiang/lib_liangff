package com.yunlong.Cinema.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.dao.SellDAO;
import com.yunlong.Cinema.util.ConnClose;
import com.yunlong.Cinema.vo.SellLog;

public class SellDAOImp implements SellDAO {

	@Override
	public List<SellLog> list(int spId) {
		List<SellLog> list=new ArrayList<SellLog>();
		Connection conn=ConnClose.connection();
		String sql="select slId,spId,rows,columns,price,accountId from sellLog where spId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, spId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SellLog sl=new SellLog(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				list.add(sl);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		ConnClose.close(conn);
		return list;
	}

	@Override
	public List<SellLog> alist(int accountId) {
		List<SellLog> list=new ArrayList<SellLog>();
		Connection conn=ConnClose.connection();
		String sql="select slId,spId,rows,columns,price,accountId from sellLog where accountId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, accountId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SellLog sl=new SellLog(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				list.add(sl);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return list;
	}

	@Override
	public Boolean sell(SellLog sellLog) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="insert into sellLog(spId,rows,columns,price,accountId) values(?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, sellLog.getSpId());
			ps.setInt(2, sellLog.getRows());
			ps.setInt(3, sellLog.getColumns());
			ps.setInt(4, sellLog.getPrice());
			ps.setInt(5, sellLog.getAccountId());
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
	public Vector<Object> State(String startTime, String endTime) {
		Vector<Object> v=new Vector<Object>();
		Connection conn=ConnClose.connection();
		String sql="select count(1),sum(s.price) from sellLog s,showPlan p where s.spId=p.spId and s.spId in(select spId from showPlan where showTime>? and showTime<?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,startTime);
			ps.setString(2, endTime);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				v.add(rs.getInt(1));
				v.add(rs.getInt(2));
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
	public Vector<Object> placeStat(String placeName, String startTime, String endTime) {
		Vector<Object> v=new Vector<Object>();
		Connection conn=ConnClose.connection();
		String sql="select count(1),sum(s.price) from sellLog s,showPlan p,place r where s.spId=p.spId and p.placeId=r.placeId and s.spId in(select spId from showPlan where showTime>? and showTime<? and placeId in(select placeId from place where placeName=?))";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,startTime);
			ps.setString(2, endTime);
			ps.setString(3, placeName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				v.add(rs.getInt(1));
				v.add(rs.getInt(2));
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
	public Vector<Object> filmStat(String filmname, String startTime,
			String endTime) {
		Vector<Object> v=new Vector<Object>();
		Connection conn=ConnClose.connection();
		String sql="select count(1),sum(s.price) from sellLog s,showPlan p,Film f where s.spId=p.spId and p.filmId=f.filmId and s.spId in(select spId from showPlan where showTime>? and showTime<? and filmId in(select filmId from Film where filmName=?))";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,startTime);
			ps.setString(2, endTime);
			ps.setString(3, filmname);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				v.add(rs.getInt(1));
				v.add(rs.getInt(2));
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
	public Vector<Object> accountStat(String userName, String startTime,
			String endTime) {
		Vector<Object> v=new Vector<Object>();
		Connection conn=ConnClose.connection();
		String sql="select count(1),sum(s.price) from sellLog s,showPlan p,Account a where s.spId=p.spId and s.spId in(select spId from showPlan where showTime>? and showTime<?) and s.accountId=a.accountId and s.accountId in(select accountId from Account where userName=?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,startTime);
			ps.setString(2, endTime);
			ps.setString(3, userName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				v.add(rs.getInt(1));
				v.add(rs.getInt(2));
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

}
