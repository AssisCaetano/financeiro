package com.controle.financeiro.dto;

public record ContasAPagarDto(String dataDoEmprestimo, String dataDeVencimento, double capitalInicial, double taxaDeJuros, double valorDoJuros, double saldoDevedor) {
    
}
