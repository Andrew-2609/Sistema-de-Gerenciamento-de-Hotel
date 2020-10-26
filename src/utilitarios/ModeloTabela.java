package utilitarios;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {
	private ArrayList linhas = null;
	private String[] colunas = null;
	
	
	public ArrayList getLinhas() {
		return linhas;
	}
	
	public void setLinhas(ArrayList dadosLinhas) {
		this.linhas = dadosLinhas;
	}
	
	public String[] getColunas() {
		return colunas;
	}
	
	public void setColunas(String[] nomesColunas) {
		this.colunas = nomesColunas;
	}
	
	public int getColumnCount() {
		return colunas.length;
	}
	
	public int getRowCount() {
		return linhas.size();
	}
	
	public String getColumnName(int numCol) {
		return colunas[numCol];
	}
	
	public Object getValueAt(int numLin, int numCol) {
		Object[] linha = (Object[])getLinhas().get(numLin);
		return linha[numCol];
	}
	
	public ModeloTabela(ArrayList lin, String[] col) {
		setLinhas(lin);
		setColunas(col);
	}
}
