package com.controle.financeiro.domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.financeiro.domain.model.ContasAPagar;
//import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.NovoEmprestimo;
import com.controle.financeiro.domain.repositore.ContasAPagarRepository;
//import com.controle.financeiro.domain.repositore.ContasAPagarRepository;
import com.controle.financeiro.domain.repositore.NovoEmprestimoRepository;
import com.controle.financeiro.domain.service.strategy.impl.ControleCreditoStrategy;
//import com.controle.financeiro.domain.service.strategy.impl.ControleCreditoStrategy;
import com.controle.financeiro.dto.NovoEmprestimoDto;

@Service
public class NovoEmprestimoService {

    @Autowired
    private ContasAPagarRepository contasAPagarRepository;
    @Autowired
    private NovoEmprestimoRepository novoEmprestimoRepository;
    
    public NovoEmprestimo saveEmprestimo(UUID id, NovoEmprestimoDto novoEmprestimoDto){
    	
    	Optional<ContasAPagar> contasOptional = contasAPagarRepository.findById(id);

        if (contasOptional.isEmpty()) {
            throw new RuntimeException("Conta a Pagar não encontrada com o ID: " + id);
        }

        ContasAPagar contasAPagar = contasOptional.get();
        NovoEmprestimo novoEmprestimo = new NovoEmprestimo();
        
        BeanUtils.copyProperties(novoEmprestimoDto, novoEmprestimo); 

        novoEmprestimo.setContasAPagar(contasAPagar); 

        contasAPagar.setDataNovoCredito(novoEmprestimo.getDataNovoCredito());
        contasAPagar.setNovoCredito(novoEmprestimo.getNovoCredito());

        ControleCreditoStrategy ccStrategy = new ControleCreditoStrategy();
        ccStrategy.processarCredito(novoEmprestimo, contasAPagar); 

        contasAPagarRepository.save(contasAPagar);
        
        return novoEmprestimoRepository.save(novoEmprestimo);
    }
        

}
