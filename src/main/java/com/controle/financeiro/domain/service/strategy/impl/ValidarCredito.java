package com.controle.financeiro.domain.service.strategy.impl;

import java.math.BigDecimal;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.NovoEmprestimo;
import com.controle.financeiro.domain.service.strategy.CreditoStrategy;

public class ValidarCredito implements CreditoStrategy {

    @Override
    public void lancamentoDeCredito(NovoEmprestimo credito, ContasAPagar contas) {
        
        if(contas.getSaldoDevedor().compareTo(BigDecimal.ZERO) <= 0){

            throw new RuntimeException("Conta encerrada, Por gentileza cadastrar uma nova Conta! ");
            
        }else if(contas.getSaldoDevedor().compareTo(BigDecimal.ZERO) >0 ){

            BigDecimal adicionarCredito = contas.getSaldoDevedor().add(credito.getNovoCredito());
            contas.setSaldoDevedor(adicionarCredito);

            BigDecimal saldoAtualizado = contas.getSaldoDevedor().multiply(BigDecimal.ONE.add(contas.getTaxaDeJuros()));
            contas.setSaldoDevedor(saldoAtualizado);
            
        }
    }
}
