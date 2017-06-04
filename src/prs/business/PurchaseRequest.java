package prs.business;

import java.io.Serializable;
import java.util.Date;

/*
 * The PurchaseRequest class represents a purchase request object that contains information about
 * a purchase request that a user has made for a product.
 */
public class PurchaseRequest implements Serializable {

	private int id;
	private String description;
	private String justification;
	private Date dateNeeded;
	private int userID;
	private String userName;
	private String deliveryMode;
	private boolean docAttached;
	private String status;
	private double total;
	private String formattedTotal;
	private Date submittedDate;
	
	// Default constructor
	public PurchaseRequest() {
		id = 0;
		description = "";
		justification = "";
		dateNeeded = null;
		userID = 0;
		userName = "";
		deliveryMode = "";
		docAttached = false;
		status = "";
		total = 0.0;
		formattedTotal = "";
		submittedDate = null;
	}

	public PurchaseRequest(String description, String justification, Date dateNeeded, int userID, String userName, String deliveryMode, boolean docAttached, String status, double total, Date submittedDate) {
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.userID = userID;
		this.userName = userName;
		this.deliveryMode = deliveryMode;
		this.docAttached = docAttached;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
	}

	public PurchaseRequest(String description, String justification, Date dateNeeded, int userID, String deliveryMode, boolean docAttached, String status, double total, Date submittedDate) {
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.userID = userID;
		this.userName = "";
		this.deliveryMode = deliveryMode;
		this.docAttached = docAttached;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Date getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(Date dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public boolean isDocAttached() {
		return docAttached;
	}

	public void setDocAttached(boolean docAttached) {
		this.docAttached = docAttached;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFormattedTotal() {
		return formattedTotal;
	}

	public void setFormattedTotal(String formattedTotal) {
		this.formattedTotal = formattedTotal;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String convertDocAttachedToString() {
		if (this.isDocAttached())
			return "Yes";
		else
			return "No";
	}
}
