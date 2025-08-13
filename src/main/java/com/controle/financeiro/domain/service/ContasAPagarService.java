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
import com.controle.financeiro.domain.model.NovoEmprestimo;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.repositore.ContasAPagarRepository;
import com.controle.financeiro.domain.repositore.PagamentoRepository;
import com.controle.financeiro.domain.service.strategy.impl.ControlePagamentoStrategy;
import com.controle.financeiro.dto.ContasAPagarDto;

@Service
public class ContasAPagarService {
    
    @Autowired
    private ContasAPagarRepository contasAPagarRepository;
 
   
    public ContasAPagar saveConta(ContasAPagar contasAPagar){
    	
        BigDecimal juroEmCimaDoCapitalInicial = contasAPagar.getCapitalInicial().multiply(contasAPagar.getTaxaDeJuros());
        BigDecimal saldoAtualizado = contasAPagar.getCapitalInicial().add(juroEmCimaDoCapitalInicial);
        
        ContasAPagar contas = new ContasAPagar();
        BeanUtils.copyProperties(contasAPagar, contas);
      
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
//            throw new RuntimeException("Dados não encontrado! ");
        }else{
            contasAPagarRepository.save(contas);
            return contasAPagarRepository.findById(id);
        }
		return contaByID;
    }
    public List<ContasAPagar> getContasAPagarAll(){
        List<ContasAPagar> listaAPagar = contasAPagarRepository.findAll();
        if(listaAPagar.isEmpty()){
//            throw new RuntimeException("Nenhuma informação encontrada!");
        }else{
            return listaAPagar;
        }
        return listaAPagar;
    }
    
    //Buscar conta pelo id
    public Optional<ContasAPagar> getContasAPagarById(UUID id, ContasAPagarDto contasAPagar){
        Optional<ContasAPagar> contaById = contasAPagarRepository.findById(id);
        BigDecimal capitalInicial;
        BigDecimal saldoReajuste;
        if(contaById.isEmpty()){
//            throw new RuntimeException("Dados não encontrado! ");
        }else if (contaById.get().getDataDeVencimento().isBefore(LocalDate.now())) {
            contaById.get().setStatus("INADIMPLENTE");
            //SE ATRASADO AO BUSCAR UM USUÁRIO POR ID TANTO O CAPITAL INICIAL QUANTO O SALDO DEVEDOR SERÁ ALTERADO E O VALOR DO JUROS SERÁ RECALCULADO
            capitalInicial  = contaById.get().getCapitalInicial().multiply(BigDecimal.ONE.add(contaById.get().getTaxaDeJuros()));
            contaById.get().setCapitalInicial(capitalInicial);
            
            saldoReajuste = contaById.get().getCapitalInicial().multiply(contaById.get().getTaxaDeJuros());
            contaById.get().setReajuste(saldoReajuste);
            
        }else {
            contaById.get().setStatus("ADIMPLENTE");
        }
        return contaById;
    }
    public Optional<ContasAPagar> deletarConta(UUID id){
        Optional<ContasAPagar> byId = contasAPagarRepository.findById(id);
        if(byId.isEmpty()){
//            throw new RuntimeException("Dados não encontrado! ");
        }else{
            contasAPagarRepository.deleteById(id);
            System.out.println("CONTA EXCLUÍDA!");
            return byId;
        }
        return byId;
    }
    
    //BUSCAR CONTAS POR SOICITANTE
    public List<ContasAPagar> buscarContaPorSolicitante(String nome){
    	return contasAPagarRepository.buscarContaPorSolicitante(nome);
    }
    
    //BUSCAR CONTAS ENTRE DATAS
    public List<ContasAPagar> buscarContaEntreDatas(LocalDate dataDoEmprestimo, LocalDate dataDeVencimento) {
        return contasAPagarRepository.buscarContaEntreDatas(dataDoEmprestimo, dataDeVencimento);
    }
}
