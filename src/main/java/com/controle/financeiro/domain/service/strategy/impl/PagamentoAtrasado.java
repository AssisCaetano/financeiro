package com.controle.financeiro.domain.service.strategy.impl;

import java.math.BigDecimal;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.service.strategy.PagamentoStrategy;

public class PagamentoAtrasado implements PagamentoStrategy{

    @Override
    public void resultadoPagamento(ContasAPagar conta, Pagamento pg) {
        
        BigDecimal juros = conta.getSaldoDevedor().multiply(conta.getTaxaDeJuros());
        conta.setValorDoJuros(juros);
        
        BigDecimal novoSaldo = conta.getSaldoDevedor().add(conta.getValorDoJuros());
        conta.setSaldoDevedor(novoSaldo);

        if (pg.getValorDoPagamento().compareTo(conta.getValorDoJuros()) > 0) {
            novoSaldo = conta.getSaldoDevedor().subtract(pg.getValorDoPagamento());
            conta.setSaldoDevedor(novoSaldo);
        }else if(pg.getValorDoPagamento().equals(conta.getValorDoJuros())){
            conta.getSaldoDevedor();
        }else if(pg.getValorDoPagamento().compareTo(conta.getValorDoJuros()) < 0){
            throw new RuntimeException("Valor informado no Pagamento menor que o Juros");
        }else{
            if(pg.getValorDoPagamento().equals(conta.getSaldoDevedor())){
                conta.setSaldoDevedor(BigDecimal.ZERO);
            }
        }
    }
    
}
