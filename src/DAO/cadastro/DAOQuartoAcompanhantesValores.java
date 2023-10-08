/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.cadastro;

import Conexao.conexao;
import Model.cadastro.ModalQuartoAcompanhantesValores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class DAOQuartoAcompanhantesValores {
    public void create(ModalQuartoAcompanhantesValores c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `tbl_quarto_acompanhantes`(`quarto_acompanhantes_id` ,"
                    + "`quarto_acompanhantes_quarto_id`, `quarto_cama_quantidade_acompanhantes`, "
                    + "`quarto_acompanhantes_valor`) VALUES"
                    + "(?,?,?,?)");
            stmt.setInt(1, c.getId());
            stmt.setInt(2, c.getCodigoQuarto());
            stmt.setInt(3, c.getQuantidade());
            stmt.setDouble(4, c.getValorDiaria());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    public void update(ModalQuartoAcompanhantesValores c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_quarto_acompanhantes` SET "
                    + "`quarto_cama_quantidade_acompanhantes`=?,`quarto_acompanhantes_valor`=? "
                    + "WHERE `quarto_acompanhantes_id`=? AND `quarto_acompanhantes_quarto_id`=?");
            stmt.setInt(1, c.getQuantidade());
            stmt.setDouble(2, c.getValorDiaria());
            stmt.setInt(3, c.getId());
            stmt.setInt(4, c.getCodigoQuarto());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    
    public void delete(ModalQuartoAcompanhantesValores c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM `tbl_quarto_acompanhantes` WHERE "
                    + "`quarto_acompanhantes_id`=? AND `quarto_acompanhantes_quarto_id`=?");
            stmt.setInt(1, c.getId());
            stmt.setInt(2, c.getCodigoQuarto());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
}
