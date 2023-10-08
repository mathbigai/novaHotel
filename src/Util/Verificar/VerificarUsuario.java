/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.Verificar;

import Conexao.conexao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatty
 */
public class VerificarUsuario {

    conexao conexaoBD = new conexao();
    private static int adm;
    String tipo;
    public static String login;
    public static String id;

    public void verificarLogin() {
        conexaoBD.executaSQL("SELECT usuario_tipo, usuario_id FROM tbl_usuario WHERE usuario_login LIKE '%" + login + "%'");
        
        try {
            if (conexaoBD.rs.next()) {
                tipo = conexaoBD.rs.getString("usuario_tipo");
                id = conexaoBD.rs.getString("usuario_id");
                
                if (tipo.equals("Administrador")){
                    setAdm(1);
                    System.out.println("Estou aqui 1");
                }else{
                    setAdm(2);
                    System.out.println("Estou aqui 2");
                }
            }
            conexaoBD.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @return the adm
     */
    public int getAdm() {
        return adm;
    }

    /**
     * @param adm the adm to set
     */
    public void setAdm(int adm) {
        this.adm = adm;
    }
}
