package br.com.loov.mycash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.loov.mycash.dao.UsuarioDAO;
import br.com.loov.mycash.model.Usuario;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private UsuarioDAO daoUsuario = new UsuarioDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String q = request.getParameter("q");

		if (q != null && q.equals("excluir")) {
			String id = request.getParameter("id");
			daoUsuario.deletar(Integer.parseInt(id));
			request.getSession().invalidate();
			request.getRequestDispatcher("Cadastro.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUsuario = request.getParameter("id");
		String nome = request.getParameter("nomeUsuario");
		String email = request.getParameter("emailUsuario");
		String senha = request.getParameter("senhaUsuario");
		String senhaConf = request.getParameter("senhaConfirUsuario");

		if (idUsuario != null && !idUsuario.isEmpty() && senha.equals(senhaConf)) {
			if ((senha.isEmpty() || senha == null) && (senha.isEmpty() || senha == null) ) {
				usuario = daoUsuario.getByEmail(email);
				senha = usuario.getSenha();
			}
			usuario = new Usuario(nome, email, senha);
			usuario.setId(Integer.parseInt(idUsuario));
			daoUsuario.alterar(usuario);
			request.getSession().setAttribute("usuario", usuario);
		
		}else {
				usuario = new Usuario(nome, email, senha);
				daoUsuario.inserir(usuario);
				request.getSession().setAttribute("usuario", usuario);
				
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response); 
	}

}
