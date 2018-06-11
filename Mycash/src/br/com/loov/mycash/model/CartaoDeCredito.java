package br.com.loov.mycash.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CartaoDeCredito {

	private Integer id;
	private String nome;
	private Double limite;
	private LocalDate vencimento;
	private List<Debito> debitos = new ArrayList<>();
	private Double valorTotal = 0.0;

	public CartaoDeCredito() {

	}

	public CartaoDeCredito(String nome, Double limite, LocalDate dataVencimento) {
		this.nome = nome;
		this.limite = limite;
		this.vencimento = dataVencimento;

	}

	public void adicionarDebito(Debito debito) {
		this.limite -= debito.getValor();
		this.valorTotal += debito.getValor();
		debitos.add(debito);
	}

	public void removerDebito(Debito debito) {
		this.limite += debito.getValor();
		this.valorTotal -= debito.getValor();
		debitos.remove(debito);
	}

	public void alterarDebito(Debito d) {
		for (Debito debito : debitos) {
			if (this.debitos.contains(d)) {
				this.limite += debito.getValor();
				this.valorTotal -= debito.getValor();
				debito.setDescricao(d.getDescricao());
				debito.setValor(d.getValor());
				this.limite -= debito.getValor();
				this.valorTotal += debito.getValor();
			}
		}
	}

	public String mostrarDate() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dateFormat.format(this.vencimento);
	}

	public LocalDate getVencimento() {
		return vencimento;
	}

	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

	public Double valorRestante() {
		return this.limite - this.valorTotal;
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

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public List<Debito> getDebitos() {
		return debitos;
	}

	public void setDebitos(List<Debito> debitos) {
		this.debitos = debitos;
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
		CartaoDeCredito other = (CartaoDeCredito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
