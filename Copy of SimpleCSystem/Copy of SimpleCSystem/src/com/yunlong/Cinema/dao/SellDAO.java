package com.yunlong.Cinema.dao;

import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.vo.SellLog;

public interface SellDAO {
	/**
	 * 通过计划编号查销售
	 * @param spId
	 * @return
	 */
	public List<SellLog> list(int spId);
	/**
	 * 通过用户ID查购票情况
	 * @param accountId
	 * @return
	 */
	public List<SellLog> alist(int accountId);
	/**
	 * 添加售出电影票
	 * @param sellLog
	 */
	public Boolean sell(SellLog sellLog);
	/**
	 * 统计某时间段的售票情况
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Object> State(String startTime,String endTime);
	/**
	 * 统计某时间段的某电影院的售票情况
	 * @param placeId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Object> placeStat(String placeName,String startTime,String endTime);
	/**
	 * 统计某时间段的某部电影的售票情况
	 * @param filmname
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Object> filmStat(String filmname,String startTime,String endTime);
	/**
	 * 统计某时间段的某个用户的购票情况
	 * @param accountId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Object> accountStat(String userName,String startTime,String endTime);
}
