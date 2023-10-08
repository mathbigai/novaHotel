/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.relatorios.processo;

import Conexao.conexao;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Matheus
 */
public class DAORelatorioFechaCaixa {

    conexao conexao = new conexao();

    public void acionaRelatoio(String sql, String texto) throws SQLException {
        Map parametros = new HashMap();
        InputStream stream = getClass().getResourceAsStream("/relatorios/processo/fechamentoCaixa.jasper");
        System.out.println(stream);
        parametros.put("REPORT_CONNECTION", conexao.conexao());
        parametros.put("periodo", texto);

        try {
            conexao.executaSQL(sql);
            JRResultSetDataSource relatResul = new JRResultSetDataSource(conexao.rs);
            JasperPrint print = JasperFillManager.fillReport(stream, parametros, relatResul);

            JasperViewer viwer = new JasperViewer(print, false);
            viwer.setVisible(true);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }
}
