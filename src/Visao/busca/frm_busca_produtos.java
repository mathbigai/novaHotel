/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.busca;

import Conexao.conexao;
import Model.cadastro.ModalProduto;
import Model.table.cadastro.TableModelProduto;
import Util.Verificar.VerificarUsuario;
import Visao.processo.frm_hospedagem;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus
 */
public class frm_busca_produtos extends javax.swing.JInternalFrame {

    TableModelProduto tableModelProduto = new TableModelProduto();
    VerificarUsuario verificarUsuario = new VerificarUsuario();
    public static frm_busca_produtos instance = null;
    conexao conn = new conexao();
    private static frm_busca_produtos frm_busca_produtos;
    String telaPai = "", sqlInicial = "";

    public static frm_busca_produtos getInstancia(String telaPai) {
        if (frm_busca_produtos == null) {
            frm_busca_produtos = new frm_busca_produtos(telaPai);
        }
        return frm_busca_produtos;
    }

    public frm_busca_produtos(String telaPai) {
        initComponents();
        this.telaPai = telaPai;
        this.sqlInicial = sqlInicial;
        jTableConsulta.setModel(tableModelProduto);
        atualizaTabela("select * from tbl_produto WHERE produto_quantidade > 0");
        jButtonLimparBusca.setVisible(false);
        jLabelVariavalBusca.setText("Nome");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelVariavalBusca = new javax.swing.JLabel();
        jLabelBusca = new javax.swing.JLabel();
        jTextFieldBusca = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jButtonLimparBusca = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsulta = new javax.swing.JTable();

        setClosable(true);

        jLabelBusca.setText("Buscar por");

        jTextFieldBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaKeyReleased(evt);
            }
        });

        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Browser-Search.png"))); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setToolTipText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonLimparBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Browser-Remove.png"))); // NOI18N
        jButtonLimparBusca.setText("Limpar");
        jButtonLimparBusca.setToolTipText("Buscar");
        jButtonLimparBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparBuscaActionPerformed(evt);
            }
        });

        jTableConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableConsultaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableConsultaMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTableConsulta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVariavalBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jButtonBuscar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonLimparBusca))
                        .addComponent(jTextFieldBusca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVariavalBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonLimparBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaKeyPressed

    }//GEN-LAST:event_jTextFieldBuscaKeyPressed

    private void jTextFieldBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaKeyReleased

    }//GEN-LAST:event_jTextFieldBuscaKeyReleased

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        if (jTextFieldBusca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o que você está procurando antes de proceguir.");
        } else {
            atualizaTabela("SELECT * FROM tbl_produto WHERE "
                    + "produto_" + jLabelVariavalBusca.getText() + " "
                    + "LIKE '%" + jTextFieldBusca.getText() + "%' AND produto_quantidade > 0");
            jButtonLimparBusca.setVisible(true);
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonLimparBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparBuscaActionPerformed
        atualizaTabela("select * from tbl_produto WHERE produto_quantidade > 0");
        jTextFieldBusca.setText("");
        jButtonLimparBusca.setVisible(false);
    }//GEN-LAST:event_jButtonLimparBuscaActionPerformed

    private void jTableConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultaMouseClicked
        if (evt.getClickCount() == 2) {
            String id = String.valueOf(jTableConsulta.getValueAt(jTableConsulta.getSelectedRow(), 0));
            String nome = String.valueOf(jTableConsulta.getValueAt(jTableConsulta.getSelectedRow(), 1));
            String quantidade = String.valueOf(jTableConsulta.getValueAt(jTableConsulta.getSelectedRow(), 3));
            if (telaPai.equals("Hospedagem")) {
                frm_hospedagem.setReceberProduto(id, nome, Integer.valueOf(quantidade));
                dispose();
            }
        }
    }//GEN-LAST:event_jTableConsultaMouseClicked

    private void jTableConsultaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultaMouseReleased
        jLabelVariavalBusca.setText(jTableConsulta.getColumnName(jTableConsulta.getSelectedColumn()));
    }//GEN-LAST:event_jTableConsultaMouseReleased

    public void atualizaTabela(String sql) {
        tableModelProduto.dados.removeAll(tableModelProduto.dados);
        conn.executaSQL(sql);
        try {
            while (conn.rs.next()) {
                ModalProduto modalQ = new ModalProduto();

                modalQ.setId(conn.rs.getInt("produto_id"));
                modalQ.setNome(conn.rs.getString("produto_nome"));
                modalQ.setValor(conn.rs.getDouble("produto_valor"));
                modalQ.setQuantidade(conn.rs.getInt("produto_quantidade"));
                tableModelProduto.addRow(modalQ);

            }
            conn.rs.close();
        } catch (SQLException ex) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonLimparBusca;
    private javax.swing.JLabel jLabelBusca;
    private javax.swing.JLabel jLabelVariavalBusca;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableConsulta;
    private javax.swing.JTextField jTextFieldBusca;
    // End of variables declaration//GEN-END:variables
}
