package com.yunlong.Cinema.dao;

import java.util.Vector;

import com.yunlong.Cinema.vo.Place;

public interface PlaceDAO {
	/**
	 * 通过电影院Id查询
	 * @param PlaceId
	 * @return
	 */
	public Place Place(int PlaceId);
	/**
	 * 添加电影院
	 * @param Place
	 * @return
	 */
	public boolean insertPlace(Place Place);
	/**
	 * 修改电影院资料
	 * @param Place
	 * @return
	 */
	public boolean editPlace(Place Place);
	/**
	 * 删除电影院资料
	 * @param PlaceId
	 * @return
	 */
	public boolean deletePlace(int PlaceId);
	/**
	 * 查所有的电影院资料
	 * @return
	 */
	public Vector<Vector<Object>> Placevector();
	/**
	 * 查所有电影院的名称
	 * @return
	 */
	public Vector<String> PlaceName();
	
	/**
	 * 查某个电影院的影厅的名称
	 * @return
	 */
	public Vector<String> location(String placeName);
	
	/**
	 * 通过电影院名字和影厅查电影院电影厅Id
	 * @param Placename
	 * @return
	 */
	public int Placen(String Placename, String location);
	
	
	/**
	 * 通过电影院Id查询电影院名
	 * @param PlaceId
	 * @return
	 */
	public String getPlaceNamek(int PlaceId);
	
	/**
	 * 通过电影院Id查询电影院名
	 * @param PlaceId
	 * @return
	 */
	public String getLocationk(int PlaceId);

}