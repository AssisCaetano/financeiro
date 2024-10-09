package com.controle.financeiro.dto;

public record ContasAPagarDto(String dataDoEmprestimo, String dataDeVencimento, double capitalInicial, double juros, double valorDoJuros, double saldoDevedor) {
    
}
