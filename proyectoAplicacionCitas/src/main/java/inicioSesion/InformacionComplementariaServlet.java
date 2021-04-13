package inicioSesion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InformacionComplementariaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InformacionComplementariaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/informacionComplementaria.jsp");
        dispatcher.forward(request, response);

	}

}
