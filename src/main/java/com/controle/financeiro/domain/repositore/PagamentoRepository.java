package com.controle.financeiro.domain.repositore;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controle.financeiro.domain.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID>{

//    @Query("SELECT p FROM Pagamento p JOIN p.contasAPagar c JOIN c.solicitante s WHERE s.id = :solicitanteId")
//    List<Pagamento> findPagamentosById(@Param ("solicitanteId") UUID solicitanteId);
    
}
