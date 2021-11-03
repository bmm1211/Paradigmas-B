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

public class GerenteAlteraContaFrame implements ActionListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton alterarLimite = new JButton("Alterar Limite");
	JTextField alterarJurosValor = new JTextField(4);
	JButton alterarJuros = new JButton("Alterar juros(%)");
	JButton sacar = new JButton("Sacar");
	JButton verificarSaldo = new JButton("Verificar Saldo");
	JTextField valorLimite = new JTextField(5);
	JTextField valorSaque = new JTextField(5);
	JLabel saldoLabel = new JLabel("Saldo (em R$): ");
	JTextArea saldo;
	JTextField valorDeposito = new JTextField(5);
	JButton depositar = new JButton("Depositar");
	Cliente cliente;

	String tipo;

	public GerenteAlteraContaFrame(Cliente cliente, String type) {

		this.cliente = cliente;
		this.tipo = type;

		depositar.addActionListener(this);
		alterarLimite.addActionListener(this);
		alterarJuros.addActionListener(this);
		sacar.addActionListener(this);
		verificarSaldo.addActionListener(this);

		frame.setTitle("Cliente: " + cliente.getLogin() + "  Conta:" + type);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));
		frame.getContentPane().add(panel);
		centreWindow(frame);

		char tipoDaConta = tipo.charAt(1);
		int contaN = Character.getNumericValue(tipo.charAt(2)) - 1;
		Double valorSaldo = 0.0;
		switch (tipoDaConta) {
		case 'S':
			valorSaldo = this.cliente.getContasSimples().get(contaN).getSaldo();
			break;

		case 'E':
			valorSaldo = this.cliente.getContasEspeciais().get(contaN).getSaldo();
			panel.add(valorLimite);
			panel.add(alterarLimite);
			break;

		case 'P':
			valorSaldo = this.cliente.getContasPoupancas().get(contaN).getSaldo();
			panel.add(alterarJurosValor);
			panel.add(alterarJuros);
			break;

		default:
			System.out.println("erro inesperado.");
		}

		panel.add(valorDeposito);
		panel.add(depositar);
		panel.add(valorSaque);
		panel.add(sacar);
		panel.add(verificarSaldo);
		saldo = new JTextArea(valorSaldo.toString());
		panel.add(saldoLabel);
		panel.add(saldo);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		char tipoDaConta = tipo.charAt(1);
		int contaN = Character.getNumericValue(tipo.charAt(2)) - 1;
		
		if(e.getSource()==alterarJuros)
			this.cliente.getContasPoupancas().get(contaN).setJuros(Float.valueOf(alterarJurosValor.getText()));

		if (e.getSource() == alterarLimite) {
			this.cliente.getContasEspeciais().get(contaN).setLimite(Double.valueOf(valorLimite.getText()));
		}

		if (e.getSource() == sacar) {

			if ((Double.valueOf(valorSaque.getText())) > 100000.) {
				switch (tipoDaConta) {
				case 'S':
					try {
						this.cliente.getContasSimples().get(contaN).saca(Double.valueOf(valorSaque.getText()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					break;

				case 'E':
					try {
						this.cliente.getContasEspeciais().get(contaN).saca(Double.valueOf(valorSaque.getText()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					break;

				case 'P':
					try {
						this.cliente.getContasPoupancas().get(contaN).saca(Double.valueOf(valorSaque.getText()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					break;

				default:
					System.out.println("erro inesperado.");
				}
				System.out.println("saque " + valorLimite.getText() + " concluido");
			} else {
				System.out.println("Lembre-se, permitido saque apenas acima de 50.000");
			}

		}

		if (e.getSource() == verificarSaldo) {

			Double valorSaldo = 0.0;
			switch (tipoDaConta) {
			case 'S':
				valorSaldo = this.cliente.getContasSimples().get(contaN).getSaldo();
				break;

			case 'E':
				valorSaldo = this.cliente.getContasEspeciais().get(contaN).getSaldo();
				break;

			case 'P':
				valorSaldo = this.cliente.getContasPoupancas().get(contaN).getSaldo();
				break;

			default:
				System.out.println("erro inesperado.");
			}

			saldo = new JTextArea("Novo Saldo:" + valorSaldo.toString());

			panel.add(saldoLabel);
			panel.add(saldo);
			frame.setVisible(true);
		}

		if (e.getSource() == depositar) {
			switch (tipoDaConta) {
			case 'S':
				this.cliente.getContasSimples().get(contaN).deposita(Double.valueOf(valorDeposito.getText()));
				break;

			case 'E':
				this.cliente.getContasEspeciais().get(contaN).deposita(Double.valueOf(valorDeposito.getText()));
				break;

			case 'P':
				this.cliente.getContasPoupancas().get(contaN).deposita(Double.valueOf(valorDeposito.getText()));
				break;

			default:
				System.out.println("erro inesperado.");
			}
		}
	}

}
