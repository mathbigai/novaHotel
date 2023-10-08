/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.cadastro;

/**
 *
 * @author Matheus
 */
public class ModalQuartoAcompanhantesValores {

    private int id;
    private int codigoQuarto;
    private int quantidade;
    private Double valorDiaria;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the codigoQuarto
     */
    public int getCodigoQuarto() {
        return codigoQuarto;
    }

    /**
     * @param codigoQuarto the codigoQuarto to set
     */
    public void setCodigoQuarto(int codigoQuarto) {
        this.codigoQuarto = codigoQuarto;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valorDiaria
     */
    public Double getValorDiaria() {
        return valorDiaria;
    }

    /**
     * @param valorDiaria the valorDiaria to set
     */
    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

}
