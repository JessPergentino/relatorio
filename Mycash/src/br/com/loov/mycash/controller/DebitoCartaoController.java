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
import br.com.loov.mycash.dao.CategoriaDAO;
import br.com.loov.mycash.dao.DebitoDAO;
import br.com.loov.mycash.model.CartaoDeCredito;
import br.com.loov.mycash.model.Categoria;
import br.com.loov.mycash.model.Debito;

@WebServlet("/DebitoCartaoController")
public class DebitoCartaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CartaoDeCredito cartao;
	private CartaoDeCreditoDAO cartaoDao = new CartaoDeCreditoDAO();
	private Debito debito;
	private DebitoDAO debitoDao = new DebitoDAO();
	private Categoria categoria;
	private CategoriaDAO daoCategoria = new CategoriaDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String q = request.getParameter("q");

		if (q != null && q.equals("novoDebito")) {
			String id = request.getParameter("id");

			cartao = cartaoDao.getByID(Integer.parseInt(id));

			request.setAttribute("cartao", cartao);
			request.setAttribute("categoriasList", daoCategoria.listar());
			request.getRequestDispatcher("FormularioDebitoCartao.jsp").forward(request, response);
		}

		if (q != null && q.equals("editar")) {
			String id = request.getParameter("id");
			String idCartao = request.getParameter("idCartao");
			
			cartao = cartaoDao.getByID(Integer.parseInt(idCartao));
			debito = debitoDao.getByID(Integer.parseInt(id));

			request.setAttribute("cartao", cartao);
			request.setAttribute("debito", debito);
			request.setAttribute("categoriasList", daoCategoria.listar());
			request.getRequestDispatcher("FormularioAlteracaoDebitoCartao.jsp").forward(request, response);
		}

		if (q != null && q.equals("excluir")) {
			String id = request.getParameter("id");
			String idCartao = request.getParameter("idCartao");
			
			cartao = cartaoDao.getByID(Integer.parseInt(idCartao));
			debito = debitoDao.getByID(Integer.parseInt(id));

			cartao.removerDebito(debito);
			debitoDao.deleteCartao(debito, cartao);
			
			request.setAttribute("cartao", cartao);
			request.setAttribute("debitoList", debitoDao.listarCartao(cartao.getId()));
			request.getRequestDispatcher("DetalheCartao.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idCartao = request.getParameter("idCartao");
		String idDebito = request.getParameter("idDebito");
		String descricao = request.getParameter("descricaoDebito");
		String valor = request.getParameter("valorDebito");
		String nomeCategoria = request.getParameter("categoria");
		String dataString = request.getParameter("dataDebito");

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataVencimento = LocalDate.parse(dataString, dateFormat);

		categoria = daoCategoria.getByNome(nomeCategoria);

		debito = new Debito(descricao, Double.parseDouble(valor), categoria, dataVencimento);

		cartao = cartaoDao.getByID(Integer.parseInt(idCartao));
		
		if (idDebito != null && !idDebito.isEmpty()) {
			debito.setId(Integer.parseInt(idDebito));
			cartao.alterarDebito(debito);
			debitoDao.updateCartao(debito, cartao);
		} else {
			cartao.adicionarDebito(debito);
			debitoDao.inserirCartao(debito, cartao);

		}

		request.setAttribute("cartao", cartao);
		request.setAttribute("debitoList", debitoDao.listarCartao(cartao.getId()));
		request.getRequestDispatcher("DetalheCartao.jsp").forward(request, response);
	}

}
