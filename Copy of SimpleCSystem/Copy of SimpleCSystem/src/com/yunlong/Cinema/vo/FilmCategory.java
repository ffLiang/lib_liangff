package com.yunlong.Cinema.vo;

import java.util.List;

public class FilmCategory {
	private int filmId;
	private int typeId;
	public FilmCategory(){
		
	}
	/**
	 * ����
	 * @param filmId ��Ӱ���
	 * @param typeId ������
	 */
	public FilmCategory(int filmId,int typeId){
		this.filmId=filmId;
		this.typeId=typeId;
	}
	
	public int getfilmId() {
		return filmId;
	}
	public void setfilmId(int filmId) {
		this.filmId = filmId;
	}
	public int gettypeId() {
		return typeId;
	}
	public void settypeId(int typeId) {
		this.typeId = typeId;
	}
}