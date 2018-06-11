<%@page import="br.com.loov.mycash.dao.UsuarioDAO"%>
<%@page import="br.com.loov.mycash.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<div id="barra-navegacao">
		<nav class="navbar navbar-expand-lg navbar-light bg-dark"> <a
			class="navbar-brand" href="index.jsp" style="color: white">MyCash</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse my-2" id="navbarText">

				<%
					HttpSession ses = request.getSession();
					Usuario usuarioSession = (Usuario) ses.getAttribute("usuario");
					
					UsuarioDAO dao = new UsuarioDAO();
					Usuario usuario = dao.getByEmail(usuarioSession.getEmail());
				%>
				<div class="navbar">
				<a class="nav-link" href="PerfilAlteracao.jsp?usuario=<%=usuario%>" style="color: white"><%=usuario.getNome()%></a> <a
					class="nav-link" href="/Mycash/LogoutController?q=logout" style="color: white">Logout</a>
			</div>
		</div>
		</nav>
		<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a
			class="nav-link active" href="index.jsp">Home</a> <a class="nav-link"
			href="MetasPoupancas.jsp">Metas e Poupanas</a> <a class="nav-link" href="Relatorio.jsp">Relatórios</a>
		</nav>
	</div>


</body>
</html>
