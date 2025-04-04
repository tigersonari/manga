package br.manga.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido extends DefaultEntity {

    @Column(nullable = false)
    private Long numeroPedido;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(
        name = "pedido_manga",
        joinColumns = @JoinColumn(name = "id_pedido"),
        inverseJoinColumns = @JoinColumn(name = "id_manga")
    )
    private List<Manga> mangasComprados;

    /*@OneToOne
    private Pagamento pagamento;

    @OneToOne
    private Entrega entrega;*/ /*verificar necessidade, tirar dúvida com professor */

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

    public List<Manga> getMangasComprados() {
        return mangasComprados;
    }

    public void setMangasComprados(List<Manga> mangasComprados) {
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

  /*   public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    } */

    
    
}
