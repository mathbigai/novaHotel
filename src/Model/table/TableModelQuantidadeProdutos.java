/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.table;

import Model.cadastro.ModalProduto;
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
public class TableModelQuantidadeProdutos extends AbstractTableModel {

    public List<ModalProduto> dados = new ArrayList<>();
    private String[] colunas = {"ID", "Nome", "Valor", "Quantidade"};

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
                BigDecimal tempBig = new BigDecimal(Double.toString(dados.get(linha).getValor()));
                NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                return nf.format(tempBig.doubleValue());
            case 3:
                return dados.get(linha).getQuantidade();
        }
        return null;
    }

    public void addRow(ModalProduto q) {
        this.dados.add(q);
        this.fireTableDataChanged();
    }

}
