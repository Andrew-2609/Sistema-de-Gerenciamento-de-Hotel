package utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import interfaces.TelaEscolha;
import interfaces.TelaLogin;

public class Banco {
	private Connection connection = null;
	private Statement statement = null;
	public ResultSet resultset = null;

	private String login;
	private String senha;

	private String ordem = "";

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getOrdem() {
		return this.ordem;
	}

	public void conectar() {
		String servidor = "jdbc:mysql://localhost:3306/hotel";
		String usuario = "root";
		String senha = "";
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public boolean estaConectado() {
		if (this.connection != null) {
//			 System.out.println("Foi sal");
			return true;
		} else {
			return false;
		}
	}

	public void desconecta() {
		try {
			this.connection.close();
		} catch (SQLException sqle) {
			System.out.println("Erro ao fechar a conexão!\nErro: " + sqle.getMessage());
		}
	}

	public void logar(String login, String senha) {
		this.login = login;
		this.senha = senha;

		String loginBanco = "";
		String senhaBanco = "";
		String query = "select * from login";
		try {
			this.resultset = this.statement.executeQuery(query);
			while (this.resultset.next()) {
				loginBanco = this.resultset.getString("usuario");
				senhaBanco = this.resultset.getString("senha");
			}

			if (loginBanco.equals(this.login) && senhaBanco.equals(this.senha)) {
				TelaLogin.retorno = "1";
				TelaEscolha te = new TelaEscolha();
				te.setVisible(true);
			} else {
				TelaLogin.retorno = "0";
				JOptionPane.showMessageDialog(null, "Login ou Senha incorreta");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void executaSQL(String sql) {
		try {
			this.statement = this.connection.createStatement(this.resultset.TYPE_SCROLL_INSENSITIVE,
					this.resultset.CONCUR_READ_ONLY);
			this.resultset = this.statement.executeQuery(sql);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO: " + ex.getMessage());
		} catch (Exception fds) {

		}
	}

	public void adicionarHospedes(String cpf, String nome, String telefone, String passaporte, String tipoQuarto, String data_nasc,
			String metodo_pagamento, String data_entrada, String data_saida) {
		try {
			String query = "INSERT INTO hospedes(cpf, nome, telefone, passaporte, tipoQuarto, data_nasc, metodo_pagamento, data_entrada, data_saida) VALUES ('"
					+ cpf + "','" + nome + "','" + telefone + "','" + passaporte + "', '" + tipoQuarto + "','" + data_nasc + "','" + metodo_pagamento
					+ "','" + data_entrada + "','" + data_saida + "')";
			this.statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void atualizarHospedes(int id, String tipoQuarto, String cpf, String n, String t, String p, String d_nasc,
			String m_pagamento, String d_entrada, String d_saida) {
		try {
			String query = "update hospedes set tipoQuarto = '" + tipoQuarto + "', cpf = '" + cpf + "', nome = '" + n
					+ "', telefone = '" + t + "', passaporte = '" + p + "', data_nasc = '" + d_nasc
					+ "', metodo_pagamento = '" + m_pagamento + "', data_entrada = '" + d_entrada + "', data_saida = '"
					+ d_saida + "'" + " where id = " + id;
			this.statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Usuario atualizado!");
			query = "select * from hospedes";
			this.resultset = this.statement.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void removerHospedes(int v) {
		try {
			String query = "DELETE FROM hospedes WHERE id = " + v;
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		}
	}

	public void verificarHospedes(int v) {
		try {
			String hospedes = "";
			String query = "select * from hospedes where id = " + v;
			this.resultset = this.statement.executeQuery(query);
			while (this.resultset.next()) {
				hospedes += "ID: " + this.resultset.getInt("id") + "\nCPF: " + this.resultset.getString("cpf") + "\nNome: " + this.resultset.getString("nome")
						+ "\nTelefone: " + this.resultset.getString("telefone") + "\nPassaporte: "
						+ this.resultset.getString("passaporte") + "\nData de Nascimento: "
						+ this.resultset.getDate("data_nasc") + "\nMetodo de Pagamento: "
						+ this.resultset.getString("metodo_pagamento") + "\nData de Entrada: "
						+ this.resultset.getDate("data_entrada") + "\nData de Saída: "
						+ this.resultset.getDate("data_saida");
				hospedes += "\n\n";
			}
			JOptionPane.showMessageDialog(null, hospedes);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void inserirFuncionarios(String nome, String cpf, Float salario, String turno, String funcao,
			String telefone) {
		try {
			String query = "INSERT INTO funcionarios(nome, cpf, salario, turno, funcao, telefone) VALUES ('" + nome
					+ "', '" + cpf + "', " + salario + ", '" + turno + "', '" + funcao + "', '" + telefone + "');";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void atualizarFuncionarios(int id, String nome, String cpf, Float salario, String turno, String funcao,
			String telefone) {
		try {
			String query = "UPDATE funcionarios SET nome = '" + nome + "', cpf = '" + cpf + "', salario = " + salario
					+ ", turno = '" + turno + "', funcao = '" + funcao + "', telefone = '" + telefone + "' WHERE id = "
					+ id;
			this.statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void deletarFuncionario(int id) {
		try {
			String query = "DELETE FROM funcionarios WHERE id = " + id;
			this.statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
