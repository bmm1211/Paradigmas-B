package edu.puc.classes;

import java.util.ArrayList;
public class Cliente {

	private ArrayList<ContaSimples> contasSimples = new ArrayList<ContaSimples>();
	private ArrayList<ContaPoupanca> contasPoupancas = new ArrayList<ContaPoupanca>();
	private ArrayList<ContaEspecial> contasEspeciais = new ArrayList<ContaEspecial>();

	private String login;
	private String senha;

	public Cliente(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	@Override
	public String toString() {

		return ("login:" + this.login + " senha:" + this.senha);

	}

	// getters & setters

	public ArrayList<ContaSimples> getContasSimples() {
		return contasSimples;
	}

	/* O parâmetro da função é do tipo contasSimples, sendo a conta que deve ser usada */
	public void setContasSimples(ArrayList<ContaSimples> contasSimples) {
		this.contasSimples = contasSimples;
	}

	public ArrayList<ContaPoupanca> getContasPoupancas() {
		return contasPoupancas;
	}

	/* O parâmetro da função é do tipo ContasPoupancas, sendo a conta que deve ser usada */
	public void setContasPoupancas(ArrayList<ContaPoupanca> contasPoupancas) {
		this.contasPoupancas = contasPoupancas;
	}

	public ArrayList<ContaEspecial> getContasEspeciais() {
		return contasEspeciais;
	}

	/* O parâmetro da função é do tipo ContasEspeciais, sendo a conta que deve ser usada */	
	public void setContasEspeciais(ArrayList<ContaEspecial> contasEspeciais) {
		this.contasEspeciais = contasEspeciais;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
