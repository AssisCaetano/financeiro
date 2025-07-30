package com.controle.financeiro.domain.service.strategy.impl;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.service.strategy.PagamentoStrategy;

public class ControlePagamentoStrategy {
    
    public void processarPagamento(ContasAPagar conta, Pagamento pg){

        PagamentoStrategy pgStrategy;

        if(pg.getDataPagamento().isAfter(conta.getDataDeVencimento())){
            pgStrategy = new PagamentoAtrasado();
        }else if(pg.getDataPagamento().isEqual(conta.getDataDeVencimento())){
            pgStrategy = new PagamentoNoPraso();
        }else{
            throw new IllegalArgumentException("Forma de pagamento invalida. ");
        }
        
        pgStrategy.resultadoPagamento(conta, pg);
    }
}
