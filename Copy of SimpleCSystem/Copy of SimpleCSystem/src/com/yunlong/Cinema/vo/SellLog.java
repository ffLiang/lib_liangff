package com.yunlong.Cinema.vo;

public class SellLog {
	private int slId;
	
	private ShowPlan showPlan;
	private Film film;
	
	private int spId;
	private int rows;
	private int columns;
	private int price;
	
	private Account account;
	private int accountId;
	
	public SellLog(){
		
	}
	/**
	 *  €∆±
	 * @param slId  €∆±±‡∫≈
	 * @param spId º∆ªÆ±‡∫≈
	 * @param rows ∫·≈≈±‡∫≈
	 * @param columns  ˙≈≈±‡∫≈
	 * @param price ∆±º€
	 * @param accountId ”√ªß±‡∫≈
	 */
	public SellLog(int slId, int spId, int rows,int columns,int price,int accountId){
		this.slId=slId;
		this.spId=spId;
		this.rows=rows;
		this.columns=columns;
		this.price=price;
		this.accountId=accountId;
	}
	public int getSlId() {
		return slId;
	}
	public void setSlId(int slId) {
		this.slId = slId;
	}
	public ShowPlan getShowPlan() {
		return showPlan;
	}
	public void setShowPlan(ShowPlan showPlan) {
		this.showPlan = showPlan;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}
