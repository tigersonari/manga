
package br.manga.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_manga")
@IdClass(PedidoMangaId.class)
public class PedidoManga {

    @Id
    @Column(name = "id_pedido", nullable = false)
    private Long pedido;

    @Id
    @Column(name = "id_manga", nullable = false)
    private Long manga;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false, insertable = false, updatable = false)
    private Pedido pedidoEntity;

    @ManyToOne
    @JoinColumn(name = "id_manga", nullable = false, insertable = false, updatable = false)
    private Manga mangaEntity;

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public Long getManga() {
        return manga;
    }

    public void setManga(Long manga) {
        this.manga = manga;
    }

    public Pedido getPedidoEntity() {
        return pedidoEntity;
    }

    public void setPedidoEntity(Pedido pedidoEntity) {
        this.pedidoEntity = pedidoEntity;
        this.pedido = pedidoEntity != null ? pedidoEntity.getId() : null;
    }

    public Manga getMangaEntity() {
        return mangaEntity;
    }

    public void setMangaEntity(Manga mangaEntity) {
        this.mangaEntity = mangaEntity;
        this.manga = mangaEntity != null ? mangaEntity.getId() : null;
    }

}