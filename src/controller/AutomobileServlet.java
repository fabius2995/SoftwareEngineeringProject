package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Automobile;
import model.AutomobileManager;


/**
 * Servlet implementation class AutomobileServlet
 */
@WebServlet("/AutomobileServlet")
public class AutomobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AutomobileManager manager;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutomobileServlet() {
        super();
        manager = new AutomobileManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		Automobile a = new Automobile();
		if (request.getSession().getAttribute("dip") == null) {
			response.sendError(401);
		}
		
		if (action.equals("") || action == null) {
			response.sendError(403);
		}
		
		switch (action.toLowerCase()) {
			case"add": {
		
				String marca = request.getParameter("marca");
				String modello = request.getParameter("modello");
				double prezzo = Double.parseDouble(request.getParameter("prezzo").replace(",", "."));
				String img = request.getParameter("img");
				
				a.setImg(img);
				a.setMarca(marca);
				a.setModello(modello);
				a.setPrezzo(prezzo);
				
				try {
					manager.doSave(a);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
			}
			case"edit": {
				
				String id = request.getParameter("id");
				if (id.equals("") || id.equals("0") || id == null) {
					response.sendError(403);
				}
				
				double prezzo = Double.parseDouble(request.getParameter("prezzp").replace(",", "."));
				String img = request.getParameter("img");
				
				try {
					a = manager.doRetrieveByKey(id);
					
					if (a.getPrezzo() != prezzo) {
						a.setPrezzo(prezzo);
					}
					if (!a.getImg().equals(img)) {
						a.setImg(img);
					}
					
					manager.doUpdate(a);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
			}
			case"remove": {
				String id = request.getParameter("id");
				if (id.equals("") || id.equals("0") || id == null) {
					response.sendError(403);
				}
				try {
					a = manager.doRetrieveByKey(id);
					if (a != null) {
						manager.doDelete(a);
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
