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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "caderneta")
public class ContasAPagar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConta;
    private LocalDate dataDoEmprestimo;
    private LocalDate dataDeVencimento;
    private String status = "ADIPLENTE";
    private double reajuste;
    private double capitalInicial;
    private double taxaDeJuros;
    private double valorDoJuros;
    private double SaldoDevedor;
    private double valorPago;
    private LocalDate dataDePagamento;
    
    @ManyToOne
    @JoinColumn(name = "id_solicitante")
    private Solicitante solicitante;

}
