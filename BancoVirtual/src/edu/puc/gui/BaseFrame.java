package edu.puc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BaseFrame implements ActionListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton cliente = new JButton("Cliente");
	JButton gerente = new JButton("Gerente");

	public BaseFrame() {

		cliente.addActionListener(this);
		gerente.addActionListener(this);

		panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));
		panel.setLayout(new GridLayout(1, 2, 4, 4));
		panel.add(cliente);
		panel.add(gerente);

		// Configuração básica para interface gráfica
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Banco Virtual");
		frame.pack();
		centreWindow(frame);
		frame.setVisible(true);

	}
	
	/* Define a posição como meio da tela */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == cliente) {
			frame.setVisible(false);
			new ClienteLoginFrame("Cliente");

		}

		if (e.getSource() == gerente) {
			frame.setVisible(false);
			new GerenteLoginFrame("Gerente");
		}

	}

}
