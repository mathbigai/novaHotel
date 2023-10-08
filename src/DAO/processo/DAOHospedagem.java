/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.processo;

import Conexao.conexao;
import Model.processo.ModalHospedagem;
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
public class DAOHospedagem {

    public void create(ModalHospedagem c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO `tbl_hospedagem`(`hospedagem_cliente_id`, "
                    + "`hospedagem_cliente_nome`, `hospedagem_quarto_id`, `hospedagem_quarto_numero`, "
                    + "`hospedagem_usuario_id`, `hospedagem_usuario_nome`, `hospedagem_status`, "
                    + "`hospedagem_checkin_data`, `hospedagem_checkin_hora`, `hospedagem_valor_diaria`, "
                    + "`hospedagem_forma_pagamento`,`hospedagem_veiculo_placa`,`hospedagem_veiculo_marca`,"
                    + "`hospedagem_cliente_empresa_id`, `hospedagem_cliente_empresa_nome`, `hospedagem_diarias_pagas`) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, c.getClienteId());
            stmt.setString(2, c.getClienteNome());
            stmt.setInt(3, c.getQuartoId());
            stmt.setString(4, c.getQuartoNumero());
            stmt.setInt(5, c.getUsuarioId());
            stmt.setString(6, c.getUsuarioNome());
            stmt.setString(7, c.getStatus());
            stmt.setDate(8, new java.sql.Date(c.getCheckinData().getTime()));
            stmt.setTime(9, new java.sql.Time(c.getCheckinHora().getTime()));
            stmt.setDouble(10, c.getValorHospedagem());
            stmt.setString(11, c.getFormaPagamento());
            stmt.setString(12, c.getPlaca());
            stmt.setString(13, c.getModelo());
            stmt.setInt(14, c.getClienteEmpresaId());
            stmt.setString(15, c.getClienteEmpresaNome());
            stmt.setInt(16, c.getDiariasPagas());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente hospedado com sucesso."
                    + "\n"
                    + "Informe quais são seus acompanhantes, se holver algum.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public void updateValorDiaria(ModalHospedagem u) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_hospedagem` SET hospedagem_valor_diaria = ? "
                    + "WHERE hospedagem_id = ?");
            stmt.setDouble(1, u.getValorHospedagem());
            stmt.setInt(2, u.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir a senha: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public void updateValorTotal(ModalHospedagem u) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_hospedagem` SET hospedagem_valor_total = ? "
                    + "WHERE hospedagem_id = ?");
            stmt.setDouble(1, u.getValorTotal());
            stmt.setInt(2, u.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir a senha: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public void updateDesistir(ModalHospedagem c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_hospedagem` SET "
                    + "`hospedagem_status`=? WHERE hospedagem_id = ?");
            stmt.setString(1, c.getStatus());
            stmt.setInt(2, c.getId());
            JOptionPane.showMessageDialog(null, "Quarto liberado");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir a senha: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    
        public void updateCheckout(ModalHospedagem c) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE `tbl_hospedagem` SET "
                    + "`hospedagem_status`=?,`hospedagem_checkout_data`=?,"
                    + "`hospedagem_checkout_hora`=?,`hospedagem_valor_total`=?,"
                    + "`hospedagem_total_diarias`=?,`hospedagem_valor_desconto`=?, `hospedagem_quantidade_diarias`=? "
                    + "WHERE hospedagem_id = ?");
            stmt.setString(1, c.getStatus());
            stmt.setDate(2, new java.sql.Date(c.getCheckoutData().getTime()));
            stmt.setTime(3, new java.sql.Time(c.getCheckoutHora().getTime()));
            stmt.setDouble(4, c.getValorTotal());
            stmt.setDouble(5, c.getValorTotalDiaria());
            stmt.setDouble(6, c.getValorDesconto());
            stmt.setDouble(7, c.getQuantidadeDiaria());
            stmt.setInt(8, c.getId());
            JOptionPane.showMessageDialog(null, "CheckOut realizado com sucesso.");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir a senha: " + ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public List<ModalHospedagem> readGeral(String sql) {
        Connection con = null;
        try {
            con = conexao.conexao();
        } catch (SQLException ex) {

        }
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ModalHospedagem> listaHonorario = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ModalHospedagem modeloHospedagem = new ModalHospedagem();

                modeloHospedagem.setValorTotal(rs.getDouble("hospedagem_valor_total"));
                modeloHospedagem.setCheckinData(rs.getDate("hospedagem_checkin_data"));
                modeloHospedagem.setCheckoutData(rs.getDate("hospedagem_checkout_data"));
                modeloHospedagem.setId(rs.getInt("hospedagem_id"));
                modeloHospedagem.setQuartoNumero(rs.getString("hospedagem_quarto_numero"));
                modeloHospedagem.setClienteNome(rs.getString("hospedagem_cliente_nome"));
                modeloHospedagem.setUsuarioNome(rs.getString("hospedagem_usuario_nome"));

                listaHonorario.add(modeloHospedagem);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar itens do Processo: " + ex);
        } finally {
            conexao.desconecta(con, stmt, rs);
        }

        return listaHonorario;
    }
}
