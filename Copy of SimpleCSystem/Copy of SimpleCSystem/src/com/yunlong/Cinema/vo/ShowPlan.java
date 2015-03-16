package com.yunlong.Cinema.vo;

public class ShowPlan {
	private int spId;
	private Film film;
	private Place Place;
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Place getPlace() {
		return Place;
	}
	
	public void setPlace(Place Place) {
		this.Place = Place;
	}
	
	private int filmId;
	private int PlaceId;
	private String showTime;
	private String endTime;
	private int price;
	
	/**
	 * 上映计划
	 * @param spId 计划编号
	 * @param filmId 电影编号
	 * @param PlaceId 电影院编号
	 * @param showTime 放映时间
	 * @param endTime 结束时间
	 * @param price 价格
	 */
	public ShowPlan(int spId,int filmId,int PlaceId,String showTime,String endTime,int price){
		this.spId=spId;
		this.filmId=filmId;
		this.PlaceId=PlaceId;
		this.showTime=showTime;
		this.endTime=endTime;
		this.price=price;
	}
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public int getPlaceId() {
		return PlaceId;
	}
	public void setPlaceId(int PlaceId) {
		this.PlaceId = PlaceId;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public int getprice() {
		return price;
	}
	public void setprice(int price) {
		this.price = price;
	}
	
}
