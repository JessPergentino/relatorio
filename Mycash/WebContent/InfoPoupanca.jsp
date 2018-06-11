<%@page import="br.com.loov.mycash.dao.PoupancaDAO"%>
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

	<div class="mx-auto pt-5" style="width: 800px;">
		<div class="card" id="card-conta-form">
			<h5 class="card-header">${p.nome}</h5>
			<div class="card-body">

				<h4>Para Chegar ao Seu Objetivo Você Deve Juntar:</h4>
				<table class="table table-bordered mx-2 my-4">
					<thead>
						<tr>
							<th scope="col">Por Dia</th>
							<th scope="col">Por Semana</th>
							<th scope="col">Por Mês</th>
							<th scope="col">Prazo</th>
							<th scope="col">Total Guardado</th>
							<th scope="col">Restante</th>
						</tr>
					</thead>
					<tbody>

						<tr id="${p.id}">
							<td>R$ ${p.calculoPorDia()}</td>
							<td>R$ ${p.calculoPorSemana()}</td>
							<td>R$ ${p.calculoPorMes()}</td>
							<td>${p.mostrarDate()}</td>
							<td>R$ ${p.mostrarDouble(p.valorAtual)}</td>
							<td>R$ ${p.mostrarDouble(p.restante())}</td>
						</tr>

					</tbody>
				</table>
			</div>

			<div class="mx-auto pb-3" style="width: 100px;">
				<a href="MetasPoupancas.jsp"><button type="button"
						class="btn btn-primary btn-md btn-block">Ok</button></a>
			</div>
		</div>
	</div>
</body>
</html>
