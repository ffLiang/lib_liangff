package com.yunlong.Cinema.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.dao.CategoryDAO;
import com.yunlong.Cinema.dao.dateDAO;
import com.yunlong.Cinema.util.ConnClose;
import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.Date;

public class DateDAOImp implements dateDAO {

	public List<String> list(int accountId) {
		List<String> list=new ArrayList<String>();
		Connection conn=ConnClose.connection();
		String sql="select accountId,date from Date where accountId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, accountId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString(2));
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

	public List<String> note(int accountId, String date) {
		List<String> note=new ArrayList<String>();
		Connection conn=ConnClose.connection();
		String sql="select accountId,date,note from Date where accountId=? and date=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, accountId);
			ps.setString(2, date);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
					note.add(rs.getString(3));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return note;
	}

	public boolean insertNote(Date date) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="insert into Date(accountId,date,note) values(?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, date.getaccountId());
			ps.setString(2, date.getdate());
			ps.setString(3, date.getnote());
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