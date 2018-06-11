<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>

	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />

	<div class="row mx-5 my-5">
		<div class="col-sm-12">
			<div class="card w-75 text-center mx-auto" id="card-conta-form">
				<h5 class="card-header">${poupanca.nome }</h5>
				<div class="card-body">
					<form class="form-group" action="PoupancaController" method="post">
						<div class="row">
							<div class="col">
								<input type="hidden" id="idPoupanca" name="idPoupanca"
									value="${poupanca.id}"> <label for="nomePoupanca">Poupança:</label>
								<input id="nomePoupanca" name="nomePoupanca" type="text"
									class="form-control" value="${poupanca.nome}">
							</div>
							<div class="col">
								<label for="valorTotal">Valor a ser Alcançado</label> <input
									id="valorTotal" name="valorTotal" type="text"
									class="form-control" value="${poupanca.valorTotal }">
							</div>
							<div class="col">
								<label for="prazo">Atá Quando Quer Juntar</label> <input
									id="prazoPoupanca" name="prazoPoupanca" type="date"
									class="form-control" value="${poupanca.prazo}">
							</div>
							<div class="col">
								<label for="valorAtual">Quanto Juntei</label> <input
									id="valorAtual" name="valorAtual" type="text"
									class="form-control" value="${poupanca.valorAtual}">
							</div>
						</div>
						<div class="form-group mx-4 my-4">
							
							<button type="submit" class="btn btn-primary">Salvar
								</button>
								<a href="MetasPoupancas.jsp">
								<button type="button" class="btn btn-secondary">Cancelar</button>
							</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>