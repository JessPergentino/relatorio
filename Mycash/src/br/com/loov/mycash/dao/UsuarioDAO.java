package br.com.loov.mycash.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.loov.mycash.model.Usuario;
import br.com.loov.util.Conexao;

public class UsuarioDAO {
	private Conexao conexao;

	public UsuarioDAO() {
		this.conexao = Conexao.getConexao();
	}

	public void close() {
		conexao.closeConnection();
	}

	public void inserir(Usuario usuario) {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into usuario (nome,email,senha) values (?,?,?);");
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletar(int idUsuario) {
		int idConta = 0;
		int idCartao = 0;

		try {

			PreparedStatement ps1 = conexao.getConnection().prepareStatement("select id from conta where id_usuario=?");
			ps1.setInt(1, idUsuario);
			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
				idConta = rs1.getInt(1);
			}
			ps1.close();
			rs1.close();

			PreparedStatement ps2 = conexao.getConnection().prepareStatement("delete from debito where id_conta=?");
			ps2.setInt(1, idConta);
			ps2.execute();
			ps2.close();

			PreparedStatement ps3 = conexao.getConnection().prepareStatement("delete from receita where id_conta=?");
			ps3.setInt(1, idConta);
			ps3.execute();
			ps3.close();

			PreparedStatement ps4 = conexao.getConnection().prepareStatement("delete from conta where id_usuario=?");
			ps4.setInt(1, idUsuario);
			ps4.execute();
			ps4.close();

			PreparedStatement ps5 = conexao.getConnection()
					.prepareStatement("select id from cartaodecredito where id_usuario=?");
			ps5.setInt(1, idUsuario);
			ResultSet rs2 = ps5.executeQuery();
			if (rs2.next()) {
				idCartao = rs2.getInt(1);
			}
			ps5.close();
			rs2.close();

			PreparedStatement ps6 = conexao.getConnection().prepareStatement("delete from debito where id_cartao=?");
			ps6.setInt(1, idCartao);
			ps6.execute();
			ps6.close();
			
			PreparedStatement ps7 = conexao.getConnection()
					.prepareStatement("delete from cartaodecredito where id_usuario=?");
			ps7.setInt(1, idUsuario);
			ps7.execute();
			ps7.close();

			PreparedStatement ps8 = conexao.getConnection().prepareStatement("delete from poupanca where id_usuario=?");
			ps8.setInt(1, idUsuario);
			ps8.execute();
			ps8.close();

			PreparedStatement ps9 = conexao.getConnection().prepareStatement("delete from meta where id_usuario=?");
			ps9.setInt(1, idUsuario);
			ps9.execute();
			ps9.close();

			PreparedStatement ps = conexao.getConnection().prepareStatement("delete from usuario where id=?");
			ps.setInt(1, idUsuario);
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Usuario getById(int id) {
		Usuario usuario = new Usuario();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id, nome, email, senha from usuario where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setSenha(rs.getString(4));
			}

			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}
	
	public void alterar(Usuario usuario) {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update usuario set nome=?, email=?, senha=? where id=?");
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Usuario getByEmail(String email) {
		Usuario usuario = new Usuario();
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("select id, nome, email, senha from usuario where email=?");
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setSenha(rs.getString(4));
			}

			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}
}
