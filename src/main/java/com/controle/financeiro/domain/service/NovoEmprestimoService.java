package com.controle.financeiro.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.NovoEmprestimo;
import com.controle.financeiro.domain.repositore.ContasAPagarRepository;
import com.controle.financeiro.domain.repositore.NovoEmprestimoRepository;
import com.controle.financeiro.domain.service.strategy.impl.ControleCreditoStrategy;
import com.controle.financeiro.dto.NovoEmprestimoDto;

@Service
public class NovoEmprestimoService {

    @Autowired
    private ContasAPagarRepository contasAPagarRepository;
    @Autowired
    private NovoEmprestimoRepository novoEmprestimoRepository;
    
    //MÉTODO RESPONSÁVEL POR SALVA UM NOVO VALOR A CONTA.
    public NovoEmprestimo saveEmprestimo(UUID id, NovoEmprestimoDto novoEmprestimoDto){
    	
    	Optional<ContasAPagar> contasOptional = contasAPagarRepository.findById(id);

        if (contasOptional.isEmpty()) {
            throw new RuntimeException("Conta a Pagar não encontrada com o ID: " + id);
        }
        
        //RECEBENDO TODOS OS DADOS DE CONTAS A PAGAR
        ContasAPagar contasAPagar = contasOptional.get();
        
        //ESTÂNCIA A CLASSE NOVO EMPRESTIMO PARA ACESSAR OS ATRIBUTOS E RECEBER A CONVERSÃO DOS DADOS
        NovoEmprestimo novoEmprestimo = new NovoEmprestimo();
        
        //REALIZA A CONVERSÃO DO DTO PARA A ENTITY
        BeanUtils.copyProperties(novoEmprestimoDto, novoEmprestimo); 

        novoEmprestimo.setContasAPagar(contasAPagar); 
        
        //ENVIO A DATA DO NOVO CRÉDETO E O VAOR CADASTRADO PARA SER EXIBIDO EM CONTAS A PAGAR
        contasAPagar.setDataNovoCredito(novoEmprestimo.getDataNovoCredito());
        contasAPagar.setNovoCredito(novoEmprestimo.getNovoCredito());
        
        //NESSE PONTO ÉINJETADO A REGRA DE NEGÓCIO PARA A ADICÇÃO DE UM NOVO VALOR
        ControleCreditoStrategy ccStrategy = new ControleCreditoStrategy();
        ccStrategy.processarCredito(novoEmprestimo, contasAPagar);

        contasAPagarRepository.save(contasAPagar);
        return novoEmprestimoRepository.save(novoEmprestimo);
    }
        
    	//MÉTODO PARA LISTA TODOS O CRÉDITOS ADICIONAIS
    	public List<NovoEmprestimo> listaTodosOsCreditosAdicionadosAConta(){	
    		return novoEmprestimoRepository.findAll();
    	}
}
