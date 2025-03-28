package com.controle.financeiro.domain.service.strategy.impl;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.service.strategy.PagamentoStrategy;

public class ControleStrategy {
    
    public void processarPagamento(ContasAPagar conta, Pagamento pg){

        PagamentoStrategy pgStrategy;

        if(pg.getDataPagamento().isAfter(conta.getDataDeVencimento())){
            pgStrategy = new PagamentoComJuros();
        }else if(pg.getDataPagamento().equals(conta.getDataDeVencimento())){
            pgStrategy = new PagamentoParcialNoVencimento();
        }else if(pg.getValorDoPagamento().equals(conta.getValorDoJuros())){
            pgStrategy = new PagamentoSemJuros();
        }else{
            throw new IllegalArgumentException("Forma de pagamento invalida. ");
        }

        // if(pg.getDataPagamento().isAfter(conta.getDataDeVencimento())){
        //     pgStrategy = new PagamentoComJuros();
        // }else if(pg.getValorDoPagamento().compareTo(conta.getValorDoJuros())>0){
        //     pgStrategy = new PagamentoParcialNoVencimento();
        // }else if(pg.getValorDoPagamento().equals(conta.getValorDoJuros())){
        //     pgStrategy = new PagamentoSemJuros();
        // }else{
        //     throw new IllegalArgumentException("Forma de pagamento invalida. ");
        // }
        
        pgStrategy.resultadoPagamento(conta, pg);
    }
}
