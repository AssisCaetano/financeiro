package com.controle.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NovoEmprestimoDto(LocalDate dataNovoCredito, BigDecimal novoCredito) {
    
}
