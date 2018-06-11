package br.com.loov.util;

import java.util.List;

import br.com.loov.mycash.model.CartaoDeCredito;
import br.com.loov.mycash.model.Conta;
import br.com.loov.mycash.model.Poupanca;

public class RelatorioCalculos {
	
	public Double calcularTotalPoupanca(List<Poupanca> listaPoupanca) {
		Double totalPoupanca = 0.0;
		for (Poupanca poupanca : listaPoupanca) {
			totalPoupanca += poupanca.getValorAtual();
		}
		return totalPoupanca;
	}
	
	public Double calcularSaldoGeral(List<CartaoDeCredito> listaCartao, List<Conta> listaConta) {
		Double totalCartao = calcularSaldoTotalCartao(listaCartao);
		Double totalConta = calcularSaldoTotalConta(listaConta);
		return totalCartao + totalConta; 
	}
	
	private Double calcularSaldoTotalConta(List<Conta> listaConta) {
		Double totalConta = 0.0;
		for (Conta conta : listaConta) {
			totalConta += conta.getSaldo();
		}
		return totalConta;
	}
	
	private Double calcularSaldoTotalCartao(List<CartaoDeCredito> listaCartao) {
		Double totalCartao = 0.0;
		for (CartaoDeCredito cartao : listaCartao) {
			totalCartao += cartao.getLimite();
		}
		return totalCartao;
	}
	
	
}
