package continuacionSesion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LugardondeviveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LugardondeviveServlet() {
        super();
    }

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lugardondevive = request.getParameter("lugardondevive");
		HttpSession httpSesion = request.getSession();
		httpSesion.setAttribute("LUGARDONDEVIVE", lugardondevive);
		RequestDispatcher dispatcher = request.getRequestDispatcher("eresHombreOmujer.jsp");
		dispatcher.forward(request, response);

	}    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("eresHombreOmujer.jsp");
        dispatcher.forward(request, response);
	}


}
