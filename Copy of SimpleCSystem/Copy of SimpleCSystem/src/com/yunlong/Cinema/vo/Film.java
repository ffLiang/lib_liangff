package com.yunlong.Cinema.vo;

import java.util.List;


public class Film {
	private int filmId;
	private String filmName;
	private String director;
	private String protagonist;
	private String timeLength;
	private String region;
	private String showTime;
	private String picture;
	private int state;
	private List<Category> categorys;
	
	private List<ShowPlan> showplan;
	
	public Film(){
		
	}
	/**
	 * 电影资料
	 * @param filmId 电影编号
	 * @param filmName 电影名
	 * @param director 导演
	 * @param protagonist 主演
	 * @param timeLength 时长
	 * @param region 地区
	 * @param showTime 上映时间
	 * @param picture 海报
	 * @param state 状态
	 */
	public Film(int filmId,String filmName,String director,String protagonist,String timeLength,String region,String showTime,String picture,int state){
		this.filmId=filmId;
		this.filmName=filmName;
		this.director=director;
		this.protagonist=protagonist;
		this.timeLength=timeLength;
		this.region=region;
		this.showTime=showTime;
		this.picture=picture;
		this.state=state;
	}
	public List<Category> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getProtagonist() {
		return protagonist;
	}
	public void setProtagonist(String protagonist) {
		this.protagonist = protagonist;
	}
	public String getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(String timeLength) {
		this.timeLength = timeLength;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public List<ShowPlan> getShowplan() {
		return showplan ;
	}
	public void setShowplan(List<ShowPlan> showplan) {
		this.showplan = showplan;
	}
}
