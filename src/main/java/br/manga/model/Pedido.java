package br.manga.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Pedido extends DefaultEntity {

    @Positive
    @Column(nullable = false)
    private Long numeroPedido;

    @Column(nullable = false)
    private LocalDate data;

    @NotBlank
    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "pedido")
private List<PedidoManga> mangasComprados; /*ATUALIZADO COM A NOVA ENTIDADE DE PEDIDO-MANGA */


    @PositiveOrZero
    @Column(nullable = false)
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


  
    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PedidoManga> getMangasComprados() {
        return mangasComprados;
    }
    
    public void setMangasComprados(List<PedidoManga> mangasComprados) {
        this.mangasComprados = mangasComprados;
    }

    
    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}