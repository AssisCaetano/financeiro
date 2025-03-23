package com.controle.financeiro.domain.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        BigDecimal juroEmCimaDoCapitalInicial = contasAPagarDto.capitalInicial().multiply(contasAPagarDto.taxaDeJuros());
        BigDecimal saldoAtualizado = contasAPagarDto.capitalInicial().add(juroEmCimaDoCapitalInicial);
        
        ContasAPagar contas = new ContasAPagar();
        BeanUtils.copyProperties(contasAPagarDto, contas);
        
        contas.setDataDoEmprestimo(LocalDate.now());
        contas.setValorDoJuros(juroEmCimaDoCapitalInicial);
        contas.setSaldoDevedor(saldoAtualizado);
        contas.setStatus("ADIMPLENTE");

        return contasAPagarRepository.save(contas);
    }
    public Optional<ContasAPagar> atualizarContasAPagar(UUID id, ContasAPagarDto contasAPagarDto){

        BigDecimal juroEmCimaDoCapitalInicial = contasAPagarDto.capitalInicial().multiply(contasAPagarDto.taxaDeJuros());
        BigDecimal saldoAtualizado = contasAPagarDto.capitalInicial().add(juroEmCimaDoCapitalInicial);

        Optional<ContasAPagar> contaByID = contasAPagarRepository.findById(id);
        ContasAPagar contas = contaByID.get();
        BeanUtils.copyProperties(contasAPagarDto, contas);
        
        contas.setValorDoJuros(juroEmCimaDoCapitalInicial);
        contas.setSaldoDevedor(saldoAtualizado);
        
        if(contaByID.isEmpty()){
            throw new RuntimeException("Dados não encontrado! ");
        }else{
            contasAPagarRepository.save(contas);
            return contasAPagarRepository.findById(id);
        }
    }
    public List<ContasAPagar> getContasAPagarAll(){
        List<ContasAPagar> listaAPagar = contasAPagarRepository.findAll();
        if(listaAPagar.isEmpty()){
            throw new RuntimeException("Nenhuma informação encontrada!");
        }else{
            return listaAPagar;
        }
    }
    public Optional<ContasAPagar> getContasAPagarById(UUID id){
        Optional<ContasAPagar> contaById = contasAPagarRepository.findById(id);
        BigDecimal saldoReajustado;
        if(contaById.isEmpty()){
            throw new RuntimeException("Dados não encontrado! ");
        }else if (contaById.get().getDataDeVencimento().isBefore(LocalDate.now())) {
            contaById.get().setStatus("INADIMPLENTE");
            saldoReajustado  = contaById.get().getSaldoDevedor().add(contaById.get().getValorDoJuros());
            contaById.get().setReajuste(saldoReajustado);
        } else {
            contaById.get().setStatus("ADIMPLENTE");
        }
        return contaById;
    }
    public Optional<ContasAPagar> deletarConta(UUID id){
        Optional<ContasAPagar> byId = contasAPagarRepository.findById(id);
        if(byId.isEmpty()){
            throw new RuntimeException("Dados não encontrado! ");
        }else{
            contasAPagarRepository.deleteById(id);
            return byId;
        }
    }
}
