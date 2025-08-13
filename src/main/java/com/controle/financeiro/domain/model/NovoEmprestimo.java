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

@Entity
@Table(schema = "opening", name = "tb_novo_credito")
public class NovoEmprestimo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCredito;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNovoCredito;
    private BigDecimal novoCredito;

    @ManyToOne
    @JoinColumn(name = "Credito", nullable = true)
    private ContasAPagar contasAPagar;

    public NovoEmprestimo() {
    }

    public NovoEmprestimo(UUID idCredito, LocalDate dataNovoCredito, BigDecimal novoCredito,
            ContasAPagar contasAPagar) {
        this.idCredito = idCredito;
        this.dataNovoCredito = dataNovoCredito;
        this.novoCredito = novoCredito;
        this.contasAPagar = contasAPagar;
    }
    
    

    public UUID getIdCredito() {
        return idCredito;
    }

	public void setIdCredito(UUID idCredito) {
        this.idCredito = idCredito;
    }

    public LocalDate getDataNovoCredito() {
        return dataNovoCredito;
    }

    public void setDataNovoCredito(LocalDate dataNovoCredito) {
        this.dataNovoCredito = dataNovoCredito;
    }

    public BigDecimal getNovoCredito() {
        return novoCredito;
    }

    public void setNovoCredito(BigDecimal novoCredito) {
        this.novoCredito = novoCredito;
    }

    public ContasAPagar getContasAPagar() {
        return contasAPagar;
    }

    public void setContasAPagar(ContasAPagar contasAPagar) {
        this.contasAPagar = contasAPagar;
    }

    
}
