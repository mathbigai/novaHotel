/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.EditoresTable.Paginacao;

import java.util.List;

/**
 *
 * @author Tatty
 * @param <T>
 */
public interface ProvedorDadosPaginacao<T> {
    
    int getTotalRowCount();
    List <T> getRows(int startIndex, int endIndex);
    
}
