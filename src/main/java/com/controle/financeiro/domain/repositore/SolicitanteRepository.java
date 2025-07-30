package com.controle.financeiro.domain.repositore;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controle.financeiro.domain.model.Solicitante;



public interface SolicitanteRepository extends JpaRepository<Solicitante, UUID>{
	
   Optional<Solicitante> findByCpf(String cpf);
     
   @Query("SELECT s FROM Solicitante s JOIN FETCH s.contasAPagar WHERE s.idSolicitante = :idSolicitante")
   Optional<Solicitante> getSolicitanteWithContasById(@Param("idSolicitante") UUID idSolicitante);


  
}
