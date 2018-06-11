<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>

	<c:import url="DependenciasJQuery.jsp" />
	<c:import url="Menu.jsp" />


	<div class="row">

		<div class="col-md-6 my-5">
			<h1 style="text-align: center">${cartao.nome }</h1>
			<h2 style="text-align: center">Limite Restante no Cartão: R$ ${cartao.limite}</h2>
		</div>

		<div class="col-md-6 my-5">
			 <div>
			 <img alt="Progress chart" src="/Mycash/GraficoCartaoController?id=${cartao.id}">
			 </div>
		</div>

	</div>

	<div class="row my-5">
		<div class="col">
			<h4 style="text-align: center">Sua Fatura - Data de Vencimento:
				${cartao.mostrarDate()}</h4>
			<form action="DebitoCartaoController" method="post">

				<table class="table table-bordered w-75 p-3 mx-auto my-4">
					<thead class="thead-light">
						<tr>
							<th scope="col">Nome</th>
							<th scope="col">Categoria</th>
							<th scope="col">Valor</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="debito" items="${debitoList}">
							<tr id="${debito.id}">
								<td>${debito.descricao}</td>
								<td>${debito.nomeCategoria()}</td>
								<td>${debito.valor}</td>
								<td>
								
								<a
									href="/Mycash/DebitoCartaoController?q=novoDebito&id=${cartao.id}">
										<button id="adicionarDebido" type="button"
											class="btn btn-primary btn-md">
											<i class="fas fa-minus"></i>
										</button>
								</a>
							
								<a
									href="/Mycash/DebitoCartaoController?q=editar&id=${debito.id}&idCartao=${cartao.id}"><button
											type="button" class="btn btn-primary btn-md">
											<i class="far fa-edit"></i>
										</button></a> <a
									href="/Mycash/DebitoCartaoController?q=excluir&id=${debito.id}&idCartao=${cartao.id}"><button
											type="button" class="btn btn-primary btn-md">
											<i class="fas fa-trash-alt"></i>
										</button></a></td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="3">Valor Total da Fatura</td>
							<td>R$ ${cartao.valorTotal}
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
