package prs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import prs.business.LineItem;
import prs.business.Product;
import prs.business.PurchaseRequest;
import prs.business.Vendor;
import prs.db.DAOFactory;
import prs.db.lineitem.LineItemDAO;
import prs.db.product.ProductDAO;
import prs.db.purchaserequest.PurchaseRequestDAO;
import prs.db.user.UserDAO;
import prs.db.vendor.VendorDAO;
import prs.util.StringUtil;

/**
 * Servlet implementation class ReviewRequestServlet
 */
@WebServlet(
		description = "This servlet handles processing of querying the DB for existing purchase requests and returning information to the user.", 
		urlPatterns = { 
				"/ReviewRequestServlet", 
				"/reviewRequests", 
				"/approveRequests",
				"/updateRequests"
		})
public class ReviewRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDAO userDAO = DAOFactory.getUserDAO();
		PurchaseRequestDAO prDAO = DAOFactory.getPurchaseRequestDAO();
		LineItemDAO lineItemDAO = DAOFactory.getLineItemDAO();
		ProductDAO productDAO = DAOFactory.getProductDAO();
		boolean success = false;
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// get the current HTTP session
		HttpSession session = request.getSession();
		String url = "";

		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "home";
		}
		
		if (action.equals("home")) {
			// Set an error message to display to the user
			request.setAttribute("message", "This function is not available.  Please select another function.");
			url = "/home.jsp";
		}
		else if (action.equals("reviewRequests")) {

			// Get the ID of the user
			int userID = (Integer) session.getAttribute("userid");
			if (userID > 0) {
				ArrayList<PurchaseRequest> purchaseRequests = prDAO.getRequestsByUserID(userID);
				if (purchaseRequests != null) {
					if (purchaseRequests.size() == 0)
						request.setAttribute("message", "You do not have any submitted requests.");
					else {
						// Add the purchase request objects to the request
						request.setAttribute("purchaseRequests", purchaseRequests);
	
						// forward request to displayRequests.jsp
						url = "/displayRequests.jsp";
						success = true;
					}
				}
				else
					request.setAttribute("message", "This function is not available.  Please select another function.");
			}
			else
				request.setAttribute("message", "This function is not available.  Please select another function.");
			if (success == false) {
				// request.setAttribute("message",  "You do not have any submitted requests.");
				// forward request to home.jsp
				url = "/home.jsp";
			}
		} // if action == "reviewRequests"
		else if (action.equals("approveRequests")) {
			
			ArrayList<PurchaseRequest> pendingRequests = prDAO.getPendingRequests();
			if ((pendingRequests != null) && (pendingRequests.size() > 0)) {
				for (PurchaseRequest pr : pendingRequests) {
					int requestorID = pr.getUserID();
					pr.setUserName(userDAO.getUserNameByID(requestorID));
				}
				// Add the purchase request objects to the request
				request.setAttribute("purchaseRequests", pendingRequests);
				
				// forward request to approveRequests.jsp
				url = "/approveRequests.jsp";
				success = true;
			}
			else if ((pendingRequests != null) && (pendingRequests.size() == 0)) {
				// Inform the user there were no requests to approve
				request.setAttribute("message",  "There were no requests to approve");
				url = "/home.jsp";
				success = true;
			}
			if (success == false) {
				// Set an error message to display to the user
				request.setAttribute("message", "This function is not available.  Please select another function.");
				url = "/home.jsp";
			}
		} // if action == "approveRequests"
		else if (action.equals("updateRequests")) {
			
			String status = "";
			String message = "";
			
			// get parameters from the request
			for (String id : request.getParameterValues("requestid")) {
				// Get the value of the "approved" checkbox
				String approved = request.getParameter("approved" + "_" + id);
				int requestID = Integer.parseInt(id);
				// If the request was approved
				if ((approved != null) && (approved.equals("on"))) {
					status = "Approved";
				}
				else {	// The request is considered rejected
					status = "Rejected";
				}
				if (prDAO.updateRequestStatus(requestID, status))
					message = "Requests have been updated successfully";
				else
					message = "Requests have not been updated successfully";
			}
			
			request.setAttribute("message", message);
			// forward request to home.jsp
			url = "/home.jsp";
		} // if action == "updateRequests"
		
		getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
