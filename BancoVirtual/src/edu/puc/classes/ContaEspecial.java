package edu.puc.classes;

public class ContaEspecial extends Conta {

	private Double limite=0.0;

	public ContaEspecial(Double valor) {
		super.saldo = valor;
	}

	public void setLimite(Double valor) {
		this.limite = valor;
	}

	public Double getLimite() {
		return this.limite;
	}

	@Override
	public Double getSaldo() {
		return super.saldo;
	}

	@Override
	public void setSaldo(Double valor) {
		super.saldo = valor;
	}

	public void deposita(Double valor) {
		super.saldo += valor;
	}

	/* Esse método saca apenas se o saldo e o limite definido pelo gerente for maior que o valor a ser sacado. */
	public void saca(Double valor) throws Exception {

		if ((this.saldo + limite) < valor)
			throw new Exception("Valor maior que saldo disponível!");
		super.saldo -= valor;

	}
}
