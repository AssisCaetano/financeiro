package com.controle.financeiro.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controle.financeiro.domain.model.Solicitante;
import com.controle.financeiro.domain.repositore.SolicitanteRepository;
import com.controle.financeiro.dto.SolicitanteDto;


@Service
public class SolicitanteService {

    @Autowired
    private SolicitanteRepository solicitanteRepository;
	
    //ESSE METODO É RESPONSAVEL POR SALVAR AS INFORMAÇÕES DO USUÁRIO NO BANCO
    public Solicitante salvarUsuario(SolicitanteDto solicitanteDto){
    	
    		Solicitante solicitante = new Solicitante();
            BeanUtils.copyProperties(solicitanteDto, solicitante);
            Solicitante validarCPF = solicitanteRepository.findByCpf(solicitante.getCpf());
        	if(validarCPF != null) {
        		throw new RuntimeException("CPF já cadastrado! ");
        	}else {
        		solicitanteRepository.save(solicitante);
        	}
		return solicitante;
    }
    
    //MÉTODO RESPONSAVÉL POR AUTALIZAR UM SOLICITANTE MAIS ANTES VERIFICA SE O ID NAO ESTÁ VAZIO
    public Optional<Solicitante> atualizaSolicitante(UUID id, SolicitanteDto solicitanteDto){
    	
        Optional<Solicitante> atualizar = solicitanteRepository.findById(id);
        if(atualizar.isEmpty()){
        }else{
            Solicitante solicitante = atualizar.get();
            BeanUtils.copyProperties(solicitanteDto, solicitante);
            solicitanteRepository.save(solicitante);
            return solicitanteRepository.findById(id);
        }
        return atualizar;
    }
    
    public List<Solicitante> listarSolicitante(){
        List<Solicitante> users = solicitanteRepository.findAll();
        if(users.isEmpty()){
        }else{
            return solicitanteRepository.findAll();
        }
		return users;
    }
    
    public Optional<Solicitante> buscaSolicitante(UUID id, SolicitanteDto solicitanteDto){
        Optional<Solicitante> localizar = solicitanteRepository.findById(id);
        if(localizar.isEmpty()){
        }else{
            return solicitanteRepository.findById(id);
        }
        return localizar;
    }
 // MÉTODO ATUALIZADO: Para buscar o Solicitante com suas contas carregadas
    @Transactional()
    public Optional<Solicitante> buscaSolicitanteComContas(UUID id){ // Parâmetro renomeado para consistência
        // Usando o novo nome do método e o parâmetro corrigido
        Optional<Solicitante> solicitanteOptional = solicitanteRepository.getSolicitanteWithContasById(id);
        if(solicitanteOptional.isEmpty()){
        } else {
            solicitanteOptional.ifPresent(solicitante -> solicitante.getContasAPagar().size());
        }
        return solicitanteOptional;
    }
    
    public Optional<Solicitante> deletaSolicitante(UUID id){
        Optional<Solicitante> deletarSolicante = solicitanteRepository.findById(id);
        if(deletarSolicante.isPresent()){
            solicitanteRepository.deleteById(id);
            return deletarSolicante;
        }else{
        }
        return deletaSolicitante(id);
    }
    
    public List<Solicitante> buscarPorNome(String nome){
    	return solicitanteRepository.buscarPorNome(nome);
    }
}
