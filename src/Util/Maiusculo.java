/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Tatty
 */
public class Maiusculo extends PlainDocument{
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException { 
        if (str == null) { 
            return; } 
        super.insertString(offs, str.toUpperCase(), a); 
    } 
}

