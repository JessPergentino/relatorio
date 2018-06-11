package br.com.loov.mycash.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.loov.mycash.dao.PoupancaDAO;
import br.com.loov.mycash.dao.UsuarioDAO;
import br.com.loov.mycash.model.Poupanca;
import br.com.loov.mycash.model.Usuario;

@WebServlet("/PoupancaController")
public class PoupancaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PoupancaDAO dao = new PoupancaDAO();
	private Poupanca poupanca;
	private Usuario usuario;
	private UsuarioDAO daoUsuario = new UsuarioDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String q = request.getParameter("q");

		if (q != null && q.equals("incluirValor")) {
			String id = request.getParameter("id");
			poupanca = dao.getByID(Integer.parseInt(id));
			request.setAttribute("poupanca", poupanca);
			request.getRequestDispatcher("FormularioPoupancaAdicionarValor.jsp").forward(request, response);
		}

		if (q != null && q.equals("editar")) {
			String id = request.getParameter("id");
			poupanca = dao.getByID(Integer.parseInt(id));
			request.setAttribute("poupanca", poupanca);
			request.getRequestDispatcher("FormularioAlteracaoPoupanca.jsp").forward(request, response);
		}

		if (q != null && q.equals("excluir")) {
			String id = request.getParameter("id");
			dao.delete(Integer.parseInt(id));
			request.getRequestDispatcher("MetasPoupancas.jsp").forward(request, response);
		}

		if (q != null && q.equals("info")) {
			String id = request.getParameter("id");
			poupanca = dao.getByID(Integer.parseInt(id));
			request.setAttribute("p", poupanca);
			request.getRequestDispatcher("InfoPoupanca.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("idPoupanca");
		String nomePoupanca = request.getParameter("nomePoupanca");
		String valorTotal = request.getParameter("valorTotal");
		String prazo = request.getParameter("prazoPoupanca");
		String valorAtual = request.getParameter("valorAtual");
		String valorAdicionado = request.getParameter("valorAdicionado");
	
		Usuario usuarioSession = (Usuario) request.getSession().getAttribute("usuario");
		usuario = daoUsuario.getByEmail(usuarioSession.getEmail());
		
		if (id != null && !id.isEmpty()) {

			if (valorAdicionado != null && !valorAdicionado.isEmpty()) {
				poupanca = dao.getByID(Integer.parseInt(id));
				poupanca.setId(Integer.parseInt(id));
				poupanca.adicionarValor(Double.parseDouble(valorAdicionado));
				dao.update(poupanca);
			} else {
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate data = LocalDate.parse(prazo, dateFormat);

				poupanca = new Poupanca(nomePoupanca, data, Double.parseDouble(valorTotal),
						Double.parseDouble(valorAtual));
				poupanca.setId(Integer.parseInt(id));
				dao.update(poupanca);
			}

		} else {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate data = LocalDate.parse(prazo, dateFormat);

			poupanca = new Poupanca(nomePoupanca, data, Double.parseDouble(valorTotal));
			dao.inserir(poupanca, usuario.getId());
		}

		try {
			request.setAttribute("poupancas", dao.listar(usuario.getId()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("MetasPoupancas.jsp").forward(request, response);
	}

}
