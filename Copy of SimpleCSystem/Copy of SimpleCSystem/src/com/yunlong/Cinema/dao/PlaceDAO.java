package com.yunlong.Cinema.dao;

import java.util.Vector;

import com.yunlong.Cinema.vo.Place;

public interface PlaceDAO {
	/**
	 * ͨ����ӰԺId��ѯ
	 * @param PlaceId
	 * @return
	 */
	public Place Place(int PlaceId);
	/**
	 * ��ӵ�ӰԺ
	 * @param Place
	 * @return
	 */
	public boolean insertPlace(Place Place);
	/**
	 * �޸ĵ�ӰԺ����
	 * @param Place
	 * @return
	 */
	public boolean editPlace(Place Place);
	/**
	 * ɾ����ӰԺ����
	 * @param PlaceId
	 * @return
	 */
	public boolean deletePlace(int PlaceId);
	/**
	 * �����еĵ�ӰԺ����
	 * @return
	 */
	public Vector<Vector<Object>> Placevector();
	/**
	 * �����е�ӰԺ������
	 * @return
	 */
	public Vector<String> PlaceName();
	
	/**
	 * ��ĳ����ӰԺ��Ӱ��������
	 * @return
	 */
	public Vector<String> location(String placeName);
	
	/**
	 * ͨ����ӰԺ���ֺ�Ӱ�����ӰԺ��Ӱ��Id
	 * @param Placename
	 * @return
	 */
	public int Placen(String Placename, String location);
	
	
	/**
	 * ͨ����ӰԺId��ѯ��ӰԺ��
	 * @param PlaceId
	 * @return
	 */
	public String getPlaceNamek(int PlaceId);
	
	/**
	 * ͨ����ӰԺId��ѯ��ӰԺ��
	 * @param PlaceId
	 * @return
	 */
	public String getLocationk(int PlaceId);

}