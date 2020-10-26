package interfaces;

import utilitarios.Banco;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DeletarFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	Banco banco = new Banco();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletarFuncionario frame = new DeletarFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeletarFuncionario() {
		banco.conectar();
		
		setTitle("Demitir Funcionário");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.WHITE);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Bodoni MT", Font.BOLD, 15));
		lblId.setBounds(199, 78, 46, 14);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setBounds(179, 93, 86, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JButton btnDeletar = new JButton("");
		btnDeletar.setIcon(new ImageIcon("apagar.jpg"));
		btnDeletar.setBounds(209, 131, 26, 26);
		contentPane.add(btnDeletar);
		btnDeletar.setToolTipText("Demitir Funcionário");
		
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().toString().matches("\\d+")) {
					String idDigitado = tfId.getText();
					int idDeletado = Integer.parseInt(tfId.getText());
					
					try {
						String query = "SELECT id FROM funcionarios WHERE id = " + idDeletado;
						banco.executaSQL(query);
							while(banco.resultset.next()) {
								if(idDigitado.equals(banco.resultset.getString("id"))) {
									banco.deletarFuncionario(idDeletado);
								}
							}
					} catch (SQLException e1) {
					}
				}else {
					JOptionPane.showMessageDialog(null, "Digite apenas números!");
				}
			}
		});
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setIcon(new ImageIcon("back.jpg"));
		btnVoltar.setBounds(10, 131, 23, 26);
		contentPane.add(btnVoltar);
		btnVoltar.setToolTipText("Voltar");
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciamentoDeFuncionarios gdf = new GerenciamentoDeFuncionarios();
				gdf.setVisible(true);
				dispose();
			}
		});
		
		JLabel labelImagemFundo = new JLabel("");
		labelImagemFundo.setIcon(new ImageIcon("demitirFuncionario.jpg"));
		labelImagemFundo.setBounds(0, 0, 444, 271);
		contentPane.add(labelImagemFundo);
	}
}
