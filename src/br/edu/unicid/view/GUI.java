package br.edu.unicid.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblModuloDoCliente;
	private JButton btnEfetuarSaque;
	private JLabel lblModuloDoAdministrador;
	private JButton btnValorTotalDisponivel;
	private JButton btnRelatorioDeCedulas;
	private JButton btnCotaMinima;
	private JButton btnReposicaoDeCedulas;
	private JLabel lblModuloDeAmbos;
	private JButton btnSair;
	
	private int cedula = 0;	
	private int qntCedula = 0;	
	private int cotaMinima = 0;	
	private int saque = 0;	
	
	public GUI() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 340, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblModuloDoCliente = new JLabel("M\u00F3dulo do Cliente:");
		lblModuloDoCliente.setFont(new Font("Arial", Font.PLAIN, 12));
		lblModuloDoCliente.setBounds(10, 11, 314, 26);
		contentPane.add(lblModuloDoCliente);
		
		btnEfetuarSaque = new JButton("Efetuar Saque");
		btnEfetuarSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				CaixaEletronico caixa = new CaixaEletronico();	
				saque = Integer.parseInt(JOptionPane.showInputDialog(null,"Insira o valor a ser sacado em numeros inteiros"));
				if((saque<=0) || (saque==1) || (saque==3)) JOptionPane.showMessageDialog(null,"Não é possivel este valor");
				else{
					if( (saque <= CaixaEletronico.getTotalDisponivel() ) )
						JOptionPane.showMessageDialog(null,caixa.sacar(saque));
					else
						JOptionPane.showMessageDialog(null,"Saldo insuficiente");
				}
			} catch (IllegalArgumentException E){
				JOptionPane.showMessageDialog(null, "Saque cancelado.");
			}
			}
			
		});
		btnEfetuarSaque.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEfetuarSaque.setBounds(10, 48, 314, 40);
		contentPane.add(btnEfetuarSaque);
		
		lblModuloDoAdministrador = new JLabel("M\u00F3dulo do Administrador:");
		lblModuloDoAdministrador.setFont(new Font("Arial", Font.PLAIN, 12));
		lblModuloDoAdministrador.setBounds(10, 99, 314, 26);
		contentPane.add(lblModuloDoAdministrador);
		
		btnValorTotalDisponivel = new JButton("Valor Total Dispon\u00EDvel");
		btnValorTotalDisponivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CaixaEletronico caixa = new CaixaEletronico();				
				JOptionPane.showMessageDialog(null,caixa.pegaValorTotalDisponivel());	
			
			}
		});
		btnValorTotalDisponivel.setFont(new Font("Arial", Font.PLAIN, 12));
		btnValorTotalDisponivel.setBounds(10, 187, 314, 40);
		contentPane.add(btnValorTotalDisponivel);
		
		btnRelatorioDeCedulas = new JButton("Relatorio de C\u00E9dulas");
		btnRelatorioDeCedulas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CaixaEletronico caixa = new CaixaEletronico();				
				JOptionPane.showMessageDialog(null,caixa.pegaRelatorioCedulas());	
				
			}
		});
		btnRelatorioDeCedulas.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRelatorioDeCedulas.setBounds(10, 136, 314, 40);
		contentPane.add(btnRelatorioDeCedulas);
		
		btnCotaMinima = new JButton("Cota Minima");
		btnCotaMinima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {										
					CaixaEletronico caixa = new CaixaEletronico();
					cotaMinima = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a cota mínima"));
					JOptionPane.showMessageDialog(null, caixa.armazenaCotaMinima(cotaMinima));
	
				} catch (IllegalArgumentException E){
					JOptionPane.showMessageDialog(null, "Alteração de cota mínima não efetuada");
				}
				}
		});
		btnCotaMinima.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCotaMinima.setBounds(10, 289, 314, 40);
		contentPane.add(btnCotaMinima);
		
		//Botão para reposição de cédulas
		
		btnReposicaoDeCedulas = new JButton("Reposi\u00E7\u00E3o de C\u00E9dulas");
		btnReposicaoDeCedulas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		try {	
				
								
			CaixaEletronico caixa = new CaixaEletronico();
			cedula = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o valor da cédula"));
			if (cedula == 2 || cedula == 5 || cedula == 10 || cedula == 20 || cedula == 50 || cedula == 100){
				
				qntCedula = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o montante a ser adicionado em cédula de " + cedula + " Reais."));
				JOptionPane.showMessageDialog(null, caixa.reposicaoCedulas(cedula, qntCedula));
					
								
			}				
				else { 
					JOptionPane.showMessageDialog(null, "Nota de " + cedula + " Reais inexistente, reinicia a operação.");
				}
			} catch (IllegalArgumentException E){
			JOptionPane.showMessageDialog(null, "REPOSIÇÃO CANCELADA");
			}
		}
		
				
		});
		btnReposicaoDeCedulas.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReposicaoDeCedulas.setBounds(10, 238, 314, 40);
		contentPane.add(btnReposicaoDeCedulas);
		
		lblModuloDeAmbos = new JLabel("M\u00F3dulo de Ambos:");
		lblModuloDeAmbos.setFont(new Font("Arial", Font.PLAIN, 12));
		lblModuloDeAmbos.setBounds(10, 340, 314, 26);
		contentPane.add(lblModuloDeAmbos);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Verifica se foram realoizados saques
				
				if(CaixaEletronico.isSaque()){
					CaixaEletronico.setRelatorio("Saques efetuados durante a operação:\n\n" + CaixaEletronico.getRelatorio());
					JOptionPane.showMessageDialog(null, CaixaEletronico.getRelatorio());
					System.exit(0);
				} else {
					JOptionPane.showMessageDialog(null, "Não foram realizados saques durante a operação");
					System.exit(0);
				}
			}
				
		});
		btnSair.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSair.setBounds(10, 377, 314, 40);
		contentPane.add(btnSair);
		
		CaixaEletronico.atualizaSaldo();
				
	}
}