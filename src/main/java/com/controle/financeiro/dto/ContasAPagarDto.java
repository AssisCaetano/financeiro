package com.controle.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContasAPagarDto(
    LocalDate dataAtual,
    LocalDate dataDoEmprestimo,
    LocalDate dataDeVencimento,
    @NotNull(message="O campo Capital Inicial não pode ser vazio") BigDecimal capitalInicial,
    @NotNull(message="O campo Taxa de Juros não pode ser vazio") BigDecimal taxaDeJuros,
    BigDecimal valorDoJuros,
    BigDecimal saldoDevedor
    ){
    
}
