package com.controle.financeiro.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Solicitante;
import com.controle.financeiro.domain.repositore.SolicitanteRepository;
import com.controle.financeiro.dto.SolicitanteDto;

@Service
public class SolicitanteService {

    @Autowired
    private SolicitanteRepository solicitanteRepository;
    // Criação de usuário
    public Solicitante salvarUsuario(SolicitanteDto solicitanteDto){

        Solicitante solicitante = new Solicitante();
        BeanUtils.copyProperties(solicitanteDto, solicitante);

        Solicitante salvarUsuario = solicitanteRepository.save(solicitante);

        return salvarUsuario;
    }

    // Atualização de usuário
    public Optional<Solicitante> atualizaSolicitante(UUID id, SolicitanteDto solicitanteDto){

        Optional<Solicitante> atualizar = solicitanteRepository.findById(id);
        Solicitante solicitante = atualizar.get();
        BeanUtils.copyProperties(solicitanteDto, solicitante);

        solicitanteRepository.save(solicitante);

        return solicitanteRepository.findById(id);
    }

    // Retornando uma lista de Solicitante
    public List<Solicitante> listarSolicitante(){
        return solicitanteRepository.findAll();
    }

    // Buscando usuário pelo Id
    public Optional<Solicitante> buscaSolicitante(UUID id, SolicitanteDto solicitanteDto){
        Optional<Solicitante> localizar = solicitanteRepository.findById(id);
        return localizar;
    }

    // Deletando um solicitante
    public Optional<Solicitante> deletaSolicitante(UUID id){
        Optional<Solicitante> deletarSolicante = solicitanteRepository.findById(id);
        solicitanteRepository.deleteById(id);
        return deletarSolicante;
    }

    // Adiciona uma conta ao usuário
    public Solicitante adicionaConta(UUID id, ContasAPagar contasAPagar){
        Optional<Solicitante> conta = solicitanteRepository.findById(id);
        if(conta.isPresent()){
            Solicitante solicitante = conta.get();
            solicitante.getContasAPagar().add(contasAPagar);
            return solicitanteRepository.save(solicitante);
        }else{
            throw new RuntimeException("Não adicionado");
        }   
    }

    
}
