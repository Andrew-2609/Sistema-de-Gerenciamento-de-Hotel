package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TelaEscolha extends JFrame {
	

	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolha frame = new TelaEscolha();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEscolha() {
		setTitle("Gerenciar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 368);
		contentPane = new JPanel();	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JButton btnHospede = new JButton("Hospede");
		btnHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarHospede ch = new CadastrarHospede();
				ch.setVisible(true);
				dispose();
			}
		});
		btnHospede.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		btnHospede.setBounds(161, 175, 115, 23);
		contentPane.add(btnHospede);
		
		JButton btnFuncionrio = new JButton("Funcion\u00E1rio");
		btnFuncionrio.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		btnFuncionrio.setBounds(385, 175, 115, 23);
		contentPane.add(btnFuncionrio);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLogin tl = new TelaLogin();
				tl.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Bell MT", Font.BOLD, 15));
		btnVoltar.setBounds(286, 271, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblGerenciamento = new JLabel("Gerenciamento");
		lblGerenciamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerenciamento.setFont(new Font("Bodoni MT", Font.BOLD, 30));
		lblGerenciamento.setForeground(Color.WHITE);
		lblGerenciamento.setBounds(237, 29, 224, 54);
		contentPane.add(lblGerenciamento);
		
		JLabel imgFundo = new JLabel("");
		imgFundo.setIcon(new ImageIcon("telaEscolha.jpg"));
		imgFundo.setBounds(0, 0, 704, 341);
		contentPane.add(imgFundo);
		
		btnFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GerenciamentoDeFuncionarios gdf = new GerenciamentoDeFuncionarios();
				gdf.setVisible(true);
				dispose();
			}
		});
		
	}
}
