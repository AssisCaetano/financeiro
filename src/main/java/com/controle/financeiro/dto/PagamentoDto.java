package com.controle.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PagamentoDto(BigDecimal valorDoPagamento, LocalDate dataPagamento) {
    
}
