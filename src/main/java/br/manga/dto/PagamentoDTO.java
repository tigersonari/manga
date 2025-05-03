package br.manga.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoDTO(
    @NotBlank(message = "adicione método de pagamento")
    String metodoPagamento,
    
    @NotBlank(message = "defina o status do pagamento")
    String status,
    
    @NotNull(message = "data de confirmação vazoa")
    LocalDate dataConfirmacao,
    
    @NotNull(message = "pedido não pode ser nulo")
    Long pedidoId 
){}
