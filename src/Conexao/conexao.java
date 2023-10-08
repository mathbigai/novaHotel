/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import Util.Verificar.VerificarPropriedades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class conexao {

    private static String drive = "com.mysql.jdbc.Driver";
    public static String nomeBanco = "";
    public static String url = "";
    public static String user = "";
    public static String pass = "";
    public static Connection con;
    public static Statement stam; //Busca no banco
    public static ResultSet rs; // Amarzenar a busca

    public conexao() {

    }

    public static Connection conexao() throws SQLException {

        //  try {
        //    Class.forName(DRIVE);
        //   return DriverManager.getConnection(URL, USER, PASS);
        // new arquivoLog(data.getDataHoraString()+" - Conexão realizada com sucesso");
        //JOptionPane.showMessageDialog(null, "Conexão do Banco de Dados Realizada com Sucesso");
        //  } catch (ClassNotFoundException | SQLException ex) {
        //      throw new RuntimeException("Erro de conexão: ", ex);
        // }
        
        VerificarPropriedades verificarPropriedades = new VerificarPropriedades();
        verificarPropriedades.carregaPropriedades();
        url = verificarPropriedades.caminhoConexao;
        user = verificarPropriedades.user;
        pass = verificarPropriedades.pass;
        try {
            System.setProperty("jdbc.Driver", drive);
            con = DriverManager.getConnection(url, user, pass);
            // new arquivoLog(data.getDataHoraString()+" - Conexão realizada com sucesso");
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Falha na conexão com o Banco de Dados: \n" + ex.getMessage());

        }
        return DriverManager.getConnection(url, user, pass);
    }

    public static void desconecta(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void desconecta(Connection conn, PreparedStatement stmt) {
        desconecta(conn);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void desconecta(Connection conn, PreparedStatement stmt, ResultSet rs) {
        desconecta(conn, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void executaSQL(String sql) {
        try {
            stam = conexao().createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stam.executeQuery(sql);
            //new arquivoLog(data.getDataHoraString()+" - Execusão do SQL realizada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao execurar SQL: \n" + ex.getMessage());

        }
    }
}
