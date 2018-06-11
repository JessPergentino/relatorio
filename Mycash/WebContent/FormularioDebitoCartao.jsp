<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>MyCash - Adicionar Debito ao Cartão</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>

	<c:import url="DependenciasJQuery.jsp" />

	<c:import url="Menu.jsp" />

	<div class="row mx-5 my-5">
		<div class="col-md-12">
			<div class="card w-50 text-center mx-auto" id="card-conta-form">
				<h5 class="card-header">Adicionar Debito ao Cartão</h5>
				<div class="card-body">
					<form action="DebitoCartaoController" method="post">

						<div class="row">
							<div class="col">
								<label for="descricaoDebito">Debito:</label> <input
									type="hidden" id="id" name="idCartao" value="${cartao.id}">
								<input id="descricaoDebito" name="descricaoDebito" type="text"
									class="form-control" placeholder="Insira o debito">
							</div>
							<div class="col">
								<label for="valorDebito">Valor:</label> <input id="valorDebito"
									name="valorDebito" type="text" class="form-control"
									placeholder="Insira o valor">
							</div>

							<div class="col">
								<label for="dataDebito">Data:</label> <input id="dataDebito"
									name="dataDebito" type="date" class="form-control">
							</div>

						</div>

						<div class="my-2">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<label class="input-group-text" for="inputGroupSelect01">Categoria</label>
								</div>
								<select class="custom-select" id="categoria" name="categoria">
									<option selected>Selecione a Categoria</option>
									<c:forEach var="categoria" items="${categoriasList}">
										<option value="${categoria.nome}">${categoria.nome}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div>
							<button type="submit" class="btn btn-primary">Salvar</button>
							<a href="index.jsp"><button type="button"
									class="btn btn-secondary">Cancelar</button></a>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
