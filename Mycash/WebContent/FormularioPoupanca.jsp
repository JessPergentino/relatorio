<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>My Cash - Nova Poupana</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>
	
	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />

	<div class="row mx-5 my-5">
		<div class="col-md-12">
			<div class="card w-50 text-center mx-auto" id="card-conta-form">
				<h5 class="card-header">Nova Poupança</h5>
				<div class="card-body">
					<form action="PoupancaController" method="post">
						<div class="row">
							<div class="col">
								<label for="nomePoupanca">Poupança:</label> 
								<input id="idPoupanca" name="idPoupanca" type="hidden" class="form-control" value="${poupanca.id}">
								<input id="nomePoupanca" name="nomePoupanca" type="text" class="form-control" placeholder="Insira a Poupança">
							</div>
							
							<div class="col">
								<label for="valorTotal">Valor a ser Alcanado:</label> 
								<input id="valorTotal" name="valorTotal" type="text" class="form-control" placeholder="Insira o valor">
							</div>
							
							<div class="col">
								<label for="prazoPoupanca">Até Quando Quer Juntar:</label> 
								<input id="prazoPoupanca" name="prazoPoupanca" type="date" class="form-control">
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
