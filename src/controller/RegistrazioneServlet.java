package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.User;
import model.UserManager;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserManager manager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        manager = new UserManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String indirizzo = request.getParameter("indirizzo");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		if (nome == null || 
			cognome == null ||
			username == null ||
			indirizzo == null ||
			password == null ||
			email == null) {
			response.sendError(403);
		}
		
		try {
			if (manager.doRetrieveByKey(username) != null ) {
				response.sendError(452); //codice nostro per indicare username già utilizzato
			} else if (manager.doRetrieveByKey(email) != null) {
				response.sendError(453); // codice nostro per indicare email già utilizzata
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		user.setCognome(cognome);
		user.setEmail(email);
		user.setIndirizzo(indirizzo);
		user.setNome(nome);
		user.setUsername(username);
		user.setPassword(password);
		
		try {
			if (manager.doSave(user) > 0) {
				request.getSession().invalidate();
				request.getSession().setAttribute("user", manager.doRetrieveByKey(email));
				request.getSession().setAttribute("cart", new Carrello());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("./");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
