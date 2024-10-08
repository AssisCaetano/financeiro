package com.controle.financeiro.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.repositore.ContasAPagarRepository;

@Service
public class ContasAPagarService {
    
    @Autowired
    private ContasAPagarRepository contasAPagarRepository;

    public ContasAPagar saveConta(ContasAPagar contasAPagar){
        
        double juroEmcimaDoCapitalInicial = contasAPagar.getCapitalInicial() * contasAPagar.getJuros();
        contasAPagar.setValorDoJuros(juroEmcimaDoCapitalInicial);

        double saldoAtualizado = contasAPagar.getCapitalInicial() + contasAPagar.getValorDoJuros();
        contasAPagar.setSaldoDevedor(saldoAtualizado);
        
        return contasAPagarRepository.save(contasAPagar);
      
    }
}
