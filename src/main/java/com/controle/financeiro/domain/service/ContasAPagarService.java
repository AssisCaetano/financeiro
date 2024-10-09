package com.controle.financeiro.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.repositore.ContasAPagarRepository;
import com.controle.financeiro.dto.ContasAPagarDto;

@Service
public class ContasAPagarService {
    
    @Autowired
    private ContasAPagarRepository contasAPagarRepository;

    public ContasAPagar saveConta(ContasAPagarDto contasAPagarDto){

        double juroEmcimaDoCapitalInicial = contasAPagarDto.capitalInicial() * contasAPagarDto.juros();
        double saldoAtualizado = contasAPagarDto.capitalInicial() + juroEmcimaDoCapitalInicial;
        ContasAPagar contas = new ContasAPagar();
        BeanUtils.copyProperties(contasAPagarDto, contas);
        contas.setValorDoJuros(juroEmcimaDoCapitalInicial);
        contas.setSaldoDevedor(saldoAtualizado);

        return contasAPagarRepository.save(contas);
      
    }
}
