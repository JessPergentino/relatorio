<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>

<title>MyCash - Novo Cartão</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>

	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />

	<div class="row mx-5 my-5">
		<div class="col-sm-12">
			<div class="card w-50 text-center mx-auto" id="card-conta-form">
				<h5 class="card-header">Novo Cartão</h5>
				<div class="card-body">
					<form action="CartaoDeCreditoController" method="post">
						<div class="row">
							<div class="col">
								<label for="inputNomeCartao">Nome do Cartão:</label> <input
									id="nomeCartao" name="nomeCartao" type="text"
									class="form-control" placeholder="Insira um nome">
							</div>
							<div class="col">
								<label for="inputLimite">Limite:</label> <input
									id="limiteCartao" name="limiteCartao" type="text"
									class="form-control" placeholder="Insira o limite">
							</div>
							<div class="col">
								<label for="dataVencimento">Data de Vencimento:</label> <input
									id="dataVencimento" name="dataVencimento" type="date"
									class="form-control">
							</div>
						</div>

						<div class="mx-3 my-2">
							<button type="submit" class="btn btn-primary">Salvar</button>
							<a href="index.jsp">
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