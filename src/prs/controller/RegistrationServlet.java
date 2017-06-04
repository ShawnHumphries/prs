package prs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prs.business.User;
import prs.db.DAOFactory;
import prs.db.user.UserDAO;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet({ "/RegistrationServlet", "/registerUser", "/register" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getParameter("action");
		String url = "";
		
		if (action == null) {
			action = "registerUser";
		}
		
		// perform action and set URL to appropriate page
		if (action.equals("registerUser")) {
			url = "/registerNewUser.jsp";
		}
		else if (action.equals("register")) {
			// get parameters from the request
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");
			String manager = request.getParameter("manager");
			boolean isManager = false;
			if (manager != null)
				isManager = true;
			User newUser = new User(userName, password, firstName, lastName, phoneNumber, email, isManager);
			UserDAO userDAO = DAOFactory.getUserDAO();
			if (userDAO.addUser(newUser)) {
		        // store a welcome message in the request
		        request.setAttribute("message", "Welcome, " + userName);
		        // store the userid and username in the session
		        request.getSession().setAttribute("userid", newUser.getId());
		        request.getSession().setAttribute("username", newUser.getUsername());
				// store User object in request
		        request.setAttribute("user", newUser);
		        // forward request to home.jsp
		        url = "/home.jsp";
			}
			else { 
				// failed to add user, forward request to index.jsp
				url = "/index.jsp";
			}
		}
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
