/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Matheus
 */
public class ModalData {
    private static DateTime dataHoje;

    /**
     * @return the dataHoje
     */
    public DateTime getDataHoje() {
        return dataHoje;
    }

    /**
     * @param dataHoje the dataHoje to set
     */
    public void setDataHoje(DateTime dataHoje) {
        this.dataHoje = dataHoje;
    }
  
    
}
