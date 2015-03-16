package com.yunlong.Cinema.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.yunlong.Cinema.dao.PlaceDAO;
import com.yunlong.Cinema.util.ConnClose;
import com.yunlong.Cinema.vo.Place;

public class PlaceDAOImp implements PlaceDAO{

	@Override
	public Place Place(int PlaceId) {
		Place ro=null;
		Connection conn=ConnClose.connection();
		String sql="select placeId,placeName,seatNumber,rows,columns,location from Place where placeId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, PlaceId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ro=new Place(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return ro;
	}
	
	@Override
	public String getPlaceNamek(int PlaceId) {
		String ro=null;
		Connection conn=ConnClose.connection();
		String sql="select placeId,placeName,seatNumber,rows,columns,location from Place where placeId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, PlaceId);
			ResultSet rs=ps.executeQuery();
			ro=rs.getString(2);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return ro;
	}
	
	@Override
	public String getLocationk(int PlaceId) {
		String ro=null;
		Connection conn=ConnClose.connection();
		String sql="select placeId,placeName,seatNumber,rows,columns,location from Place where placeId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, PlaceId);
			ResultSet rs=ps.executeQuery();
			ro=rs.getString(6);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return ro;
	}

	@Override
	public boolean insertPlace(Place Place) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="insert into Place(PlaceName,seatNumber,rows,columns,location) values(?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, Place.getPlaceName());
			ps.setInt(2, Place.getSeatNumber());
			ps.setInt(3, Place.getRows());
			ps.setInt(4, Place.getColumns());
			ps.setString(5, Place.getLocation());
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
	public boolean editPlace(Place Place) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="update Place set PlaceName=?,seatNumber=?,rows=?,columns=?,location=? where PlaceId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, Place.getPlaceName());
			ps.setInt(2, Place.getSeatNumber());
			ps.setInt(3, Place.getRows());
			ps.setInt(4, Place.getColumns());
			ps.setString(5, Place.getLocation());
			ps.setInt(6, Place.getPlaceId());
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
	public boolean deletePlace(int PlaceId) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="delete from Place where PlaceId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, PlaceId);
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
	public Vector<Vector<Object>> Placevector() {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select PlaceId,PlaceName,seatNumber,rows,columns,location from Place";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(6));
				v.add(rs.getInt(3));
				v.add(rs.getInt(4));
				v.add(rs.getInt(5));
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
	public Vector<String> colRName(){
		Vector<String> v=new Vector<String>();
		v.add("电影院编号");
		v.add("电影院名称");
		v.add("影厅");
		v.add("总座位数");
		v.add("座位排数");
		v.add("座位列数");
		return v;
	}

	@Override
	public Vector<String> PlaceName() {
		Vector<String> v=new Vector<String>();
		Connection conn=ConnClose.connection();
		String sql="select distinct PlaceName from Place";
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
	public Vector<String> location(String placeName) {
		Vector<String> v=new Vector<String>();
		Connection conn=ConnClose.connection();
		String sql="select location from Place where placeName=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, placeName);
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
	public int Placen(String Placename, String location) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="select PlaceId from Place where placeName=? and location=? ";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, Placename);
			ps.setString(2, location);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				m=rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return m;
	}
}