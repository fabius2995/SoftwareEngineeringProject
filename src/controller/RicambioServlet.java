package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dipendente;
import model.Ricambio;
import model.RicambioManager;

/**
 * Servlet implementation class RicambioServlet
 */
@WebServlet("/RicambioServlet")
public class RicambioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RicambioManager manager;
    public RicambioServlet() {
        super();
        manager = new RicambioManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Dipendente dip = (Dipendente) request.getSession().getAttribute("dip");
		String action = request.getParameter("action");
		Ricambio r = new Ricambio();
		if (dip == null) {
			response.sendError(401);
		}
		if (action != null && action.equals("")) {
			response.sendError(403);
		}
		
		switch (action.toLowerCase()) {
			case"add": {
				String marca = request.getParameter("marca");
				String tipo = request.getParameter("tipo");
				int qnt = Integer.parseInt(request.getParameter("qnt"));
				double prezzo = Double.parseDouble(request.getParameter("prezzo").replace(",", "."));
				String img = request.getParameter("img");
				
				if (marca.equals("") || tipo.equals("") || qnt <= 0 || prezzo <= 0 ) {
					response.sendError(403);
				 } else {
					r.setImg(((img != null) ? img : ""));
					r.setMarca(marca);
					r.setPrezzo(prezzo);
					r.setQuantità(qnt);
					r.setTipo(tipo);
					
					try {
						manager.doSave(r);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				break;
			}
			case"edit": {
				String id = request.getParameter("id");
				if (id.equals("0") || id == null || id.equals("")) {
					response.sendError(403);
				}
				
				int qnt = Integer.parseInt(request.getParameter("qnt"));
				double prezzo = Double.parseDouble(request.getParameter("prezzo").replace(",", "."));
				String img = request.getParameter("img");
				try {
					r = manager.doRetrieveByKey(id);
					
					if (r.getQuantità() < qnt) {
						r.setQuantità(qnt);
					}
					
					if (!img.equals("")) {
						r.setImg(img);
					}
					
					if (r.getPrezzo() != prezzo) {
						r.setPrezzo(prezzo);
					}
					manager.doUpdate(r);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			case"remove": {
				String id = request.getParameter("id");
				if (id.equals("") || id == null) {
					response.sendError(403);
				}
				
				try {
					r = (Ricambio) manager.doRetrieveByKey(id);
					if (r != null) {
						manager.doDelete(r);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
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
