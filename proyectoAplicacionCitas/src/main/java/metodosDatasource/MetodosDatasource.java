package metodosDatasource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import datosUsuario.DatosUsuario;


public class MetodosDatasource {
	private static PreparedStatement stm = null;
	private  static Connection conn = null;
	
	public static void makeJDBCConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
			return;
		}

		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/basededatoscitas", "root","");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}
	
	public static void addDataToDB(DatosUsuario datosUsuario) throws SQLException {

		String insertQueryStatement = "INSERT INTO `tablausuarios`(`busca`, `sexo`, `fechanacimiento`, `lugardondevive` ) VALUES (?,?,?,?)";

		stm = conn.prepareStatement(insertQueryStatement);
		stm.setString(1, datosUsuario.getBusca());
		stm.setString(2, datosUsuario.getSexo());
		stm.setString(3, datosUsuario.getFechanacimiento());
		stm.setString(4, datosUsuario.getLugardondevive());
		stm.executeUpdate();
	}  

	
	public static DatosUsuario getUsuarioById(String ident) {
		DatosUsuario usuarioById = null;
		int idInt = 0;
		try {
			idInt = Integer.parseInt(ident);
		} catch (NumberFormatException exception) {
			usuarioById = null;
			exception.printStackTrace();
		}
		
		try {
			// MySQL Select Query Tutorial
			String getQueryStatement = "SELECT * FROM tablaproblemas";

			stm = conn.prepareStatement(getQueryStatement);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String busca = rs.getString("busca");
				String sexo = rs.getString("sexo");
				SimpleDateFormat sdt = new SimpleDateFormat("dd/mm/yyyy");
				String fechanacimiento = sdt.format("fechanacimiento");
				String lugardondevive = rs.getString("lugardondevive");
				DatosUsuario datosusuario = new DatosUsuario(busca, sexo, fechanacimiento, lugardondevive);
				if(id == idInt) { usuarioById = datosusuario; }
			}

		} catch (SQLException e) {
			usuarioById = null;
			e.printStackTrace();
		}
		
		
		return usuarioById;
	}
	
	private static int devuelveNumeroMasAlto(List<Integer> listaIds) {
		int devolucion = 0;
		devolucion = Collections.max(listaIds);
		return devolucion;
	}
	
	public static int getLastId() {
		int lastId = 0;
		List<Integer> listaIdentificadores = new ArrayList<Integer>();		
		String lastIdString;
		try {
			// MySQL Select Query Tutorial
			String getQueryStatement = "SELECT * FROM tablausuarios";

			stm = conn.prepareStatement(getQueryStatement);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				listaIdentificadores.add(id);
			}
		lastId = devuelveNumeroMasAlto(listaIdentificadores);

		} catch (SQLException e) {
			lastId = 0;
			e.printStackTrace();
		}
				
		return lastId;		
	}
	
	public static void updateSoyHombre(int idObjeto) {
		String idObj = String.valueOf(idObjeto);
		try {
			String updateQueryStatement = "UPDATE `tablausuarios` SET `sexo` = ? WHERE `id` = ?";
			stm = conn.prepareStatement(updateQueryStatement);
			stm.setString(1, "hombre");
			stm.setString(2, idObj);
			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public static void updateSoyMujer(int idObjeto) {
		String idObj = String.valueOf(idObjeto);
		try {
			String updateQueryStatement = "UPDATE `tablausuarios` SET `sexo` = ? WHERE `id` = ?";
			stm = conn.prepareStatement(updateQueryStatement);
			stm.setString(1, "mujer");
			stm.setString(2, idObj);
			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		}		
	}
	
	public static void updateFechaNacimiento(int idObjeto, String fechaNacimiento) {
		String idObj = String.valueOf(idObjeto);		
		try {
			String updateQueryStatement = "UPDATE `tablausuarios` SET `fechanacimiento` = ? WHERE `id` = ?";
			stm = conn.prepareStatement(updateQueryStatement);
			stm.setString(1, fechaNacimiento);
			stm.setString(2, idObj);
			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		}		
		
	}    

	public static void updateLugarDondeVive(int idObjeto, String lugardondevive) {
		String idObj = String.valueOf(idObjeto);		
		try {
			String updateQueryStatement = "UPDATE `tablausuarios` SET `lugardondevive` = ? WHERE `id` = ?";
			stm = conn.prepareStatement(updateQueryStatement);
			stm.setString(1, lugardondevive);
			stm.setString(2, idObj);
			stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		}		
		
	}    

	
/*	
	public static String updateProblema(FormularioToUpdate formToUpdate) {
		String devolucion;
	if(comprobadorExistenciaId(formToUpdate.getIdentificador())) {
		if(formToUpdate.getCampo().equalsIgnoreCase("1")) {
			try {
				String updateQueryStatement = "UPDATE `tablaproblemas` SET `enunciadoDelProblema` = ? WHERE `id` = ?";

				stm = conn.prepareStatement(updateQueryStatement);
				stm.setString(1, formToUpdate.getDatos());
				stm.setString(2, formToUpdate.getIdentificador());
				stm.executeUpdate();


			} catch (SQLException e) {
				e.printStackTrace();
				devolucion = "Se ha excedido el límite en el número de caracteres introducido para el campo de enunciado del problema (1022 caracteres incluidos espacios en blanco).";
				String sqlException1 = "<h3>Introduzca de nuevo la información en el casillero de información actualizada teniendo en cuenta esta limitación.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Información actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException1;
			}
			devolucion = "Información del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El área al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resolución del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La solución del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resolución del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>¿Desea seguir utilizando la aplicación? si es así introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el número 1 y si desea salir de la aplicación introduzca cualquier otro número o carácter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					¿Continuar?\r\n" + 
					"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
					"						<br><br>\r\n" + 
					"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"					</form>\r\n" + 
					"";
			return devolucion + informacionProblema + salirOnoDeAplicacion;
			
		}else if(formToUpdate.getCampo().equalsIgnoreCase("2")) {
			if(formToUpdate.getDatos().equalsIgnoreCase("A")||formToUpdate.getDatos().equalsIgnoreCase("AL")|| formToUpdate.getDatos().equalsIgnoreCase("GA")
			   ||formToUpdate.getDatos().equalsIgnoreCase("P")||formToUpdate.getDatos().equalsIgnoreCase("GE")||formToUpdate.getDatos().equalsIgnoreCase("C")
			   ||formToUpdate.getDatos().equalsIgnoreCase("E")||formToUpdate.getDatos().equalsIgnoreCase("T")) {
				try {
					String updateQueryStatement = "UPDATE `tablaproblemas` SET `areaDelProblema` = ? WHERE `id` = ?";

					stm = conn.prepareStatement(updateQueryStatement);
					stm.setString(1, formToUpdate.getDatos());
					stm.setString(2, formToUpdate.getIdentificador());
					stm.executeUpdate();


				} catch (SQLException e) {
					e.printStackTrace();
				}
				devolucion = "Información del problema ha sido actualizada.";
				ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
				String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
						"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
						"					<h3>El área al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
						"					<h3>El procedimiento de resolución del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
						"					<h3>La solución del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
						"					<h3>El porcentaje de resolución del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
						"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
						"";
				String salirOnoDeAplicacion = "	<h3>¿Desea seguir utilizando la aplicación? si es así introduzca en el siguiente casillero</h3>\r\n" + 
						"					<h3>el número 1 y si desea salir de la aplicación introduzca cualquier otro número o carácter.</h3>\r\n" + 
						"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
						"					¿Continuar?\r\n" + 
						"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
						"						<br><br>\r\n" + 
						"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"					</form>\r\n" + 
						"";

				return devolucion + informacionProblema + salirOnoDeAplicacion;
			}else {
				devolucion = "La clave introducida para determinar el área al que pertenece el problema es incorecta.";
				String formulario = "<h3>Introduzca en el casillero de información actualizada el área que queremos</h3>\r\n" + 
						"			<h3>asignar al problema (A para problemas de aritmética, AL para problemas de álgebra,</h3>\r\n" + 
						"			<h3>GA para problemas de geometría analítica, P para problemas de proporcionalidad,</h3>\r\n" + 
						"			<h3>GE para problemas de geometría euclidiana, C para problemas de combinatoria</h3>\r\n" + 
						"			<h3>E para problemas de estadística, T para problemas de trigonometría).</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Información actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + formulario;
			}

		}else if(formToUpdate.getCampo().equalsIgnoreCase("3")) {
			try {
				String updateQueryStatement = "UPDATE `tablaproblemas` SET `procedimientoResolucion` = ? WHERE `id` = ?";

				stm = conn.prepareStatement(updateQueryStatement);
				stm.setString(1, formToUpdate.getDatos());
				stm.setString(2, formToUpdate.getIdentificador());
				stm.executeUpdate();


			} catch (SQLException e) {
				e.printStackTrace();
				devolucion = "Se ha excedido el límite en el número de caracteres introducido para el campo de procedimiento de resolución del problema (1022 caracteres incluidos espacios en blanco).";
				String sqlException3 = "<h3>Introduzca de nuevo la información en el casillero de información actualizada teniendo en cuenta esta limitación.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Información actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException3;
			}
			devolucion = "Información del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El área al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resolución del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La solución del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resolución del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>¿Desea seguir utilizando la aplicación? si es así introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el número 1 y si desea salir de la aplicación introduzca cualquier otro número o carácter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					¿Continuar?\r\n" + 
					"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
					"						<br><br>\r\n" + 
					"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"					</form>\r\n" + 
					"";

			return devolucion + informacionProblema + salirOnoDeAplicacion;

		}else if(formToUpdate.getCampo().equalsIgnoreCase("4")) {
			try {
				String updateQueryStatement = "UPDATE `tablaproblemas` SET `solucionProblema` = ? WHERE `id` = ?";

				stm = conn.prepareStatement(updateQueryStatement);
				stm.setString(1, formToUpdate.getDatos());
				stm.setString(2, formToUpdate.getIdentificador());
				stm.executeUpdate();


			} catch (SQLException e) {
				e.printStackTrace();
				devolucion = "Se ha excedido el límite en el número de caracteres introducido para el campo de solución del problema (300 caracteres incluidos espacios en blanco).";
				String sqlException4 = "<h3>Introduzca de nuevo la información en el casillero de información actualizada teniendo en cuenta esta limitación.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Información actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException4;
			}
			devolucion = "Información del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El área al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resolución del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La solución del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resolución del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>¿Desea seguir utilizando la aplicación? si es así introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el número 1 y si desea salir de la aplicación introduzca cualquier otro número o carácter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					¿Continuar?\r\n" + 
					"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
					"						<br><br>\r\n" + 
					"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"					</form>\r\n" + 
					"";

			return devolucion + informacionProblema + salirOnoDeAplicacion;

		}else if(formToUpdate.getCampo().equalsIgnoreCase("5")) {
			try {
				String updateQueryStatement = "UPDATE `tablaproblemas` SET `porcentajeDeResolucion` = ? WHERE `id` = ?";

				stm = conn.prepareStatement(updateQueryStatement);
				stm.setString(1, formToUpdate.getDatos());
				stm.setString(2, formToUpdate.getIdentificador());
				stm.executeUpdate();


			} catch (SQLException e) {
				e.printStackTrace();
				devolucion = "Se ha excedido el límite en el número de caracteres introducido para el campo de porcentaje de resolución del problema (200 caracteres incluidos espacios en blanco).";
				String sqlException5 = "<h3>Introduzca de nuevo la información en el casillero de información actualizada teniendo en cuenta esta limitación.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Información actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException5;
			}
			devolucion = "Información del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El área al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resolución del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La solución del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resolución del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>¿Desea seguir utilizando la aplicación? si es así introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el número 1 y si desea salir de la aplicación introduzca cualquier otro número o carácter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					¿Continuar?\r\n" + 
					"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
					"						<br><br>\r\n" + 
					"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"					</form>\r\n" + 
					"";

			return devolucion + informacionProblema + salirOnoDeAplicacion;

		}else if(formToUpdate.getCampo().equalsIgnoreCase("6")) {
			try {
				String updateQueryStatement = "UPDATE `tablaproblemas` SET `tiempoMedioMinutosInvertidoEnProblema` = ? WHERE `id` = ?";

				stm = conn.prepareStatement(updateQueryStatement);
				stm.setString(1, formToUpdate.getDatos());
				stm.setString(2, formToUpdate.getIdentificador());
				stm.executeUpdate();


			} catch (SQLException e) {
				e.printStackTrace();
				devolucion = "Se ha excedido el límite en el número de caracteres introducido para el campo de tiempo medio invertido en la resolución del problema (200 caracteres incluidos espacios en blanco).";
				String sqlException6 = "<h3>Introduzca de nuevo la información en el casillero de información actualizada teniendo en cuenta esta limitación.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Información actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException6;
			}
			devolucion = "Información del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El área al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resolución del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La solución del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resolución del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>¿Desea seguir utilizando la aplicación? si es así introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el número 1 y si desea salir de la aplicación introduzca cualquier otro número o carácter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					¿Continuar?\r\n" + 
					"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
					"						<br><br>\r\n" + 
					"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"					</form>\r\n" + 
					"";

			return devolucion + informacionProblema + salirOnoDeAplicacion;

		}else {
			devolucion = "Se ha introducido una clave incorrecta para designar el campo cuyos datos se quieren modificar.";
			String formulario = "<h3>Introduzca en el casillero del campo el campo del problema</h3>\r\n" + 
					"			<h3>donde se quiere realizar la modificación.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificación es el enunciado</h3>\r\n" + 
					"			<h3>del problema introduzca en el casillero del campo el dígito 1.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificación es el área</h3>\r\n" + 
					"			<h3>al que pertenece el problema (A para problemas de aritmética, AL para problemas de álgebra,</h3>\r\n" + 
					"			<h3>GA para problemas de geometría analítica, P para problemas de proporcionalidad,</h3>\r\n" + 
					"			<h3>GE para problemas de geometría euclidiana, C para problemas de combinatoria</h3>\r\n" + 
					"			<h3>E para problemas de estadística, T para problemas de trigonometría) introduzca</h3>\r\n" + 
					"			<h3>en el casillero del campo el dígito 2.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificación es el procedimiento</h3>\r\n" + 
					"			<h3>de resolución del problema introduzca en el casillero del campo el dígito 3.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificación es la solución</h3>\r\n" + 
					"			<h3>del problema introduzca en el casillero del campo el dígito 4.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificación es el porcentaje</h3>\r\n" + 
					"			<h3>de veces que el problema se resolvió con éxito introduzca en el casillero</h3>\r\n" + 
					"			<h3>del campo el dígito 5.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificación es el tiempo</h3>\r\n" + 
					"			<h3>medio en resolver el problema introduzca en el casillero del campo el dígito 6.</h3>\r\n" + 
					"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
					"			Identificador del problema:<br>\r\n" + 
					"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
					"			<br>\r\n" + 
					"			Campo:<br>\r\n" + 
					"			<input type=\"text\" name=\"campo\" value=\"\">\r\n" + 
					"			<br>\r\n" + 
					"			Información actualizada:<br>\r\n" + 
					"			<input type=\"text\" name=\"datos\" value=\""+formToUpdate.getDatos()+"\">\r\n" + 
					"			<br><br>\r\n" + 
					"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"			</form>\r\n" + 
					"";
			return devolucion + formulario;
		}
	}else {
		devolucion = "No existe un problema con el identificador introducido.";
		String salirOnoDeAplicacion = "	<h3>¿Desea seguir utilizando la aplicación? si es así introduzca en el siguiente casillero</h3>\r\n" + 
				"					<h3>el número 1 y si desea salir de la aplicación introduzca cualquier otro número o carácter.</h3>\r\n" + 
				"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
				"					¿Continuar?\r\n" + 
				"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
				"						<br><br>\r\n" + 
				"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
				"					</form>\r\n" + 
				"";

		return devolucion + salirOnoDeAplicacion;
	}		
   }
	
	public static String eliminarProblema(String ident) {
		String devolucion = "El problema seleccionado ha sido eliminado.";
		if(comprobadorExistenciaId(ident)) {	
			try {
				String deleteQueryStatement = "DELETE FROM `tablaproblemas` WHERE `id` = ?";
				stm = conn.prepareStatement(deleteQueryStatement);
				stm.setString(1, ident);
				stm.executeUpdate();			
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			String salirOnoDeAplicacion = "	<h3>¿Desea seguir utilizando la aplicación? si es así introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el número 1 y si desea salir de la aplicación introduzca cualquier otro número o carácter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					¿Continuar?\r\n" + 
					"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
					"						<br><br>\r\n" + 
					"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"					</form>\r\n" + 
					"";
		
			return devolucion + salirOnoDeAplicacion;
		}else {
			devolucion = "No existe un problema con el identificador introducido.";
			String salirOnoDeAplicacion = "	<h3>¿Desea seguir utilizando la aplicación? si es así introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el número 1 y si desea salir de la aplicación introduzca cualquier otro número o carácter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					¿Continuar?\r\n" + 
					"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
					"						<br><br>\r\n" + 
					"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"					</form>\r\n" + 
					"";

			return devolucion + salirOnoDeAplicacion;			
		}
	}
	

	public static boolean comprobadorExistenciaId(String ident) {
		boolean devolucion = false;
		int idInt = 0;	
		try {
			idInt = Integer.parseInt(ident);
		} catch (NumberFormatException exception) {
			devolucion = false;
			return devolucion;
		}
		
		try {
			String getQueryStatement = "SELECT * FROM tablaproblemas";
			stm = conn.prepareStatement(getQueryStatement);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				if(idInt == id) {
					devolucion = true;
					return devolucion;
				}
			}			
		} catch (SQLException e) {
			devolucion = false;
			e.printStackTrace();			
		}
		devolucion = false;
		return devolucion;
	}  */
	

}
