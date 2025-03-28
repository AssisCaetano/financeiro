package com.controle.financeiro.domain.service.strategy.impl;

import java.math.BigDecimal;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.service.strategy.PagamentoStrategy;

public class PagamentoComJuros implements PagamentoStrategy{

    @Override
    public void resultadoPagamento(ContasAPagar conta, Pagamento pg) {

        BigDecimal juros = conta.getSaldoDevedor().multiply(conta.getTaxaDeJuros());
        conta.setValorDoJuros(juros);

        // 2. Atualizar saldo devedor (saldo + juros)
        BigDecimal novoSaldo = conta.getSaldoDevedor().add(conta.getValorDoJuros());
        conta.setSaldoDevedor(novoSaldo);

        // 3. Verificar se o pagamento cobre os juros
        if (pg.getValorDoPagamento().compareTo(juros) > 0) {
        // Pagamento maior que os juros: abate do saldo
            BigDecimal saldoRestante = novoSaldo.subtract(pg.getValorDoPagamento());
            conta.setSaldoDevedor(saldoRestante);
        } else if (pg.getValorDoPagamento().equals(juros)) {
            conta.getSaldoDevedor();
        }
    }
    
}
