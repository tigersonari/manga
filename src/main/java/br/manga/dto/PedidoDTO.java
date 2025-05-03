package br.manga.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record PedidoDTO(
    @NotNull(message = "número do pedido não pode ser vazio")
    @Positive(message = "insira um número de pedido válido")
    Long numeroPedido,
    
    @NotNull(message = "data não pode ser nula")
    LocalDate data,
    
    @NotBlank(message = "status não pode ser vazio")
    String status,
    
    @NotNull(message = "valor total está vazio")
    @PositiveOrZero(message = "insira um valor válido")
    Double valorTotal,
    
    @NotNull(message = "usuário não pode ser nulo")
    Long usuarioId,
    
    List<ItemPedidoDTO> itens
) {}
