package prs.db.purchaserequest;

import prs.business.PurchaseRequest;

public interface PurchaseRequestWriter {

	boolean createRequest(PurchaseRequest inRequest);
	boolean updateRequestStatus(int inRequestID, String inStatus);
}
