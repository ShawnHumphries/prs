package prs.controller;

import java.io.IOException;
import java.sql.Date;
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
import prs.db.purchaserequest.PurchaseRequestConstants;
import prs.db.purchaserequest.PurchaseRequestDAO;
import prs.db.vendor.VendorDAO;
import prs.util.StringUtil;

/**
 * Servlet implementation class PurchaseRequestServlet
 */
@WebServlet({ "/PurchaseRequestServlet", "/logout", "/home", "/createRequest", "/newRequest", "/submitRequest", "/showVendors", "/showProducts" })
public class PurchaseRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// get the current HTTP session
		HttpSession session = request.getSession();
		String url = "";
		
		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "logout";
		}
		if (action.equals("logout") || action.equals("home")) {
			// remove attributes from the session
			session.removeAttribute("newRequest");
			session.removeAttribute("lineItems");
			session.removeAttribute("purchaseRequestProducts");
			
			if (action.equals("logout")) {
				// remove userid and username from session
				session.removeAttribute("userid");
				session.removeAttribute("username");
				// forward request to index.jsp
				url = "/index.jsp";
			}
			else {
				url = "/home.jsp";
			}
		}
		if (action.equals("createRequest")) {
			
			// forward request to createRequest.jsp
			url = "/createRequest.jsp";
		}
		else if (action.equals("newRequest") || (action.equals("showVendors"))) {
			// get parameters from the request
			int uID = (Integer) request.getSession().getAttribute("userid");
			String userName = (String) request.getSession().getAttribute("username");
			String description = request.getParameter("description");
			String justification = request.getParameter("justification");
			String neededDate = request.getParameter("dateNeeded");
			Date dateNeeded = StringUtil.convertStringToSQLDate(neededDate);
			String deliveryMode = request.getParameter("deliveryMode");
			String docAttached = request.getParameter("docAttached");
			boolean isDocAttached = false;
			if (docAttached.equalsIgnoreCase("Yes"))
				isDocAttached = true;
			String status = "Submitted";
			Date submittedDate = StringUtil.convertTodaysDateToSQLDate();
			
			// Create a new request object with as much information as we have right now
			PurchaseRequest newRequest = new PurchaseRequest(description, justification, dateNeeded, uID, userName, deliveryMode, isDocAttached, status, 0.0, submittedDate);
			// store Request object in session
			session.setAttribute("newRequest", newRequest);

			// Get vendors from the DB and show them to the user
			VendorDAO vendorDAO = DAOFactory.getVendorDAO();
			ArrayList<Vendor> vendors = vendorDAO.getAllVendors();
			// store Vendor object in request
			request.setAttribute("vendors", vendors);
	  
			// forward request to showVendors.jsp
	        url = "/showVendors.jsp";
		}
		else if (action.equals("showProducts")) {
			ProductDAO productDAO = DAOFactory.getProductDAO();
			// Get the vendor ID of the vendor that was selected
			String vendorID = request.getParameter("vendorID");
			int id = Integer.parseInt(vendorID);
			
			// get the purchase request from session
			PurchaseRequest purchaseRequest = (PurchaseRequest) session.getAttribute("newRequest");
				
			// Get the list of products associated with the vendor id
			ArrayList<Product> products = productDAO.getProductsByVendorID(id);
			// store Product List in session
			session.setAttribute("purchaseRequestProducts", products);
			
			// Add the purchase request back to the session
			session.setAttribute("newRequest", purchaseRequest);

			// forward request to showProducts.jsp
			url = "/showProducts.jsp";
		}
		else if (action.equals("submitRequest")) {

			ArrayList <LineItem> lineItems = new ArrayList<>();
			ArrayList<Integer> quantities = new ArrayList<>();
			String result = "";
			double total = 0.0;
			int quantity = 0;
			int productIndex = 0;
			
			// get the products from the session
			ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("purchaseRequestProducts");
			// get the purchase request from session
			PurchaseRequest purchaseRequest = (PurchaseRequest) session.getAttribute("newRequest");
			// get parameters from the request
			for (String id : request.getParameterValues("productid")) {
				String q = request.getParameter("quantity" + "_" + id);
				if (q.length() > 0)
					quantity = Integer.parseInt(q);
				else
					quantity = 0;
				quantities.add(quantity);
			}
			for (Product product : products) {
				quantity = quantities.get(productIndex);
				// System.out.println("They want " + quantity + " of " + product.getName());
				if (quantity > 0) {
					total += quantity * product.getPrice();
					LineItem lineitem = new LineItem(product.getId(), quantity);
					lineItems.add(lineitem);
				}
				productIndex++;
			}
			
			// Set the purchase request total (for display purposes)
			purchaseRequest.setTotal(total);
			purchaseRequest.setFormattedTotal(StringUtil.getFormattedDouble(purchaseRequest.getTotal()));
			
			// set the status of the request.  All requests under $50.00 are automatically approved.
			if (purchaseRequest.getStatus().equals("Submitted") && (total < PurchaseRequestConstants.MGR_APPROVAL_LIMIT)) {
				purchaseRequest.setStatus("Approved");
			}
			
			PurchaseRequestDAO purchaseRequestDAO = DAOFactory.getPurchaseRequestDAO();
			if (purchaseRequestDAO.createRequest(purchaseRequest)) {
				// The INSERT was successful...so now get the ID of the INSERT
				int requestID = purchaseRequestDAO.getLastInsertID();
				LineItemDAO lineItemDAO = DAOFactory.getLineItemDAO();
				for (LineItem lineItem : lineItems) {
					lineItem.setRequestID(requestID);
					lineItemDAO.addLineItem(lineItem);
				}
				// System.out.println("\nYour request has been added successfully.\n");
				result = "Your request was added successfully";
			}
			else {
				// System.out.println("\nYour request was not added successfully.\n");
				result = "Your request was not added successfully";
			}

			// Add the purchase request back to the session
			session.setAttribute("newRequest", purchaseRequest);
			// Add the lineitems to the session
			session.setAttribute("lineItems", lineItems);
			// store message in request
			request.setAttribute("result", result);

			// forward request to showResults.jsp
			url = "/showResults.jsp";
		}

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
