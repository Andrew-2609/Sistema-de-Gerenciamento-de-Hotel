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
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import utilitarios.Banco;

public class AtualizarFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JFormattedTextField tfCpf;
	private JFormattedTextField tfSalario;
	private JTextField tfFuncao;
	private JFormattedTextField tfTelefone;
	private JTextField tfId;
	private JComboBox selectTurno;

	Banco bd = new Banco();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarFuncionario frame = new AtualizarFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AtualizarFuncionario() {
		bd.conectar();

		setTitle("Atualizar Funcionário");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblCpf.setBounds(19, 72, 55, 16);
		contentPane.add(lblCpf);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblNome.setBounds(19, 24, 55, 16);
		contentPane.add(lblNome);

		JLabel lblSalario = new JLabel("Sal\u00E1rio");
		lblSalario.setForeground(Color.WHITE);
		lblSalario.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblSalario.setBounds(309, 70, 55, 16);
		contentPane.add(lblSalario);

		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setForeground(Color.WHITE);
		lblTurno.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblTurno.setBounds(19, 117, 55, 16);
		contentPane.add(lblTurno);

		JLabel lblFuncao = new JLabel("Fun\u00E7\u00E3o");
		lblFuncao.setForeground(Color.WHITE);
		lblFuncao.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblFuncao.setBounds(587, 24, 55, 16);
		contentPane.add(lblFuncao);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblTelefone.setBounds(587, 72, 55, 16);
		contentPane.add(lblTelefone);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(309, 24, 48, 14);
		contentPane.add(lblId);

		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setColumns(10);
		tfNome.setBounds(19, 40, 171, 20);
		contentPane.add(tfNome);

		tfCpf = new JFormattedTextField();
		tfCpf.setEditable(false);
		tfCpf.setColumns(10);
		tfCpf.setBounds(19, 86, 104, 20);
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

		tfSalario = new JFormattedTextField();
		tfSalario.setEditable(false);
		tfSalario.setColumns(10);
		tfSalario.setBounds(309, 84, 104, 20);
		contentPane.add(tfSalario);
		
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

		tfFuncao = new JTextField();
		tfFuncao.setEditable(false);
		tfFuncao.setColumns(10);
		tfFuncao.setBounds(587, 40, 104, 20);
		contentPane.add(tfFuncao);

		tfTelefone = new JFormattedTextField();
		tfTelefone.setEditable(false);
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(587, 86, 104, 20);
		contentPane.add(tfTelefone);
		
		MaskFormatter mascaraTelefone;
		try {
			mascaraTelefone = new MaskFormatter("(##) #####-####");
			mascaraTelefone.install(tfTelefone);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
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
		
		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBounds(309, 38, 104, 20);
		contentPane.add(tfId);

		String[] opcoes = new String[] { "Manhã", "Tarde", "Noite", "Integral" };
		selectTurno = new JComboBox(opcoes);
		selectTurno.setEditable(false);
		selectTurno.setBounds(19, 134, 104, 20);
		contentPane.add(selectTurno);

		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(
				new ImageIcon("back.jpg"));
		btnVoltar.setBounds(19, 165, 23, 26);
		contentPane.add(btnVoltar);
		btnVoltar.setToolTipText("Voltar");

		JButton btnAtualizar = new JButton("");
		btnAtualizar.setIcon(new ImageIcon(
				"Atualizar.jpg"));
		btnAtualizar.setBounds(353, 163, 23, 26);
		contentPane.add(btnAtualizar);
		btnAtualizar.setVisible(false);
		btnAtualizar.setToolTipText("Atualizar Registro");

		JButton btnCarregarDados = new JButton("");
		btnCarregarDados.setIcon(new ImageIcon("naozei.jpg"));
		btnCarregarDados.setFont(new Font("Bodoni MT", Font.BOLD, 12));
		btnCarregarDados.setBounds(425, 34, 23, 26);
		contentPane.add(btnCarregarDados);
		btnCarregarDados.setToolTipText("Carregar Registro");
		
		JButton btnResetar = new JButton("");
		btnResetar.setIcon(new ImageIcon("apagar.jpg"));
		btnResetar.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		btnResetar.setBounds(353, 116, 23, 26);
		contentPane.add(btnResetar);
		btnResetar.setVisible(false);
		btnResetar.setToolTipText("Resetar");
		
		JLabel lblImagemFundo = new JLabel("");
		lblImagemFundo.setIcon(
				new ImageIcon("saida.jpg"));
		lblImagemFundo.setBounds(0, 0, 704, 341);
		contentPane.add(lblImagemFundo);
		
		//-------------------------AÇÕES DOS BOTÕES-------------------------
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GerenciamentoDeFuncionarios gdf = new GerenciamentoDeFuncionarios();
				gdf.setVisible(true);
				dispose();
			}
		});
		
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfNome.getText().isEmpty()
						|| tfCpf.getText().toString().matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}") == false
						|| tfFuncao.getText().isEmpty() || tfSalario.getText().toString().matches("\\d{4}\\.\\d{2}") == false || tfTelefone.getText().toString().matches("\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}") == false) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				} else {
					bd.atualizarFuncionarios(Integer.parseInt(tfId.getText()), tfNome.getText(), tfCpf.getText(),
							Float.parseFloat(tfSalario.getText()), selectTurno.getSelectedItem().toString(),
							tfFuncao.getText(), tfTelefone.getText());
				}
			}
		});

		btnCarregarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfId.getText().toString().matches("\\d+")) {
					String idDigitado = tfId.getText();
					try {
						String query = "SELECT * FROM funcionarios WHERE id = " + idDigitado;
						bd.executaSQL(query);
						while (bd.resultset.next()) {
							if (idDigitado.equals(bd.resultset.getString("id"))) {
								JOptionPane.showMessageDialog(null, "Registro carregado!");
								tfNome.setText(bd.resultset.getString("nome"));
								tfCpf.setText(bd.resultset.getString("cpf"));
								selectTurno.setSelectedItem(bd.resultset.getString("turno"));
								tfSalario.setText(bd.resultset.getString("salario"));
								tfFuncao.setText(bd.resultset.getString("funcao"));
								tfTelefone.setText(bd.resultset.getString("telefone"));

								tornarEditaveis();

								btnCarregarDados.setVisible(false);
								btnResetar.setVisible(true);
								btnAtualizar.setVisible(true);
								tfId.setEditable(false);
							}
						}
					} catch (Exception e) {
						System.out.println("Não deixe o campo vazio!");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Informe apenas números!");
				}
			}
		});

		btnResetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Informe um Registro!");
				desativarEdicao();
				resetarValores();
				btnResetar.setVisible(false);
				btnCarregarDados.setVisible(true);
				btnAtualizar.setVisible(false);
				tfId.setEditable(true);
			}
		});
	}

	public void tornarEditaveis() {
		tfNome.setEditable(true);
		tfCpf.setEditable(true);
		selectTurno.setEditable(true);
		tfSalario.setEditable(true);
		tfFuncao.setEditable(true);
		tfTelefone.setEditable(true);
	}

	public void desativarEdicao() {
		tfNome.setEditable(false);
		tfCpf.setEditable(false);
		selectTurno.setEditable(false);
		tfSalario.setEditable(false);
		tfFuncao.setEditable(false);
		tfTelefone.setEditable(false);
	}
	
	public void resetarValores() {
		tfNome.setText("");
		tfCpf.setText("");
		selectTurno.setSelectedItem("Manhã");
		tfSalario.setText("");
		tfFuncao.setText("");
		tfTelefone.setText("");
	}
}