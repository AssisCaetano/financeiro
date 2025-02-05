package com.controle.financeiro.domain.service;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

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

    //cadastrar conta
    public ContasAPagar saveConta(ContasAPagarDto contasAPagarDto){

        double juroEmcimaDoCapitalInicial = contasAPagarDto.capitalInicial() * contasAPagarDto.taxaDeJuros();
        double saldoAtualizado = contasAPagarDto.capitalInicial() + juroEmcimaDoCapitalInicial;

        ContasAPagar contas = new ContasAPagar();
        BeanUtils.copyProperties(contasAPagarDto, contas);

        contas.setDataDoEmprestimo(contas.getDataDoEmprestimo().now());
        contas.setValorDoJuros(juroEmcimaDoCapitalInicial);
        contas.setSaldoDevedor(saldoAtualizado);

        return contasAPagarRepository.save(contas);
      
    }

      //atualiza uma conta
      public Optional<ContasAPagar> atualizarContasAPagar(UUID id, ContasAPagarDto contasAPagarDto){

        Optional<ContasAPagar> contaByID = contasAPagarRepository.findById(id);

        double juroEmcimaDoCapitalInicial = contasAPagarDto.capitalInicial() * contasAPagarDto.taxaDeJuros();
        double saldoAtualizado = contasAPagarDto.capitalInicial() + juroEmcimaDoCapitalInicial;

        ContasAPagar contas = contaByID.get();
        BeanUtils.copyProperties(contasAPagarDto, contas);

        contas.setValorDoJuros(juroEmcimaDoCapitalInicial);
        contas.setSaldoDevedor(saldoAtualizado);
        contasAPagarRepository.save(contas);

        return contasAPagarRepository.findById(id);
    }

    //retorna uma lista de contas
    public List<ContasAPagar> getContasAPagarAll(){
        List<ContasAPagar> listaAPagar = contasAPagarRepository.findAll();
        
        return listaAPagar;
    }

    //retorna uma unica conta
    public Optional<ContasAPagar> getContasAPagarById(UUID id){
        Optional<ContasAPagar> contaById = contasAPagarRepository.findById(id);
        double saldoReajustado;
        if(contaById.get().getDataDeVencimento().isBefore(LocalDate.now())){
            contaById.get().setStatus("INADIMPLENTE");
            saldoReajustado = contaById.get().getSaldoDevedor()+contaById.get().getValorDoJuros();
            contaById.get().setReajuste(saldoReajustado);
        }
        return contaById;
    }
    //deletando conta
    public Optional<ContasAPagar> deletarConta(UUID id){
        Optional<ContasAPagar> byId = contasAPagarRepository.findById(id);
        contasAPagarRepository.deleteById(id);
        return byId;
       
    }

}
