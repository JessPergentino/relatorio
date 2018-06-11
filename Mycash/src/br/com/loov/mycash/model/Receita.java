package br.com.loov.mycash.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Receita {

	private Integer id;
	private String descricao;
	private Double valorReceita;
	private LocalDate data;

	public Receita() {
	
	}

	public Receita(String descricao, Double valor, LocalDate data) {
		this.descricao = descricao;
		this.valorReceita = valor;
		this.data = data;
	}
	
	public Receita(Integer id,String descricao, Double valor, LocalDate data) {
		this.id = id;
		this.descricao = descricao;
		this.valorReceita = valor;
		this.data = data;
	}

	public Receita(Receita r) {
		this.id = r.id;
		this.descricao = r.descricao;
		this.valorReceita = r.valorReceita;
		this.data = r.data;
	}

	public String mostrarDate() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dateFormat.format(this.data);
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

	public void setValorReceita(Double valor) {
		this.valorReceita = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getValorReceita() {
		return valorReceita;
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
		Receita other = (Receita) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
