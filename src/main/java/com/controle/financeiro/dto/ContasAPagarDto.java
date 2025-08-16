package com.controle.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContasAPagarDto(LocalDate dataAtual, LocalDate dataDoEmprestimo, LocalDate dataDeVencimento, BigDecimal capitalInicial, BigDecimal taxaDeJuros, BigDecimal valorDoJuros, BigDecimal saldoDevedor){
    
}
