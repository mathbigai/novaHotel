/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.table.cadastro;

import Model.cadastro.ModalProduto;
import Model.cadastro.ModalUsuario;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matheus
 */
public class TableModelUsuario extends AbstractTableModel {

    public List<ModalUsuario> dados = new ArrayList<>();
    private String[] colunas = {"ID", "Nome", "Login", "Inátivo?"};

    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getId();
            case 1:
                return dados.get(linha).getNome();
            case 2:
                return dados.get(linha).getLogin();
            case 3:
                return dados.get(linha).isInativo();
        }
        return null;
    }
    
     @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Boolean.class;
        }
        return null;
    }

    public void addRow(ModalUsuario q) {
        this.dados.add(q);
        this.fireTableDataChanged();
    }

}
