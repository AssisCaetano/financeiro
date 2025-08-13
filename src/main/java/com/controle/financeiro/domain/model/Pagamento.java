package com.controle.financeiro.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "opening" ,name = "tb_pagamento")
public class Pagamento{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPagamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataDeVencimento;
    private BigDecimal valorDivida;
    private BigDecimal valorDoPagamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy" )
    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "idContas", nullable = true)
    private ContasAPagar contasAPagar;

    public Pagamento() {
    }

    public Pagamento(UUID idPagamento, LocalDate dataDeVencimento, BigDecimal valorDivida, BigDecimal valorDoPagamento,
            LocalDate dataPagamento, ContasAPagar contasAPagar) {
        this.idPagamento = idPagamento;
        this.dataDeVencimento = dataDeVencimento;
        this.valorDivida = valorDivida;
        this.valorDoPagamento = valorDoPagamento;
        this.dataPagamento = dataPagamento;
        this.contasAPagar = contasAPagar;
    }

    public UUID getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(UUID idPagamento) {
        this.idPagamento = idPagamento;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public BigDecimal getValorDivida() {
        return valorDivida;
    }

    public void setValorDivida(BigDecimal valorDivida) {
        this.valorDivida = valorDivida;
    }

    public BigDecimal getValorDoPagamento() {
        return valorDoPagamento;
    }

    public void setValorDoPagamento(BigDecimal valorDoPagamento) {
        this.valorDoPagamento = valorDoPagamento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public ContasAPagar getContasAPagar() {
        return contasAPagar;
    }

    public void setContasAPagar(ContasAPagar contasAPagar) {
        this.contasAPagar = contasAPagar;
    }

    
}
