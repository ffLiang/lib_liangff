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

public class favorCategoryDAO {
	/**
	 * 查用户喜欢的所有的电影类型
	 * @return
	 */
	public  List<String> list(int accountId){
		List<String> list=new ArrayList<String>();
		Connection conn=ConnClose.connection();
		String sql="select accountId,category from FavorCategory where accountId=?";
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
	 * 添加用户喜欢的电影种类
	 * @param category
	 * @return
	 */
	public boolean insertFCate(FavorCategory FavorCategory){
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="insert into FavorCategory(accountId,category) values(?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, FavorCategory.getaccountId());
			ps.setString(2, FavorCategory.getcategory());
			System.out.print(ps);
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
	 * 删除用户喜欢的电影种类
	 * @param category
	 * @return
	 */
	public boolean deleteFCate(FavorCategory FavorCategory){
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="delete from FavorCategory where accountId=? and category=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, FavorCategory.getaccountId());
			ps.setString(2, FavorCategory.getcategory());
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
