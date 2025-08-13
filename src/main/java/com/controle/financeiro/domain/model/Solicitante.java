package com.controle.financeiro.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

@Entity
@Table( name = "tb_usuario")
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idSolicitante;
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    @Column(columnDefinition="varchar(255)")
    private String descricao;

    @OneToMany(mappedBy = "solicitante", orphanRemoval=true, fetch = FetchType.LAZY)
    private List<ContasAPagar> contasAPagar = new ArrayList<>();

    public Solicitante() {
    }
    
    
    public Solicitante(UUID idSolicitante, String nome, String endereco, String telefone, String cpf, String descricao,
			List<ContasAPagar> contasAPagar) {
		super();
		this.idSolicitante = idSolicitante;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.cpf = cpf;
		this.descricao = descricao;
		this.contasAPagar = contasAPagar;
	}

	public UUID getIdSolicitante() {
        return idSolicitante;
    }

	public void setIdSolicitante(UUID idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ContasAPagar> getContasAPagar() {
        return contasAPagar;
    }

    public void setContasAPagar(List<ContasAPagar> contasAPagar) {
        this.contasAPagar = contasAPagar;
    }

}
