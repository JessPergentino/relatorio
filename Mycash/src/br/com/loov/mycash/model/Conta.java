package br.com.loov.mycash.model;

import java.util.ArrayList;
import java.util.List;

public class Conta {
	
	private Integer id;
	private String nome;
	private Double saldo;
	
	private List<Debito> debitos = new ArrayList<>();
	private List<Receita> receitas = new ArrayList<>();
	
	public Conta() {
		
	}
	
	public Conta(String nome) {
		this.nome = nome;
	}
	
	
	public Conta(String nome, Double saldoInicial) {
		this.nome = nome;
		this.saldo = saldoInicial;
	}
	
	public void adicionarReceita(Receita receita) {
		this.saldo += receita.getValorReceita();
		this.receitas.add(receita);
	}
	
	public void alterarReceita(Receita r) {
		for (Receita receita : receitas) {
			if (this.receitas.contains(r)) {
				this.saldo -= receita.getValorReceita();
				receita.setDescricao(r.getDescricao());
				receita.setValorReceita(r.getValorReceita());
				this.saldo += receita.getValorReceita();
			}
		}
		
	}
	
	public void removerReceita(Receita receita) {
		this.saldo -= receita.getValorReceita();
		this.receitas.remove(receita);
	}
		
	public void adicionarDebito(Debito debito) {
		this.saldo -= debito.getValor();
		this.debitos.add(debito);
	}
	
	public void removerDebito (Debito debito) {
		this.saldo += debito.getValor();
		this.debitos.remove(debito);
	}
	
	public void alterarDebito(Debito d) {
		for (Debito debito : debitos) {
			if (this.debitos.contains(d)) {
				this.saldo += debito.getValor();
				debito.setDescricao(d.getDescricao());
				debito.setValor(d.getValor());
				this.saldo -= debito.getValor();
			}
		}
	}
	
	
	public List<Debito> getDebitos() {
		return debitos;
	}

	public void setDebitos(List<Debito> debitos) {
		this.debitos = debitos;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return this.nome + " - " + this.saldo;
	}
}
