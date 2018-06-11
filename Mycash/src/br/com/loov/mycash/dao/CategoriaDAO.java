package br.com.loov.mycash.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.loov.mycash.model.Categoria;
import br.com.loov.util.Conexao;

public class CategoriaDAO {

	private Conexao conexao;

	public CategoriaDAO() {
		this.conexao = Conexao.getConexao();
	}
	
	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<>();
		
		PreparedStatement ps2;
		try {
			ps2 = conexao.getConnection().prepareStatement("select id, nome from categoria");
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Categoria c = new Categoria();
				c.setId(rs2.getInt(1));
				c.setNome(rs2.getString(2));
				categorias.add(c);
			}
			ps2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return categorias;
		
	}
	
	public Categoria getByNome(String nome) {
		Categoria categoria = null;
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("select id, nome from categoria where nome=?");
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				categoria = new Categoria();
				categoria.setId(rs.getInt(1));
				categoria.setNome(rs.getString(2));;
			}

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categoria;
	}
}
