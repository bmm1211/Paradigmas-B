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
import net.miginfocom.swing.MigLayout;

public class GerenteAlteraClienteFrame implements ActionListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel contas;
	JButton conta;
	Cliente cliente;

	JButton criarConta = new JButton("Criar nova conta");
	JLabel selecionarContaLabel = new JLabel("Qual Conta Deseja alterar?");
	JTextField selecionarConta = new JTextField(15);
	JButton alterarContaButton = new JButton("Alterar conta");

	public GerenteAlteraClienteFrame(Cliente cliente) {

		this.cliente = cliente;

		alterarContaButton.addActionListener(this);

		
		frame.setTitle("Cliente: " + cliente.getLogin());
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));
		frame.getContentPane().add(panel);
		centreWindow(frame);
		panel.setLayout(new MigLayout("", "[74px][78px][82px][111px]", "[23px][][][][][][][][][][][]"));

		contas = new JLabel("Contas Simples");
		panel.add(contas, "cell 0 0,alignx left,aligny center");

		int quantasContas = cliente.getContasSimples().size();
		for (int i = 0; i < quantasContas; i++) {
			panel.add(new JTextArea("CS" + (i + 1)), "cell 0 " + (i + 1) + ",alignx left,aligny center");
		}

		contas = new JLabel("Contas Especiais");
		panel.add(contas, "cell 1 0,alignx left,aligny center");

		quantasContas = cliente.getContasEspeciais().size();
		for (int i = 0; i < quantasContas; i++) {
			panel.add(new JTextArea("CE" + (i + 1)), ("cell 1 " + (i + 1) + ",alignx left,aligny center"));
		}

		contas = new JLabel("Contas Poupança");
		panel.add(contas, "cell 2 0,alignx left,aligny center");

		quantasContas = cliente.getContasPoupancas().size();
		for (int i = 0; i < quantasContas; i++) {
			panel.add(new JTextArea("CP" + (i + 1)), ("cell 2 " + (i + 1) + ",alignx left,aligny center"));
		}

		criarConta.addActionListener(this);

		criarConta.setBounds(10, 300, 300, 300);
		panel.add(criarConta, "cell 0 11,alignx left,aligny top");

		panel.add(selecionarContaLabel, "cell 1 11");
		panel.add(selecionarConta, "cell 2 11");
		panel.add(alterarContaButton, "cell 3 11");

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == criarConta) {

			frame.setVisible(false);
			new NovaContaFrame(cliente);

		}

		if (e.getSource() == alterarContaButton) {
			String option = selecionarConta.getText();

			new GerenteAlteraContaFrame(cliente, option);

		}

	}
}

