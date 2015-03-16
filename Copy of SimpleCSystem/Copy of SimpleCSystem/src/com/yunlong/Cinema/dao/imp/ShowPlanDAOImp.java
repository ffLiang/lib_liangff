package com.yunlong.Cinema.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.dao.ShowPlanDAO;
import com.yunlong.Cinema.util.ConnClose;
import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.ShowPlan;

public class ShowPlanDAOImp implements ShowPlanDAO {

	@Override
	public ShowPlan spIdlist(int spId) {
		ShowPlan sp = null;
		Connection conn=ConnClose.connection();
		String sql="select spId,filmId,placeId,showTime,endTime,price from showPlan where spId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, spId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				sp=new ShowPlan(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),  rs.getString(5),rs.getInt(6));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return sp;
	}

	@Override
	public List<Film> Timelist() {
		List<Film> lt=new ArrayList<Film>();
		Connection conn=ConnClose.connection();
		String sql="select filmId, filmName, director, protagonist, timeLength, region, showTimes, picture, state from film where filmId in(select filmId from showplan where endTime>getdate())";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Film sp=new Film(rs.getInt("filmId"),rs.getString("filmName"),rs.getString("director"),rs.getString("protagonist"),rs.getString("timeLength"),rs.getString("region"),rs.getString("showTimes"),rs.getString("picture"),rs.getInt("state"));
				//sp.setShowplan();
				lt.add(sp);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return lt;
	}

	@Override
	public List<ShowPlan> fshowplan(int filmId) {
		List<ShowPlan> lt=new ArrayList<ShowPlan>();
		Connection conn=ConnClose.connection();
		String sql="select spId, filmId, s.placeId as placeId, showTime, endTime, pirce, r.* from showplan s,place r where s.placeid=r.placeid and endTime>getdate() and  filmId=?";
	
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ShowPlan sp=new ShowPlan(rs.getInt("spId"), rs.getInt("filmId"), rs.getInt("placeId"),rs.getString("showTime"), rs.getString("endTime"),rs.getInt("price"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return lt;
	}

	@Override
	public boolean adshowplan(ShowPlan showplan) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="insert into showPlan(filmId,placeId,showTime,endTime,price) values(?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, showplan.getFilmId());
			ps.setInt(2, showplan.getPlaceId());
			ps.setString(3, showplan.getShowTime());
			ps.setString(4, showplan.getEndTime());
			ps.setInt(5, showplan.getprice());
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
	public Vector<Vector<Object>> selectSP(String startTime, String endTime) {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select spId,filmName,placeName,showTime,endTime,s.price from showPlan s,Film f,place r where s.filmId=f.filmId and s.placeId=r.placeId and showTime>? and showTime<? ";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, startTime);
			ps.setString(2, endTime);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getInt(6));
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

	@Override
	public Vector<Vector<Object>> selectSPAll() {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select spId,filmName,placeName,location,showTime,endTime,s.price from showPlan s,Film f,place r where s.filmId=f.filmId and s.placeId=r.placeId and endTime>getdate()";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getInt(7));
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

	@Override
	public boolean editSP(ShowPlan showplan) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="update showPlan set filmId=?,placeId=?,showTime=?,endTime=?,price=?  where spId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, showplan.getFilmId());
			ps.setInt(2, showplan.getPlaceId());
			ps.setString(3, showplan.getShowTime());
			ps.setString(4, showplan.getEndTime());
			ps.setInt(5, showplan.getprice());
			ps.setInt(6, showplan.getSpId());
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
	public boolean upSP(int filmId) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="update Film set state=? where filmId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, filmId);
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
	public boolean downSP(int filmId) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="update Film  set state=? where filmId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, 2);
			ps.setInt(2, filmId);
			m=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return m>0;
	}
	public Vector<String> colname(){
		Vector<String> v=new Vector<String>();
		v.add("计划编号");
		v.add("电影名称");
		v.add("电影院");
		v.add("影厅");
		v.add("放映时间");
		v.add("结束时间");
		v.add("价格");
		return v;
	}

	@Override
	public Vector<Vector<Object>> allshowplan() {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select spId,filmName,placeName,r.location,showTime,endTime,s.price from showPlan s,Film f,place r where s.filmId=f.filmId and s.placeId=r.placeId ";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getInt(7));
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

	@Override
	public List<String> selecshowpw(String starTime, String endTime, int placeId) {
		List<String> li=new ArrayList<String>();
		Connection conn=ConnClose.connection();
		String sql="select spId from showPlan where ((showTime>=? and showTime<=? or endTime<=? and endTime>=?)  or (showTime<? and endTime>?)) and placeId=?";
		//String sql="select spId from showPlan where (showTime>='2012-5-30 21:00:00' and showTime<='2012-5-30 23:00:00' or endTime<='2012-5-30 23:00:00' and endTime>='2012-5-30 21:00:00') and placeId=3";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, starTime);
			ps.setString(2, endTime);
			ps.setString(3, endTime);
			ps.setString(4, starTime);
			ps.setString(5, starTime);
			ps.setString(6, endTime);
			ps.setInt(7, placeId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				li.add(rs.getString(1));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return li;
	}

}
