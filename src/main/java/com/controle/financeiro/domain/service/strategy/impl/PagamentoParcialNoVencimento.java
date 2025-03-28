package com.controle.financeiro.domain.service.strategy.impl;

import java.math.BigDecimal;
import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.service.strategy.PagamentoStrategy;

public class PagamentoParcialNoVencimento implements PagamentoStrategy{

    @Override
    public void resultadoPagamento(ContasAPagar conta, Pagamento pg) {

       // Se o pagamento for maior que os juros, abate do saldo
        if (pg.getValorDoPagamento().compareTo(conta.getValorDoJuros()) > 0) {
            BigDecimal novoSaldo = conta.getSaldoDevedor().subtract(pg.getValorDoPagamento());
            conta.setSaldoDevedor(novoSaldo);
        }else if(pg.getValorDoPagamento().equals(conta.getValorDoJuros())){
            conta.getSaldoDevedor();
        }
    }
    
}
