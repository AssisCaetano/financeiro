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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

}
