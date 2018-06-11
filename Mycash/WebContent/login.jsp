<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyCash - Login</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>
	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="MenuLogin.jsp" />

	<div class="my-5">
	<div class="card  w-25 mx-auto">
		<div class="card-header">Login</div>
		<div class="card-body">
		
			<form action="LoginController" method="post" class="my-3">
			
			<div class="form-group">
			<label for="emailUsuario">Email:</label> <input id="emailUsuario"
									name="emailUsuario" type="email" class="form-control"
									placeholder="Insira o seu email">
			</div>
			
			<div class="form-group">
			<label for="senhaUsuario">Senha:</label> <input id="senhaUsuario"
									name="senhaUsuario" type="password" class="form-control"
									placeholder="Insira a sua senha">
			</div>
			
			<div class=" my-3 text-center">
			<button type="submit" class="btn btn-primary">Login</button>
			<a href="Cadastro.jsp"><button type="button" class="btn btn-primary">Cadastre-Se</button></a>
			</div>
			
			</form>
		</div>
	</div>
</div>


</body>
</html>