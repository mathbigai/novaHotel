/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.processo;

import DAO.cadastro.*;
import Conexao.conexao;
import Model.cadastro.ModalQuartoAcompanhantesValores;
import Model.processo.ModalAcompanhantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class DAOAcompanhantes {

    public void create(ModalAcompanhantes c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `tbl_acompanhantes`(`acompanhantes_id`, "
                    + "`acompanhantes_nome`, `acompanhantes_cpf`, `acompanhantes_hospedagem_id`) "
                    + "VALUES (?,?,?,?)");
            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getCpf());
            stmt.setInt(4, c.getHospedagemId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public void update(ModalAcompanhantes c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_acompanhantes` SET "
                    + "`acompanhantes_nome`=?,`acompanhantes_cpf`=? "
                    + "WHERE `acompanhantes_id`=? AND `acompanhantes_hospedagem_id`=?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setInt(3, c.getId());
            stmt.setInt(4, c.getHospedagemId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public void delete(ModalAcompanhantes c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM `tbl_acompanhantes` WHERE "
                    + "`acompanhantes_id`=? AND `acompanhantes_hospedagem_id`=?");
            stmt.setInt(1, c.getId());
            stmt.setInt(2, c.getHospedagemId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
}
