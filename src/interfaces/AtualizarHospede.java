package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import utilitarios.Banco;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class AtualizarHospede extends JFrame {

	private JPanel contentPane;
	private JComboBox tfTipoDeQuarto;
	private JTextField tfPassaporte;
	private JTextField tfNome;
	private JFormattedTextField tfCpf;
	private JFormattedTextField tfDiaEntrada;
	private JFormattedTextField tfDiaSaida;
	private JFormattedTextField tfDiaNasc;
	private JComboBox tfMetodoPagamento;
	private JFormattedTextField tfTelefone;
	private JTextField tfId;
	private JFormattedTextField tfAnoNasc;
	private JFormattedTextField tfMesNasc;
	private JFormattedTextField tfMesSaida;
	private JFormattedTextField tfAnoSaida;
	private JFormattedTextField tfMesEntrada;
	private JFormattedTextField tfAnoEntrada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarHospede frame = new AtualizarHospede();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AtualizarHospede() {
		Banco banco = new Banco();
		banco.conectar();
		
		setTitle("Atualizar Hóspede");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		String[] opcoesTipo = new String[] {"Solteiro", "Duplo Solteiro", "Casal", "Luxo"};
		tfTipoDeQuarto = new JComboBox(opcoesTipo);
		tfTipoDeQuarto.setBounds(10, 157, 129, 20);
		contentPane.add(tfTipoDeQuarto);
		
		JLabel lblTipoDeQuarto = new JLabel("Tipo de Quarto");
		lblTipoDeQuarto.setForeground(Color.WHITE);
		lblTipoDeQuarto.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblTipoDeQuarto.setBounds(10, 143, 129, 14);
		contentPane.add(lblTipoDeQuarto);
		
		tfPassaporte = new JTextField();
		tfPassaporte.setColumns(10);
		tfPassaporte.setBounds(598, 103, 96, 20);
		contentPane.add(tfPassaporte);
		tfPassaporte.setVisible(false);
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(10, 45, 170, 20);
		contentPane.add(tfNome);
		
		JLabel lbNome = new JLabel("Nome");
		lbNome.setForeground(Color.WHITE);
		lbNome.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lbNome.setBounds(10, 30, 96, 14);
		contentPane.add(lbNome);
		
		JLabel lbCpf = new JLabel("CPF");
		lbCpf.setForeground(Color.WHITE);
		lbCpf.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lbCpf.setBounds(222, 30, 96, 14);
		contentPane.add(lbCpf);
		
		tfCpf = new JFormattedTextField();
		tfCpf.setColumns(10);
		tfCpf.setBounds(222, 45, 96, 20);
		contentPane.add(tfCpf);
		
		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.install(tfCpf);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		tfCpf.addFocusListener(new FocusAdapter() {
			String textoCpf;

			@Override
			public void focusLost(FocusEvent arg0) {
				textoCpf = tfCpf.getText();
				if (textoCpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
					tfCpf.setText(textoCpf);
				} else {
					tfCpf.setText("");
				}
			}
		});
		
		JLabel lbDataEntrada = new JLabel("Data de entrada");
		lbDataEntrada.setForeground(Color.WHITE);
		lbDataEntrada.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lbDataEntrada.setBounds(222, 89, 96, 14);
		contentPane.add(lbDataEntrada);
		
		JLabel lbDataSaida = new JLabel("Data de sa\u00EDda");
		lbDataSaida.setForeground(Color.WHITE);
		lbDataSaida.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lbDataSaida.setBounds(386, 89, 96, 14);
		contentPane.add(lbDataSaida);
		
		JLabel lbDataNasc = new JLabel("Data de nascimento");
		lbDataNasc.setForeground(Color.WHITE);
		lbDataNasc.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lbDataNasc.setBounds(386, 30, 122, 14);
		contentPane.add(lbDataNasc);
		
		tfDiaNasc = new JFormattedTextField();
		tfDiaNasc.setHorizontalAlignment(SwingConstants.CENTER);
		tfDiaNasc.setColumns(10);
		tfDiaNasc.setBounds(386, 45, 24, 20);
		contentPane.add(tfDiaNasc);
		
		try {
			MaskFormatter mascaraDiaNasc = new MaskFormatter("##");
			mascaraDiaNasc.install(tfDiaNasc);
		} catch (ParseException e3) {
			e3.printStackTrace();
		}
		
		tfMesNasc = new JFormattedTextField();
		tfMesNasc.setBounds(409, 45, 24, 20);
		contentPane.add(tfMesNasc);
		tfMesNasc.setColumns(10);
		
		try {
			MaskFormatter mascaraMesNasc = new MaskFormatter("##");
			mascaraMesNasc.install(tfMesNasc);
		} catch (ParseException e3) {
			e3.printStackTrace();
		}
		
		tfAnoNasc = new JFormattedTextField();
		tfAnoNasc.setHorizontalAlignment(SwingConstants.CENTER);
		tfAnoNasc.setBounds(432, 45, 48, 20);
		contentPane.add(tfAnoNasc);
		tfAnoNasc.setColumns(10);
		
		try {
			MaskFormatter mascaraAnoNasc = new MaskFormatter("####");
			mascaraAnoNasc.install(tfAnoNasc);
		} catch (ParseException e3) {
			e3.printStackTrace();
		}
		
		tfDiaEntrada = new JFormattedTextField();
		tfDiaEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		tfDiaEntrada.setColumns(10);
		tfDiaEntrada.setBounds(222, 104, 24, 20);
		contentPane.add(tfDiaEntrada);
		
		try {
			MaskFormatter mascaraDiaEntrada = new MaskFormatter("##");
			mascaraDiaEntrada.install(tfDiaEntrada);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		tfMesEntrada = new JFormattedTextField();
		tfMesEntrada.setBounds(245, 104, 24, 20);
		contentPane.add(tfMesEntrada);
		tfMesEntrada.setColumns(10);
		
		try {
			MaskFormatter mascaraMesEntrada = new MaskFormatter("##");
			mascaraMesEntrada.install(tfMesEntrada);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		tfAnoEntrada = new JFormattedTextField();
		tfAnoEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		tfAnoEntrada.setBounds(268, 104, 48, 20);
		contentPane.add(tfAnoEntrada);
		tfAnoEntrada.setColumns(10);
		
		try {
			MaskFormatter mascaraAnoEntrada = new MaskFormatter("####");
			mascaraAnoEntrada.install(tfAnoEntrada);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		tfDiaSaida = new JFormattedTextField();
		tfDiaSaida.setHorizontalAlignment(SwingConstants.CENTER);
		tfDiaSaida.setColumns(10);
		tfDiaSaida.setBounds(386, 104, 24, 20);
		contentPane.add(tfDiaSaida);
		
		try {
			MaskFormatter mascaraDiaSaida = new MaskFormatter("##");
			mascaraDiaSaida.install(tfDiaSaida);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		tfMesSaida = new JFormattedTextField();
		tfMesSaida.setBounds(409, 104, 24, 20);
		contentPane.add(tfMesSaida);
		tfMesSaida.setColumns(10);
		
		try {
			MaskFormatter mascaraMesSaida = new MaskFormatter("##");
			mascaraMesSaida.install(tfMesSaida);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		tfAnoSaida = new JFormattedTextField();
		tfAnoSaida.setHorizontalAlignment(SwingConstants.CENTER);
		tfAnoSaida.setBounds(432, 104, 48, 20);
		contentPane.add(tfAnoSaida);
		tfAnoSaida.setColumns(10);
		
		try {
			MaskFormatter mascaraAnoSaida = new MaskFormatter("####");
			mascaraAnoSaida.install(tfAnoSaida);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		JLabel lbTelefone = new JLabel("Telefone");
		lbTelefone.setForeground(Color.WHITE);
		lbTelefone.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lbTelefone.setBounds(598, 30, 96, 14);
		contentPane.add(lbTelefone);
		
		JLabel lbPagamento = new JLabel("Pagamento");
		lbPagamento.setForeground(Color.WHITE);
		lbPagamento.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lbPagamento.setBounds(10, 89, 96, 14);
		contentPane.add(lbPagamento);
		
		String opcoes[] = new String[] {"Cartão de Crédito", "Cartão de Débito", "Boleto Bancário"};
		tfMetodoPagamento = new JComboBox(opcoes);
		tfMetodoPagamento.setBounds(10, 104, 129, 20);
		contentPane.add(tfMetodoPagamento);
		
		tfTelefone = new JFormattedTextField();
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(598, 45, 96, 20);
		contentPane.add(tfTelefone);
		
		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.install(tfTelefone);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		tfTelefone.addFocusListener(new FocusAdapter() {
			String textoTelefone;

			@Override
			public void focusLost(FocusEvent e) {
				textoTelefone = tfTelefone.getText();

				if (textoTelefone.matches("\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}")) {
					tfTelefone.setText(textoTelefone);
				} else {
					tfTelefone.setText("");
				}
			}
		});
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon("back.jpg"));
		btnVoltar.setBounds(10, 188, 26, 26);
		contentPane.add(btnVoltar);
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.setIcon(new ImageIcon("Atualizar.jpg"));
		btnAtualizar.setBounds(335, 188, 26, 26);
		contentPane.add(btnAtualizar);
		
		tfId = new JTextField();
		tfId.setBackground(Color.WHITE);
		tfId.setBounds(303, 157, 96, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lbId = new JLabel("ID");
		lbId.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lbId.setForeground(Color.BLACK);
		lbId.setBounds(340, 143, 48, 14);
		contentPane.add(lbId);
		
		JCheckBox checkboxPassaporte = new JCheckBox("Passaporte");
		checkboxPassaporte.setSelected(false);
		checkboxPassaporte.setFont(new Font("Bodoni MT", Font.BOLD, 12));
		checkboxPassaporte.setBounds(598, 90, 96, 14);
		contentPane.add(checkboxPassaporte);
		
		JLabel imgFundo = new JLabel("");
		imgFundo.setIcon(new ImageIcon("recepcao.jpg"));
		imgFundo.setBounds(0, 0, 704, 341);
		contentPane.add(imgFundo);
		
		checkboxPassaporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkboxPassaporte.isSelected() == true) {
					tfPassaporte.setVisible(true);
				}else {
					tfPassaporte.setVisible(false);
				}
			}
		});
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarHospede ch = new CadastrarHospede();
				ch.setVisible(true);
				dispose();
			}
		});
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfNome.getText().isEmpty() || tfDiaNasc.getText().toString().matches("\\d{2}") == false || tfMesNasc.getText().toString().matches("\\d{2}") == false
						|| tfAnoNasc.getText().toString().matches("\\d{4}") == false
						|| tfTelefone.getText().toString().matches("\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}") == false
						|| tfCpf.getText().toString().matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}") == false
						|| tfDiaEntrada.getText().toString().matches("\\d{2}") == false || tfMesEntrada.getText().toString().matches("\\d{2}") == false
						|| tfAnoEntrada.getText().toString().matches("\\d{4}") == false || tfDiaSaida.getText().toString().matches("\\d{2}") == false
						|| tfMesSaida.getText().toString().matches("\\d{2}") == false || tfAnoSaida.getText().toString().matches("\\d{4}") == false || tfId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				} else {
					String data_entrada_completa = tfAnoEntrada.getText() + "-" + tfMesEntrada.getText() + "-" + tfDiaEntrada.getText();
					String data_saida_completa = tfAnoSaida.getText() + "-" + tfMesSaida.getText() + "-" + tfDiaSaida.getText();
					String data_nasc_completa = tfAnoNasc.getText() + "-" + tfMesNasc.getText() + "-" + tfDiaNasc.getText();
					
					int id = Integer.parseInt(tfId.getText()); 
					String tipoQuarto = tfTipoDeQuarto.getSelectedItem().toString();
					String cpf = tfCpf.getText();
					String nome = tfNome.getText();
					String tele = tfTelefone.getText();
					String pas = tfPassaporte.getText();
					String m_pagamento = tfMetodoPagamento.getSelectedItem().toString();
					banco.atualizarHospedes(id, tipoQuarto, cpf, nome, tele, pas, data_nasc_completa, m_pagamento, data_entrada_completa, data_saida_completa);
				}
			}
		});
	}
}
