package com.controle.financeiro.dto;

import java.time.LocalDate;

public record PagamentoDto(double valorDoPagamento, LocalDate dataPagamento) {
    
}
