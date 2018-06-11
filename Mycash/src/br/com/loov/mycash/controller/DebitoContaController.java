package br.com.loov.mycash.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.loov.mycash.dao.CategoriaDAO;
import br.com.loov.mycash.dao.ContaDAO;
import br.com.loov.mycash.dao.DebitoDAO;
import br.com.loov.mycash.dao.ReceitaDAO;
import br.com.loov.mycash.model.Categoria;
import br.com.loov.mycash.model.Conta;
import br.com.loov.mycash.model.Debito;

@WebServlet("/DebitoContaController")
public class DebitoContaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Debito debito;
	private DebitoDAO debitoDao = new DebitoDAO();
	private Conta conta;
	private ContaDAO daoConta = new ContaDAO();
	private Categoria categoria;
	private CategoriaDAO daoCategoria = new CategoriaDAO();
	private ReceitaDAO daoReceita = new ReceitaDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String q = request.getParameter("q");
		
		if (q != null && q.equals("incluirDebito")) {
			String idConta = request.getParameter("id");
			
			conta = daoConta.getByID(Integer.parseInt(idConta));
			
			request.setAttribute("conta", conta);
			request.setAttribute("categoriasList",  daoCategoria.listar());
			request.getRequestDispatcher("FormularioDebitoConta.jsp").forward(request, response);;
		}
		

		if (q != null && q.equals("editar")) {
			String id = request.getParameter("id");
			String idConta = request.getParameter("idConta");
			
			conta = daoConta.getByID(Integer.parseInt(idConta));
			debito = debitoDao.getByID(Integer.parseInt(id));
			
			request.setAttribute("conta", conta);
			request.setAttribute("debito", debito);
			request.setAttribute("categoriasList", daoCategoria.listar());
			request.getRequestDispatcher("FormularioAlteracaoDebitoConta.jsp").forward(request, response);
		}
		
		if (q != null && q.equals("excluir")) {
			String id = request.getParameter("id");
			String idConta = request.getParameter("idConta");;
			
			conta = daoConta.getByID(Integer.parseInt(idConta));
			debito = debitoDao.getByID(Integer.parseInt(id));
			
			conta.removerDebito(debito);
			debitoDao.deleteConta(debito, conta);;
			
			request.setAttribute("conta", conta);
			request.setAttribute("receitaList", daoReceita.listar(conta.getId()));
			request.setAttribute("debitoList", debitoDao.listarConta(Integer.parseInt(idConta)));
			request.getRequestDispatcher("DetalheContas.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idDebito = request.getParameter("idDebito");
		String descricao = request.getParameter("descricaoDebito");
		String valor = request.getParameter("valorDebito");
		String nomeCategoria = request.getParameter("categoria");
		String dataString = request.getParameter("dataDebito");
		String idConta = request.getParameter("id");
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataDebito = LocalDate.parse(dataString, dateFormat);
		
		categoria = daoCategoria.getByNome(nomeCategoria);
		
		debito = new Debito(descricao, Double.parseDouble(valor), categoria ,
					dataDebito);

		conta = daoConta.getByID(Integer.parseInt(idConta));
			
		if(idDebito != null && !idDebito.isEmpty()) {
			debito.setId(Integer.parseInt(idDebito));
			conta.alterarDebito(debito);
			debitoDao.updateConta(debito, conta);
				
		}else {
			conta.adicionarDebito(debito);
			debitoDao.inserirConta(debito, conta);
		}
		
		request.setAttribute("conta", conta);
		request.setAttribute("debitoList", debitoDao.listarConta(conta.getId()));
		request.setAttribute("receitaList", daoReceita.listar(conta.getId()));
		request.getRequestDispatcher("DetalheContas.jsp").forward(request, response);
		
	}

}
