<%@page import="br.com.loov.mycash.dao.UsuarioDAO"%>
<%@page import="br.com.loov.mycash.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="DependenciasBootstrap.jsp" />
<title>MyCash - Perfil/Alteração</title>
</head>
<body>

	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />

	<div class="my-5">
		<div class="card  w-50 mx-auto">
			<div class="card-header">Suas Informações</div>
			<div class="card-body">

				<form action="UsuarioController" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<input type="hidden" id="id" name="id" value="${usuario.id}" >
							<label for="nomeUsuario">Nome:</label> <input type="text"
								class="form-control" id="nomeUsuario" name="nomeUsuario"
								value="${usuario.nome}">
						</div>

						<div class="form-group col-md-6">
							<label for="senhaUsuario">Nova Senha:</label> <input
								type="password" class="form-control" id="senhaUsuario"
								name="senhaUsuario">
						</div>

					</div>

					<div class="form-row">

						<div class="form-group col-md-6">
							<label for="emailUsuario">Email:</label> <input type="email"
								class="form-control" id="emailUsuario" name="emailUsuario"
								value="${usuario.email }">
						</div>

						<div class="form-group col-md-6">
							<label for="senhaConfirUsuario">Confirmar Senha:</label> <input
								id="senhaConfirUsuario" name="senhaConfirUsuario"
								class="form-control">

						</div>

					</div>

					<div class="form-row">

						<div class="col-md-6">
							<button type="submit" class="btn btn-primary">Alterar</button>
						</div>

						<div class="col-md-6">
							<a href="/Mycash/UsuarioController?q=excluir&id=${usuario.id}"><button
									type="button" class="btn btn-primary">Deletar Conta</button></a>
						</div>

					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>