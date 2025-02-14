package com.controle.financeiro.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "conta")
public class ContasAPagar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConta;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataDoEmprestimo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataDeVencimento;
    private String status = "ADIPLENTE";
    private BigDecimal reajuste;
    private BigDecimal capitalInicial;
    private BigDecimal taxaDeJuros;
    private BigDecimal valorDoJuros;
    private BigDecimal SaldoDevedor;
    private BigDecimal valorPago;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataDePagamento;
    
    @ManyToOne
    @JoinColumn(name = "id_solicitante")
    private Solicitante solicitante;

}
