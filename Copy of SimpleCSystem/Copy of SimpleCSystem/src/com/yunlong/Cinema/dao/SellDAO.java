package com.yunlong.Cinema.dao;

import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.vo.SellLog;

public interface SellDAO {
	/**
	 * ͨ���ƻ���Ų�����
	 * @param spId
	 * @return
	 */
	public List<SellLog> list(int spId);
	/**
	 * ͨ���û�ID�鹺Ʊ���
	 * @param accountId
	 * @return
	 */
	public List<SellLog> alist(int accountId);
	/**
	 * ����۳���ӰƱ
	 * @param sellLog
	 */
	public Boolean sell(SellLog sellLog);
	/**
	 * ͳ��ĳʱ��ε���Ʊ���
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Object> State(String startTime,String endTime);
	/**
	 * ͳ��ĳʱ��ε�ĳ��ӰԺ����Ʊ���
	 * @param placeId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Object> placeStat(String placeName,String startTime,String endTime);
	/**
	 * ͳ��ĳʱ��ε�ĳ����Ӱ����Ʊ���
	 * @param filmname
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Object> filmStat(String filmname,String startTime,String endTime);
	/**
	 * ͳ��ĳʱ��ε�ĳ���û��Ĺ�Ʊ���
	 * @param accountId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Object> accountStat(String userName,String startTime,String endTime);
}
