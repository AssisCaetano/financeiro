package com.controle.financeiro.domain.service.strategy;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;

public interface PagamentoStrategy {
    void resultadoPagamento(ContasAPagar contasAPagar, Pagamento pg);
}
