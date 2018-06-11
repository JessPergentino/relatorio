package br.com.loov.mycash.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.loov.mycash.model.Categoria;
import br.com.loov.mycash.model.Conta;
import br.com.loov.mycash.model.Debito;
import br.com.loov.mycash.model.Receita;
import br.com.loov.util.Conexao;

public class ContaDAO {

	private Conexao conexao;

	
	public ContaDAO() {
		this.conexao = Conexao.getConexao();
	}

	public List<Conta> listar(int idUsuario) {
		List<Conta> contas = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,nome,saldo from conta where id_usuario=?;");
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Conta conta = new Conta();
				conta.setId(rs.getInt(1));
				conta.setNome(rs.getString(2));
				conta.setSaldo(rs.getDouble(3));
				
				contas.add(conta);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return contas;
	}

	public void inserir(Conta conta, int idUsuario) {
		try {
			
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into conta (nome,saldo,id_usuario) values (?,?,?);");
			ps.setString(1, conta.getNome());
			ps.setDouble(2, conta.getSaldo());
			ps.setInt(3, idUsuario);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void close() {
		conexao.closeConnection();
	}

	public void delete(int id) {
		try {
			PreparedStatement ps2 = conexao.getConnection().prepareStatement("delete from receita where id_conta=?");
			ps2.setInt(1, id);
			ps2.execute();
			ps2.close();
			
			PreparedStatement ps3 = conexao.getConnection().prepareStatement("delete from debito where id_conta=?");
			ps3.setInt(1, id);
			ps3.execute();
			ps3.close();
			
			PreparedStatement ps = conexao.getConnection().prepareStatement("delete from conta where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Conta conta) {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update conta set nome=? where id =?;");
			ps.setString(1, conta.getNome());
			ps.setInt(2, conta.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public Conta getByID(int id) {
		Conta conta = null;
		Receita receita = null;
		Debito debito = null;
		
		List<Receita> listaReceita = new ArrayList<>();
		List<Debito> listaDebito = new ArrayList<>();
		List<Categoria> categorias = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("select id,nome,saldo from conta where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				conta = new Conta();
				conta.setId(rs.getInt(1));
				conta.setNome(rs.getString(2));
				conta.setSaldo(rs.getDouble(3));
			}
			
			PreparedStatement ps2 = conexao.getConnection().prepareStatement("select id,descricao,valor from receita where id_conta=?;");
			ps2.setInt(1, id);
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				receita = new Receita();
				receita.setId(rs2.getInt(1));
				receita.setDescricao(rs2.getString(2));
				receita.setValorReceita(rs2.getDouble(3));
				
				listaReceita.add(receita);
				
			}
			
			conta.setReceitas(listaReceita);
			
			PreparedStatement ps3 = conexao.getConnection().prepareStatement("select id,decricao,valor,data_debito,id_categoria from debito where id_conta=?;");
			ps3.setInt(1, id);
			ResultSet rs3 = ps3.executeQuery();
			while (rs3.next()) {
				debito = new Debito();
				debito.setId(rs3.getInt(1));
				debito.setDescricao(rs3.getString(2));
				debito.setValor(rs3.getDouble(3));
				debito.setData(rs3.getDate(4).toLocalDate());
				debito.getCategoria().setId(rs3.getInt(5));
				
				listaDebito.add(debito);
				
			}
			
			ps3.close();
			
			PreparedStatement ps4 = conexao.getConnection().prepareStatement("select id, nome from categoria");
			ResultSet rs4 = ps4.executeQuery();
			while (rs4.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs4.getInt(1));
				categoria.setNome(rs4.getString(2));
				categorias.add(categoria);
			}
			ps4.close();

			for (int i = 0; i < listaDebito.size(); i++) {
				for (int j = 0; j < categorias.size(); j++) {
					if (listaDebito.get(i).getCategoria().getId() == categorias.get(j).getId()) {
						listaDebito.get(i).getCategoria().setNome(categorias.get(j).getNome());
						
					}
				}
			}
			
			conta.setDebitos(listaDebito);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conta;
	}

}
