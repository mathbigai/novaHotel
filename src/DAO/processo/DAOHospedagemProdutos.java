/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.processo;

import Conexao.conexao;
import Model.processo.ModalHospedagemProdutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class DAOHospedagemProdutos {
    public void create(ModalHospedagemProdutos c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `tbl_produtos_hospedagem`"
                    + "(`produtos_hospedagem_id`, `produtos_hospedagem_produtos_id`, "
                    + "`produtos_hospedagem_nome`, `produtos_hospedagem_produtos_valor`, "
                    + "`produtos_hospedagem_quantidade`, `produtos_hospedagem_hospedagem_id`)"
                    + " VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, c.getId());
            stmt.setInt(2, c.getIdProduto());
            stmt.setString(3, c.getNomeProduto());
            stmt.setDouble(4, c.getValorTotal());
            stmt.setInt(5, c.getQuantidade());
            stmt.setInt(6, c.getIdHospedagem());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    
    public void update(ModalHospedagemProdutos c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_produtos_hospedagem` SET "
                    + "`produtos_hospedagem_produtos_valor`=?,`produtos_hospedagem_quantidade`=?"
                    + " WHERE `produtos_hospedagem_id`=? AND `produtos_hospedagem_hospedagem_id`=?");
            stmt.setDouble(1, c.getValorTotal());
            stmt.setInt(2, c.getQuantidade());
            stmt.setInt(3, c.getId());
            stmt.setInt(4, c.getIdHospedagem());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    public void delete(ModalHospedagemProdutos c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM `tbl_produtos_hospedagem` WHERE "
                    + "`produtos_hospedagem_id`=? AND `produtos_hospedagem_hospedagem_id`=?");
            stmt.setInt(1, c.getId());
            stmt.setInt(2, c.getIdHospedagem());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
}
