package edu.puc.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static edu.puc.gui.BaseFrame.centreWindow;
import static edu.puc.persist.ClientePersistence.writeClienteToFile;

import edu.puc.classes.Cliente;

public class RegistrationFrame implements ActionListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel Loginlabel;
	JLabel pwdLabel;
	JButton enviar = new JButton("Cadastrar usuário");
	JButton login = new JButton("Fazer Login");
	JTextField loginTextField = new JTextField(20);
	JTextField pwdTextField = new JTextField(20);

	public RegistrationFrame(String tipo) {

		this.enviar.addActionListener(this);
		this.login.addActionListener(this);

		// Configuração básica para interface gráfica
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(tipo + " Registro");
		frame.setSize(300, 300);
		frame.setVisible(true);
		panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));
		frame.add(panel);
		centreWindow(frame);

		this.Loginlabel = new JLabel("Usuário");
		Loginlabel.setBounds(50, 150, 100, 30);
		panel.add(Loginlabel);

		loginTextField.setBounds(100, 20, 165, 25);
		panel.add(loginTextField);

		this.pwdLabel = new JLabel("Senha");
		pwdLabel.setBounds(50, 220, 100, 30);
		panel.add(pwdLabel);

		pwdTextField.setBounds(140, 20, 165, 25);
		panel.add(pwdTextField);

		enviar.setBounds(10, 110, 300, 25);
		panel.add(enviar);

		login.setBounds(10, 110, 300, 25);
		panel.add(login);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == enviar) {
			String login, pwd;
			login = loginTextField.getText();
			pwd = pwdTextField.getText();
			new Cliente(login, pwd);
			writeClienteToFile(login,pwd);
			System.out.println("Cliente Registrado!");
		}

		if (e.getSource() == login) {
			frame.setVisible(false);
			new ClienteLoginFrame("Cliente");

		}
	}
}
