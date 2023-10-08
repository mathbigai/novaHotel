/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.table.processo;

import Model.processo.ModalAcompanhantes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matheus
 */
public class TableModelAcompanhantes extends AbstractTableModel {

    DateFormat dataNormal = new SimpleDateFormat("dd/MM/yyyy");
    public List<ModalAcompanhantes> dados = new ArrayList<>();
    private String[] colunas = {"ID", "Nome", "CPF"};

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
                return dados.get(linha).getCpf();
        }
        return null;
    }

    public void addRow(ModalAcompanhantes q) {
        this.dados.add(q);
        this.fireTableDataChanged();
    }

}
