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
			
			<h1 style="text-align: center">Conta: ${conta.nome}</h1>
			<h2 style="text-align: center">Seu Saldo Total : R$
				${conta.saldo}</h2>
		</div>

		<div class="col-md-6 my-5">
			 <div>
			 <img alt="Progress chart" src="/Mycash/GraficoContaController?id=${conta.id}">
			 </div>
		</div>

	</div>

	<div class="row my-5">
		<div class="col-md-6">
			<h4 style="text-align: center">Seus Debitos</h4>
			<form action="DebitoController" method="post">

				<table class="table table-bordered mx-3 my-3">
					<thead class="thead-light">
						<tr>
							<th scope="col">Nome</th>
							<th scope="col">Categoria</th>
							<th scope="col">Valor</th>
							<th scope="col">Data</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="debitoList" items="${debitoList}">
							<tr id="${debitoList.id}">
								<td>${debitoList.descricao}</td>
								<td>${debitoList.nomeCategoria()}</td>
								<td>R$ ${debitoList.valor}</td>
								<td>${debitoList.mostrarDate()}</td>
								<td><a
									href="/Mycash/DebitoContaController?q=incluirDebito&id=${conta.id}">
										<button id="adicionarDebido" type="button"
											class="btn btn-primary btn-md">
											<i class="fas fa-minus"></i>
										</button>
								</a> <a
									href="/Mycash/DebitoContaController?q=editar&id=${debitoList.id}&idConta=${conta.id}">
										<button type="button" class="btn btn-primary btn-md">
											<i class="far fa-edit"></i>
										</button>
								</a> <a
									href="/Mycash/DebitoContaController?q=excluir&id=${debitoList.id}&idConta=${conta.id}">
										<button type="button" class="btn btn-primary btn-md">
											<i class="fas fa-trash-alt"></i>
										</button>
								</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</form>
		</div>

		<div class="col-md-6">
			<h4 style="text-align: center">Suas Receitas</h4>
			<form action="ReceitaController" method="post">
				<table class="table table-bordered mx-3 my-3">
					<thead class="thead-light">
						<tr>
							<th scope="col">Descrição</th>
							<th scope="col">Valor</th>
							<th scope="col">Data</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="receitaList" items="${receitaList}">
							<tr>
								<td>${receitaList.descricao}</td>
								<td>R$ ${receitaList.valorReceita}</td>
								<td>${receitaList.mostrarDate()}</td>
								<td><a
									href="/Mycash/ReceitaController?q=incluirReceita&id=${conta.id}"><button
											id="botaoAdicionarReceita" type="button"
											class="btn btn-primary btn-md">
											<i class="fas fa-dollar-sign"></i>
										</button></a> <a
									href="/Mycash/ReceitaController?q=editar&id=${receitaList.id}&idConta=${conta.id}">
										<button type="button" class="btn btn-primary btn-md">
											<i class="far fa-edit"></i>
										</button>
								</a> <a
									href="/Mycash/ReceitaController?q=excluirDaConta&id=${receitaList.id}&idConta=${conta.id}"><button
											type="button" class="btn btn-primary btn-md">
											<i class="fas fa-trash-alt"></i>
										</button></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>

	</div>

</body>
</html>
