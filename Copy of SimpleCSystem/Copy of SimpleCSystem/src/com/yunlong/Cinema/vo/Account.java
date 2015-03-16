package com.yunlong.Cinema.vo;

public class Account {
	private int accountId;
	private String userName;
	private String passWord;
	private int state;
	private int roles;
	private String accountName;
	public Account(){
		
	}
	/**
	 * 账号资料
	 * @param accountId 账户编号
	 * @param userName 用户名
	 * @param passWord 密码
	 * @param state 状态
	 * @param roles 权限
	 * @param accountName 账号
	 */
	public Account(int accountId,String userName,String passWord,int state,int roles,String accountName){
		this.accountId=accountId;
		this.userName=userName;
		this.passWord=passWord;
		this.state=state;
		this.roles=roles;
		this.accountName=accountName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getRoles() {
		return roles;
	}
	public void setRoles(int roles) {
		this.roles = roles;
	}
}
