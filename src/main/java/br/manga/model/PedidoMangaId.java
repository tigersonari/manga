package br.manga.model;

import java.io.Serializable;
import java.util.Objects;

public class PedidoMangaId implements Serializable {

    private Long pedido;
    private Long manga;

    public PedidoMangaId() {}

    public PedidoMangaId(Long pedido, Long manga) {
        this.pedido = pedido;
        this.manga = manga;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoMangaId that = (PedidoMangaId) o;
        return Objects.equals(pedido, that.pedido) &&
               Objects.equals(manga, that.manga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, manga);
    }
}