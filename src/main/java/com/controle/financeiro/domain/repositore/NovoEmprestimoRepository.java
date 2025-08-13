package com.controle.financeiro.domain.repositore;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controle.financeiro.domain.model.NovoEmprestimo;

public interface NovoEmprestimoRepository extends JpaRepository<NovoEmprestimo, UUID>{
    
}
