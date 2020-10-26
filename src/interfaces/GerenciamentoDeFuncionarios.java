package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

import utilitarios.Banco;
import utilitarios.ModeloTabela;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GerenciamentoDeFuncionarios extends JFrame {

	private JTextField tfNome;
	private JFormattedTextField tfTelefone;
	private JFormattedTextField tfSalario;
	private JFormattedTextField tfCpf;
	private JTextField tfFuncao;
	private JComboBox tfTurno;
	private JTable jTableFuncionarios;

	Banco bd = new Banco();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciamentoDeFuncionarios window = new GerenciamentoDeFuncionarios();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GerenciamentoDeFuncionarios() {
		initialize();
	}

	private void initialize() {
		bd.conectar();

		setTitle("Gerenciamento de Funcionários");
		setBounds(100, 100, 716, 473);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		jTableFuncionarios = new JTable();
		jTableFuncionarios.setBounds(12, 171, 481, 125);
		jTableFuncionarios.setFont(new Font("Dialog", Font.PLAIN, 12));

		JScrollPane scrollPaneTabela = new JScrollPane(jTableFuncionarios);
		scrollPaneTabela.setBounds(-3, 277, 713, 171);
		getContentPane().add(scrollPaneTabela);

		tfNome = new JTextField();
		tfNome.setBackground(Color.GRAY);
		tfNome.setEnabled(false);
		tfNome.setBounds(8, 62, 159, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);

		tfTelefone = new JFormattedTextField();
		tfTelefone.setBackground(Color.GRAY);
		tfTelefone.setEnabled(false);
		tfTelefone.setBounds(576, 108, 104, 20);
		getContentPane().add(tfTelefone);
		tfTelefone.setColumns(10);

		MaskFormatter mascaraTelefone;
		try {
			mascaraTelefone = new MaskFormatter("(##) #####-####");
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

		tfSalario = new JFormattedTextField();
		tfSalario.setHorizontalAlignment(SwingConstants.CENTER);
		tfSalario.setBackground(Color.GRAY);
		tfSalario.setEnabled(false);
		tfSalario.setBounds(305, 62, 90, 20);
		getContentPane().add(tfSalario);
		tfSalario.setColumns(10);

		try {
			MaskFormatter mascaraSalario = new MaskFormatter("####.##");
			mascaraSalario.install(tfSalario);
		} catch (ParseException e2) {
			JOptionPane.showMessageDialog(null, "Preencha todos os digitos do salário, mesmo que seja com 0!");
		}

		tfSalario.addFocusListener(new FocusAdapter() {
			String textoSalario;

			@Override
			public void focusLost(FocusEvent e) {
				textoSalario = tfSalario.getText();
				if (textoSalario.matches("\\d{4}\\.\\d{2}")) {
					tfSalario.setText(textoSalario);
				} else {
					tfSalario.setText("");
				}
			}
		});

		tfCpf = new JFormattedTextField();
		tfCpf.setBackground(Color.GRAY);
		tfCpf.setEnabled(false);
		tfCpf.setBounds(8, 108, 104, 20);
		getContentPane().add(tfCpf);
		tfCpf.setColumns(10);

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

		tfFuncao = new JTextField();
		tfFuncao.setBackground(Color.GRAY);
		tfFuncao.setEnabled(false);
		tfFuncao.setBounds(576, 62, 104, 20);
		getContentPane().add(tfFuncao);
		tfFuncao.setColumns(10);

		String[] turnos = new String[] { "Manhã", "Tarde", "Noite", "Integral" };

		tfTurno = new JComboBox(turnos);
		tfTurno.setFont(new Font("Bodoni MT", Font.PLAIN, 12));
		tfTurno.setBackground(Color.GRAY);
		tfTurno.setEnabled(false);
		tfTurno.setBounds(305, 108, 90, 20);
		getContentPane().add(tfTurno);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblNome.setBounds(8, 46, 55, 16);
		getContentPane().add(lblNome);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblCpf.setBounds(8, 94, 55, 16);
		getContentPane().add(lblCpf);

		JLabel lblSalrio = new JLabel("Sal\u00E1rio");
		lblSalrio.setForeground(Color.WHITE);
		lblSalrio.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblSalrio.setBounds(305, 46, 55, 16);
		getContentPane().add(lblSalrio);

		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblTurno.setForeground(Color.WHITE);
		lblTurno.setBounds(305, 94, 55, 16);
		getContentPane().add(lblTurno);

		JLabel lblFuncao = new JLabel("Fun\u00E7\u00E3o");
		lblFuncao.setForeground(Color.WHITE);
		lblFuncao.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblFuncao.setBounds(576, 46, 55, 16);
		getContentPane().add(lblFuncao);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setBounds(576, 94, 55, 16);
		getContentPane().add(lblTelefone);

		JButton btnNovo = new JButton("");
		btnNovo.setIcon(new ImageIcon("naozei.jpg"));
		btnNovo.setBounds(285, 152, 23, 26);
		getContentPane().add(btnNovo);

		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon("apagar.jpg"));
		btnExcluir.setBounds(390, 152, 23, 26);
		getContentPane().add(btnExcluir);

		JButton btnInserir = new JButton("");
		btnInserir.setEnabled(false);

		btnInserir.setIcon(new ImageIcon("Salvar.jpg"));
		btnInserir.setBounds(320, 152, 23, 26);
		getContentPane().add(btnInserir);

		JButton btnAtualizar = new JButton("");
		btnAtualizar.setIcon(new ImageIcon("Atualizar.jpg"));
		btnAtualizar.setBounds(355, 152, 23, 26);
		getContentPane().add(btnAtualizar);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon("back.jpg"));
		btnVoltar.setBounds(-1, 152, 23, 26);
		getContentPane().add(btnVoltar);

		// --------------------------------------AÇÃO DOS
		// BOTÕES--------------------------------------
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEscolha telaEscolha = new TelaEscolha();
				telaEscolha.setVisible(true);
				dispose();
			}
		});

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ativarInputs();
				btnInserir.setEnabled(true);
			}
		});

		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfNome.getText().isEmpty()
						|| tfCpf.getText().toString().matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}") == false
						|| tfFuncao.getText().isEmpty()
						|| tfSalario.getText().toString().matches("\\d{4}\\.\\d{2}") == false
						|| tfTelefone.getText().toString().matches("\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}") == false) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				} else {
					bd.inserirFuncionarios(tfNome.getText(), tfCpf.getText(), Float.parseFloat(tfSalario.getText()),
							tfTurno.getSelectedItem().toString(), tfFuncao.getText(), tfTelefone.getText());
					preencherTabela("SELECT * FROM funcionarios ORDER BY id");
					JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!");
					desativarInputs();
					btnInserir.setEnabled(false);
				}
			}
		});

		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtualizarFuncionario atualizarFuncionario = new AtualizarFuncionario();
				atualizarFuncionario.setVisible(true);
				dispose();
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletarFuncionario deletarFuncionario = new DeletarFuncionario();
				deletarFuncionario.setVisible(true);
				dispose();
			}
		});

		JLabel label = new JLabel("");
		label.setIcon(
				new ImageIcon("saida.jpg"));
		label.setBounds(-3, 0, 713, 343);
		getContentPane().add(label);

		preencherTabela("SELECT * FROM funcionarios ORDER BY id");
	}

	public void ativarInputs() {
		tfNome.setEnabled(true);
		tfNome.setBackground(Color.white);
		tfTelefone.setEnabled(true);
		tfTelefone.setBackground(Color.white);
		tfTurno.setEnabled(true);
		tfTurno.setBackground(Color.white);
		tfFuncao.setEnabled(true);
		tfFuncao.setBackground(Color.white);
		tfCpf.setEnabled(true);
		tfCpf.setBackground(Color.white);
		tfSalario.setEnabled(true);
		tfSalario.setBackground(Color.white);
	}

	public void desativarInputs() {
		tfNome.setEnabled(false);
		tfNome.setBackground(Color.gray);
		tfNome.setText("");
		tfTelefone.setEnabled(false);
		tfTelefone.setBackground(Color.gray);
		tfTelefone.setText("");
		tfTurno.setEnabled(false);
		tfTurno.setBackground(Color.gray);
		tfFuncao.setEnabled(false);
		tfFuncao.setBackground(Color.gray);
		tfFuncao.setText("");
		tfCpf.setEnabled(false);
		tfCpf.setBackground(Color.gray);
		tfCpf.setText("");
		tfSalario.setEnabled(false);
		tfSalario.setBackground(Color.gray);
		tfSalario.setText("");
	}

	public void preencherTabela(String query) {

		ArrayList dados = new ArrayList();

		String[] colunas = new String[] { "ID", "CPF", "Nome", "Salário", "Turno", "Função", "Telefone" };

		try {
			bd.executaSQL(query);
			bd.resultset.first();
			do {
				dados.add(new Object[] { bd.resultset.getInt("id"), bd.resultset.getString("cpf"),
						bd.resultset.getString("nome"), bd.resultset.getDouble("salario"),
						bd.resultset.getString("turno"), bd.resultset.getString("funcao"),
						bd.resultset.getString("telefone") });
			} while (bd.resultset.next());
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela!\nERRO: " + sqle.getMessage());
		}

		ModeloTabela modelo = new ModeloTabela(dados, colunas);

		jTableFuncionarios.setModel(modelo);

		jTableFuncionarios.getColumnModel().getColumn(0).setResizable(false);
		jTableFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(42);
		jTableFuncionarios.getColumnModel().getColumn(1).setResizable(false);
		jTableFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(100);
		jTableFuncionarios.getColumnModel().getColumn(2).setResizable(false);
		jTableFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(210);
		jTableFuncionarios.getColumnModel().getColumn(3).setResizable(false);
		jTableFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(50);
		jTableFuncionarios.getColumnModel().getColumn(4).setResizable(false);
		jTableFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(80);
		jTableFuncionarios.getColumnModel().getColumn(5).setResizable(false);
		jTableFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(100);
		jTableFuncionarios.getColumnModel().getColumn(6).setResizable(false);
		jTableFuncionarios.getColumnModel().getColumn(6).setPreferredWidth(110);

		jTableFuncionarios.getTableHeader().setReorderingAllowed(false);
		jTableFuncionarios.setAutoResizeMode(jTableFuncionarios.AUTO_RESIZE_OFF);

		jTableFuncionarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		for (int i = 0; i < colunas.length; i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			jTableFuncionarios.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}
}