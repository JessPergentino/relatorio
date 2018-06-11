<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>My Cash - Adicionar Poupança</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>
	
	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />

	<div class="row mx-5 my-5">
		<div class="col-md-12">
			<div class="card w-25 text-center mx-auto" id="card-conta-form">
				<h5 class="card-header">Adicionar Novo Valor</h5>
				<div class="card-body">
					<form action="PoupancaController" method="post">
						<div class="row">
							<div class="col">
								<label for="nomePoupanca">Poupança:</label>
								<h4 id="nomePoupanca">${poupanca.nome}</h4>
								<input id="idPoupanca" name="idPoupanca" type="hidden" class="form-control" value="${poupanca.id}">
							</div>
							
							<div class="col">
								<label for="valorAdicionado">Valor:</label> 
								<input id="valorAdicionado" name="valorAdicionado" type="text" class="form-control" placeholder="Insira o valor">
							</div>
							
						</div>

						<div class="col mx-3 my-3">
							
							<button type="submit" class="btn btn-primary">Salvar</button>
							<a href="MetasPoupancas.jsp" > <button type="button" class="btn btn-secondary"
								>Cancelar</button></a>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
