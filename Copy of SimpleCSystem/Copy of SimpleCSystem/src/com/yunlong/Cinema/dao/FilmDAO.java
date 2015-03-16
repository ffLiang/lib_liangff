package com.yunlong.Cinema.dao;

import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.ShowPlan;

public interface FilmDAO {
	/**
	 * 通过电影编号查电影资料
	 * @param filmId
	 * @return
	 */
	public Film list(int filmId);
	/**
	 * 通过ID查放映计划
	 * @param id
	 * @return
	 */
	public List<ShowPlan> show(int id);
	/**
	 * 查今天正在放和将要放的电影
	 * @return
	 */
	public List<Film> listCurrentDay();
	/**
	 * 通过电影名字查电影资料【返回Film】
	 * @param filmName
	 * @return
	 */
	public Film list(String filmName);
	
	/**
	 * 通过电影名字查电影资料【返回Film】
	 * @param filmName
	 * @return
	 */
	public Film list1(String filmName);
	
	/**
	 * 电影入库
	 * @param film
	 * @return
	 */
	public boolean insert(Film film);
	/**
	 * 修改电影资料
	 * @param film
	 * @return
	 */
	public boolean edit(Film film);
	/**
	 * 查找上线电影
	 * @return
	 */
	public Vector<Vector<Object>> list();
	
	/**
	 * 用户查找上线电影
	 * @return
	 */
	public Vector<Vector<Object>> list1(String fc, String fd, String fp);
	
	/**
	 * 查找上线电影
	 * @return
	 */
	public Vector<Vector<Object>> list2();
	/**
	 * 查找所有电影
	 * @return
	 */
	public Vector<Vector<Object>> listAll();
	
	/**
	 * 根据电影名查电影资料【返回Vector】
	 * @param filmName
	 * @return
	 */
	Vector<Vector<Object>> filmlist(String filmName);
	
	
	/**
	 * 根据电影名查电影资料【返回Vector】
	 * @param filmName
	 * @return
	 */
	public Vector<Vector<Object>> filmlist1(String filmName);
	
	/**
	 * 根据导演查电影资料【返回Vector】
	 * @param director
	 * @return
	 */
	public Vector<Vector<Object>> filmlist2(String director);
	
	/**
	 * 根据演员查电影资料【返回Vector】
	 * @param protagonist
	 * @return
	 */
	public Vector<Vector<Object>> filmlist3(String protagonist);
	
	
	
	/**
	 * 查明天将要放的电影
	 * @return
	 */
	public List<Film> tomCurrentDay();
	/**
	 * 查后天将要放的电影
	 * @return
	 */
	public List<Film> atomCurrentDay();
	/**
	 * 通过电影ID查电影种类
	 * @return
	 */
	public List<Category> catelist(int filmId);
	/**
	 * 添加电影相应的类型
	 * @param filmId
	 * @param categoryId
	 * @return
	 */
	public boolean insertcate(int filmId,int categoryId);
	/**
	 * 删除电影相应的类型
	 * @param filmId
	 * @param categoryId
	 * @return
	 */
	public boolean deletecate(int filmId);
	
	
}
