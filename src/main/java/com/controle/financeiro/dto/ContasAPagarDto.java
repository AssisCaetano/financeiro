package com.controle.financeiro.dto;

import java.time.LocalDate;

public record ContasAPagarDto(LocalDate dataDoEmprestimo, LocalDate dataDeVencimento, double capitalInicial, double taxaDeJuros, double valorDoJuros, double saldoDevedor) {
    
}
