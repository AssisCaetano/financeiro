package com.controle.financeiro.domain.repositore;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controle.financeiro.domain.model.ContasAPagar;

public interface ContasAPagarRepository extends JpaRepository<ContasAPagar, UUID>{

	//BUSCAR CONTA PELA DATA
	@Query("SELECT s FROM ContasAPagar s WHERE s.solicitante.nome like concat('%',?1, '%')")
	List<ContasAPagar> buscarContaPorSolicitante(String nome);
	
	//BUSCAR CONTA ENTRE DATAS
	@Query("SELECT c FROM ContasAPagar c WHERE c.dataDoEmprestimo BETWEEN c.dataDoEmprestimo AND c.dataDeVencimento")
	List<ContasAPagar> buscarContaEntreDatas (LocalDate dataDoEmprestimo, LocalDate dataDeVencimento);
}
