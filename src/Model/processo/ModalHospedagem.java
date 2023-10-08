/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.processo;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author Matheus
 */
public class ModalHospedagem {

    private int id;
    private int clienteId;
    private String clienteNome;
    private int quartoId;
    private String quartoNumero;
    private int usuarioId;
    private String usuarioNome;
    private String status;
    private Date checkinData;
    private Time checkinHora;
    private Date checkoutData;
    private Time checkoutHora;
    private Double valorTotal;
    private Double valorHospedagem;
    private String formaPagamento;
    private String placa;
    private String modelo;
    private Double valorTotalDiaria;
    private Double valorDesconto;
    private Double quantidadeDiaria;
    private int clienteEmpresaId;
    private String clienteEmpresaNome;
    private int diariasPagas;

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
     * @return the clienteId
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the clienteNome
     */
    public String getClienteNome() {
        return clienteNome;
    }

    /**
     * @param clienteNome the clienteNome to set
     */
    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    /**
     * @return the quartoId
     */
    public int getQuartoId() {
        return quartoId;
    }

    /**
     * @param quartoId the quartoId to set
     */
    public void setQuartoId(int quartoId) {
        this.quartoId = quartoId;
    }

    /**
     * @return the quartoNumero
     */
    public String getQuartoNumero() {
        return quartoNumero;
    }

    /**
     * @param quartoNumero the quartoNumero to set
     */
    public void setQuartoNumero(String quartoNumero) {
        this.quartoNumero = quartoNumero;
    }

    /**
     * @return the usuarioId
     */
    public int getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return the usuarioNome
     */
    public String getUsuarioNome() {
        return usuarioNome;
    }

    /**
     * @param usuarioNome the usuarioNome to set
     */
    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the checkinData
     */
    public Date getCheckinData() {
        return checkinData;
    }

    /**
     * @param checkinData the checkinData to set
     */
    public void setCheckinData(Date checkinData) {
        this.checkinData = checkinData;
    }

    /**
     * @return the checkinHora
     */
    public Time getCheckinHora() {
        return checkinHora;
    }

    /**
     * @param checkinHora the checkinHora to set
     */
    public void setCheckinHora(Time checkinHora) {
        this.checkinHora = checkinHora;
    }

    /**
     * @return the checkoutData
     */
    public Date getCheckoutData() {
        return checkoutData;
    }

    /**
     * @param checkoutData the checkoutData to set
     */
    public void setCheckoutData(Date checkoutData) {
        this.checkoutData = checkoutData;
    }

    /**
     * @return the checkoutHora
     */
    public Time getCheckoutHora() {
        return checkoutHora;
    }

    /**
     * @param checkoutHora the checkoutHora to set
     */
    public void setCheckoutHora(Time checkoutHora) {
        this.checkoutHora = checkoutHora;
    }

    /**
     * @return the valorTotal
     */
    public Double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the formaPagamento
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * @return the valorHospedagem
     */
    public Double getValorHospedagem() {
        return valorHospedagem;
    }

    /**
     * @param valorHospedagem the valorHospedagem to set
     */
    public void setValorHospedagem(Double valorHospedagem) {
        this.valorHospedagem = valorHospedagem;
    }

    /**
     * @return the valorTotalDiaria
     */
    public Double getValorTotalDiaria() {
        return valorTotalDiaria;
    }

    /**
     * @param valorTotalDiaria the valorTotalDiaria to set
     */
    public void setValorTotalDiaria(Double valorTotalDiaria) {
        this.valorTotalDiaria = valorTotalDiaria;
    }

    /**
     * @return the valorDesconto
     */
    public Double getValorDesconto() {
        return valorDesconto;
    }

    /**
     * @param valorDesconto the valorDesconto to set
     */
    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    /**
     * @return the quantidadeDiaria
     */
    public Double getQuantidadeDiaria() {
        return quantidadeDiaria;
    }

    /**
     * @param quantidadeDiaria the quantidadeDiaria to set
     */
    public void setQuantidadeDiaria(Double quantidadeDiaria) {
        this.quantidadeDiaria = quantidadeDiaria;
    }

    /**
     * @return the clienteEmpresaId
     */
    public int getClienteEmpresaId() {
        return clienteEmpresaId;
    }

    /**
     * @param clienteEmpresaId the clienteEmpresaId to set
     */
    public void setClienteEmpresaId(int clienteEmpresaId) {
        this.clienteEmpresaId = clienteEmpresaId;
    }

    /**
     * @return the clienteEmpresaNome
     */
    public String getClienteEmpresaNome() {
        return clienteEmpresaNome;
    }

    /**
     * @param clienteEmpresaNome the clienteEmpresaNome to set
     */
    public void setClienteEmpresaNome(String clienteEmpresaNome) {
        this.clienteEmpresaNome = clienteEmpresaNome;
    }

    /**
     * @return the diariasPagas
     */
    public int getDiariasPagas() {
        return diariasPagas;
    }

    /**
     * @param diariasPagas the diariasPagas to set
     */
    public void setDiariasPagas(int diariasPagas) {
        this.diariasPagas = diariasPagas;
    }

}
