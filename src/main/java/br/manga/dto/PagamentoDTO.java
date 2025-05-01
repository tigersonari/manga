package br.manga.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoDTO(
    @NotBlank String metodoPagamento,
    @NotNull String status,
    @NotNull LocalDate dataConfirmacao,
    Integer idPedido
) {}