<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>MyCash - Adicionar Receita</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>

	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />

	<div class="row mx-5 my-5">
		<div class="col-md-12">
			<div class="card w-50 text-center mx-auto" id="card-conta-form">
				<h5 class="card-header">Adicionar Receita</h5>
				<div class="card-body">
					<form action="ReceitaController" method="post">
						<div class="row">

							<div class="col">
								<label for="nomeReceita">Receita:</label> <input id="idConta"
									name="idConta" type="hidden" class="form-control"
									value="${conta.id}"> <input id="nomeReceita"
									name="descricaoReceita" type="text" class="form-control"
									placeholder="Insira a Receita">
							</div>

							<div class="col">
								<label for="valorReceita">Valor:</label> <input
									id="valorReceita" name="valorReceita" type="text"
									class="form-control" placeholder="Insira o valor">
							</div>

							<div class="col">
								<label for="dataReceita">Data:</label> <input id="dataReceita"
									name="dataReceita" type="date" class="form-control">
							</div>


						</div>

						<div class="mx-3 my-2">
						<button type="submit" class="btn btn-primary">Salvar</button>
						<a href="index.jsp">
							<button type="button" class="btn btn-secondary"
								>Cancelar</button>
						</a>
						</div>
					</form>
				</div>

			</div>

		</div>
	</div>

</body>
</html>
