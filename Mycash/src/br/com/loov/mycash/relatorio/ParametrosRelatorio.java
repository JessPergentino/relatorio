package br.com.loov.mycash.relatorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.loov.mycash.model.CartaoDeCredito;
import br.com.loov.mycash.model.Conta;
import br.com.loov.mycash.model.Debito;
import br.com.loov.util.Conexao;

public class ParametrosRelatorio {

	private Conexao conexao;

	public ParametrosRelatorio() {

		this.conexao = Conexao.getConexao();
	}

	public List<Conta> buscarContaUsuario(int idUsuario) {
		List<Conta> lista = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id, nomeconta, saldo from conta where id_usuario=?");
			ps.setInt(1, idUsuario);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Conta conta = new Conta();
				conta.setId(rs.getInt(1));
				conta.setNome(rs.getString(2));
				conta.setSaldo(rs.getDouble(3));
				lista.add(conta);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return lista;
	}

	public List<Debito> buscarDebitosContasMesAtual(int idUsuario, int idConta) {
		List<Debito> listaDebitos = new ArrayList<>();
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select descricao, valor, data_debito from debito\r\n"
							+ "inner join conta on conta.id = debito.id_conta\r\n"
							+ "where id_usuario = ? and Extract(month \r\n" + "\r\n"
							+ "from data_debito) >= Extract(month from ? ::DATE)\r\n"
							+ "and Extract(month from data_debito) <= Extract(month \r\n" + "\r\n" + "from ?::DATE)");
			ps.setInt(1, idUsuario);
			ps.setInt(2, idConta);
			ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			ps.setDate(4, java.sql.Date.valueOf(LocalDate.now()));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Debito debito = new Debito();
				debito.setDescricao(rs.getString(1));
				debito.setValor(rs.getDouble(2));
				debito.setData(rs.getDate(3).toLocalDate());
				listaDebitos.add(debito);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDebitos;
	}

	public List<CartaoDeCredito> buscarCartaoUsuario(int idUsuario) {
		List<CartaoDeCredito> lista = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement(
					"select id, nomecartao,limite,valor_total,vencimento from cartaodecredito where id_usuario = ?");
			ps.setInt(1, idUsuario);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CartaoDeCredito cartao = new CartaoDeCredito();
				cartao.setId(rs.getInt(1));
				cartao.setNome(rs.getString(2));
				cartao.setLimite(rs.getDouble(3));
				cartao.setValorTotal(rs.getDouble(4));
				cartao.setVencimento(rs.getDate(5).toLocalDate());
				lista.add(cartao);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Debito> buscarDebitosCartaoMesAtual(int idUsuario, int idCartao) {
		List<Debito> listaDebitos = new ArrayList<>();
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select descricao, valor, data_debito from debito\r\n"
							+ "inner join cartaodecredito on cartaodecredito.id = \r\n" + "\r\n"
							+ "debito.id_cartao\r\n" + "where id_usuario = ? and cartaodecredito.id = ? and \r\n"
							+ "\r\n" + "Extract(month from data_debito) >= Extract(month from \r\n" + "\r\n"
							+ "?::DATE)\r\n" + "and Extract(month from data_debito) <= Extract(month \r\n" + "\r\n"
							+ "from ?::DATE)");
			ps.setInt(1, idUsuario);
			ps.setInt(2, idCartao);
			ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			ps.setDate(4, java.sql.Date.valueOf(LocalDate.now()));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Debito debito = new Debito();
				debito.setDescricao(rs.getString(1));
				debito.setValor(rs.getDouble(2));
				debito.setData(rs.getDate(3).toLocalDate());
				listaDebitos.add(debito);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDebitos;
	}

	public List<Object> buscarCategoriaContaMesAtual(int idUsuario) {
		List<Object> lista = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select categoria.nome, count(categoria.nome) as quantidade\r\n"
							+ "from categoria\r\n" + "inner join debito on debito.id_categoria = categoria.id\r\n"
							+ "inner join conta on conta.id = debito.id_conta\r\n"
							+ "inner join usuario on usuario.id = conta.id_usuario\r\n"
							+ "where Extract(month from data_debito) >= Extract(month from ?::DATE)\r\n"
							+ "and Extract(month from data_debito) <= Extract(month from ?::DATE) and usuario.id = ?\r\n"
							+ "group by categoria.nome");
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
			ps.setInt(3, idUsuario);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nome = rs.getString(1);
				int qtd = rs.getInt(2);
				lista.add(nome);
				lista.add(qtd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public List<Object> buscarCategoriaCartaoMesAtual(int idUsuario) {
		List<Object> lista = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select categoria.nome, count(categoria.nome) as quantidade\r\n"
							+ "from categoria\r\n" + "inner join debito on debito.id_categoria = categoria.id\r\n"
							+ "inner join cartaodecredito on cartaodecredito.id = debito.id_cartao\r\n"
							+ "inner join usuario on usuario.id = cartaodecredito.id_usuario\r\n"
							+ " where Extract(month from data_debito) >= Extract(month from ?::DATE)\r\n"
							+ "and Extract(month from data_debito) <= Extract(month from ?::DATE) and usuario.id = ?\r\n"
							+ "group by categoria.nome");
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
			ps.setInt(3, idUsuario);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nome = rs.getString(1);
				int qtd = rs.getInt(2);
				lista.add(nome);
				lista.add(qtd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void close() {
		conexao.closeConnection();
	}
}
