package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Carrello;
import model.Ordine;
import model.OrdineManager;
import model.Ricambio;
import model.RicambioManager;
import model.User;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrdineManager manager;
    private RicambioManager rManager;
    private Gson gson;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        manager = new OrdineManager();
        rManager = new RicambioManager();
        gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = "";
		int id = 0;
		Carrello cart = (Carrello) request.getSession().getAttribute("cart");
		String result = "";
		User u = (User) request.getSession().getAttribute("user");
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		response.setStatus(200);
		
		//deve essere registrato
		if ( u == null) {
			response.sendRedirect("./login.jsp");
		}
		//se è registrato ha un suo carrello
		if (cart == null) {
			request.getSession().invalidate();
			response.sendRedirect("./login.jsp");
		}
		
		action = request.getParameter("action");
		
		//action deve avere un valore
		if (action.equals("")) {
			response.sendError(403);
		}
		
		switch (action.toLowerCase()) {
			case"add": {
				try {
					id = Integer.parseInt(request.getParameter("id"));
					Ricambio r = rManager.doRetrieveByKey("" + id);
					if (r != null) {
						cart.addRicambio(r);
						result = "ok";
						
					} else {
						result = "error";
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.print(gson.toJson(result));

				break;
			}
			case"remove": {
				try {
					id = Integer.parseInt(request.getParameter("id"));
					Ricambio r = rManager.doRetrieveByKey("" + id);

					if (r != null) {
						cart.removeRicambio(r);
						result = "ok";
						
					} else {
						response.sendError(403);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.print(gson.toJson(result));
				System.out.println("" + result);
				break;
				
			}
			case"clear": {
				id = Integer.parseInt(request.getParameter("id"));
				if (id == 0) {
					cart.clearCart();
					result = "ok";
					
					out.print(gson.toJson(result));
				} else {
					result = "error";
				}
			}
			case"buy": {
				Ordine o = new Ordine();
				o.setUsername(u.getUsername());
				o.setIndirizzo(u.getIndirizzo());
				o.setProdotti(cart.getList());
				o.setTot(cart.getTotale());
				try {
					manager.doSave(o);
					cart.clearCart();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		request.getSession().setAttribute("cart", cart);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
