/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.EditoresTable.Paginacao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tatty
 * @param <T>
 */
public abstract class ModeloTabela<T> extends AbstractTableModel{
    
   private List<T> lista = new ArrayList<>();

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }
   
   @Override
    public int getRowCount(){
        return lista.size();
    }
    
   @Override
    public Object getValueAt(int fila, int coluna){
        T t = lista.get(fila);
        return  getValueAt(t, coluna);
    }
    
    public abstract Object getValueAt(T t, int coluna);
    
   @Override
     public abstract String getColumnName(int coluna);
    
}
