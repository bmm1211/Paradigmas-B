package edu.puc.persist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ClientePersistence {

	/**
	 * Escreve as informações do usuário no arquivo ClienteInfo.txt
	 * Os parâmetros são usuário e senha do tipo string
	 */
	public static void writeClienteToFile(String usuario, String senha) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("ClienteInfo.txt", true));
			out.write("login:" + usuario + " senha:" + encryptsPassword(senha));
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.out.println("Falha ao salvar.");
		}
	}
	
	/**
	 * Coloca um cliente no fim da fila
	 * 
	 * Os parâmetros são usuário e senha do tipo string
	 */
	public static void writeNewClienteToFile(String usuario, String senha) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("ClienteInfo.txt", true));
			out.newLine();
			out.write("login:" + usuario + " senha:" + encryptsPassword(senha));
			out.close();
		} catch (IOException e) {
			System.out.println("Falha ao salvar.");
		}
	}

	/**
	 * Faz a criptografia de uma senha usando sha256, de modo que a senha fica oculta
	 * 
	 * O parâmetro é a senha
	 * Retorna senha criptografada
	 */
	public static String encryptsPassword(String password) {

		try {
			
			MessageDigest digest;
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(password.getBytes("utf8"));
			return String.format("%064x", new BigInteger(1, digest.digest()));
			
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		return (null);
	}

	/**
	 * Verifica se o usuário existe
	 * 
	 * os parâmetros são login e senha do tipo string
	 * 
	 * retorna true se o usuário existe, false se não existe
	 */
	public static boolean checkIfExists(String login, String senha) {

		FileInputStream fstream;
		try {
			fstream = new FileInputStream("ClienteInfo.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			String line = ("login:" + login + " senha:" + encryptsPassword(senha));
			// Lendo o arquivo linha por linha 
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
	


}
