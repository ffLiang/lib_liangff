package com.yunlong.Cinema.vo;

import java.util.List;

public class Date {
	private int accountId;
	private String date;
	private String note;
	public Date(){
		
	}
	/**
	 * 观影日志
	 * @param accountId 帐号ID
	 * @param date 日期
	 * @param note 记录
	 */
	public Date(int accountId, String date, String note){
		this.accountId=accountId;
		this.date=date;
		this.note=note;
	}
	
	public int getaccountId() {
		return accountId;
	}
	public void setaccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getdate() {
		return date;
	}
	public void setdate(String date) {
		this.date = date;
	}
	public String getnote() {
		return note;
	}
	public void setnote(String note) {
		this.note = note;
	}
}
