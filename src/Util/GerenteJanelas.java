/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Matheus
 */
public class GerenteJanelas {

    private static JDesktopPane jDesktopPane;

    public GerenteJanelas(JDesktopPane jDesktopPane) {
        GerenteJanelas.jDesktopPane = jDesktopPane;
    }

    public static void abrirjanelas(JInternalFrame jInternalFrame) {
        if (jInternalFrame.isVisible()) {
            jInternalFrame.toFront();
            jInternalFrame.requestFocus();
        } else {
            jDesktopPane.add(jInternalFrame);
            jInternalFrame.setVisible(true);
        }

    }

}
