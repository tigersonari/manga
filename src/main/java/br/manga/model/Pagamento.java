package br.manga.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;

@Entity
public class Pagamento extends DefaultEntity {

    @Column(nullable = false)
    private String metodoPagamento;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDate dataConfirmacao;

    @OneToOne /*? conferir depois*/
    @Column(nullable = false)
    @JoinTable(
        name = "pedido_pagamento",
        joinColumns = @JoinColumn(name = "id_pagamento"),
        inverseJoinColumns = @JoinColumn(name = "id_pedido")
    )
    private Long idPedido;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(LocalDate dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    

}
