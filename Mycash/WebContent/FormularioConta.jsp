<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>

<title>MyCash - Nova Conta</title>

<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>

	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />
	
		<div id="conteudo-form-conta" class="row mx-5 my-5">
			<div class="col-sm-12">
				<div class="card w-50 text-center mx-auto" id="card-conta-form">
					<h5 id="card-headr" class="card-header">Nova Conta</h5>
					<div id="card-body" class="card-body">
						<form action="ContaController" method="post">
							<div class="row">
								<div class="col">
									<label for="nomeConta">Nome da Conta:</label> <input
										id="nomeConta" type="text" class="form-control" name="nomeConta"
										placeholder="Insira o nome da Conta">
								</div>
								<div class="col">
									<label for="saldoInicial">Saldo Inicial:</label> <input
										id="saldoInicial" type="text" class="form-control" name="saldoInicial"
										placeholder="Insira o saldo inicial">
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