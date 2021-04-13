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
				devolucion = "Se ha excedido el l�mite en el n�mero de caracteres introducido para el campo de enunciado del problema (1022 caracteres incluidos espacios en blanco).";
				String sqlException1 = "<h3>Introduzca de nuevo la informaci�n en el casillero de informaci�n actualizada teniendo en cuenta esta limitaci�n.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Informaci�n actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException1;
			}
			devolucion = "Informaci�n del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El �rea al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resoluci�n del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La soluci�n del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resoluci�n del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>�Desea seguir utilizando la aplicaci�n? si es as� introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el n�mero 1 y si desea salir de la aplicaci�n introduzca cualquier otro n�mero o car�cter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					�Continuar?\r\n" + 
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
				devolucion = "Informaci�n del problema ha sido actualizada.";
				ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
				String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
						"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
						"					<h3>El �rea al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
						"					<h3>El procedimiento de resoluci�n del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
						"					<h3>La soluci�n del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
						"					<h3>El porcentaje de resoluci�n del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
						"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
						"";
				String salirOnoDeAplicacion = "	<h3>�Desea seguir utilizando la aplicaci�n? si es as� introduzca en el siguiente casillero</h3>\r\n" + 
						"					<h3>el n�mero 1 y si desea salir de la aplicaci�n introduzca cualquier otro n�mero o car�cter.</h3>\r\n" + 
						"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
						"					�Continuar?\r\n" + 
						"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
						"						<br><br>\r\n" + 
						"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"					</form>\r\n" + 
						"";

				return devolucion + informacionProblema + salirOnoDeAplicacion;
			}else {
				devolucion = "La clave introducida para determinar el �rea al que pertenece el problema es incorecta.";
				String formulario = "<h3>Introduzca en el casillero de informaci�n actualizada el �rea que queremos</h3>\r\n" + 
						"			<h3>asignar al problema (A para problemas de aritm�tica, AL para problemas de �lgebra,</h3>\r\n" + 
						"			<h3>GA para problemas de geometr�a anal�tica, P para problemas de proporcionalidad,</h3>\r\n" + 
						"			<h3>GE para problemas de geometr�a euclidiana, C para problemas de combinatoria</h3>\r\n" + 
						"			<h3>E para problemas de estad�stica, T para problemas de trigonometr�a).</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Informaci�n actualizada:<br>\r\n" + 
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
				devolucion = "Se ha excedido el l�mite en el n�mero de caracteres introducido para el campo de procedimiento de resoluci�n del problema (1022 caracteres incluidos espacios en blanco).";
				String sqlException3 = "<h3>Introduzca de nuevo la informaci�n en el casillero de informaci�n actualizada teniendo en cuenta esta limitaci�n.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Informaci�n actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException3;
			}
			devolucion = "Informaci�n del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El �rea al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resoluci�n del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La soluci�n del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resoluci�n del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>�Desea seguir utilizando la aplicaci�n? si es as� introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el n�mero 1 y si desea salir de la aplicaci�n introduzca cualquier otro n�mero o car�cter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					�Continuar?\r\n" + 
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
				devolucion = "Se ha excedido el l�mite en el n�mero de caracteres introducido para el campo de soluci�n del problema (300 caracteres incluidos espacios en blanco).";
				String sqlException4 = "<h3>Introduzca de nuevo la informaci�n en el casillero de informaci�n actualizada teniendo en cuenta esta limitaci�n.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Informaci�n actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException4;
			}
			devolucion = "Informaci�n del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El �rea al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resoluci�n del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La soluci�n del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resoluci�n del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>�Desea seguir utilizando la aplicaci�n? si es as� introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el n�mero 1 y si desea salir de la aplicaci�n introduzca cualquier otro n�mero o car�cter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					�Continuar?\r\n" + 
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
				devolucion = "Se ha excedido el l�mite en el n�mero de caracteres introducido para el campo de porcentaje de resoluci�n del problema (200 caracteres incluidos espacios en blanco).";
				String sqlException5 = "<h3>Introduzca de nuevo la informaci�n en el casillero de informaci�n actualizada teniendo en cuenta esta limitaci�n.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Informaci�n actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException5;
			}
			devolucion = "Informaci�n del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El �rea al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resoluci�n del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La soluci�n del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resoluci�n del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>�Desea seguir utilizando la aplicaci�n? si es as� introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el n�mero 1 y si desea salir de la aplicaci�n introduzca cualquier otro n�mero o car�cter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					�Continuar?\r\n" + 
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
				devolucion = "Se ha excedido el l�mite en el n�mero de caracteres introducido para el campo de tiempo medio invertido en la resoluci�n del problema (200 caracteres incluidos espacios en blanco).";
				String sqlException6 = "<h3>Introduzca de nuevo la informaci�n en el casillero de informaci�n actualizada teniendo en cuenta esta limitaci�n.</h3>\r\n" + 
						"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
						"			Identificador del problema:<br>\r\n" + 
						"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Campo:<br>\r\n" + 
						"			<input type=\"text\" name=\"campo\" value=\""+formToUpdate.getCampo()+"\">\r\n" + 
						"			<br>\r\n" + 
						"			Informaci�n actualizada:<br>\r\n" + 
						"			<input type=\"text\" name=\"datos\" value=\"\">\r\n" + 
						"			<br><br>\r\n" + 
						"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
						"			</form>\r\n" + 
						"";
				return devolucion + sqlException6;
			}
			devolucion = "Informaci�n del problema ha sido actualizada.";
			ProblemasDao problema = getProblemaById(formToUpdate.getIdentificador());
			String informacionProblema = "<h3>El identificador del problema es :&nbsp"+problema.getId()+"</h3>\r\n" + 
					"					<h3>El enunciado del problema es :&nbsp"+problema.getEnunciadoDelProblema()+"</h3>\r\n" + 
					"					<h3>El �rea al que pertenece el problema es :&nbsp"+problema.getAreaDelProblema()+"</h3>\r\n" + 
					"					<h3>El procedimiento de resoluci�n del problema es :&nbsp"+problema.getProcedimientoResolucion()+"</h3>\r\n" + 
					"					<h3>La soluci�n del problema es :&nbsp"+problema.getSolucionProblema()+"</h3>\r\n" + 
					"					<h3>El porcentaje de resoluci�n del problema es :&nbsp"+problema.getPorcentajeDeResolucion()+"</h3>\r\n" + 
					"					<h3>El tiempo medio invertido en resolver el problema es :&nbsp"+problema.getTiempoMedioMinutosInvertidoEnProblema()+"</h3>\r\n" + 
					"";
			String salirOnoDeAplicacion = "	<h3>�Desea seguir utilizando la aplicaci�n? si es as� introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el n�mero 1 y si desea salir de la aplicaci�n introduzca cualquier otro n�mero o car�cter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					�Continuar?\r\n" + 
					"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
					"						<br><br>\r\n" + 
					"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"					</form>\r\n" + 
					"";

			return devolucion + informacionProblema + salirOnoDeAplicacion;

		}else {
			devolucion = "Se ha introducido una clave incorrecta para designar el campo cuyos datos se quieren modificar.";
			String formulario = "<h3>Introduzca en el casillero del campo el campo del problema</h3>\r\n" + 
					"			<h3>donde se quiere realizar la modificaci�n.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificaci�n es el enunciado</h3>\r\n" + 
					"			<h3>del problema introduzca en el casillero del campo el d�gito 1.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificaci�n es el �rea</h3>\r\n" + 
					"			<h3>al que pertenece el problema (A para problemas de aritm�tica, AL para problemas de �lgebra,</h3>\r\n" + 
					"			<h3>GA para problemas de geometr�a anal�tica, P para problemas de proporcionalidad,</h3>\r\n" + 
					"			<h3>GE para problemas de geometr�a euclidiana, C para problemas de combinatoria</h3>\r\n" + 
					"			<h3>E para problemas de estad�stica, T para problemas de trigonometr�a) introduzca</h3>\r\n" + 
					"			<h3>en el casillero del campo el d�gito 2.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificaci�n es el procedimiento</h3>\r\n" + 
					"			<h3>de resoluci�n del problema introduzca en el casillero del campo el d�gito 3.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificaci�n es la soluci�n</h3>\r\n" + 
					"			<h3>del problema introduzca en el casillero del campo el d�gito 4.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificaci�n es el porcentaje</h3>\r\n" + 
					"			<h3>de veces que el problema se resolvi� con �xito introduzca en el casillero</h3>\r\n" + 
					"			<h3>del campo el d�gito 5.</h3>\r\n" + 
					"			<h3>--Si el campo en el que se quiere realizar la modificaci�n es el tiempo</h3>\r\n" + 
					"			<h3>medio en resolver el problema introduzca en el casillero del campo el d�gito 6.</h3>\r\n" + 
					"			<form action=\"FormularioModificacionProblemaServlet\" method=\"post\">\r\n" + 
					"			Identificador del problema:<br>\r\n" + 
					"			<input type=\"text\" name=\"identificador\" value=\""+formToUpdate.getIdentificador()+"\">\r\n" + 
					"			<br>\r\n" + 
					"			Campo:<br>\r\n" + 
					"			<input type=\"text\" name=\"campo\" value=\"\">\r\n" + 
					"			<br>\r\n" + 
					"			Informaci�n actualizada:<br>\r\n" + 
					"			<input type=\"text\" name=\"datos\" value=\""+formToUpdate.getDatos()+"\">\r\n" + 
					"			<br><br>\r\n" + 
					"		  	<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"			</form>\r\n" + 
					"";
			return devolucion + formulario;
		}
	}else {
		devolucion = "No existe un problema con el identificador introducido.";
		String salirOnoDeAplicacion = "	<h3>�Desea seguir utilizando la aplicaci�n? si es as� introduzca en el siguiente casillero</h3>\r\n" + 
				"					<h3>el n�mero 1 y si desea salir de la aplicaci�n introduzca cualquier otro n�mero o car�cter.</h3>\r\n" + 
				"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
				"					�Continuar?\r\n" + 
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
		
			String salirOnoDeAplicacion = "	<h3>�Desea seguir utilizando la aplicaci�n? si es as� introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el n�mero 1 y si desea salir de la aplicaci�n introduzca cualquier otro n�mero o car�cter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					�Continuar?\r\n" + 
					"					<input type=\"text\" name=\"decisionSalirOno\" value=\"\">\r\n" + 
					"						<br><br>\r\n" + 
					"		  	  		<input type=\"submit\" value=\"Enviar\">\r\n" + 
					"					</form>\r\n" + 
					"";
		
			return devolucion + salirOnoDeAplicacion;
		}else {
			devolucion = "No existe un problema con el identificador introducido.";
			String salirOnoDeAplicacion = "	<h3>�Desea seguir utilizando la aplicaci�n? si es as� introduzca en el siguiente casillero</h3>\r\n" + 
					"					<h3>el n�mero 1 y si desea salir de la aplicaci�n introduzca cualquier otro n�mero o car�cter.</h3>\r\n" + 
					"					<form action=\"DecisionSalirDeLaAplicacionServlet\" method=\"post\">\r\n" + 
					"					�Continuar?\r\n" + 
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
