package br.com.loov.mycash.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.loov.mycash.model.CartaoDeCredito;
import br.com.loov.mycash.model.Categoria;
import br.com.loov.mycash.model.Conta;
import br.com.loov.mycash.model.Debito;
import br.com.loov.util.Conexao;

public class DebitoDAO {

	private Conexao conexao;

	public DebitoDAO() {
		this.conexao = Conexao.getConexao();
	}

	public List<Debito> listarConta(int id) {
		List<Debito> debitos = new ArrayList<>();
		List<Categoria> categorias = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,decricao,valor,data_debito,id_categoria from debito where id_conta=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categoria categoria = new Categoria();
				Debito d = new Debito();
				d.setId(rs.getInt(1));
				d.setDescricao(rs.getString(2));
				d.setValor(rs.getDouble(3));
				d.setData(rs.getDate(4).toLocalDate());
				categoria.setId(rs.getInt(5));
				d.setCategoria(categoria);
				debitos.add(d);
			}
			ps.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("select id, nome from categoria");
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Categoria c = new Categoria();
				c.setId(rs2.getInt(1));
				c.setNome(rs2.getString(2));
				categorias.add(c);
			}
			ps2.close();

			for (int i = 0; i < debitos.size(); i++) {
				for (int j = 0; j < categorias.size(); j++) {
					if (debitos.get(i).getCategoria().getId() == categorias.get(j).getId()) {
						debitos.get(i).getCategoria().setNome(categorias.get(j).getNome());
					}
				}
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return debitos;
	}
	
	public List<Debito> listarCartao(int id) {
		List<Debito> debitos = new ArrayList<>();
		List<Categoria> categorias = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,decricao,valor,data_debito,id_categoria from debito where id_cartao=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Debito d = new Debito();
				d.setId(rs.getInt(1));
				d.setDescricao(rs.getString(2));
				d.setValor(rs.getDouble(3));
				d.setData(rs.getDate(4).toLocalDate());
				d.getCategoria().setId(rs.getInt(5));
				debitos.add(d);
			}
			ps.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("select id, nome from categoria");
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Categoria c = new Categoria();
				c.setId(rs2.getInt(1));
				c.setNome(rs2.getString(2));
				categorias.add(c);
			}
			ps2.close();

			for (int i = 0; i < debitos.size(); i++) {
				for (int j = 0; j < categorias.size(); j++) {
					if (debitos.get(i).getCategoria().getId() == categorias.get(j).getId()) {
						debitos.get(i).getCategoria().setNome(categorias.get(j).getNome());
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return debitos;
	}

	public void inserirConta(Debito debito, Conta conta) {
		try {

			PreparedStatement ps = conexao.getConnection().prepareStatement(
					"insert into debito (decricao,valor,data_debito, id_categoria, id_conta) values (?,?,?,?,?);");

			ps.setString(1, debito.getDescricao());
			ps.setDouble(2, debito.getValor());
			ps.setDate(3, java.sql.Date.valueOf(debito.getData()));
			ps.setInt(4, debito.getCategoria().getId());
			ps.setInt(5, conta.getId());

			ps.execute();
			ps.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("update conta set saldo=? where id=?;");

			ps2.setDouble(1, conta.getSaldo());
			ps2.setInt(2, conta.getId());

			ps2.execute();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void inserirCartao(Debito debito, CartaoDeCredito cartao) {
		try {

			PreparedStatement ps = conexao.getConnection().prepareStatement(
					"insert into debito (decricao,valor,data_debito, id_categoria, id_cartao) values (?,?,?,?,?);");

			ps.setString(1, debito.getDescricao());
			ps.setDouble(2, debito.getValor());
			ps.setDate(3, java.sql.Date.valueOf(debito.getData()));
			ps.setInt(4, debito.getCategoria().getId());
			ps.setInt(5, cartao.getId());

			ps.execute();
			ps.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("update cartaodecredito set valor_total=?, limite=? where id=?;");

			ps2.setDouble(1, cartao.getValorTotal());
			ps2.setDouble(2, cartao.getLimite());
			ps2.setInt(3, cartao.getId());

			ps2.execute();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Debito getByID(int id) {
		Debito d = null;
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,decricao,valor,data_debito,id_categoria from debito where id=?");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				d = new Debito();
				d.setId(rs.getInt(1));
				d.setDescricao(rs.getString(2));
				d.setValor(rs.getDouble(3));
				d.setData(rs.getDate(4).toLocalDate());
				d.getCategoria().setId(rs.getInt(5));
			}

			ps.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("select nome from categoria where id=?");

			ps2.setInt(1, d.getCategoria().getId());

			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next()) {
				d.getCategoria().setNome(rs2.getString(1));
			}

			ps2.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public void close() {
		conexao.closeConnection();
	}

	public void deleteConta(Debito debito, Conta conta) {
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("delete from debito where id=?");

			ps.setInt(1, debito.getId());

			ps.execute();
			ps.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("update conta set saldo=? where id=?;");

			ps2.setDouble(1, conta.getSaldo());
			ps2.setInt(2, conta.getId());

			ps2.execute();
			ps2.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public void deleteCartao(Debito debito, CartaoDeCredito cartao) {
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("delete from debito where id=?");

			ps.setInt(1, debito.getId());

			ps.execute();
			ps.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("update cartaodecredito set valor_total=?, limite=? where id=?;");

			ps2.setDouble(1, cartao.getValorTotal());
			ps2.setDouble(2, cartao.getLimite());
			ps2.setInt(3, cartao.getId());

			ps2.execute();
			ps2.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void updateConta(Debito debito, Conta conta) {
		try {

			PreparedStatement ps = conexao.getConnection().prepareStatement(
					"update debito set decricao=?, valor=?, data_debito=?,id_categoria=? where id=?;");

			ps.setString(1, debito.getDescricao());
			ps.setDouble(2, debito.getValor());
			ps.setDate(3, java.sql.Date.valueOf(debito.getData()));
			ps.setInt(4, debito.getCategoria().getId());
			ps.setInt(5, debito.getId());

			ps.execute();
			ps.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("update conta set saldo=? where id=?;");

			ps2.setDouble(1, conta.getSaldo());
			ps2.setInt(2, conta.getId());

			ps2.execute();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void updateCartao(Debito debito, CartaoDeCredito cartao) {
		try {

			PreparedStatement ps = conexao.getConnection().prepareStatement(
					"update debito set decricao=?, valor=?, data_debito=?,id_categoria=? where id=?;");

			ps.setString(1, debito.getDescricao());
			ps.setDouble(2, debito.getValor());
			ps.setDate(3, java.sql.Date.valueOf(debito.getData()));
			ps.setInt(4, debito.getCategoria().getId());
			ps.setInt(5, debito.getId());

			ps.execute();
			ps.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("update cartaodecredito set valor_total=?, limite=? where id=?;");

			ps2.setDouble(1, cartao.getValorTotal());
			ps2.setDouble(2, cartao.getLimite());
			ps2.setInt(3, cartao.getId());

			ps2.execute();
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
