package com.controle.financeiro.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idSolicitante;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String telefone;
    private String cpf;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    private List<ContasAPagar> contasAPagar = new ArrayList<>();

    public Solicitante() {
    }

    public Solicitante(UUID idSolicitante, String nome, String sobrenome, String endereco, String telefone, String cpf,
            List<ContasAPagar> contasAPagar) {
        this.idSolicitante = idSolicitante;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public List<ContasAPagar> getContasAPagar() {
        return contasAPagar;
    }

    public void setContasAPagar(List<ContasAPagar> contasAPagar) {
        this.contasAPagar = contasAPagar;
    }

    

}
