package br.com.loov.mycash.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.loov.mycash.dao.ContaDAO;
import br.com.loov.mycash.model.Conta;
import br.com.loov.mycash.relatorio.GeradorRelatorio;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@WebServlet("/RelatorioMensalController")
public class RelatorioMensalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContaDAO dao = new ContaDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		System.out.println(idUsuario);
		// acha jrxml dentro da aplica��o
		ServletContext contexto = request.getServletContext();
		String jasper = contexto.getRealPath("relatorio/relatorioMensal.jasper");
		String jasperSub = contexto.getRealPath("relatorio/");

		// prepara par�metros
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("SUBREPORT_DIR",jasperSub);
		

		// gera relat�rio
		List<Conta> listaConta = dao.listar(idUsuario);
		GeradorRelatorio gerador = new GeradorRelatorio(new JRBeanCollectionDataSource(listaConta));
		gerador.geraPdf(jasper, parametros, response.getOutputStream());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
