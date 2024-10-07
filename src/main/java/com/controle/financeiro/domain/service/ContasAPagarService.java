package com.controle.financeiro.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.repositore.ContasAPagarRepository;

public class ContasAPagarService {
    
    @Autowired
    private ContasAPagarRepository contasAPagarRe;

    public ContasAPagar saveConta(){
        ContasAPagar contasAPagar = new ContasAPagar();
        double jurosEmCimaDoCapitalInicial = contasAPagar.getCapitalInicial() * contasAPagar.getJuros();
        contasAPagar.setValorDoJuros(jurosEmCimaDoCapitalInicial);
        double total = jurosEmCimaDoCapitalInicial + contasAPagar.getCapitalInicial();
        contasAPagar.setSaldoDevedor(total);

        contasAPagarRe.save(saveConta());
        return saveConta();
    }
}
