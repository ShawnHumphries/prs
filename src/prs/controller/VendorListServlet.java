package prs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prs.business.Vendor;
import prs.db.DAOFactory;
import prs.db.vendor.VendorDAO;

/**
 * Servlet implementation class VendorListServlet
 */
@WebServlet({ "/VendorListServlet", "/listVendors" })
public class VendorListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String url = "";
		
		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "listVendors";
		}
		else if (action.equals("listVendors")) {
			// there are no parameters to get from the request
			VendorDAO vendorDAO = DAOFactory.getVendorDAO();
			ArrayList<Vendor> vendors = vendorDAO.getAllVendors();
			// store Vendor object in request
			request.setAttribute("vendors", vendors);
	  
			// forward request to vendor.jsp
	        url = "/listVendors.jsp";

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
