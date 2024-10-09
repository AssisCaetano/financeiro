package com.controle.financeiro.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String dataDoEmprestimo;
    private String dataDeVencimento;
    private double capitalInicial;
    private double juros;
    private double valorDoJuros;
    private double SaldoDevedor;
    
}
