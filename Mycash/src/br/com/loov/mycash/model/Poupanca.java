package br.com.loov.mycash.model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Poupanca {

	private Integer id;
	private String nome;
	private LocalDate prazo;
	private Double valorTotal;
	private Double valorAtual;

	public Poupanca() {

	}

	public Poupanca(String nome, LocalDate prazo, Double valorTotal) {
		this.nome = nome;
		this.prazo = prazo;
		this.valorTotal = valorTotal;
	}

	public Poupanca(String nome, LocalDate prazo, Double valorTotal, Double valorAtual) {
		this.nome = nome;
		this.prazo = prazo;
		this.valorTotal = valorTotal;
		this.valorAtual = valorAtual;
	}

	public void adicionarValor(Double valor) {
		this.valorAtual += valor;
	}

	public String calculoPorDia() {
		LocalDate dateNow = LocalDate.now();

		long diferencaEmDias = ChronoUnit.DAYS.between(dateNow, this.prazo);

		if (diferencaEmDias == 0) {
			return mostrarDouble(restante());
		} else {
			Double resultDias = restante() / diferencaEmDias;
			return mostrarDouble(resultDias);
		}

	}

	public String calculoPorSemana() {
		LocalDate dateNow = LocalDate.now();

		long diferencaEmSemanas = ChronoUnit.WEEKS.between(dateNow, this.prazo);

		if (diferencaEmSemanas == 0) {
			return mostrarDouble(restante());
		} else {
			Double resultSemana = restante() / diferencaEmSemanas;
			return mostrarDouble(resultSemana);
		}

	}

	public String calculoPorMes() {
		LocalDate dateNow = LocalDate.now();

		long diferencaEmMeses = ChronoUnit.MONTHS.between(dateNow, this.prazo);

		if (diferencaEmMeses == 0) {
			return mostrarDouble(restante());
		} else {
			Double resultMes = restante() / diferencaEmMeses;
			return mostrarDouble(resultMes);
		}

	}

	public Double restante() {
		return this.valorTotal - this.valorAtual;

	}
	
	public String mostrarDate() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dateFormat.format(this.prazo);
	}
	
	public String mostrarDouble(Double valor) {
		DecimalFormat df = new DecimalFormat("0.##");
		String dx = df.format(valor);
		return dx;
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

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(Double valorAtual) {
		this.valorAtual = valorAtual;
	}

}
