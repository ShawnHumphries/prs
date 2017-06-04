package prs.db;

import prs.db.lineitem.*;
import prs.db.product.*;
import prs.db.purchaserequest.*;
import prs.db.user.*;
import prs.db.vendor.*;

public class DAOFactory {

	public static VendorDAO getVendorDAO() {
		
		VendorDAO vDAO = new VendorDB();
		return vDAO;
	}
	
	public static ProductDAO getProductDAO() {
		
		ProductDAO pDAO = new ProductDB();
		return pDAO;
	}
	
	public static UserDAO getUserDAO() {
		
		UserDAO uDAO = new UserDB();
		return uDAO;
	}
	
	public static PurchaseRequestDAO getPurchaseRequestDAO() {
		
		PurchaseRequestDAO prDAO = new PurchaseRequestDB();
		return prDAO;
	}
	
	public static LineItemDAO getLineItemDAO() {
		
		LineItemDAO liDAO = new LineItemDB();
		return liDAO;
	}
}
