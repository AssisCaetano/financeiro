package com.controle.financeiro.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "caderneta")
public class ContasAPagar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConta;
    private double capitalInicial;
    private double juros;
    private double valorDoJuros;
    private double valorDoJurosPago;
    private double adiantamentoDeCapital;
    private double SaldoDevedor;

    public ContasAPagar() {
    }

    public ContasAPagar(UUID idConta, double capitalInicial, double juros, double valorDoJuros, double valorDoJurosPago,
            double adiantamentoDeCapital, double saldoDevedor) {
        this.idConta = idConta;
        this.capitalInicial = capitalInicial;
        this.juros = juros;
        this.valorDoJuros = valorDoJuros;
        this.valorDoJurosPago = valorDoJurosPago;
        this.adiantamentoDeCapital = adiantamentoDeCapital;
        this.SaldoDevedor = saldoDevedor;
    }

    public UUID getIdConta() {
        return idConta;
    }

    public void setIdConta(UUID idConta) {
        this.idConta = idConta;
    }

    public double getCapitalInicial() {
        return capitalInicial;
    }

    public void setCapitalInicial(double capitalInicial) {
        this.capitalInicial = capitalInicial;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getValorDoJuros() {
        return valorDoJuros;
    }

    public void setValorDoJuros(double valorDoJuros) {
        this.valorDoJuros = valorDoJuros;
    }

    public double getValorDoJurosPago() {
        return valorDoJurosPago;
    }

    public void setValorDoJurosPago(double valorDoJurosPago) {
        this.valorDoJurosPago = valorDoJurosPago;
    }

    public double getAdiantamentoDeCapital() {
        return adiantamentoDeCapital;
    }

    public void setAdiantamentoDeCapital(double adiantamentoDeCapital) {
        this.adiantamentoDeCapital = adiantamentoDeCapital;
    }

    public double getSaldoDevedor() {
        return SaldoDevedor;
    }

    public void setSaldoDevedor(double saldoDevedor) {
        SaldoDevedor = saldoDevedor;
    }
    
}
