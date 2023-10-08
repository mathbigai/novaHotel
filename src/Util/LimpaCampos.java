/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Matheus
 */
public class LimpaCampos {

    public static void limpaCampos(Component[] componente) {
        Color colorPadrao = new Color(255, 255, 255);
        for (Component comp : componente) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
                ((JTextField) comp).setBackground(colorPadrao);
            }
            if (comp instanceof JFormattedTextField) {
                ((JFormattedTextField) comp).setText("");
                ((JFormattedTextField) comp).setBackground(colorPadrao);
            }
            if (comp instanceof JTextArea) {
                ((JTextArea) comp).setText("");
                ((JTextArea) comp).setBackground(colorPadrao);
            }
            if (comp instanceof JComboBox) {
                if (((JComboBox) comp).getItemCount() > 0) {
                    ((JComboBox) comp).setSelectedIndex(0);
                }
            }
            if (comp instanceof JCheckBox) {
                ((JCheckBox) comp).setSelected(false);
            }
        }
    }
}
