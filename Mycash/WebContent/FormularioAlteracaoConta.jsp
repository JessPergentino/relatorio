<%@page import="br.com.loov.mycash.model.Conta"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loov.mycash.dao.ContaDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<title>MyCash - Alterar Conta</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>

	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />

	<div class="row mx-5 my-5">
		<div class="col-sm-12">
			<div class="card w-25 text-center mx-auto" id="card-conta-form">
				<h5 class="card-header">${conta.nome} </h5>
				<div class="card-body">
					<form class="form-group" action="ContaController" method="post">
						<div class="row">
							<div class="col">
								<input type="hidden" id="id" name="id" value="${conta.id }">
								<label for="nomeConta">Conta:</label>
								<input id="nomeConta" name="nomeConta" type="text" class="form-control"
									value="${conta.nome }">
							</div>
							<div class="col">
								<label for="saldoConta">Saldo Inicial:</label> <input
									id="saldoConta" name="saldoConta" type="text" class="form-control"
									value="${conta.saldo}" readonly>
							</div>
						</div>
						<div class="form-group mx-4 my-4" >
							
							<button type="submit" class="btn btn-primary">Salvar
								</button>
								<a href="index.jsp" > <button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cancelar</button> </a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
