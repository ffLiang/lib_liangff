package com.yunlong.Cinema.vo;

import java.util.List;

public class Category {
	private int categoryId;
	private String categoryName;
	private List<Film> film;
	public Category(){
		
	}
	/**
	 * ������
	 * @param categoryId ������
	 * @param categoryName ������
	 */
	public Category(int categoryId,String categoryName){
		this.categoryId=categoryId;
		this.categoryName=categoryName;
	}
	/**
	 * ������
	 * @param categoryId ������
	 * @param categoryName ������
	 * @param film ��Ӧ��Ӱ
	 */
	public Category(int categoryId,String categoryName, List<Film> film){
		this.categoryId=categoryId;
		this.categoryName=categoryName;
		this.film=film;
	}
	public List<Film> getFilm() {
		return film;
	}
	public void setFilm(List<Film> film) {
		this.film = film;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
