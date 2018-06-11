<%@page import="br.com.loov.util.RelatorioCalculos"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.com.loov.mycash.model.CartaoDeCredito"%>
<%@page import="br.com.loov.mycash.dao.CartaoDeCreditoDAO"%>
<%@page import="br.com.loov.mycash.model.Poupanca"%>
<%@page import="br.com.loov.mycash.dao.PoupancaDAO"%>
<%@page import="br.com.loov.mycash.model.Conta"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loov.mycash.dao.ContaDAO"%>
<%@page import="br.com.loov.mycash.dao.UsuarioDAO"%>
<%@page import="br.com.loov.mycash.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyCash - Relatório</title>
<c:import url="DependenciasBootstrap.jsp" />
</head>
<body>
	<c:import url="DependenciasJQuery.jsp" />
	<c:import url="Menu.jsp" />

	<%
		HttpSession ses = request.getSession();
		Usuario usuarioSession = (Usuario) ses.getAttribute("usuario");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.getByEmail(usuarioSession.getEmail());

		ContaDAO daoConta = new ContaDAO();
		List<Conta> listaConta = daoConta.listar(usuario.getId());

		PoupancaDAO daoPoupanca = new PoupancaDAO();
		List<Poupanca> listaPoupanca = daoPoupanca.listar(usuario.getId());

		CartaoDeCreditoDAO daoCartao = new CartaoDeCreditoDAO();
		List<CartaoDeCredito> listaCartao = daoCartao.listar(usuario.getId());

		RelatorioCalculos calculos = new RelatorioCalculos();
		Double totalPoupanca = calculos.calcularTotalPoupanca(listaPoupanca);
		Double saldoGeral = calculos.calcularSaldoGeral(listaCartao, listaConta);
	%>

	<div class="row mx-5 my-5">
		<div class="col-sm-6 text-center">
			<h2>Resumo</h2>
			<h3>Seu saldo em geral é:</h3>
			<h4>
				R$<%=saldoGeral%></h4>
			<br>
			<h3>Total nas Poupanças:</h3>
			<h4>
				R$<%=totalPoupanca%></h4>
		</div>

		<div class="col-sm-4">
			<table class="table table-bordered table-sm mx-2 my-4">
				<thead class="thead-light">
					<tr class="text-center">
						<th scope="col">Relatórios</th>
						<th scope="col">Download</th>
					</tr>
				</thead>
				<tbody>
					<tr id="" class="text-center">
						<td>Mês Atual</td>
						<td><a href="/Mycash/RelatorioMensalController?idUsuario=<%=usuario.getId()%>"><button type="button" class="btn btn-primary btn-sm">
								<i class="fas fa-download"></i>
							</button></a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="row mx-5 my-5">

		<div class="col-sm-4">
			<table class="table table-bordered table-sm mx-2 my-4">
				<thead class="thead-light">
					<tr>
						<th scope="col">Contas</th>
						<th scope="col">Saldos</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" items="<%=listaConta%>">

						<tr id="${c.id}">
							<td>${c.nome }</td>
							<td>${c.saldo}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="col-sm-4">
			<table class="table table-bordered table-sm mx-2 my-4">
				<thead class="thead-light">
					<tr>
						<th scope="col">Poupanças</th>
						<th scope="col">Valor</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="<%=listaPoupanca%>">

						<tr id="${p.id}">
							<td>${p.nome}</td>
							<td>R$ ${p.mostrarDouble(p.valorAtual)}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="col-sm-4">
			<table class="table table-bordered table-sm mx-2 my-4">
				<thead class="thead-light">
					<tr>
						<th scope="col">Cartões</th>
						<th scope="col">Limite Restante</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cc" items="<%=listaCartao%>">

						<tr id="${cc.id}">
							<td>${cc.nome }</td>
							<td>R$ ${cc.limite}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>