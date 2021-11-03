package edu.puc.classes;

import java.util.ArrayList;
import static edu.puc.persist.GerentePersistence.getClientesFromFileToArrayList;

public class Gerente {
	
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public Gerente(){
		this.clientes = getClientesFromFileToArrayList();
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
}
