/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.cadastro;

import Conexao.conexao;
import Model.cadastro.ModalCliente;
import Model.cadastro.ModalQuarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class DAOCliente {

    public void create(ModalCliente c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `tbl_cliente`(`cliente_nome`, `cliente_RG`, "
                    + "`cliente_CPF_CNPJ`, `cliente_endereco_logradouro`, `cliente_endereco_numero`, "
                    + "`cliente_endereco_bairro`, `cliente_endereco_cidade`, `cliente_endereco_estado`, "
                    + "`cliente_telefone`, `cliente_endereco_complemento`, `cliente_endereco_cep`,"
                    + "`cliente_razao_social`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getRg());
            stmt.setString(3, c.getCpfCnpj());
            stmt.setString(4, c.getLogradouro());
            stmt.setString(5, c.getNumero());
            stmt.setString(6, c.getBairro());
            stmt.setString(7, c.getCidade());
            stmt.setString(8, c.getEstado());
            stmt.setString(9, c.getCelular());
            stmt.setString(10, c.getComplemento());
            stmt.setString(11, c.getCep());
            stmt.setString(12, c.getRazaoSocial());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public void update(ModalCliente c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_cliente` SET `cliente_nome`=?,"
                    + "`cliente_RG`=?,`cliente_CPF_CNPJ`=?,`cliente_endereco_logradouro`=?,"
                    + "`cliente_endereco_numero`=?,`cliente_endereco_bairro`=?,"
                    + "`cliente_endereco_cidade`=?,`cliente_endereco_estado`=?,"
                    + "`cliente_telefone`=?,`cliente_endereco_complemento`=?,"
                    + "`cliente_endereco_cep`=?, `cliente_razao_social`=? WHERE `cliente_id`=?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getRg());
            stmt.setString(3, c.getCpfCnpj());
            stmt.setString(4, c.getLogradouro());
            stmt.setString(5, c.getNumero());
            stmt.setString(6, c.getBairro());
            stmt.setString(7, c.getCidade());
            stmt.setString(8, c.getEstado());
            stmt.setString(9, c.getCelular());
            stmt.setString(10, c.getComplemento());
            stmt.setString(11, c.getCep());
            stmt.setString(12, c.getRazaoSocial());
            stmt.setInt(13, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
}
