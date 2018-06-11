<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>MyCash - Alterar Carto</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>

	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />

	<div class="row mx-5 my-5">
		<div class="col-sm-12">
			<div class="card w-50 text-center mx-auto" id="card-conta-form">
				<h5 class="card-header">${cartao.nome}</h5>
				<div class="card-body">
					<form action="CartaoDeCreditoController" method="post">
						<div class="row">
							<div class="col">
								<label for="nomeCartao">Nome do Carto:</label> <input
									type="hidden" id="id" name="id" value="${cartao.id}"> <input
									id="nomeCartao" name="nomeCartao" type="text" class="form-control"
									value="${cartao.nome}">
							</div>
							<div class="col">
								<label for="limiteCartao">Limite:</label> <input
									id="limiteCartao" name="limiteCartao" type="text" class="form-control"
									value="${cartao.limite }">
							</div>
							<div class="col">
								<label for="dataVencimentoCartao">Data de Vencimento:</label> <input
									id="dataVencimentoCartao" name="dataVencimento" type="date" class="form-control"
									value="${cartao.vencimento}">
							</div>
						</div>

						<div class="my-2">
							
							<button type="submit" class="btn btn-primary">Salvar
								</button>
								<a href="index.jsp" ><button type="button" class="btn btn-secondary"
								>Cancelar</button></a>
						</div>

					</form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>