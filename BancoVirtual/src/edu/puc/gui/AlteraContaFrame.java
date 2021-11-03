package edu.puc.gui;

import static edu.puc.gui.BaseFrame.centreWindow;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.puc.classes.Cliente;

public class AlteraContaFrame implements ActionListener {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton depositar = new JButton("Depositar");
	JButton sacar = new JButton("Sacar");
	JButton verificarSaldo = new JButton("Verificar Saldo");
	JButton verificarExtrato = new JButton("Verificar Extrato");
	JTextField valorDeposito = new JTextField(5);
	JTextField valorSaque = new JTextField(5);
	JLabel saldoLabel = new JLabel("Saldo (em R$): ");
	JTextArea saldo;
	Cliente cliente;

	String tipo;
	String extrato;

	public AlteraContaFrame(Cliente cliente, String type) {

		this.cliente = cliente;
		this.tipo = type;

		System.out.println("Opção inválida, digite com letra maiúscula");

		depositar.addActionListener(this);
		sacar.addActionListener(this);
		verificarSaldo.addActionListener(this);
		verificarExtrato.addActionListener(this);
		
		
		frame.setTitle("Cliente: " + cliente.getLogin() + "  Conta:" + type);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));
		frame.getContentPane().add(panel);
		centreWindow(frame);
		panel.add(valorDeposito);
		panel.add(depositar);
		panel.add(valorSaque);
		panel.add(sacar);
		panel.add(verificarSaldo);
		panel.add(verificarExtrato);
		
		char tipoDaConta = tipo.charAt(1);
		int contaN = Character.getNumericValue(tipo.charAt(2))-1;
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
			System.out.println("Erro inesperado.");
		}
	
		saldo = new JTextArea(valorSaldo.toString());
		panel.add(saldoLabel);
		panel.add(saldo);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		char tipoDaConta = tipo.charAt(1);
		int contaN = Character.getNumericValue(tipo.charAt(2))-1;

		if (e.getSource() == depositar) {
			extrato = ("Depósito de " + valorDeposito.getText() + "R$.");
			switch (tipoDaConta) {
			case 'S':
				this.cliente.getContasSimples().get(contaN).deposita(Double.valueOf(valorDeposito.getText()));
				this.cliente.getContasSimples().get(contaN).getHistorico().add(extrato);
				break;

			case 'E':
				this.cliente.getContasEspeciais().get(contaN).deposita(Double.valueOf(valorDeposito.getText()));
				this.cliente.getContasEspeciais().get(contaN).getHistorico().add(extrato);

				break;

			case 'P':
				this.cliente.getContasPoupancas().get(contaN).deposita(Double.valueOf(valorDeposito.getText()));
				this.cliente.getContasPoupancas().get(contaN).getHistorico().add(extrato);

				break;

			default:
				System.out.println("Erro inesperado.");
			}
		}

		if (e.getSource() == sacar) {
			extrato = ("Saque de " + valorDeposito.getText() + "R$.");
			switch (tipoDaConta) {
			case 'S':
				try {
					this.cliente.getContasSimples().get(contaN).saca(Double.valueOf(valorSaque.getText()));
					this.cliente.getContasSimples().get(contaN).getHistorico().add(extrato);

				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;

			case 'E':
				try {
					this.cliente.getContasEspeciais().get(contaN).saca(Double.valueOf(valorSaque.getText()));
					this.cliente.getContasEspeciais().get(contaN).getHistorico().add(extrato);

				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;

			case 'P':
				try {
					this.cliente.getContasPoupancas().get(contaN).saca(Double.valueOf(valorSaque.getText()));
					this.cliente.getContasPoupancas().get(contaN).getHistorico().add(extrato);

				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;

			default:
				System.out.println("Erro inesperado.");
			}
			System.out.println("saque " + valorDeposito.getText() + " concluido");
		}

		if (e.getSource()== verificarSaldo) {
			
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
				System.out.println("Erro inesperado.");
			}
			
			saldo = new JTextArea("Novo Saldo:" + valorSaldo.toString());
			panel.add(saldoLabel);
			panel.add(saldo);
			frame.setVisible(true);
			
		}

		if (e.getSource() == verificarExtrato) {
			ArrayList<String> extratoCompleto = new ArrayList<String>();
			switch (tipoDaConta) {
			case 'S':
				extratoCompleto = this.cliente.getContasSimples().get(contaN).getHistorico();
				break;

			case 'E':
				extratoCompleto = this.cliente.getContasEspeciais().get(contaN).getHistorico();
				break;

			case 'P':
				extratoCompleto = this.cliente.getContasPoupancas().get(contaN).getHistorico();
				break;

			default:
				System.out.println("Erro inesperado.");
			}

			JTextArea extrato = new JTextArea("Extrato completo:" + extratoCompleto.toString());
			panel.add(extrato);
			frame.setVisible(true);

		}
		
	
	}
	

}
