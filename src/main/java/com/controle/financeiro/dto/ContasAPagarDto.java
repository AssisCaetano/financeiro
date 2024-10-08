package com.controle.financeiro.dto;

public record ContasAPagarDto(double capitalInicial, double juros, double valorDoJuros, double valorDoJurosPago, double adiantamentoDeCapital, double saldoDevedor) {
    
}
