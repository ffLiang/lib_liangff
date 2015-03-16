package com.yunlong.Cinema.dao;


import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.SellLog;
import com.yunlong.Cinema.vo.ShowPlan;

public interface ShowPlanDAO {
	/**
	 * ͨ���ƻ���Ų���ӳ�ƻ�
	 * @param spId
	 * @return
	 */
	public ShowPlan spIdlist(int spId);
	/**
	 * ͨ����Ӱ����ʱ���ƻ�
	 * @param endTime
	 * @return
	 */
	public List<Film> Timelist();
	/**
	 * ͨ����ӰID�����ںͽ�Ҫ��ӳ�ļƻ�
	 * @param filmId
	 * @return
	 */
	public List<ShowPlan> fshowplan(int filmId);
	/**
	 * ��ӷ�ӳ�ƻ�
	 * @param showplan
	 * @return
	 */
	public boolean adshowplan(ShowPlan showplan);
	/**
	 * ����ĳ��ʱ��ķ�ӳ�ƻ�
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Vector<Vector<Object>> selectSP(String startTime,String endTime);
	/**
	 * Ŀǰ��Ч�ķ�ӳ�ƻ�
	 * @return
	 */
	public Vector<Vector<Object>> selectSPAll();
	/**
	 * �޸ķ�ӳ�ƻ�
	 * @return
	 */
	public boolean editSP(ShowPlan showplan);
	/**
	 * ��Ӱ����
	 * @return
	 */
	public boolean upSP(int filmId);
	/**
	 * ��Ӱ����
	 * @return
	 */
	public boolean downSP(int filmId);
	/**
	 * ���еķ�ӳ�ƻ�
	 * @return
	 */
	public Vector<Vector<Object>> allshowplan();
	/**
	 * ��ĳʱ���ĳ��ӰԺ���޵�Ӱ�ڷ�ӳ
	 * @param starTime
	 * @param endTime
	 * @param placeId
	 * @return
	 */
	public List<String> selecshowpw(String starTime,String endTime,int placeId);
}
