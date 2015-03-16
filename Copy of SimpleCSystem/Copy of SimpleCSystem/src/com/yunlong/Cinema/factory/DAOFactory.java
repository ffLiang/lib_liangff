package com.yunlong.Cinema.factory;


import com.yunlong.Cinema.dao.imp.AccountADOImp;
import com.yunlong.Cinema.dao.imp.CategoryImp;
import com.yunlong.Cinema.dao.imp.DateDAOImp;
import com.yunlong.Cinema.dao.imp.FilmDAOImp;
import com.yunlong.Cinema.dao.imp.PlaceDAOImp;
import com.yunlong.Cinema.dao.imp.SellDAOImp;
import com.yunlong.Cinema.dao.imp.ShowPlanDAOImp;

public class DAOFactory {
	private static ShowPlanDAOImp showplandao=new ShowPlanDAOImp();
	private static PlaceDAOImp Placedao=new PlaceDAOImp();
	private static FilmDAOImp filmdao=new FilmDAOImp();
	private static SellDAOImp selldao=new SellDAOImp();
	private static CategoryImp categorydao=new CategoryImp();
	private static AccountADOImp accountdao=new AccountADOImp();
	private static DateDAOImp datedao=new DateDAOImp();
	
	public static AccountADOImp getAccountdao() {
		return accountdao;
	}
	public static CategoryImp getCategorydao() {
		return categorydao;
	}
	public static ShowPlanDAOImp getShowplandao() {
		return showplandao;
	}
	public static PlaceDAOImp getPlacedao() {
		return Placedao;
	}
	public static FilmDAOImp getFilmdao() {
		return filmdao;
	}
	public static SellDAOImp getSelldao() {
		return selldao;
	}
	public static DateDAOImp getdatedao() {
		return datedao;
	}
	
}
 
