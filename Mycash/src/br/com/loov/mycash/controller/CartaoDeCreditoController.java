package br.com.loov.mycash.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.loov.mycash.dao.CartaoDeCreditoDAO;
import br.com.loov.mycash.dao.UsuarioDAO;
import br.com.loov.mycash.model.CartaoDeCredito;
import br.com.loov.mycash.model.Usuario;

@WebServlet("/CartaoDeCreditoController")
public class CartaoDeCreditoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CartaoDeCredito cartao;
	private CartaoDeCreditoDAO daoCartao = new CartaoDeCreditoDAO();
	private Usuario usuario;
	private UsuarioDAO daoUsuario = new UsuarioDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String q = request.getParameter("q");

		if (q != null && q.equals("editar")) {
			String id = request.getParameter("id");
			cartao = daoCartao.getByID(Integer.parseInt(id));

			request.setAttribute("cartao", cartao);
			request.getRequestDispatcher("FormularioAlteracaoCartao.jsp").forward(request, response);
		}

		if (q != null && q.equals("excluir")) {
			String id = request.getParameter("id");
			daoCartao.delete(Integer.parseInt(id));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

		if (q != null && q.equals("detalhe")) {
			String id = request.getParameter("id");
			cartao = daoCartao.getByID(Integer.parseInt(id));
			request.setAttribute("cartao", cartao);
			request.setAttribute("debitoList", cartao.getDebitos());
			request.getRequestDispatcher("DetalheCartao.jsp").forward(request, response);
		
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String nome = request.getParameter("nomeCartao");
		String limite = request.getParameter("limiteCartao");
		String data = request.getParameter("dataVencimento");
		
		Usuario usuarioSession = (Usuario) request.getSession().getAttribute("usuario");
		usuario = daoUsuario.getByEmail(usuarioSession.getEmail());

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataVencimento = LocalDate.parse(data, dateFormat);

		cartao = new CartaoDeCredito(nome, Double.parseDouble(limite), dataVencimento);

		if (id != null && !id.isEmpty()) {
			cartao.setId(Integer.parseInt(id));
			daoCartao.update(cartao);
		} else {
			daoCartao.inserir(cartao,usuario.getId());
		}

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
