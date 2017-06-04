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
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		// get parameters from the request
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		UserDAO userDAO = DAOFactory.getUserDAO();
		User user = userDAO.getUserByUserNameAndPassword(userName, password);
		String url = "";
		if (user != null)
		{
			// store user id and user name in session
			request.getSession().setAttribute("userid", user.getId());
			request.getSession().setAttribute("username", user.getUsername());
	        request.getSession().setAttribute("user", user);
			
			request.setAttribute("message", "Welcome, " + user.getUsername());
	        
	        // forward request to JSP
	        url = "/home.jsp";
		}
		else
		{
			url = "/index.jsp";
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
