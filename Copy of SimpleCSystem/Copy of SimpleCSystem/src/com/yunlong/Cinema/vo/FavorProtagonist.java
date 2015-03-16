package com.yunlong.Cinema.vo;

import java.util.List;

public class FavorProtagonist {
	private int accountId;
	private String protagonist;
	private List<String> protagonists;
	public FavorProtagonist(int accountId, String protagonist){
		this.accountId=accountId;
		this.protagonist=protagonist;
	}
	public List<String> getprotagonists() {
		return protagonists;
	}
	public void setprotagonists(List<String> protagonists) {
		this.protagonists = protagonists;
	}
	public int getaccountId(){
		return accountId;
	}
	public void setaccountId(int accountId){
		this.accountId=accountId;
	}
	public String getprotagonist(){
		return protagonist;
	}
	public void setprotagonist(String protagonist){
		this.protagonist=protagonist;
	}
}
