/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.cadastro;

import Conexao.conexao;
import Model.cadastro.ModalCabecalho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class DAOCabecalho {
    public void upload(ModalCabecalho c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_cabecalho` SET "
                    + "`cabecalho_linha_1`=?,`cabecalho_linha_2`=?,"
                    + "`cabecalho_linha_3`=?,`cabecalho_linha_4`=?, `cabecalho_logo`=? WHERE `cabecalho_id`= 1");
            stmt.setString(1, c.getCabecalho1());
            stmt.setString(2, c.getCabecalho2());
            stmt.setString(3, c.getCabecalho3());
            stmt.setString(4, c.getCabecalho4());
            stmt.setBytes(5, c.getLogo());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
}
