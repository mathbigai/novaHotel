/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.cadastro;

import Conexao.conexao;
import Model.cadastro.ModalProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class DAOProduto {
    public void create(ModalProduto c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `tbl_produto`(`produto_nome`, "
                    + "`produto_valor`, `produto_quantidade`) VALUES "
                    + "(?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setDouble(2, c.getValor());
            stmt.setInt(3, c.getQuantidade());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    public void update(ModalProduto c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_produto` SET "
                    + "`produto_nome`=?,`produto_valor`=?,`produto_quantidade`=? "
                    + "WHERE `produto_id`=?");
            stmt.setString(1, c.getNome());
            stmt.setDouble(2, c.getValor());
            stmt.setInt(3, c.getQuantidade());
            stmt.setInt(4, c.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    public void updateQuantidade(ModalProduto c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_produto` SET `produto_quantidade`=? "
                    + "WHERE `produto_id`=?");
            stmt.setInt(1, c.getQuantidade());
            stmt.setInt(2, c.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
}
