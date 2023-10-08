/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.EditoresTable.Paginacao;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

/**
 *
 * @author Tatty
 * @param <T>
 */
public class PaginadorTabela<T> {

    private JTable table;
    private ProvedorDadosPaginacao<T> provedorDados;
    private int[] arrayLinhasPermididas;
    private int linhaPermitida;
    private ModeloTabela<T> modeloTabela;
    private int paginaAtual = 1;

    private JComboBox<Integer> comboBoxLinhasPermitidas;
    private JPanel painelPaginacaoBotoes;
    private int limiteBotoesPaginadores = 7;

    public PaginadorTabela(JTable table, ProvedorDadosPaginacao<T> provedorDados,
            int[] arrayLinhasPermididas, int linhaPermitida) {
        this.table = table;
        this.provedorDados = provedorDados;
        this.arrayLinhasPermididas = arrayLinhasPermididas;
        this.linhaPermitida = linhaPermitida;
        init();
    }

    private void init() {
        inicializarModeloTabela();
        paginar();
    }

    private void inicializarModeloTabela() {
        try {
            this.modeloTabela = (ModeloTabela<T>) this.table.getModel();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void criarListaLinhasPermitidas(JPanel painelPaginador) {
        painelPaginacaoBotoes = new JPanel(new GridLayout(1, limiteBotoesPaginadores, 10, 10));
        painelPaginador.add(painelPaginacaoBotoes);

        if (arrayLinhasPermididas != null) {
            Integer array[] = new Integer[arrayLinhasPermididas.length];

            for (int i = 0; i < arrayLinhasPermididas.length; i++) {
                array[i] = arrayLinhasPermididas[i];
            }

            comboBoxLinhasPermitidas = new JComboBox<>(array);

            painelPaginador.add(Box.createHorizontalStrut(15));
            painelPaginador.add(new JLabel("Número de Linhas "));
            painelPaginador.add(comboBoxLinhasPermitidas);

        }
    }

    public void eventoComboBox(JComboBox<Integer> pageComboBox) {
        int linhaInicialPagina = ((paginaAtual - 1) * linhaPermitida) + 1;
        linhaPermitida = (Integer) pageComboBox.getSelectedItem();
        paginaAtual = ((linhaInicialPagina - 1) / linhaInicialPagina) + 1;
        paginar();
    }

    public void atualizarBotoesPaginacao() {
        painelPaginacaoBotoes.removeAll();
        int totalLinhas = provedorDados.getTotalRowCount();
        int paginas = (int) Math.ceil((double) totalLinhas / linhaPermitida);
        if (paginas > limiteBotoesPaginadores) {
            agregarBotoesPaginacao(painelPaginacaoBotoes, 1);
            if (paginaAtual <= (limiteBotoesPaginadores + 1) / 2) {
                agregarRangoBotoesPaginacao(painelPaginacaoBotoes, 2, limiteBotoesPaginadores - 2);
                painelPaginacaoBotoes.add(criarElipses());
                agregarBotoesPaginacao(painelPaginacaoBotoes, paginas);
            } else if (paginaAtual > (paginas - ((limiteBotoesPaginadores + 1) / 2))) {
                painelPaginacaoBotoes.add(criarElipses());
                agregarRangoBotoesPaginacao(painelPaginacaoBotoes, paginas - limiteBotoesPaginadores + 3, paginas);
            } else {
                painelPaginacaoBotoes.add(criarElipses());
                int inicio = paginaAtual - (limiteBotoesPaginadores - 4) / 2;
                int finalizar = inicio + limiteBotoesPaginadores - 5;
                agregarRangoBotoesPaginacao(painelPaginacaoBotoes, inicio, finalizar);
                painelPaginacaoBotoes.add(criarElipses());
                agregarBotoesPaginacao(painelPaginacaoBotoes, paginas);
            }
        } else {
            agregarRangoBotoesPaginacao(painelPaginacaoBotoes, 1, paginas);
        }

        painelPaginacaoBotoes.getParent().validate();
        painelPaginacaoBotoes.getParent().repaint();
    }

    private JLabel criarElipses() {
        return new JLabel("...", SwingConstants.CENTER);
    }

    private void agregarRangoBotoesPaginacao(JPanel panel, int inicio, int finalizar) {
        int init = inicio;
        for (inicio = init; inicio <= finalizar; inicio++) {
            agregarBotoesPaginacao(panel, inicio);
        }
    }

    private void agregarBotoesPaginacao(JPanel panel, int numeroPagina) {

        JToggleButton toggleButton = new JToggleButton(Integer.toString(numeroPagina));
        toggleButton.setMargin(new Insets(1, 3, 1, 3));
        panel.add(toggleButton);
        if (numeroPagina == paginaAtual) {
            toggleButton.setSelected(true);
        }

        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaAtual = Integer.parseInt(e.getActionCommand());
                paginar();
            }
        });

    }

    private void paginar() {
        int inicio = (paginaAtual - 1) * linhaPermitida;
        int finalizar = inicio + linhaPermitida;

        if (finalizar > provedorDados.getTotalRowCount()) {
            finalizar = provedorDados.getTotalRowCount();
        }

        List<T> lista = provedorDados.getRows(inicio, finalizar);
        modeloTabela.setLista(lista);
        modeloTabela.fireTableDataChanged();
    }

    public JComboBox<Integer> getComboBoxLinhasPermitidas() {
        return comboBoxLinhasPermitidas;
    }

    public void setComboBoxLinhasPermitidas(JComboBox<Integer> comboBoxLinhasPermitidas) {
        this.comboBoxLinhasPermitidas = comboBoxLinhasPermitidas;
    }

}
