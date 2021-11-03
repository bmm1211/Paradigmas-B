package edu.puc.gui;

import static edu.puc.gui.BaseFrame.centreWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import edu.puc.classes.Cliente;
import edu.puc.classes.ContaEspecial;
import edu.puc.classes.ContaPoupanca;
import edu.puc.classes.ContaSimples;

public class NovaContaFrame implements ActionListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel optionLabel = new JLabel("Tipo da conta:");
	JRadioButton option = new JRadioButton("Normal");
	JRadioButton option2 = new JRadioButton("Especial");
	JRadioButton option3 = new JRadioButton("Poupança");
	JButton enviar = new JButton("Enviar");
	JLabel valorLabel = new JLabel("Valor");
	JTextField valorTextField = new JTextField(10);
	Cliente cliente;

	NovaContaFrame(Cliente cliente) {
		this.cliente = cliente;

		enviar.addActionListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(cliente.getLogin());
		frame.setSize(400, 400);
		frame.setVisible(true);
		panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));
		frame.add(panel);
		centreWindow(frame);

		panel.add(optionLabel);
		panel.add(option);
		panel.add(option2);
		panel.add(option3);

		this.valorLabel = new JLabel("Valor");
		panel.add(valorLabel);

		panel.add(valorTextField);

		panel.add(enviar);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		double valor = Double.valueOf(valorTextField.getText());

		if (valor<0)
			valor = 0;
		
		if (option.isSelected())
			cliente.getContasSimples().add(new ContaSimples(valor));

		if (option2.isSelected())
			cliente.getContasEspeciais().add(new ContaEspecial(valor));

		if (option3.isSelected())
			cliente.getContasPoupancas().add(new ContaPoupanca(valor));

		
		frame.setVisible(false);
		new ClienteFrame(this.cliente);

	}
}
