package com.controle.financeiro.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public record SolicitanteDto(@NotBlank String nome, @NotBlank String endereco, @NotBlank String telefone, @CPF String cpf, String descricao) {
}
