package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

import utilitarios.Banco;
import utilitarios.ModeloTabela;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class CadastrarHospede extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JFormattedTextField tfCpf;
	private JFormattedTextField tfDiaNasc;
	private JFormattedTextField tfTelefone;
	private JComboBox tfTipoQuarto;
	private JTextField tfPassaporte;
	private JFormattedTextField tfDiaEntrada;
	private JComboBox tfPagamento;
	private JFormattedTextField tfDiaSaida;
	private JFormattedTextField tfMesNasc;
	private JFormattedTextField tfAnoNasc;
	private JFormattedTextField tfMesEntrada;
	private JFormattedTextField tfAnoEntrada;
	private JFormattedTextField tfMesSaida;
	private JFormattedTextField tfAnoSaida;

	Banco banco = new Banco();
	private JTable jTableHospedes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarHospede frame = new CadastrarHospede();
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
	public CadastrarHospede() {
		banco.conectar();
		banco.estaConectado();

		setTitle("Cadastrar Hóspede");
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		tfNome = new JTextField();
		tfNome.setEnabled(false);
		tfNome.setBackground(Color.GRAY);
		tfNome.setBounds(10, 40, 170, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfCpf = new JFormattedTextField();
		tfCpf.setEnabled(false);
		tfCpf.setBackground(Color.GRAY);
		tfCpf.setColumns(10);
		tfCpf.setBounds(222, 40, 96, 20);
		contentPane.add(tfCpf);

		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.install(tfCpf);
		} catch (ParseException e1) {
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

		tfDiaNasc = new JFormattedTextField();
		tfDiaNasc.setHorizontalAlignment(SwingConstants.CENTER);
		tfDiaNasc.setEnabled(false);
		tfDiaNasc.setBackground(Color.GRAY);
		tfDiaNasc.setColumns(10);
		tfDiaNasc.setBounds(386, 40, 24, 20);
		contentPane.add(tfDiaNasc);

		try {
			MaskFormatter mascaraDiaNasc = new MaskFormatter("##");
			mascaraDiaNasc.install(tfDiaNasc);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		tfMesNasc = new JFormattedTextField();
		tfMesNasc.setHorizontalAlignment(SwingConstants.CENTER);
		tfMesNasc.setEnabled(false);
		tfMesNasc.setBackground(Color.GRAY);
		tfMesNasc.setBounds(409, 40, 24, 20);
		contentPane.add(tfMesNasc);
		tfMesNasc.setColumns(10);

		try {
			MaskFormatter mascaraMesNasc = new MaskFormatter("##");
			mascaraMesNasc.install(tfMesNasc);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		tfAnoNasc = new JFormattedTextField();
		tfAnoNasc.setHorizontalAlignment(SwingConstants.CENTER);
		tfAnoNasc.setEnabled(false);
		tfAnoNasc.setBackground(Color.GRAY);
		tfAnoNasc.setBounds(432, 40, 48, 20);
		contentPane.add(tfAnoNasc);
		tfAnoNasc.setColumns(10);

		try {
			MaskFormatter mascaraAnoNasc = new MaskFormatter("####");
			mascaraAnoNasc.install(tfAnoNasc);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		tfTelefone = new JFormattedTextField();
		tfTelefone.setEnabled(false);
		tfTelefone.setBackground(Color.GRAY);
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(565, 40, 129, 20);
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

		String opcoesQuarto[] = new String[] { "Solteiro", "Duplo Solteiro", "Casal", "Luxo" };
		tfTipoQuarto = new JComboBox(opcoesQuarto);
		tfTipoQuarto.setEnabled(false);
		tfTipoQuarto.setBackground(Color.GRAY);
		tfTipoQuarto.setBounds(10, 152, 129, 20);
		contentPane.add(tfTipoQuarto);

		tfPassaporte = new JTextField();
		tfPassaporte.setEnabled(false);
		tfPassaporte.setBackground(Color.GRAY);
		tfPassaporte.setColumns(10);
		tfPassaporte.setBounds(10, 99, 96, 20);
		contentPane.add(tfPassaporte);
		tfPassaporte.setVisible(false);

		tfDiaEntrada = new JFormattedTextField();
		tfDiaEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		tfDiaEntrada.setEnabled(false);
		tfDiaEntrada.setBackground(Color.GRAY);
		tfDiaEntrada.setColumns(10);
		tfDiaEntrada.setBounds(222, 99, 24, 20);
		contentPane.add(tfDiaEntrada);

		try {
			MaskFormatter mascaraDiaEntrada = new MaskFormatter("##");
			mascaraDiaEntrada.install(tfDiaEntrada);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		tfMesEntrada = new JFormattedTextField();
		tfMesEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		tfMesEntrada.setEnabled(false);
		tfMesEntrada.setBackground(Color.GRAY);
		tfMesEntrada.setBounds(245, 99, 24, 20);
		contentPane.add(tfMesEntrada);
		tfMesEntrada.setColumns(10);

		try {
			MaskFormatter mascaraMesEntrada = new MaskFormatter("##");
			mascaraMesEntrada.install(tfMesEntrada);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		tfAnoEntrada = new JFormattedTextField();
		tfAnoEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		tfAnoEntrada.setEnabled(false);
		tfAnoEntrada.setBackground(Color.GRAY);
		tfAnoEntrada.setBounds(268, 99, 48, 20);
		contentPane.add(tfAnoEntrada);
		tfAnoEntrada.setColumns(10);

		try {
			MaskFormatter mascaraAnoEntrada = new MaskFormatter("####");
			mascaraAnoEntrada.install(tfAnoEntrada);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		tfDiaSaida = new JFormattedTextField();
		tfDiaSaida.setHorizontalAlignment(SwingConstants.CENTER);
		tfDiaSaida.setEnabled(false);
		tfDiaSaida.setBackground(Color.GRAY);
		tfDiaSaida.setColumns(10);
		tfDiaSaida.setBounds(386, 99, 24, 20);
		contentPane.add(tfDiaSaida);

		try {
			MaskFormatter mascaraDiaSaida = new MaskFormatter("##");
			mascaraDiaSaida.install(tfDiaSaida);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		tfMesSaida = new JFormattedTextField();
		tfMesSaida.setHorizontalAlignment(SwingConstants.CENTER);
		tfMesSaida.setEnabled(false);
		tfMesSaida.setBackground(Color.GRAY);
		tfMesSaida.setColumns(10);
		tfMesSaida.setBounds(409, 99, 24, 20);
		contentPane.add(tfMesSaida);

		try {
			MaskFormatter mascaraMesSaida = new MaskFormatter("##");
			mascaraMesSaida.install(tfMesSaida);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		tfAnoSaida = new JFormattedTextField();
		tfAnoSaida.setHorizontalAlignment(SwingConstants.CENTER);
		tfAnoSaida.setEnabled(false);
		tfAnoSaida.setBackground(Color.GRAY);
		tfAnoSaida.setColumns(10);
		tfAnoSaida.setBounds(432, 99, 48, 20);
		contentPane.add(tfAnoSaida);

		try {
			MaskFormatter mascaraAnoSaida = new MaskFormatter("####");
			mascaraAnoSaida.install(tfAnoSaida);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		String[] opcoesPagamento = new String[] { "Cartão de Crédito", "Cartão de Débito", "Boleto Bancário" };

		tfPagamento = new JComboBox(opcoesPagamento);
		tfPagamento.setEnabled(false);
		tfPagamento.setBackground(Color.GRAY);
		tfPagamento.setBounds(565, 99, 129, 20);
		contentPane.add(tfPagamento);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblNome.setBounds(10, 25, 96, 14);
		contentPane.add(lblNome);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblCpf.setBounds(222, 25, 96, 14);
		contentPane.add(lblCpf);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblTelefone.setBounds(565, 25, 96, 14);
		contentPane.add(lblTelefone);

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setForeground(Color.WHITE);
		lblDataDeNascimento.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblDataDeNascimento.setBounds(386, 25, 122, 14);
		contentPane.add(lblDataDeNascimento);

		JLabel lblTipoDeQuarto = new JLabel("Tipo de Quarto");
		lblTipoDeQuarto.setForeground(Color.WHITE);
		lblTipoDeQuarto.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblTipoDeQuarto.setBounds(10, 138, 129, 14);
		contentPane.add(lblTipoDeQuarto);

		JLabel lblDataDeEntrada = new JLabel("Data de Entrada");
		lblDataDeEntrada.setForeground(Color.WHITE);
		lblDataDeEntrada.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblDataDeEntrada.setBounds(222, 84, 106, 14);
		contentPane.add(lblDataDeEntrada);

		JLabel lblDataDeSada = new JLabel("Data de Sa\u00EDda");
		lblDataDeSada.setForeground(Color.WHITE);
		lblDataDeSada.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblDataDeSada.setBounds(386, 84, 96, 14);
		contentPane.add(lblDataDeSada);

		JLabel lblPagamento = new JLabel("Pagamento");
		lblPagamento.setForeground(Color.WHITE);
		lblPagamento.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblPagamento.setBounds(565, 84, 96, 14);
		contentPane.add(lblPagamento);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon("back.jpg"));
		btnVoltar.setBounds(10, 183, 26, 26);
		contentPane.add(btnVoltar);

		JButton btnNovo = new JButton("");
		btnNovo.setIcon(new ImageIcon("naozei.jpg"));
		btnNovo.setBounds(252, 183, 26, 26);
		contentPane.add(btnNovo);

		JButton btnInserir = new JButton("");
		btnInserir.setEnabled(false);
		btnInserir.setIcon(new ImageIcon("Salvar.jpg"));
		btnInserir.setBounds(302, 183, 26, 26);
		contentPane.add(btnInserir);

		JButton btnAtualizar = new JButton("");
		btnAtualizar.setIcon(new ImageIcon("Atualizar.jpg"));
		btnAtualizar.setBounds(352, 183, 26, 26);
		contentPane.add(btnAtualizar);

		JButton btnDeletar = new JButton("");
		btnDeletar.setIcon(new ImageIcon("apagar.jpg"));
		btnDeletar.setBounds(402, 183, 26, 26);
		contentPane.add(btnDeletar);

		JCheckBox checkboxPassaporte = new JCheckBox("Passaporte");
		checkboxPassaporte.setEnabled(false);
		checkboxPassaporte.setSelected(false);
		checkboxPassaporte.setHorizontalAlignment(SwingConstants.CENTER);
		checkboxPassaporte.setFont(new Font("Bodoni MT", Font.BOLD, 12));
		checkboxPassaporte.setBounds(10, 85, 96, 14);
		contentPane.add(checkboxPassaporte);

		checkboxPassaporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkboxPassaporte.isSelected() == true) {
					tfPassaporte.setVisible(true);
				} else {
					tfPassaporte.setVisible(false);
				}
			}
		});

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNome.setEnabled(true);
				tfNome.setBackground(Color.white);
				tfTelefone.setBackground(Color.white);
				tfTelefone.setEnabled(true);
				tfCpf.setEnabled(true);
				tfCpf.setBackground(Color.white);
				tfTipoQuarto.setEnabled(true);
				tfTipoQuarto.setBackground(Color.white);
				tfDiaNasc.setEnabled(true);
				tfDiaNasc.setBackground(Color.white);
				tfDiaEntrada.setEnabled(true);
				tfDiaEntrada.setBackground(Color.white);
				tfDiaSaida.setEnabled(true);
				tfDiaSaida.setBackground(Color.white);
				tfMesNasc.setEnabled(true);
				tfMesNasc.setBackground(Color.white);
				tfMesEntrada.setEnabled(true);
				tfMesEntrada.setBackground(Color.white);
				tfMesSaida.setEnabled(true);
				tfMesSaida.setBackground(Color.white);
				tfAnoNasc.setEnabled(true);
				tfAnoNasc.setBackground(Color.white);
				tfAnoEntrada.setEnabled(true);
				tfAnoEntrada.setBackground(Color.white);
				tfAnoSaida.setEnabled(true);
				tfAnoSaida.setBackground(Color.white);
				tfPagamento.setEnabled(true);
				tfPagamento.setBackground(Color.white);
				tfPassaporte.setEnabled(true);
				tfPassaporte.setBackground(Color.white);
				checkboxPassaporte.setEnabled(true);
				btnInserir.setEnabled(true);
			}
		});

		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEscolha te = new TelaEscolha();
				te.setVisible(true);
				dispose();
			}
		});

		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfNome.getText().isEmpty() || tfDiaNasc.getText().toString().matches("\\d{2}") == false
						|| tfMesNasc.getText().toString().matches("\\d{2}") == false
						|| tfAnoNasc.getText().toString().matches("\\d{4}") == false
						|| tfTelefone.getText().toString().matches("\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}") == false
						|| tfCpf.getText().toString().matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}") == false
						|| tfDiaEntrada.getText().toString().matches("\\d{2}") == false
						|| tfMesEntrada.getText().toString().matches("\\d{2}") == false
						|| tfAnoEntrada.getText().toString().matches("\\d{4}") == false
						|| tfDiaSaida.getText().toString().matches("\\d{2}") == false
						|| tfMesSaida.getText().toString().matches("\\d{2}") == false
						|| tfAnoSaida.getText().toString().matches("\\d{4}") == false) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				} else {
					try {
						String data_entrada_completa = tfAnoEntrada.getText() + "-" + tfMesEntrada.getText() + "-"
								+ tfDiaEntrada.getText();
						String data_saida_completa = tfAnoSaida.getText() + "-" + tfMesSaida.getText() + "-"
								+ tfDiaSaida.getText();
						String data_nasc_completa = tfAnoNasc.getText() + "-" + tfMesNasc.getText() + "-"
								+ tfDiaNasc.getText();

						String cpf = tfCpf.getText();
						String nome = tfNome.getText();
						String tel = tfTelefone.getText();
						String passaporte = tfPassaporte.getText();
						String tipoQuarto = tfTipoQuarto.getSelectedItem().toString();
						String m_paga = tfPagamento.getSelectedItem().toString();

						banco.adicionarHospedes(cpf, nome, tel, passaporte, tipoQuarto, data_nasc_completa, m_paga,
								data_entrada_completa, data_saida_completa);
						desativarInputs();
						checkboxPassaporte.setEnabled(false);
						checkboxPassaporte.setSelected(false);
						tfPassaporte.setVisible(false);
						btnInserir.setEnabled(false);

						preencherTabela("SELECT * FROM hospedes ORDER BY id");
					} catch (Exception e1) {
						System.out.println("Erro: " + e1.getMessage());
					}
				}
			}
		});

		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtualizarHospede ah = new AtualizarHospede();
				ah.setVisible(true);
				dispose();
			}
		});

		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelarReserva cr = new CancelarReserva();
				cr.setVisible(true);
				dispose();
			}
		});

		jTableHospedes = new JTable();
		JScrollPane scrollPane = new JScrollPane(jTableHospedes);
		scrollPane.setBounds(10, 221, 678, 106);
		contentPane.add(scrollPane);

		JLabel imgFundo = new JLabel("");
		imgFundo.setIcon(new ImageIcon(
				"quartohotel.jpg"));
		imgFundo.setBounds(0, 0, 702, 341);
		contentPane.add(imgFundo);

		preencherTabela("SELECT * FROM hospedes ORDER BY id");
	}

	public void desativarInputs() {
		tfNome.setEnabled(false);
		tfNome.setBackground(Color.gray);
		tfNome.setText("");
		tfTelefone.setEnabled(false);
		tfTelefone.setBackground(Color.gray);
		tfTelefone.setText("");
		tfPassaporte.setEnabled(false);
		tfPassaporte.setBackground(Color.gray);
		tfPassaporte.setText("");
		tfCpf.setEnabled(false);
		tfCpf.setBackground(Color.gray);
		tfCpf.setText("");
		tfTipoQuarto.setEnabled(false);
		tfTipoQuarto.setBackground(Color.gray);
		tfTipoQuarto.setSelectedItem("Solteiro");
		tfDiaNasc.setEnabled(false);
		tfDiaNasc.setBackground(Color.gray);
		tfDiaNasc.setText("");
		tfMesNasc.setEnabled(false);
		tfMesNasc.setBackground(Color.gray);
		tfMesNasc.setText("");
		tfAnoNasc.setEnabled(false);
		tfAnoNasc.setBackground(Color.gray);
		tfAnoNasc.setText("");
		tfPagamento.setEnabled(false);
		tfPagamento.setBackground(Color.gray);
		tfPagamento.setSelectedItem("Cartão de Crédito");
		tfDiaEntrada.setEnabled(false);
		tfDiaEntrada.setBackground(Color.gray);
		tfDiaEntrada.setText("");
		tfMesEntrada.setEnabled(false);
		tfMesEntrada.setBackground(Color.gray);
		tfMesEntrada.setText("");
		tfAnoEntrada.setEnabled(false);
		tfAnoEntrada.setBackground(Color.gray);
		tfAnoEntrada.setText("");
		tfDiaSaida.setEnabled(false);
		tfDiaSaida.setBackground(Color.gray);
		tfDiaSaida.setText("");
		tfMesSaida.setEnabled(false);
		tfMesSaida.setBackground(Color.gray);
		tfMesSaida.setText("");
		tfAnoSaida.setEnabled(false);
		tfAnoSaida.setBackground(Color.gray);
		tfAnoSaida.setText("");
	}

	public void preencherTabela(String query) {

		ArrayList<Object[]> dados = new ArrayList();

		String[] colunas = new String[] { "ID", "CPF", "Nome", "Quarto", "Telefone", "DataNascimento", "DataEntrada",
				"DataSaída", "Pagamento", "Passaporte" };

		try {
			banco.executaSQL(query);
			banco.resultset.first();
			do {
				dados.add(new Object[] { banco.resultset.getInt("id"), banco.resultset.getString("cpf"),
						banco.resultset.getString("nome"), banco.resultset.getString("tipoQuarto"),
						banco.resultset.getString("telefone"), banco.resultset.getDate("data_nasc"),
						banco.resultset.getDate("data_entrada"), banco.resultset.getDate("data_saida"),
						banco.resultset.getString("metodo_pagamento"), banco.resultset.getString("passaporte") });
			} while (banco.resultset.next());
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela!\nERRO: " + sqle.getMessage());
		}

		ModeloTabela modelo = new ModeloTabela(dados, colunas);

		jTableHospedes.setModel(modelo);

		jTableHospedes.getColumnModel().getColumn(0).setResizable(false);
		jTableHospedes.getColumnModel().getColumn(0).setPreferredWidth(42);
		jTableHospedes.getColumnModel().getColumn(1).setResizable(false);
		jTableHospedes.getColumnModel().getColumn(1).setPreferredWidth(100);
		jTableHospedes.getColumnModel().getColumn(2).setResizable(false);
		jTableHospedes.getColumnModel().getColumn(2).setPreferredWidth(210);
		jTableHospedes.getColumnModel().getColumn(3).setResizable(false);
		jTableHospedes.getColumnModel().getColumn(3).setPreferredWidth(50);
		jTableHospedes.getColumnModel().getColumn(4).setResizable(false);
		jTableHospedes.getColumnModel().getColumn(4).setPreferredWidth(100);
		jTableHospedes.getColumnModel().getColumn(5).setResizable(false);
		jTableHospedes.getColumnModel().getColumn(5).setPreferredWidth(105);
		jTableHospedes.getColumnModel().getColumn(6).setResizable(false);
		jTableHospedes.getColumnModel().getColumn(6).setPreferredWidth(85);
		jTableHospedes.getColumnModel().getColumn(7).setResizable(false);
		jTableHospedes.getColumnModel().getColumn(7).setPreferredWidth(85);
		jTableHospedes.getColumnModel().getColumn(8).setResizable(false);
		jTableHospedes.getColumnModel().getColumn(8).setPreferredWidth(120);

		jTableHospedes.getTableHeader().setReorderingAllowed(false);
		jTableHospedes.setAutoResizeMode(jTableHospedes.AUTO_RESIZE_OFF);

		jTableHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		for (int i = 0; i < colunas.length; i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			jTableHospedes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}
}
