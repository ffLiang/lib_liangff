package com.yunlong.Cinema.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnClose {
	private final static String url="jdbc:sqlserver://localhost:1433;databasename=CinemaSystem";
	private final static String user="sa";
	private final static String password="123456";
	private ConnClose(){
		
	}
	public static Connection connection(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println("连接失败");
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn){	
		try {
			if(conn!=null&&conn.isClosed()){
			conn.close();	
			}	
		} catch (SQLException e) {
			System.out.print("数据库关闭失败");
			e.printStackTrace();
		}
	}
}
