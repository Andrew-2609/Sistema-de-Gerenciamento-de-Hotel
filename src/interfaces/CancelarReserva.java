	package interfaces;
	
	import java.awt.Color;
	import java.awt.EventQueue;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.SQLException;
	
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	import javax.swing.border.EmptyBorder;
	
	import utilitarios.Banco;
	import javax.swing.SwingConstants;
	
	public class CancelarReserva extends JFrame {
		
		private JPanel contentPane;
		private JTextField tfId;
	
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						CancelarReserva frame = new CancelarReserva();
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
		public CancelarReserva() {
			Banco banco = new Banco();
			banco.conectar();
			
			setTitle("Cancelar Reserva");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 707, 368);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			setLocationRelativeTo(null);
			setResizable(false);
			
			tfId = new JTextField();
			tfId.setBounds(306, 128, 96, 20);
			contentPane.add(tfId);
			tfId.setColumns(10);
			
			JButton btnApagarRegistro = new JButton("Apagar registro");
			btnApagarRegistro.setFont(new Font("Bodoni MT", Font.BOLD, 15));
			btnApagarRegistro.setBounds(146, 185, 150, 23);
			contentPane.add(btnApagarRegistro);
			
			JButton btnVerificarRegistro = new JButton("Verificar registro");
			btnVerificarRegistro.setFont(new Font("Bodoni MT", Font.BOLD, 15));
			btnVerificarRegistro.setBounds(412, 185, 150, 23);
			contentPane.add(btnVerificarRegistro);
			
			JButton btnVoltar = new JButton("Voltar");
			btnVoltar.setFont(new Font("Bodoni MT", Font.BOLD, 15));
			btnVoltar.setBounds(306, 307, 96, 23);
			contentPane.add(btnVoltar);
			
			JLabel lblCancelarReserva = new JLabel("Cancelar Reserva");
			lblCancelarReserva.setForeground(Color.WHITE);
			lblCancelarReserva.setFont(new Font("Bodoni MT", Font.BOLD, 30));
			lblCancelarReserva.setBounds(242, 28, 222, 35);
			contentPane.add(lblCancelarReserva);
			
			JLabel lblId = new JLabel("ID");
			lblId.setHorizontalAlignment(SwingConstants.CENTER);
			lblId.setForeground(Color.WHITE);
			lblId.setFont(new Font("Bodoni MT", Font.BOLD, 15));
			lblId.setBounds(306, 103, 96, 14);
			contentPane.add(lblId);
			
			JLabel imgFundo = new JLabel("");
			imgFundo.setIcon(new ImageIcon("saida.jpg"));
			imgFundo.setBounds(0, 0, 704, 341);
			contentPane.add(imgFundo);
			setVisible(true);
			
			btnVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CadastrarHospede ch = new CadastrarHospede();
					ch.setVisible(true);
					dispose();
				}
			});
			
			btnVerificarRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfId.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Insira algum valor!");
					}else {
						int id = Integer.parseInt(tfId.getText());
						
						try {
							String query = "SELECT id FROM hospedes WHERE id = " + id;
							banco.executaSQL(query);
							while(banco.resultset.next()) {
								if(id == banco.resultset.getInt("id")) {
									banco.verificarHospedes(id);
								}else {
									
								}
							}
						}catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Registro inexistente");
						}
					}
					
				}
			});
			
			btnApagarRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfId.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Insira algum valor!");
					}else {
						int id = Integer.parseInt(tfId.getText());
						
						try {
							String query = "SELECT id FROM hospedes WHERE id = " + id;
							banco.executaSQL(query);
							while(banco.resultset.next()) {
								if(id == banco.resultset.getInt("id")) {
									banco.removerHospedes(id);
									JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
								}else {
									JOptionPane.showMessageDialog(null, "Registro inexistente");
								}
							}
						} catch (SQLException e1) {
						}
					}
	//				banco.removerHospedes(n_quarto);
				}
			});
		}
	
	}