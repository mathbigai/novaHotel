/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.processo;

import Conexao.conexao;
import DAO.processo.DAOHospedagem;
import DAO.relatorios.processo.DAORelatorioContratoHospedagem;
import DAO.relatorios.processo.DAORelatorioFechaCaixa;
import DAO.relatorios.processo.DAORelatorioResumoHospedagem;
import Model.processo.ModalAcompanhantes;
import Model.processo.ModalHospedagem;
import Model.processo.ModalHospedagemProdutos;
import Model.table.processo.TableModelAcompanhantes;
import Model.table.processo.TableModelHospedagemProdutos;
import Util.EditoresTable.Paginacao.ModeloTabela;
import Util.EditoresTable.Paginacao.PaginadorTabela;
import Util.EditoresTable.Paginacao.ProvedorDadosPaginacao;
import Util.LimpaCampos;
import Util.Maiusculo;
import Util.ValorMonetario;
import Util.Verificar.VerificarUsuario;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author Matheus
 */
public class frm_consulta_hospedagem extends javax.swing.JInternalFrame {

    DateFormat dataNormal = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat dateFormat = new SimpleDateFormat("MMMMM");
    DateFormat ano = new SimpleDateFormat("yyyy");
    DateFormat dateFormat2 = new SimpleDateFormat("yyy-MM-dd");
    DAOHospedagem daoU = new DAOHospedagem();
    DAORelatorioFechaCaixa dAORelatorioFechaCaixa = new DAORelatorioFechaCaixa();
    private PaginadorTabela<ModalHospedagem> paginadorTabela;
    private JComboBox<Integer> comboBoxLinhasPermitidas;
    private static frm_consulta_hospedagem frm_consulta_hospedagem;
    conexao conexao = new conexao();
    int flag = 0, flagCamposVaziosCheckIn = 0, flagCamposVaziosAcompanhantes = 0, flagCamposVaziosProdutos = 0, id = 0, flagAcompanhantes = 0,
            idHospedagem = 0, idUltimoCadastro = 0, quartoId = 0, flagBotaoDesativado = 0, flagProdutos = 0,
            flagControleCheckout = 0, flagAlteraProduto = 0, quantidadeProduto = 0, quantidadeProdutoHospedagem = 0,
            flagCamposVaziosCheckOut = 0, flagSemAcompanhante = 0, posX, posY;
    double valorTotal = 0.0, valorTotalCheckOut = 0.0, valorDiaria = 0.0,
            produtoValor = 0.0, valorTotalProduto = 0.0, contaValorTotal = 0.0;
    TableModelAcompanhantes tableModelAcompanhantes = new TableModelAcompanhantes();
    TableModelHospedagemProdutos tableModelHospedagemProdutos = new TableModelHospedagemProdutos();
    ValorMonetario valorMonetario = new ValorMonetario();
    VerificarUsuario verificaUsuario = new VerificarUsuario();
    String sql = "", sqlPesquisa = "", texto = "";
    Date inicio = null, fim = null;
    ArrayList<Double> somaTotal = new ArrayList();
    private final JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem menuItem = null;
    private JMenu menu = null;

    /**
     * Creates new form frm_consulta_hospedagem
     *
     * @return
     */
    public static frm_consulta_hospedagem getInstancia() {
        if (frm_consulta_hospedagem == null) {
            frm_consulta_hospedagem = new frm_consulta_hospedagem();
        }
        return frm_consulta_hospedagem;
    }

    public frm_consulta_hospedagem() {
        initComponents();

        Calendar c = Calendar.getInstance();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        d = c.getTime();

        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        inicio = c.getTime();

        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        fim = c.getTime();
        jTextFieldBusca.setDocument(new Maiusculo());
        jLabelVariavalBuscaInicio.setText("CheckIn");
        jLabelVariavalBuscaFim.setText("CheckIn");
        jLabelVariavalBusca.setText("Usuário");
        jDateChooserDataInico.setDate(inicio);
        jDateChooserDataFinal.setDate(fim);

        jTableAcompanhantes.setModel(tableModelAcompanhantes);
        jTableProdutos.setModel(tableModelHospedagemProdutos);
        jButtonCancelar.setVisible(false);
        jButtonImprimir.setVisible(false);
        jButtonLimparBusca.setVisible(false);
        if (verificaUsuario.getAdm() == 1) {
            texto = dataNormal.format(jDateChooserDataInico.getDate()) + " ATÉ "
                    + dataNormal.format(jDateChooserDataFinal.getDate());
            sql = "SELECT * FROM tbl_hospedagem WHERE hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'"
                    + " ORDER BY hospedagem_id DESC";
            somarValoresTabela("WHERE hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'"
                    + " ORDER BY hospedagem_id DESC");

            sqlPesquisa = "SELECT c.*, cli.*, h.*, (SELECT (SUM(h.hospedagem_valor_total)) "
                    + "FROM tbl_hospedagem h WHERE h.hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "') as SOMA FROM tbl_cabecalho c, "
                    + "tbl_cliente cli INNER JOIN tbl_hospedagem h on cli.cliente_id=h.hospedagem_cliente_id "
                    + "WHERE h.hospedagem_checkin_data between " + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' ORDER BY h.hospedagem_id DESC;";
        } else {
            texto = dataNormal.format(jDateChooserDataInico.getDate()) + " ATÉ "
                    + dataNormal.format(jDateChooserDataFinal.getDate()) + " - POR " + jTextFieldBusca.getText();
            sql = "SELECT * FROM tbl_hospedagem WHERE hospedagem_usuario_id ="
                    + " " + verificaUsuario.id + " AND hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' "
                    + "ORDER BY hospedagem_id DESC";
            somarValoresTabela("WHERE hospedagem_usuario_id ="
                    + " " + verificaUsuario.id + " AND hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' "
                    + "ORDER BY hospedagem_id DESC");
            sqlPesquisa = "SELECT c.*, cli.*, h.*, (SELECT (SUM(h.hospedagem_valor_total)) "
                    + "FROM tbl_hospedagem h WHERE h.hospedagem_usuario_id ="
                    + " " + verificaUsuario.id + " AND h.hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "') as SOMA FROM tbl_cabecalho c, "
                    + "tbl_cliente cli INNER JOIN tbl_hospedagem h on cli.cliente_id=h.hospedagem_cliente_id "
                    + "WHERE h.hospedagem_usuario_id ="
                    + " " + verificaUsuario.id + " AND h.hospedagem_checkin_data between " + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' ORDER BY h.hospedagem_id DESC;";
        }
        exibirTabela(sql);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanelGeral = new javax.swing.JPanel();
        jPanelConsulta = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsulta = new javax.swing.JTable();
        jPanelPaginacao = new javax.swing.JPanel();
        jButtonBuscar = new javax.swing.JButton();
        jButtonLimparBusca = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelBusca = new javax.swing.JLabel();
        jLabelVariavalBusca = new javax.swing.JLabel();
        jTextFieldBusca = new javax.swing.JTextField();
        jLabelDataInicio = new javax.swing.JLabel();
        jLabelVariavalBuscaInicio = new javax.swing.JLabel();
        jDateChooserDataInico = new com.toedter.calendar.JDateChooser();
        jLabelDataFinal = new javax.swing.JLabel();
        jLabelVariavalBuscaFim = new javax.swing.JLabel();
        jDateChooserDataFinal = new com.toedter.calendar.JDateChooser();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanelHospedagem = new javax.swing.JPanel();
        jTabbedPaneHospedagem = new javax.swing.JTabbedPane();
        jPanelGeralHospedagem = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelCheckIn = new javax.swing.JPanel();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<>();
        jPanelVeiculo = new javax.swing.JPanel();
        jCheckBoxPossui = new javax.swing.JCheckBox();
        jLabelPlaca = new javax.swing.JLabel();
        jTextFieldPlaca = new javax.swing.JTextField();
        jLabelModelo = new javax.swing.JLabel();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldClienteNome = new javax.swing.JTextField();
        jTextFieldClienteCodigo = new javax.swing.JTextField();
        jLabelCliente = new javax.swing.JLabel();
        jLabelValorParcial = new javax.swing.JLabel();
        jDateChooserCheckIn = new com.toedter.calendar.JDateChooser();
        jTextFieldEmpresaNome = new javax.swing.JTextField();
        jTextFieldEmpresaCodigo = new javax.swing.JTextField();
        jLabelEmpresa = new javax.swing.JLabel();
        jTextFieldDiariaspagas = new javax.swing.JTextField();
        jLabelDiariasPagas = new javax.swing.JLabel();
        jPanelCheckOut = new javax.swing.JPanel();
        jTextFieldDiarias = new javax.swing.JTextField();
        jLabelDiarias = new javax.swing.JLabel();
        jLabelValorDiaria = new javax.swing.JLabel();
        jLabelResumoDiaria = new javax.swing.JLabel();
        jLabelResumoProdutos = new javax.swing.JLabel();
        jLabelResumoDesconto = new javax.swing.JLabel();
        jLabelValorProdutos = new javax.swing.JLabel();
        jLabelValorDesconto = new javax.swing.JLabel();
        jDateChooserCheckOut = new com.toedter.calendar.JDateChooser();
        jLabelValorTotal = new javax.swing.JLabel();
        jLabelResumoDiariasPagas = new javax.swing.JLabel();
        jLabelValorDiariasPagas = new javax.swing.JLabel();
        jPanelAcompanhantes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAcompanhantes = new javax.swing.JTable();
        jFormattedTextFieldCPF = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeAcompanhante = new javax.swing.JTextField();
        jLabelNomeAcompanhante = new javax.swing.JLabel();
        jLabelValorDiariaAcompanhantes = new javax.swing.JLabel();
        jPanelProdutos = new javax.swing.JPanel();
        jTextFieldProdutoCodigo = new javax.swing.JTextField();
        jTextFieldProdutoNome = new javax.swing.JTextField();
        jLabelNomeAcompanhante1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jLabelValorTotalProduto = new javax.swing.JLabel();
        jLabelNumeroQuarto = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jLabelCodigo = new javax.swing.JLabel();
        jLabelQuarto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButtonVerificar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabelValorTotalTexto = new javax.swing.JLabel();
        jLabelValorTotalBusca = new javax.swing.JLabel();
        jButtonCaixa = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Consulta Hospedagem");
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

        jPanelGeral.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelGeral.setLayout(new java.awt.CardLayout());

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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableConsultaMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTableConsulta);

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setToolTipText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonLimparBusca.setText("Limpar");
        jButtonLimparBusca.setToolTipText("Buscar");
        jButtonLimparBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparBuscaActionPerformed(evt);
            }
        });

        jLabelBusca.setText("Buscar por");

        jTextFieldBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaKeyReleased(evt);
            }
        });

        jLabelDataInicio.setText("Inicio");

        jDateChooserDataInico.setDateFormatString("dd/MM/yyyy");

        jLabelDataFinal.setText("Final");

        jDateChooserDataFinal.setDateFormatString("dd/MM/yyyy");

        jCheckBox2.setText("ChekOut");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelVariavalBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooserDataInico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelDataInicio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVariavalBuscaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDateChooserDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelDataFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVariavalBuscaFim, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelVariavalBuscaFim, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelDataInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelVariavalBuscaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserDataInico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelBusca)
                            .addComponent(jLabelVariavalBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox2))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelConsultaLayout = new javax.swing.GroupLayout(jPanelConsulta);
        jPanelConsulta.setLayout(jPanelConsultaLayout);
        jPanelConsultaLayout.setHorizontalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPaginacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonLimparBusca)
                            .addComponent(jButtonBuscar))))
                .addContainerGap())
        );
        jPanelConsultaLayout.setVerticalGroup(
            jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaLayout.createSequentialGroup()
                        .addComponent(jButtonBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimparBusca))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelGeral.add(jPanelConsulta, "consulta");

        jComboBoxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Qual a forma de pagamento?", "Dinheiro", "Cartão", "PIX" }));
        jComboBoxFormaPagamento.setEnabled(false);

        jPanelVeiculo.setBorder(javax.swing.BorderFactory.createTitledBorder("Veículo"));
        jPanelVeiculo.setEnabled(false);

        jCheckBoxPossui.setText("Possui?");
        jCheckBoxPossui.setEnabled(false);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxPossui)
                    .addGroup(jPanelVeiculoLayout.createSequentialGroup()
                        .addGroup(jPanelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPlaca)
                            .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jLabelModelo)))))
        );
        jPanelVeiculoLayout.setVerticalGroup(
            jPanelVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVeiculoLayout.createSequentialGroup()
                .addComponent(jLabelModelo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVeiculoLayout.createSequentialGroup()
                .addComponent(jCheckBoxPossui)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPlaca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTextFieldClienteNome.setEnabled(false);
        jTextFieldClienteNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteNomeActionPerformed(evt);
            }
        });

        jTextFieldClienteCodigo.setToolTipText("Clique em F2 para pegar usar o ultimo cliente cadastrado, ou F4 para uma busca mais personalizada.");
        jTextFieldClienteCodigo.setEnabled(false);
        jTextFieldClienteCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldClienteCodigoKeyReleased(evt);
            }
        });

        jLabelCliente.setText("Hóspede");

        jLabelValorParcial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelValorParcial.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorParcial.setText("jLabel1");

        jDateChooserCheckIn.setDateFormatString("dd/MM/yyyy");
        jDateChooserCheckIn.setEnabled(false);

        jTextFieldEmpresaNome.setEnabled(false);
        jTextFieldEmpresaNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmpresaNomeActionPerformed(evt);
            }
        });

        jTextFieldEmpresaCodigo.setToolTipText("Clique em F2 para pegar usar o ultimo cliente cadastrado, ou F4 para uma busca mais personalizada.");
        jTextFieldEmpresaCodigo.setEnabled(false);
        jTextFieldEmpresaCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldEmpresaCodigoKeyReleased(evt);
            }
        });

        jLabelEmpresa.setText("Empresa");

        jTextFieldDiariaspagas.setEnabled(false);

        jLabelDiariasPagas.setText("Diárias Pagas");

        javax.swing.GroupLayout jPanelCheckInLayout = new javax.swing.GroupLayout(jPanelCheckIn);
        jPanelCheckIn.setLayout(jPanelCheckInLayout);
        jPanelCheckInLayout.setHorizontalGroup(
            jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckInLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelValorParcial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelCheckInLayout.createSequentialGroup()
                        .addComponent(jLabelCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldClienteNome))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckInLayout.createSequentialGroup()
                        .addComponent(jLabelEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEmpresaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEmpresaNome)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooserCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCheckInLayout.createSequentialGroup()
                        .addComponent(jPanelVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxFormaPagamento, 0, 319, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckInLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabelDiariasPagas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDiariaspagas, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanelCheckInLayout.setVerticalGroup(
            jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckInLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserCheckIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldEmpresaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldEmpresaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelEmpresa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCliente)
                    .addComponent(jTextFieldClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCheckInLayout.createSequentialGroup()
                        .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDiariaspagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDiariasPagas))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValorParcial)
                .addGap(96, 96, 96))
        );

        jTabbedPane1.addTab("CheckIn", jPanelCheckIn);

        jTextFieldDiarias.setToolTipText("Esse valor será multiplicado a partir do valor de uma diaria, levando em consideração a quantidade de pessoas informadas no Check-In");
        jTextFieldDiarias.setEnabled(false);
        jTextFieldDiarias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDiariasKeyReleased(evt);
            }
        });

        jLabelDiarias.setText("Quantas Diarias?");

        jLabelValorDiaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelValorDiaria.setText("jLabel7");

        jLabelResumoDiaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResumoDiaria.setText("Diárias");

        jLabelResumoProdutos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResumoProdutos.setText("Gastos com produtos");

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

        jLabelValorDesconto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelValorDesconto.setForeground(new java.awt.Color(255, 0, 0));
        jLabelValorDesconto.setText("jLabel2");

        jDateChooserCheckOut.setDateFormatString("dd/MM/yyyy");
        jDateChooserCheckOut.setEnabled(false);

        jLabelValorTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorTotal.setText("R$ 0.0");

        jLabelResumoDiariasPagas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResumoDiariasPagas.setForeground(new java.awt.Color(255, 0, 0));
        jLabelResumoDiariasPagas.setText("Diárias Pagas");

        jLabelValorDiariasPagas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelValorDiariasPagas.setForeground(new java.awt.Color(255, 0, 0));
        jLabelValorDiariasPagas.setText("jLabel2");

        javax.swing.GroupLayout jPanelCheckOutLayout = new javax.swing.GroupLayout(jPanelCheckOut);
        jPanelCheckOut.setLayout(jPanelCheckOutLayout);
        jPanelCheckOutLayout.setHorizontalGroup(
            jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                        .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelDiarias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldDiarias, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooserCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelValorTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
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
                        .addComponent(jLabelResumoDiariasPagas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelValorDiariasPagas)))
                .addContainerGap())
        );
        jPanelCheckOutLayout.setVerticalGroup(
            jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckOutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDiarias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldDiarias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooserCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorDiaria)
                    .addComponent(jLabelResumoDiaria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorProdutos)
                    .addComponent(jLabelResumoProdutos))
                .addGap(7, 7, 7)
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorDiariasPagas)
                    .addComponent(jLabelResumoDiariasPagas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCheckOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorDesconto)
                    .addComponent(jLabelResumoDesconto))
                .addGap(18, 18, 18)
                .addComponent(jLabelValorTotal)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CheckOut", jPanelCheckOut);

        javax.swing.GroupLayout jPanelGeralHospedagemLayout = new javax.swing.GroupLayout(jPanelGeralHospedagem);
        jPanelGeralHospedagem.setLayout(jPanelGeralHospedagemLayout);
        jPanelGeralHospedagemLayout.setHorizontalGroup(
            jPanelGeralHospedagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanelGeralHospedagemLayout.setVerticalGroup(
            jPanelGeralHospedagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPaneHospedagem.addTab("Geral", jPanelGeralHospedagem);

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
        jFormattedTextFieldCPF.setEnabled(false);

        jLabel3.setText("CPF");

        jTextFieldNomeAcompanhante.setEnabled(false);

        jLabelNomeAcompanhante.setText("Nome");

        jLabelValorDiariaAcompanhantes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelValorDiariaAcompanhantes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorDiariaAcompanhantes.setText("jLabel2");

        javax.swing.GroupLayout jPanelAcompanhantesLayout = new javax.swing.GroupLayout(jPanelAcompanhantes);
        jPanelAcompanhantes.setLayout(jPanelAcompanhantesLayout);
        jPanelAcompanhantesLayout.setHorizontalGroup(
            jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                        .addGroup(jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomeAcompanhante, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                                .addComponent(jLabelNomeAcompanhante)
                                .addGap(0, 237, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldCPF)
                            .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 236, Short.MAX_VALUE)))
                        .addGap(44, 44, 44))
                    .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAcompanhantesLayout.createSequentialGroup()
                        .addComponent(jLabelValorDiariaAcompanhantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanelAcompanhantesLayout.setVerticalGroup(
            jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAcompanhantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAcompanhantesLayout.createSequentialGroup()
                        .addComponent(jLabelNomeAcompanhante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNomeAcompanhante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValorDiariaAcompanhantes)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPaneHospedagem.addTab("Acompanhantes", jPanelAcompanhantes);

        jTextFieldProdutoCodigo.setEnabled(false);
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

        jLabelValorTotalProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelValorTotalProduto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorTotalProduto.setText("jLabel2");

        javax.swing.GroupLayout jPanelProdutosLayout = new javax.swing.GroupLayout(jPanelProdutos);
        jPanelProdutos.setLayout(jPanelProdutosLayout);
        jPanelProdutosLayout.setHorizontalGroup(
            jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelValorTotalProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelProdutosLayout.createSequentialGroup()
                        .addComponent(jLabelNomeAcompanhante1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelProdutosLayout.createSequentialGroup()
                        .addComponent(jTextFieldProdutoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldProdutoNome))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelProdutosLayout.setVerticalGroup(
            jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomeAcompanhante1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldProdutoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldProdutoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValorTotalProduto)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPaneHospedagem.addTab("Produtos Consumidos", jPanelProdutos);

        jLabelNumeroQuarto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelNumeroQuarto.setText("jLabel2");

        jTextFieldCodigo.setEditable(false);
        jTextFieldCodigo.setEnabled(false);

        jLabelCodigo.setText("Código");

        jLabelQuarto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelQuarto.setText("Quarto");

        javax.swing.GroupLayout jPanelHospedagemLayout = new javax.swing.GroupLayout(jPanelHospedagem);
        jPanelHospedagem.setLayout(jPanelHospedagemLayout);
        jPanelHospedagemLayout.setHorizontalGroup(
            jPanelHospedagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHospedagemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHospedagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneHospedagem)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHospedagemLayout.createSequentialGroup()
                        .addComponent(jLabelCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelQuarto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNumeroQuarto)))
                .addContainerGap())
        );
        jPanelHospedagemLayout.setVerticalGroup(
            jPanelHospedagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHospedagemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHospedagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHospedagemLayout.createSequentialGroup()
                        .addGroup(jPanelHospedagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelQuarto)
                            .addComponent(jLabelNumeroQuarto))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHospedagemLayout.createSequentialGroup()
                        .addGroup(jPanelHospedagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jTabbedPaneHospedagem)
                .addContainerGap())
        );

        jPanelGeral.add(jPanelHospedagem, "hospedagem");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("CONSULTA HOSPEDAGEM");

        jButtonVerificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Zoom-Fit.png"))); // NOI18N
        jButtonVerificar.setToolTipText("Verificar");
        jButtonVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerificarActionPerformed(evt);
            }
        });

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Relatorio3.png"))); // NOI18N
        jButtonImprimir.setToolTipText("Imprimir");
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Remove.png"))); // NOI18N
        jButtonCancelar.setToolTipText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabelValorTotalTexto.setText("Valor Total");

        jLabelValorTotalBusca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelValorTotalBusca.setText("jLabel4");

        jButtonCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Bullish.png"))); // NOI18N
        jButtonCaixa.setToolTipText("Fechar Caixa neste Periodo");
        jButtonCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCaixaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(450, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabelValorTotalTexto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelValorTotalBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButtonImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonCaixa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(96, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelValorTotalTexto)
                    .addComponent(jLabelValorTotalBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addComponent(jPanelGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaKeyPressed

    }//GEN-LAST:event_jTextFieldBuscaKeyPressed

    private void jTextFieldBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaKeyReleased

    }//GEN-LAST:event_jTextFieldBuscaKeyReleased

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        String variavelLabel = "", complementoSql = "";
        DateFormat dateFormat2 = new SimpleDateFormat("yyy-MM-dd");
        int flag = 0;

        if (jDateChooserDataInico.getDate() != null && jDateChooserDataFinal.getDate() != null) {
            if (jDateChooserDataInico.getDate().compareTo(jDateChooserDataFinal.getDate()) > 0) {
                JOptionPane.showMessageDialog(null, "Data de Início não pode ser menor que data final.\nPor favor, verifique.");
                flag = 1;
            } else {
                flag = 0;
            }
        }
        if (flag == 0) {
            if (jCheckBox2.isSelected()) {
                complementoSql = "  AND hospedagem_checkout_data IS NOT NULL";
            } else {
                complementoSql = "";
            }
            if (jTextFieldBusca.getText().isEmpty()) {
                sql = "SELECT * FROM tbl_hospedagem WHERE hospedagem_checkin_data between "
                        + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                        + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'" + complementoSql;
                sqlPesquisa = "SELECT c.*, cli.*, h.*, (SELECT (SUM(h.hospedagem_valor_total)) "
                        + "FROM tbl_hospedagem h WHERE h.hospedagem_checkin_data between "
                        + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                        + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' " + complementoSql + ") as SOMA FROM tbl_cabecalho c, "
                        + "tbl_cliente cli INNER JOIN tbl_hospedagem h on cli.cliente_id=h.hospedagem_cliente_id "
                        + "WHERE h.hospedagem_checkin_data between " + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                        + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' " + complementoSql + " ORDER BY h.hospedagem_id DESC;";
                exibirTabela(sql);
                somarValoresTabela("WHERE hospedagem_checkin_data between "
                        + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                        + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'" + complementoSql);
                jButtonLimparBusca.setVisible(true);
                texto = dataNormal.format(jDateChooserDataInico.getDate()) + " ATÉ "
                        + dataNormal.format(jDateChooserDataFinal.getDate());
            } else {
                switch (jLabelVariavalBusca.getText()) {
                    case "ID":
                        variavelLabel = "id";
                        break;
                    case "Hóspede":
                        variavelLabel = "cliente_nome";
                        break;
                    case "Quarto":
                        variavelLabel = "quarto_numero";
                        break;
                    case "Valor Total":
                        variavelLabel = "valor_total";
                        break;
                    case "Usuário":
                        variavelLabel = "usuario_nome";
                        break;
                    default:
                        break;
                }
                texto = dataNormal.format(jDateChooserDataInico.getDate()) + " ATÉ "
                        + dataNormal.format(jDateChooserDataFinal.getDate()) + " - POR " + jTextFieldBusca.getText();
                sql = "SELECT * FROM tbl_hospedagem WHERE "
                        + "hospedagem_" + variavelLabel + " "
                        + "LIKE '%" + jTextFieldBusca.getText() + "%' "
                        + "AND hospedagem_checkin_data between "
                        + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                        + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'" + complementoSql;
                sqlPesquisa = "SELECT c.*, cli.*, h.*, (SELECT (SUM(h.hospedagem_valor_total)) "
                        + "FROM tbl_hospedagem h WHERE "
                        + "hospedagem_" + variavelLabel + " "
                        + "LIKE '%" + jTextFieldBusca.getText() + "%' "
                        + "AND hospedagem_checkin_data between "
                        + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                        + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'" + complementoSql + ") as SOMA FROM tbl_cabecalho c, "
                        + "tbl_cliente cli INNER JOIN tbl_hospedagem h on cli.cliente_id=h.hospedagem_cliente_id "
                        + "WHERE "
                        + "hospedagem_" + variavelLabel + " "
                        + "LIKE '%" + jTextFieldBusca.getText() + "%' "
                        + "AND hospedagem_checkin_data between "
                        + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                        + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'" + complementoSql + " ORDER BY h.hospedagem_id DESC;";
                exibirTabela(sql);
                somarValoresTabela("WHERE "
                        + "hospedagem_" + variavelLabel + " "
                        + "LIKE '%" + jTextFieldBusca.getText() + "%' "
                        + "AND hospedagem_checkin_data between "
                        + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                        + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'" + complementoSql);
                jButtonLimparBusca.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonLimparBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparBuscaActionPerformed
        jDateChooserDataInico.setDate(inicio);
        jDateChooserDataFinal.setDate(fim);
        if (verificaUsuario.getAdm() == 1) {
            texto = dataNormal.format(jDateChooserDataInico.getDate()) + " ATÉ "
                    + dataNormal.format(jDateChooserDataFinal.getDate());
            sql = "SELECT * FROM tbl_hospedagem WHERE hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'"
                    + " ORDER BY hospedagem_id DESC";
            somarValoresTabela("WHERE hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "'"
                    + " ORDER BY hospedagem_id DESC");

            sqlPesquisa = "SELECT c.*, cli.*, h.*, (SELECT (SUM(h.hospedagem_valor_total)) "
                    + "FROM tbl_hospedagem h WHERE h.hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "') as SOMA FROM tbl_cabecalho c, "
                    + "tbl_cliente cli INNER JOIN tbl_hospedagem h on cli.cliente_id=h.hospedagem_cliente_id "
                    + "WHERE h.hospedagem_checkin_data between " + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' ORDER BY h.hospedagem_id DESC;";
        } else {
            texto = dataNormal.format(jDateChooserDataInico.getDate()) + " ATÉ "
                    + dataNormal.format(jDateChooserDataFinal.getDate()) + " - POR " + jTextFieldBusca.getText();
            sql = "SELECT * FROM tbl_hospedagem WHERE hospedagem_usuario_id ="
                    + " " + verificaUsuario.id + " AND hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' "
                    + "ORDER BY hospedagem_id DESC";
            somarValoresTabela("WHERE hospedagem_usuario_id ="
                    + " " + verificaUsuario.id + " AND hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' "
                    + "ORDER BY hospedagem_id DESC");
            sqlPesquisa = "SELECT c.*, cli.*, h.*, (SELECT (SUM(h.hospedagem_valor_total)) "
                    + "FROM tbl_hospedagem h WHERE h.hospedagem_usuario_id ="
                    + " " + verificaUsuario.id + " AND h.hospedagem_checkin_data between "
                    + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "') as SOMA FROM tbl_cabecalho c, "
                    + "tbl_cliente cli INNER JOIN tbl_hospedagem h on cli.cliente_id=h.hospedagem_cliente_id "
                    + "WHERE h.hospedagem_usuario_id ="
                    + " " + verificaUsuario.id + " AND h.hospedagem_checkin_data between " + "'" + dateFormat2.format(jDateChooserDataInico.getDate()) + "' "
                    + "and '" + dateFormat2.format(jDateChooserDataFinal.getDate()) + "' ORDER BY h.hospedagem_id DESC;";

        }
        exibirTabela(sql);

        jTextFieldBusca.setText("");
        jButtonLimparBusca.setVisible(false);
    }//GEN-LAST:event_jButtonLimparBuscaActionPerformed

    private void jTableConsultaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultaMouseReleased
        if (jTableConsulta.getColumnName(jTableConsulta.getSelectedColumn()).equals("CheckIn")
                || jTableConsulta.getColumnName(jTableConsulta.getSelectedColumn()).equals("CheckOut")) {
            jLabelVariavalBuscaInicio.setText(jTableConsulta.getColumnName(jTableConsulta.getSelectedColumn()));
            jLabelVariavalBuscaFim.setText(jTableConsulta.getColumnName(jTableConsulta.getSelectedColumn()));
        } else {
            jLabelVariavalBusca.setText(jTableConsulta.getColumnName(jTableConsulta.getSelectedColumn()));
        }

    }//GEN-LAST:event_jTableConsultaMouseReleased

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        frm_consulta_hospedagem = null;
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerificarActionPerformed
        if (jTableConsulta.getSelectedRow() != -1) {
            Date diaHoje = new Date();
            jButtonCancelar.setVisible(true);
            jButtonImprimir.setVisible(true);
            jButtonCaixa.setVisible(false);
            jTextFieldCodigo.setText(jTableConsulta.getValueAt(jTableConsulta.getSelectedRow(), 0).toString());
            jLabelNumeroQuarto.setText(jTableConsulta.getValueAt(jTableConsulta.getSelectedRow(), 4).toString());
            conexao.executaSQL("SELECT * FROM tbl_hospedagem WHERE hospedagem_id = " + jTextFieldCodigo.getText());
            try {
                if (conexao.rs.next()) {
                    jTextFieldClienteCodigo.setText(String.valueOf(conexao.rs.getInt("hospedagem_cliente_id")));
                    jTextFieldClienteNome.setText(conexao.rs.getString("hospedagem_cliente_nome"));
                    String nomeEmpresa = conexao.rs.getString("hospedagem_cliente_empresa_nome");
                    if (nomeEmpresa == null) {
                        jTextFieldEmpresaCodigo.setVisible(false);
                        jLabelEmpresa.setVisible(false);
                        jTextFieldEmpresaNome.setVisible(false);
                        jLabelDiariasPagas.setVisible(false);
                    } else {
                        jTextFieldEmpresaCodigo.setVisible(true);
                        jTextFieldEmpresaNome.setVisible(true);
                        jLabelDiariasPagas.setVisible(true);
                        jTextFieldEmpresaCodigo.setText(String.valueOf(conexao.rs.getInt("hospedagem_cliente_empresa_id")));
                        jTextFieldEmpresaNome.setText(conexao.rs.getString("hospedagem_cliente_empresa_nome"));
                    }
                    jTextFieldDiariaspagas.setText(String.valueOf(conexao.rs.getInt("hospedagem_diarias_pagas")));
                    jDateChooserCheckIn.setDate(conexao.rs.getDate("hospedagem_checkin_data"));
                    jDateChooserCheckOut.setDate(conexao.rs.getDate("hospedagem_checkout_data"));
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
                    jComboBoxFormaPagamento.setSelectedItem(conexao.rs.getString("hospedagem_forma_pagamento"));
                    DateTime diaCheckIn = new DateTime(conexao.rs.getDate("hospedagem_checkin_data"));
                    DateTime hoje = new DateTime(diaHoje);
                    int dias = Days.daysBetween(diaCheckIn, hoje).getDays() + 1;
                    if (conexao.rs.getDouble("hospedagem_total_diarias") == 0.0) {
                        jTextFieldDiarias.setText(String.valueOf(dias));
                        jLabelValorDiaria.setText(String.valueOf((valorDiaria * Double.valueOf(dias))));
                    } else {
                        jTextFieldDiarias.setText(conexao.rs.getString("hospedagem_quantidade_diarias"));
                        jLabelValorDiaria.setText(String.valueOf(conexao.rs.getDouble("hospedagem_total_diarias")));
                    }
                    jLabelValorDesconto.setText(String.valueOf(conexao.rs.getDouble("hospedagem_valor_desconto")));
                    jLabelValorProdutos.setText("0.0");
                    jLabelValorDiariasPagas.setText(String.valueOf(valorDiaria * Integer.valueOf(jTextFieldDiariaspagas.getText())));
                    jLabelValorTotalProduto.setText("0.0");
                    calculaValorTotalCheckOut(Double.valueOf(jLabelValorProdutos.getText()),
                            Double.valueOf(jLabelValorDesconto.getText()),
                            Double.valueOf(jLabelValorDiaria.getText()));
                    atualizaValorProdutos(Integer.valueOf(jTextFieldCodigo.getText()));
                    atualizaTabelaProdutos("SELECT * FROM tbl_produtos_hospedagem WHERE "
                            + "produtos_hospedagem_hospedagem_id = " + jTextFieldCodigo.getText());
                    atualizaTabelaAcompanhantes("SELECT * FROM tbl_acompanhantes WHERE acompanhantes_hospedagem_id=" + jTextFieldCodigo.getText());
                }
                CardLayout cl = (CardLayout) jPanelGeral.getLayout();
                cl.show(jPanelGeral, "hospedagem");
                conexao.rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(frm_hospedagem.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Para alterar, selecione algum item.");
        }
    }//GEN-LAST:event_jButtonVerificarActionPerformed

    private void jTableAcompanhantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAcompanhantesMouseClicked
        if (jTableAcompanhantes.getSelectedRow() != -1) {
            jTextFieldNomeAcompanhante.setText(jTableAcompanhantes.getValueAt(jTableAcompanhantes.getSelectedRow(), 1).toString());
            jFormattedTextFieldCPF.setText(String.valueOf(jTableAcompanhantes.getValueAt(jTableAcompanhantes.getSelectedRow(), 2)));
        }
    }//GEN-LAST:event_jTableAcompanhantesMouseClicked

    private void jTableAcompanhantesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableAcompanhantesPropertyChange

    }//GEN-LAST:event_jTableAcompanhantesPropertyChange

    private void jTextFieldProdutoCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldProdutoCodigoKeyReleased

    }//GEN-LAST:event_jTextFieldProdutoCodigoKeyReleased

    private void jTextFieldProdutoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProdutoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProdutoNomeActionPerformed

    private void jTableProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProdutosMouseClicked
        if (jTableProdutos.getSelectedRow() != -1) {
            jTextFieldProdutoCodigo.setText(jTableProdutos.getValueAt(jTableProdutos.getSelectedRow(), 1).toString());
            jTextFieldProdutoNome.setText(jTableProdutos.getValueAt(jTableProdutos.getSelectedRow(), 2).toString());
        }
    }//GEN-LAST:event_jTableProdutosMouseClicked

    private void jTableProdutosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableProdutosPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableProdutosPropertyChange

    private void jTextFieldDiariasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDiariasKeyReleased

    }//GEN-LAST:event_jTextFieldDiariasKeyReleased

    private void jLabelValorProdutosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelValorProdutosPropertyChange

    }//GEN-LAST:event_jLabelValorProdutosPropertyChange

    private void jCheckBoxPossuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPossuiActionPerformed
        if (jCheckBoxPossui.isSelected()) {
            jTextFieldPlaca.setEnabled(true);
            jTextFieldModelo.setEnabled(true);
        } else {
            jTextFieldPlaca.setEnabled(false);
            jTextFieldModelo.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxPossuiActionPerformed

    private void jTextFieldClienteNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClienteNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteNomeActionPerformed

    private void jTextFieldClienteCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldClienteCodigoKeyReleased

    }//GEN-LAST:event_jTextFieldClienteCodigoKeyReleased

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        jButtonImprimir.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                posX = e.getX() + 660;
                posY = e.getY() + 80;
                criaPopupMenuComum();
            }
        });

    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jButtonCancelar.setVisible(false);
        jButtonImprimir.setVisible(false);
        jButtonCaixa.setVisible(true);
        CardLayout cl = (CardLayout) jPanelGeral.getLayout();
        cl.show(jPanelGeral, "consulta");
        limpaCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextFieldEmpresaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmpresaNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmpresaNomeActionPerformed

    private void jTextFieldEmpresaCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmpresaCodigoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmpresaCodigoKeyReleased

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jButtonCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCaixaActionPerformed
        try {
            dAORelatorioFechaCaixa.acionaRelatoio(sqlPesquisa, texto);
        } catch (SQLException ex) {
            Logger.getLogger(frm_consulta_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCaixaActionPerformed

    //PAGINAÇÃO
    private TableModel creatModelTable() {
        return new ModeloTabela<ModalHospedagem>() {
            @Override
            public Object getValueAt(ModalHospedagem t, int coluna) {
                switch (coluna) {
                    case 0:
                        return t.getId();
                    case 1:
                        return t.getClienteNome();
                    case 2:
                        return dataNormal.format(t.getCheckinData());
                    case 3:
                        String dataCheckout = "";
                        if (t.getCheckoutData() == null) {
                            dataCheckout = "";
                        } else {
                            dataCheckout = dataNormal.format(t.getCheckoutData());
                        }
                        return dataCheckout;
                    case 4:
                        return t.getQuartoNumero();
                    case 5:
                        return t.getValorTotal();
                    case 6:
                        return t.getUsuarioNome();
                }
                return null;
            }

            @Override
            public String getColumnName(int coluna) {
                switch (coluna) {
                    case 0:
                        return "ID";
                    case 1:
                        return "Hóspede";
                    case 2:
                        return "CheckIn";
                    case 3:
                        return "CheckOut";
                    case 4:
                        return "Quarto";
                    case 5:
                        return "Valor Total";
                    case 6:
                        return "Usuário";
                }
                return null;
            }

            @Override
            public int getColumnCount() {
                return 7;
            }
        };
    }

    private ProvedorDadosPaginacao<ModalHospedagem> criarProvedorDados(String sqlTabela) {
        List<ModalHospedagem> lista = daoU.readGeral(sqlTabela);
        return new ProvedorDadosPaginacao<ModalHospedagem>() {
            @Override
            public int getTotalRowCount() {
                return lista.size();
            }

            @Override
            public List<ModalHospedagem> getRows(int startIndex, int endIndex) {
                return lista.subList(startIndex, endIndex);
            }
        };
    }

    private void exibirTabela(String sqlTabela) {
        jTableConsulta.removeAll();
        jPanelPaginacao.removeAll();
        ProvedorDadosPaginacao<ModalHospedagem> provedorDadosPaginacao = null;
        this.jTableConsulta.setModel(creatModelTable());
        jTableConsulta.getColumnModel().getColumn(0).setMaxWidth(40);
        provedorDadosPaginacao = criarProvedorDados(sqlTabela);
        paginadorTabela = new PaginadorTabela(this.jTableConsulta, provedorDadosPaginacao, new int[]{5, 10, 20, 50, 75, 100}, 10);
        paginadorTabela.criarListaLinhasPermitidas(this.jPanelPaginacao);

        comboBoxLinhasPermitidas = paginadorTabela.getComboBoxLinhasPermitidas();
        comboBoxLinhasPermitidas.addActionListener((ActionEvent e) -> {
            Object evt = e.getSource();

            if (evt.equals(comboBoxLinhasPermitidas)) {
                paginadorTabela.eventoComboBox(comboBoxLinhasPermitidas);
            }
        });
        jTableConsulta.getModel().addTableModelListener((TableModelEvent e) -> {
            paginadorTabela.atualizarBotoesPaginacao();
        });
        comboBoxLinhasPermitidas.setSelectedItem(Integer.parseInt("10"));
    }

    private void calculaValorTotalCheckOut(double valorProduto, double valorDesconto, double valorDiaria) {
        valorTotalCheckOut = (valorProduto + valorDiaria) - valorDesconto;
        valorMonetario.formatarValor(String.valueOf(valorTotalCheckOut).replaceAll("\\.", ","));
        jLabelValorTotal.setText(valorMonetario.valorMonetario);
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

    private void limpaCampos() {
        jTableProdutos.removeAll();
        jTableAcompanhantes.removeAll();
        LimpaCampos.limpaCampos(jPanelCheckIn.getComponents());
        LimpaCampos.limpaCampos(jPanelVeiculo.getComponents());
        LimpaCampos.limpaCampos(jPanelCheckOut.getComponents());
        LimpaCampos.limpaCampos(jPanelAcompanhantes.getComponents());
        LimpaCampos.limpaCampos(jPanelProdutos.getComponents());
    }

    private void somarValoresTabela(String sql) {
        conexao.executaSQL("SELECT SUM(hospedagem_valor_total) FROM tbl_hospedagem " + sql);
        try {
            if (conexao.rs.next()) {
                String sv = String.valueOf(conexao.rs.getDouble("SUM(hospedagem_valor_total)"));
                String vsf = sv.replace("R$", "").replace(" ", "").replace(",", ".");
                Double valor = new Double(vsf);

                NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                jLabelValorTotalBusca.setText(nf.format(valor));
            }
            conexao.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(frm_consulta_hospedagem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void criaPopupMenuComum() {
        popupMenu.removeAll();
//CRIANDO A OPÇÃO DE ADICIONAR AGENDA
        //Criando Item Menu
        menuItem = new JMenuItem(
                "Reemitr Contrato", null
        );
        //Aplicando Drescrição
        menuItem.getAccessibleContext().setAccessibleDescription("Contrato de Hospedagem");
        //Adicionando um Action Listener
        menuItem.addActionListener((ActionEvent e) -> {
            DAORelatorioContratoHospedagem relatorioContratoHospedagem = new DAORelatorioContratoHospedagem();
            try {
                if (jTextFieldEmpresaNome.isVisible()) {
                    relatorioContratoHospedagem.acionaRelatoioEmpresa(jTextFieldCodigo.getText());
                } else {
                    relatorioContratoHospedagem.acionaRelatoio(jTextFieldCodigo.getText());
                }

            } catch (SQLException ex) {

            }
        });
        popupMenu.add(menuItem);
        if (jDateChooserCheckOut.getDate() != null) {
            menuItem = new JMenuItem(
                    "Reemitir Recibo", null
            );
            //Aplicando Drescrição
            menuItem.getAccessibleContext().setAccessibleDescription("Agenda de Tarefa");
            //Adicionando um Action Listener
            menuItem.addActionListener((ActionEvent e) -> {
                InputStream streamEmpresa = getClass().getResourceAsStream("/relatorios/processo/reciboHospedagemEmpresa.jasper");
                InputStream stream = getClass().getResourceAsStream("/relatorios/processo/reciboHospedagem.jasper");
                DAORelatorioResumoHospedagem relatorioResumoHospedagem = new DAORelatorioResumoHospedagem();
                try {
                    if (jTextFieldEmpresaNome.isVisible()) {
                    relatorioResumoHospedagem.acionaRelatoioEmpresa(jTextFieldCodigo.getText(), streamEmpresa);
                } else {
                    relatorioResumoHospedagem.acionaRelatoio(jTextFieldCodigo.getText(), stream);
                }
                    
                } catch (SQLException ex) {

                }
            });
            //Adicionando o Item menu no PopupMenu    
            popupMenu.add(menuItem);
        }

        posicaoMouse();
    }

    public void posicaoMouse() {
        popupMenu.show(this, posX, posY + 35);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCaixa;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonLimparBusca;
    private javax.swing.JButton jButtonVerificar;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBoxPossui;
    private javax.swing.JComboBox<String> jComboBoxFormaPagamento;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooserCheckIn;
    private com.toedter.calendar.JDateChooser jDateChooserCheckOut;
    private com.toedter.calendar.JDateChooser jDateChooserDataFinal;
    private com.toedter.calendar.JDateChooser jDateChooserDataInico;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelBusca;
    private javax.swing.JLabel jLabelCliente;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelDataFinal;
    private javax.swing.JLabel jLabelDataInicio;
    private javax.swing.JLabel jLabelDiarias;
    private javax.swing.JLabel jLabelDiariasPagas;
    private javax.swing.JLabel jLabelEmpresa;
    private javax.swing.JLabel jLabelModelo;
    private javax.swing.JLabel jLabelNomeAcompanhante;
    private javax.swing.JLabel jLabelNomeAcompanhante1;
    private javax.swing.JLabel jLabelNumeroQuarto;
    private javax.swing.JLabel jLabelPlaca;
    private javax.swing.JLabel jLabelQuarto;
    private javax.swing.JLabel jLabelResumoDesconto;
    private javax.swing.JLabel jLabelResumoDiaria;
    private javax.swing.JLabel jLabelResumoDiariasPagas;
    private javax.swing.JLabel jLabelResumoProdutos;
    private javax.swing.JLabel jLabelValorDesconto;
    private javax.swing.JLabel jLabelValorDiaria;
    private javax.swing.JLabel jLabelValorDiariaAcompanhantes;
    private javax.swing.JLabel jLabelValorDiariasPagas;
    private javax.swing.JLabel jLabelValorParcial;
    private javax.swing.JLabel jLabelValorProdutos;
    private javax.swing.JLabel jLabelValorTotal;
    private javax.swing.JLabel jLabelValorTotalBusca;
    private javax.swing.JLabel jLabelValorTotalProduto;
    private javax.swing.JLabel jLabelValorTotalTexto;
    private javax.swing.JLabel jLabelVariavalBusca;
    private javax.swing.JLabel jLabelVariavalBuscaFim;
    private javax.swing.JLabel jLabelVariavalBuscaInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAcompanhantes;
    private javax.swing.JPanel jPanelCheckIn;
    private javax.swing.JPanel jPanelCheckOut;
    private javax.swing.JPanel jPanelConsulta;
    private javax.swing.JPanel jPanelGeral;
    private javax.swing.JPanel jPanelGeralHospedagem;
    private javax.swing.JPanel jPanelHospedagem;
    private javax.swing.JPanel jPanelPaginacao;
    private javax.swing.JPanel jPanelProdutos;
    private javax.swing.JPanel jPanelVeiculo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPaneHospedagem;
    private javax.swing.JTable jTableAcompanhantes;
    private javax.swing.JTable jTableConsulta;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JTextField jTextFieldBusca;
    private javax.swing.JTextField jTextFieldClienteCodigo;
    private javax.swing.JTextField jTextFieldClienteNome;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldDiarias;
    private javax.swing.JTextField jTextFieldDiariaspagas;
    private javax.swing.JTextField jTextFieldEmpresaCodigo;
    private javax.swing.JTextField jTextFieldEmpresaNome;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldNomeAcompanhante;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldProdutoCodigo;
    private javax.swing.JTextField jTextFieldProdutoNome;
    // End of variables declaration//GEN-END:variables

}
