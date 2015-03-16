package com.yunlong.Cinema.vo;

import java.util.List;

public class FavorCategory {
	private int accountId;
	private String category;
	private List<String> categorys;
	public FavorCategory(int accountId, String category){
		this.accountId=accountId;
		this.category=category;
	}
	public List<String> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<String> categorys) {
		this.categorys = categorys;
	}
	public int getaccountId(){
		return accountId;
	}
	public void setaccountId(int accountId){
		this.accountId=accountId;
	}
	public String getcategory(){
		return category;
	}
	public void setcategory(String category){
		this.category=category;
	}
}
