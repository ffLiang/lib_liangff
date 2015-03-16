package com.yunlong.Cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yunlong.Cinema.util.ConnClose;
import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.FavorCategory;
import com.yunlong.Cinema.vo.FavorDirector;

public class FavorDirectorDAO {
	/**
	 * 查用户喜欢的所有的导演
	 * @return
	 */
	public List<String> list(int accountId){
		List<String> list=new ArrayList<String>();
		Connection conn=ConnClose.connection();
		String sql="select accountId,director from FavorDirector where accountId=?";
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
	/**
	 * 添加用户喜欢的导演
	 * @param category
	 * @return
	 */
	public boolean insertFDire(FavorDirector Favordirector){
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="insert into FavorDirector(accountId,director) values(?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Favordirector.getaccountId());
			ps.setString(2, Favordirector.getdirector());
			m=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return m>0;
	}
	/**
	 * 删除用户喜欢的导演
	 * @param category
	 * @return
	 */
	public boolean deleteFDire(FavorDirector Favordirector){
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="delete from FavorDirector where accountId=? and director=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Favordirector.getaccountId());
			ps.setString(2, Favordirector.getdirector());
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
