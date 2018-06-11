package br.com.loov.mycash.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.loov.mycash.model.Poupanca;
import br.com.loov.util.Conexao;

public class PoupancaDAO {

	private Conexao conexao;

	public PoupancaDAO() {
		this.conexao = Conexao.getConexao();
	}

	public Poupanca getByID(int id) {
		Poupanca poupanca = null;
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("select id, nome, prazo, valor_meta, valor from poupanca where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				poupanca = new Poupanca();
				poupanca.setId(rs.getInt(1));
				poupanca.setNome(rs.getString(2));
				poupanca.setPrazo(rs.getDate(3).toLocalDate());
				poupanca.setValorTotal(rs.getDouble(4));
				poupanca.setValorAtual(rs.getDouble(5));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return poupanca;
	}

	public void delete(int id) {
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("delete from poupanca where id=?;");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Poupanca poupanca) {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update poupanca set nome=?, prazo=?, valor_meta=?, valor=? where id=?;");
			ps.setString(1, poupanca.getNome());
			ps.setDate(2, java.sql.Date.valueOf(poupanca.getPrazo()));
			ps.setDouble(3, poupanca.getValorTotal());
			ps.setDouble(4, poupanca.getValorAtual());
			ps.setInt(5, poupanca.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void inserir(Poupanca poupanca, int idUsuario) {
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into poupanca (nome, prazo, valor_meta,id_usuario) values (?, ?, ?,?);");
			ps.setString(1, poupanca.getNome());
			ps.setDate(2, java.sql.Date.valueOf(poupanca.getPrazo()));
			ps.setDouble(3, poupanca.getValorTotal());
			ps.setInt(4, idUsuario);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Poupanca> listar(int idUsuario) throws SQLException {
		Poupanca poupanca;
		List<Poupanca> lista = new ArrayList<>();
		PreparedStatement ps = conexao.getConnection()
				.prepareStatement("select id, nome, prazo, valor_meta, valor from poupanca where id_usuario=?;");
		ps.setInt(1, idUsuario);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			poupanca = new Poupanca();
			poupanca.setId(rs.getInt(1));
			poupanca.setNome(rs.getString(2));
			poupanca.setPrazo(rs.getDate(3).toLocalDate());
			poupanca.setValorTotal(rs.getDouble(4));
			poupanca.setValorAtual(rs.getDouble(5));
			lista.add(poupanca);
		}
		return lista;
	}

	public void close() {
		conexao.closeConnection();
	}
}
