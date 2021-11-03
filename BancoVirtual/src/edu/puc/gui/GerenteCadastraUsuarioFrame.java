package edu.puc.gui;

import static edu.puc.gui.BaseFrame.centreWindow;
import static edu.puc.persist.ClientePersistence.writeNewClienteToFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GerenteCadastraUsuarioFrame implements ActionListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel Loginlabel;
	JLabel pwdLabel;
	JButton enviar = new JButton("Registrar");
	JTextField loginTextField = new JTextField(20);
	JTextField pwdTextField = new JTextField(20);

	public GerenteCadastraUsuarioFrame() {

		this.enviar.addActionListener(this);

		// Configuração básica para interface gráfica
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Registro de cliente");
		frame.setSize(300, 200);
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

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == enviar) {
			String login, pwd;
			login = loginTextField.getText();
			pwd = pwdTextField.getText();
			writeNewClienteToFile(login, pwd);
			frame.setVisible(false);
			System.out.println("Cliente Registrado!");
		}

	}
}
