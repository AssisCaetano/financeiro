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

    public Solicitante salvarUsuario(SolicitanteDto solicitanteDto){
        Solicitante solicitante = new Solicitante();
        BeanUtils.copyProperties(solicitanteDto, solicitante);
        Optional<Solicitante> usuario = solicitanteRepository.findByCpf(solicitante.getCpf());
        if(usuario.isPresent()){
            throw new RuntimeException("Os campos preenchido já existem!");
        }else{
            return solicitanteRepository.save(solicitante);
        }
    }
    public Optional<Solicitante> atualizaSolicitante(UUID id, SolicitanteDto solicitanteDto){
        Optional<Solicitante> atualizar = solicitanteRepository.findById(id);
        if(atualizar.isEmpty()){
            throw new RuntimeException("Preencha todos os campos! ");
        }else{
            Solicitante solicitante = atualizar.get();
            BeanUtils.copyProperties(solicitanteDto, solicitante);
            solicitanteRepository.save(solicitante);
            return solicitanteRepository.findById(id);
        }
    }
    public List<Solicitante> listarSolicitante(){
        List<Solicitante> users = solicitanteRepository.findAll();
        if(users.isEmpty()){
            throw new RuntimeException("Nenhuma informação encontrada!");
        }else{
            return solicitanteRepository.findAll();
        }
    }
    public Optional<Solicitante> buscaSolicitante(UUID id, SolicitanteDto solicitanteDto){
        Optional<Solicitante> localizar = solicitanteRepository.findById(id);
        if(localizar.isEmpty()){
            throw new RuntimeException("Preencha todos os campos! ");
        }else{
            return solicitanteRepository.findById(id);
        }
    }
    public Optional<Solicitante> deletaSolicitante(UUID id){
        Optional<Solicitante> deletarSolicante = solicitanteRepository.findById(id);
        if(deletarSolicante.isPresent()){
            solicitanteRepository.deleteById(id);
            return deletarSolicante;
        }else{
            throw new RuntimeException("Dados não encontrado! ");
        }
    }
    public Solicitante adicionaConta(UUID id, ContasAPagar contasAPagar){
        Optional<Solicitante> usr = solicitanteRepository.findById(id);
        if(usr.isPresent()){
            Solicitante solicitante = usr.get();
            solicitante.getContasAPagar().add(contasAPagar);
            return solicitanteRepository.save(solicitante);
        }else{
            throw new RuntimeException("Conta não adicionada");
        }
    }
}
