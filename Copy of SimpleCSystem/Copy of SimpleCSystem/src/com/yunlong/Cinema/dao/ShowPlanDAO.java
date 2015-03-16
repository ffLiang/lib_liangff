package com.yunlong.Cinema.dao;


import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.SellLog;
import com.yunlong.Cinema.vo.ShowPlan;

public interface ShowPlanDAO {
	/**
	 * 通过计划编号查上映计划
	 * @param spId
	 * @return
	 */
	public ShowPlan spIdlist(int spId);
	/**
	 * 通过电影结束时间查计划
	 * @param endTime
	 * @return
	 */
	public List<Film> Timelist();
	/**
	 * 通过电影ID查正在和将要放映的计划
	 * @param filmId
	 * @return
	 */
	public List<ShowPlan> fshowplan(int filmId);
	/**
	 * 添加放映计划
	 * @param showplan
	 * @return
	 */
	public boolean adshowplan(ShowPlan showplan);
	/**
	 * 查找某段时间的放映计划
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Vector<Object>> selectSP(String startTime,String endTime);
	/**
	 * 目前有效的放映计划
	 * @return
	 */
	public Vector<Vector<Object>> selectSPAll();
	/**
	 * 修改放映计划
	 * @return
	 */
	public boolean editSP(ShowPlan showplan);
	/**
	 * 电影上线
	 * @return
	 */
	public boolean upSP(int filmId);
	/**
	 * 电影下线
	 * @return
	 */
	public boolean downSP(int filmId);
	/**
	 * 所有的放映计划
	 * @return
	 */
	public Vector<Vector<Object>> allshowplan();
	/**
	 * 查某时间段某电影院有无电影在放映
	 * @param starTime
	 * @param endTime
	 * @param placeId
	 * @return
	 */
	public List<String> selecshowpw(String starTime,String endTime,int placeId);
}
