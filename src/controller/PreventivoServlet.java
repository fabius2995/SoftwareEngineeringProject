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
import model.Automobile;
import model.AutomobileManager;
import model.Preventivo;
import model.PreventivoManager;
import model.User;

/**
 * Servlet implementation class preventivoServlet
 */
@WebServlet("/PreventivoServlet")
public class PreventivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreventivoManager manager;
    private AutomobileManager aManager;
    private Gson gson;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreventivoServlet() {
        super();
        manager = new PreventivoManager();
        aManager = new AutomobileManager();
        gson = new Gson();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User name = (User) request.getSession().getAttribute("user");
		if (name == null)
			response.sendError(401);
		
		int id = Integer.parseInt(request.getParameter("id"));
		if (id <= 0) {
			response.sendError(400);
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//salva il preventivo
		try {
			Automobile a = aManager.doRetrieveByKey(String.valueOf(id));
			Preventivo p = new Preventivo();
			p.setId_auto(a.getId());
			p.setPrezzo(a.getPrezzo());
			p.setUserClient(name.getUsername());

			if ( manager.doSave(p) > 0 ) {
				//invia risposta ajax per dire che ha funzionato 
				response.setStatus(200);
				out.print(gson.toJson(p.getPrezzo()));
			} else {
				response.setStatus(200);
				out.print(gson.toJson("error"));
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
