/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.table.cadastro;

import Model.cadastro.ModalQuarto;
import Model.cadastro.ModalQuartoAcompanhantesValores;
import Util.ValorMonetario;
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
public class TableModelQuartoAcompanhanteValores extends AbstractTableModel {
    
    public List<ModalQuartoAcompanhantesValores> dados = new ArrayList<>();
    private String[] colunas = {"ID", "Quantidade", "Valor"};

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
                return dados.get(linha).getQuantidade();
            case 2:
                return dados.get(linha).getValorDiaria();
        }
        return null;
    }

    public void addRow(ModalQuartoAcompanhantesValores q) {
        this.dados.add(q);
        this.fireTableDataChanged();
    }

}
