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
public class DAORelatorioContratoHospedagem {

    conexao conexao = new conexao();

    public void acionaRelatoio(String idHospedagem) throws SQLException {
        Map parametros = new HashMap();
        InputStream stream = getClass().getResourceAsStream("/relatorios/processo/contratoHospedagem.jasper");
        System.out.println(stream);
        parametros.put("REPORT_CONNECTION", conexao.conexao());

        try {
            conexao.executaSQL("SELECT c.*, cli.*, h.* FROM tbl_cabecalho c, tbl_cliente cli "
                    + "INNER JOIN tbl_hospedagem h on cli.cliente_id=h.hospedagem_cliente_id "
                    + "WHERE h.hospedagem_id = " + idHospedagem);
            JRResultSetDataSource relatResul = new JRResultSetDataSource(conexao.rs);
            JasperPrint print = JasperFillManager.fillReport(stream, parametros, relatResul);

            JasperViewer viwer = new JasperViewer(print, false);
            viwer.setVisible(true);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }

    }

    public void acionaRelatoioEmpresa(String idHospedagem) throws SQLException {
        Map parametros = new HashMap();
        InputStream stream = getClass().getResourceAsStream("/relatorios/processo/contratoHospedagemEmpresa.jasper");
        System.out.println(stream);
        parametros.put("REPORT_CONNECTION", conexao.conexao());

        try {
            conexao.executaSQL("SELECT c.*, cli.*, h.*, (SELECT cli.cliente_CPF_CNPJ FROM "
                    + "tbl_cliente cli RIGHT JOIN tbl_hospedagem h on "
                    + "cli.cliente_id=h.hospedagem_cliente_empresa_id "
                    + "WHERE h.hospedagem_id = " + idHospedagem + " AND LENGTH(cli.cliente_CPF_CNPJ) > 11) "
                    + "AS CNPJ FROM tbl_cabecalho c, tbl_cliente cli "
                    + "INNER JOIN tbl_hospedagem h on cli.cliente_id=h.hospedagem_cliente_id "
                    + "WHERE h.hospedagem_id = " + idHospedagem);
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
