package com.controle.financeiro.domain.service.strategy;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.NovoEmprestimo;

public interface CreditoStrategy {
    void lancamentoDeCredito(NovoEmprestimo credito, ContasAPagar contas);
}
