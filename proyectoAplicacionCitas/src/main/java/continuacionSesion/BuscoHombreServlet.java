package continuacionSesion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datosUsuario.DatosUsuario;

public class BuscoHombreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuscoHombreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatosUsuario objetoUsuario = new DatosUsuario("hombre", null, null, null);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("OBJETOUSUARIO", objetoUsuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/eresHombreOmujer.jsp");
		dispatcher.forward(request, response);				
	}

}
