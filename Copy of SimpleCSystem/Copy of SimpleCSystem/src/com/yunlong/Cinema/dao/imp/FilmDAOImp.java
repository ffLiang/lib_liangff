package com.yunlong.Cinema.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.dao.FilmDAO;
import com.yunlong.Cinema.util.ConnClose;
import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.Place;
import com.yunlong.Cinema.vo.ShowPlan;


public class FilmDAOImp implements FilmDAO {

	@Override
	public Film list(int filmId) {
		Film fl=null;
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where filmId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				fl=new Film(rs.getInt(1), rs.getString(2),  rs.getString(3),  rs.getString(4),  rs.getString(5),  rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return fl;
	}

	@Override
	public List<ShowPlan> show(int id) {
		List<ShowPlan> list=new ArrayList<ShowPlan>();
		String sql="select spId, filmId, s.placeId as placeId, showTime, endTime, price, r.* from showplan s,place r where s.placeid=r.placeid and endTime>getdate() and  filmId="+id;
		Connection conn=ConnClose.connection();
		try {
			Statement sta=conn.createStatement();
			ResultSet rs=sta.executeQuery(sql);
			while(rs.next()){
				ShowPlan sp=new ShowPlan(rs.getInt("spId"),rs.getInt("filmId"), rs.getInt("placeId"), rs.getString("showTime"),rs.getString("endTime"),rs.getInt("price"));
				sp.setPlace(new Place(0, rs.getString("placeName"), 0, 0, 0, ""));
				list.add(sp);
			}
			rs.close();
			sta.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return list;
	}

	@Override
	public List<Film> listCurrentDay() {
		List<Film> list=new ArrayList<Film>();
		String sql="select filmId, filmName, director, protagonist, timeLength, region, showTimes, picture, state from film where filmId in(select filmId from showplan where endTime>getdate() and showTime<dateadd(dd,datediff(dd,0,getdate()),1))";
		Connection conn=ConnClose.connection();
		try {
			Statement sta=conn.createStatement();
			ResultSet rs=sta.executeQuery(sql);
			while(rs.next()){
				Film film=new Film(rs.getInt("filmId"),rs.getString("filmName"),rs.getString("director"),rs.getString("protagonist"),rs.getString("timeLength"),rs.getString("region"),rs.getString("showTimes"),rs.getString("picture"),rs.getInt("state"));
				film.setShowplan(this.show(rs.getInt("filmId")));
				list.add(film);
				
			}
			rs.close();
			sta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return list;
	}

	@Override
	public Film list(String filmName) {
		Film fl=null;
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where filmName=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, filmName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				fl=new Film(rs.getInt(1), rs.getString(2),  rs.getString(3),  rs.getString(4),  rs.getString(5),  rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return fl;
	}

	
	@Override
	public Film list1(String filmName) {
		Film fl=null;
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where filmName=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, filmName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				fl=new Film(rs.getInt(1), rs.getString(2),  rs.getString(3),  rs.getString(4),  rs.getString(5),  rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return fl;
	}
	
	@Override
	public boolean insert(Film film) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="insert into Film(filmName,director,protagonist,timelength,region,showTimes,picture,state) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, film.getFilmName());
			ps.setString(2, film.getDirector());
			ps.setString(3, film.getProtagonist());
			ps.setString(4, film.getTimeLength());
			ps.setString(5, film.getRegion());
			ps.setString(6, film.getShowTime());
			ps.setString(7, film.getPicture());
			ps.setInt(8, film.getState());
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
	public boolean edit(Film film) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="update Film set filmName=?,director=?,protagonist=?,timelength=?,region=?,showTimes=?,picture=?  where filmId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, film.getFilmName());
			ps.setString(2, film.getDirector());
			ps.setString(3, film.getProtagonist());
			ps.setString(4, film.getTimeLength());
			ps.setString(5, film.getRegion());
			ps.setString(6, film.getShowTime());
			ps.setString(7, film.getPicture());
			ps.setInt(8, film.getFilmId());
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
	public Vector<Vector<Object>> list() {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where state=1";
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
				v.add(rs.getString(7));
				v.add(state(rs.getInt(9)));
				List<Category> ls=this.catelist(rs.getInt(1));
				String s="";
				for (Category category : ls) {
					s=s+category.getCategoryName()+"/";
				}
				v.add(s);
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
	public Vector<Vector<Object>> list1(String fc, String fd, String fp) {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where state=1 and filmId in(select filmId from favorCategory where category like ?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+fc+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				List<Category> ls=this.catelist(rs.getInt(1));
				String s="";
				for (Category category : ls) {
					s=s+category.getCategoryName()+"/";
				}
				v.add(s);
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
	public Vector<Vector<Object>> list2() {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where state=1";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				List<Category> ls=this.catelist(rs.getInt(1));
				String s="";
				for (Category category : ls) {
					s=s+category.getCategoryName()+"/";
				}
				v.add(s);
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
	public Vector<Vector<Object>> listAll() {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film";
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
				v.add(rs.getString(7));	
				v.add(state(rs.getInt(9)));
				List<Category> ls=this.catelist(rs.getInt(1));
				String s="";
				for (Category category : ls) {
					s=s+category.getCategoryName()+"/";
				}
				v.add(s);
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
	public String state(int state){
		String s=null;
		if(state==1){
			s="上线";
		}
		if(state==2){
			s="下线";
		}
		if(state==3){
			s="即将上线";
		}
		return s;
	}
	public Vector<String> namelist() {
		Vector<String> v=new Vector<String>();
		Connection conn=ConnClose.connection();
		String sql="select filmName from Film where state=1";
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
	public Vector<String> colname(){
		Vector<String> v=new Vector<String>();
		v.add("电影编号");
		v.add("电影名称");
		v.add("导演");
		v.add("主演");
		v.add("时长");
		v.add("地区");
		v.add("上映时间");
		v.add("状态");
		v.add("电影类型");
		return v;
	}

	@Override
	public Vector<Vector<Object>> filmlist(String filmName) {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where filmName=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, filmName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				
				v.add(state(rs.getInt(10)));
				List<Category> ls=this.catelist(rs.getInt(1));
				String s="";
				for (Category category : ls) {
					s=s+category.getCategoryName()+"/";
				}
				v.add(s);
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
	public Vector<Vector<Object>> filmlist1(String filmName) {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where filmName like ?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+filmName+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				List<Category> ls=this.catelist(rs.getInt(1));
				String s="";
				for (Category category : ls) {
					s=s+category.getCategoryName()+"/";
				}
				v.add(s);
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
	public Vector<Vector<Object>> filmlist2(String director) {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where director like ?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+director+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();

				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				List<Category> ls=this.catelist(rs.getInt(1));
				String s="";
				for (Category category : ls) {
					s=s+category.getCategoryName()+"/";
				}
				v.add(s);
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
	public Vector<Vector<Object>> filmlist3(String protagonist) {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where protagonist like ?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+protagonist+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();

				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				List<Category> ls=this.catelist(rs.getInt(1));
				String s="";
				for (Category category : ls) {
					s=s+category.getCategoryName()+"/";
				}
				v.add(s);
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
	public List<Film> tomCurrentDay() {
		List<Film> list=new ArrayList<Film>();
		String sql="select filmId, filmName, director, protagonist, timeLength, region, showTimes, picture, state from film where filmId in(select filmId from showplan where  showTime>dateadd(dd,datediff(dd,0,getdate()),1) and dateadd(dd,datediff(dd,0,getdate()),2)>showTime)";
		Connection conn=ConnClose.connection();
		try {
			Statement sta=conn.createStatement();
			ResultSet rs=sta.executeQuery(sql);
			while(rs.next()){
				Film film=new Film(rs.getInt("filmId"),rs.getString("filmName"),rs.getString("director"),rs.getString("protagonist"),rs.getString("timeLength"),rs.getString("region"),rs.getString("showTimes"),rs.getString("picture"),rs.getInt("state"));
				film.setShowplan(this.show(rs.getInt("filmId")));
				list.add(film);
				
			}
			rs.close();
			sta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return list;
	}

	@Override
	public List<Film> atomCurrentDay() {
		List<Film> list=new ArrayList<Film>();
		String sql="select filmId, filmName, director, protagonist, timeLength, region, showTimes, picture, state from film where filmId in(select filmId from showplan where  showTime>dateadd(dd,datediff(dd,0,getdate()),2) and dateadd(dd,datediff(dd,0,getdate()),3)>showTime)";
		Connection conn=ConnClose.connection();
		try {
			Statement sta=conn.createStatement();
			ResultSet rs=sta.executeQuery(sql);
			while(rs.next()){
				Film film=new Film(rs.getInt("filmId"),rs.getString("filmName"),rs.getString("director"),rs.getString("protagonist"),rs.getString("timeLength"),rs.getString("region"),rs.getString("showTimes"),rs.getString("picture"),rs.getInt("state"));
				film.setShowplan(this.show(rs.getInt("filmId")));
				list.add(film);
				
			}
			rs.close();
			sta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnClose.close(conn);
		return list;
	}
	@Override
	public List<Category> catelist(int filmId) {
		List<Category> list=new ArrayList<Category>();
		Connection conn=ConnClose.connection();
		String sql="select c.CategoryId,CategoryName from Category c,FilmCategory f where c.CategoryId=f.CategoryId and filmId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Category ca=new Category(rs.getInt(1),rs.getString(2));
				list.add(ca);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertcate(int filmId, int categoryId) {
	int m=0;
	Connection conn=ConnClose.connection();
	String sql="insert into FilmCategory(filmId,categoryId) values(?,?)";
	try {
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, filmId);
		ps.setInt(2, categoryId);
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
	public boolean deletecate(int filmId) {
		int m=0;
		Connection conn=ConnClose.connection();
		String sql="delete from FilmCategory where filmId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			m=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ConnClose.close(conn);
		}
		return m>0;
	}
	public Vector<Vector<Object>> FClist(String categoryName) {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmName,director,protagonist,timelength,region,state from Film where filmId in(select filmId from FilmCategory where categoryId=(select categoryId from Category where categoryName=?))";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, categoryName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(state(rs.getInt(6)));
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
	
	public Vector<Vector<Object>> FClist1(String categoryName) {
		Vector<Vector<Object>> vv=new Vector<Vector<Object>>();
		Connection conn=ConnClose.connection();
		String sql="select filmId,filmName,director,protagonist,timelength,region,showTimes,picture,state from Film where filmId in(select filmId from FilmCategory where categoryId=(select categoryId from Category where categoryName=?))";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, categoryName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vector<Object> v=new Vector<Object>();
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				List<Category> ls=this.catelist(rs.getInt(1));
				String s="";
				for (Category category : ls) {
					s=s+category.getCategoryName()+"/";
				}
				v.add(s);
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
	
	public Vector<String> colCategory(){
		Vector<String> v=new Vector<String>();
		v.add("电影名称");
		v.add("导演");
		v.add("主演");
		v.add("时长");
		v.add("地区");
		v.add("状态");
		return v;
	}
	public Vector<String> colCategory1(){
		Vector<String> v=new Vector<String>();
		v.add("电影名称");
		v.add("导演");
		v.add("主演");
		v.add("时长");
		v.add("地区");
		v.add("上映时间");
		v.add("电影类型");
		return v;
	}
}
