package prs.db.purchaserequest;

import java.util.ArrayList;

import prs.business.PurchaseRequest;

public interface PurchaseRequestReader {

	ArrayList <PurchaseRequest> getRequestsByUserID(int userID);
	ArrayList <PurchaseRequest> getPendingRequests();
	int getLastInsertID();
}
