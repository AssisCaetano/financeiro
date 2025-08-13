package com.controle.financeiro.domain.service.strategy.impl;

import java.math.BigDecimal;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.NovoEmprestimo;
import com.controle.financeiro.domain.service.strategy.CreditoStrategy;

public class ValidarCredito implements CreditoStrategy {

    @Override
    public void lancamentoDeCredito(NovoEmprestimo credito, ContasAPagar contas) {
        
    	/*Se o capital inicial for igual a zero, não é pra permitir que seja adicionado valor*/
        if(contas.getCapitalInicial().compareTo(BigDecimal.ZERO) == 0){

            throw new RuntimeException("Conta encerrada, Por gentileza cadastrar uma nova Conta! ");
            
        }
        /*Se saldo devedor for maior que zero ai sim pode ser adicionado um novo valor*/
        else if(contas.getCapitalInicial().compareTo(BigDecimal.ZERO) >0 ){

        	//ESTOU SETANDO UM NOVO VALOR EM CAPITAL INICIAL
            BigDecimal adicionandoCredito = contas.getCapitalInicial().add(credito.getNovoCredito());
            contas.setCapitalInicial(adicionandoCredito);
            
            //ESTOU REALIZANDO A ATUALIZAÇÃO DO VALOR DO JUROS EM CIMA DO CAPITAL INICIAL
            BigDecimal atualizandoValorDoJuros = contas.getCapitalInicial().multiply(contas.getTaxaDeJuros());
            contas.setValorDoJuros(atualizandoValorDoJuros);
            
            //ESTOU ATUALIZANDO SALDO DEVEDOR SAMANDO O CAITAL INICIAL COM O VALOR DO JUROS
            BigDecimal atualizandoOSadoDevedor = contas.getCapitalInicial().add(atualizandoValorDoJuros);
            contas.setSaldoDevedor(atualizandoOSadoDevedor);
        }
    }
}