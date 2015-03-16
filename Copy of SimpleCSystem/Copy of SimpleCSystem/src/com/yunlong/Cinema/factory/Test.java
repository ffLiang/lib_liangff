package com.yunlong.Cinema.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.yunlong.Cinema.util.ConnClose;

public class Test {
	static int th;
	static String f;
	public Test(){
		Vector<Object> ft = selecshowp("2012-5-30 21:00:00.0", "2012-5-30 23:00:00.0",3 );
		//boolean th=ft.size()==1;
		 th=ft.size();
		 f=ft.get(0).toString();
	}
	public Vector<Object> selecshowp(String starTime, String endTime, int placeId) {
		Vector<Object> v=new Vector<Object>();
		Connection conn=ConnClose.connection();
		String sql="select spId from showPlan where (showTime>? and showTime<? or endTime<? and endTime>?) and placeId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, starTime);
			ps.setString(2, endTime);
			ps.setString(3, endTime);
			ps.setString(4, starTime);
			ps.setInt(5, placeId);
			System.err.println("RRRR");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				v.add(rs.getInt(1));
			    while(rs.next()){
				    v.add(rs.getInt(1));
			    }
			}
			else
				v.add("RRRR");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		
		return v;}
	public static void main(String[] args) {
		Test t=new Test();
		System.out.println(t.th);
		System.out.println(t.f);
	}
}
