package edu.puc.classes;

import java.util.ArrayList;

public class Conta {

	public Double saldo;

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public ArrayList<String> getHistorico() {
		return historico;
	}

	public void setHistorico(ArrayList<String> histórico) {
		this.historico = histórico;
	}

	private ArrayList<String> historico = new ArrayList<String>();
	

}
