/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.processo;

import Conexao.conexao;
import DAO.cadastro.DAOProduto;
import DAO.cadastro.DAOQuarto;
import DAO.processo.DAOAcompanhantes;
import DAO.processo.DAOHospedagem;
import DAO.processo.DAOHospedagemProdutos;
import DAO.relatorios.processo.DAORelatorioContratoHospedagem;
import DAO.relatorios.processo.DAORelatorioResumoHospedagem;
import Model.ModalData;
import Model.cadastro.ModalProduto;
import Model.cadastro.ModalQuarto;
import Model.processo.ModalAcompanhantes;
import Model.processo.ModalHospedagem;
import Model.processo.ModalHospedagemProdutos;
import Model.table.processo.TableModelAcompanhantes;
import Model.table.processo.TableModelHospedagemProdutos;
import Util.CPF;
import Util.GerenteJanelas;
import Util.LimpaCampos;
import Util.Maiusculo;
import Util.ValorMonetario;
import Util.Verificar.VerificarUsuario;
import Util.somenteNumeros;
import Visao.v_principal;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author Matheus
 */
public class frm_hospedagem extends javax.swing.JInternalFrame {

    //HOSPEDAGEM
    ModalHospedagem modalHospedagem = new ModalHospedagem();
    DAOHospedagem dAOHospedagem = new DAOHospedagem();
    //QUARTO
    ModalQuarto modalQuarto = new ModalQuarto();
    DAOQuarto daoQuarto = new DAOQuarto();
    //USUARIO
    VerificarUsuario verificarUsuario = new VerificarUsuario();
    //ACOMPANHANTE
    ModalAcompanhantes modalAcompanhantes = new ModalAcompanhantes();
    DAOAcompanhantes dAOAcompanhantes = new DAOAcompanhantes();
    //PRODUTO HOSPEDAGEM
    ModalHospedagemProdutos modalHospedagemProdutos = new ModalHospedagemProdutos();
    DAOHospedagemProdutos dAOHospedagemProdutos = new DAOHospedagemProdutos();
    //PRODUTO
    ModalProduto modalProduto = new ModalProduto();
    DAOProduto dAOProduto = new DAOProduto();
    GerenteJanelas gerenteJanelas = new GerenteJanelas(v_principal.jDesktopPanePrincipal);
    TableModelAcompanhantes tableModelAcompanhantes = new TableModelAcompanhantes();
    TableModelHospedagemProdutos tableModelHospedagemProdutos = new TableModelHospedagemProdutos();
    ModalData modalData = new ModalData();
    public ActionListener btnCLick = null;
    public int contBtn = 0;
    private static frm_hospedagem frm_hospedagem;
    conexao conexao = new conexao();
    ValorMonetario valorMonetario = new ValorMonetario();
    Color colorId = new Color(230, 225, 229);
    Color color = new Color(56, 176, 222), colorPadrao = new Color(255, 255, 255),
            colorDisponivel = new Color(0, 135, 0), colorOcupado = new Color(0, 0, 135), colorIndisponivel = new Color(135, 0, 0), colorPago = new Color(245, 31, 0);
    int flag = 0, flagCamposVaziosCheckIn = 0, flagCamposVaziosAcompanhantes = 0, flagCamposVaziosProdutos = 0, id = 0, flagAcompanhantes = 0,
            idHospedagem = 0, idUltimoCadastro = 0, quartoId = 0, flagBotaoDesativado = 0, flagProdutos = 0,
            flagControleCheckout = 0, flagAlteraProduto = 0, quantidadeProdutoHospedagem = 0,
            flagCamposVaziosCheckOut = 0, flagSemAcompanhante = 0, flagControleBotao = 0;
    static int quantidadeProduto = 0;
    double valorTotal = 0.0, valorTotalCheckOut = 0.0, valorDiaria = 0.0, produtoValor = 0.0, valorTotalProduto = 0.0;
    String idAcompanhante = "", idProduto = "";
    DateFormat dataNormal = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
    DateTime dataInicial = new DateTime(2012, 12, 1, 12, 0);

    /**
     * Creates new form frm_cliente
     *
     * @return
     */
    public static frm_hospedagem getInstancia() {
        if (frm_hospedagem == null) {
            frm_hospedagem = new frm_hospedagem();
        }
        return frm_hospedagem;
    }
    
    public frm_hospedagem() {
        initComponents();
        jPanelGeral.setVisible(false);
        this.setSize(650, 530);
        adicionaBotoes();
        flag = 1;
        jTableAcompanhantes.setModel(tableModelAcompanhantes);
        jTableProdutos.setModel(tableModelHospedagemProdutos);
        maiusculo(jPanelCheckIn.getComponents());
        maiusculo(jPanelAcompanhantes.getComponents());
        jCheckBoxPossui.setSelected(false);
        jCheckBoxEmpresarial.setSelected(false);
        jTextFieldDiariasPagas.setDocument(new somenteNumeros());
        libercaCampsoEmpresa();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPaneQuartos = new javax.swing.JDesktopPane();
        jPanelGeral = new javax.swing.JPanel();
        jLabelNumeroQuarto = new javax.swing.JLabel();
        jLabelQuarto = new javax.swing.JLabel();
        jPanelHospedagem = new javax.swing.JPanel();
        jPanelCheckOut = new javax.swing.JPanel();
        jLabelCaixa = new javax.swing.JLabel();
        jLabelValorTotal = new javax.swing.JLabel();
        jButtonFinalizarCheckOut = new javax.swing.JButton();
        jLabelDiarias = new javax.swing.JLabel();
        jTextFieldDiarias = new javax.swing.JTextField();
        jLabelDesconto = new javax.swing.JLabel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jLabelValorDesconto = new javax.swing.JLabel();
        jLabelResumoDesconto = new javax.swing.JLabel();
        jLabelValorProdutos = new javax.swing.JLabel();
        jLabelResumoProdutos = new javax.swing.JLabel();
        jLabelValorDiaria = new javax.swing.JLabel();
        jLabelResumoDiaria = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonAdicionarProdutos = new javax.swing.JButton();
        jLabelCheckIn = new javax.swing.JLabel();
        jLabelDiaCheckIn = new javax.swing.JLabel();
        jLabelResumoDiariasPagas = new javax.swing.JLabel();
        jLabelValorDiariasPagas = new javax.swing.JLabel();
        jButtonDesistir = new javax.swing.JButton();
        jPanelAcompanhantes = new javax.swing.JPanel();
        jLabeAdicionarAcompanhantes = new javax.swing.JLabel();
        jTextFieldNomeAcompanhante = new javax.swing.JTextField();
        jLabelNomeAcompanhante = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAcompanhantes = new javax.swing.JTable();
        jFormattedTextFieldCPF = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonAdicionarPessoa = new javax.swing.JButton();
        jButtonAlterarPessoa = new javax.swing.JButton();
        jButtonRemoverPessoa = new javax.swing.JButton();
        jButtonFinalizarHospedagem = new javax.swing.JButton();
        jLabelValorDiariaAcompanhantes = new javax.swing.JLabel();
        jButtonCancelarPessoa = new javax.swing.JButton();
        jPanelCheckIn = new javax.swing.JPanel();
        jTextFieldClienteCodigoEmpresa = new javax.swing.JTextField();
        jTextFieldClienteNomeEmpresa = new javax.swing.JTextField();
        jLabelClienteEmpresa = new javax.swing.JLabel();
        jPanelVeiculo = new javax.swing.JPanel();
        jCheckBoxPossui = new javax.swing.JCheckBox();
        jLabelPlaca = new javax.swing.JLabel();
        jTextFieldPlaca = new javax.swing.JTextField();
        jLabelModelo = new javax.swing.JLabel();
        jTextFieldModelo = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabelValorParcial = new javax.swing.JLabel();
        jLabelHospedagem = new javax.swing.JLabel();
        jButtonCaixa = new javax.swing.JButton();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<>();
        jButtonVerificaAcomapoanhantes = new javax.swing.JButton();
        jCheckBoxEmpresarial = new javax.swing.JCheckBox();
        jTextFieldClienteCodigo = new javax.swing.JTextField();
        jTextFieldClienteNome = new javax.swing.JTextField();
        jLabelCliente1 = new javax.swing.JLabel();
        jLabelDiariasPagas = new javax.swing.JLabel();
        jTextFieldDiariasPagas = new javax.swing.JTextField();
        jPanelIndisponivel = new javax.swing.JPanel();
        jLabelIndisponivel = new javax.swing.JLabel();
        jPanelProdutos = new javax.swing.JPanel();
        jLabelQuantidade = new javax.swing.JLabel();
        jLabeAdicionarAcompanhantes1 = new javax.swing.JLabel();
        jLabelNomeAcompanhante1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jButtonAdicionarProduto = new javax.swing.JButton();
        jButtonAlterarProduto = new javax.swing.JButton();
        jButtonRemoverProduto = new javax.swing.JButton();
        jButtonVoltarCheckOut = new javax.swing.JButton();
        jLabelValorTotalProduto = new javax.swing.JLabel();
        jButtonCancelarProduto = new javax.swing.JButton();
        jTextFieldProdutoCodigo = new javax.swing.JTextField();
        jTextFieldProdutoNome = new javax.swing.JTextField();
        jComboBoxQuantidade = new javax.swing.JComboBox<>();
        jPanelApresentacao = new javax.swing.JPanel();
        jLabelDescricao = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaItensInclusos = new javax.swing.JTextArea();
        jLabelAndar = new javax.swing.JLabel();
        jLabelDescricao1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jButtonIniciarCheckIn = new javax.swing.JButton();
        jLabelValorAndar = new javax.swing.JLabel();
        jLabelValorTipoQuarto = new javax.swing.JLabel();
        jLabelTipoQuarto = new javax.swing.JLabel();
        jLabelHospedagem1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Hospedagem");
        setToolTipText("Hospedagem");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jDesktopPaneQuartos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDesktopPaneQuartosMouseClicked(evt);
            }
        });
        jDesktopPaneQuartos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDesktopPaneQuartosKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPaneQuartosLayout = new javax.swing.GroupLayout(jDesktopPaneQuartos);
        jDesktopPaneQuartos.setLayout(jDesktopPaneQuartosLayout);
        jDesktopPaneQuartosLayout.setHorizontalGroup(
            jDesktopPaneQuartosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        jDesktopPaneQuartosLayout.setVerticalGroup(
            jDesktopPaneQuartosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabelNumeroQuarto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelNumeroQuarto.setText("jLabel2");

        jLabelQuarto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelQuarto.setText("Quarto");

        jPanelHospedagem.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelHospedagem.setLayout(new java.awt.CardLayout());

        jLabelCaixa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelCaixa.setText("Caixa");
        jLabelCaixa.setToolTipText("");

        jLabelValorTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorTotal.setText("R$ 0.0");

        jButtonFinalizarCheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Box-File.png"))); // NOI18N
        jButtonFinalizarCheckOut.setText("Finalizar CheckOut");
        jButtonFinalizarCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarCheckOutActionPerformed(evt);
            }
        });

        jLabelDiarias.setText("Quantas Diarias?");

        jTextFieldDiarias.setToolTipText("Esse valor será multiplicado a partir do valor de uma diaria, levando em consideração a quantidade de pessoas informadas no Check-In");
        jTextFieldDiarias.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextFieldDiariasPropertyChange(evt);
            }
        });
        jTextFieldDiarias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDiariasKeyReleased(evt);
            }
        });

        jLabelDesconto.setText("Desconto");

        jTextFieldDesconto.setToolTipText("Informar a quantidade em R$ do desconto que quer aplicar.");
        jTextFieldDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDescontoKeyReleased(evt);
            }
        });

        jLabelValorDesconto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelValorDesconto.setForeground(new java.awt.Color(255, 0, 0));
        jLabelValorDesconto.setText("jLabel2");

        jLabelResumoDesconto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResumoDesconto.setForeground(new java.awt.Color(255, 0, 0));
        jLabelResumoDesconto.setText("Desconto");

        jLabelValorProdutos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelValorProdutos.setText("jLabel5");
        jLabelValorProdutos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabelValorProdutosPropertyChange(evt);
            }
        });

        jLabelResumoProdutos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResumoProdutos.setText("Gastos com produtos");

        jLabelValorDiaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelValorDiaria.setText("jLabel7");

        jLabelResumoDiaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResumoDiaria.setText("Diárias");

        jButtonAdicionarProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Cart-Plus.png"))); // NOI18N
        jButtonAdicionarProdutos.setText("Adicionar Produtos");
        jButtonAdicionarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarProdutosActionPerformed(evt);
            }
        });

        jLabelCheckIn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelCheckIn.setText("CheckIn");

        jLabelDiaCheckIn.setText("jLabel2");

        jLabelResumoDiariasPagas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResumoDiariasPagas.setForeground(new java.awt.Color(255, 0, 0));
        jLabelResumoDiariasPagas.setText("Diárias Pagas");

        jLabelValorDiariasPagas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelValorDiariasPagas.setForeground(new java.awt.Color(255, 0, 0));
        jLabelValorDiariasPagas.setText("jLabel2");

        jButtonDesistir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Cart-Minus3.png"))); // NOI18N
        jButtonDesistir.setText("Desistir da Hospedagem");
        jButtonDesistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesistirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCheckOutLayout = new javax.swing.GroupLayout(jPanelCheckOut);
        jPanelCheckOut.setLayout(jPanelCheckOutLayout);
        jPanelCheckOutLayout.setHorizontalGroup(
            jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAdicionarProdutos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelValorTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(jButtonFinalizarCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                        .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelCaixa)
                            .addComponent(jLabelDiarias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldDiarias)
                            .addComponent(jLabelDesconto)
                            .addComponent(jTextFieldDesconto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCheckIn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDiaCheckIn, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckOutLayout.createSequentialGroup()
                        .addComponent(jLabelResumoDesconto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelValorDesconto))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckOutLayout.createSequentialGroup()
                        .addComponent(jLabelResumoDiaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelValorDiaria))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckOutLayout.createSequentialGroup()
                        .addComponent(jLabelResumoProdutos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelValorProdutos))
                    .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                        .addComponent(jLabelResumoDiariasPagas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelValorDiariasPagas))
                    .addComponent(jButtonDesistir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelCheckOutLayout.setVerticalGroup(
            jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                        .addComponent(jLabelCaixa)
                        .addGap(9, 9, 9)
                        .addComponent(jLabelDiarias))
                    .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                        .addComponent(jLabelCheckIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelDiaCheckIn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDiarias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDesconto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButtonDesistir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdicionarProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorDiaria)
                    .addComponent(jLabelResumoDiaria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorProdutos)
                    .addComponent(jLabelResumoProdutos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorDiariasPagas)
                    .addComponent(jLabelResumoDiariasPagas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorDesconto)
                    .addComponent(jLabelResumoDesconto))
                .addGap(18, 18, 18)
                .addComponent(jLabelValorTotal)
                .addGap(18, 18, 18)
                .addComponent(jButtonFinalizarCheckOut)
                .addContainerGap())
        );

        jPanelHospedagem.add(jPanelCheckOut, "saida");

        jLabeAdicionarAcompanhantes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabeAdicionarAcompanhantes.setText("Adicionar Acompanhantes");

        jLabelNomeAcompanhante.setText("Nome");

        jTableAcompanhantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableAcompanhantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAcompanhantesMouseClicked(evt);
            }
        });
        jTableAcompanhantes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableAcompanhantesPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAcompanhantes);

        try {
            jFormattedTextFieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("CPF");

        jButtonAdicionarPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Download.png"))); // NOI18N
        jButtonAdicionarPessoa.setText("Adicionar");
        jButtonAdicionarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarPessoaActionPerformed(evt);
            }
        });

        jButtonAlterarPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Repeat (3).png"))); // NOI18N
        jButtonAlterarPessoa.setText("Alterar");
        jButtonAlterarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarPessoaActionPerformed(evt);
            }
        });

        jButtonRemoverPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Contact-Error.png"))); // NOI18N
        jButtonRemoverPessoa.setText("Remover");
        jButtonRemoverPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverPessoaActionPerformed(evt);
            }
        });

        jButtonFinalizarHospedagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Moleskine-Open.png"))); // NOI18N
        jButtonFinalizarHospedagem.setText("Finalizar Check-In");
        jButtonFinalizarHospedagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarHospedagemActionPerformed(evt);
            }
        });

        jLabelValorDiariaAcompanhantes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelValorDiariaAcompanhantes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorDiariaAcompanhantes.setText("jLabel2");

        jButtonCancelarPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Remove (2).png"))); // NOI18N
        jButtonCancelarPessoa.setText("Cancelar");
        jButtonCancelarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarPessoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAcompanhantesLayout = new javax.swing.GroupLayout(jPanelAcompanhantes);
        jPanelAcompanhantes.setLayout(jPanelAcompanhantesLayout);
        jPanelAcompanhantesLayout.setHorizontalGroup(
            jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRemoverPessoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextFieldNomeAcompanhante, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jFormattedTextFieldCPF)
                    .addComponent(jButtonAlterarPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdicionarPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFinalizarHospedagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelValorDiariaAcompanhantes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                        .addGroup(jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabeAdicionarAcompanhantes)
                            .addComponent(jLabelNomeAcompanhante)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addComponent(jButtonCancelarPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelAcompanhantesLayout.setVerticalGroup(
            jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabeAdicionarAcompanhantes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNomeAcompanhante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNomeAcompanhante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdicionarPessoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlterarPessoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRemoverPessoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancelarPessoa)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValorDiariaAcompanhantes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButtonFinalizarHospedagem)
                .addContainerGap())
        );

        jPanelHospedagem.add(jPanelAcompanhantes, "acompanhantes");

        jTextFieldClienteCodigoEmpresa.setToolTipText("Clique em F2 para pegar usar o ultimo cliente cadastrado, ou F4 para uma busca mais personalizada.");
        jTextFieldClienteCodigoEmpresa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldClienteCodigoEmpresaFocusLost(evt);
            }
        });
        jTextFieldClienteCodigoEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldClienteCodigoEmpresaKeyReleased(evt);
            }
        });

        jTextFieldClienteNomeEmpresa.setEnabled(false);
        jTextFieldClienteNomeEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteNomeEmpresaActionPerformed(evt);
            }
        });

        jLabelClienteEmpresa.setText("Empresa");

        jPanelVeiculo.setBorder(javax.swing.BorderFactory.createTitledBorder("Veículo"));

        jCheckBoxPossui.setText("Possui?");
        jCheckBoxPossui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPossuiActionPerformed(evt);
            }
        });

        jLabelPlaca.setText("Placa");

        jTextFieldPlaca.setEnabled(false);

        jLabelModelo.setText("Marca");

        jTextFieldModelo.setEnabled(false);

        javax.swing.GroupLayout jPanelVeiculoLayout = new javax.swing.GroupLayout(jPanelVeiculo);
        jPanelVeiculo.setLayout(jPanelVeiculoLayout);
        jPanelVeiculoLayout.setHorizontalGroup(
            jPanelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPlaca)
                    .addGroup(jPanelVeiculoLayout.createSequentialGroup()
                        .addGroup(jPanelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxPossui)
                            .addComponent(jLabelPlaca)
                            .addComponent(jLabelModelo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextFieldModelo))
                .addContainerGap())
        );
        jPanelVeiculoLayout.setVerticalGroup(
            jPanelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVeiculoLayout.createSequentialGroup()
                .addComponent(jCheckBoxPossui)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPlaca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelModelo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Download.png"))); // NOI18N
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.setToolTipText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Remove (2).png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setToolTipText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabelValorParcial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelValorParcial.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorParcial.setText("jLabel1");

        jLabelHospedagem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelHospedagem.setText("Hospedagem");

        jButtonCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Cart-Plus.png"))); // NOI18N
        jButtonCaixa.setText("CheckOut");
        jButtonCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCaixaActionPerformed(evt);
            }
        });

        jComboBoxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qual a forma de pagamento?", "Dinheiro", "Cartão", "PIX" }));

        jButtonVerificaAcomapoanhantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Woman-T-Shirt.png"))); // NOI18N
        jButtonVerificaAcomapoanhantes.setText("Verificar Acompanhantes");
        jButtonVerificaAcomapoanhantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerificaAcomapoanhantesActionPerformed(evt);
            }
        });

        jCheckBoxEmpresarial.setText("Empresarial?");
        jCheckBoxEmpresarial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEmpresarialActionPerformed(evt);
            }
        });

        jTextFieldClienteCodigo.setToolTipText("Clique em F2 para pegar usar o ultimo cliente cadastrado, ou F4 para uma busca mais personalizada.");
        jTextFieldClienteCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldClienteCodigoFocusLost(evt);
            }
        });
        jTextFieldClienteCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldClienteCodigoKeyReleased(evt);
            }
        });

        jTextFieldClienteNome.setEnabled(false);
        jTextFieldClienteNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteNomeActionPerformed(evt);
            }
        });

        jLabelCliente1.setText("Hóspede");

        jLabelDiariasPagas.setText("Quantas diárias já foram pagas?");

        jTextFieldDiariasPagas.setText("0");
        jTextFieldDiariasPagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDiariasPagasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCheckInLayout = new javax.swing.GroupLayout(jPanelCheckIn);
        jPanelCheckIn.setLayout(jPanelCheckInLayout);
        jPanelCheckInLayout.setHorizontalGroup(
            jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckInLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckInLayout.createSequentialGroup()
                        .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonVerificaAcomapoanhantes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCaixa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCheckInLayout.createSequentialGroup()
                                .addComponent(jLabelHospedagem)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCheckInLayout.createSequentialGroup()
                                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(jButtonCancelar)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckInLayout.createSequentialGroup()
                        .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelCheckInLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jCheckBoxEmpresarial))
                            .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelCheckInLayout.createSequentialGroup()
                                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldClienteCodigoEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelClienteEmpresa, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldClienteNomeEmpresa))
                            .addComponent(jPanelVeiculo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelValorParcial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanelCheckInLayout.createSequentialGroup()
                        .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldClienteCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCliente1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldClienteNome)
                        .addContainerGap())
                    .addGroup(jPanelCheckInLayout.createSequentialGroup()
                        .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDiariasPagas)
                            .addComponent(jTextFieldDiariasPagas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelCheckInLayout.setVerticalGroup(
            jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckInLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHospedagem)
                    .addComponent(jCheckBoxEmpresarial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelClienteEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldClienteCodigoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldClienteNomeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCliente1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDiariasPagas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDiariasPagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabelValorParcial)
                .addGap(23, 23, 23)
                .addComponent(jButtonVerificaAcomapoanhantes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCaixa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        jPanelHospedagem.add(jPanelCheckIn, "entrada");

        jLabelIndisponivel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelIndisponivel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIndisponivel.setText("Indisponível");

        javax.swing.GroupLayout jPanelIndisponivelLayout = new javax.swing.GroupLayout(jPanelIndisponivel);
        jPanelIndisponivel.setLayout(jPanelIndisponivelLayout);
        jPanelIndisponivelLayout.setHorizontalGroup(
            jPanelIndisponivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelIndisponivel, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        );
        jPanelIndisponivelLayout.setVerticalGroup(
            jPanelIndisponivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelIndisponivel, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        );

        jPanelHospedagem.add(jPanelIndisponivel, "indisponivel");

        jLabelQuantidade.setText("Quantidade Disponível");

        jLabeAdicionarAcompanhantes1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabeAdicionarAcompanhantes1.setText("Adicionar Produtos");

        jLabelNomeAcompanhante1.setText("Produto");

        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProdutosMouseClicked(evt);
            }
        });
        jTableProdutos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableProdutosPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTableProdutos);

        jButtonAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Download.png"))); // NOI18N
        jButtonAdicionarProduto.setText("Adicionar");
        jButtonAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarProdutoActionPerformed(evt);
            }
        });

        jButtonAlterarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Repeat (3).png"))); // NOI18N
        jButtonAlterarProduto.setText("Alterar");
        jButtonAlterarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarProdutoActionPerformed(evt);
            }
        });

        jButtonRemoverProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Contact-Error.png"))); // NOI18N
        jButtonRemoverProduto.setText("Remover");
        jButtonRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverProdutoActionPerformed(evt);
            }
        });

        jButtonVoltarCheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Moleskine-Open.png"))); // NOI18N
        jButtonVoltarCheckOut.setText("Voltar ao Check-Out");
        jButtonVoltarCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarCheckOutActionPerformed(evt);
            }
        });

        jLabelValorTotalProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelValorTotalProduto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorTotalProduto.setText("jLabel2");

        jButtonCancelarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Remove (2).png"))); // NOI18N
        jButtonCancelarProduto.setText("Cancelar");
        jButtonCancelarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarProdutoActionPerformed(evt);
            }
        });

        jTextFieldProdutoCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldProdutoCodigoFocusLost(evt);
            }
        });
        jTextFieldProdutoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldProdutoCodigoKeyReleased(evt);
            }
        });

        jTextFieldProdutoNome.setEnabled(false);
        jTextFieldProdutoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdutoNomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProdutosLayout = new javax.swing.GroupLayout(jPanelProdutos);
        jPanelProdutos.setLayout(jPanelProdutosLayout);
        jPanelProdutosLayout.setHorizontalGroup(
            jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRemoverProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButtonAlterarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdicionarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVoltarCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelValorTotalProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelProdutosLayout.createSequentialGroup()
                        .addGroup(jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxQuantidade, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelQuantidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabeAdicionarAcompanhantes1)
                            .addComponent(jLabelNomeAcompanhante1)
                            .addGroup(jPanelProdutosLayout.createSequentialGroup()
                                .addComponent(jTextFieldProdutoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldProdutoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelProdutosLayout.setVerticalGroup(
            jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabeAdicionarAcompanhantes1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNomeAcompanhante1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldProdutoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldProdutoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelQuantidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlterarProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRemoverProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelarProduto)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelValorTotalProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jButtonVoltarCheckOut)
                .addContainerGap())
        );

        jPanelHospedagem.add(jPanelProdutos, "produtos");

        jLabelDescricao.setText("Descrição");

        jTextAreaItensInclusos.setColumns(20);
        jTextAreaItensInclusos.setRows(5);
        jTextAreaItensInclusos.setEnabled(false);
        jScrollPane4.setViewportView(jTextAreaItensInclusos);
        jTextAreaItensInclusos.getAccessibleContext().setAccessibleName("");

        jLabelAndar.setText("Andar");

        jLabelDescricao1.setText("Itens inclusos");

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jTextAreaDescricao.setEnabled(false);
        jScrollPane3.setViewportView(jTextAreaDescricao);

        jButtonIniciarCheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Moleskine-Open.png"))); // NOI18N
        jButtonIniciarCheckIn.setText("Iniciar CheckIn");
        jButtonIniciarCheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarCheckInActionPerformed(evt);
            }
        });

        jLabelValorAndar.setText("jLabel5");

        jLabelValorTipoQuarto.setText("jLabel1");

        jLabelTipoQuarto.setText("Tipo do Quarto:");

        jLabelHospedagem1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelHospedagem1.setText("Apresentação");

        javax.swing.GroupLayout jPanelApresentacaoLayout = new javax.swing.GroupLayout(jPanelApresentacao);
        jPanelApresentacao.setLayout(jPanelApresentacaoLayout);
        jPanelApresentacaoLayout.setHorizontalGroup(
            jPanelApresentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelApresentacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelApresentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelApresentacaoLayout.createSequentialGroup()
                        .addComponent(jLabelValorAndar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelAndar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(jButtonIniciarCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanelApresentacaoLayout.createSequentialGroup()
                        .addGroup(jPanelApresentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDescricao1)
                            .addComponent(jLabelHospedagem1)
                            .addComponent(jLabelDescricao))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelApresentacaoLayout.createSequentialGroup()
                        .addComponent(jLabelTipoQuarto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelValorTipoQuarto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelApresentacaoLayout.setVerticalGroup(
            jPanelApresentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelApresentacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelHospedagem1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelApresentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorAndar)
                    .addComponent(jLabelAndar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelDescricao1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelApresentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipoQuarto)
                    .addComponent(jLabelValorTipoQuarto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addComponent(jButtonIniciarCheckIn)
                .addContainerGap())
        );

        jPanelHospedagem.add(jPanelApresentacao, "apresentacao");

        javax.swing.GroupLayout jPanelGeralLayout = new javax.swing.GroupLayout(jPanelGeral);
        jPanelGeral.setLayout(jPanelGeralLayout);
        jPanelGeralLayout.setHorizontalGroup(
            jPanelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelQuarto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNumeroQuarto)
                .addContainerGap(141, Short.MAX_VALUE))
            .addGroup(jPanelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelHospedagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelGeralLayout.setVerticalGroup(
            jPanelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuarto)
                    .addComponent(jLabelNumeroQuarto))
                .addContainerGap(571, Short.MAX_VALUE))
            .addGroup(jPanelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelGeralLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jPanelHospedagem, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPaneQuartos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneQuartos)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        Date checkinData = new Date();
        java.sql.Time sqlTime = new Time(new Date().getTime());
        verificaCampos();
        if (flagCamposVaziosCheckIn == 1) {
            modalHospedagem.setClienteId(Integer.valueOf(jTextFieldClienteCodigo.getText()));
            modalHospedagem.setClienteNome(jTextFieldClienteNome.getText());
            modalHospedagem.setQuartoId(quartoId);
            modalHospedagem.setQuartoNumero(jLabelNumeroQuarto.getText());
            modalHospedagem.setUsuarioId(Integer.valueOf(verificarUsuario.id));
            modalHospedagem.setUsuarioNome(verificarUsuario.login);
            modalHospedagem.setStatus("CheckIn");
            modalHospedagem.setCheckinData(checkinData);
            modalHospedagem.setCheckinHora(sqlTime);
            modalHospedagem.setValorHospedagem(0.0);
            modalHospedagem.setPlaca(jTextFieldPlaca.getText());
            modalHospedagem.setModelo(jTextFieldModelo.getText());
            modalHospedagem.setFormaPagamento(jComboBoxFormaPagamento.getSelectedItem().toString());
            if (jTextFieldDiariasPagas.getText().isEmpty()) {
                jTextFieldDiariasPagas.setText("0");
            }
            modalHospedagem.setDiariasPagas(Integer.valueOf(jTextFieldDiariasPagas.getText()));
            if (jCheckBoxEmpresarial.isSelected()) {
                modalHospedagem.setClienteEmpresaId(Integer.valueOf(jTextFieldClienteCodigoEmpresa.getText()));
                modalHospedagem.setClienteEmpresaNome(jTextFieldClienteNomeEmpresa.getText());
            }
            if (flag == 1) {
                try {
                    dAOHospedagem.create(modalHospedagem);
                    ultimaHospedagem(Integer.valueOf(jTextFieldClienteCodigo.getText()));
                    flagAcompanhantes = 1;
                    flagControleBotao = 0;
                    esquemaBotoesAcompanhantes();
                    desabilitaBotoes();
                    CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
                    cl.show(jPanelHospedagem, "acompanhantes");
                } catch (SQLException ex) {
                    Logger.getLogger(frm_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limparCampos();
        jPanelGeral.setVisible(false);
        this.setSize(650, 530);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextFieldClienteNomeEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClienteNomeEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteNomeEmpresaActionPerformed

    private void jDesktopPaneQuartosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDesktopPaneQuartosKeyReleased

    }//GEN-LAST:event_jDesktopPaneQuartosKeyReleased

    private void jDesktopPaneQuartosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopPaneQuartosMouseClicked

    }//GEN-LAST:event_jDesktopPaneQuartosMouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        frm_hospedagem = null;
    }//GEN-LAST:event_formInternalFrameClosed

    private void jCheckBoxPossuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPossuiActionPerformed
        if (jCheckBoxPossui.isSelected()) {
            jTextFieldPlaca.setEnabled(true);
            jTextFieldModelo.setEnabled(true);
        } else {
            jTextFieldPlaca.setEnabled(false);
            jTextFieldModelo.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxPossuiActionPerformed

    private void jTextFieldClienteCodigoEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldClienteCodigoEmpresaKeyReleased
        
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            v_principal.abrirBuscador("Hospedagem",
                    "SELECT cliente_id, cliente_nome, cliente_cpf_cnpj FROM tbl_cliente "
                    + "WHERE LENGTH(cliente_cpf_cnpj) = 14 ");
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            flagControleCheckout = 1;
            resgataUltimoCadastroClienteEmpresa();
        }
        if (jTextFieldClienteCodigoEmpresa.getText() != null && evt.getKeyCode() == KeyEvent.VK_ENTER) {
            flagControleCheckout = 2;
            resgataUltimoCadastroClienteEmpresa();
        }
        if (jTextFieldClienteCodigoEmpresa.getText().isEmpty()) {
            jTextFieldClienteNomeEmpresa.setText("");
        }

    }//GEN-LAST:event_jTextFieldClienteCodigoEmpresaKeyReleased

    private void jButtonAdicionarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarPessoaActionPerformed
        flagAcompanhantes = 1;
        cadastrarAcompanhantes();
    }//GEN-LAST:event_jButtonAdicionarPessoaActionPerformed

    private void jButtonAlterarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarPessoaActionPerformed
        flagAcompanhantes = 2;
        cadastrarAcompanhantes();
    }//GEN-LAST:event_jButtonAlterarPessoaActionPerformed

    private void jButtonRemoverPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverPessoaActionPerformed
        flagAcompanhantes = 3;
        cadastrarAcompanhantes();
    }//GEN-LAST:event_jButtonRemoverPessoaActionPerformed

    private void jTableAcompanhantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAcompanhantesMouseClicked
        if (jTableAcompanhantes.getSelectedRow() != -1) {
            flagAcompanhantes = 2;
            esquemaBotoesAcompanhantes();
            jTextFieldNomeAcompanhante.setText(jTableAcompanhantes.getValueAt(jTableAcompanhantes.getSelectedRow(), 1).toString());
            jFormattedTextFieldCPF.setText(String.valueOf(jTableAcompanhantes.getValueAt(jTableAcompanhantes.getSelectedRow(), 2)));
            idAcompanhante = String.valueOf(jTableAcompanhantes.getValueAt(jTableAcompanhantes.getSelectedRow(), 0));
        }

    }//GEN-LAST:event_jTableAcompanhantesMouseClicked

    private void jButtonFinalizarHospedagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarHospedagemActionPerformed
        if (flagControleBotao == 0) {
            modalHospedagem.setValorHospedagem(valorTotal);
            modalHospedagem.setValorTotal(valorTotal);
            modalHospedagem.setId(idHospedagem);
            try {
                dAOHospedagem.updateValorDiaria(modalHospedagem);
                dAOHospedagem.updateValorTotal(modalHospedagem);
                if (Integer.valueOf(jTextFieldDiariasPagas.getText()) > 0) {
                    modalQuarto.setStatus("Ocupado/Pago");
                } else {
                    modalQuarto.setStatus("Ocupado");
                }
                modalQuarto.setId(quartoId);
                daoQuarto.updateStatus(modalQuarto);
                int opcion = JOptionPane.showConfirmDialog(null, "Gostaria de Emitir o Contrato da Hospedagem agora?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    DAORelatorioContratoHospedagem relatorioContratoHospedagem = new DAORelatorioContratoHospedagem();
                    try {
                        if (jCheckBoxEmpresarial.isSelected()) {
                            relatorioContratoHospedagem.acionaRelatoioEmpresa(String.valueOf(idHospedagem));
                        } else {
                            relatorioContratoHospedagem.acionaRelatoio(String.valueOf(idHospedagem));
                        }
                        
                    } catch (SQLException ex) {
                        
                    }
                }
                
                jPanelGeral.setVisible(false);
                this.setSize(650, 530);
                flagBotaoDesativado = 0;
                habilitaBotoes();
                if (Integer.valueOf(jTextFieldDiariasPagas.getText()) > 0) {
                    for (int i = 0; i < jDesktopPaneQuartos.getComponentCount(); i++) {
                        if (jDesktopPaneQuartos.getComponent(i).getName().equals(jLabelNumeroQuarto.getText())) {
                            jDesktopPaneQuartos.getComponent(i).setBackground(colorPago);
                        }
                    }
                } else {
                    for (int i = 0; i < jDesktopPaneQuartos.getComponentCount(); i++) {
                        if (jDesktopPaneQuartos.getComponent(i).getName().equals(jLabelNumeroQuarto.getText())) {
                            jDesktopPaneQuartos.getComponent(i).setBackground(colorOcupado);
                        }
                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
            }
            limparCampos();
            limpaCamposAcompanhantes();
        } else {
            CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
            cl.show(jPanelHospedagem, "entrada");
        }

    }//GEN-LAST:event_jButtonFinalizarHospedagemActionPerformed

    private void jTableAcompanhantesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableAcompanhantesPropertyChange

    }//GEN-LAST:event_jTableAcompanhantesPropertyChange

    private void jButtonCancelarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarPessoaActionPerformed
        if (flagBotaoDesativado == 1) {
            limpaCamposAcompanhantes();
            flagAcompanhantes = 1;
            esquemaBotoesAcompanhantes();
            jButtonAdicionarPessoa.setVisible(false);
        } else {
            flagAcompanhantes = 1;
            limpaCamposAcompanhantes();
            esquemaBotoesAcompanhantes();
        }
    }//GEN-LAST:event_jButtonCancelarPessoaActionPerformed

    private void jButtonVerificaAcomapoanhantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerificaAcomapoanhantesActionPerformed
        flagAcompanhantes = 4;
        flagControleBotao = 1;
        esquemaBotoesAcompanhantes();
        
        try {
            atualizaTabelaAcompanhantes("SELECT * FROM tbl_acompanhantes WHERE acompanhantes_hospedagem_id=" + idHospedagem);
            CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
            cl.show(jPanelHospedagem, "acompanhantes");
        } catch (SQLException ex) {
            Logger.getLogger(frm_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonVerificaAcomapoanhantesActionPerformed

    private void jTableProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProdutosMouseClicked
        if (jTableProdutos.getSelectedRow() != -1) {
            flagProdutos = 2;
            esquemaBotoesProdutos();
            jTextFieldProdutoCodigo.setText(jTableProdutos.getValueAt(jTableProdutos.getSelectedRow(), 1).toString());
            jTextFieldProdutoNome.setText(jTableProdutos.getValueAt(jTableProdutos.getSelectedRow(), 2).toString());
            pegarQuantidadeProduto(Integer.valueOf(jTextFieldProdutoCodigo.getText()));
            adicionaQuantidadeProdutos(quantidadeProduto);
            idProduto = String.valueOf(jTableProdutos.getValueAt(jTableProdutos.getSelectedRow(), 0));
            quantidadeProdutoHospedagem = Integer.valueOf(jTableProdutos.getValueAt(jTableProdutos.getSelectedRow(), 4).toString());
        }
    }//GEN-LAST:event_jTableProdutosMouseClicked

    private void jTableProdutosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableProdutosPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableProdutosPropertyChange

    private void jButtonAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarProdutoActionPerformed
        flagProdutos = 1;
        cadastrarProdutos();
    }//GEN-LAST:event_jButtonAdicionarProdutoActionPerformed

    private void jButtonAlterarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarProdutoActionPerformed
        flagProdutos = 2;
        cadastrarProdutos();
    }//GEN-LAST:event_jButtonAlterarProdutoActionPerformed

    private void jButtonRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverProdutoActionPerformed
        flagProdutos = 3;
        int opcion = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            cadastrarProdutos();
        }

    }//GEN-LAST:event_jButtonRemoverProdutoActionPerformed

    private void jButtonVoltarCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarCheckOutActionPerformed
        LimpaCampos.limpaCampos(jPanelProdutos.getComponents());
        CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
        cl.show(jPanelHospedagem, "saida");
    }//GEN-LAST:event_jButtonVoltarCheckOutActionPerformed

    private void jButtonCancelarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarProdutoActionPerformed
        limpaCamposProdutos();
        flagProdutos = 1;
        esquemaBotoesProdutos();
    }//GEN-LAST:event_jButtonCancelarProdutoActionPerformed

    private void jTextFieldProdutoCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldProdutoCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            v_principal.abrirBuscadorProdutos("Hospedagem");
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            flagControleCheckout = 1;
            resgataUltimoCadastroProduto();
        }
        if (jTextFieldProdutoCodigo.getText() != null && evt.getKeyCode() == KeyEvent.VK_ENTER) {
            flagControleCheckout = 2;
            resgataUltimoCadastroProduto();
        }
        if (jTextFieldProdutoCodigo.getText().isEmpty()) {
            jTextFieldProdutoNome.setText("");
            jLabelQuantidade.setVisible(false);
            jComboBoxQuantidade.setVisible(false);
        }
    }//GEN-LAST:event_jTextFieldProdutoCodigoKeyReleased

    private void jTextFieldProdutoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProdutoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProdutoNomeActionPerformed

    private void jButtonAdicionarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarProdutosActionPerformed
        CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
        cl.show(jPanelHospedagem, "produtos");
        flagProdutos = 1;
        esquemaBotoesProdutos();
    }//GEN-LAST:event_jButtonAdicionarProdutosActionPerformed

    private void jButtonCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCaixaActionPerformed
        CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
        cl.show(jPanelHospedagem, "saida");
    }//GEN-LAST:event_jButtonCaixaActionPerformed

    private void jTextFieldDiariasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDiariasKeyReleased
        if (jTextFieldDiarias.getText().isEmpty()) {
            jLabelValorDiaria.setText("0.0");
            calculaValorTotalCheckOut(Double.valueOf(jLabelValorProdutos.getText()),
                    Double.valueOf(jLabelValorDesconto.getText()),
                    Double.valueOf(jLabelValorDiaria.getText()));
        } else {
            jLabelValorDiaria.setText(String.valueOf((valorDiaria * Double.valueOf(jTextFieldDiarias.getText()))));
            calculaValorTotalCheckOut(Double.valueOf(jLabelValorProdutos.getText()),
                    Double.valueOf(jLabelValorDesconto.getText()),
                    Double.valueOf(jLabelValorDiaria.getText()));
        }

    }//GEN-LAST:event_jTextFieldDiariasKeyReleased

    private void jTextFieldDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDescontoKeyReleased
        if (jTextFieldDesconto.getText().isEmpty()) {
            jLabelValorDesconto.setText("0.0");
            calculaValorTotalCheckOut(Double.valueOf(jLabelValorProdutos.getText()),
                    Double.valueOf(jLabelValorDesconto.getText()),
                    Double.valueOf(jLabelValorDiaria.getText()));
        } else {
            jLabelValorDesconto.setText(String.valueOf(Double.valueOf(jTextFieldDesconto.getText())));
            calculaValorTotalCheckOut(Double.valueOf(jLabelValorProdutos.getText()),
                    Double.valueOf(jLabelValorDesconto.getText()),
                    Double.valueOf(jLabelValorDiaria.getText()));
        }
    }//GEN-LAST:event_jTextFieldDescontoKeyReleased

    private void jLabelValorProdutosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelValorProdutosPropertyChange
        

    }//GEN-LAST:event_jLabelValorProdutosPropertyChange

    private void jButtonFinalizarCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarCheckOutActionPerformed
        Date checkinData = new Date();
        java.sql.Time sqlTime = new Time(new Date().getTime());
        verificaCamposCheckOut();
        flagControleBotao = 0;
        if (flagCamposVaziosCheckOut == 1) {
            modalHospedagem.setStatus("CheckOut");
            modalHospedagem.setCheckoutData(checkinData);
            modalHospedagem.setCheckoutHora(sqlTime);
            modalHospedagem.setValorTotal(valorTotalCheckOut);
            modalHospedagem.setValorTotalDiaria(Double.valueOf(jLabelValorDiaria.getText()));
            modalHospedagem.setValorDesconto(Double.valueOf(jLabelValorDesconto.getText()));
            modalHospedagem.setQuantidadeDiaria(Double.valueOf(jTextFieldDiarias.getText()));
            modalHospedagem.setId(idHospedagem);
            try {
                dAOHospedagem.updateCheckout(modalHospedagem);
                modalQuarto.setStatus("Disponível");
                modalQuarto.setId(quartoId);
                daoQuarto.updateStatus(modalQuarto);
                int opcion = JOptionPane.showConfirmDialog(null, "Gostaria de Emitir o Resumo da Hospedagem agora?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (opcion == 0) {
                    if (jCheckBoxEmpresarial.isSelected()) {
                        InputStream stream = getClass().getResourceAsStream("/relatorios/processo/reciboHospedagemEmpresa.jasper");
                        DAORelatorioResumoHospedagem relatorioResumoHospedagem = new DAORelatorioResumoHospedagem();
                        try {
                            relatorioResumoHospedagem.acionaRelatoioEmpresa(String.valueOf(idHospedagem), stream);
                        } catch (SQLException ex) {
                            
                        }
                    } else {
                        InputStream stream = getClass().getResourceAsStream("/relatorios/processo/reciboHospedagem.jasper");
                        DAORelatorioResumoHospedagem relatorioResumoHospedagem = new DAORelatorioResumoHospedagem();
                        try {
                            relatorioResumoHospedagem.acionaRelatoio(String.valueOf(idHospedagem), stream);
                        } catch (SQLException ex) {
                            
                        }
                    }
                    
                }
                
                limpaCamposCheckOut();
                jPanelGeral.setVisible(false);
                this.setSize(650, 530);
                flagBotaoDesativado = 0;
                
                for (int i = 0; i < jDesktopPaneQuartos.getComponentCount(); i++) {
                    if (jDesktopPaneQuartos.getComponent(i).getName().equals(jLabelNumeroQuarto.getText())) {
                        jDesktopPaneQuartos.getComponent(i).setBackground(colorDisponivel);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_jButtonFinalizarCheckOutActionPerformed

    private void jButtonIniciarCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarCheckInActionPerformed
        CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
        cl.show(jPanelHospedagem, "entrada");
    }//GEN-LAST:event_jButtonIniciarCheckInActionPerformed

    private void jTextFieldDiariasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextFieldDiariasPropertyChange

    }//GEN-LAST:event_jTextFieldDiariasPropertyChange

    private void jTextFieldClienteCodigoEmpresaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldClienteCodigoEmpresaFocusLost
        flagControleCheckout = 2;
        if (jTextFieldClienteCodigoEmpresa.getText().isEmpty()) {
            
        } else {
            resgataUltimoCadastroClienteEmpresa();
        }

    }//GEN-LAST:event_jTextFieldClienteCodigoEmpresaFocusLost

    private void jTextFieldProdutoCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldProdutoCodigoFocusLost
        flagControleCheckout = 2;
        if (jTextFieldProdutoCodigo.getText().isEmpty()) {
            
        } else {
            resgataUltimoCadastroProduto();
        }

    }//GEN-LAST:event_jTextFieldProdutoCodigoFocusLost

    private void jCheckBoxEmpresarialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEmpresarialActionPerformed
        libercaCampsoEmpresa();
    }//GEN-LAST:event_jCheckBoxEmpresarialActionPerformed

    private void jTextFieldClienteCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldClienteCodigoFocusLost
        flagControleCheckout = 2;
        if (jTextFieldClienteCodigoEmpresa.getText().isEmpty()) {
            
        } else {
            resgataUltimoCadastroCliente();
        }
    }//GEN-LAST:event_jTextFieldClienteCodigoFocusLost

    private void jTextFieldClienteCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldClienteCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            v_principal.abrirBuscador("Hospedagem Cliente",
                    "SELECT cliente_id, cliente_nome, cliente_cpf_cnpj FROM tbl_cliente "
                    + "WHERE LENGTH(cliente_cpf_cnpj) = 11");
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            flagControleCheckout = 1;
            resgataUltimoCadastroCliente();
        }
        if (jTextFieldClienteCodigoEmpresa.getText() != null && evt.getKeyCode() == KeyEvent.VK_ENTER) {
            flagControleCheckout = 2;
            resgataUltimoCadastroCliente();
        }
        if (jTextFieldClienteCodigoEmpresa.getText().isEmpty()) {
            jTextFieldClienteNomeEmpresa.setText("");
        }
    }//GEN-LAST:event_jTextFieldClienteCodigoKeyReleased

    private void jTextFieldClienteNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClienteNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteNomeActionPerformed

    private void jTextFieldDiariasPagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDiariasPagasActionPerformed

    }//GEN-LAST:event_jTextFieldDiariasPagasActionPerformed

    private void jButtonDesistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesistirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja liberar o quarto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            modalHospedagem.setId(idHospedagem);
            modalHospedagem.setStatus("Desistiu");
            try {
                dAOHospedagem.updateDesistir(modalHospedagem);
                modalQuarto.setStatus("Disponível");
                modalQuarto.setId(quartoId);
                daoQuarto.updateStatus(modalQuarto);
                limpaCamposCheckOut();
                jPanelGeral.setVisible(false);
                this.setSize(650, 530);
                flagBotaoDesativado = 0;
                
                for (int i = 0; i < jDesktopPaneQuartos.getComponentCount(); i++) {
                    if (jDesktopPaneQuartos.getComponent(i).getName().equals(jLabelNumeroQuarto.getText())) {
                        jDesktopPaneQuartos.getComponent(i).setBackground(colorDisponivel);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButtonDesistirActionPerformed
    
    private void adicionaBotoes() {
        
        conexao.executaSQL("SELECT quarto_numero, quarto_status FROM tbl_quarto ORDER BY quarto_numero");
        try {
            while (conexao.rs.next()) {
                int btnPorLinha = 800 / (200 + 10);
                int linhaAtual = (++contBtn) / btnPorLinha;
                int localAtual = contBtn % btnPorLinha;
                
                JButton newbtn = new JButton(conexao.rs.getString("quarto_numero"));
                newbtn.setName(conexao.rs.getString("quarto_numero"));
                if (conexao.rs.getString("quarto_status").equals("Disponível")) {
                    newbtn.setBackground(colorDisponivel);
                }
                if (conexao.rs.getString("quarto_status").equals("Ocupado")) {
                    newbtn.setBackground(colorOcupado);
                }
                if (conexao.rs.getString("quarto_status").equals("Ocupado/Pago")) {
                    newbtn.setBackground(colorPago);
                }
                if (conexao.rs.getString("quarto_status").equals("Indisponível")) {
                    newbtn.setBackground(colorIndisponivel);
                }
                
                newbtn.setSize(200, 40);
                newbtn.setLocation(10 + ((200 + 10) * localAtual), 10 + ((40 + 10) * linhaAtual));
                newbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Bed-2.png")));
                newbtn.addActionListener(btnCLick = (ActionEvent e) -> {
                    esquemaBotoes(newbtn.getBackground(), newbtn.getText());
                    acaoBotaoQuarto(newbtn.getText());
                });
                Dimension d = new Dimension(800, 10 + ((40 + 10) * linhaAtual) + 50);
                jDesktopPaneQuartos.setPreferredSize(d);
                jDesktopPaneQuartos.setSize(d);
                jDesktopPaneQuartos.add(newbtn);
            }
            conexao.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(frm_hospedagem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void acaoBotaoQuarto(String quarto) {
        this.setSize(930, 630);
        jLabelNumeroQuarto.setText(quarto);
        jPanelGeral.setVisible(true);
    }
    
    private void esquemaBotoes(Color status, String numero) {
        jTextFieldClienteCodigoEmpresa.setBackground(colorId);
        if (status == colorDisponivel) {
            flag = 1;
            limparCampos();
            carregaInformacoesQuartoDisponivel(numero);
            jButtonCaixa.setVisible(false);
            jButtonSalvar.setVisible(true);
            jButtonCancelar.setVisible(true);
            jButtonVerificaAcomapoanhantes.setVisible(false);
            CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
            cl.show(jPanelHospedagem, "apresentacao");
        }
        if (status == colorPago) {
            limparCampos();
            carregaInformacoesQuartoOcupado(numero);
            jButtonCaixa.setVisible(true);
            jButtonSalvar.setVisible(false);
            jButtonCancelar.setVisible(true);
            jButtonVerificaAcomapoanhantes.setVisible(true);
            CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
            cl.show(jPanelHospedagem, "entrada");
        }
        if (status == colorOcupado) {
            limparCampos();
            carregaInformacoesQuartoOcupado(numero);
            jButtonCaixa.setVisible(true);
            jButtonSalvar.setVisible(false);
            jButtonCancelar.setVisible(true);
            jButtonVerificaAcomapoanhantes.setVisible(true);
            CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
            cl.show(jPanelHospedagem, "entrada");
        }
        if (status == colorIndisponivel) {
            limparCampos();
            CardLayout cl = (CardLayout) jPanelHospedagem.getLayout();
            cl.show(jPanelHospedagem, "indisponivel");
        }
    }
    
    private void esquemaBotoesAcompanhantes() {
        switch (flagAcompanhantes) {
            case 1:
                if (flagBotaoDesativado == 1) {
                    jButtonAdicionarPessoa.setVisible(false);
                } else {
                    jButtonAdicionarPessoa.setVisible(true);
                }
                jButtonAlterarPessoa.setVisible(false);
                jButtonRemoverPessoa.setVisible(false);
                jButtonCancelarPessoa.setVisible(false);
                jFormattedTextFieldCPF.setEnabled(true);
                break;
            case 2:
                jButtonAdicionarPessoa.setVisible(false);
                jButtonAlterarPessoa.setVisible(true);
                jButtonRemoverPessoa.setVisible(true);
                jButtonCancelarPessoa.setVisible(true);
                jFormattedTextFieldCPF.setEnabled(false);
                if (flagControleBotao == 1) {
                    jButtonFinalizarHospedagem.setText("Voltar");
                } else {
                    jButtonFinalizarHospedagem.setText("Finalizar Check-In");
                }
                
                break;
            case 4:
                if (flagBotaoDesativado == 1) {
                    jButtonAdicionarPessoa.setVisible(false);
                } else {
                    jButtonAdicionarPessoa.setVisible(true);
                }
                jButtonAlterarPessoa.setVisible(false);
                jButtonRemoverPessoa.setVisible(false);
                jButtonCancelarPessoa.setVisible(false);
                jFormattedTextFieldCPF.setEnabled(true);
                jButtonFinalizarHospedagem.setText("Voltar");
                break;
        }
    }
    
    private void esquemaBotoesProdutos() {
        switch (flagProdutos) {
            case 1:
                if (flagBotaoDesativado == 1) {
                    jButtonAdicionarProduto.setVisible(false);
                } else {
                    jButtonAdicionarProduto.setVisible(true);
                }
                jButtonAlterarProduto.setVisible(false);
                jButtonRemoverProduto.setVisible(false);
                jButtonCancelarProduto.setVisible(false);
                jLabelQuantidade.setVisible(false);
                jComboBoxQuantidade.setVisible(false);
                jTextFieldProdutoCodigo.setEnabled(true);
                break;
            case 2:
                jButtonAdicionarProduto.setVisible(false);
                jButtonAlterarProduto.setVisible(true);
                jButtonRemoverProduto.setVisible(true);
                jButtonCancelarProduto.setVisible(true);
                jTextFieldProdutoCodigo.setEnabled(false);
                break;
            case 4:
                jButtonAdicionarProduto.setVisible(false);
                jButtonAlterarProduto.setVisible(false);
                jButtonRemoverProduto.setVisible(false);
                jButtonCancelarProduto.setVisible(false);
                jTextFieldProdutoCodigo.setEnabled(false);
                jTextFieldProdutoNome.setEnabled(false);
                break;
        }
    }
    
    private void verificaCampos() {
        if (jCheckBoxEmpresarial.isSelected()) {
            if (jTextFieldClienteCodigoEmpresa.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo: Empresa, está em branco");
                jTextFieldClienteCodigoEmpresa.requestFocus();
                jTextFieldClienteCodigoEmpresa.setBackground(color);
            } else {
                jTextFieldClienteCodigoEmpresa.setBackground(colorPadrao);
                if (jTextFieldClienteNomeEmpresa.getText().isEmpty()) {
                    jTextFieldClienteNomeEmpresa.requestFocus();
                    jTextFieldClienteNomeEmpresa.setBackground(color);
                } else {
                    jTextFieldClienteNomeEmpresa.setBackground(colorPadrao);
                }
            }
        } else {
            jTextFieldClienteCodigoEmpresa.setBackground(colorPadrao);
            jTextFieldClienteNomeEmpresa.setBackground(colorPadrao);
        }
        if (jTextFieldClienteCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo: Hóspede, está em branco");
            jTextFieldClienteCodigo.requestFocus();
            jTextFieldClienteCodigo.setBackground(color);
        } else {
            jTextFieldClienteCodigo.setBackground(colorPadrao);
            if (jTextFieldClienteNome.getText().isEmpty()) {
                jTextFieldClienteNome.requestFocus();
                jTextFieldClienteNome.setBackground(color);
            } else {
                jTextFieldClienteNome.setBackground(colorPadrao);
            }
        }
        if (jCheckBoxPossui.isSelected()) {
            if (jTextFieldPlaca.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo: Cliente, está em branco");
                jTextFieldPlaca.requestFocus();
                jTextFieldPlaca.setBackground(color);
            } else {
                jTextFieldPlaca.setBackground(colorPadrao);
            }
            if (jTextFieldModelo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo: Cliente, está em branco");
                jTextFieldModelo.requestFocus();
                jTextFieldModelo.setBackground(color);
            } else {
                jTextFieldModelo.setBackground(colorPadrao);
            }
        } else {
            jTextFieldPlaca.setBackground(colorPadrao);
            jTextFieldModelo.setBackground(colorPadrao);
        }
        if (jComboBoxFormaPagamento.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione uma forma de pagamento.");
            jComboBoxFormaPagamento.requestFocus();
            jComboBoxFormaPagamento.setBackground(color);
        } else {
            jComboBoxFormaPagamento.setBackground(colorPadrao);
        }
        if (jTextFieldClienteCodigo.getBackground() == colorPadrao
                && jTextFieldPlaca.getBackground() == colorPadrao
                && jTextFieldModelo.getBackground() == colorPadrao
                && jComboBoxFormaPagamento.getBackground() == colorPadrao
                && jTextFieldClienteNome.getBackground() == colorPadrao
                && jTextFieldClienteNomeEmpresa.getBackground() == colorPadrao
                && jTextFieldClienteCodigoEmpresa.getBackground() == colorPadrao) {
            flagCamposVaziosCheckIn = 1;
        }
    }
    
    private void verificaCamposAcompanhantes() {
        CPF pf = new CPF(jFormattedTextFieldCPF.getText());
        if (jTextFieldNomeAcompanhante.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo: Nome Completo, está em branco");
            jTextFieldNomeAcompanhante.requestFocus();
            jTextFieldNomeAcompanhante.setBackground(color);
        } else {
            jTextFieldNomeAcompanhante.setBackground(colorPadrao);
        }
        if (jFormattedTextFieldCPF.getText().replaceAll("[./-]", "").isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo: CPF, está em branco");
            jFormattedTextFieldCPF.requestFocus();
            jFormattedTextFieldCPF.setBackground(color);
        } else {
            if (pf.isCPF()) {
                jFormattedTextFieldCPF.setBackground(colorPadrao);
            } else {
                JOptionPane.showMessageDialog(null, "Compo: CPF, está inválido");
                jFormattedTextFieldCPF.requestFocus();
                jFormattedTextFieldCPF.setBackground(color);
            }
        }
        if (jTextFieldNomeAcompanhante.getBackground() == colorPadrao
                && jFormattedTextFieldCPF.getBackground() == colorPadrao) {
            flagCamposVaziosAcompanhantes = 1;
        }
    }
    
    private void verificaCamposProdutos() {
        if (jTextFieldProdutoNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo: Produto, está em branco");
            jTextFieldProdutoCodigo.requestFocus();
            jTextFieldProdutoCodigo.setBackground(color);
        } else {
            jTextFieldProdutoCodigo.setBackground(colorPadrao);
        }
        if (jTextFieldProdutoCodigo.getBackground() == colorPadrao) {
            flagCamposVaziosProdutos = 1;
        } else {
            flagCamposVaziosProdutos = 0;
        }
    }
    
    private void verificaCamposCheckOut() {
        if (jTextFieldDiarias.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo: Diárias, está em branco");
            jTextFieldDiarias.requestFocus();
            jTextFieldDiarias.setBackground(color);
        } else {
            jTextFieldDiarias.setBackground(colorPadrao);
        }
        
        if (jTextFieldDiarias.getBackground() == colorPadrao) {
            flagCamposVaziosCheckOut = 1;
        }
    }
    
    public static void setReceberClienteEmpresa(String id, String nome) {
        jTextFieldClienteCodigoEmpresa.setText(id);
        jTextFieldClienteNomeEmpresa.setText(nome);
    }
    
    public static void setReceberCliente(String id, String nome) {
        jTextFieldClienteCodigo.setText(id);
        jTextFieldClienteNome.setText(nome);
    }
    
    public static void setReceberProduto(String id, String nome, int quantidade) {
        jTextFieldProdutoCodigo.setText(id);
        jTextFieldProdutoNome.setText(nome);
        quantidadeProduto = quantidade;
        adicionaQuantidadeProdutos(quantidade);
        jLabelQuantidade.setVisible(true);
        jComboBoxQuantidade.setVisible(true);
    }
    
    private void carregaInformacoesQuartoDisponivel(String numero) {
        
        conexao.executaSQL("SELECT * FROM tbl_quarto WHERE quarto_numero='" + numero + "'");
        try {
            if (conexao.rs.next()) {
                quartoId = conexao.rs.getInt("quarto_id");
                jLabelValorAndar.setText(conexao.rs.getString("quarto_andar"));
                jTextAreaDescricao.setText(conexao.rs.getString("quarto_descricao"));
                jTextAreaItensInclusos.setText(conexao.rs.getString("quarto_itens_dispo"));
                jLabelValorTipoQuarto.setText(conexao.rs.getString("quarto_tipo"));
                carregaInformacoesValoresQuarto(quartoId);
                desbloquearCampos(jPanelCheckIn.getComponents());
                desbloquearCampos(jPanelVeiculo.getComponents());
                jTextFieldClienteNomeEmpresa.setEnabled(false);
                jTextFieldPlaca.setEnabled(false);
                jTextFieldModelo.setEnabled(false);
                jTextFieldClienteNome.setEnabled(false);
            }
            conexao.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(frm_hospedagem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregaInformacoesQuartoOcupado(String numero) {
        Date diaHoje = new Date();
        conexao.executaSQL("SELECT * FROM tbl_hospedagem WHERE hospedagem_quarto_numero='" + numero + "' AND hospedagem_status='CheckIn'");
        try {
            if (conexao.rs.next()) {
                idHospedagem = conexao.rs.getInt("hospedagem_id");
                quartoId = conexao.rs.getInt("hospedagem_quarto_id");
                if (conexao.rs.getInt("hospedagem_cliente_empresa_id") == 0) {
                    jCheckBoxEmpresarial.setSelected(false);
                    libercaCampsoEmpresa();
                } else {
                    jCheckBoxEmpresarial.setSelected(true);
                    libercaCampsoEmpresa();
                    jTextFieldClienteCodigoEmpresa.setText(String.valueOf(conexao.rs.getInt("hospedagem_cliente_empresa_id")));
                    jTextFieldClienteNomeEmpresa.setText(conexao.rs.getString("hospedagem_cliente_empresa_nome"));
                }
                bloquearCampos(jPanelCheckIn.getComponents());
                bloquearCampos(jPanelVeiculo.getComponents());
                jTextFieldClienteCodigo.setText(String.valueOf(conexao.rs.getInt("hospedagem_cliente_id")));
                jTextFieldClienteNome.setText(conexao.rs.getString("hospedagem_cliente_nome"));
                jTextFieldDiariasPagas.setText(String.valueOf(conexao.rs.getInt("hospedagem_diarias_pagas")));
                if (conexao.rs.getString("hospedagem_veiculo_placa").isEmpty()) {
                    jCheckBoxPossui.setSelected(false);
                    jTextFieldPlaca.setText("");
                    jTextFieldModelo.setText("");
                } else {
                    jCheckBoxPossui.setSelected(true);
                    jTextFieldPlaca.setText(conexao.rs.getString("hospedagem_veiculo_placa"));
                    jTextFieldModelo.setText(conexao.rs.getString("hospedagem_veiculo_marca"));
                }
                valorDiaria = conexao.rs.getDouble("hospedagem_valor_diaria");
                valorMonetario.formatarValor(String.valueOf(valorDiaria).replaceAll("\\.", ","));
                jLabelValorParcial.setText("Uma diária fica: " + valorMonetario.valorMonetario);
                jLabelValorDiariaAcompanhantes.setText("Valor da diaria: " + valorMonetario.valorMonetario);
                jLabelDiaCheckIn.setText(dataNormal.format(conexao.rs.getDate("hospedagem_checkin_data")));
                jComboBoxFormaPagamento.setSelectedItem(conexao.rs.getString("hospedagem_forma_pagamento"));
                DateTime diaCheckIn = new DateTime(conexao.rs.getDate("hospedagem_checkin_data"));
                verificaHora();
                int dias = Days.daysBetween(diaCheckIn, modalData.getDataHoje()).getDays() + 1;
                jLabelValorDiaria.setText(String.valueOf((valorDiaria * Double.valueOf(dias))));
                jTextFieldDiarias.setText(String.valueOf(dias));
                jLabelValorDesconto.setText("0.0");
                jLabelValorProdutos.setText("0.0");
                jLabelValorTotalProduto.setText("0.0");
                jLabelValorDiariasPagas.setText(String.valueOf(valorDiaria * Integer.valueOf(jTextFieldDiariasPagas.getText())));
                calculaValorTotalCheckOut(Double.valueOf(jLabelValorProdutos.getText()),
                        Double.valueOf(jLabelValorDesconto.getText()),
                        Double.valueOf(jLabelValorDiaria.getText()));
                atualizaValorProdutos(idHospedagem);
                atualizaTabelaProdutos("SELECT * FROM tbl_produtos_hospedagem WHERE "
                        + "produtos_hospedagem_hospedagem_id = " + idHospedagem);
            }
            conexao.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(frm_hospedagem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregaInformacoesValoresQuarto(int id) {
        conexao.executaSQL("SELECT * FROM tbl_quarto_acompanhantes WHERE quarto_acompanhantes_quarto_id = " + id + " AND quarto_cama_quantidade_acompanhantes = 1");
        try {
            if (conexao.rs.next()) {
                valorTotal = conexao.rs.getDouble("quarto_acompanhantes_valor");
                valorMonetario.formatarValor(String.valueOf(conexao.rs.getDouble("quarto_acompanhantes_valor")).replaceAll("\\.", ","));
                jLabelValorParcial.setText("Para uma pessoa: " + valorMonetario.valorMonetario);
                jLabelValorDiariaAcompanhantes.setText("Valor da diaria: " + valorMonetario.valorMonetario);
            }
            conexao.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(frm_hospedagem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void libercaCampsoEmpresa() {
        if (jCheckBoxEmpresarial.isSelected()) {
            jTextFieldClienteNomeEmpresa.setVisible(true);
            jTextFieldClienteCodigoEmpresa.setVisible(true);
            jLabelClienteEmpresa.setVisible(true);
        } else {
            jTextFieldClienteNomeEmpresa.setVisible(false);
            jTextFieldClienteCodigoEmpresa.setVisible(false);
            jLabelClienteEmpresa.setVisible(false);
        }
    }
    
    private void resgataUltimoCadastroCliente() {
        if (flagControleCheckout == 1) {
            conexao.executaSQL("SELECT cliente_id, cliente_nome FROM tbl_cliente WHERE LENGTH(cliente_cpf_cnpj) = 11");
            try {
                if (conexao.rs.last()) {
                    jTextFieldClienteCodigo.setText(String.valueOf(conexao.rs.getInt("cliente_id")));
                    jTextFieldClienteNome.setText(conexao.rs.getString("cliente_nome"));
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            conexao.executaSQL("SELECT cliente_id, cliente_nome FROM tbl_cliente WHERE "
                    + "cliente_id = " + jTextFieldClienteCodigo.getText() + " AND LENGTH(cliente_cpf_cnpj) = 11");
            try {
                if (conexao.rs.last()) {
                    jTextFieldClienteCodigo.setText(String.valueOf(conexao.rs.getInt("cliente_id")));
                    jTextFieldClienteNome.setText(conexao.rs.getString("cliente_nome"));
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado, por favor tente novamente.");
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void resgataUltimoCadastroClienteEmpresa() {
        if (flagControleCheckout == 1) {
            conexao.executaSQL("SELECT cliente_id, cliente_nome FROM "
                    + "tbl_cliente WHERE LENGTH(cliente_cpf_cnpj) = 14");
            try {
                if (conexao.rs.last()) {
                    jTextFieldClienteCodigoEmpresa.setText(String.valueOf(conexao.rs.getInt("cliente_id")));
                    jTextFieldClienteNomeEmpresa.setText(conexao.rs.getString("cliente_nome"));
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            conexao.executaSQL("SELECT cliente_id, cliente_nome FROM tbl_cliente WHERE "
                    + "cliente_id = " + jTextFieldClienteCodigoEmpresa.getText() + " "
                    + "AND LENGTH(cliente_cpf_cnpj) = 14");
            try {
                if (conexao.rs.last()) {
                    jTextFieldClienteCodigoEmpresa.setText(String.valueOf(conexao.rs.getInt("cliente_id")));
                    jTextFieldClienteNomeEmpresa.setText(conexao.rs.getString("cliente_nome"));
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado, por favor tente novamente.");
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void resgataUltimoCadastroProduto() {
        if (flagControleCheckout == 1) {
            conexao.executaSQL("SELECT produto_id, produto_nome, produto_quantidade FROM tbl_produto WHERE produto_quantidade > 0");
            try {
                if (conexao.rs.last()) {
                    jTextFieldProdutoCodigo.setText(String.valueOf(conexao.rs.getInt("produto_id")));
                    jTextFieldProdutoNome.setText(conexao.rs.getString("produto_nome"));
                    quantidadeProduto = conexao.rs.getInt("produto_quantidade");
                    adicionaQuantidadeProdutos(quantidadeProduto);
                    jLabelQuantidade.setVisible(true);
                    jComboBoxQuantidade.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Produto indisponivel ou não cadastrado, por favor verifique.");
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            conexao.executaSQL("SELECT produto_id, produto_nome, produto_quantidade FROM tbl_produto WHERE "
                    + "produto_id = " + jTextFieldProdutoCodigo.getText() + " AND produto_quantidade > 0");
            try {
                if (conexao.rs.last()) {
                    jTextFieldProdutoCodigo.setText(String.valueOf(conexao.rs.getInt("produto_id")));
                    jTextFieldProdutoNome.setText(conexao.rs.getString("produto_nome"));
                    quantidadeProduto = conexao.rs.getInt("produto_quantidade");
                    adicionaQuantidadeProdutos(quantidadeProduto);
                    jLabelQuantidade.setVisible(true);
                    jComboBoxQuantidade.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Produto indisponivel ou não cadastrado, por favor verifique.");
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void limparCampos() {
        LimpaCampos.limpaCampos(jPanelCheckIn.getComponents());
        LimpaCampos.limpaCampos(jPanelVeiculo.getComponents());
        LimpaCampos.limpaCampos(jPanelCheckOut.getComponents());
    }
    
    private void limpaCamposAcompanhantes() {
        LimpaCampos.limpaCampos(jPanelAcompanhantes.getComponents());
        jTableAcompanhantes.removeAll();
    }
    
    private void limpaCamposProdutos() {
        LimpaCampos.limpaCampos(jPanelProdutos.getComponents());
        jTableProdutos.removeAll();
    }
    
    private void limpaCamposCheckOut() {
        LimpaCampos.limpaCampos(jPanelCheckOut.getComponents());
    }
    
    private void ultimaHospedagem(int idCliente) {
        conexao.executaSQL("SELECT hospedagem_id FROM tbl_hospedagem WHERE hospedagem_cliente_id = " + idCliente);
        try {
            if (conexao.rs.last()) {
                idHospedagem = conexao.rs.getInt("hospedagem_id");
                jTextFieldClienteCodigoEmpresa.setEnabled(false);
                jTextFieldClienteCodigoEmpresa.setEnabled(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frm_hospedagem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ultimoCadastroAcompanhantes(int numero) {
        conexao.executaSQL("SELECT acompanhantes_id FROM tbl_acompanhantes "
                + "WHERE acompanhantes_hospedagem_id = " + numero);
        try {
            if (conexao.rs.last()) {
                idUltimoCadastro = conexao.rs.getInt("acompanhantes_id") + 1;
            } else {
                idUltimoCadastro = 1;
            }
            conexao.rs.close();
        } catch (SQLException ex) {
            
        }
    }
    
    private void ultimoCadastroProduto(int numero) {
        conexao.executaSQL("SELECT produtos_hospedagem_id FROM tbl_produtos_hospedagem "
                + "WHERE produtos_hospedagem_hospedagem_id = " + numero);
        try {
            if (conexao.rs.last()) {
                idUltimoCadastro = conexao.rs.getInt("produtos_hospedagem_id") + 1;
            } else {
                idUltimoCadastro = 1;
            }
            conexao.rs.close();
        } catch (SQLException ex) {
            
        }
    }
    
    private void pegarValorProduto(int idProduto) {
        conexao.executaSQL("SELECT produto_valor FROM tbl_produto WHERE produto_id = " + idProduto);
        try {
            if (conexao.rs.next()) {
                produtoValor = conexao.rs.getDouble("produto_valor");
            }
            conexao.rs.close();
        } catch (SQLException ex) {
        }
    }
    
    private void pegarQuantidadeProduto(int idProduto) {
        conexao.executaSQL("SELECT produto_quantidade FROM tbl_produto WHERE produto_id = " + idProduto);
        try {
            if (conexao.rs.next()) {
                quantidadeProduto = conexao.rs.getInt("produto_quantidade");
            }
            conexao.rs.close();
        } catch (SQLException ex) {
        }
    }
    
    private void cadastrarAcompanhantes() {
        esquemaBotoesAcompanhantes();
        verificaCamposAcompanhantes();
        Date acompanhanteData = new Date();
        if (flagCamposVaziosAcompanhantes == 1) {
            ultimoCadastroAcompanhantes(idHospedagem);
            modalAcompanhantes.setHospedagemId(idHospedagem);
            modalAcompanhantes.setCpf(jFormattedTextFieldCPF.getText().replaceAll("[()./-]", ""));
            modalAcompanhantes.setNome(jTextFieldNomeAcompanhante.getText());
            modalAcompanhantes.setId(idUltimoCadastro);
            modalAcompanhantes.setData(acompanhanteData);
            switch (flagAcompanhantes) {
                case 1:
                    conexao.executaSQL("SELECT * FROM tbl_acompanhantes WHERE acompanhantes_cpf = '"
                            + jFormattedTextFieldCPF.getText().replaceAll("[()./-]", "") + "' AND acompanhantes_hospedagem_id = " + idHospedagem);
                    try {
                        if (conexao.rs.next()) {
                            do {
                                JOptionPane.showMessageDialog(rootPane, "Acompanhante já cadastrado!");
                                jTextFieldNomeAcompanhante.requestFocus();
                                jTextFieldNomeAcompanhante.setBackground(color);
                            } while (conexao.rs.next());
                        } else {
                            contarAcompanhantes(quartoId, idHospedagem);
                            limpaCamposAcompanhantes();
                            atualizaTabelaAcompanhantes("SELECT * FROM tbl_acompanhantes WHERE acompanhantes_hospedagem_id = " + idHospedagem);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(frm_hospedagem.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2:
                    modalAcompanhantes.setId(Integer.valueOf(idAcompanhante));
                     {
                        try {
                            dAOAcompanhantes.update(modalAcompanhantes);
                            limpaCamposAcompanhantes();
                            flagAcompanhantes = 1;
                            esquemaBotoesAcompanhantes();
                            atualizaTabelaAcompanhantes("SELECT * FROM tbl_acompanhantes WHERE acompanhantes_hospedagem_id = " + idHospedagem);
                        } catch (SQLException ex) {
                            Logger.getLogger(frm_hospedagem.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case 3:
                    modalAcompanhantes.setId(Integer.valueOf(idAcompanhante));
                     {
                        try {
                            contarAcompanhantes(quartoId, idHospedagem);
                            
                            limpaCamposAcompanhantes();
                            flagAcompanhantes = 1;
                            esquemaBotoesAcompanhantes();
                            atualizaTabelaAcompanhantes("SELECT * FROM tbl_acompanhantes WHERE acompanhantes_hospedagem_id = " + idHospedagem);
                        } catch (SQLException ex) {
                            Logger.getLogger(frm_hospedagem.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                
                default:
                    break;
            }
        }
    }
    
    private void cadastrarProdutos() {
        esquemaBotoesProdutos();
        verificaCamposProdutos();
        Date produtoData = new Date();
        if (flagCamposVaziosProdutos == 1) {
            pegarValorProduto(Integer.valueOf(jTextFieldProdutoCodigo.getText()));
            if (flagProdutos == 1) {
                conexao.executaSQL("SELECT * FROM tbl_produtos_hospedagem "
                        + "WHERE produtos_hospedagem_produtos_id = " + jTextFieldProdutoCodigo.getText() + " AND "
                        + "produtos_hospedagem_hospedagem_id = " + idHospedagem);
                try {
                    if (conexao.rs.next()) {
                        flagProdutos = 2;
                        flagAlteraProduto = 1;
                        int quantidade = (conexao.rs.getInt("produtos_hospedagem_quantidade")
                                + Integer.valueOf(jComboBoxQuantidade.getSelectedItem().toString()));
                        modalHospedagemProdutos.setQuantidade(quantidade);
                        modalHospedagemProdutos.setValorTotal((produtoValor * quantidade));
                        modalHospedagemProdutos.setId(conexao.rs.getInt("produtos_hospedagem_id"));
                    } else {
                        flagProdutos = 1;
                        flagAlteraProduto = 0;
                        int quantidade = Integer.valueOf(jComboBoxQuantidade.getSelectedItem().toString());
                        modalHospedagemProdutos.setQuantidade(quantidade);
                        modalHospedagemProdutos.setValorTotal((produtoValor * quantidade));
                        ultimoCadastroProduto(idHospedagem);
                        modalHospedagemProdutos.setId(idUltimoCadastro);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(frm_hospedagem.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (flagProdutos == 2) {
                int quantidade = quantidadeProdutoHospedagem + Integer.valueOf(jComboBoxQuantidade.getSelectedItem().toString());
                modalHospedagemProdutos.setQuantidade(quantidade);
                modalHospedagemProdutos.setValorTotal((produtoValor * quantidade));
            }
            modalHospedagemProdutos.setIdHospedagem(idHospedagem);
            modalHospedagemProdutos.setIdProduto(Integer.valueOf(jTextFieldProdutoCodigo.getText()));
            pegarValorProduto(Integer.valueOf(jTextFieldProdutoCodigo.getText()));
            modalHospedagemProdutos.setNomeProduto(jTextFieldProdutoNome.getText());
            modalHospedagemProdutos.setData(produtoData);
            switch (flagProdutos) {
                case 1: {
                    try {
                        dAOHospedagemProdutos.create(modalHospedagemProdutos);
                        atualizaValorProdutos(idHospedagem);
                        modalProduto.setQuantidade(quantidadeProduto - Integer.valueOf(jComboBoxQuantidade.getSelectedItem().toString()));
                        modalProduto.setId(Integer.valueOf(jTextFieldProdutoCodigo.getText()));
                        dAOProduto.updateQuantidade(modalProduto);
                        limpaCamposProdutos();
                        atualizaTabelaProdutos("SELECT * FROM tbl_produtos_hospedagem WHERE "
                                + "produtos_hospedagem_hospedagem_id = " + idHospedagem);
                    } catch (SQLException ex) {
                        Logger.getLogger(frm_hospedagem.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                break;
                
                case 2:
                    if (flagAlteraProduto == 0) {
                        modalHospedagemProdutos.setId(Integer.valueOf(idProduto));
                    }
                    try {
                        dAOHospedagemProdutos.update(modalHospedagemProdutos);
                        atualizaValorProdutos(idHospedagem);
                        modalProduto.setQuantidade(quantidadeProduto - Integer.valueOf(jComboBoxQuantidade.getSelectedItem().toString()));
                        modalProduto.setId(Integer.valueOf(jTextFieldProdutoCodigo.getText()));
                        dAOProduto.updateQuantidade(modalProduto);
                        limpaCamposProdutos();
                        flagProdutos = 1;
                        esquemaBotoesProdutos();
                        atualizaTabelaProdutos("SELECT * FROM tbl_produtos_hospedagem WHERE "
                                + "produtos_hospedagem_hospedagem_id = " + idHospedagem);
                    } catch (SQLException ex) {
                        Logger.getLogger(frm_hospedagem.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    break;
                case 3:
                    modalHospedagemProdutos.setId(Integer.valueOf(idProduto));
                    try {
                        dAOHospedagemProdutos.delete(modalHospedagemProdutos);
                        atualizaValorProdutos(idHospedagem);
                        modalProduto.setQuantidade(quantidadeProduto + quantidadeProdutoHospedagem);
                        modalProduto.setId(Integer.valueOf(jTextFieldProdutoCodigo.getText()));
                        dAOProduto.updateQuantidade(modalProduto);
                        limpaCamposProdutos();
                        flagProdutos = 1;
                        esquemaBotoesProdutos();
                        atualizaTabelaProdutos("SELECT * FROM tbl_produtos_hospedagem WHERE "
                                + "produtos_hospedagem_hospedagem_id = " + idHospedagem);
                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(frm_hospedagem.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                default:
                    break;
            }
            
        }
    }
    
    public void atualizaTabelaAcompanhantes(String sql) throws SQLException {
        tableModelAcompanhantes.dados.removeAll(tableModelAcompanhantes.dados);
        conexao.executaSQL(sql);
        while (conexao.rs.next()) {
            ModalAcompanhantes modalQ = new ModalAcompanhantes();
            
            modalQ.setId(conexao.rs.getInt("acompanhantes_id"));
            modalQ.setNome(conexao.rs.getString("acompanhantes_nome"));
            modalQ.setCpf(conexao.rs.getString("acompanhantes_cpf"));
            tableModelAcompanhantes.addRow(modalQ);
        }
        conexao.rs.close();
    }
    
    public void atualizaTabelaProdutos(String sql) throws SQLException {
        tableModelHospedagemProdutos.dados.removeAll(tableModelHospedagemProdutos.dados);
        conexao.executaSQL(sql);
        while (conexao.rs.next()) {
            ModalHospedagemProdutos modalQ = new ModalHospedagemProdutos();
            modalQ.setId(conexao.rs.getInt("produtos_hospedagem_id"));
            modalQ.setIdProduto(conexao.rs.getInt("produtos_hospedagem_produtos_id"));
            modalQ.setNomeProduto(conexao.rs.getString("produtos_hospedagem_nome"));
            modalQ.setValorTotal(conexao.rs.getDouble("produtos_hospedagem_produtos_valor"));
            modalQ.setQuantidade(conexao.rs.getInt("produtos_hospedagem_quantidade"));
            tableModelHospedagemProdutos.addRow(modalQ);
        }
        conexao.rs.close();
    }
    
    public void atualizaValorProdutos(int idHospedagem) {
        conexao.executaSQL("SELECT SUM(produtos_hospedagem_produtos_valor) as soma FROM "
                + "`tbl_produtos_hospedagem` WHERE produtos_hospedagem_hospedagem_id = " + idHospedagem);
        try {
            if (conexao.rs.next()) {
                jLabelValorProdutos.setText(String.valueOf(conexao.rs.getDouble("soma")));
                valorMonetario.formatarValor(String.valueOf(conexao.rs.getDouble("soma")).replaceAll("\\.", ","));
                jLabelValorTotalProduto.setText("Total de produtos: " + valorMonetario.valorMonetario);
                calculaValorTotalCheckOut(Double.valueOf(jLabelValorProdutos.getText()),
                        Double.valueOf(jLabelValorDesconto.getText()),
                        Double.valueOf(jLabelValorDiaria.getText()));
            }
            conexao.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(frm_hospedagem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void maiusculo(Component[] componente) {
        for (Component comp : componente) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setDocument(new Maiusculo());
            } else if (comp instanceof JFormattedTextField) {
                ((JFormattedTextField) comp).setDocument(new Maiusculo());
            }
        }
    }
    
    private void contarAcompanhantes(int quartoId, int hospedagemId) {
        if (flagAcompanhantes == 1) {
            
            int quantidade = 0;
            conexao.executaSQL("SELECT COUNT(acompanhantes_id) FROM tbl_acompanhantes WHERE acompanhantes_hospedagem_id = " + hospedagemId);
            
            try {
                if (conexao.rs.next()) {
                    quantidade = conexao.rs.getInt("COUNT(acompanhantes_id)") + 1;
                } else {
                    quantidade = 1;
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            conexao.executaSQL("SELECT quarto_acompanhantes_id, quarto_acompanhantes_valor FROM tbl_quarto_acompanhantes WHERE quarto_cama_quantidade_acompanhantes = "
                    + (quantidade + 1) + " AND quarto_acompanhantes_quarto_id = " + quartoId);
            try {
                if (conexao.rs.next()) {
                    valorTotal = conexao.rs.getDouble("quarto_acompanhantes_valor");
                    valorMonetario.formatarValor(String.valueOf(valorTotal).replaceAll("\\.", ","));
                    jLabelValorDiariaAcompanhantes.setText("Valor da diaria: " + valorMonetario.valorMonetario);
                    jLabelValorParcial.setText("Uma diária fica: " + valorMonetario.valorMonetario);
                    valorDiaria = valorTotal;
                    dAOAcompanhantes.create(modalAcompanhantes);
                    if (flagControleBotao == 1) {
                        modalHospedagem.setValorHospedagem(valorTotal);
                        modalHospedagem.setValorTotal(valorTotal);
                        modalHospedagem.setId(idHospedagem);
                        try {
                            dAOHospedagem.updateValorDiaria(modalHospedagem);
                            dAOHospedagem.updateValorTotal(modalHospedagem);
                            carregaInformacoesValoresQuarto(quartoId);
                        } catch (SQLException ex) {
                            Logger.getLogger(frm_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    if (flagAcompanhantes == 3) {
                        dAOAcompanhantes.delete(modalAcompanhantes);
                        flagBotaoDesativado = 0;
                        jButtonAdicionarPessoa.setVisible(true);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Número maxímo de pessoas no quarto atingido.");
                        flagBotaoDesativado = 1;
                        jButtonAdicionarPessoa.setVisible(false);
                    }
                    
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (flagAcompanhantes == 3) {
            int quantidade = 0;
            conexao.executaSQL("SELECT COUNT(acompanhantes_id) FROM tbl_acompanhantes WHERE acompanhantes_hospedagem_id = " + hospedagemId);
            try {
                if (conexao.rs.next()) {
                    quantidade = conexao.rs.getInt("COUNT(acompanhantes_id)") - 1;
                } else {
                    quantidade = 1;
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            conexao.executaSQL("SELECT quarto_acompanhantes_id, quarto_acompanhantes_valor FROM tbl_quarto_acompanhantes WHERE quarto_cama_quantidade_acompanhantes = "
                    + (quantidade + 1) + " AND quarto_acompanhantes_quarto_id = " + quartoId);
            try {
                if (conexao.rs.next()) {
                    valorTotal = conexao.rs.getDouble("quarto_acompanhantes_valor");
                    valorMonetario.formatarValor(String.valueOf(valorTotal).replaceAll("\\.", ","));
                    jLabelValorDiariaAcompanhantes.setText("Valor da diaria: " + valorMonetario.valorMonetario);
                    jLabelValorParcial.setText("Uma diária fica: " + valorMonetario.valorMonetario);
                    valorDiaria = valorTotal;
                    dAOAcompanhantes.delete(modalAcompanhantes);
                    if (flagControleBotao == 1) {
                        modalHospedagem.setValorHospedagem(valorTotal);
                        modalHospedagem.setValorTotal(valorTotal);
                        modalHospedagem.setId(idHospedagem);
                        try {
                            dAOHospedagem.updateValorDiaria(modalHospedagem);
                            dAOHospedagem.updateValorTotal(modalHospedagem);
                        } catch (SQLException ex) {
                            Logger.getLogger(frm_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    if (flagAcompanhantes == 3) {
                        dAOAcompanhantes.delete(modalAcompanhantes);
                        flagBotaoDesativado = 0;
                        jButtonAdicionarPessoa.setVisible(true);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Número maxímo de pessoas no quarto atingido.");
                        flagBotaoDesativado = 1;
                        jButtonAdicionarPessoa.setVisible(false);
                    }
                    
                }
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calculaValorTotalCheckOut(double valorProduto, double valorDesconto, double valorDiaria) {
        valorTotalCheckOut = (valorProduto + valorDiaria) - valorDesconto;
        valorMonetario.formatarValor(String.valueOf(valorTotalCheckOut).replaceAll("\\.", ","));
        jLabelValorTotal.setText(valorMonetario.valorMonetario);
    }
    
    private static void adicionaQuantidadeProdutos(int quantidade) {
        jComboBoxQuantidade.removeAllItems();
        if (quantidade == 0) {
            jLabelQuantidade.setVisible(false);
            jComboBoxQuantidade.setVisible(false);
        } else {
            jLabelQuantidade.setVisible(true);
            jComboBoxQuantidade.setVisible(true);
            for (int i = 0; i < quantidade; i++) {
                jComboBoxQuantidade.addItem(String.valueOf(i + 1));
            }
        }
        
    }
    
    private void desabilitaBotoes() {
        for (int i = 0; i < jDesktopPaneQuartos.getComponentCount(); i++) {
            jDesktopPaneQuartos.getComponent(i).setEnabled(false);
        }
    }
    
    private void habilitaBotoes() {
        for (int i = 0; i < jDesktopPaneQuartos.getComponentCount(); i++) {
            jDesktopPaneQuartos.getComponent(i).setEnabled(true);
        }
    }
    
    private void bloquearCampos(Component[] componente) {
        for (Component comp : componente) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setEnabled(false);
            }
            if (comp instanceof JCheckBox) {
                ((JCheckBox) comp).setEnabled(false);
            }
            if (comp instanceof JComboBox) {
                ((JComboBox) comp).setEnabled(false);
            }
        }
    }
    
    private void desbloquearCampos(Component[] componente) {
        for (Component comp : componente) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setEnabled(true);
            }
            if (comp instanceof JCheckBox) {
                ((JCheckBox) comp).setEnabled(true);
            }
            if (comp instanceof JComboBox) {
                ((JComboBox) comp).setEnabled(true);
            }
        }
    }
    
    private void verificaHora() {
        Date diaHoje = new Date();
        DateTime hoje = new DateTime(diaHoje);
        Calendar agora = Calendar.getInstance();
        Calendar ontem = Calendar.getInstance();
        ontem.add(agora.DATE, -1);
        Date present;
        try {
            present = parser.parse(v_principal.jLabelHora.getText());
            Date closed = parser.parse("12:00");
            if (present.after(closed)) {
                modalData.setDataHoje(hoje);
            } else {
                DateTime ontemD = new DateTime(ontem.getTime());
                modalData.setDataHoje(ontemD);
            }
        } catch (ParseException ex) {
            Logger.getLogger(frm_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionarPessoa;
    private javax.swing.JButton jButtonAdicionarProduto;
    private javax.swing.JButton jButtonAdicionarProdutos;
    private javax.swing.JButton jButtonAlterarPessoa;
    private javax.swing.JButton jButtonAlterarProduto;
    private javax.swing.JButton jButtonCaixa;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCancelarPessoa;
    private javax.swing.JButton jButtonCancelarProduto;
    private javax.swing.JButton jButtonDesistir;
    private javax.swing.JButton jButtonFinalizarCheckOut;
    private javax.swing.JButton jButtonFinalizarHospedagem;
    private javax.swing.JButton jButtonIniciarCheckIn;
    private javax.swing.JButton jButtonRemoverPessoa;
    private javax.swing.JButton jButtonRemoverProduto;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVerificaAcomapoanhantes;
    private javax.swing.JButton jButtonVoltarCheckOut;
    private javax.swing.JCheckBox jCheckBoxEmpresarial;
    private javax.swing.JCheckBox jCheckBoxPossui;
    private javax.swing.JComboBox<String> jComboBoxFormaPagamento;
    private static javax.swing.JComboBox<String> jComboBoxQuantidade;
    private javax.swing.JDesktopPane jDesktopPaneQuartos;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPF;
    private javax.swing.JLabel jLabeAdicionarAcompanhantes;
    private javax.swing.JLabel jLabeAdicionarAcompanhantes1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelAndar;
    private javax.swing.JLabel jLabelCaixa;
    private javax.swing.JLabel jLabelCheckIn;
    private javax.swing.JLabel jLabelCliente1;
    private javax.swing.JLabel jLabelClienteEmpresa;
    private javax.swing.JLabel jLabelDesconto;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelDescricao1;
    private javax.swing.JLabel jLabelDiaCheckIn;
    private javax.swing.JLabel jLabelDiarias;
    private javax.swing.JLabel jLabelDiariasPagas;
    private javax.swing.JLabel jLabelHospedagem;
    private javax.swing.JLabel jLabelHospedagem1;
    private javax.swing.JLabel jLabelIndisponivel;
    private javax.swing.JLabel jLabelModelo;
    private javax.swing.JLabel jLabelNomeAcompanhante;
    private javax.swing.JLabel jLabelNomeAcompanhante1;
    private javax.swing.JLabel jLabelNumeroQuarto;
    private javax.swing.JLabel jLabelPlaca;
    private static javax.swing.JLabel jLabelQuantidade;
    private javax.swing.JLabel jLabelQuarto;
    private javax.swing.JLabel jLabelResumoDesconto;
    private javax.swing.JLabel jLabelResumoDiaria;
    private javax.swing.JLabel jLabelResumoDiariasPagas;
    private javax.swing.JLabel jLabelResumoProdutos;
    private javax.swing.JLabel jLabelTipoQuarto;
    private javax.swing.JLabel jLabelValorAndar;
    private javax.swing.JLabel jLabelValorDesconto;
    private javax.swing.JLabel jLabelValorDiaria;
    private javax.swing.JLabel jLabelValorDiariaAcompanhantes;
    private javax.swing.JLabel jLabelValorDiariasPagas;
    private javax.swing.JLabel jLabelValorParcial;
    private javax.swing.JLabel jLabelValorProdutos;
    private javax.swing.JLabel jLabelValorTipoQuarto;
    private javax.swing.JLabel jLabelValorTotal;
    private javax.swing.JLabel jLabelValorTotalProduto;
    private javax.swing.JPanel jPanelAcompanhantes;
    private javax.swing.JPanel jPanelApresentacao;
    private javax.swing.JPanel jPanelCheckIn;
    private javax.swing.JPanel jPanelCheckOut;
    private javax.swing.JPanel jPanelGeral;
    private javax.swing.JPanel jPanelHospedagem;
    private javax.swing.JPanel jPanelIndisponivel;
    private javax.swing.JPanel jPanelProdutos;
    private javax.swing.JPanel jPanelVeiculo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableAcompanhantes;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextArea jTextAreaItensInclusos;
    private static javax.swing.JTextField jTextFieldClienteCodigo;
    private static javax.swing.JTextField jTextFieldClienteCodigoEmpresa;
    private static javax.swing.JTextField jTextFieldClienteNome;
    private static javax.swing.JTextField jTextFieldClienteNomeEmpresa;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldDiarias;
    private javax.swing.JTextField jTextFieldDiariasPagas;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldNomeAcompanhante;
    private javax.swing.JTextField jTextFieldPlaca;
    private static javax.swing.JTextField jTextFieldProdutoCodigo;
    private static javax.swing.JTextField jTextFieldProdutoNome;
    // End of variables declaration//GEN-END:variables

}
