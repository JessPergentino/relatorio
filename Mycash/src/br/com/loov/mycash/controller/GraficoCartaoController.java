package br.com.loov.mycash.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import br.com.loov.mycash.dao.CartaoDeCreditoDAO;
import br.com.loov.mycash.dao.CategoriaDAO;
import br.com.loov.mycash.dao.DebitoDAO;
import br.com.loov.mycash.model.CartaoDeCredito;
import br.com.loov.mycash.model.Categoria;
import br.com.loov.mycash.model.Debito;

@WebServlet("/GraficoCartaoController")
public class GraficoCartaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static CartaoDeCreditoDAO cartaoDao = new CartaoDeCreditoDAO();
	private static CartaoDeCredito cartao;
	private static CategoriaDAO categoriaDao = new CategoriaDAO();
	private DebitoDAO debitoDao = new DebitoDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		cartao = cartaoDao.getByID(Integer.parseInt(id));
		doPost(request, response);
	}

	private PieDataset carregarDados() {

		DefaultPieDataset result = new DefaultPieDataset();

		List<Categoria> categorias = categoriaDao.listar();
		List<Debito> debitos = debitoDao.listarCartao(cartao.getId());

		String chave = "";
		Double valor = 0.0;

		for (int i = 0; i < categorias.size(); i++) {
			chave = categorias.get(i).getNome();			
			for (int j = 0; j < debitos.size(); j++) {
				if (categorias.get(i).getNome().equals(debitos.get(j).getCategoria().getNome())) {
					valor += debitos.get(j).getValor();
				}
			}
			result.setValue(chave, valor);
			valor = 0.0;
		}
		return result;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			JFreeChart chart = ChartFactory.createPieChart("Detalhe dos Gastos", carregarDados(), true, true, false);
			if (chart != null) {
				int width = 400;
				int height = 250;
				final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
				response.setContentType("image/png");
				OutputStream out = response.getOutputStream();
				ChartUtilities.writeChartAsPNG(out, chart, width, height, info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
