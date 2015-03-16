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
	 * �˺�����
	 * @param accountId �˻����
	 * @param userName �û���
	 * @param passWord ����
	 * @param state ״̬
	 * @param roles Ȩ��
	 * @param accountName �˺�
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
