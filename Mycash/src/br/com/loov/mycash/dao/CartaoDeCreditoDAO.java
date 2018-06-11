package br.com.loov.mycash.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.loov.mycash.model.CartaoDeCredito;
import br.com.loov.mycash.model.Categoria;
import br.com.loov.mycash.model.Debito;
import br.com.loov.util.Conexao;

public class CartaoDeCreditoDAO {

	private Conexao conexao;

	public CartaoDeCreditoDAO() {
		this.conexao = Conexao.getConexao();
	}

	public List<CartaoDeCredito> listar(int idUsuario) {
		List<CartaoDeCredito> cartoes = new ArrayList<>();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,nome,limite,vencimento,valor_total from cartaodecredito where id_usuario=?;");
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CartaoDeCredito cdc = new CartaoDeCredito();
				cdc.setId(rs.getInt(1));
				cdc.setNome(rs.getString(2));
				cdc.setLimite(rs.getDouble(3));
				cdc.setVencimento(rs.getDate(4).toLocalDate());
				cdc.setValorTotal(rs.getDouble(5));
				cartoes.add(cdc);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartoes;
	}

	public void inserir(CartaoDeCredito cartao, int idUsuario) {
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into cartaodecredito (nome,limite,vencimento, id_usuario) values (?,?,?,?);");
			ps.setString(1, cartao.getNome());
			ps.setDouble(2, cartao.getLimite());
			ps.setDate(3, java.sql.Date.valueOf(cartao.getVencimento()));
			ps.setInt(4, idUsuario);
			ps.execute();
			ps.close();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public CartaoDeCredito getByID(int id) {
		CartaoDeCredito cdc = null;
		Debito debito = null;
		List<Debito> listaDebito = new ArrayList<>();
		List<Categoria> categorias = new ArrayList<>();
		
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id,nome,limite, valor_total, vencimento from cartaodecredito where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cdc = new CartaoDeCredito();
				cdc.setId(rs.getInt(1));
				cdc.setNome(rs.getString(2));
				cdc.setLimite(rs.getDouble(3));
				cdc.setValorTotal(rs.getDouble(4));
				cdc.setVencimento(rs.getDate(5).toLocalDate());
			}
			
			

			PreparedStatement ps3 = conexao.getConnection().prepareStatement("select id,decricao,valor,data_debito,id_categoria from debito where id_cartao=?;");
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
			
			cdc.setDebitos(listaDebito);
			
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
			
			cdc.setDebitos(listaDebito);
			
			ps.close();
			ps3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cdc;
	}

	public void close() {
		conexao.closeConnection();
	}

	public void delete(int id) {
		PreparedStatement ps;
		try {
			
			PreparedStatement ps2 = conexao.getConnection().prepareStatement("delete from debito where id_cartao=?");
			ps2.setInt(1, id);
			ps2.execute();
			ps2.close();
			
			ps = conexao.getConnection().prepareStatement("delete from cartaodecredito where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(CartaoDeCredito cartao) {
		try {

			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update cartaodecredito set nome=?,limite =?, vencimento=? where id =?;");
			ps.setString(1, cartao.getNome());
			ps.setDouble(2, cartao.getLimite());
			ps.setDate(3, java.sql.Date.valueOf(cartao.getVencimento()));
			ps.setInt(4, cartao.getId());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
