package br.com.loov.mycash.controller;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.loov.mycash.dao.ContaDAO;
import br.com.loov.mycash.dao.DebitoDAO;
import br.com.loov.mycash.dao.ReceitaDAO;
import br.com.loov.mycash.model.Conta;
import br.com.loov.mycash.model.Receita;

@WebServlet("/ReceitaController")
public class ReceitaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Conta conta;
	private ContaDAO daoConta = new ContaDAO();
	private Receita receita;
	private ReceitaDAO daoReceita = new ReceitaDAO();
	private DebitoDAO daoDebito = new DebitoDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String q = request.getParameter("q");

		if (q != null && q.equals("incluirReceita")) {
			String id = request.getParameter("id");
			
			conta = daoConta.getByID(Integer.parseInt(id));
			
			request.setAttribute("conta", conta);
			request.getRequestDispatcher("FormularioReceita.jsp").forward(request, response);
		}

		if (q != null && q.equals("editar")) {
			String id = request.getParameter("id");
			String idConta = request.getParameter("idConta");
			
			conta = daoConta.getByID(Integer.parseInt(idConta));
			receita = daoReceita.getByID(Integer.parseInt(id));
			
			request.setAttribute("conta", conta);
			request.setAttribute("receita", receita);
			request.getRequestDispatcher("FormularioAlteracaoReceita.jsp").forward(request, response);
		}

		if (q != null && q.equals("excluirDaConta")) {
			String id = request.getParameter("id");
			String idConta = request.getParameter("idConta");
			
			conta = daoConta.getByID(Integer.parseInt(idConta));
			receita = daoReceita.getByID(Integer.parseInt(id));
			conta.removerReceita(receita);
			daoReceita.delete(receita, conta);

			request.setAttribute("conta", conta);
			request.setAttribute("receitaList", daoReceita.listar(conta.getId()));
			request.setAttribute("debitoList", daoDebito.listarConta(conta.getId()));
			request.getRequestDispatcher("DetalheContas.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idConta = request.getParameter("idConta");
		String idReceita = request.getParameter("idReceita");
		String descricaoReceita = request.getParameter("descricaoReceita");
		String valorReceita = request.getParameter("valorReceita");
		String dataString = request.getParameter("dataReceita");

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataReceita = LocalDate.parse(dataString, dateFormat);
		
		receita = new Receita(descricaoReceita, Double.parseDouble(valorReceita), dataReceita);
		
		conta = daoConta.getByID(Integer.parseInt(idConta));
		
		if (idReceita != null && !idReceita.isEmpty()) {
			receita.setId(Integer.parseInt(idReceita));
			conta.alterarReceita(receita);
			daoReceita.update(receita, conta);
			
		} else {
			conta.adicionarReceita(receita);
			daoReceita.inserir(receita, conta);

		}

		request.setAttribute("conta", conta);
		request.setAttribute("debitoList", daoDebito.listarConta(conta.getId()));
		request.setAttribute("receitaList", daoReceita.listar(conta.getId()));
		request.getRequestDispatcher("DetalheContas.jsp").forward(request, response);

	}

}
