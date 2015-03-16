package com.yunlong.Cinema.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yunlong.Cinema.dao.CategoryDAO;
import com.yunlong.Cinema.util.ConnClose;
import com.yunlong.Cinema.vo.Category;

public class CategoryImp implements CategoryDAO {

	@Override
	public List<Category> list() {
		List<Category> list=new ArrayList<Category>();
		Connection conn=ConnClose.connection();
		String sql="select CategoryId,CategoryName from Category";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Category c=new Category(rs.getInt(1),rs.getString(2));
				list.add(c);
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
	public int cate(String cat) {
		int c=0;
		Connection conn=ConnClose.connection();
		String sql="select CategoryId from Category where CategoryName=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, cat);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
					c=rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return c;
	}

	@Override
	public boolean insertCate(Category category) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="insert into Category(categoryName) values(?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
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
	public boolean editCate(Category category) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="update Category set categoryName=? where categoryId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryName());
			ps.setInt(2, category.getCategoryId());
			m=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m>0;
	}

}
