package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import utilitarios.Banco;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TelaLogin extends JFrame {

	private JTextField inputUsuario;
	private JPasswordField inputSenha;
	
	public static String retorno = "0";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		Banco banco = new Banco();
		banco.conectar();
		
		setTitle("Login");
		setBounds(100, 100, 700, 369);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		inputUsuario = new JTextField();
		inputUsuario.setBounds(269, 101, 144, 20);
		getContentPane().add(inputUsuario);
		inputUsuario.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setFont(new Font("Bodoni MT", Font.BOLD, 18));
		lblUsurio.setForeground(new Color(255, 255, 255));
		lblUsurio.setBounds(318, 68, 90, 20);
		getContentPane().add(lblUsurio);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(270, 157, 143, 20);
		getContentPane().add(inputSenha);
		inputSenha.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Bodoni MT", Font.BOLD, 18));
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(318, 133, 71, 23);
		getContentPane().add(lblSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				banco.logar(inputUsuario.getText(), inputSenha.getText());
				if(retorno == "1") {
					dispose();
				}else {
				}
			}
		});
		btnEntrar.setFont(new Font("Bell MT", Font.BOLD, 15));
		btnEntrar.setBounds(300, 218, 89, 23);
		getContentPane().add(btnEntrar);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("hotel.png"));
		label_1.setBounds(-10, 0, 706, 341);
		getContentPane().add(label_1);
		
		JLabel labelConectado = new JLabel("");
		
		labelConectado.setBounds(10, 307, 185, 23);
		getContentPane().add(labelConectado);
		
		if(banco.estaConectado() == true) {
			labelConectado.setForeground(Color.green);
			labelConectado.setBackground(Color.black);
			labelConectado.setText("Banco de dados conectado");
		}else {
			labelConectado.setForeground(Color.red);
			labelConectado.setBackground(Color.black);
			labelConectado.setText("Banco de dados não conectado");
		}
	}
}
