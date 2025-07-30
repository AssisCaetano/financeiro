package com.controle.financeiro.domain.service.strategy.impl;

import java.math.BigDecimal;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.NovoEmprestimo;
import com.controle.financeiro.domain.service.strategy.CreditoStrategy;

public class ControleCreditoStrategy {
    
    public void processarCredito(NovoEmprestimo credito, ContasAPagar contas){

        CreditoStrategy creditoStrategy;

            if(contas.getSaldoDevedor().compareTo(BigDecimal.ZERO) <= 0){
                creditoStrategy = new ValidarCredito();
            }else if(contas.getSaldoDevedor().compareTo(BigDecimal.ZERO) > 0 ){
                creditoStrategy = new ValidarCredito();
            }else{
                throw new IllegalArgumentException("Opção de credito ivalido. ");
            }
            creditoStrategy.lancamentoDeCredito(credito, contas);
    }
}
