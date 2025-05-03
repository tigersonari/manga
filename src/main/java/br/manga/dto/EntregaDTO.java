package br.manga.dto;

import jakarta.validation.constraints.NotBlank;

public record EntregaDTO(
    @NotBlank(message = "adicione endereco")
    String endereco,
    
    @NotBlank(message = "é necessário adicionar o código de rastreio")
    String codigoRastreio,
    
    @NotBlank(message = "status não pode ser vazio")
    String status,
    
    Long pedidoId
) {}