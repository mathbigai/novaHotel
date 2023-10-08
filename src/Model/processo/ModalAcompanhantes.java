/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.processo;

import java.util.Date;

/**
 *
 * @author Matheus
 */
public class ModalAcompanhantes {
    private int id;
    private String nome;
    private String cpf;
    private int hospedagemId;
    private Date data;
    
    

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the hospedagemId
     */
    public int getHospedagemId() {
        return hospedagemId;
    }

    /**
     * @param hospedagemId the hospedagemId to set
     */
    public void setHospedagemId(int hospedagemId) {
        this.hospedagemId = hospedagemId;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }
    
    
}
