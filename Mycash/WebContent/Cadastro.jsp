<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyCash - Cadastre-se</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>
	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="MenuLogin.jsp" />


	<div class="row my-5">
		
		<div class="col-md-6">
			<h1 class="text-center">IMAGEM DA LOGO</h1>
		</div>

		<div class="col-md-6">
			
			<h3 class="text-center" >Cadastre-se</h3>
			
			<form action="UsuarioController" method="post" class="my-3">
			<div class="form-group col-md-8">
			<b><label for="nomeUsuario">Nome:</label></b>
			<input id="nomeUsuario"
				name="nomeUsuario" type="text" class="form-control"
				placeholder="Insira o seu nome">
			</div>
			
			<div class="form-group col-md-8">
			<b><label for="emailUsuario">Email:</label> </b>
			<input id="emailUsuario"
				name="emailUsuario" type="email" class="form-control"
				placeholder="Insira o seu email"> 
			</div>
			
			<div class="form-group col-md-8">
			<b><label for="senhaUsuario">Senha:</label></b>
			<input id="senhaUsuario" name="senhaUsuario" type="password"
				class="form-control" placeholder="Insira a sua senha">
			</div>
			
			<div class="form-group col-md-8">
			<b><label for="senhaConfirUsuario">Confirmar Senha:</label></b>
			<input id="senhaConfirUsuario" name="senhaConfirUsuario" type="password"
				class="form-control" placeholder="Confirme a senha">
			</div>
			
			<div class=" my-3 text-center">
			<button type="submit" class="btn btn-primary">Cadastrar</button>
			</div>
			</form>
		</div>
	</div>


</body>
</html>