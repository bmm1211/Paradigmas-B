package edu.puc.classes;

public class ContaSimples extends Conta {
	
	
	public ContaSimples(Double valor) {
		this.saldo = valor;
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
		
		if (valor > saldo)
			throw new Exception("Valor maior que saldo disponível!");
		super.saldo -= valor;

	}
}
