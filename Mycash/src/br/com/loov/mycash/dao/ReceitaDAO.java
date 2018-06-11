package br.com.loov.mycash.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.loov.mycash.model.Conta;
import br.com.loov.mycash.model.Receita;
import br.com.loov.util.Conexao;

public class ReceitaDAO {

	private Conexao conexao;

	public ReceitaDAO() {
		this.conexao = Conexao.getConexao();
	}

	public List<Receita> listar(int id) {
		List<Receita> receitas = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,descricao,valor, data_receita from receita where id_conta=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Receita r = new Receita();
				r.setId(rs.getInt(1));
				r.setDescricao(rs.getString(2));
				r.setValorReceita(rs.getDouble(3));
				r.setData(rs.getDate(4).toLocalDate());
				receitas.add(r);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return receitas;
	}

	public void inserir(Receita receita, Conta conta) {
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into receita (descricao,valor,id_conta,data_receita) values (?,?,?,?);");

			ps.setString(1, receita.getDescricao());
			ps.setDouble(2, receita.getValorReceita());
			ps.setInt(3, conta.getId());
			ps.setDate(4, java.sql.Date.valueOf(receita.getData()));

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

	public Receita getByID(int id) {
		Receita r = null;
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,descricao,valor,data_receita from receita where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = new Receita();
				r.setId(rs.getInt(1));
				r.setDescricao(rs.getString(2));
				r.setValorReceita(rs.getDouble(3));
				r.setData(rs.getDate(4).toLocalDate());
			}
			
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public void close() {
		conexao.closeConnection();
	}

	public void update(Receita receita, Conta conta) {
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update receita set descricao=?,valor=?,data_receita=? where id =?;");
			
			ps.setString(1, receita.getDescricao());
			ps.setDouble(2, receita.getValorReceita());
			ps.setDate(3, java.sql.Date.valueOf(receita.getData()));
			ps.setInt(4, receita.getId());
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

	public void delete(Receita receita, Conta conta) {
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("delete from receita where id =?;");
			ps.setInt(1, receita.getId());
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

}
