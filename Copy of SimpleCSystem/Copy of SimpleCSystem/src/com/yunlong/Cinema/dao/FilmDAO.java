package com.yunlong.Cinema.dao;

import java.util.List;
import java.util.Vector;

import com.yunlong.Cinema.vo.Category;
import com.yunlong.Cinema.vo.Film;
import com.yunlong.Cinema.vo.ShowPlan;

public interface FilmDAO {
	/**
	 * ͨ����Ӱ��Ų��Ӱ����
	 * @param filmId
	 * @return
	 */
	public Film list(int filmId);
	/**
	 * ͨ��ID���ӳ�ƻ�
	 * @param id
	 * @return
	 */
	public List<ShowPlan> show(int id);
	/**
	 * ��������ڷźͽ�Ҫ�ŵĵ�Ӱ
	 * @return
	 */
	public List<Film> listCurrentDay();
	/**
	 * ͨ����Ӱ���ֲ��Ӱ���ϡ�����Film��
	 * @param filmName
	 * @return
	 */
	public Film list(String filmName);
	
	/**
	 * ͨ����Ӱ���ֲ��Ӱ���ϡ�����Film��
	 * @param filmName
	 * @return
	 */
	public Film list1(String filmName);
	
	/**
	 * ��Ӱ���
	 * @param film
	 * @return
	 */
	public boolean insert(Film film);
	/**
	 * �޸ĵ�Ӱ����
	 * @param film
	 * @return
	 */
	public boolean edit(Film film);
	/**
	 * �������ߵ�Ӱ
	 * @return
	 */
	public Vector<Vector<Object>> list();
	
	/**
	 * �û��������ߵ�Ӱ
	 * @return
	 */
	public Vector<Vector<Object>> list1(String fc, String fd, String fp);
	
	/**
	 * �������ߵ�Ӱ
	 * @return
	 */
	public Vector<Vector<Object>> list2();
	/**
	 * �������е�Ӱ
	 * @return
	 */
	public Vector<Vector<Object>> listAll();
	
	/**
	 * ���ݵ�Ӱ�����Ӱ���ϡ�����Vector��
	 * @param filmName
	 * @return
	 */
	Vector<Vector<Object>> filmlist(String filmName);
	
	
	/**
	 * ���ݵ�Ӱ�����Ӱ���ϡ�����Vector��
	 * @param filmName
	 * @return
	 */
	public Vector<Vector<Object>> filmlist1(String filmName);
	
	/**
	 * ���ݵ��ݲ��Ӱ���ϡ�����Vector��
	 * @param director
	 * @return
	 */
	public Vector<Vector<Object>> filmlist2(String director);
	
	/**
	 * ������Ա���Ӱ���ϡ�����Vector��
	 * @param protagonist
	 * @return
	 */
	public Vector<Vector<Object>> filmlist3(String protagonist);
	
	
	
	/**
	 * �����콫Ҫ�ŵĵ�Ӱ
	 * @return
	 */
	public List<Film> tomCurrentDay();
	/**
	 * ����콫Ҫ�ŵĵ�Ӱ
	 * @return
	 */
	public List<Film> atomCurrentDay();
	/**
	 * ͨ����ӰID���Ӱ����
	 * @return
	 */
	public List<Category> catelist(int filmId);
	/**
	 * ��ӵ�Ӱ��Ӧ������
	 * @param filmId
	 * @param categoryId
	 * @return
	 */
	public boolean insertcate(int filmId,int categoryId);
	/**
	 * ɾ����Ӱ��Ӧ������
	 * @param filmId
	 * @param categoryId
	 * @return
	 */
	public boolean deletecate(int filmId);
	
	
}
