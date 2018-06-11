package br.com.loov.mycash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.loov.mycash.dao.ContaDAO;
import br.com.loov.mycash.dao.DebitoDAO;
import br.com.loov.mycash.dao.ReceitaDAO;
import br.com.loov.mycash.dao.UsuarioDAO;
import br.com.loov.mycash.model.Conta;
import br.com.loov.mycash.model.Usuario;

@WebServlet("/ContaController")
public class ContaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Conta conta;
	private ContaDAO dao = new ContaDAO();
	private DebitoDAO debitoDao = new DebitoDAO();
	private ReceitaDAO receitaDao = new ReceitaDAO();
	private UsuarioDAO daoUsuario = new UsuarioDAO();
	private Usuario usuario;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String q = request.getParameter("q");

		if (q != null && q.equals("editar")) {
			String id = request.getParameter("id");
			Conta conta = dao.getByID(Integer.parseInt(id));
			request.setAttribute("conta", conta);
			request.getRequestDispatcher("FormularioAlteracaoConta.jsp").forward(request, response);
		}

		if (q != null && q.equals("excluir")) {
			String id = request.getParameter("id");
			dao.delete(Integer.parseInt(id));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

		if (q != null && q.equals("detalhe")) {
			String id = request.getParameter("id");
			conta = dao.getByID(Integer.parseInt(id));
			request.setAttribute("conta", conta);
			request.setAttribute("receitaList", receitaDao.listar(conta.getId()));
			request.setAttribute("debitoList", debitoDao.listarConta(conta.getId()));
			request.getRequestDispatcher("DetalheContas.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String nome = request.getParameter("nomeConta");
		String saldoInicial = request.getParameter("saldoInicial");
		
		Usuario usuarioSession = (Usuario) request.getSession().getAttribute("usuario");
		usuario = daoUsuario.getByEmail(usuarioSession.getEmail());
		

		if (id != null && !id.isEmpty()) {
			conta = new Conta(nome);
			conta.setId(Integer.parseInt(id));
			dao.update(conta);

		} else {
			conta = new Conta(nome, Double.parseDouble(saldoInicial));
			dao.inserir(conta, usuario.getId());

		}

		request.setAttribute("contas", dao.listar(usuario.getId()));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
