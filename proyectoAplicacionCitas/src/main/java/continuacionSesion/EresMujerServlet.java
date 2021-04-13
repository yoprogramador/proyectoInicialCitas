package continuacionSesion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EresMujerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EresMujerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String variableSexo = "M";
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("VARIABLESEXO", variableSexo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/eresHombreOmujer.jsp");
		dispatcher.forward(request, response);
	}

}
