package com.controle.financeiro.domain.service.strategy.impl;

import java.math.BigDecimal;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.service.strategy.PagamentoStrategy;

public class PagamentoNoPraso implements PagamentoStrategy{

    @Override
    public void resultadoPagamento(ContasAPagar conta, Pagamento pg) {
    	
    	
    	    // 1. Verificar se o pagamento é igual ou maior que o saldo devedor (PAGAMENTO COMPLETO)
    	    if (pg.getValorDoPagamento().compareTo(conta.getSaldoDevedor()) == 0) {
    	        conta.setCapitalInicial(BigDecimal.ZERO);
    	        conta.setSaldoDevedor(BigDecimal.ZERO);
    	        conta.setValorDoJuros(BigDecimal.ZERO);
    	        

    	    // 2. Se o pagamento for menor que o saldo devedor, mas maior que os juros (PAGAMENTO PARCIAL COBRINDO JUROS E PARTE DO CAPITAL)
    	    } else if (pg.getValorDoPagamento().compareTo(conta.getValorDoJuros()) > 0) {
    	    	
    	        // Calcula o valor que sobra do pagamento após cobrir os juros
    	        BigDecimal valorRestanteParaAbaterCapital = pg.getValorDoPagamento().subtract(conta.getValorDoJuros());

    	        // Abate esse valor do capital inicial
    	        BigDecimal novoCapitalInicial = conta.getCapitalInicial().subtract(valorRestanteParaAbaterCapital);
    	        conta.setCapitalInicial(novoCapitalInicial);

    	        // Recalcula os juros com base no novo capital inicial
    	        BigDecimal novosJuros = novoCapitalInicial.multiply(conta.getTaxaDeJuros());
    	        conta.setValorDoJuros(novosJuros);

    	        // Recalcula o saldo devedor
    	        BigDecimal novoSaldoDevedor = novoCapitalInicial.add(novosJuros);
    	        conta.setSaldoDevedor(novoSaldoDevedor);

    	    // 3. Se o pagamento for igual ao valor dos juros (PAGAMENTO APENAS DOS JUROS)
    	    }else { 
    	    	pg.getValorDoPagamento().equals(conta.getValorDoJuros());
    	    	conta.getCapitalInicial();
    	        conta.getSaldoDevedor();
    	    }

    }
    
}
