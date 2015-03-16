package com.yunlong.Cinema.vo;

import java.util.List;

public class FavorDirector {
	private int accountId;
	private String director;
	private List<String> directors;
	public FavorDirector(int accountId, String director){
		this.accountId=accountId;
		this.director=director;
	}
	public List<String> getDirectors() {
		return directors;
	}
	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}
	public int getaccountId(){
		return accountId;
	}
	public void setaccountId(int accountId){
		this.accountId=accountId;
	}
	public String getdirector(){
		return director;
	}
	public void setdirector(String director){
		this.director=director;
	}
}