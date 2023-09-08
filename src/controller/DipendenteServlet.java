package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dipendente;
import model.DipendenteManager;

/**
 * Servlet implementation class DipendenteServlet
 */
@WebServlet("/DipendenteServlet")
public class DipendenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DipendenteManager manager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DipendenteServlet() {
        super();
        manager = new DipendenteManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dipendente dip = (Dipendente) request.getSession().getAttribute("dip");
		Dipendente d = new Dipendente();
		
		if (dip == null || !dip.isAdmin()) {
			response.sendError(401);		
		}
		
		String action = request.getParameter("action");
		if (action.equals("") || action == null) {
			response.sendError(403);
		}
		
		switch (action.toLowerCase()) {
			case"add": {
				String uname = request.getParameter("username");
				String email = request.getParameter("email");
				String pass = request.getParameter("password");
				String nome = request.getParameter("nome");
				String cognome = request.getParameter("cognome");
				double stipendio = Double.parseDouble(request.getParameter("stipendio").replace(",", "."));
				String ruolo = request.getParameter("ruolo");
				
				d.setCognome(cognome);
				d.setEmail(email);
				d.setNome(nome);
				d.setPassword(pass);
				d.setRuolo(ruolo);
				d.setStipendio(stipendio);
				d.setUsername(uname);
				
				try {
					manager.doSave(d);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			case"remove": {
				String key = request.getParameter("key");
				if (key.equals("") || key == null) {
					response.sendError(403);
				}
				
				try {
					d = manager.doRetrieveByKey(key);
					
					if (d == null) {
						response.sendError(403);
					}
					
					manager.doDelete(d);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
			}
			case"edit": {
				String key = request.getParameter("key");
				if (key.equals("") || key == null) {
					response.sendError(403);
				}
				String pass = request.getParameter("password");
				double stipendio = Double.parseDouble(request.getParameter("stipendio").replace(",", "."));
				
				try {
					d = manager.doRetrieveByKey(key);
					
					if (d.getStipendio() != stipendio && stipendio > 0) {
						d.setStipendio(stipendio);
					}
					
					if (!d.getPassword().equals(pass)) {
						d.setPassword(pass);
					}
					
					manager.doUpdate(d);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
			}
			case"set": {
				String key = request.getParameter("key");
				
				if (key.equals("") || key == null) {
					response.sendError(403);
				}
				
				try {
					
					manager.setAdministrator(key);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
