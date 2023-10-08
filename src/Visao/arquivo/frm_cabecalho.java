/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.arquivo;

import Conexao.conexao;
import DAO.cadastro.DAOCabecalho;
import Model.cadastro.ModalCabecalho;
import Util.ByteImg;
import Util.ManipularImagem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Matheus
 */
public class frm_cabecalho extends javax.swing.JInternalFrame {

    BufferedImage imagem;
    int verificador = 0;
    ByteImg byteImg = new ByteImg();
    private static frm_cabecalho frm_cabecalho;
    ModalCabecalho modalCabecalho = new ModalCabecalho();
    DAOCabecalho dAOCabecalho = new DAOCabecalho();
    conexao conexao = new conexao();

    /**
     * Creates new form frm_usuario
     *
     * @return
     */
    public static frm_cabecalho getInstancia() {
        if (frm_cabecalho == null) {
            frm_cabecalho = new frm_cabecalho();
        }
        return frm_cabecalho;
    }

    public frm_cabecalho() {
        initComponents();
        iniciaCabecalho();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonAdicionarArquivo = new javax.swing.JButton();
        jTextFieldLinha4 = new javax.swing.JTextField();
        jLabelLinha4 = new javax.swing.JLabel();
        jTextFieldLinha3 = new javax.swing.JTextField();
        jLabelLinha3 = new javax.swing.JLabel();
        jTextFieldLinha2 = new javax.swing.JTextField();
        jLabelLinha2 = new javax.swing.JLabel();
        jTextFieldLinha1 = new javax.swing.JTextField();
        jLabelLinha1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelImagem = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonAdicionarArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Download.png"))); // NOI18N
        jButtonAdicionarArquivo.setText("Adicionar Arquivo");
        jButtonAdicionarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarArquivoActionPerformed(evt);
            }
        });

        jLabelLinha4.setText("Linha 4");

        jLabelLinha3.setText("Linha 3");

        jLabelLinha2.setText("Linha 2");

        jLabelLinha1.setText("Linha 1");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonAdicionarArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldLinha3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelLinha4)
                            .addComponent(jLabelLinha3)
                            .addComponent(jLabelLinha1)
                            .addComponent(jTextFieldLinha1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLinha2)
                            .addComponent(jTextFieldLinha4, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldLinha2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabelLinha1)
                        .addGap(2, 2, 2)
                        .addComponent(jTextFieldLinha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelLinha2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLinha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelLinha3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLinha3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabelLinha4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLinha4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdicionarArquivo)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("CADASTRO DE CABEÇALHO");

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Box-Incoming.png"))); // NOI18N
        jButtonSalvar.setToolTipText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(16, 16, 16)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdicionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarArquivoActionPerformed
        FileFilter imageFilter = new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes());
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(imageFilter);
        int res = fc.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();

            imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 235, 230);
            //  jLabelImagem.setIcon(byteI.byteImg(imagem));
            jLabelImagem.setIcon(new ImageIcon(imagem));
            verificador = 1;
        } else {

        }
    }//GEN-LAST:event_jButtonAdicionarArquivoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        modalCabecalho.setCabecalho1(jTextFieldLinha1.getText());
        modalCabecalho.setCabecalho2(jTextFieldLinha2.getText());
        modalCabecalho.setCabecalho3(jTextFieldLinha3.getText());
        modalCabecalho.setCabecalho4(jTextFieldLinha4.getText());
        if (verificador == 0) {
            try {
                Icon icon = jLabelImagem.getIcon();

                BufferedImage bi = new BufferedImage(
                        icon.getIconWidth(),
                        icon.getIconHeight(),
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.createGraphics();
                // paint the Icon to the BufferedImage.
                icon.paintIcon(null, g, 0, 0);
                g.setColor(Color.WHITE);
                //   g.drawString(text,10,20);
                g.dispose();
                modalCabecalho.setLogo(ManipularImagem.getImgBytes(bi));

                dAOCabecalho.upload(modalCabecalho);
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex);
            }
        } else {
            try {
                modalCabecalho.setLogo(ManipularImagem.getImgBytes(imagem));

                dAOCabecalho.upload(modalCabecalho);
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex);
            }
            verificador = 0;
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void iniciaCabecalho(){
        conexao.executaSQL("SELECT * FROM tbl_cabecalho WHERE cabecalho_id = 1");
        try {
            if (conexao.rs.next()) {
                jTextFieldLinha1.setText(conexao.rs.getString("cabecalho_linha_1"));
                jTextFieldLinha2.setText(conexao.rs.getString("cabecalho_linha_2"));
                jTextFieldLinha3.setText(conexao.rs.getString("cabecalho_linha_3"));
                jTextFieldLinha4.setText(conexao.rs.getString("cabecalho_linha_4"));
                ManipularImagem.exibiImagemLabel(conexao.rs.getBytes("cabecalho_logo"), jLabelImagem);
                //  jLabelImagem.setIcon(byteI.byteImg(conexaoBD.rs.getBytes("CABECALHO_LOGO")));
            }
            conexao.rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionarArquivo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelLinha1;
    private javax.swing.JLabel jLabelLinha2;
    private javax.swing.JLabel jLabelLinha3;
    private javax.swing.JLabel jLabelLinha4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldLinha1;
    private javax.swing.JTextField jTextFieldLinha2;
    private javax.swing.JTextField jTextFieldLinha3;
    private javax.swing.JTextField jTextFieldLinha4;
    // End of variables declaration//GEN-END:variables
}
