package edu.puc.persist;

import static edu.puc.persist.ClientePersistence.encryptsPassword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.puc.classes.Cliente;
import edu.puc.classes.ContaEspecial;
import edu.puc.classes.ContaPoupanca;
import edu.puc.classes.ContaSimples;

public class GerentePersistence {

	/**
	 * 
	 *  Recupera clientes do arquivo e coloca-os em um arrayList
	 *  Retorna o arrayList com todos os clientes
	 */
	public static ArrayList<Cliente> getClientesFromFileToArrayList() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		FileInputStream fstream;
		try {
			fstream = new FileInputStream("ClienteInfo.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;
			// Lê o arquivo linha por linha
			while ((strLine = br.readLine()) != null) {
				// Imprime o conteúdo
				String login = getLogin(strLine);
				String pwd = getSenha(strLine);

				Cliente cliente = new Cliente(login, pwd);
				cliente.getContasEspeciais()
						.add(new ContaEspecial((double) Math.floor(Math.random() * (1000000 - 100 + 1) + 100)));
				cliente.getContasPoupancas()
						.add(new ContaPoupanca((double) Math.floor(Math.random() * (1000000 - 100 + 1) + 100)));
				cliente.getContasSimples()
						.add(new ContaSimples((double) Math.floor(Math.random() * (1000000 - 100 + 1) + 100)));
				clientes.add(cliente);
			}
			br.close();
			System.out.println("Todos os clientes foram instanciados!");
			return clientes;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Extrai somente o login
	 * 
	 * O parâmetro é uma string que contém login e senha divididos por um espaço
	 * Retorna o login
	 */
	public static String getLogin(String login_senha) {

		String login;
		String[] loginSplit = login_senha.split(" ");
		;

		login = loginSplit[0].substring(2);
		return login;
	}

	/**
	 * Extrai somente a senha
	 * 
	 * O parâmetro é uma string que contém login e senha divididos por um espaço
	 * Retorna o login
	 */
	public static String getSenha(String login_senha) {

		String senha;
		String[] loginSplit = login_senha.split(" ");
		;

		senha = loginSplit[1].substring(2);
		return senha;
	}

	/**
	 * Verifica se o gerente existe
	 * 
	 * Os parâmetros são login e senha do tipo string
	 * Retorna true se existe, false se não existe
	 */
	public static boolean checkIfGerenteExists(String login, String senha) {

		FileInputStream fstream;
		try {
			fstream = new FileInputStream("GerenteInfo.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			String line = ("login:" + login + " senha:" + encryptsPassword(senha));
			// Lê o arquivo linha por linha
			while ((strLine = br.readLine()) != null) {
				// Imprime o conteúdo
				System.out.println(strLine + "\n" + line + "\n-------\n");
				if (strLine.equals(line)) {
					System.out.println("Usuário encontrado!");
					br.close();
					return true;

				}
			}
			br.close();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * Adiciona o novo gerente no fim do arquivo
	 * 
	 * Os parâmetros são usuário e senha
	 */
	public static void addNewGerente(String usuario, String senha) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("GerenteInfo.txt", true));
			out.newLine();
			out.write("login:" + usuario + " senha:" + encryptsPassword(senha));
			out.close();
		} catch (IOException e) {
			System.out.println("Falha ao salvar.");
		}
	}
}
