package com.yunlong.Cinema.vo;

public class Place {
	private int PlaceId;
	private String PlaceName;
	private int seatNumber;
	private int rows;
	private int columns;
	private String location;
	public Place(){
		
	}
	/**
	 * 电影院
	 * @param PlaceId 电影院ID
	 * @param PlaceName 电影院名
	 * @param location 位置
	 */
	public Place(int PlaceId,String PlaceName,int seatNumber,int rows,int columns,String location){
		this.PlaceId=PlaceId;
		this.PlaceName=PlaceName;
		this.seatNumber=seatNumber;
		this.rows=rows;
		this.columns=columns;
		this.location=location;
	}
	public int getPlaceId() {
		return PlaceId;
	}
	public void setPlaceId(int PlaceId) {
		this.PlaceId = PlaceId;
	}
	public String getPlaceName() {
		return PlaceName;
	}
	public void setPlaceName(String PlaceName) {
		this.PlaceName = PlaceName;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
}