/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.Verificar;

import Util.CaminhoClasse;
import Util.Meios;
import Visao.conexao.frm_conexao;
import Visao.frm_login;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class VerificarPropriedades {

    public static String conexao;
    public static String caminhoConexao;
    public static String nomeBanco;
    public static String user;
    public static String pass;
    public static String login;
    public static String feitaConexao;
    public static int flag = 1;
    Meios meio = new Meios();
    String caminho;
    CaminhoClasse caminhoClasse = new CaminhoClasse();

    private Properties getProp() {
        Properties props = new Properties();
        FileInputStream stream;
        try {
            flag = 1;
            stream = new FileInputStream("C:\\NovaHotel\\conexao.properties");
            props.load(new BufferedInputStream(stream));
        } catch (FileNotFoundException ex) {
            flag = 0;
            criarPropriedades();
        } catch (IOException ex) {
        }

        return props;

    }

    public void carregaPropriedades() {
        if (flag == 1) {
            getProp();
            conexao = getProp().getProperty("prop.server.servidor");
            caminhoConexao = getProp().getProperty("prop.server.caminho_conexao");
            nomeBanco = getProp().getProperty("prop.server.nome_banco");
            user = getProp().getProperty("prop.server.user");
            pass = Meios.Decripto(getProp().getProperty("prop.server.pass"));
            login = getProp().getProperty("prop.server.login");
            feitaConexao = getProp().getProperty("prop.server.feita_conexao");
        }
    }

    public void salvaPropriedades(String conexao, String caminhoConexao, String nomeBanco, String user, String pass, String feitaConexao) {
        Properties props = new Properties();
        try {
            FileOutputStream out = new FileOutputStream("C:\\NovaHotel\\conexao.properties");
            props.setProperty("prop.server.servidor", conexao);
            props.setProperty("prop.server.caminho_conexao", caminhoConexao);
            props.setProperty("prop.server.nome_banco", nomeBanco);
            props.setProperty("prop.server.user", user);
            props.setProperty("prop.server.pass", Meios.Cripto(pass));
            props.setProperty("prop.server.feita_conexao", feitaConexao);
            props.store(out, null);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro em escrever no arquivo: " + ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro em escrever no arquivo: " + ex);
        }
    }

    public void salvaLogin(String login) {
        System.out.println("Util.Verificar.VerificarPropriedades.salvaLogin()");
        Properties props = new Properties();
        try {
            FileOutputStream out = new FileOutputStream("C:\\NovaHotel\\conexao.properties");
            props.setProperty("prop.server.servidor", conexao);
            props.setProperty("prop.server.caminho_conexao", caminhoConexao);
            props.setProperty("prop.server.nome_banco", nomeBanco);
            props.setProperty("prop.server.user", user);
            props.setProperty("prop.server.pass", Meios.Cripto(pass));
            props.setProperty("prop.server.feita_conexao", feitaConexao);
            props.setProperty("prop.server.login", login);
            props.store(out, null);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro em escrever no arquivo: " + ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro em escrever no arquivo: " + ex);
        }
    }

    private void criarPropriedades() {
        File file = new File("C:\\NovaHotel\\conexao.properties");

        try {
            //para criar o arquivo

            file.createNewFile();
            if (frm_conexao.instance == null) {
                frm_conexao.instance = new frm_conexao();
                frm_conexao.instance.setVisible(true);
            } else {
                frm_conexao.instance.setVisible(true);
                frm_conexao.instance.setExtendedState(JFrame.NORMAL);
            }
            frm_login telaLogin = new frm_login();
            telaLogin.dispose();
        } catch (IOException io) {
        }
    }
}
