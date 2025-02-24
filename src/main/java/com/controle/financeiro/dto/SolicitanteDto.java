package com.controle.financeiro.dto;

import jakarta.validation.constraints.NotBlank;

public record SolicitanteDto(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank String endereco, @NotBlank String telefone, @NotBlank String cpf) {
}
