package com.controle.financeiro.domain.repositore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controle.financeiro.domain.model.Solicitante;



public interface SolicitanteRepository extends JpaRepository<Solicitante, UUID>{
	
	Solicitante findByCpf(String cpf);

	//BUSCA UM USUÁRIO E TODAS AS CONTAS QUE ESTIVEREM ASSOCUIADOS A ELE
   @Query("SELECT s FROM Solicitante s JOIN FETCH s.contasAPagar WHERE s.idSolicitante = :idSolicitante")
   Optional<Solicitante> getSolicitanteWithContasById(@Param("idSolicitante") UUID idSolicitante);

   //BUSCA USUÁRIO POR NOME
   @Query("SELECT n FROM Solicitante n WHERE n.nome like concat('%',?1,'%')")
   List<Solicitante> buscarPorNome(String nome);

 
}
