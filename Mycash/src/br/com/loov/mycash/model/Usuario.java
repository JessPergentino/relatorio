package br.com.loov.mycash.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private Integer id;
	private String nome;
	private String email;
	private String senha;
	
	private List<Poupanca> poupancas = new ArrayList<>();
	private List<Conta> contas = new ArrayList<>();
	private List<CartaoDeCredito> cartoes = new ArrayList<>();
	private List<Meta> metas = new ArrayList<>();
	
	public Usuario() {

	}

	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public void adicionarMeta(Meta meta) {
		this.metas.add(meta);
	}
	
	public void removerMeta(Meta meta) {
		this.metas.remove(meta);
	}
	
	public void adicionarPoupanca(Poupanca poupanca) {
		this.poupancas.add(poupanca);
	}
	
	public void removerPoupanca(Poupanca poupanca) {
		this.poupancas.remove(poupanca);
	}
	
	public void adicionarConta(Conta conta) {
		this.contas.add(conta);
	}
	
	public void removerConta(Conta conta) {
		this.contas.remove(conta);
	}
	
	public void adicionarCartao(CartaoDeCredito cartao) {
		this.cartoes.add(cartao);
	}
	
	public void removerCartao(CartaoDeCredito cartao) {
		this.cartoes.remove(cartao);
	}

	public List<Poupanca> getPoupancas() {
		return poupancas;
	}

	public void setPoupancas(List<Poupanca> poupancas) {
		this.poupancas = poupancas;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<CartaoDeCredito> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<CartaoDeCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public List<Meta> getMetas() {
		return metas;
	}

	public void setMetas(List<Meta> metas) {
		this.metas = metas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (this.senha == null) {
			if (other.senha != null)
				return false;
		} else if (!this.senha.equals(other.senha))
			return false;
		return true;
	}

}
