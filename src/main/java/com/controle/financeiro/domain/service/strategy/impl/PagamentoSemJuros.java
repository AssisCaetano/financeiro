package com.controle.financeiro.domain.service.strategy.impl;

import java.math.BigDecimal;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.service.strategy.PagamentoStrategy;

public class PagamentoSemJuros implements PagamentoStrategy{

    @Override
    public void resultadoPagamento(ContasAPagar conta, Pagamento pg) {
        // Pagamento antes do vencimento: abate direto do saldo sem juros
        BigDecimal novoSaldo = conta.getSaldoDevedor().subtract(pg.getValorDoPagamento());
        conta.setSaldoDevedor(novoSaldo);
        
    }
    
}
