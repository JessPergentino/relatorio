<%@page import="br.com.loov.mycash.dao.UsuarioDAO"%>
<%@page import="br.com.loov.mycash.model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loov.mycash.dao.PoupancaDAO"%>
<%@page import="br.com.loov.mycash.model.Poupanca"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<title>MyCash - Metas/Poupanas</title>
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
	
	PoupancaDAO daoPoupanca = new PoupancaDAO();
	List<Poupanca> lista = daoPoupanca.listar(usuario.getId());
		
	%>

	<div class="row mx-5 my-5">
		<div class="col-sm-6">
			<div class="card" id="card-conta">
				<h5 class="card-header">Minhas Metas</h5>
				<div class="card-body">
					<div class="container">
						<div class="row justify-content-md-end">
							<div class="col col-lg-2">

								<button id="novaMeta" type="button"
									class="btn btn-primary btn-md" data-toggle="modal"
									data-target="#modalNovaMeta" formmethod="post"
									formaction="/Mycash/MetaController?q=incluir&id=${m.id}">
									<i class="fas fa-plus"></i>
								</button>

								<!-- Modal Meta -->
								<div class="modal fade bd-example-modal-lg" id="modalNovaMeta"
									tabindex="-1" role="dialog"
									aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered modal-lg"
										role="document">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">

												<!-- Alterar para formulario de inclusao -->
												<c:import url="index.jsp" />

											</div>
										</div>
									</div>
								</div>


							</div>
						</div>
						<div class="row">
							<div class="col">
								<table class="table table-sm table-bordered my-4">
									<thead class="thead-light">
										<tr>
											<th scope="col">Categorias</th>
											<th scope="col">Valor Limite</th>
											<th scope="col">Ações</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="m" items="${metas}">

											<tr id="${m.id }">
												<td>${m.categoria}</td>
												<td>
													<button type="button" class="btn btn-primary btn-md">
														<i class="fas fa-trash-alt"></i>
													</button>


													<button id="alterarMeta" type="button"
														class="btn btn-primary btn-md" data-toggle="modal"
														data-target="#modalAlteracaoMeta" formmethod="post"
														formaction="/Mycash/MetaController?q=editar&id=${m.id}">
														<i class="far fa-edit"></i>
													</button> <!-- Modal Editar Meta -->
													<div class="modal fade bd-example-modal-lg"
														id="modalAlteracaoMeta" tabindex="-1" role="dialog"
														aria-labelledby="exampleModalCenterTitle"
														aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered modal-lg"
															role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																	<!-- Alterar para formulario de alteracao -->
																	<c:import url="index.jsp" />
																</div>
															</div>
														</div>
													</div>

													<button id="infoMeta" type="button"
														class="btn btn-primary btn-md" data-toggle="modal"
														data-target="#infoMeta" formmethod="post"
														formaction="/Mycash/MetaController?q=info&id=${m.id}">
														<i class="fas fa-info-circle"></i>
													</button> <!-- Modal Info Meta -->
													<div class="modal fade bd-example-modal-lg"
														id="infoPoupanca" tabindex="-1" role="dialog"
														aria-labelledby="exampleModalCenterTitle"
														aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered modal-lg"
															role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																	<!-- Alterar para info -->
																	<c:import url="index.jsp" />
																</div>
															</div>
														</div>
													</div>
												</td>
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

		<div class="col-sm-6">
			<div class="card" id="card-poupanca">
				<h5 class="card-header">Minhas Poupanças</h5>
				<div class="card-body">
					<div class="container">
						<div class="row justify-content-md-end">
							<div class="col col-lg-2">

								<a href="FormularioPoupanca.jsp">
									<button id="novaPoupanca" type="button"
										class="btn btn-primary btn-md">
										<i class="fas fa-plus"></i>
									</button>
								</a>

							</div>
						</div>
					</div>

					<div class="col">

						<table class="table table-sm table-bordered my-3">
							<thead class="thead-light">
								<tr>
									<th scope="col">Nome</th>
									<th scope="col">Quanto já Tenho</th>
									<th scope="col">Prazo</th>
									<th scope="col">Ações</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="p" items="<%=lista%>">
									<tr id="${p.id }">
										<td>${p.nome }</td>
										<td>R$ ${p.mostrarDouble(p.valorAtual)}</td>
										<td>${p.mostrarDate()}</td>
										<td>
										
										<a href="/Mycash/PoupancaController?q=incluirValor&id=${p.id}"> <button type="button"
													class="btn btn-primary btn-sm">
													<i class="fas fa-dollar-sign"></i>
										</button></a>
											
											 <a href="/Mycash/PoupancaController?q=excluir&id=${p.id}">
												<button type="button" class="btn btn-primary btn-sm">
													<i class="fas fa-trash-alt"></i>
												</button>
										</a> 
										
										<a href="/Mycash/PoupancaController?q=editar&id=${p.id}">
												<button id="alterarPoupanca" type="button"
													class="btn btn-primary btn-sm">
													<i class="far fa-edit"></i>
												</button>
										</a> 
										
										<a href="/Mycash/PoupancaController?q=info&id=${p.id}" ><button
											type="button" class="btn btn-primary btn-sm"
											>
											<i class="fas fa-info-circle"></i>
										</button> </a> 
					
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
