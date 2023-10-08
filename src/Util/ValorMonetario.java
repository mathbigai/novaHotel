/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Matheus
 */
public class ValorMonetario {

    public static String valorMonetario;
    
    public void formatarValor(String valor) {
        String sv = valor;
        String vsf = sv.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
        Double valorReal = new Double(vsf);
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        valorMonetario = nf.format(valorReal);
    }
}
