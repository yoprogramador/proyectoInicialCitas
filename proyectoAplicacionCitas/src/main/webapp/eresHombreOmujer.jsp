<%@page import="continuacionSesion.FechaNacimientoServlet"%>
<%@page import="javax.swing.tree.VariableHeightLayoutCache"%>
<%@page import="java.sql.SQLException"%>
<%@page import="metodosDatasource.MetodosDatasource"%>
<%@page import="datosUsuario.DatosUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="ISO-8859-1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body
				<%
					HttpSession httpSession8 = request.getSession();
							DatosUsuario objetoUsuario =(DatosUsuario)httpSession8.getAttribute("OBJETOUSUARIO");
					MetodosDatasource.makeJDBCConnection();
					try{
						MetodosDatasource.addDataToDB(objetoUsuario);
					}catch(SQLException exception){
						exception.printStackTrace();
				%>
					<h3>Lo sentimos, se ha producido un error en el procesamiento de los datos.</h3>				
				<%		
					}
				%>
					 <% final int idObjeto = MetodosDatasource.getLastId(); %>
				<%
					HttpSession httpSession9 = request.getSession();
					String variableSexo =(String)httpSession9.getAttribute("VARIABLESEXO");		
					if(variableSexo == null){
				%>


 class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header"> <a href="index.html" class="logo"><strong>Editorial</strong>
					by HTML5 UP</a>
				<ul class="icons">
					<li><a href="#" class="icon fa-twitter"><span
							class="label">Twitter</span></a></li>
					<li><a href="#" class="icon fa-facebook"><span
							class="label">Facebook</span></a></li>
					<li><a href="#" class="icon fa-snapchat-ghost"><span
							class="label">Snapchat</span></a></li>
					<li><a href="#" class="icon fa-instagram"><span
							class="label">Instagram</span></a></li>
					<li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
				</ul>
				</header>

				<!-- Banner -->
				<section id="banner">
				<div class="content">
					<header>
					<h1>
						Háblanos sobre ti<br /> Queremos conocerte
					</h1>
					<p>Alguien te está esperando. Nosotros te ayudaremos a encontral@.</p>
					</header>
					<p>¿Qué eres hombre o mujer?</p>
				<ul class="actions">
						<li><a href="EresHombreServlet" class="button big">hombre</a></li>
					</ul>
				<ul class="actions">
						<li><a href="EresMujerServlet" class="button big">mujer</a></li>
					</ul>
				</div>
				<span class="image object"> <img
					src="images/fotoPareja3.jpg" alt="" />
				</span> </section>

				
		<!--
				<ul class="actions">
						<li><a href="BuscoHombreServlet" class="button big">un hombre</a></li>
					</ul>
				<ul class="actions">
						<li><a href="BuscoMujerServlet" class="button big">una mujer</a></li>
					</ul>					  
		
		
		
						<form action="SoyHombreServlet" method="post">
					  		<br>
							<input type="text" name="id" value="">
							<br><br>
							<input type="submit" value="">
						</form>
		
		
						<form action="SoyMujerServlet" method="post">
					  		<br>
							<input type="text" name="id" value="">
							<br><br>
							<input type="submit" value="">
						</form>
		
			<form action="Registry" method="post" >
					<div class="row gtr-uniform">
						<div class="col-6 col-12-xsmall">
							<input type="text" name="NOMBRE" placeholder="Nombre">
						</div>
						<div class="col-6 col-12-xsmall">
							<input type="password" name="PASS" value=""
								placeholder="Email" />
						</div>
						
						<div class="col-12">
							<ul class="actions">
								<li><input type="submit" value="Send Message"
									class="primary" /></li>
							
							</ul>
						</div>
					</div>
				</form>  -->

			</div>
		</div>

	</div> 

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>


			<%
				}else if(variableSexo.equals("H")){
					
					MetodosDatasource.updateSoyHombre(idObjeto);
					
					HttpSession httpSession10 = request.getSession();
					String fechaNacimiento =(String)httpSession10.getAttribute("FECHANACIMIENTO");
					if(fechaNacimiento == null){
					
			%>						
		
				<!-- Wrapper -->
				<div id="wrapper">
			
					<!-- Main -->
					<div id="main">
						<div class="inner">
			
							<!-- Header -->
							<header id="header"> <a href="index.html" class="logo"><strong>Web</strong>
								encuenta pareja</a>
							<ul class="icons">
								<li><a href="#" class="icon fa-twitter"><span
										class="label">Twitter</span></a></li>
								<li><a href="#" class="icon fa-facebook"><span
										class="label">Facebook</span></a></li>
								<li><a href="#" class="icon fa-snapchat-ghost"><span
										class="label">Snapchat</span></a></li>
								<li><a href="#" class="icon fa-instagram"><span
										class="label">Instagram</span></a></li>
								<li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
							</ul>
							</header>
			
							<!-- Banner -->
							<section id="banner">
							<div class="content">
								<header>
								<h1>
									Háblanos sobre ti<br /> Queremos conocerte
								</h1>
								<p>Cualquier edad puede ser un buen momento para comenzar una relación</p>
								</header>
								<p>Dinos tu fecha de nacimiento</p>
								<ul class="actions">
									<li><form action="FechaNacimientoServlet" method="post">
								  		Fecha de nacimiento:<br>
										<input type="text" name="fechaNacimiento" value="">
										<br><br>
										<input type="submit" value="Enviar">
									</form>
									</li>
								</ul>
							</div>
							<span class="image object"> <img
								src="images/fotoPersonasDistintasEdades.jpg" alt="" />
							</span> </section>
			
							<!-- Section
							<form action="Registry" method="post" >
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
										<input type="text" name="NOMBRE" placeholder="Nombre">
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="password" name="PASS" value=""
											placeholder="Email" />
									</div>
									
									<div class="col-12">
										<ul class="actions">
											<li><input type="submit" value="Send Message"
												class="primary" /></li>
										
										</ul>
									</div>
								</div>
							</form>  -->
			
						</div>
					</div>
			
				</div>
			
				<!-- Scripts -->
				<script src="assets/js/jquery.min.js"></script>
				<script src="assets/js/browser.min.js"></script>
				<script src="assets/js/breakpoints.min.js"></script>
				<script src="assets/js/util.js"></script>
				<script src="assets/js/main.js"></script>
				<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
					integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
					crossorigin="anonymous"></script>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
					integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
					crossorigin="anonymous"></script>
				<script
					src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
					integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
					crossorigin="anonymous"></script>				
			<%
					}else{
						MetodosDatasource.updateFechaNacimiento(idObjeto, fechaNacimiento);
						HttpSession httpSession11 = request.getSession();
						String lugardondevive =(String)httpSession10.getAttribute("LUGARDONDEVIVE");
						if(lugardondevive == null){
			%>
				<!-- Wrapper -->
				<div id="wrapper">
			
					<!-- Main -->
					<div id="main">
						<div class="inner">
			
							<!-- Header -->
							<header id="header"> <a href="index.html" class="logo"><strong>Editorial</strong>
								by HTML5 UP</a>
							<ul class="icons">
								<li><a href="#" class="icon fa-twitter"><span
										class="label">Twitter</span></a></li>
								<li><a href="#" class="icon fa-facebook"><span
										class="label">Facebook</span></a></li>
								<li><a href="#" class="icon fa-snapchat-ghost"><span
										class="label">Snapchat</span></a></li>
								<li><a href="#" class="icon fa-instagram"><span
										class="label">Instagram</span></a></li>
								<li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
							</ul>
							</header>
			
							<!-- Banner -->
							<section id="banner">
							<div class="content">
								<header>
								<h1>
									Háblanos sobre ti<br /> Queremos conocerte
								</h1>
								<p>Da igual el sitio donde estés, siempre puede haber alguien que quiera compatir tu vida</p>
								</header>
								<p>Indícamos tu lugar de residencia</p>
								<ul class="actions">
									<li><form action="LugardondeviveServlet" method="post">
								  		Localidad donde vives:<br>
										<input type="text" name="lugardondevive" value="">
										<br><br>
										<input type="submit" value="Enviar">
									</form>
									</li>
								</ul>
							</div>
							<span class="image object"> <img
								src="images/fotoDeUnaCalle.jpeg" alt="" />
							</span> </section>
			
							<!-- Section
							<form action="Registry" method="post" >
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
										<input type="text" name="NOMBRE" placeholder="Nombre">
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="password" name="PASS" value=""
											placeholder="Email" />
									</div>
									
									<div class="col-12">
										<ul class="actions">
											<li><input type="submit" value="Send Message"
												class="primary" /></li>
										
										</ul>
									</div>
								</div>
							</form>  -->
			
						</div>
					</div>
			
				</div>
			
				<!-- Scripts -->
				<script src="assets/js/jquery.min.js"></script>
				<script src="assets/js/browser.min.js"></script>
				<script src="assets/js/breakpoints.min.js"></script>
				<script src="assets/js/util.js"></script>
				<script src="assets/js/main.js"></script>
				<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
					integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
					crossorigin="anonymous"></script>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
					integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
					crossorigin="anonymous"></script>
				<script
					src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
					integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
					crossorigin="anonymous"></script>				
		
			<%
					 }else{
						 MetodosDatasource.updateLugarDondeVive(idObjeto, lugardondevive);
			%>
						 <h3>Los datos del usuario han sido introducidos en la base de datos</h3>
			<%
					 }
				   }
			
			
				}else if(variableSexo.equals("M")){
					
					MetodosDatasource.updateSoyMujer(idObjeto);
					
					HttpSession httpSession10 = request.getSession();
					String fechaNacimiento =(String)httpSession10.getAttribute("FECHANACIMIENTO");
					if(fechaNacimiento == null){

			%>

				<!-- Wrapper -->
				<div id="wrapper">
			
					<!-- Main -->
					<div id="main">
						<div class="inner">
			
							<!-- Header -->
							<header id="header"> <a href="index.html" class="logo"><strong>Editorial</strong>
								by HTML5 UP</a>
							<ul class="icons">
								<li><a href="#" class="icon fa-twitter"><span
										class="label">Twitter</span></a></li>
								<li><a href="#" class="icon fa-facebook"><span
										class="label">Facebook</span></a></li>
								<li><a href="#" class="icon fa-snapchat-ghost"><span
										class="label">Snapchat</span></a></li>
								<li><a href="#" class="icon fa-instagram"><span
										class="label">Instagram</span></a></li>
								<li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
							</ul>
							</header>
			
							<!-- Banner -->
							<section id="banner">
							<div class="content">
								<header>
								<h1>
									Háblanos sobre ti<br /> Queremos conocerte
								</h1>
								<p>Cualquier edad puede ser un buen momento para comenzar una relación</p>
								</header>
								<p>Dinos tu fecha de nacimiento</p>
								<ul class="actions">
									<li><form action="FechaNacimientoServlet" method="post">
								  		Fecha de nacimiento:<br>
										<input type="text" name="fechaNacimiento" value="">
										<br><br>
										<input type="submit" value="Enviar">
									</form>
									</li>
								</ul>
							</div>
							<span class="image object"> <img
								src="images/fotoPersonasDistintasEdades.jpg" alt="" />
							</span> </section>
			
							<!-- Section
							<form action="Registry" method="post" >
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
										<input type="text" name="NOMBRE" placeholder="Nombre">
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="password" name="PASS" value=""
											placeholder="Email" />
									</div>
									
									<div class="col-12">
										<ul class="actions">
											<li><input type="submit" value="Send Message"
												class="primary" /></li>
										
										</ul>
									</div>
								</div>
							</form>  -->
			
						</div>
					</div>
			
				</div>
			
				<!-- Scripts -->
				<script src="assets/js/jquery.min.js"></script>
				<script src="assets/js/browser.min.js"></script>
				<script src="assets/js/breakpoints.min.js"></script>
				<script src="assets/js/util.js"></script>
				<script src="assets/js/main.js"></script>
				<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
					integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
					crossorigin="anonymous"></script>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
					integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
					crossorigin="anonymous"></script>
				<script
					src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
					integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
					crossorigin="anonymous"></script>
	 
			<%
					}else{
						MetodosDatasource.updateFechaNacimiento(idObjeto, fechaNacimiento);
						HttpSession httpSession11 = request.getSession();
						String lugardondevive =(String)httpSession10.getAttribute("LUGARDONDEVIVE");
						if(lugardondevive == null){
			%>
				<!-- Wrapper -->
				<div id="wrapper">
			
					<!-- Main -->
					<div id="main">
						<div class="inner">
			
							<!-- Header -->
							<header id="header"> <a href="index.html" class="logo"><strong>Editorial</strong>
								by HTML5 UP</a>
							<ul class="icons">
								<li><a href="#" class="icon fa-twitter"><span
										class="label">Twitter</span></a></li>
								<li><a href="#" class="icon fa-facebook"><span
										class="label">Facebook</span></a></li>
								<li><a href="#" class="icon fa-snapchat-ghost"><span
										class="label">Snapchat</span></a></li>
								<li><a href="#" class="icon fa-instagram"><span
										class="label">Instagram</span></a></li>
								<li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
							</ul>
							</header>
			
							<!-- Banner -->
							<section id="banner">
							<div class="content">
								<header>
								<h1>
									Háblanos sobre ti<br /> Queremos conocerte
								</h1>
								<p>Da igual el sitio donde estés, siempre puede haber alguien que quiera compatir tu vida</p>
								</header>
								<p>Indícamos tu lugar de residencia</p>
								<ul class="actions">
									<li><form action="LugardondeviveServlet" method="post">
								  		Localidad donde vives:<br>
										<input type="text" name="lugardondevive" value="">
										<br><br>
										<input type="submit" value="Enviar">
									</form>
									</li>
								</ul>
							</div>
							<span class="image object"> <img
								src="images/fotoDeUnaCalle.jpeg" alt="" />
							</span> </section>
			
							<!-- Section
							<form action="Registry" method="post" >
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
										<input type="text" name="NOMBRE" placeholder="Nombre">
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="password" name="PASS" value=""
											placeholder="Email" />
									</div>
									
									<div class="col-12">
										<ul class="actions">
											<li><input type="submit" value="Send Message"
												class="primary" /></li>
										
										</ul>
									</div>
								</div>
							</form>  -->
			
						</div>
					</div>
			
				</div>
			
				<!-- Scripts -->
				<script src="assets/js/jquery.min.js"></script>
				<script src="assets/js/browser.min.js"></script>
				<script src="assets/js/breakpoints.min.js"></script>
				<script src="assets/js/util.js"></script>
				<script src="assets/js/main.js"></script>
				<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
					integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
					crossorigin="anonymous"></script>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
					integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
					crossorigin="anonymous"></script>
				<script
					src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
					integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
					crossorigin="anonymous"></script>				
						
			<%	
					}else{
						MetodosDatasource.updateLugarDondeVive(idObjeto, lugardondevive);
			%>
						<h3>Los datos del usuario han sido introducidos en la base de datos</h3>
			<%
					}
				  }
				}
			%>		
		

</body>

</html>