package br.com.loov.mycash.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Debito {

	private Integer id;
	private String descricao;
	private Double valor;
	private Categoria categoria = new Categoria();
	private LocalDate data;

	public Debito() {

	}

	public Debito(String descricao, Double valor, Categoria categoria, LocalDate data) {
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.data = data;

	}

	public Debito(Debito debito) {
		this.id = debito.id;
		this.descricao = debito.descricao;
		this.valor = debito.valor;
		this.categoria = debito.categoria;
		this.data = debito.data;
	}
	
	public String nomeCategoria() {
		return this.categoria.getNome();
	}
	
	public String mostrarDate() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dateFormat.format(this.data);
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Debito other = (Debito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
