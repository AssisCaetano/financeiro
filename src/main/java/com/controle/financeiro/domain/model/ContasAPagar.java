package com.controle.financeiro.domain.model;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_conta")
public class ContasAPagar {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idConta;


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataDoEmprestimo;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataDeVencimento;
	private String status;
	

//	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal reajuste = BigDecimal.ZERO;
	private BigDecimal capitalInicial = BigDecimal.ZERO;
	private BigDecimal taxaDeJuros = BigDecimal.ZERO;
	private BigDecimal valorDoJuros = BigDecimal.ZERO;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataNovoCredito;
	private BigDecimal novoCredito = BigDecimal.ZERO;
	private BigDecimal saldoDevedor = BigDecimal.ZERO;

	@Column(nullable = false)
	private BigDecimal valorPago = BigDecimal.ZERO;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataDePagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "solicitante_id")
	private Solicitante solicitante;


	public ContasAPagar() {
	}
	
	public ContasAPagar(UUID idConta, LocalDate dataDoEmprestimo, LocalDate dataDeVencimento, String status,
			BigDecimal reajuste, BigDecimal capitalInicial, BigDecimal taxaDeJuros, BigDecimal valorDoJuros,
			LocalDate dataNovoCredito, BigDecimal novoCredito, BigDecimal saldoDevedor, BigDecimal valorPago,
			LocalDate dataDePagamento, Solicitante solicitante) {
		super();
		this.idConta = idConta;
		this.dataDoEmprestimo = dataDoEmprestimo;
		this.dataDeVencimento = dataDeVencimento;
		this.status = status;
		this.reajuste = reajuste;
		this.capitalInicial = capitalInicial;
		this.taxaDeJuros = taxaDeJuros;
		this.valorDoJuros = valorDoJuros;
		this.dataNovoCredito = dataNovoCredito;
		this.novoCredito = novoCredito;
		this.saldoDevedor = saldoDevedor;
		this.valorPago = valorPago;
		this.dataDePagamento = dataDePagamento;
		this.solicitante = solicitante;
	}

    public UUID getIdConta() {
        return idConta;
    }

    public void setIdConta(UUID idConta) {
        this.idConta = idConta;
    }

    public LocalDate getDataDoEmprestimo() {
        return dataDoEmprestimo;
    }

    public void setDataDoEmprestimo(LocalDate dataDoEmprestimo) {
        this.dataDoEmprestimo = dataDoEmprestimo;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getReajuste() {
        return reajuste;
    }

    public void setReajuste(BigDecimal reajuste) {
        this.reajuste = reajuste;
    }

    public BigDecimal getCapitalInicial() {
        return capitalInicial;
    }

    public void setCapitalInicial(BigDecimal capitalInicial) {
        this.capitalInicial = capitalInicial;
    }

    public BigDecimal getTaxaDeJuros() {
        return taxaDeJuros;
    }

    public void setTaxaDeJuros(BigDecimal taxaDeJuros) {
        this.taxaDeJuros = taxaDeJuros;
    }

    public BigDecimal getValorDoJuros() {
        return valorDoJuros;
    }

    public void setValorDoJuros(BigDecimal valorDoJuros) {
        this.valorDoJuros = valorDoJuros;
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

    public BigDecimal getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(BigDecimal saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataDePagamento() {
        return dataDePagamento;
    }

    public void setDataDePagamento(LocalDate dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }
	
	
}
