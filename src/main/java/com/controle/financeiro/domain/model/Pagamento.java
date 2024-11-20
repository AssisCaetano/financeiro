package com.controle.financeiro.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "pagamento")
public class Pagamento{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPagamento;
    private LocalDate dataDeVencimento;
    private double valorDivida;
    private double valorDoPagamento;
    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "idContas", nullable = true)
    private ContasAPagar contasAPagar;

   
}
