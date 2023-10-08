/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.cadastro;

import Conexao.conexao;
import Model.cadastro.ModalUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class DAOUsuario {

    public void create(ModalUsuario c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `tbl_usuario`(`usuario_nome`, `usuario_login`, "
                    + "`usuario_tipo`, `usuario_inativo`) VALUES "
                    + "(?,?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getLogin());
            stmt.setString(3, c.getTipo());
            stmt.setBoolean(4, c.isInativo());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public void update(ModalUsuario c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_usuario` SET "
                    + "`usuario_nome`=?,`usuario_login`=?,"
                    + "`usuario_tipo`=?,`usuario_inativo`=? "
                    + "WHERE `usuario_id`=?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getLogin());
            stmt.setString(3, c.getTipo());
            stmt.setBoolean(4, c.isInativo());
            stmt.setInt(5, c.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    public void updatePass(ModalUsuario u, int flag) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tbl_usuario SET usuario_senha = ? WHERE usuario_id = ?");
            stmt.setString(1, u.getSenha());
            stmt.setInt(2, u.getId());

            stmt.executeUpdate();

            if (flag == 1) {
                JOptionPane.showMessageDialog(null, "Senha inserida com sucesso");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir a senha: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public boolean checkLogin(String login, String senha) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_usuario WHERE usuario_login = ? AND usuario_senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();
            if (rs.next() && rs.getBoolean("usuario_inativo") == false) {
                check = true;
            } else {
                JOptionPane.showMessageDialog(null, "Problemas para fazer o login, verifique a senha ou o login.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar usuário: " + ex);
        } finally {
            conexao.desconecta(con, stmt, rs);
        }

        return check;
    }
}
