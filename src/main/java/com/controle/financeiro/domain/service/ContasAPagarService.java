package com.controle.financeiro.domain.service;

import com.controle.financeiro.domain.model.ContasAPagar;

public class ContasAPagarService {
    
    public ContasAPagar saveConta(){
        ContasAPagar contasAPagar = new ContasAPagar();

        double jurosEmCimaDoCapitalInicial = contasAPagar.getCapitalInicial() * contasAPagar.getJuros();
        contasAPagar.setValorDoJuros(jurosEmCimaDoCapitalInicial);
        
        return saveConta();
    }
}
