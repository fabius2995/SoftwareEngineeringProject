package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserManager uManager;
    private DipendenteManager dManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        uManager = new UserManager();
        dManager = new DipendenteManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || password == null)
			response.sendError(400);
		try {
			User utenteVero = uManager.doRetrieveByKey(username);
			
			if (utenteVero != null) {
				if (utenteVero.getPassword().equals(password)) {
					request.getSession().setAttribute("user", utenteVero);
					request.getSession().setAttribute("cart", new Carrello());
					response.sendRedirect("./home.jsp");
				} else {
					response.sendError(401);
				}
			} else {
				Dipendente dip = dManager.doRetrieveByKey(username);
				if (dip != null) {
					if (dip.getPassword().equals(password)) {
						request.getSession().setAttribute("dip", dip);
						response.sendRedirect("./home.jsp");
					}
				} else {
					response.sendError(401);
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
