<%@page import="br.com.loov.mycash.dao.UsuarioDAO"%>
<%@page import="br.com.loov.mycash.model.Usuario"%>
<%@page import="br.com.loov.mycash.model.CartaoDeCredito"%>
<%@page import="br.com.loov.mycash.dao.ContaDAO"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loov.mycash.model.Conta"%>
<%@page import="br.com.loov.mycash.dao.CartaoDeCreditoDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<title>MyCash - Home</title>
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
		List<Conta> lista = daoConta.listar(usuario.getId());
	%>

	<div class="row mx-5 my-5">
		<div class="col-sm-6">
			<div class="card" id="card-conta">
				<h5 class="card-header">Minhas Contas</h5>
				<div class="card-body">
					<div class="container">
						<div class="row justify-content-md-end">
							<div class="col col-lg-2">
								<a href="FormularioConta.jsp"><button type="button"
										class="btn btn-primary btn-md">
										<i class="fas fa-plus"></i>
									</button></a>
							</div>
						</div>
						<div class="row">
							<div class="col">

								<table class="table table-bordered table-sm mx-2 my-4">
									<thead class="thead-light">
										<tr>
											<th scope="col">Nome</th>
											<th scope="col">Valor</th>
											<th scope="col">Açoes</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="c" items="<%=lista%>">

											<tr id="${c.id}">
												<td>${c.nome}</td>
												<td>R$ ${c.saldo}</td>
												<td><a
													href="/Mycash/ReceitaController?q=incluirReceita&id=${c.id}"><button
															id="botaoAdicionarReceita" type="button"
															class="btn btn-primary btn-sm">
															<i class="fas fa-dollar-sign"></i>
														</button></a> <a
													href="/Mycash/DebitoContaController?q=incluirDebito&id=${c.id}">
														<button id="adicionarDebido" type="button"
															class="btn btn-primary btn-sm">
															<i class="fas fa-minus"></i>
														</button>
												</a> <a href="/Mycash/ContaController?q=editar&id=${c.id}"><button
															id="alterarConta" type="submit"
															class="btn btn-primary btn-sm">
															<i class="far fa-edit"></i>
														</button></a> <a href="/Mycash/ContaController?q=excluir&id=${c.id}"><button
															type="submit" class="btn btn-primary btn-sm">
															<i class="fas fa-trash-alt"></i>
														</button></a> <a href="/Mycash/ContaController?q=detalhe&id=${c.id}"><button
															type="submit" class="btn btn-primary btn-sm">
															<i class="fas fa-chart-pie"></i>
														</button></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%
			
			CartaoDeCreditoDAO daoCartao = new CartaoDeCreditoDAO();
			List<CartaoDeCredito> listaCartao = daoCartao.listar(usuario.getId());
		%>

		<div class="col-sm-6">
			<div class="card" id="card-cartao">
				<h5 class="card-header">Meus Cartões</h5>
				<div class="card-body">
					<form action="CartaoDeCreditoController">
						<div class="container">
							<div class="row justify-content-md-end">
								<div class="col col-lg-2">
									<a href="FormularioCartao.jsp"><button type="button"
											class="btn btn-primary btn-md">
											<i class="fas fa-plus"></i>
										</button> </a>
								</div>
							</div>
							<div class="row">
								<div class="col">

									<table class="table table-bordered table-sm mx-2 my-4">
										<thead class="thead-light">
											<tr>
												<th scope="col">Nome</th>
												<th scope="col">Vencimento</th>
												<th scope="col">Limite Restante</th>
												<th scope="col">Valor Gasto</th>
												<th scope="col">Ações</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach var="cc" items="<%=listaCartao%>">
												<tr id="${cc.id }">

													<td>${cc.nome }</td>
													<td>${cc.mostrarDate()}</td>
													<td>R$ ${cc.limite}</td>
													<td>R$ ${cc.valorTotal}</td>
													<td><a
														href="/Mycash/DebitoCartaoController?q=novoDebito&id=${cc.id}">
															<button type="button" class="btn btn-primary btn-sm">
																<i class="fas fa-minus"></i>
															</button>
													</a> <a
														href="/Mycash/CartaoDeCreditoController?q=editar&id=${cc.id}">
															<button type="button" class="btn btn-primary btn-sm">
																<i class="far fa-edit"></i>
															</button>
													</a> <a
														href="/Mycash/CartaoDeCreditoController?q=excluir&id=${cc.id}">
															<button type="button" class="btn btn-primary btn-sm">
																<i class="fas fa-trash-alt"></i>
															</button>
													</a> <a
														href="/Mycash/CartaoDeCreditoController?q=detalhe&id=${cc.id}">
															<button type="button" class="btn btn-primary btn-sm">
																<i class="fas fa-chart-pie"></i>
															</button>
													</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>