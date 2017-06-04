package prs.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prs.business.Product;
import prs.db.DAOFactory;
import prs.db.product.ProductDAO;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet({ "/ProductListServlet", "/listProducts" })
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "";
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// get parameters from the request
		String action = request.getParameter("action");
		if (action == null) {
			action = "listProducts";
		}
		if (action.equals("listProducts")) {
			ProductDAO productDAO = DAOFactory.getProductDAO();
			// Get the vendor ID of the vendor that was selected
			String vendorID = request.getParameter("vendorID");
			int id = Integer.parseInt(vendorID);

			// Get the list of products associated with the vendor id
			ArrayList<Product> products = productDAO.getProductsByVendorID(id);
			
			request.setAttribute("products", products);
	        url = "/listProducts.jsp";
		}
		// forward request to JSP
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
