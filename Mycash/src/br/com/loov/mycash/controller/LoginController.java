package br.com.loov.mycash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.loov.mycash.dao.UsuarioDAO;
import br.com.loov.mycash.model.Usuario;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO daoUsuario = new UsuarioDAO();
	private Usuario usuario;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("emailUsuario");
		String senha = request.getParameter("senhaUsuario");

		usuario = daoUsuario.getByEmail(email);
		
		if (((email!=null) && (senha!=null)) && (email.equals(usuario.getEmail())) && (senha.equals(usuario.getSenha()))) {
			System.out.println("LOGOU");
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect("index.jsp");
		} else {
			System.out.println("NÃO LOGOU");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
