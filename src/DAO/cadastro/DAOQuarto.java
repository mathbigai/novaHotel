/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.cadastro;

import Conexao.conexao;
import Model.cadastro.ModalQuarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class DAOQuarto {

    public void create(ModalQuarto c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `tbl_quarto`(`quarto_numero`, `quarto_andar`, "
                    + "`quarto_descricao`, `quarto_tipo`, `quarto_itens_dispo`, `quarto_status`) VALUES "
                    + "(?,?,?,?,?,?)");
            stmt.setString(1, c.getNumero());
            stmt.setString(2, c.getAndar());
            stmt.setString(3, c.getDescricao());
            stmt.setString(4, c.getTipo());
            stmt.setString(5, c.getItensDispo());
            stmt.setString(6, c.getStatus());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    
    public void update(ModalQuarto c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_quarto` SET `quarto_numero`=?,"
                    + "`quarto_andar`=?,`quarto_descricao`=?,"
                    + "`quarto_tipo`=?,`quarto_itens_dispo`=?,"
                    + "`quarto_status`=? WHERE `quarto_id`=?");
            stmt.setString(1, c.getNumero());
            stmt.setString(2, c.getAndar());
            stmt.setString(3, c.getDescricao());
            stmt.setString(4, c.getTipo());
            stmt.setString(5, c.getItensDispo());
            stmt.setString(6, c.getStatus());
            stmt.setInt(7, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    public void updateStatus(ModalQuarto c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_quarto` SET `quarto_status`=? WHERE `quarto_id`=?");
            stmt.setString(1, c.getStatus());
            stmt.setInt(2, c.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
}
