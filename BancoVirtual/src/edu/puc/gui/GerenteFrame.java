package edu.puc.gui;

import static edu.puc.gui.BaseFrame.centreWindow;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.puc.classes.Cliente;
import edu.puc.classes.Gerente;

public class GerenteFrame implements ActionListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel listaDeClientesLabel = new JLabel("Todos os clientes:");
	JTextField clienteTexField = new JTextField(3);

	JLabel selecionarContaLabel = new JLabel("Qual Conta Deseja modificar?");
	JButton alterarContaButton = new JButton("Modificar conta:");
	JButton cadastrarUsuarioButton = new JButton("Cadastrar novo usuário.");
	JButton cadastrarGerenteButton = new JButton("Cadastrar novo gerente.");

	Gerente gerente = new Gerente();

	public GerenteFrame(Gerente gerente) {

		alterarContaButton.addActionListener(this);
		cadastrarGerenteButton.addActionListener(this);
		cadastrarUsuarioButton.addActionListener(this);

		this.gerente = gerente;

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Gerente");
		frame.setSize(700, 400);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));
		frame.getContentPane().add(panel);
		centreWindow(frame);
		panel.add(listaDeClientesLabel);

		frame.setVisible(true);

		int i = 0;
		for (Cliente cliente : gerente.getClientes()) {

			panel.add(new JTextArea(cliente.getLogin() + "(" + i + ")"));
			i++;
		}

		panel.add(alterarContaButton);
		panel.add(clienteTexField);
		panel.add(cadastrarUsuarioButton);
		panel.add(cadastrarGerenteButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == alterarContaButton) {

			new GerenteAlteraClienteFrame(gerente.getClientes().get(Integer.parseInt(clienteTexField.getText())));
		}
		
		if (e.getSource()==cadastrarUsuarioButton) {
			new GerenteCadastraUsuarioFrame();
			
		}
		
		if(e.getSource()==cadastrarGerenteButton) {
			new GerenteCadastraGerenteFrame();
		}
	}
}
