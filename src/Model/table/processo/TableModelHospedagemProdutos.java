/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.table.processo;

import Model.processo.ModalAcompanhantes;
import Model.processo.ModalHospedagemProdutos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matheus
 */
public class TableModelHospedagemProdutos extends AbstractTableModel {

    public List<ModalHospedagemProdutos> dados = new ArrayList<>();
    private String[] colunas = {"ID", "ID P", "Nome", "Valor", "Quantidade"};

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
                return dados.get(linha).getIdProduto();
            case 2:
                return dados.get(linha).getNomeProduto();
            case 3:
                return dados.get(linha).getValorTotal();
            case 4:
                return dados.get(linha).getQuantidade();
        }
        return null;
    }

    public void addRow(ModalHospedagemProdutos q) {
        this.dados.add(q);
        this.fireTableDataChanged();
    }

}
